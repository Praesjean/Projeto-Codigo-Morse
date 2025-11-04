package scr.codigomorse;

public class NoMorse {
    public char caractere;
    public NoMorse esquerda;
    public NoMorse direita;

    public NoMorse(char caractere) {
        this.caractere = caractere;
        this.esquerda = null;
        this.direita = null;
    }
}