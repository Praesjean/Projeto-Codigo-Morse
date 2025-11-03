import java.util.HashMap;
import java.util.Map;

public class ArvoreMorse {
    public NoMorse raiz;
    public Map<Character, String> tabelaCodificacao;

    public ArvoreMorse() {
        this.raiz = new NoMorse('\0');
        this.tabelaCodificacao = new HashMap<>();
        construirArvore();
    }

    private void construirArvore() {
        inserir('E', ".");
        inserir('T', "-");
        inserir('I', "..");
        inserir('A', ".-");
        inserir('N', "-.");
        inserir('M', "--");
        inserir('S', "...");
        inserir('U', "..-");
        inserir('R', ".-.");
        inserir('W', ".--");
        inserir('D', "-..");
        inserir('K', "-.-");
        inserir('G', "--.");
        inserir('O', "---");
        inserir('H', "....");
        inserir('V', "...-");
        inserir('F', "..-.");
        inserir('L', ".-..");
        inserir('P', ".--.");
        inserir('J', ".---");
        inserir('B', "-...");
        inserir('X', "-..-");
        inserir('C', "-.-.");
        inserir('Y', "-.--");
        inserir('Z', "--..");
        inserir('Q', "--.-");

        inserir('1', ".----");
        inserir('2', "..---");
        inserir('3', "...--");
        inserir('4', "....-");
        inserir('5', ".....");
        inserir('6', "-....");
        inserir('7', "--...");
        inserir('8', "---..");
        inserir('9', "----.");
        inserir('0', "-----");
    }

    public void inserir(char caractere, String codigoMorse) {
        tabelaCodificacao.put(caractere, codigoMorse);
        NoMorse atual = raiz;

        for (int i = 0; i < codigoMorse.length(); i++) {
            char simbolo = codigoMorse.charAt(i);

            if (simbolo == '.') {
                if (atual.esquerda == null) {
                    atual.esquerda = new NoMorse('\0');
                }
                atual = atual.esquerda;
            } else if (simbolo == '-') {
                if (atual.direita == null) {
                    atual.direita = new NoMorse('\0');
                }
                atual = atual.direita;
            }
        }
        atual.caractere = caractere;
    }

    public String decodificar(String codigoMorse) {
        if (codigoMorse == null || codigoMorse.trim().isEmpty()) {
            return "";
        }

        StringBuilder resultado = new StringBuilder();
        String[] palavras = codigoMorse.trim().split("   ");

        for (String palavra : palavras) {
            String[] letras = palavra.split(" ");
            
            for (String letra : letras) {
                if (!letra.isEmpty()) {
                    char caractereDecodificado = decodificarCaractere(letra);
                    if (caractereDecodificado != '\0') {
                        resultado.append(caractereDecodificado);
                    } else {
                        resultado.append('?');
                    }
                }
            }
            resultado.append(' ');
        }
        return resultado.toString().trim();
    }

    private char decodificarCaractere(String codigoMorse) {
        NoMorse atual = raiz;

        for (int i = 0; i < codigoMorse.length(); i++) {
            char simbolo = codigoMorse.charAt(i);

            if (simbolo == '.') {
                atual = atual.esquerda;
            } else if (simbolo == '-') {
                atual = atual.direita;
            }

            if (atual == null) {
                return '\0';
            }
        }
        return atual.caractere;
    }

    public String codificar(String texto) {
        if (texto == null || texto.isEmpty()) {
            return "";
        }

        StringBuilder resultado = new StringBuilder();
        texto = texto.toUpperCase();

        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            
            if (c == ' ') {
                resultado.append("  ");
            } else if (tabelaCodificacao.containsKey(c)) {
                resultado.append(tabelaCodificacao.get(c));
                resultado.append(" ");
            }
        }
        return resultado.toString().trim();
    }
}