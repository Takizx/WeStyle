package Visao;

import java.awt.EventQueue;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                JanelaPrincipal janela = new JanelaPrincipal();
                janela.setVisible(true);
                
                JanelaPrincipal.mudarTela("principal"); 
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}