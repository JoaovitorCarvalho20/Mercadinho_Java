package com.mycompany.mercadinho;

public class Administrador extends Funcionario {
    private boolean isAdm;

    public Administrador(String nome, int id, String senha) { // Atualizado o construtor
        super(nome, id, senha);
        this.setIsAdm(true);
    }

    public Administrador() {
        this.setIsAdm(true);
    }

    public boolean isIsAdm() {
        return isAdm;
    }

    public void setIsAdm(boolean isAdm) {
        this.isAdm = isAdm;
    }

    @Override
    public String toString() {
        return "Administrador{" + "isAdm=" + isAdm + ", Nome=" + this.getNome() + ", ID=" + this.getId() + ", SENHA=" + this.getSenha() + '}';
    }
}