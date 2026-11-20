package br.com.simuladoremprestimo;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsavel pelos calculos financeiros do emprestimo utilizando
 * juros compostos pelo sistema de amortizacao frances (Tabela Price).
 */
public class CalculadoraEmprestimo {
    private final double valorPrincipal;
    private final double taxaMensalPercentual;
    private final int prazoMeses;

    public CalculadoraEmprestimo(double valorPrincipal, double taxaMensalPercentual, int prazoMeses) {
        validarParametros(valorPrincipal, taxaMensalPercentual, prazoMeses);
        this.valorPrincipal = valorPrincipal;
        this.taxaMensalPercentual = taxaMensalPercentual;
        this.prazoMeses = prazoMeses;
    }

    private void validarParametros(double valorPrincipal, double taxaMensalPercentual, int prazoMeses) {
        if (Double.isNaN(valorPrincipal) || Double.isInfinite(valorPrincipal) || valorPrincipal <= 0) {
            throw new IllegalArgumentException("O valor do emprestimo deve ser maior que zero.");
        }
        if (Double.isNaN(taxaMensalPercentual) || Double.isInfinite(taxaMensalPercentual) || taxaMensalPercentual < 0) {
            throw new IllegalArgumentException("A taxa de juros mensal nao pode ser negativa.");
        }
        if (prazoMeses <= 0) {
            throw new IllegalArgumentException("O prazo em meses deve ser maior que zero.");
        }
    }

    /**
     * Calcula o valor base da parcela fixa (PMT) pela formula da Tabela Price.
     */
    public double calcularValorParcela() {
        double taxaDecimal = taxaMensalPercentual / 100.0;
        if (taxaDecimal == 0.0) {
            return valorPrincipal / prazoMeses;
        }
        double fator = Math.pow(1.0 + taxaDecimal, prazoMeses);
        return valorPrincipal * ((taxaDecimal * fator) / (fator - 1.0));
    }

    /**
     * Gera a evolucao mes a mes do saldo devedor, juros e amortizacao.
     */
    public List<Parcela> gerarDemonstrativo() {
        List<Parcela> parcelas = new ArrayList<>();
        double pmt = calcularValorParcela();
        double taxaDecimal = taxaMensalPercentual / 100.0;
        double saldo = valorPrincipal;

        for (int mes = 1; mes <= prazoMeses; mes++) {
            double jurosMes = saldo * taxaDecimal;
            double amortizacaoMes;
            double parcelaEfetiva;

            if (mes == prazoMeses) {
                // Ajuste na ultima parcela para zerar o saldo sem distorcao de centavos
                amortizacaoMes = saldo;
                parcelaEfetiva = amortizacaoMes + jurosMes;
                saldo = 0.0;
            } else {
                amortizacaoMes = pmt - jurosMes;
                parcelaEfetiva = pmt;
                saldo -= amortizacaoMes;
            }

            parcelas.add(new Parcela(mes, parcelaEfetiva, jurosMes, amortizacaoMes, saldo));
        }

        return parcelas;
    }

    public double calcularTotalPago(List<Parcela> parcelas) {
        double total = 0.0;
        for (Parcela p : parcelas) {
            total += p.getValorParcela();
        }
        return total;
    }

    public double calcularTotalJuros(List<Parcela> parcelas) {
        double totalJuros = 0.0;
        for (Parcela p : parcelas) {
            totalJuros += p.getJuros();
        }
        return totalJuros;
    }

    public double getValorPrincipal() {
        return valorPrincipal;
    }

    public double getTaxaMensalPercentual() {
        return taxaMensalPercentual;
    }

    public int getPrazoMeses() {
        return prazoMeses;
    }
}
