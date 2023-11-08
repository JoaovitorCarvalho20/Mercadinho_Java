package com.mycompany.mercadinho;

/**
 * Esta é a classe que define um tipo de dado abstrato para a criação da estrutura de classes de contas bancárias.
 */
public class Cliente {
    private String nome;
    private String telefone;
    private String endereco;
    private String cpf;
    private String email;

    // Usar "private" é interessante para garantir a visibilidade e a segurança dos acessos, além de ser usado para construir o encapsulamento.
    private static int numCliente = 0;

    // A vantagem do uso de "protected" é o acesso direto e a permanência nos filhos.
    protected static int numCliente2 = 0;

    /**
     * Construtor padrão de Cliente. Incrementa o número de clientes.
     */
    public Cliente() {
        numCliente++;
    }

    /**
     * Obtém o número de clientes instanciados.
     *
     * @return O número de clientes instanciados.
     */
    public static int getNumClientes() {
        return numCliente;
    }

    /**
     * Construtor sobrecarregado da classe Cliente.
     *
     * @param nome     O nome do cliente.
     * @param telefone O telefone do cliente.
     * @param endereco O endereço do cliente.
     * @param cpf      O CPF do cliente.
     * @param email    O email do cliente.
     */
    public Cliente(String nome, String telefone, String endereco, String cpf, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cpf = cpf;
        this.email = email;
    }

    /**
     * Obtém o nome do cliente.
     *
     * @return O nome do cliente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do cliente.
     *
     * @param nome O novo nome do cliente.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o telefone do cliente.
     *
     * @return O telefone do cliente.
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Define o telefone do cliente.
     *
     * @param telefone O novo telefone do cliente.
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Obtém o endereço do cliente.
     *
     * @return O endereço do cliente.
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Define o endereço do cliente.
     *
     * @param endereco O novo endereço do cliente.
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * Obtém o CPF do cliente.
     *
     * @return O CPF do cliente.
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Define o CPF do cliente.
     *
     * @param cpf O novo CPF do cliente.
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Obtém o email do cliente.
     *
     * @return O email do cliente.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o email do cliente.
     *
     * @param email O novo email do cliente.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sobrescreve o método toString para retornar informações do cliente.
     *
     * @return Uma representação em formato de string do objeto Cliente.
     */
    @Override
    public String toString() {
        return "Cliente{" + "nome=" + nome + ", telefone=" + telefone + ", endereco=" + endereco + ", cpf=" + cpf + ", email=" + email + '}';
    }
}
