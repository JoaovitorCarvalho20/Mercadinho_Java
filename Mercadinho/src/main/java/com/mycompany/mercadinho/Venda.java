package com.mycompany.mercadinho;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A classe Venda representa uma transação de venda de produtos em um Mercadinho.
 */
public class Venda {
    private static int nextId = 1;
    private int id;
    private List<VendaItem> itens;
    private double valorTotal; // Adicione o atributo para armazenar o valor total

    /**
     * Construtor da classe Venda. Inicializa um objeto de venda com um ID único e uma lista de itens vazia.
     */
    public Venda() {
        id = nextId++;
        itens = new ArrayList<>();
        valorTotal = 0; // Inicialize o valor total como zero no construtor
    }

    /**
     * Obtém o ID único da venda.
     *
     * @return O ID da venda.
     */
    public int getId() {
        return id;
    }

    /**
     * Adiciona um item à lista de itens da venda e atualiza o valor total da venda.
     *
     * @param item O item a ser adicionado à venda.
     */
    public void adicionarItem(VendaItem item) {
        itens.add(item);
        // Atualize o valor total durante a adição do item
        valorTotal += item.getProduto().getValorDoProduto() * item.getQuantidade();
    }

    /**
     * Realiza a venda dos itens presentes na lista e remove a quantidade correspondente do estoque.
     *
     * @param estoque O estoque do qual os produtos serão vendidos.
     */
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

    /**
     * Cancela uma parte da venda, adicionando a quantidade especificada de itens de volta ao estoque.
     *
     * @param estoque            O estoque onde os produtos serão adicionados de volta.
     * @param quantidadeCancelada A quantidade de itens a ser cancelada.
     * @return Verdadeiro se o cancelamento foi bem-sucedido, falso caso contrário.
     */
    public boolean cancelarVenda(Estoque estoque, int quantidadeCancelada) {
        List<VendaItem> itensACancelar = new ArrayList<>();
        double valorCancelado = 0; // Variável para rastrear o valor cancelado

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

                    valorCancelado += quantidadeCancelada * produto.getValorDoProduto(); // Atualize o valor cancelado
                    System.out.println(quantidadeCancelada + " unidades de " + produto.getNomeProduto() + " retornadas ao estoque.");
                    break;
                } else {
                    estoque.adicionarQuantidadePorID(produto.getId(), quantidade);
                    quantidadeCancelada -= quantidade;
                    item.setQuantidade(0);

                    valorCancelado += quantidade * produto.getValorDoProduto(); // Atualize o valor cancelado
                    System.out.println(quantidade + " unidades de " + produto.getNomeProduto() + " retornadas ao estoque.");
                    itensACancelar.add(item);
                }
            }
        }

        itens.removeAll(itensACancelar);
        valorTotal -= valorCancelado; // Atualize o valor total após o cancelamento

        return true;
    }

    /**
     * Obtém a data da venda (a data atual).
     *
     * @return A data da venda.
     */
    public Date getData() {
        return new Date();
    }

    /**
     * Obtém o valor total da venda.
     *
     * @return O valor total da venda.
     */
    public double getValorTotal() {
        return valorTotal;
    }

    /**
     * Obtém um Iterable de VendaItem que permite iterar pelos itens da venda.
     *
     * @return Um objeto Iterable que contém os itens da venda.
     */
    public Iterable<VendaItem> getItens() {
        return itens;
    }

    /**
     * Calcula a quantidade total vendida de um produto específico na venda.
     *
     * @param produto O produto para o qual a quantidade vendida deve ser calculada.
     * @return A quantidade total vendida desse produto na venda.
     */
    public int getQuantidadeVendida(Produto produto) {
        int quantidadeVendida = 0;

        for (VendaItem item : itens) {
            if (item.getProduto().equals(produto)) {
                quantidadeVendida += item.getQuantidade();
            }
        }

        return quantidadeVendida;
    }

    /**
     * Cancela a venda, adicionando os itens de volta ao estoque e redefinindo o valor total para zero.
     *
     * @param estoque O estoque onde os produtos serão adicionados de volta.
     * @return Verdadeiro se o cancelamento foi bem-sucedido, falso caso contrário.
     */
    public boolean cancelarVenda(Estoque estoque) {
        for (VendaItem item : itens) {
            Produto produto = item.getProduto();
            int quantidade = item.getQuantidade();
            estoque.adicionarQuantidadePorID(produto.getId(), quantidade);
        }

        valorTotal = 0; // Atualize o valor total após o cancelamento
        itens.clear();
        return true;
    }

    /**
     * Obtém a quantidade total de itens vendidos em toda a venda.
     *
     * @return A quantidade total de itens vendidos.
     */
    public int getQuantidadeTotal() {
        int quantidadeTotal = 0;

        for (VendaItem item : itens) {
            quantidadeTotal += item.getQuantidade();
        }

        return quantidadeTotal;
    }
}
