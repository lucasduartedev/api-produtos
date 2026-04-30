package com.api.produtos.models;

import com.api.produtos.dto.ProdutoAtualizarDTO;
import com.api.produtos.dto.ProdutoCadastrarDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "preco")
    private BigDecimal preco;

    @Column(name = "ativo")
    private boolean ativo;

    // Contrutor
    public Produto(ProdutoCadastrarDTO dados) {
        this.nome = dados.nome();
        this.descricao = dados.descricao();
        this.preco = dados.preco();
        this.ativo = true;
    }

    // Métodos
    public void atualizarDados(ProdutoAtualizarDTO dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.drescricao() != null) {
            this.descricao = dados.drescricao();
        }
        if (dados.preco() != null) {
            this.preco = dados.preco();
        }
    }

    // Exclusão Lógica
    public void desativarProduto() {
        this.ativo = false;
    }

    public void ativarProduto() {
        this.ativo = true;
    }
}
