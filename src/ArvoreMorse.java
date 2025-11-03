import java.util.HashMap;
import java.util.Map;

public class ArvoreMorse {
    public NoMorse raiz;
    public Map<Character, String> tabelaCodificacao;
    private static final Map<Character, String> CODIGOS_MORSE_PADRAO = new HashMap<>();
    
    static {
        CODIGOS_MORSE_PADRAO.put('E', ".");
        CODIGOS_MORSE_PADRAO.put('T', "-");
        CODIGOS_MORSE_PADRAO.put('I', "..");
        CODIGOS_MORSE_PADRAO.put('A', ".-");
        CODIGOS_MORSE_PADRAO.put('N', "-.");
        CODIGOS_MORSE_PADRAO.put('M', "--");
        CODIGOS_MORSE_PADRAO.put('S', "...");
        CODIGOS_MORSE_PADRAO.put('U', "..-");
        CODIGOS_MORSE_PADRAO.put('R', ".-.");
        CODIGOS_MORSE_PADRAO.put('W', ".--");
        CODIGOS_MORSE_PADRAO.put('D', "-..");
        CODIGOS_MORSE_PADRAO.put('K', "-.-");
        CODIGOS_MORSE_PADRAO.put('G', "--.");
        CODIGOS_MORSE_PADRAO.put('O', "---");
        CODIGOS_MORSE_PADRAO.put('H', "....");
        CODIGOS_MORSE_PADRAO.put('V', "...-");
        CODIGOS_MORSE_PADRAO.put('F', "..-.");
        CODIGOS_MORSE_PADRAO.put('L', ".-..");
        CODIGOS_MORSE_PADRAO.put('P', ".--.");
        CODIGOS_MORSE_PADRAO.put('J', ".---");
        CODIGOS_MORSE_PADRAO.put('B', "-...");
        CODIGOS_MORSE_PADRAO.put('X', "-..-");
        CODIGOS_MORSE_PADRAO.put('C', "-.-.");
        CODIGOS_MORSE_PADRAO.put('Y', "-.--");
        CODIGOS_MORSE_PADRAO.put('Z', "--..");
        CODIGOS_MORSE_PADRAO.put('Q', "--.-");
        CODIGOS_MORSE_PADRAO.put('1', ".----");
        CODIGOS_MORSE_PADRAO.put('2', "..---");
        CODIGOS_MORSE_PADRAO.put('3', "...--");
        CODIGOS_MORSE_PADRAO.put('4', "....-");
        CODIGOS_MORSE_PADRAO.put('5', ".....");
        CODIGOS_MORSE_PADRAO.put('6', "-....");
        CODIGOS_MORSE_PADRAO.put('7', "--...");
        CODIGOS_MORSE_PADRAO.put('8', "---..");
        CODIGOS_MORSE_PADRAO.put('9', "----.");
        CODIGOS_MORSE_PADRAO.put('0', "-----");
    }

    public ArvoreMorse() {
        this.raiz = new NoMorse('\0');
        this.tabelaCodificacao = new HashMap<>();
    }

    private void inserir(char caractere, String codigoMorse) {
        if (tabelaCodificacao.containsKey(caractere)) {
            return;
        }
        
        tabelaCodificacao.put(caractere, codigoMorse);
        NoMorse atual = raiz;

        for (int i = 0; i < codigoMorse.length(); i++) {
            char simbolo = codigoMorse.charAt(i);

            if (simbolo == '.') {
                if (atual.esquerda == null) {
                    char proxCaractere = (i == codigoMorse.length() - 1) ? caractere : '\0';
                    atual.esquerda = new NoMorse(proxCaractere);
                }
                atual = atual.esquerda;
            } else if (simbolo == '-') {
                if (atual.direita == null) {
                    char proxCaractere = (i == codigoMorse.length() - 1) ? caractere : '\0';
                    atual.direita = new NoMorse(proxCaractere);
                }
                atual = atual.direita;
            }
        }
        if (atual.caractere == '\0') {
            atual.caractere = caractere;
        }
        System.out.println("✓ Caractere '" + caractere + "' adicionado à árvore!");
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
                    if (!letra.matches("[.-]+")) {
                        System.out.println("Caractere inválido ignorado: " + letra);
                        continue;
                    }

                    char caractereDecodificado = decodificarCaractere(letra);
                    if (caractereDecodificado != '\0') {
                        resultado.append(caractereDecodificado);
                        
                        char upper = Character.toUpperCase(caractereDecodificado);
                        if (!tabelaCodificacao.containsKey(upper)) {
                            inserir(upper, letra);
                        }
                    } else {
                        char encontrado = buscarNaTabelaPadrao(letra);
                        if (encontrado != '\0') {
                            resultado.append(encontrado);
                            inserir(encontrado, letra);
                        } else {
                            System.out.println("Código Morse desconhecido: " + letra);
                            resultado.append('?');
                        }
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
            } else {
                String codigoMorse = tabelaCodificacao.get(c);
                
                if (codigoMorse == null) {
                    codigoMorse = CODIGOS_MORSE_PADRAO.get(c);
                    if (codigoMorse != null) {
                        inserir(c, codigoMorse);
                    } else {
                        System.out.println("Caractere '" + c + "' não possui código Morse conhecido.");
                        continue;
                    }
                }
                
                resultado.append(codigoMorse);
                resultado.append(" ");
            }
        }
        return resultado.toString().trim();
    }

    private char buscarNaTabelaPadrao(String codigoMorse) {
        for (Map.Entry<Character, String> entry : CODIGOS_MORSE_PADRAO.entrySet()) {
            if (entry.getValue().equals(codigoMorse)) {
                return entry.getKey();
            }
        }
        return '\0';
    }

    public int getTamanhoArvore() {
        return tabelaCodificacao.size();
    }
}