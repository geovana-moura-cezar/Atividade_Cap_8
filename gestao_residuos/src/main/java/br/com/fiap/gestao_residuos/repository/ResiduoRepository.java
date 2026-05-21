package br.com.fiap.gestao_residuos.repository;

import br.com.fiap.gestao_residuos.model.Residuo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResiduoRepository extends JpaRepository<Residuo, Long> {
}
