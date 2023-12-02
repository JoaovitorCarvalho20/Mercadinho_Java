package com.mycompany.mercadinho;

import Comparators.ClienteComparator;
import Comparators.FuncionarioComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
/**
 Classe Sistema que intancia o Mercadinho.
 * E onde os testes propostos são feitos 
 
 */


public class Sistema {

    public static void main(String[] args) {
        // Instanciando o mercadinho usando o Singleton que garante apenas uma instância única do Mercadinho
        Mercadinho mercadinho = Mercadinho.getInstance();
        //Q16
        // Criação da lista de funcionários
        List<Funcionario> listaFuncionarios = new ArrayList<>();

        // Criação de instâncias da classe Funcionario
        Funcionario f1 = new Funcionario("João", 1, "teste");
        Funcionario f2 = new Funcionario("Maria", 2, "teste123");
        Funcionario f3 = new Funcionario("Carlos", 3, "123teste");
        Funcionario f4 = new Funcionario("Ana", 4, "12teste3");

        // Adição dos funcionários à lista
        listaFuncionarios.add(f1);
        listaFuncionarios.add(f2);
        listaFuncionarios.add(f3);
        listaFuncionarios.add(f4);

        // Ordenação da lista de funcionários com base no nome usando o FuncionarioComparator
        Collections.sort(listaFuncionarios, new FuncionarioComparator());

        // Impressão dos funcionários ordenados por nome
        System.out.println("Funcionários ordenados por nome:");
        for (Funcionario funcionario : listaFuncionarios) {
            System.out.println(funcionario);
        }

        System.out.println("-----------------------------");

        // Criação da lista de clientes
        List<Cliente> listaClientes = new ArrayList<>();

        // Criação do Iterator para a lista de clientes
        Iterator<Cliente> iteratorClientes = listaClientes.iterator();

        // Criação de instâncias da classe Cliente
        Cliente c1 = new Cliente("João", "123-456789", "Rua A", "111.222.333-44", "joao@example.com");
        Cliente c2 = new Cliente("Maria", "987-654321", "Rua B", "222.333.444-55", "maria@example.com");
        Cliente c3 = new Cliente("Carlos", "555-123456", "Rua C", "333.444.555-66", "carlos@example.com");
        Cliente c4 = new Cliente("Ana", "999-888777", "Rua D", "444.555.666-77", "ana@example.com");

        // Adição dos clientes à lista
        listaClientes.add(c1);
        listaClientes.add(c2);
        listaClientes.add(c3);
        listaClientes.add(c4);
        //Q16
        // Ordenação da lista de clientes com base no CPF usando o ClienteComparator
        Collections.sort(listaClientes, new ClienteComparator());

        // Impressão dos clientes ordenados por CPF
        System.out.println("Clientes ordenados por CPF:");
        for (Cliente cliente : listaClientes) {
            System.out.println(cliente);
        }

        //Q17
        // Encontrar cliente existente
        System.out.println("\nEncontrar cliente por telefone");

        Cliente clienteEncontrado = mercadinho.findClienteByTelefone("987-654321", listaClientes);
        if (clienteEncontrado != null) {
            System.out.println("Cliente encontrado: " + clienteEncontrado.getNome());
        } else {
            System.out.println("Cliente não encontrado.");
        }

        System.out.println("\nEncontrar cliente por telefone");
        // Tentar encontrar cliente inexistente
        Cliente clienteNaoEncontrado = mercadinho.findClienteByTelefone("111-222333", listaClientes);
        if (clienteNaoEncontrado != null) {
            System.out.println("Cliente encontrado: " + clienteNaoEncontrado.getNome());
        } else {
            System.out.println("Cliente não encontrado.");
        }

//        // Percorre a lista de clientes usando o código fornecido
//        System.out.println("\nPercorrendo a lista de clientes usando o Iterator:");
//        while (iteratorClientes.hasNext()) {
//            mercadinho.imprimirCliente(iteratorClientes.next());


        // Lógica para continuar ou encerrar o programa
        while (true) {
             /**
              Adminitrador: 
              * Nome : joao
              * Senha : 123
              * 
              * Funcionario:
              * nome: Vitor
              * Senha : 456
              * 
              */


            mercadinho.login();
        }
    }
}

// Para rodar o codigo antes troque os caminhos da classe Manipular Json


