package com.mycompany.mercadinho;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Venda {
    private List<VendaItem> itens; // Lista de itens vendidos

    public Venda() {
        itens = new ArrayList<>();
    }

    public void adicionarItem(VendaItem item) {
        itens.add(item);
    }

    public boolean realizarVenda(int indiceCaixa, Estoque estoque) {
        if (itens.isEmpty()) {
            System.out.println("Nenhum produto foi adicionado à venda. Venda cancelada.");
            return false;
        }

        for (VendaItem item : itens) {
            if (item.getQuantidade() <= 0) {
                System.out.println("A quantidade deve ser maior que zero.");
                return false;
            }

            Produto produto = item.getProduto();
            if (estoque.verificarEstoque(produto) >= item.getQuantidade()) {
                estoque.removerProduto(produto, item.getQuantidade());
                System.out.println(item.getQuantidade() + " unidades de " + produto.getNomeProduto() + " vendidas.");
            } else {
                System.out.println("Quantidade insuficiente em estoque para vender.");
                return false;
            }
        }

        itens.clear(); // Limpa a lista de itens após a venda
        return true;
    
    }

    public Date getData() {
        return new Date(); // Use a data atual como exemplo; você pode configurar a data adequadamente.
    }

    public double getValorTotal() {
        double total = 0;
        for (VendaItem item : itens) {
            total += item.getProduto().getValorDoProduto() * item.getQuantidade();
        }
        return total;
    }

    public Iterable<VendaItem> getItens() {
        return itens;
    }

    void cancelarVenda(Estoque estoque) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
