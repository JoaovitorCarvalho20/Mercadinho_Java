package com.mycompany.mercadinho;

/**
 * Esta é a classe que representa um Administrador em um mercadinho.
 * Ela herda os métodos e atributos da classe Funcionário e adiciona o atributo isAdm.
 */
public class Administrador extends Funcionario {
    private boolean isAdm;

    /**
     * Construtor parametrizado para criar um objeto Administrador com nome, ID, senha e definir se é administrador.
     *
     * @param nome  O nome do administrador.
     * @param id    O ID do administrador.
     * @param senha A senha do administrador.
     */
    public Administrador(String nome, int id, String senha) {
        super(nome, id, senha);
        this.setIsAdm(true);
    }

    /**
     * Construtor padrão vazio que define por padrão que é um administrador.
     */
    public Administrador() {
        this.setIsAdm(true);
    }

    /**
     * Verifica se o funcionário é um administrador.
     *
     * @return `true` se o funcionário é um administrador, caso contrário `false`.
     */
    public boolean isIsAdm() {
        return isAdm;
    }

    /**
     * Define se o funcionário é um administrador.
     *
     * @param isAdm `true` se o funcionário é um administrador, caso contrário `false`.
     */
    public void setIsAdm(boolean isAdm) {
        this.isAdm = isAdm;
    }

    /**
     * Substitui o método toString para fornecer uma representação em formato de string do objeto Administrador.
     *
     * @return Uma representação em formato de string do objeto Administrador.
     */
    @Override
    public String toString() {
        return "Administrador{" + "isAdm=" + isAdm + ", Nome=" + this.getNome() + ", ID=" + this.getId() + ", SENHA=" + this.getSenha() + '}';
    }
}
