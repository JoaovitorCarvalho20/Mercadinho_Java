package com.mycompany.mercadinho;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A classe GerenciamentoFiscal lida com o cálculo das vendas e informações fiscais relacionadas.
 */
public class GerenciamentoFiscal {
    private List<Venda> vendas; // Lista de vendas registradas.

    /**
     * Construtor que inicializa a classe com a lista de vendas.
     *
     * @param vendas A lista de vendas registradas.
     */
    public GerenciamentoFiscal(List<Venda> vendas) {
        this.vendas = vendas;
    }

    /**
     * Calcula o total das vendas em um determinado dia.
     *
     * @param data A data para a qual as vendas diárias devem ser calculadas.
     * @return O valor total das vendas para o dia especificado.
     */
    public double calcularVendasDiarias(Date data) {
        double totalVendas = 0;
        for (Venda venda : vendas) {
            Date dataVenda = venda.getData();
            if (isSameDay(data, dataVenda)) {
                totalVendas += venda.getValorTotal();
            }
        }
        return totalVendas;
    }

    /**
     * Calcula o total das vendas em um determinado mês e ano.
     *
     * @param ano O ano para o qual as vendas mensais devem ser calculadas.
     * @param mes O mês para o qual as vendas mensais devem ser calculadas.
     * @return O valor total das vendas para o mês e ano especificados.
     */
    public double calcularVendasMensais(int ano, int mes) {
        double totalVendas = 0;
        for (Venda venda : vendas) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(venda.getData());
            int anoVenda = cal.get(Calendar.YEAR);
            int mesVenda = cal.get(Calendar.MONTH) + 1; // Janeiro é 0, então somamos 1
            if (ano == anoVenda && mes == mesVenda) {
                totalVendas += venda.getValorTotal();
            }
        }
        return totalVendas;
    }

    /**
     * Calcula o total das vendas em um determinado ano.
     *
     * @param ano O ano para o qual as vendas anuais devem ser calculadas.
     * @return O valor total das vendas para o ano especificado.
     */
    public double calcularVendasAnuais(int ano) {
        double totalVendas = 0;
        for (Venda venda : vendas) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(venda.getData());
            int anoVenda = cal.get(Calendar.YEAR);
            if (ano == anoVenda) {
                totalVendas += venda.getValorTotal();
            }
        }
        return totalVendas;
    }

    /**
     * Verifica se duas datas representam o mesmo dia.
     *
     * @param date1 A primeira data a ser comparada.
     * @param date2 A segunda data a ser comparada.
     * @return Verdadeiro se as datas representam o mesmo dia, caso contrário, falso.
     */
    private boolean isSameDay(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
               cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
               cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public String toString() {
        double totalVendasDiarias = calcularVendasDiarias(new Date());
        Calendar cal = Calendar.getInstance();
        int ano = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH) + 1;
        double totalVendasMensais = calcularVendasMensais(ano, mes);
        double totalVendasAnuais = calcularVendasAnuais(ano);

        return "GerenciamentoFiscal{" +
                "vendas=" + vendas +
                ", totalVendasDiarias=" + totalVendasDiarias +
                ", totalVendasMensais=" + totalVendasMensais +
                ", totalVendasAnuais=" + totalVendasAnuais +
                '}';
    }
}
