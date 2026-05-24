package br.com.fiap.gestao_residuos.service;

import br.com.fiap.gestao_residuos.dto.AlertaExibicaoDTO;
import br.com.fiap.gestao_residuos.model.Alerta;
import br.com.fiap.gestao_residuos.repository.AlertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlertaService {

    @Autowired
    private AlertaRepository repository;

    //LISTAR TODOS
    public Page<AlertaExibicaoDTO> listarTodos(Pageable paginacao){
        return repository
                .findAll(paginacao)
                .map(AlertaExibicaoDTO::new);
    }

    //BUSCAR POR ID
    public AlertaExibicaoDTO buscarPorId(Long id){
        Optional<Alerta> alertaOptional = repository.findById(id);

        if (alertaOptional.isPresent()){
            return new AlertaExibicaoDTO(alertaOptional.get());
        } else {
            throw new RuntimeException("Alerta não encontrado!");
        }
    }
}
