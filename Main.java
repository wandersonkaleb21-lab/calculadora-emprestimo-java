package br.com.simuladoremprestimo;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * Classe principal responsavel pela interacao via console, validacao
 * de entradas do usuario e exibicao formatada dos resultados.
 */
public class Main {
    private static final Locale LOCALE_PT_BR = new Locale("pt", "BR");

    public static void main(String[] args) {
        Locale.setDefault(LOCALE_PT_BR);
        Scanner scanner = new Scanner(System.in);

        exibirCabecalho();

        boolean continuar = true;
        while (continuar) {
            double valorEmprestimo = lerDoublePositivo(scanner, "Digite o valor total do emprestimo (R$): ");
            double taxaMensal = lerDoubleNaoNegativo(scanner, "Digite a taxa de juros mensal (%): ");
            int prazoMeses = lerInteiroPositivo(scanner, "Digite o prazo de pagamento (em meses): ");

            CalculadoraEmprestimo calculadora = new CalculadoraEmprestimo(valorEmprestimo, taxaMensal, prazoMeses);
            List<Parcela> demonstrativo = calculadora.gerarDemonstrativo();

            exibirResumoInicial(calculadora);
            exibirTabelaDemonstrativo(demonstrativo);
            exibirResumoFinal(calculadora, demonstrativo);

            continuar = perguntarNovaSimulacao(scanner);
        }

        System.out.println("\nObrigado por utilizar a Calculadora de Emprestimos. Ate mais!");
        scanner.close();
    }

    private static void exibirCabecalho() {
        System.out.println("=============================================================================");
        System.out.println("           CALCULADORA DE PARCELAMENTOS E JUROS (TABELA PRICE)");
        System.out.println("=============================================================================");
    }

    private static double lerDoublePositivo(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim().replace(",", ".");
            try {
                double valor = Double.parseDouble(entrada);
                if (Double.isNaN(valor) || Double.isInfinite(valor)) {
                    System.out.println("[Erro] Valor invalido. Tente novamente.");
                } else if (valor <= 0) {
                    System.out.println("[Erro] O valor deve ser maior que zero.");
                } else {
                    return valor;
                }
            } catch (NumberFormatException e) {
                System.out.println("[Erro] Entrada invalida. Digite um numero valido (ex.: 5000.00).");
            }
        }
    }

    private static double lerDoubleNaoNegativo(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim().replace(",", ".");
            try {
                double valor = Double.parseDouble(entrada);
                if (Double.isNaN(valor) || Double.isInfinite(valor)) {
                    System.out.println("[Erro] Taxa invalida. Tente novamente.");
                } else if (valor < 0) {
                    System.out.println("[Erro] A taxa de juros nao pode ser negativa.");
                } else {
                    return valor;
                }
            } catch (NumberFormatException e) {
                System.out.println("[Erro] Entrada invalida. Digite uma porcentagem valida (ex.: 1.5).");
            }
        }
    }

    private static int lerInteiroPositivo(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();
            try {
                int valor = Integer.parseInt(entrada);
                if (valor <= 0) {
                    System.out.println("[Erro] O prazo deve ser maior que zero meses.");
                } else {
                    return valor;
                }
            } catch (NumberFormatException e) {
                System.out.println("[Erro] Entrada invalida. Digite um numero inteiro de meses (ex.: 12).");
            }
        }
    }

    private static void exibirResumoInicial(CalculadoraEmprestimo calc) {
        System.out.println("\n--- Resumo da Simulacao ---");
        System.out.printf("Valor do emprestimo  : R$ %,.2f%n", calc.getValorPrincipal());
        System.out.printf("Taxa de juros mensal : %.2f%%%n", calc.getTaxaMensalPercentual());
        System.out.printf("Prazo                : %d meses%n", calc.getPrazoMeses());
        System.out.printf("Parcela fixa (Price) : R$ %,.2f%n", calc.calcularValorParcela());
    }

    private static void exibirTabelaDemonstrativo(List<Parcela> parcelas) {
        System.out.println("\n-----------------------------------------------------------------------------");
        System.out.printf("%-8s | %-15s | %-15s | %-15s | %-15s%n",
                "Parcela", "Valor (R$)", "Juros (R$)", "Amortizacao (R$)", "Saldo Devedor");
        System.out.println("-----------------------------------------------------------------------------");

        for (Parcela p : parcelas) {
            System.out.printf("%-8d | %15.2f | %15.2f | %15.2f | %15.2f%n",
                    p.getNumero(),
                    p.getValorParcela(),
                    p.getJuros(),
                    p.getAmortizacao(),
                    p.getSaldoDevedor());
        }
        System.out.println("-----------------------------------------------------------------------------");
    }

    private static void exibirResumoFinal(CalculadoraEmprestimo calc, List<Parcela> parcelas) {
        double totalPago = calc.calcularTotalPago(parcelas);
        double totalJuros = calc.calcularTotalJuros(parcelas);

        System.out.println("\n--- Resumo Final ---");
        System.out.printf("Total de juros acumulados : R$ %,.2f%n", totalJuros);
        System.out.printf("Valor total pago ao final : R$ %,.2f%n", totalPago);
    }

    private static boolean perguntarNovaSimulacao(Scanner scanner) {
        while (true) {
            System.out.print("\nDeseja realizar outra simulacao? (s/n): ");
            String resposta = scanner.nextLine().trim().toLowerCase(LOCALE_PT_BR);
            if (resposta.equals("s") || resposta.equals("sim")) {
                return true;
            }
            if (resposta.equals("n") || resposta.equals("nao") || resposta.equals("não")) {
                return false;
            }
            System.out.println("[Erro] Responda com 's' para sim ou 'n' para nao.");
        }
    }
}
