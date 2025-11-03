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
                    ui.visualizarArvore();
                    break;
                case 4:
                    ui.visualizarTabela();
                    break;
                case 5:
                    ui.demonstrarExemplo();
                    break;
                case 0:
                    continuar = false;
                    exibirMensagemDespedida();
                    break;
                default:
                    System.out.println("\nOpção inválida! Tente novamente.");
            }

            if (continuar && opcao != 0) {
                ui.aguardarContinuar();
            }
        }

        ui.fechar();
    }

    private static void exibirCabecalho() {
        System.out.println("\nSISTEMA DE CÓDIGO MORSE - TDE 2");
        System.out.println("\nImplementação com Árvore Binária");
    }

    private static void exibirMensagemDespedida() {
        System.out.println("\nObrigado por usar nosso sistema!");
    }
}