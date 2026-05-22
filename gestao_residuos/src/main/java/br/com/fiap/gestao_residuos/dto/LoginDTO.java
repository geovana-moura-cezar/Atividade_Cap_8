package br.com.fiap.gestao_residuos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDTO (
        @NotBlank
        @Email(message = "O e-mail não é válido!")
        String email,

        @NotBlank
        @Size(min = 6, max = 20, message = "A senha deve conter entre 6 e 20 caracteres")
        String senha
){

}
