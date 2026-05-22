package br.com.fiap.gestao_residuos.repository;

import br.com.fiap.gestao_residuos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

    UserDetails findByEmail(String email);

}
