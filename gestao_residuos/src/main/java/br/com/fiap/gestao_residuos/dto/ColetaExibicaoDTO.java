package br.com.fiap.gestao_residuos.dto;

import br.com.fiap.gestao_residuos.model.Coleta;
import br.com.fiap.gestao_residuos.model.LocalColeta;

import java.time.LocalDate;

public record ColetaExibicaoDTO(
        Long id,
        String status,
        LocalDate dataSolicitacao,
        LocalDate dataRealizacao,
        LocalColeta localColeta
) {
    public ColetaExibicaoDTO(Coleta coleta){
        this(
                coleta.getId(),
                coleta.getStatus(),
                coleta.getDataSolicitacao(),
                coleta.getDataRealizacao(),
                coleta.getLocalColeta()
        );
    }
}
