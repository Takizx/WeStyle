package Projetofinal;

import java.awt.EventQueue; 
import java.awt.Font;      
import java.awt.Color;      

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout; // layout para organizar os componentes

public class Telaprincipal extends JFrame {

    private static final long serialVersionUID = 1L;

    // painel principal onde todos os componentes ficam
    private JPanel contentPane;

    public static void main(String[] args) {
       
        EventQueue.invokeLater(() -> {
            try {
                Telaprincipal frame = new Telaprincipal();
                frame.setVisible(true); // torna a janela visivel
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Telaprincipal() {
        
        setTitle("WeStyle");

       
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // define posição e tamanho da janela
        setBounds(100, 100, 542, 427);

        contentPane = new JPanel();

        // margens
        contentPane.setBorder(new EmptyBorder(30, 30, 30, 30));

        //a cor de fundo
        contentPane.setBackground(new Color(106, 143, 123));

        setContentPane(contentPane);
        
        // wrap 1 → um componente por linha
        // align center center → centraliza tudo horizontal e verticalmente
        contentPane.setLayout(new MigLayout(
                "wrap 1, align center center",
                "[grow, center]",
                "[]20[]30[]15[]"
        ));

        JLabel lblTitulo = new JLabel("WeStyle");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 36)); // fonte grande e em negrito
        lblTitulo.setForeground(Color.WHITE); // cor do texto branca
        contentPane.add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("Moda que combina com você!");
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 16));
        lblSubtitulo.setForeground(Color.WHITE);
        contentPane.add(lblSubtitulo);

        // botão de entrar
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnEntrar.setBackground(Color.WHITE); // fundo branco
        btnEntrar.setForeground(new Color(106, 143, 123)); // texto na cor do tema
        btnEntrar.setOpaque(true); // permite exibir a cor de fundo
        btnEntrar.setBorderPainted(false); 
        contentPane.add(btnEntrar, "width 200"); // largura 

        // botao de cadastrar
        JButton btnCadastrar = new JButton("Cadastrar-se");
        btnCadastrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCadastrar.setBackground(Color.WHITE);
        btnCadastrar.setForeground(new Color(106, 143, 123));
        btnCadastrar.setOpaque(true);
        btnCadastrar.setBorderPainted(false);
        contentPane.add(btnCadastrar, "width 200");
    }
}