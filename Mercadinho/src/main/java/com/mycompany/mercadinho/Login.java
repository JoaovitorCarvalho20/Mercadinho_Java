package com.mycompany.mercadinho;

import java.util.Scanner;

public class Login {

    private String nome;
    private String email;
    private String senha;

    Scanner input = new Scanner(System.in);

    public Login() {
        this.nome = "ADM"; // Definindo nome, email e senha do administrador
        this.email = "admin@teste";
        this.senha = "senha123";
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void cadastro() {
        System.out.println("Entre com o nome do usuário");
        String nome = input.nextLine();
        setNome(nome);

        System.out.println("Entre com o email");
        String email = input.next();
        setEmail(email);

        System.out.println("Entre com a senha");
        String senha = input.next();
        setSenha(senha);
    }

  

    Object getNomeUsuario() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}