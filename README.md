# Calculadora de Parcelamentos de Emprestimo e Juros

## Sobre o Projeto
Este projeto nasceu com o proposito de tornar o planejamento financeiro mais transparente e acessivel. Muitas vezes, ao contratar um emprestimo ou financiamento, o consumidor olha apenas para o valor da parcela mensal e nao percebe quanto esta pagando de juros ao longo do tempo nem como o saldo devedor realmente diminui.

Desenvolvida em **Java SE** para execucao via console na IDE **Eclipse**, esta aplicacao simula emprestimos utilizando o sistema de amortizacao frances (**Tabela Price**), detalhando mes a mes os juros incidentes, a amortizacao do capital e o saldo devedor atualizado.

---

## Funcionalidades
- **Entrada Interativa e Segura:** Leitura de valor do emprestimo, taxa de juros mensal (%) e prazo (meses) via console (`Scanner`), aceitando tanto ponto quanto virgula como separador decimal.
- **Validacao de Dados:** Impede valores negativos, prazo igual a zero ou entradas nao numericas sem encerrar o programa abruptamente.
- **Calculo pela Tabela Price:** Calcula a prestacao fixa com base em juros compostos.
- **Demonstrativo Mes a Mes:** Exibe uma tabela alinhada contendo numero da parcela, valor da parcela, juros, amortizacao e saldo devedor atualizado.
- **Resumo Financeiro Final:** Apresenta o total de juros acumulados e o montante total pago ao termino do contrato.

---

## Estrutura do Projeto
```text
calculadora-emprestimo-java/
├── src/
│   └── br/
│       └── com/
│           └── simuladoremprestimo/
│               ├── Main.java                  # Interface via console, validacao e exibicao
│               ├── CalculadoraEmprestimo.java # Regras financeiras e geracao do demonstrativo
│               └── Parcela.java               # Modelo de dominio encapsulado de cada mes
└── README.md
```

---

## Como Executar no Eclipse
1. Abra a IDE **Eclipse**.
2. Va em **File > Open Projects from File System...** (ou crie um novo projeto Java em **File > New > Java Project** e copie a pasta `src`).
3. Selecione a pasta raiz `calculadora-emprestimo-java` e clique em **Finish**.
4. No **Package Explorer**, navegue ate `src/br/com/simuladoremprestimo/Main.java`.
5. Clique com o botao direito sobre `Main.java` e selecione **Run As > Java Application**.
6. Siga as instrucoes exibidas na aba **Console** do Eclipse.

---

## Exemplo de Execucao
```text
=============================================================================
           CALCULADORA DE PARCELAMENTOS E JUROS (TABELA PRICE)
=============================================================================
Digite o valor total do emprestimo (R$): 10000
Digite a taxa de juros mensal (%): 2
Digite o prazo de pagamento (em meses): 6

--- Resumo da Simulacao ---
Valor do emprestimo  : R$ 10.000,00
Taxa de juros mensal : 2,00%
Prazo                : 6 meses
Parcela fixa (Price) : R$ 1.785,26
```

---

## Tecnologias e Boas Praticas Aplicadas
- **Java SE** (sem bibliotecas externas, interfaces graficas ou bancos de dados)
- **Programacao Orientada a Objetos (POO):** Encapsulamento, separacao de responsabilidades e uso de `ArrayList<Parcela>`
- **Tratamento de Excecoes:** Uso de `try-catch` (`NumberFormatException`) e validacoes de dominio (`IllegalArgumentException`)
