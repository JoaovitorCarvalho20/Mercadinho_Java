package com.mycompany.mercadinho;

import java.util.Scanner;

/**
 * A classe Login representa informações relacionadas ao login de um usuário.
 */
public class Login {

    private String nome; // Nome do usuário.
    private String email; // Endereço de email do usuário.
    private String senha; // Senha do usuário.

  

    /**
     * Construtor padrão da classe Login.
     * Inicializa o objeto, mas os campos nome, email e senha não estão definidos aqui.
     */
    public Login() {
        this.nome = nome; // Definindo nome, email e senha do administrador
        this.email = email;
        this.senha = senha;
    }

    /**
     * Define o nome do usuário.
     *
     * @param nome O nome a ser definido para o usuário.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o nome do usuário.
     *
     * @return O nome do usuário.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o endereço de email do usuário.
     *
     * @param email O endereço de email a ser definido para o usuário.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtém o endereço de email do usuário.
     *
     * @return O endereço de email do usuário.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define a senha do usuário.
     *
     * @param senha A senha a ser definida para o usuário.
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Obtém a senha do usuário.
     *
     * @return A senha do usuário.
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Este método não está implementado e lança uma exceção.
     * Deve ser implementado adequadamente para realizar uma ação específica.
     *
     * @return Um objeto não suportado (UnsupportedOperationException).
     */
    Object getNomeUsuario() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
