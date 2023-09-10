import packBanco.TelaInicial;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            TelaInicial.menuDeOpcoes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}