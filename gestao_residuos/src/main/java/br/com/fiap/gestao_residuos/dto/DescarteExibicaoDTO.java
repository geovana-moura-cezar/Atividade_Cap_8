package br.com.fiap.gestao_residuos.dto;

import br.com.fiap.gestao_residuos.model.Descarte;
import br.com.fiap.gestao_residuos.model.LocalColeta;
import br.com.fiap.gestao_residuos.model.Residuo;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DescarteExibicaoDTO(
        Long id,
        LocalDate dataDescarte,
        BigDecimal quantidade,
        String status,
        String residuoNome,
        String enderecoColeta
) {
    public DescarteExibicaoDTO(Descarte descarte){
        this(
                descarte.getId(),
                descarte.getDataDescarte(),
                descarte.getQuantidade(),
                descarte.getStatus(),
                descarte.getResiduo().getNome(),
                descarte.getLocalColeta().getEndereco()
        );
    }
}
