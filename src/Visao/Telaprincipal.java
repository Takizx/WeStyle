package Visao;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

public class Telaprincipal extends JPanel {

    private static final long serialVersionUID = 1L;

    public Telaprincipal() {
        this.setBorder(new EmptyBorder(30, 30, 30, 30));
        this.setBackground(new Color(106, 143, 123));
        this.setLayout(new MigLayout("fill, align center center", "[grow, center]", "[grow, center]"));

        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        card.setLayout(new MigLayout("wrap 1, insets 40, gap 20, align center center", "[center]", "[]20[]30[]30[]20[][]"));
        
        this.add(card, "center, w 450!, h 500!");

        JLabel lblTitulo = new JLabel("WeStyle");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitulo.setForeground(new Color(106, 143, 123));
        card.add(lblTitulo, "gapy 10");

        JLabel lblSubtitulo = new JLabel("Moda que combina com você!");
        lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 16));
        lblSubtitulo.setForeground(new Color(106, 143, 123));
        card.add(lblSubtitulo, "gapy 10");

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnEntrar.setBackground(new Color(106, 143, 123));
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.addActionListener(e -> {
            JanelaPrincipal.mudarTela("entrar");
        });
        card.add(btnEntrar, "width 220!, height 45!, gapy 20");

        JButton btnCadastrar = new JButton("Cadastrar-se");
        btnCadastrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCadastrar.setBackground(new Color(106, 143, 123));
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.setBorder(new LineBorder(new Color(106, 143, 123), 1, true));
        btnCadastrar.addActionListener(e -> {
            JanelaPrincipal.mudarTela("cadastro");
        });
        card.add(btnCadastrar, "width 220!, height 45!, gapy 10");
    }
}