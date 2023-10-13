/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mercadinho;

/**
 *
 * @author joaov
 */
import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;
import java.util.Map;

public class Estoque {
    private Map<Produto, Integer> produtosQuantidade;
    private List<Venda> vendas; // Lista para registrar vendas

    public Estoque() {
        produtosQuantidade = new HashMap<>();
         vendas = new ArrayList<>();
    }
    public Map<Produto, Integer> getProdutosQuantidade() {
    return produtosQuantidade;
}

public List<Produto> getProdutos() {
    List<Produto> produtos = new ArrayList<>(produtosQuantidade.keySet());
    return produtos;
}

  
    public void adicionarProduto(Produto produto, int quantidade) {
        if (quantidade <= 0) {
            System.out.println("A quantidade deve ser maior que zero.");
            return;
        }

        if (produtosQuantidade.containsKey(produto)) {
            int quantidadeExistente = produtosQuantidade.get(produto);
            produtosQuantidade.put(produto, quantidadeExistente + quantidade);
            System.out.println(quantidade + " unidades de " + produto.getNomeProduto() + " adicionadas ao estoque.");
        } else {
            produtosQuantidade.put(produto, quantidade);
            System.out.println(quantidade + " unidades de " + produto.getNomeProduto() + " adicionadas ao estoque.");
        }
      
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

    public void atualizarProduto(Produto produto, int novaQuantidade) {
        if (novaQuantidade <= 0) {
            System.out.println("A nova quantidade deve ser maior que zero.");
            return;
        }

        produtosQuantidade.put(produto, novaQuantidade);
        System.out.println("Quantidade de " + produto.getNomeProduto() + " atualizada para " + novaQuantidade + " unidades.");
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

   public void registrarVenda(Produto produto, int quantidade) {
    if (quantidade <= 0) {
        System.out.println("A quantidade deve ser maior que zero.");
        return;
    }

    if (produtosQuantidade.containsKey(produto)) {
        int quantidadeExistente = produtosQuantidade.get(produto);
        if (quantidadeExistente >= quantidade) {
            produtosQuantidade.put(produto, quantidadeExistente - quantidade);
            System.out.println(quantidade + " unidades de " + produto.getNomeProduto() + " vendidas.");
        } else {
            System.out.println("Quantidade insuficiente em estoque para vender.");
        }
    } else {
        System.out.println("Produto não encontrado em estoque.");
    }

    // Aqui você pode adicionar a venda à lista de vendas ou a outro registro, se necessário.
    Venda venda = new Venda(produto, quantidade);
    vendas.add(venda);
}


    @Override
    public String toString() {
        return "Estoque{" + "produtosQuantidade=" + produtosQuantidade + ", vendas=" + vendas + '}';
    }

  
    
}