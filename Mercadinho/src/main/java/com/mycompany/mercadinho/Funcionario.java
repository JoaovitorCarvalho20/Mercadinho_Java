package com.mycompany.mercadinho;

/**
 * Esta é a classe que representa um Funcionário em um mercadinho.
 * Ela define os atributos nome, id e senha.
 * Também define um construtor parametrizado e um construtor padrão.
 * Além disso, ela inclui um método sobrescrito toString para fornecer uma representação em formato de string do objeto Funcionário.
 */
public class Funcionario {
    private String nome;
    private int id;
    private String senha;

    /**
     * Construtor parametrizado para criar um objeto Funcionário com nome, ID e senha.
     *
     * @param nome  O nome do funcionário.
     * @param id    O ID do funcionário.
     * @param senha A senha do funcionário.
     */
    public Funcionario(String nome, int id, String senha) {
        this.nome = nome;
        this.id = id;
        this.senha = senha;
    }

    /**
     * Construtor padrão vazio.
     */
    public Funcionario() {
    }

    /**
     * Obtém o nome do funcionário.
     *
     * @return O nome do funcionário.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do funcionário.
     *
     * @param nome O novo nome do funcionário.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o ID do funcionário.
     *
     * @return O ID do funcionário.
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID do funcionário.
     *
     * @param id O novo ID do funcionário.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtém a senha do funcionário.
     *
     * @return A senha do funcionário.
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Define a senha do funcionário.
     *
     * @param senha A nova senha do funcionário.
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Substitui o método toString para fornecer uma representação em formato de string do objeto Funcionário.
     *
     * @return Uma representação em formato de string do objeto Funcionário.
     */
    @Override
    public String toString() {
        return "Funcionario{" + "nome=" + nome + ", id=" + id + ", senha=" + senha + '}';
    }
}
