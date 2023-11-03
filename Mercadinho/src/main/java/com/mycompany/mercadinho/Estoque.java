package com.mycompany.mercadinho;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estoque {

    private Map<Produto, Integer> produtosQuantidade;

    public Estoque() {
        produtosQuantidade = new HashMap<>();
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        if (quantidade <= 0) {
            System.out.println("A quantidade deve ser maior que zero.");
            return;
        }

        produtosQuantidade.put(produto, produtosQuantidade.getOrDefault(produto, 0) + quantidade);
        System.out.println(quantidade + " unidades de " + produto.getNomeProduto() + " adicionadas ao estoque.");
    }

    public void removerProduto(Produto produto, int quantidade) {
        if (quantidade <= 0) {
            System.out.println("A quantidade deve ser maior que zero.");
            return;
        }

        if (produtosQuantidade.containsKey(produto)) {
            int quantidadeExistente = produtosQuantidade.get(produto);
            if (quantidadeExistente >= quantidade) {
                produtosQuantidade.put(produto, quantidadeExistente - quantidade);
                System.out.println(quantidade + " unidades de " + produto.getNomeProduto() + " removidas do estoque.");
            } else {
                System.out.println("Quantidade insuficiente em estoque para remover.");
            }
        } else {
            System.out.println("Produto não encontrado em estoque.");
        }
    }

    public int verificarEstoque(Produto produto) {
        int quantidade = produtosQuantidade.getOrDefault(produto, 0);
        System.out.println("Quantidade de " + produto.getNomeProduto() + " em estoque: " + quantidade + " unidades.");
        return quantidade;
    }

    public void exibirEstoque() {
        System.out.println("=== Estoque Atual ===");
        for (Map.Entry<Produto, Integer> entry : produtosQuantidade.entrySet()) {
            Produto produto = entry.getKey();
            int quantidade = entry.getValue();
            System.out.println(produto.toString() + " - Quantidade em estoque: " + quantidade + " unidades.");
        }
    }

    public Produto encontrarProdutoPorID(int produtoId) {
        for (Produto produto : produtosQuantidade.keySet()) {
            if (produto.getId() == produtoId) {
                return produto;
            }
        }
        return null; // Retorna nulo se o produto não for encontrado
    }

    public List<Produto> getProdutos() {
        List<Produto> listaProdutos = new ArrayList<>(produtosQuantidade.keySet());
        return listaProdutos;
    }



}
