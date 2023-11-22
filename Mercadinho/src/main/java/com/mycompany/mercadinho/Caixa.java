package com.mycompany.mercadinho;

import java.util.Date;
import java.util.List;

/**
 * A classe Caixa representa uma caixa registradora em um mercadinho.
 */
public class Caixa {
    private int numeroCaixa; // Número do caixa.
    private List<Venda> vendasDiarias; // Lista de vendas diárias.
    private List<Venda> vendasMensais; // Lista de vendas mensais.
    private GerenciamentoFiscal gerenciamentoFiscal; // Gerenciamento fiscal do caixa.
    private Funcionario  funcionarioResponsavel; // Funcionário responsável pelo caixa.
 
    /**
     * Construtor parametrizado que cria uma instância de Caixa com informações iniciais.
     *
     * @param numeroCaixa       O número do caixa.
     * @param vendasDiarias     A lista de vendas diárias.
     * @param vendasMensais     A lista de vendas mensais.
     * @param gerenciamentoFiscal O gerenciamento fiscal associado ao caixa.
     */
    public Caixa(int numeroCaixa, List<Venda> vendasDiarias, List<Venda> vendasMensais, GerenciamentoFiscal gerenciamentoFiscal) {
        this.numeroCaixa = numeroCaixa;
        this.vendasDiarias = vendasDiarias;
        this.vendasMensais = vendasMensais;
        this.gerenciamentoFiscal = gerenciamentoFiscal;
    }

    /**
     * Construtor padrão vazio.
     */
    public Caixa() {
    }

    /**
     * Obtém o número do caixa.
     *
     * @return O número do caixa.
     */
    public int getNumeroCaixa() {
        return numeroCaixa;
    }

    /**
     * Define o número do caixa.
     *
     * @param numeroCaixa O novo número do caixa.
     */
    public void setNumeroCaixa(int numeroCaixa) {
        this.numeroCaixa = numeroCaixa;
    }

    /**
     * Calcula o rendimento diário com base em uma data específica.
     *
     * @param data A data para o cálculo do rendimento.
     * @return O rendimento diário.
     */
    public double calcularRendimentoDiario(Date data) {
        double rendimentoDiario = gerenciamentoFiscal.calcularVendasDiarias(data);
        return rendimentoDiario;
    }

    /**
     * Calcula o rendimento mensal com base no ano e mês especificados.
     *
     * @param ano O ano para o cálculo do rendimento.
     * @param mes O mês para o cálculo do rendimento.
     * @return O rendimento mensal.
     */
    public double calcularRendimentoMensal(int ano, int mes) {
        double rendimentoMensal = gerenciamentoFiscal.calcularVendasMensais(ano, mes);
        return rendimentoMensal;
    }

    /**
     * Calcula o rendimento anual com base no ano especificado.
     *
     * @param ano O ano para o cálculo do rendimento.
     * @return O rendimento anual.
     */
    public double calcularRendimentoAnual(int ano) {
        double rendimentoAnual = gerenciamentoFiscal.calcularVendasAnuais(ano);
        return rendimentoAnual;
    }

    /**
     * Obtém o funcionário responsável pelo caixa.
     *
     * @return O funcionário responsável.
     */
    public Funcionario getFuncionarioResponsavel() {
        return funcionarioResponsavel;
    }

    /**
     * Define o funcionário responsável pelo caixa.
     *
     * @param funcionarioResponsavel O novo funcionário responsável.
     */
    public void setFuncionarioResponsavel(Funcionario funcionarioResponsavel) {
        this.funcionarioResponsavel = funcionarioResponsavel;
    }

    /**
     * Gera um relatório das vendas diárias, incluindo informações sobre o rendimento, a data, o número do caixa e o funcionário responsável.
     *
     * @param dataAtual A data atual para o relatório.
     * @return Uma representação em formato de string do relatório das vendas diárias.
     */
    public String gerarRelatorioVendasDiarias(Date dataAtual) {
        double rendimentoDiario = calcularRendimentoDiario(dataAtual);
        String nomeFuncionario = funcionarioResponsavel != null ? funcionarioResponsavel.getNome() : "Nenhum funcionário responsável";
        String tipoFuncionario = funcionarioResponsavel != null ? (funcionarioResponsavel instanceof Administrador ? "Administrador" : "Funcionário") : "N/A";

        return "Relatório de Vendas Diárias\n" +
               "Data: " + dataAtual +
               "\nNúmero do Caixa: " + numeroCaixa +
               "\nRendimento Diário: R$" + rendimentoDiario +
               "\nFuncionário Responsável: " + nomeFuncionario +
               "\nTipo de Funcionário: " + tipoFuncionario;
    }
}
