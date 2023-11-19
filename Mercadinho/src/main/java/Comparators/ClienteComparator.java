/*
 * Clique em nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para alterar esta licença
 * Clique em nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java para editar este modelo
 */
package Comparators;

import com.mycompany.mercadinho.Cliente;
import java.util.Comparator;

/**
 * Comparador para ordenar objetos Cliente com base no CPF.
 * 
 * Essa classe implementa a interface Comparator, permitindo a comparação de objetos Cliente.
 */
public class ClienteComparator implements Comparator<Cliente> {

    /**
     * Compara dois objetos Cliente com base no CPF.
     *
     * @param c1 o primeiro objeto Cliente
     * @param c2 o segundo objeto Cliente
     * @return um valor negativo, zero ou um valor positivo, conforme o CPF do primeiro objeto seja menor que, igual a, ou maior que o segundo
     */
    @Override
    public int compare(Cliente c1, Cliente c2) {
        return c1.getCpf().compareTo(c2.getCpf());
    }
}
