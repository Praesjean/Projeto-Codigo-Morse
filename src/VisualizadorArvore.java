import java.util.Map;

public class VisualizadorArvore {

    public static void imprimirArvore(ArvoreMorse arvore) {
        System.out.println("========== ÁRVORE DE CÓDIGO MORSE ==========");
        System.out.println("\nLegenda: . = esquerda | - = direita\n");
        imprimirArvoreRecursivo(arvore.raiz, "", true, "RAIZ", "");
        System.out.println();
    }

    private static void imprimirArvoreRecursivo(NoMorse no, String prefixo, boolean ehUltimo, String direcao, String caminho) {
        if (no != null) {
            System.out.print(prefixo);
            System.out.print(ehUltimo ? "└── " : "├── ");

            if (no.caractere != '\0') {
                System.out.println("'" + caminho + "' → " + no.caractere);
            } else {
                if (no.esquerda != null || no.direita != null) {
                    System.out.println(caminho.isEmpty() ? "○" : "'" + caminho + "'");
                }
            }
            
            String novoPrefixo = prefixo + (ehUltimo ? "    " : "│   ");

            if (no.esquerda != null) {
                String novoCaminho = caminho.isEmpty() ? "." : caminho + ".";
                imprimirArvoreRecursivo(no.esquerda, novoPrefixo, no.direita == null, ".", novoCaminho);
            }

            if (no.direita != null) {
                String novoCaminho = caminho.isEmpty() ? "-" : caminho + "-";
                imprimirArvoreRecursivo(no.direita, novoPrefixo, true, "-", novoCaminho);
            }
        }
    }

    public static void imprimirTabelaMorse(ArvoreMorse arvore) {
        System.out.println("========== TABELA DE CÓDIGO MORSE ==========");
        System.out.println("\n┌───────────┬──────────────────┐");
        System.out.println("│ Caractere │   Código Morse   │");
        System.out.println("├───────────┼──────────────────┤");

        arvore.tabelaCodificacao.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .forEach(entry -> 
                System.out.printf("│     %c     │      %-11s│\n", entry.getKey(), entry.getValue())
            );
        
        System.out.println("└───────────┴──────────────────┘\n");
    }
}