package com.mycompany.mercadinho;

public class Funcionario {
    private String nome;
    private int id; // Alterado para int
    private String senha;
    

    public Funcionario(String nome, int id, String senha) { // Atualizado o construtor
        this.nome = nome;
        this.id = id;
        this.senha = senha;
    }

    public Funcionario() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() { // Alterado para retornar int
        return id;
    }

    public void setId(int id) { // Alterado para aceitar int como parâmetro
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Funcionario{" + "nome=" + nome + ", id=" + id + ", senha=" + senha + '}';
    }
}