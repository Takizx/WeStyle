package Projetofinal;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class Telaprincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Telaprincipal frame = new Telaprincipal();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Telaprincipal() {
        setTitle("WeStyle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 542, 427);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(30, 30, 30, 30));
        setContentPane(contentPane);

        contentPane.setLayout(new MigLayout(
                "wrap 1, align center center",
                "[grow, center]",
                "[]20[]30[]15[]"
        ));

        JLabel lblTitulo = new JLabel("WeStyle");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));
        contentPane.add(lblTitulo);

        JLabel lblSubtitulo = new JLabel("Moda que combina com você!");
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 16));
        contentPane.add(lblSubtitulo);

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setFont(new Font("Arial", Font.BOLD, 14));
        contentPane.add(btnEntrar, "width 200");

        JButton btnCadastrar = new JButton("Cadastrar-se");
        btnCadastrar.setFont(new Font("Arial", Font.BOLD, 14));
        contentPane.add(btnCadastrar, "width 200");
    }
}