package com.mycompany.mercadinho;

public class Funcionario {

    private String nome;
    private String id;
    private String senha;

    //Contrutor sobrecarregado que define o que tem que ser definido como paramentro quanddo se instancia o objeto 
    public Funcionario(String nome, String id, String senha) {
        this.nome = nome;
        this.id = id;
        this.senha = senha;
    }

    //construtor padrao definido 
    public Funcionario() {
    }

    // metodos de acesso 
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    //metodo toString sobrescrito para imprimir nome id e senha 
    @Override
    public String toString() {
        return "Funcionario{" + "nome=" + nome + ", id=" + id + ", senha=" + senha + '}';
    }

}
