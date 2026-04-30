package com.api.produtos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoCadastrarDTO(
        @NotBlank String nome,
        @NotBlank String descricao,
        @NotNull BigDecimal preco
) {
}
