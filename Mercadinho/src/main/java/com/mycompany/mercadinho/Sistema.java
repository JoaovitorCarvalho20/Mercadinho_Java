package com.mycompany.mercadinho;

import Comparators.ClienteComparator;

import java.util.ArrayList;

import java.util.Collections;

import java.util.List;

import Comparators.EstoqueComparator;

public class Sistema {

    public static void main(String[] args) {
        // Instanciando o mercadinho usando o Singleton que garante apenas uma instância única do Mercadinho
        Mercadinho mercadinho = Mercadinho.getInstance();

        Produto p1 = new Produto("Arroz", 10, "Alimentos");
        Produto p2 = new Produto("Feijão", 13, "Alimentos");
        Produto p3 = new Produto("Macarrão", 14, "Alimentos");

        Estoque estoque = new Estoque();
        estoque.adicionarProduto(p1, 30);
        estoque.adicionarProduto(p2, 10);
        estoque.adicionarProduto(p3, 50);

        // Modificado: Obtendo a lista de produtos do estoque
        List<Produto> produtosNoEstoque = estoque.getProdutos();

        // Modificado: Ordenando a lista de produtos usando o comparador EstoqueComparator
        Collections.sort(produtosNoEstoque, new EstoqueComparator(estoque));

        // Imprimindo os produtos modificados os produtos ordenados
        System.out.println("Produtos no estoque ordenados:");
        for (Produto produto : produtosNoEstoque) {
            System.out.println(produto);
        }

        System.out.println("-----------------------------");

        // Criação da lista de clientes
        List<Cliente> listaClientes = new ArrayList<>();

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

        // Ordenação da lista de clientes com base no CPF usando o ClienteComparator
        Collections.sort(listaClientes, new ClienteComparator());

        // Impressão dos clientes ordenados por CPF
        System.out.println("Clientes ordenados por CPF:");
        for (Cliente cliente : listaClientes) {
            System.out.println(cliente);
        }

        /**
         * Esta é a classe principal do sistema do Mercadinho. Autor: João Vitor
         * Aparecido Carvalho
         */
        while (true) {
            mercadinho.login();
        }
    }
}
