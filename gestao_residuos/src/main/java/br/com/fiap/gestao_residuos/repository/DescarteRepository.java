package br.com.fiap.gestao_residuos.repository;

import br.com.fiap.gestao_residuos.model.Descarte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescarteRepository extends JpaRepository<Descarte, Long> {
}
