package br.com.fiap.gestao_residuos.repository;

import br.com.fiap.gestao_residuos.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {
}
