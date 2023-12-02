package com.mycompany.mercadinho;

import java.util.ArrayList;
import java.util.List;


/**
 * A classe Estoque representa o estoque de produtos em um mercadinho.
 */
public class Estoque {
    
    private Produto produto;
    private Integer quantidade;
   

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }


  
    /**
     * Construtor padrão que inicializa  produtosQuantidade.
     */
    public Estoque() {
      this.produto=produto;
      this.quantidade=quantidade;
    }
  
    
   

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
    
    // Verifica se o produto está no estoque
    if (produto != null) {
        int quantidadeExistente = produto.getQuantidadeInicial();

        // Verifica se a quantidade em estoque é suficiente
        if (quantidadeExistente >= quantidade) {
            produto.setQuantidadeInicial(quantidadeExistente - quantidade);
            System.out.println(quantidade + " unidades de " + produto.getNomeProduto() + " removidas do estoque.");
        } else {
            System.out.println("Quantidade insuficiente em estoque para remover.");
        }
    } else {
        System.out.println("Produto não encontrado em estoque.");
    }

    // Você pode precisar adicionar manipulação de JSON aqui
    Manipularjson.EscreverEstoque(produto);
    Manipularjson.LerEstoque();
}

    /**
     * Verifica a quantidade de um produto no estoque.
     *
     * @param produto O produto a ser verificado.
     * @return A quantidade em estoque do produto.
     */
  public int verificarEstoque(Produto produto) {
    int quantidade = produto.getQuantidadeInicial();
    //System.out.println("Quantidade de " + produto.getNomeProduto() + " em estoque: " + quantidade + " unidades.");
    return quantidade;
}


    /**
     * Exibe o estoque atual, listando todos os produtos e suas quantidades em estoque.
     */
public List<Produto> exibirEstoque() {
    System.out.println("---- Estoque Atual ----");

     return Manipularjson.LerEstoque(); 
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
    // Verifica se o produto no estoque tem o ID correspondente
    if (this.produto != null && this.produto.getId() == produtoId) {
        return this.produto;
    }
    
    // Retorna null se o produto não for encontrado
    return null;
}


    /**
     * Obtém uma lista de todos os produtos no estoque.
     *
     * @return Uma lista de produtos.
     */
  public List<Produto> getProdutos() {
    List<Produto> listaProdutos = new ArrayList<>();
    
    // Verifica se o produto não é nulo antes de acessar seus métodos
    if (produto != null) {
        for (int i = 0; i < produto.getQuantidadeInicial(); i++) {
            listaProdutos.add(produto);
        }
    }

    return listaProdutos;
}

}
