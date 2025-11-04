package scr.codigomorse;

public class Main {
    public static void main(String[] args) {
        ArvoreMorse arvore = new ArvoreMorse();
        InterfaceUsuario ui = new InterfaceUsuario(arvore);

        exibirCabecalho();

        boolean continuar = true;

        while (continuar) {
            ui.exibirMenu();
            int opcao = ui.obterOpcao();

            switch (opcao) {
                case 1:
                    ui.codificarTexto();
                    break;
                case 2:
                    ui.decodificarMorse();
                    break;
                case 3:
                    ui.visualizarArvoreJavaFX();
                    break;
                case 4:
                    ui.visualizarTabela();
                    break;
                case 0:
                    continuar = false;
                    exibirMensagemDespedida();
                    break;
                default:
                    System.out.println("\nOpção inválida! Tente novamente.");
            }

            if (continuar && opcao != 0 && opcao != 3) {
                ui.aguardarContinuar();
            }
        }

        ui.fechar();
        System.exit(0);
    }

    private static void exibirCabecalho() {
        System.out.println("\n╔═══════════════════════════════════════════════╗");
        System.out.println("║     SISTEMA DE CÓDIGO MORSE - TDE 2           ║");
        System.out.println("║     Implementação com Árvore Binária          ║");
        System.out.println("║     Visualização JavaFX                       ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
        System.out.println("\nA árvore inicia vazia e é construída dinamicamente!");
        System.out.println("Cada vez que você codifica ou decodifica, novos");
        System.out.println("caracteres são automaticamente adicionados à árvore.\n");
    }

    private static void exibirMensagemDespedida() {
        System.out.println("========== Obrigado por usar nosso sistema! ==========");
    }
}