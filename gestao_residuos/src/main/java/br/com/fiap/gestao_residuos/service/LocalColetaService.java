package br.com.fiap.gestao_residuos.service;

import br.com.fiap.gestao_residuos.dto.LocalColetaCadastroDTO;
import br.com.fiap.gestao_residuos.dto.LocalColetaExibicaoDTO;
import br.com.fiap.gestao_residuos.model.LocalColeta;
import br.com.fiap.gestao_residuos.repository.LocalColetaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LocalColetaService {

    @Autowired
    private LocalColetaRepository repository;

    //LISTAR TODOS
    public Page<LocalColetaExibicaoDTO> listarTodos(Pageable paginacao){
        return repository
                .findAll(paginacao)
                .map(LocalColetaExibicaoDTO::new);
    }

    //BUSCAR POR ID
    public LocalColetaExibicaoDTO buscarPorId(Long id){
        Optional<LocalColeta> localColetaOptional = repository.findById(id);

        if (localColetaOptional.isPresent()){
            return new LocalColetaExibicaoDTO(localColetaOptional.get());
        } else {
            throw new RuntimeException("Local de Coleta não encontrado!");
        }
    }

    // CADASTRAR
    @Transactional
    public LocalColetaExibicaoDTO cadastrar (LocalColetaCadastroDTO localColetaCadastroDTO){
        LocalColeta localColeta = new LocalColeta();
        BeanUtils.copyProperties(localColetaCadastroDTO, localColeta);

        LocalColeta LocalColetaSalvo = repository.save(localColeta);

        return new LocalColetaExibicaoDTO(LocalColetaSalvo);
    }

    //ATUALIZAR
    @Transactional
    public LocalColetaExibicaoDTO atualizar (LocalColetaCadastroDTO localColetaCadastroDTO){
        Optional<LocalColeta> localColetaOptional = repository.findById(localColetaCadastroDTO.id());

        if (localColetaOptional.isPresent()){
            LocalColeta LocalColeta = new LocalColeta();
            BeanUtils.copyProperties(localColetaCadastroDTO, LocalColeta);

            return new LocalColetaExibicaoDTO(repository.save(LocalColeta));
        } else {
            throw new RuntimeException("Local de Coleta não encontrado!");
        }
    }

    //EXCLUIR
    @Transactional
    public void excluir(Long id){
        Optional<LocalColeta> localColetaOptional = repository.findById(id);

        if (localColetaOptional.isPresent()){
            repository.delete(localColetaOptional.get());
        } else {
            throw new RuntimeException("Local de Coleta não encontrado!");
        }
    }
    
}
