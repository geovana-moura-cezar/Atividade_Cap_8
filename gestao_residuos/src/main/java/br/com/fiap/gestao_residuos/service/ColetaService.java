package br.com.fiap.gestao_residuos.service;

import br.com.fiap.gestao_residuos.dto.ColetaCadastroDTO;
import br.com.fiap.gestao_residuos.dto.ColetaExibicaoDTO;
import br.com.fiap.gestao_residuos.model.Coleta;
import br.com.fiap.gestao_residuos.model.LocalColeta;
import br.com.fiap.gestao_residuos.repository.ColetaRepository;
import br.com.fiap.gestao_residuos.repository.LocalColetaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ColetaService {

    @Autowired
    private ColetaRepository repository;

    @Autowired
    private LocalColetaRepository localColetaRepository;

    //LISTAR TODOS
    public Page<ColetaExibicaoDTO> listarTodos(Pageable paginacao){
        return repository
                .findAll(paginacao)
                .map(ColetaExibicaoDTO::new);
    }

    //BUSCAR POR ID
    public ColetaExibicaoDTO buscarPorId(Long id){
        Optional<Coleta> coletaoOptional = repository.findById(id);

        if (coletaoOptional.isPresent()){
            return new ColetaExibicaoDTO(coletaoOptional.get());
        } else {
            throw new RuntimeException("Coleta não encontrada!");
        }
    }

    // CADASTRAR
    @Transactional
    public ColetaExibicaoDTO cadastrar (ColetaCadastroDTO coletaCadastroDTO){

        LocalColeta localColeta = localColetaRepository.findById(coletaCadastroDTO.localColetaId())
                .orElseThrow(() -> new RuntimeException("Local de coleta não encontrado"));
        Coleta coleta = new Coleta();

        BeanUtils.copyProperties(coletaCadastroDTO, coleta);

        coleta.setLocalColeta(localColeta);

        Coleta coletaSalva = repository.save(coleta);

        return new ColetaExibicaoDTO(coletaSalva);
    }

    //ATUALIZAR
    @Transactional
    public ColetaExibicaoDTO atualizar(ColetaCadastroDTO coletaCadastroDTO){

        Coleta coleta = repository.findById(coletaCadastroDTO.id())
                .orElseThrow(() -> new RuntimeException("Coleta não encontrada"));

        LocalColeta localColeta = localColetaRepository
                .findById(coletaCadastroDTO.localColetaId())
                .orElseThrow(() -> new RuntimeException("Local de coleta não encontrado"));

        coleta.setDataSolicitacao(coletaCadastroDTO.dataSolicitacao());
        coleta.setDataRealizacao(coletaCadastroDTO.dataRealizacao());
        coleta.setStatus(coletaCadastroDTO.status());

        coleta.setLocalColeta(localColeta);

        Coleta coletaSalva = repository.save(coleta);

        return new ColetaExibicaoDTO(coletaSalva);
    }

    //EXCLUIR
    @Transactional
    public void excluir(Long id){
        Optional<Coleta> coletaOptional = repository.findById(id);

        if (coletaOptional.isPresent()){
            repository.delete(coletaOptional.get());
        } else {
            throw new RuntimeException("Coleta não encontrada!");
        }
    }
}
