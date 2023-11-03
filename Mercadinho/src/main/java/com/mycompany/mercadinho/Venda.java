package com.mycompany.mercadinho;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Venda {
    private static int nextId = 1; // Próximo ID disponível para uma nova venda
    private int id; // ID da venda
    private List<VendaItem> itens; // Lista de itens vendidos

    public Venda(int idVenda) {
        id = nextId++; // Atribui um ID único à venda e incrementa o próximo ID disponível
        itens = new ArrayList<>(); // Inicializa a lista de itens da venda
    }

    public int getId() {
        return id; // Retorna o ID da venda
    }

    public void adicionarItem(VendaItem item) {
        itens.add(item); // Adiciona um item à lista de itens vendidos na venda
    }

    public boolean realizarVenda(Estoque estoque) {
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
        return new Date(); // Retorna a data atual como exemplo; você pode configurar a data adequadamente.
    }

    public double getValorTotal() {
        double total = 0;

        for (VendaItem item : itens) {
            total += item.getProduto().getValorDoProduto() * item.getQuantidade();
        }

        return total; // Calcula e retorna o valor total da venda
    }

    public Iterable<VendaItem> getItens() {
        return itens; // Retorna os itens vendidos na forma de um objeto Iterable
    }

    public int getQuantidadeVendida(Produto produto) {
        int quantidadeVendida = 0;

        for (VendaItem item : itens) {
            if (item.getProduto().equals(produto)) {
                quantidadeVendida += item.getQuantidade();
            }
        }

        return quantidadeVendida; // Retorna a quantidade vendida de um produto específico
    }

    public boolean cancelarVenda(Estoque estoque, int quantidadeCancelada) {
        // Cria uma lista para armazenar os itens que serão cancelados
        List<VendaItem> itensACancelar = new ArrayList<>();

        // Itera pelos itens da venda
        for (VendaItem item : itens) {
            Produto produto = estoque.encontrarProdutoPorID(item.getProduto().getId());

            if (produto != null) {
                int quantidade = item.getQuantidade();

                if (quantidadeCancelada <= quantidade) {
                    estoque.adicionarProduto(produto, quantidadeCancelada);
                    item.setQuantidade(quantidade - quantidadeCancelada);

                    if (item.getQuantidade() == 0) {
                        itensACancelar.add(item);
                    }

                    System.out.println(quantidadeCancelada + " unidades de " + produto.getNomeProduto() + " retornadas ao estoque.");
                    break;
                } else {
                    estoque.adicionarProduto(produto, quantidade);
                    quantidadeCancelada -= quantidade;
                    item.setQuantidade(0);
                    System.out.println(quantidade + " unidades de " + produto.getNomeProduto() + " retornadas ao estoque.");
                }
            }
        }

        itens.removeAll(itensACancelar);

        return true; // Cancela uma venda e retorna verdadeiro se o cancelamento foi bem-sucedido
    }

    public int getQuantidadeTotal() {
        int quantidadeTotal = 0;

        for (VendaItem item : itens) {
            quantidadeTotal += item.getQuantidade();
        }

        return quantidadeTotal; // Retorna a quantidade total de itens vendidos na venda
    }
}
