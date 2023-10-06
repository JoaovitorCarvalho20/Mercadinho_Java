package com.mycompany.mercadinho;

import java.util.Scanner;

// Declaração da classe Login
public class Login {

    // Declaração de variáveis de instância privadas
    private String nome;
    private String email;
    private String seenha;

    // Declaração de um objeto Scanner para entrada de dados
    Scanner input = new Scanner(System.in);

    // Construtor da classe Login (não inicializa corretamente as variáveis)
    public Login() {
        this.nome = nome;
        this.email = email;
        this.seenha = seenha;
    }

    // Métodos getters e setters para as variáveis nome, email e seenha
    // Método para configurar o nome
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Método para obter o nome
    public String getNome() {
        return nome;
    }

    // Método para configurar o email
    public void setEmail(String email) {
        this.email = email;
    }

    // Método para obter o email
    public String getEmail() {
        return email;
    }

    // Método para configurar a senha (você escreveu "seenha" em vez de "senha")
    public void setSeenha(String senha) {
        this.seenha = senha;
    }

    // Método para obter a senha (você escreveu "seenha" em vez de "senha")
    public String getSeenha() {
        return seenha;
    }

    // Método para realizar o cadastro do usuário
    public void cadastro() {
        System.out.println("Entre com o nome do usuário");
        String nome = input.nextLine();
        setNome(nome);

        System.out.println("Entre com o email");
        String email = input.next();
        setEmail(email);

        System.out.println("Entre com a senha");
        String senha = input.next();
        setSeenha(senha);
    }

    // Método para verificar o cadastro e conceder acesso com base nos valores fornecidos
    public void verificarCadastro() {
        if (getNome().equals("ADM") && getEmail().equals("adm@teste") && getSeenha().equals("senha123")) {
            System.out.println("Acesso aprovado ao administrador");
        } else if (getNome().equals("Fun") && getEmail().equals("fun@teste") && getSeenha().equals("senha456")) {
            System.out.println("Acesso aprovado ao funcionário");
        } else {
            System.out.println("Acesso negado. Tente novamente.");
        }
    }
}
