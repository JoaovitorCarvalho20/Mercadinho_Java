package com.mycompany.mercadinho;

/**
 * A classe VendaItem representa um item específico vendido em uma transação de venda.
 */
public class VendaItem {
    private Produto produto;   // O produto associado a este item de venda.
    private int quantidade;    // A quantidade vendida deste produto.

    /**
     * Construtor da classe VendaItem. Inicializa um novo item de venda com um produto e uma quantidade.
     *
     * @param produto    O produto vendido.
     * @param quantidade A quantidade deste produto vendida.
     */
    public VendaItem(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    /**
     * Obtém o produto associado a este item de venda.
     *
     * @return O produto associado ao item de venda.
     */
    public Produto getProduto() {
        return produto;
    }

    /**
     * Obtém a quantidade deste produto vendida neste item de venda.
     *
     * @return A quantidade deste produto vendida.
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Define a quantidade deste produto vendida neste item de venda.
     *
     * @param quantidade A nova quantidade deste produto vendida.
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Gera uma representação em formato de string deste item de venda.
     *
     * @return Uma string que representa o item de venda, incluindo o produto e a quantidade.
     */
    @Override
    public String toString() {
        return "VendaItem{" +
                "produto=" + produto +
                ", quantidade=" + quantidade +
                '}';
    }
}
