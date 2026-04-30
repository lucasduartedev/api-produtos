package com.api.produtos.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoAtualizarDTO(
        String nome,
        String drescricao,
        BigDecimal preco
) {
}
