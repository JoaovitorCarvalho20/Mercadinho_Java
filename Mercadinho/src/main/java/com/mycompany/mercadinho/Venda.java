package com.mycompany.mercadinho;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Venda {
    private List<Estoque> caixas;
    private List<VendaItem> itens; // Lista de itens vendidos

    public Venda() {
        caixas = new ArrayList<>();
        itens = new ArrayList<>();
        // Inicializar as 5 caixas
        for (int i = 0; i < 5; i++) {
            caixas.add(new Estoque());
        }
    }

    public Estoque getCaixa(int indice) {
        if (indice >= 0 && indice < 5) {
            return caixas.get(indice);
        } else {
            System.out.println("Índice de caixa inválido.");
            return null;
        }
    }

    // Método para realizar uma venda em uma caixa específica
    public void realizarVenda(int indiceCaixa, Produto produto, int quantidade) {
        Estoque caixa = getCaixa(indiceCaixa);
        if (caixa != null) {
            caixa.registrarVenda(produto, quantidade);

            // Registrar a venda na lista de itens
            VendaItem item = new VendaItem(produto, quantidade);
            itens.add(item);

            System.out.println(quantidade + " unidades de " + produto.getNomeProduto() + " vendidas.");
        }
    }

    // Método para cancelar uma venda em uma caixa específica
    public void cancelarVenda(int indiceCaixa, Produto produto, int quantidade) {
        Estoque caixa = getCaixa(indiceCaixa);
        if (caixa != null) {
            caixa.adicionarProduto(produto, quantidade); // Adicionar produtos de volta ao estoque
            System.out.println("Venda cancelada. " + quantidade + " unidades de " + produto.getNomeProduto() + " adicionadas de volta ao estoque da caixa " + indiceCaixa + ".");

            // Remover o item da lista de itens
            VendaItem item = new VendaItem(produto, quantidade);
            itens.remove(item);
        }
    }

    // Método para exibir o estoque de uma caixa específica
    public void exibirEstoqueCaixa(int indiceCaixa) {
        Estoque caixa = getCaixa(indiceCaixa);
        if (caixa != null) {
            caixa.exibirEstoque();
        }
    }

    // Método para obter a data da venda
    public Date getData() {
        return new Date(); // Use a data atual como exemplo; você pode configurar a data adequadamente.
    }

    // Método para obter o valor total da venda
    public double getValorTotal() {
        double total = 0;
        for (VendaItem item : itens) {
            total += item.getProduto().getValorDoProduto() * item.getQuantidade();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Venda{" + "caixas=" + caixas + ", itens=" + itens + '}';
    }
    
}
