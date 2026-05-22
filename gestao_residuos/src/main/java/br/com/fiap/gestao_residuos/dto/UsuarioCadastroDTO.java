package br.com.fiap.gestao_residuos.dto;

import br.com.fiap.gestao_residuos.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCadastroDTO(

        Long id,

        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "E-mail é obrigatório")
        @Email
        String email,

        @NotBlank(message = "Senha é obrigatório")
        @Size(min = 6, max = 20, message = "A senha deve conter entre 6e 20 caracteres!")
        String senha,

        Role role
) {
}
