
package com.mycompany.mercadinho;


public class Produto {
    
   /*O valor da variável estática contador é inicializado com o tamanho da lista TP_POO.getMeusProdutos().size(). Isso pode significar que a classe está contando o número de produtos em uma lista chamada getMeusProdutos()*/

//private static int contador = Mercadinho.getMeusProdutos().size();
private static int contador;
private String nomeProduto;
private String loteDoProduto;
private double valorDoProduto;
private int id;

//Contrutor padrao, ele tem o intuito de caontar as instancias 
    public Produto() {
        contador++;
    }
//Contrutor sobrecarregado que define o que tem que ser definido como paramentro quanddo se instancia o objeto 
    public Produto(String nomeProduto, String loteDoProduto, double valorDoProduto, int id) {
        this.nomeProduto = nomeProduto;
        this.loteDoProduto = loteDoProduto;
        this.valorDoProduto = valorDoProduto;
        this.id = id;
    }
    
    // metodos de acesso da classe Produto

    public static int getContador() {
        return contador;
    }



    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getLoteDoProduto() {
        return loteDoProduto;
    }

    public void setLoteDoProduto(String loteDoProduto) {
        this.loteDoProduto = loteDoProduto;
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
//metodo toString sobrescrito para mostrar o nomeDoPoduto, loteDoProduto, valorDoProdut e sua id.
    @Override
    public String toString() {
        return "Produto{" + "nomeProduto=" + nomeProduto + ", loteDoProduto=" + loteDoProduto + ", valorDoProduto=" + valorDoProduto + ", id=" + id + '}';
    }

    
}
