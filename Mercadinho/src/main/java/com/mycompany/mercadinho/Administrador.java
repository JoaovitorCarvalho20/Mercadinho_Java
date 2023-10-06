
package com.mycompany.mercadinho;

// Uso de herança dos atributos defindos em funcionario

public class Administrador extends Funcionario {
   private boolean isAdm;

    public Administrador(String nome, String id, String senha) {
        super(nome, id, senha);
        this.setIsAdm(true);
    }
    public Administrador(){
        this.setIsAdm(true);
    }

    public boolean isIsAdm() {
        return isAdm;
    }

    public void setIsAdm(boolean isAdm) {
        this.isAdm = isAdm;
    }

    
    //metodo toString sobrescrito que mostra nome, id, senha.
    @Override
    public String toString() {
        return "Administrador{" + "isAdm=" + isAdm +"Nome="+this.getNome()+"ID="+this.getId()+"SENHA"+this.getSenha()+'}';
    }

    
  
    }
   
   
    
    

