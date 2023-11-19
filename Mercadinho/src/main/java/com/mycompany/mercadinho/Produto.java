package com.mycompany.mercadinho;

/**
 * Esta é a classe que representa um Produto em um mercadinho.
 * Ela define os atributos nomeProduto, valorDoProduto, id, categoria, e um atributo estático contador que conta
 * quantas instâncias de Produto foram criadas.
 */
public class Produto {
    private static int contador = 0;
    private String nomeProduto;
    private double valorDoProduto;
    private int id;
    private String categoria;

    /**
     * Construtor para criar um objeto Produto.
     *
     * @param nomeProduto    O nome do produto.
     * @param valorDoProduto O valor do produto.
     * @param categoria      A categoria do produto.
     */
    public Produto(String nomeProduto, double valorDoProduto, String categoria) {
        this.nomeProduto = nomeProduto;
        this.valorDoProduto = valorDoProduto;
        this.categoria = categoria; // Adiciona a categoria do produto
        this.id = contador; // Atribui o valor de contador como o ID do produto
        contador++; // Incrementa o contador para o próximo produto
    }

    /**
     * Obtém o contador de instâncias de Produto criadas.
     *
     * @return O contador de instâncias de Produto.
     */
    public static int getContador() {
        return contador;
    }

    /**
     * Obtém o nome do produto.
     *
     * @return O nome do produto.
     */
    public String getNomeProduto() {
        return nomeProduto;
    }

    /**
     * Define o nome do produto.
     *
     * @param nomeProduto O novo nome do produto.
     */
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    /**
     * Obtém o valor do produto.
     *
     * @return O valor do produto.
     */
    public double getValorDoProduto() {
        return valorDoProduto;
    }

    /**
     * Define o valor do produto.
     *
     * @param valorDoProduto O novo valor do produto.
     */
    public void setValorDoProduto(double valorDoProduto) {
        this.valorDoProduto = valorDoProduto;
    }

    /**
     * Obtém a categoria do produto.
     *
     * @return A categoria do produto.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Define a categoria do produto.
     *
     * @param categoria A nova categoria do produto.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Obtém o ID do produto.
     *
     * @return O ID do produto.
     */
    public int getId() {
        return id;
    }

  
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Substitui o método toString para fornecer uma representação em formato de string do objeto Produto.
     *
     * @return Uma representação em formato de string do objeto Produto.
     */
    @Override
    public String toString() {
        return "Produto{" +
                "nomeProduto='" + nomeProduto + '\'' +
                ", valorDoProduto=" + valorDoProduto +
                ", categoria='" + categoria + '\'' +
                ", id=" + id +
                '}';
    }
}
