package br.com.simuladoremprestimo;

/**
 * Representa uma parcela mensal dentro do demonstrativo de amortizacao
 * do emprestimo (Sistema Price).
 */
public class Parcela {
    private final int numero;
    private final double valorParcela;
    private final double juros;
    private final double amortizacao;
    private final double saldoDevedor;

    public Parcela(int numero, double valorParcela, double juros, double amortizacao, double saldoDevedor) {
        this.numero = numero;
        this.valorParcela = valorParcela;
        this.juros = juros;
        this.amortizacao = amortizacao;
        this.saldoDevedor = Math.max(0.0, saldoDevedor);
    }

    public int getNumero() {
        return numero;
    }

    public double getValorParcela() {
        return valorParcela;
    }

    public double getJuros() {
        return juros;
    }

    public double getAmortizacao() {
        return amortizacao;
    }

    public double getSaldoDevedor() {
        return saldoDevedor;
    }
}
