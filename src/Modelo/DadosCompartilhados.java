package Modelo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class DadosCompartilhados {
    public static List<PecaPersonalizada> pecasCriadas = new ArrayList<>();
    public static String usuarioTipo = "Cliente"; 
    public static String enderecoEntrega = "Não informado";

    public static class PecaPersonalizada {
        public String nome;
        public Color cor;
        public String estampa;
        public String preco;

        public PecaPersonalizada(String nome, Color cor, String estampa, String preco) {
            this.nome = nome;
            this.cor = cor;
            this.estampa = estampa;
            this.preco = preco;
        }
    }

    public static void salvarDados() {
    }

    public static void carregarDados() {
    }
}