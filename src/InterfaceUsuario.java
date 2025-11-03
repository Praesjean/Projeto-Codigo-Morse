import java.util.Scanner;

public class InterfaceUsuario {
    private Scanner scanner;
    private ArvoreMorse arvore;

    public InterfaceUsuario(ArvoreMorse arvore) {
        this.scanner = new Scanner(System.in);
        this.arvore = arvore;
    }

    public void exibirMenu() {
        System.out.println("\n========== MENU PRINCIPAL ==========");
        System.out.println("\n1. Codificar texto para Morse");
        System.out.println("\n2. Decodificar Morse para texto");
        System.out.println("\n3. Visualizar árvore (Console)");
        System.out.println("\n4. Ver tabela de código Morse");
        System.out.println("\n0. Sair");
        System.out.println("\nCaracteres na árvore: " + arvore.getTamanhoArvore());
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
        System.out.println("\n========== CODIFICAÇÃO DE TEXTO ==========");
        System.out.print("\nDigite o texto a ser codificado: ");
        String texto = scanner.nextLine();

        System.out.println("\nProcessando e construindo árvore...\n");
        String codigoMorse = arvore.codificar(texto);

        System.out.println("\n========== Resultado ==========");
        System.out.println("\nTexto original: " + texto);
        System.out.println("Código Morse:   " + codigoMorse);
        System.out.println("\nA árvore foi atualizada com os caracteres usados!");
        System.out.println("===============================");
    }

    public void decodificarMorse() {
        System.out.println("\n========== DECODIFICAÇÃO DE CÓDIGO MORSE ==========");
        System.out.println("\nInstruções:");
        System.out.println("  - Use pontos (.) e traços (-)");
        System.out.println("  - Separe letras com um espaço");
        System.out.println("  - Separe palavras com três espaços");
        System.out.println("\n===================================================");
        System.out.print("\nDigite o código Morse: ");
        String codigoMorse = scanner.nextLine();
        
        System.out.println("\nProcessando e construindo árvore...\n");
        String textoDecodificado = arvore.decodificar(codigoMorse);
 
        System.out.println("\n========== Resultado ==========");
        System.out.println("\nCódigo Morse:      " + codigoMorse);
        System.out.println("Texto decodificado: " + textoDecodificado);
        System.out.println("\nA árvore foi atualizada com os caracteres usados!");
        System.out.println("===============================");
    }

    public void visualizarArvore() {
        if (arvore.getTamanhoArvore() == 0) {
            System.out.println("\nA árvore ainda está vazia!");
            System.out.println("Dica: Use as opções 1 ou 2 para codificar/decodificar");
            System.out.println("e a árvore será construída automaticamente.");
            return;
        }
        VisualizadorArvore.imprimirArvore(arvore);
    }

    public void visualizarTabela() {
        if (arvore.getTamanhoArvore() == 0) {
            System.out.println("\nA árvore ainda está vazia!");
            System.out.println("Dica: Use as opções 1 ou 2 para codificar/decodificar");
            System.out.println("e a árvore será construída automaticamente.");
            return;
        }
        VisualizadorArvore.imprimirTabelaMorse(arvore);
    }

    public void aguardarContinuar() {
        System.out.println("\nPressione ENTER para continuar...");
        scanner.nextLine();
    }

    public void fechar() {
        scanner.close();
    }
}