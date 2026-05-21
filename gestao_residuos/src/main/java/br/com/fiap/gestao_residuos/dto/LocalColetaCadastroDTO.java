package br.com.fiap.gestao_residuos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LocalColetaCadastroDTO(

        Long id,

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "Endereço é obrigatório")
        String endereco,

        @NotNull
        Double capacidadeMaxima
) {
}
