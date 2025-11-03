import java.util.Map;

public class VisualizadorArvore {

    public static void imprimirArvore(ArvoreMorse arvore) {

        System.out.println("\nÁRVORE DE CÓDIGO MORSE");
        System.out.println("\n(. = esquerda, - = direita)");
        imprimirArvoreRecursivo(arvore.raiz, "", true, "RAIZ");
    }

    private static void imprimirArvoreRecursivo(NoMorse no, String prefixo, boolean ehUltimo, String direcao) {
        if (no != null) {
            System.out.print(prefixo);
            System.out.print(ehUltimo ? "└── " : "├── ");

            String valorNo = no.caractere != '\0' ? String.valueOf(no.caractere) : "[ ]";
            System.out.println(direcao + ": " + valorNo);
            
            String novoPrefixo = prefixo + (ehUltimo ? "    " : "│   ");

            if (no.esquerda != null) {
                imprimirArvoreRecursivo(no.esquerda, novoPrefixo, no.direita == null, ".");
            }

            if (no.direita != null) {
                imprimirArvoreRecursivo(no.direita, novoPrefixo, true, "-");
            }
        }
    }

    public static void imprimirTabelaMorse(ArvoreMorse arvore) {
        System.out.println("\nTABELA DE CÓDIGO MORSE");
        System.out.println("\nCaractere | Código Morse");

        arvore.tabelaCodificacao.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .forEach(entry -> 
                System.out.printf("    %c     |     %s\n", entry.getKey(), entry.getValue())
            );
    }
}