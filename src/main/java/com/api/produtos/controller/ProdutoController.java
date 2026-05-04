package com.api.produtos.controller;

import com.api.produtos.dto.ProdutoAtualizarDTO;
import com.api.produtos.dto.ProdutoCadastrarDTO;
import com.api.produtos.dto.ProdutoListagemDTO;
import com.api.produtos.models.Produto;
import com.api.produtos.service.ProdutoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody @Valid ProdutoCadastrarDTO dados) {
        Produto produto = produtoService.cadastrarProduto(new Produto(dados));
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @PostMapping("/lote")
    @Transactional
    public ResponseEntity<List<Produto>> cadastrarProdutos(@RequestBody @Valid List<ProdutoCadastrarDTO> dados) {
        List<Produto> produtos = dados.stream()
                .map(Produto::new)
                .toList();
        List<Produto> produtosCadastrados = produtoService.cadastrarProdutos(produtos);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtosCadastrados);
    }

    @GetMapping
    public ResponseEntity<Page<ProdutoListagemDTO>> listarProdutos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        try {
            Page<Produto> produtos = produtoService.listarProdutosAtivos(paginacao);
            Page<ProdutoListagemDTO> dados = produtos.map(ProdutoListagemDTO::new);
            return ResponseEntity.ok(dados);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoListagemDTO> buscarProdutoPorId(@PathVariable("id") Long id) {
        return produtoService.buscarProdutoPorId(id)
                .map(ProdutoListagemDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ProdutoListagemDTO> atualizarProduto(
            @PathVariable("id") Long id,
            @RequestBody @Valid ProdutoAtualizarDTO dados
    ) {
        return produtoService.atualizarProduto(id, dados)
                .map(produto -> ResponseEntity.ok(new ProdutoListagemDTO(produto)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        try {
            produtoService.exclusaoLogicaProduto(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ativar/{id}")
    public ResponseEntity<Void> ativarProduto(@PathVariable Long id) {
        try {
            produtoService.ativarProduto(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
