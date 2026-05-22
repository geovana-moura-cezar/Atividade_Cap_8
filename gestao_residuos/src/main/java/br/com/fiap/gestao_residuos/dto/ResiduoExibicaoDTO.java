package br.com.fiap.gestao_residuos.dto;

import br.com.fiap.gestao_residuos.model.Residuo;

import java.math.BigDecimal;

public record ResiduoExibicaoDTO(

        Long id,
        String nome,
        String tipo,
        BigDecimal peso
) {
    public ResiduoExibicaoDTO (Residuo residuo){
        this(
                residuo.getId(),
                residuo.getNome(),
                residuo.getTipo(),
                residuo.getPeso()
        );
    }
}
