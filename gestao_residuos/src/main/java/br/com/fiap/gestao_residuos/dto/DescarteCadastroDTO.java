package br.com.fiap.gestao_residuos.dto;

import br.com.fiap.gestao_residuos.model.LocalColeta;
import br.com.fiap.gestao_residuos.model.Residuo;
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
