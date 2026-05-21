package br.com.fiap.gestao_residuos.dto;

import br.com.fiap.gestao_residuos.model.Residuo;

public record ResiduoExibicaoDTO(

        Long id,
        String nome,
        String tipo,
        Double peso
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
