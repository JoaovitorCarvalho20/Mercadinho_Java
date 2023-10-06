package com.mycompany.mercadinho;
 //A classe Clientedefine um tipo de dado abstrato para a criação da estrutura de classes de contas bancárias

public class Cliente {
    private String nome;
    private String telefone;
    private String endereco;
    private String cpf;
    private String email;
    
// usar o private é interesante por conta da visibiidade,garante a segunça dos acessos e é usado para contruir o encapulamento  
//Um método estático pertence à classe, não a instâncias individuais da classe.
    private static int numCliente=0;
// vantagen do uso so protectd acesso direto e permanencia nos filhos
   protected  static int numCliente2=0;
    
   //Contrutor padrao, ele tem o intuito de caontar as instancias 
    public Cliente(){
        numCliente++;
    }
  
    // Metodo que retorna a quantidade de clientes instanciados;
    public static int getNumClientes(){
        return numCliente;
    }
     /* Construtor sobrecarregado da classe Cliente.
     nome String que identifica o nome do cliente.
     telefone String que identifica o telefone do cliente.
     endereco String que identifica o endereço do cliente.
     cpf String que identifica o CPF do cliente.
    email String que indentifica o email do cliente.
    */

    public Cliente(String nome, String telefone, String endereco, String cpf, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cpf = cpf;
        this.email = email;
    }
// Metodo usado para acessar o nome do cliente e retorna o nome do cliente 
    public String getNome() {
        return nome;
    }
//Metodo usado para definir o nome do clente
//nome Será repassado String com o nome do cliente a ser definido.
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
// Metodo usado para acessar o telefone do cliente e retorna o nome do cliente 
    public String getTelefone() {
        return telefone;
    }
//Metodo usado para definir o telefone do clente
//telefone Será repassado String com o telefone do cliente a ser definido.
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    
 // Metodo usado para acessar o endereço do cliente e retorna o nome do cliente 
    public String getEndereco() {
        return endereco;
    }

//Metodo usado para definir o telefone do clente
//endereço Será repassado String com o telefone do cliente a ser definido.
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

   // Metodo usado para acessar o cpf do cliente e retorna o nome do cliente 
    public String getCpf() {
        return cpf;
    }
//Metodo usado para definir o cpf do clente
//cpf Será repassado String com o cpf do cliente a ser definido.
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    
// Metodo usado para acessar o email do cliente e retorna o nome do cliente 
    public String getEmail() {
        return email;
    }
    
//Metodo usado para definir o email do clente
//email Será repassado String com o email do cliente a ser definido.
    public void setEmail(String email) {
        this.email = email;
    }

// metodo toString reescrito para retornar o nome telefone,endereço,cpf,email

    @Override
    public String toString() {
        return "Cliente{" + "nome=" + nome + ", telefone=" + telefone + ", endereco=" + endereco + ", cpf=" + cpf + ", email=" + email + '}';
    }
    
    
    
    
}
