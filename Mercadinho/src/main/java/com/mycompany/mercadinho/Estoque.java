package com.mycompany.mercadinho;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A classe Estoque representa o estoque de produtos em um mercadinho.
 */
public class Estoque {
    public Map<Produto, Integer> produtosQuantidade; // Um mapeamento de produtos para suas quantidades em estoque.

    /**
     * Construtor padrão que inicializa o mapa de produtosQuantidade.
     */
    public Estoque() {
        produtosQuantidade = new HashMap<>();
    }
    Manipularjson manipularjson=new Manipularjson();

    /**
     * Adiciona a quantidade especificada de um produto ao estoque.
     *
     * @param produto    O produto a ser adicionado.
     * @param quantidade A quantidade a ser adicionada.
     */
    public void adicionarProduto(Produto produto, int quantidade) {
        if (quantidade <= 0) {
            System.out.println("A quantidade deve ser maior que zero.");
            return;
        }

        produtosQuantidade.put(produto, produtosQuantidade.getOrDefault(produto, 0) + quantidade);
        System.out.println(quantidade + " unidades de " + produto.getNomeProduto() + " adicionadas ao estoque.");
       
    }

    /**
     * Remove a quantidade especificada de um produto do estoque, se disponível.
     *
     * @param produto    O produto a ser removido.
     * @param quantidade A quantidade a ser removida.
     */
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
        Manipularjson.EscreverEstoque(this);
        Manipularjson.LerEstoque();
    }

    /**
     * Verifica a quantidade de um produto no estoque.
     *
     * @param produto O produto a ser verificado.
     * @return A quantidade em estoque do produto.
     */
    public int verificarEstoque(Produto produto) {
        int quantidade = produtosQuantidade.getOrDefault(produto, 0);
        System.out.println("Quantidade de " + produto.getNomeProduto() + " em estoque: " + quantidade + " unidades.");
        return quantidade;
    }

    /**
     * Exibe o estoque atual, listando todos os produtos e suas quantidades em estoque.
     */
    public void exibirEstoque() {
        System.out.println("---- Estoque Atual ----");
        for (Map.Entry<Produto, Integer> entry : produtosQuantidade.entrySet()) {
            Produto produto = entry.getKey();
            int quantidade = entry.getValue();
            System.out.println(produto.toString() + " - Quantidade em estoque: " + quantidade + " unidades.");
        }
    }

    /**
     * Adiciona a quantidade especificada de um produto ao estoque, com base no ID do produto.
     *
     * @param produtoId  O ID do produto a ser adicionado.
     * @param quantidade A quantidade a ser adicionada.
     */
    public void adicionarQuantidadePorID(int produtoId, int quantidade) {
        Produto produto = encontrarProdutoPorID(produtoId);

        if (produto != null) {
            adicionarProduto(produto, quantidade);
        } else {
            System.out.println("Produto não encontrado em estoque.");
        }
    }

    /**
     * Encontra um produto no estoque com base no ID do produto.
     *
     * @param produtoId O ID do produto a ser encontrado.
     * @return O produto encontrado ou null se não encontrado.
     */
    public Produto encontrarProdutoPorID(int produtoId) {
        for (Produto produto : produtosQuantidade.keySet()) {
            if (produto.getId() == produtoId) {
                return produto;
            }
        }
        return null;
    }

    /**
     * Obtém uma lista de todos os produtos no estoque.
     *
     * @return Uma lista de produtos.
     */
    public List<Produto> getProdutos() {
        List<Produto> listaProdutos = new ArrayList<>(produtosQuantidade.keySet());
        return listaProdutos;
    }
}
