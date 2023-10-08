/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mercadinho;

/**
 *
 * @author joaov
 */
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

    @Override
    public String toString() {
        return "VendaItem{" + "produto=" + produto + ", quantidade=" + quantidade + '}';
    }
    
}

