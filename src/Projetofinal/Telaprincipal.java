package Projetofinal;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
    setBounds(100, 100, 1000, 706);

    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(30, 30, 30, 30));
    contentPane.setBackground(new Color(106, 143, 123));

    contentPane.setLayout(new MigLayout(
            "align center center",
            "",
            ""
    ));

    setContentPane(contentPane);

    JPanel card = new JPanel();
    card.setBackground(Color.WHITE);
    card.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));

    card.setLayout(new MigLayout("wrap 1, insets 40, gap 20", "[grow,center]", "[]20[]30[]30[]20[][][][][][]"));

    contentPane.add(card, "w 450!, h 500!");

    JButton btnEntrar = new JButton("Entrar");
    btnEntrar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {

            Telaentrar tela = new Telaentrar();
            tela.setVisible(true);
            dispose();

        }
    });

    JLabel lblTitulo = new JLabel("WeStyle");
    lblTitulo.setFont(new Font("Arial", Font.BOLD, 36));
    lblTitulo.setForeground(new Color(106, 143, 123));
    card.add(lblTitulo, "cell 0 2");

    JLabel lblSubtitulo = new JLabel("Moda que combina com você!");
    lblSubtitulo.setFont(new Font("Arial", Font.PLAIN, 16));
    lblSubtitulo.setForeground(new Color(106, 143, 123));
    card.add(lblSubtitulo, "cell 0 4");

    btnEntrar.setFont(new Font("Arial", Font.BOLD, 14));
    btnEntrar.setBackground(new Color(106, 143, 123));
    btnEntrar.setForeground(Color.WHITE);
    card.add(btnEntrar, "cell 0 6,width 220,height 45");

    JButton btnCadastrar = new JButton("Cadastrar-se");
    btnCadastrar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {

            Telacadastro tela = new Telacadastro();
            tela.setVisible(true);
            dispose();

        }
    });

    btnCadastrar.setFont(new Font("Arial", Font.BOLD, 14));
    btnCadastrar.setBackground(Color.WHITE);
    btnCadastrar.setForeground(new Color(106, 143, 123));
    btnCadastrar.setBorder(new LineBorder(new Color(106, 143, 123), 1, true));
    card.add(btnCadastrar, "cell 0 8,width 220,height 45");

    EventQueue.invokeLater(() -> contentPane.requestFocusInWindow());
}
}
