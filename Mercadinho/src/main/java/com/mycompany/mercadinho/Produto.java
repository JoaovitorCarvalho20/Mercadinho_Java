package com.mycompany.mercadinho;

public class Produto {
    private static int contador;
    private String nomeProduto;
    private double valorDoProduto;
    private int id;

    public Produto(String nomeProduto, String nomeProduto1, double valorDoProduto) {
        this.nomeProduto = nomeProduto;
        this.valorDoProduto = valorDoProduto;
        this.id = contador; // Atribui o valor de contador como o ID do produto
        contador++; // Incrementa o contador para o próximo produto
    }

    public static int getContador() {
        return contador;
    }

    Produto(String nomeProduto, double precoProduto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
                ", id=" + id +
                '}';
    }
}
