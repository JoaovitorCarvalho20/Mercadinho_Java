package com.mycompany.mercadinho;

public class Produto {
    private static int contador = 0;
    private String nomeProduto;
    private double valorDoProduto;
    private int id;
    private String categoria;

    public Produto(String nomeProduto, double valorDoProduto, String categoria) {
        this.nomeProduto = nomeProduto;
        this.valorDoProduto = valorDoProduto;
        this.categoria = categoria; // Adiciona a categoria do produto
        this.id = contador; // Atribui o valor de contador como o ID do produto
        contador++; // Incrementa o contador para o próximo produto
    }

    public static int getContador() {
        return contador;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getValorDoProduto() {
        return valorDoProduto;
    }

    public void setValorDoProduto(double valorDoProduto) {
        this.valorDoProduto = valorDoProduto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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