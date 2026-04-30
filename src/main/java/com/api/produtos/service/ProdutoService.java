package com.api.produtos.service;

import com.api.produtos.dto.ProdutoAtualizarDTO;
import com.api.produtos.models.Produto;
import com.api.produtos.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto cadastrarProduto(Produto dados) {
        return produtoRepository.save(dados);
    }

    public List<Produto> cadastrarProdutos(List<Produto> produtos) {
        return produtoRepository.saveAll(produtos);
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Page<Produto> listarProdutosAtivos(Pageable paginacao) {
        return produtoRepository.findAllByAtivoTrue(paginacao);
    }

    public Optional<Produto> buscarProdutoPorId(Long id) {
        return produtoRepository.findById(id);
    }

    @Transactional
    public Optional<Produto> atualizarProduto(Long id, ProdutoAtualizarDTO dados) {
        return buscarProdutoPorId(id)
                .map(produto -> {
                    produto.atualizarDados(dados);
                    return produto;
                });
    }

    // Exclusão permanente
    @Transactional
    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }

    // Exclusão Lógica
    @Transactional
    public void exclusaoLogicaProduto(Long id) {
            produtoRepository.findById(id).ifPresent(produto -> {
                produto.desativarProduto();
                produtoRepository.save(produto);
            });
//        produtoRepository.findById(id).ifPresent(Produto::desativarProduto);
    }

    public void ativarProduto(Long id) {
        produtoRepository.findById(id).ifPresent(produto -> {
            produto.ativarProduto();
            produtoRepository.save(produto);
        });
    }

}
