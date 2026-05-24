package br.com.fiap.gestao_residuos.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DescarteCadastroDTO(

        Long id,
        LocalDate dataDescarte,

        @NotNull
        BigDecimal quantidade,

        String status,

        @NotNull
        Long residuoId,

        @NotNull
        Long localColetaId
) {
}
