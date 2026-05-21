package br.com.fiap.gestao_residuos.exception;

import java.time.LocalDateTime;

public record ErroRespostaDTO(
        LocalDateTime timestamp,
        Integer status,
        String erro,
        String mensagem,
        String caminho
) {
}