package br.com.fiap.gestao_residuos.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ColetaCadastroDTO(

        Long id,
        String status,

        @NotNull
        LocalDate dataSolicitacao,

        LocalDate dataRealizacao,

        @NotNull
        Long localColetaId
) {
}
