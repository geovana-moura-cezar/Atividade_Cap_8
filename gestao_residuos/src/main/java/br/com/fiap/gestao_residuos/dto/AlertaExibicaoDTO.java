package br.com.fiap.gestao_residuos.dto;

import br.com.fiap.gestao_residuos.model.Alerta;
import br.com.fiap.gestao_residuos.model.LocalColeta;
import br.com.fiap.gestao_residuos.repository.AlertaRepository;

import java.time.LocalDate;

public record AlertaExibicaoDTO(
        Long id,
        String mensagem,
        Integer nivel,
        LocalDate dataCriacao,
        Long localColetaId
) {
    public AlertaExibicaoDTO(Alerta alerta){
        this(
                alerta.getId(),
                alerta.getMensagem(),
                alerta.getNivel(),
                alerta.getDataCriacao(),
                alerta.getLocalColeta().getId()
        );
    }
}
