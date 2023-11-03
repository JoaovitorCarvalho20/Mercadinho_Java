package com.mycompany.mercadinho;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Venda {
    private static int nextId = 1;
    private int id;
    private List<VendaItem> itens;

    public Venda() {
        id = nextId++;
        itens = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void adicionarItem(VendaItem item) {
        itens.add(item);
    }

    
    public void realizarVenda(Estoque estoque) {
    for (VendaItem item : itens) {
        Produto produto = item.getProduto();
        int quantidade = item.getQuantidade();

        if (estoque.verificarEstoque(produto) >= quantidade) {
            estoque.removerProduto(produto, quantidade);
        } else {
            System.out.println("Erro: estoque insuficiente para o produto " + produto.getNomeProduto());
           
        }
    }
}

    
    
    public boolean cancelarVenda(Estoque estoque, int quantidadeCancelada) {
        List<VendaItem> itensACancelar = new ArrayList<>();

        for (VendaItem item : itens) {
            Produto produto = item.getProduto();

            if (produto != null) {
                int quantidade = item.getQuantidade();

                if (quantidadeCancelada <= quantidade) {
                    estoque.adicionarQuantidadePorID(produto.getId(), quantidadeCancelada);
                    item.setQuantidade(quantidade - quantidadeCancelada);

                    if (item.getQuantidade() == 0) {
                        itensACancelar.add(item);
                    }

                    System.out.println(quantidadeCancelada + " unidades de " + produto.getNomeProduto() + " retornadas ao estoque.");
                    break;
                } else {
                    estoque.adicionarQuantidadePorID(produto.getId(), quantidade);
                    quantidadeCancelada -= quantidade;
                    item.setQuantidade(0);
                    System.out.println(quantidade + " unidades de " + produto.getNomeProduto() + " retornadas ao estoque.");
                    itensACancelar.add(item);
                }
            }
        }

        itens.removeAll(itensACancelar);

        return true;
    }

    public Date getData() {
        return new Date();
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

    public int getQuantidadeVendida(Produto produto) {
        int quantidadeVendida = 0;

        for (VendaItem item : itens) {
            if (item.getProduto().equals(produto)) {
                quantidadeVendida += item.getQuantidade();
            }
        }

        return quantidadeVendida;
    }

    public boolean cancelarVenda(Estoque estoque) {
        for (VendaItem item : itens) {
            Produto produto = item.getProduto();
            int quantidade = item.getQuantidade();
            estoque.adicionarQuantidadePorID(produto.getId(), quantidade);
        }

        itens.clear();
        return true;
    }

    public int getQuantidadeTotal() {
        int quantidadeTotal = 0;

        for (VendaItem item : itens) {
            quantidadeTotal += item.getQuantidade();
        }

        return quantidadeTotal;
    }
}
