package com.mycompany.mercadinho;

import java.util.Date;
import java.util.List;

public class Caixa {
    private int numeroCaixa;
    private List<Venda> vendasDiarias;
    private List<Venda> vendasMensais;
    private GerenciamentoFiscal gerenciamentoFiscal;
    private Funcionario funcionarioResponsavel;

    public Caixa(int numeroCaixa, List<Venda> vendasDiarias, List<Venda> vendasMensais, GerenciamentoFiscal gerenciamentoFiscal) {
        this.numeroCaixa = numeroCaixa;
        this.vendasDiarias = vendasDiarias;
        this.vendasMensais = vendasMensais;
        this.gerenciamentoFiscal = gerenciamentoFiscal;
    }
    public Caixa(){

    }

    public int getNumeroCaixa() {
        return numeroCaixa;
    }

    public void setNumeroCaixa(int numeroCaixa) {
        this.numeroCaixa = numeroCaixa;
    }

    public double calcularRendimentoDiario(Date data) {
        double rendimentoDiario = gerenciamentoFiscal.calcularVendasDiarias(data);
        return rendimentoDiario;
    }

    public double calcularRendimentoMensal(int ano, int mes) {
        double rendimentoMensal = gerenciamentoFiscal.calcularVendasMensais(ano, mes);
        return rendimentoMensal;
    }

    public double calcularRendimentoAnual(int ano) {
        double rendimentoAnual = gerenciamentoFiscal.calcularVendasAnuais(ano);
        return rendimentoAnual;
    }

    public Funcionario getFuncionarioResponsavel() {
        return funcionarioResponsavel;
    }

    public void setFuncionarioResponsavel(Funcionario funcionarioResponsavel) {
        this.funcionarioResponsavel = funcionarioResponsavel;
    }

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
