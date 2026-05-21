package br.com.fiap.gestao_residuos.dto;

import br.com.fiap.gestao_residuos.model.LocalColeta;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ColetaCadastroDTO(

        Long id,
        String status,

        @NotNull
        LocalDate dataSolicitacao,

        @NotNull
        Long localColetaId
) {
}
