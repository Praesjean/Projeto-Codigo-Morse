import java.util.Scanner;

public class InterfaceUsuario {
    private Scanner scanner;
    private ArvoreMorse arvore;

    public InterfaceUsuario(ArvoreMorse arvore) {
        this.scanner = new Scanner(System.in);
        this.arvore = arvore;
    }

    public void exibirMenu() {
        System.out.println("\nMENU PRINCIPAL");
        System.out.println("\n1. Codificar texto para Morse");
        System.out.println("\n2. Decodificar Morse para texto");
        System.out.println("\n3. Visualizar árvore binária");
        System.out.println("\n4. Ver tabela de código Morse");
        System.out.println("\n5. Demonstração com exemplo");
        System.out.println("\n0. Sair");
        System.out.print("\nEscolha uma opção: ");
    }

    public int obterOpcao() {
        try {
            int opcao = scanner.nextInt();
            scanner.nextLine();
            return opcao;
        } catch (Exception e) {
            scanner.nextLine();
            return -1;
        }
    }

    public void codificarTexto() {

        System.out.println("\nCODIFICAÇÃO DE TEXTO");
        System.out.print("\nDigite o texto a ser codificado: ");
        String texto = scanner.nextLine();

        String codigoMorse = arvore.codificar(texto);

        System.out.println("\nResultado");
        System.out.println("\nTexto original: " + texto);
        System.out.println("\nCódigo Morse:   " + codigoMorse);
    }

    public void decodificarMorse() {
        System.out.println("\nDECODIFICAÇÃO DE CÓDIGO MORSE");
        System.out.println("\nInstruções:");
        System.out.println("\nUse pontos (.) e traços (-)");
        System.out.println("\nSepare letras com um espaço");
        System.out.println("\nSepare palavras com três espaços");
        System.out.print("\nDigite o código Morse: ");
        String codigoMorse = scanner.nextLine();
        
        String textoDecodificado = arvore.decodificar(codigoMorse);
 
        System.out.println("\nResultado");
        System.out.println("\nCódigo Morse:      " + codigoMorse);
        System.out.println("\nTexto decodificado: " + textoDecodificado);
    }

    public void visualizarArvore() {
        VisualizadorArvore.imprimirArvore(arvore);
    }

    public void visualizarTabela() {
        VisualizadorArvore.imprimirTabelaMorse(arvore);
    }

    public void demonstrarExemplo() {
        System.out.println("\nDEMONSTRAÇÃO COM EXEMPLO");

        String textoExemplo = "\nGalo vai ser campeao da sulamericana";
        System.out.println("\nTexto de exemplo: " + textoExemplo);

        String morse = arvore.codificar(textoExemplo);
        System.out.println("\nCodificado:       " + morse);
        
        String decodificado = arvore.decodificar(morse);
        System.out.println("\nDecodificado:     " + decodificado);
        
        System.out.println("\nA codificação e decodificação funcionam corretamente!");
    }

    public void aguardarContinuar() {
        System.out.println("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }

    public void fechar() {
        scanner.close();
    }
}