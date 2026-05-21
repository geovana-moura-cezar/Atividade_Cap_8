package br.com.fiap.gestao_residuos.dto;

import br.com.fiap.gestao_residuos.model.Role;
import br.com.fiap.gestao_residuos.model.Usuario;

public record UsuarioExibicaoDTO(
        Long id,
        String nome,
        String email,
        Role role
) {
    public UsuarioExibicaoDTO(Usuario usuario){
        this(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getRole()
        );
    }
}
