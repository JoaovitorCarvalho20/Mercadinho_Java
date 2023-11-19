package Comparators;

import com.mycompany.mercadinho.Estoque;
import com.mycompany.mercadinho.Produto;

import java.util.Comparator;

/**
 * Comparador para ordenar objetos Produto com base na quantidade.
 */
public class EstoqueComparator implements Comparator<Produto> {

    private final Estoque estoque; // Adicione uma referência ao Estoque relevante

    /**
     * Construtor que recebe um objeto Estoque.
     * @param estoque o objeto Estoque a ser utilizado para comparação
     */
    public EstoqueComparator(Estoque estoque) {
        this.estoque = estoque;
    }

    /**
     * Compara dois objetos Produto com base na quantidade do produto específico no Estoque.
     *
     * @param p1 o primeiro objeto Produto
     * @param p2 o segundo objeto Produto
     * @return um inteiro negativo, zero ou um inteiro positivo, conforme o primeiro argumento seja menor que, igual a, ou maior que o segundo
     */
    @Override
    public int compare(Produto p1, Produto p2) {
        int quantidadeP1 = estoque.produtosQuantidade.getOrDefault(p1, 0);
        int quantidadeP2 = estoque.produtosQuantidade.getOrDefault(p2, 0);

        // Compare as quantidades em ordem decrescente
        return Integer.compare(quantidadeP2, quantidadeP1);
    }
}
