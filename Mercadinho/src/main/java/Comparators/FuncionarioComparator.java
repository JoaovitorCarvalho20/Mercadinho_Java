package Comparators;

import com.mycompany.mercadinho.Funcionario;
import java.util.Comparator;

/**
 * Comparador para ordenar objetos Funcionario com base no nome.
 */
public class FuncionarioComparator implements Comparator<Funcionario> {

    /**
     * Compara dois objetos Funcionario com base nos nomes.
     *
     * @param f1 o primeiro objeto Funcionario
     * @param f2 o segundo objeto Funcionario
     * @return um valor negativo, zero ou um valor positivo, conforme o nome do primeiro objeto seja menor que, igual a, ou maior que o segundo
     */
    @Override
    public int compare(Funcionario f1, Funcionario f2) {
        return f1.getNome().compareTo(f2.getNome());
    }
}
