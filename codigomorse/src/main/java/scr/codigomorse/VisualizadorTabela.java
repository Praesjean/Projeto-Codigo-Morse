package scr.codigomorse;

import java.util.Map;

public class VisualizadorTabela {

    public static void imprimirTabelaMorse(ArvoreMorse arvore) {
        System.out.println("========== TABELA DE CÓDIGO MORSE ==========");
        System.out.println("\n┌───────────┬──────────────────┐");
        System.out.println("│ Caractere │   Código Morse   │");
        System.out.println("├───────────┼──────────────────┤");

        arvore.tabelaCodificacao.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry ->
                        System.out.printf("│     %c     │      %-11s │\n", entry.getKey(), entry.getValue())
                );

        System.out.println("└───────────┴──────────────────┘\n");
    }
}
