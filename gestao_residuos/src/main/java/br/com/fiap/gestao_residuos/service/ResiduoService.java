package br.com.fiap.gestao_residuos.service;

import br.com.fiap.gestao_residuos.dto.ResiduoCadastroDTO;
import br.com.fiap.gestao_residuos.dto.ResiduoExibicaoDTO;
import br.com.fiap.gestao_residuos.model.Residuo;
import br.com.fiap.gestao_residuos.repository.ResiduoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResiduoService {

    @Autowired
    private ResiduoRepository repository;

    //LISTAR TODOS
    public Page<ResiduoExibicaoDTO> listarTodos(Pageable paginacao){
        return repository
                .findAll(paginacao)
                .map(ResiduoExibicaoDTO::new);
    }

    //BUSCAR POR ID
    public ResiduoExibicaoDTO buscarPorId(Long id){
        Optional<Residuo> residuoOptional = repository.findById(id);

        if (residuoOptional.isPresent()){
            return new ResiduoExibicaoDTO(residuoOptional.get());
        } else {
            throw new RuntimeException("Residuo não encontrado!");
        }
    }

    // CADASTRAR
    @Transactional
    public ResiduoExibicaoDTO cadastrar (ResiduoCadastroDTO residuoCadastroDTO){
        Residuo residuo = new Residuo();
        BeanUtils.copyProperties(residuoCadastroDTO, residuo);

        Residuo residuoSalvo = repository.save(residuo);

        return new ResiduoExibicaoDTO(residuoSalvo);
    }

    //ATUALIZAR
    @Transactional
    public ResiduoExibicaoDTO atualizar (ResiduoCadastroDTO residuoCadastroDTO){
        Optional<Residuo> residuoOptional = repository.findById(residuoCadastroDTO.id());

        if (residuoOptional.isPresent()){
            Residuo residuo = new Residuo();
            BeanUtils.copyProperties(residuoCadastroDTO, residuo);

            return new ResiduoExibicaoDTO(repository.save(residuo));
        } else {
            throw new RuntimeException("Residuo não encontrado!");
        }
    }

    //EXCLUIR
    @Transactional
    public void excluir(Long id){
        Optional<Residuo> residuoOptional = repository.findById(id);

        if (residuoOptional.isPresent()){
            repository.delete(residuoOptional.get());
        } else {
            throw new RuntimeException("Residuo não encontrado!");
        }
    }
}
