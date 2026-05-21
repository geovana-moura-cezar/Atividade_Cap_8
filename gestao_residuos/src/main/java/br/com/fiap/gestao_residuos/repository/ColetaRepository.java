package br.com.fiap.gestao_residuos.repository;

import br.com.fiap.gestao_residuos.model.Coleta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColetaRepository extends JpaRepository<Coleta, Long> {
}
