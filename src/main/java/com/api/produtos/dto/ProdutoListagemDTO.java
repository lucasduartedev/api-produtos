package com.api.produtos.dto;

import com.api.produtos.models.Produto;

import java.math.BigDecimal;

public record ProdutoListagemDTO(Long id, String nome, String descricao, BigDecimal preco) {

    public ProdutoListagemDTO(Produto produto) {
        this(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco()
        );
    }

}
