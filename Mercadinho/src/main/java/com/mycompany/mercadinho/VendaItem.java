package com.mycompany.mercadinho;

public class VendaItem {
    private Produto produto;
    private int quantidade;

    public VendaItem(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "VendaItem{" + "produto=" + produto + ", quantidade=" + quantidade + '}';
    }
}
