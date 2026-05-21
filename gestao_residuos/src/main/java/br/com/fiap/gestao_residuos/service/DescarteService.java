package br.com.fiap.gestao_residuos.service;

import br.com.fiap.gestao_residuos.dto.DescarteCadastroDTO;
import br.com.fiap.gestao_residuos.dto.DescarteExibicaoDTO;
import br.com.fiap.gestao_residuos.model.Descarte;
import br.com.fiap.gestao_residuos.repository.DescarteRepository;
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
    public DescarteExibicaoDTO cadastrar (DescarteCadastroDTO DescarteCadastroDTO){
        Descarte descarte = new Descarte();
        BeanUtils.copyProperties(DescarteCadastroDTO, descarte);

        Descarte descarteSalvo = repository.save(descarte);

        return new DescarteExibicaoDTO(descarteSalvo);
    }

    //ATUALIZAR
    @Transactional
    public DescarteExibicaoDTO atualizar (DescarteCadastroDTO descarteCadastroDTO){
        Optional<Descarte> descarteOptional = repository.findById(descarteCadastroDTO.id());

        if (descarteOptional.isPresent()){
            Descarte descarte = new Descarte();
            BeanUtils.copyProperties(descarteCadastroDTO, descarte);

            return new DescarteExibicaoDTO(repository.save(descarte));
        } else {
            throw new RuntimeException("Descarte não encontrado!");
        }
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
