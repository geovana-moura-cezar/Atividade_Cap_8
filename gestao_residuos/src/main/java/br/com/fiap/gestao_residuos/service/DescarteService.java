package br.com.fiap.gestao_residuos.service;

import br.com.fiap.gestao_residuos.dto.ColetaExibicaoDTO;
import br.com.fiap.gestao_residuos.dto.DescarteCadastroDTO;
import br.com.fiap.gestao_residuos.dto.DescarteExibicaoDTO;
import br.com.fiap.gestao_residuos.model.Descarte;
import br.com.fiap.gestao_residuos.model.LocalColeta;
import br.com.fiap.gestao_residuos.model.Residuo;
import br.com.fiap.gestao_residuos.repository.DescarteRepository;
import br.com.fiap.gestao_residuos.repository.LocalColetaRepository;
import br.com.fiap.gestao_residuos.repository.ResiduoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DescarteService {
    
    @Autowired
    private DescarteRepository repository;

    @Autowired
    private ResiduoRepository residuoRepository;

    @Autowired
    private LocalColetaRepository localColetaRepository;
    
    //LISTAR TODOS
    public Page<DescarteExibicaoDTO> listarTodos(Pageable paginacao){
        return repository
                .findAll(paginacao)
                .map(DescarteExibicaoDTO::new);
    }

    //BUSCAR POR ID
    public DescarteExibicaoDTO buscarPorId(Long id){
        Optional<Descarte> descarteOptional = repository.findById(id);

        if (descarteOptional.isPresent()){
            return new DescarteExibicaoDTO(descarteOptional.get());
        } else {
            throw new RuntimeException("Descarte não encontrado!");
        }
    }

    // CADASTRAR
    @Transactional
    public DescarteExibicaoDTO cadastrar (DescarteCadastroDTO descarteCadastroDTO){

        Residuo residuo = residuoRepository.findById(descarteCadastroDTO.residuoId())
                .orElseThrow(() -> new RuntimeException("Resíduo não encontrado"));

        LocalColeta localColeta = localColetaRepository.findById(descarteCadastroDTO.localColetaId())
                .orElseThrow(() -> new RuntimeException("Local de coleta não encontrado"));

        Descarte descarte = new Descarte();

        BeanUtils.copyProperties(descarteCadastroDTO, descarte);

        descarte.setResiduo(residuo);
        descarte.setLocalColeta(localColeta);

        Descarte descarteSalvo = repository.save(descarte);

        return new DescarteExibicaoDTO(descarteSalvo);
    }

    //ATUALIZAR
    @Transactional
    public DescarteExibicaoDTO atualizar (DescarteCadastroDTO descarteCadastroDTO){
        Descarte descarte = repository.findById(descarteCadastroDTO.id())
                .orElseThrow(() -> new RuntimeException("Descarte não encontrada"));

        Residuo residuo = residuoRepository
                .findById(descarteCadastroDTO.residuoId())
                .orElseThrow(() -> new RuntimeException("Residuo de coleta não encontrado"));


        LocalColeta localColeta = localColetaRepository
                .findById(descarteCadastroDTO.localColetaId())
                .orElseThrow(() -> new RuntimeException("Local de coleta não encontrado"));

        descarte.setStatus(descarteCadastroDTO.status());

        descarte.setResiduo(residuo);
        descarte.setLocalColeta(localColeta);

        Descarte descarteSalvo = repository.save(descarte);

        return new DescarteExibicaoDTO(descarteSalvo);
    }

    //EXCLUIR
    @Transactional
    public void excluir(Long id){
        Optional<Descarte> descarteOptional = repository.findById(id);

        if (descarteOptional.isPresent()){
            repository.delete(descarteOptional.get());
        } else {
            throw new RuntimeException("Descarte não encontrado!");
        }
    }
}
