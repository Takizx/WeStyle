package Projetofinal;

import java.awt.EventQueue;

public class Main {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Telaprincipal tela = new Telaprincipal();
            tela.setVisible(true);
        });
    }
}