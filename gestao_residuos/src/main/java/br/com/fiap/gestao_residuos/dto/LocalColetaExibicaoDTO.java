package br.com.fiap.gestao_residuos.dto;

import br.com.fiap.gestao_residuos.model.LocalColeta;

import java.math.BigDecimal;

public record LocalColetaExibicaoDTO(
        Long id,
        String nome,
        String endereco,
        BigDecimal capacidadeMaxima
) {
    public LocalColetaExibicaoDTO(LocalColeta localColeta){
        this(
                localColeta.getId(),
                localColeta.getNome(),
                localColeta.getEndereco(),
                localColeta.getCapacidadeMaxima()
        );
    }
}
