package br.com.fiap.gestao_residuos.repository;

import br.com.fiap.gestao_residuos.model.LocalColeta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalColetaRepository extends JpaRepository<LocalColeta, Long> {
}
