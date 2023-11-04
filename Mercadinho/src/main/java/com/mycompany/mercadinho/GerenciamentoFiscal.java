package com.mycompany.mercadinho;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GerenciamentoFiscal {
    private List<Venda> vendas;

    public GerenciamentoFiscal(List<Venda> vendas) {
        this.vendas = vendas;
    }

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
