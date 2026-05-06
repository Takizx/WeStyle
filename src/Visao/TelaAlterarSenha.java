package Visao;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import net.miginfocom.swing.MigLayout;

public class TelaAlterarSenha extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textSenhaAtual;
    private JTextField textNovaSenha;
    private JTextField textConfirmarSenha;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaAlterarSenha frame = new TelaAlterarSenha();
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaAlterarSenha() {
        setTitle("Alterar Senha");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 700);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(106,143,123));
        contentPane.setBorder(new EmptyBorder(20,20,20,20));

        contentPane.setLayout(new MigLayout("align center center", "", ""));
        setContentPane(contentPane);

        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(new LineBorder(Color.LIGHT_GRAY,1,true));

        card.setLayout(new MigLayout("wrap, insets 40, gap 15", "[grow,fill]", "[][][][][][][][]"));

        contentPane.add(card,"w 450!, h 550!");

        JLabel lblTitulo = new JLabel("Alterar Senha", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial",Font.BOLD,26));
        lblTitulo.setForeground(new Color(106,143,123));
        card.add(lblTitulo,"alignx center");

        JLabel lblAtual = new JLabel("Senha atual");
        lblAtual.setForeground(new Color(106, 143, 132));
        card.add(lblAtual);

        textSenhaAtual = new JTextField();
        card.add(textSenhaAtual,"height 40!");

        JLabel lblNova = new JLabel("Nova senha");
        lblNova.setForeground(new Color(106, 143, 132));
        card.add(lblNova);

        textNovaSenha = new JTextField();
        card.add(textNovaSenha,"height 40!");

        JLabel lblConfirmar = new JLabel("Confirmar nova senha");
        lblConfirmar.setForeground(new Color(106, 143, 132));
        card.add(lblConfirmar);

        textConfirmarSenha = new JTextField();
        card.add(textConfirmarSenha,"height 40!");

        JButton btnSalvar = new JButton("Salvar nova senha");
        btnSalvar.setBackground(new Color(106,143,123));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.addActionListener(e -> {
            String atual = textSenhaAtual.getText();
            String nova = textNovaSenha.getText();
            String confirmar = textConfirmarSenha.getText();

            if(atual.isEmpty() || nova.isEmpty() || confirmar.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                return;
            }

            if(!nova.equals(confirmar)) {
                JOptionPane.showMessageDialog(null, "As senhas não coincidem!");
                return;
            }

            JOptionPane.showMessageDialog(null, "Senha alterada com sucesso!");
            new TelaPerfil().setVisible(true);
            dispose();
        });
        card.add(btnSalvar,"height 45!,gapy 10");

        JButton btnVoltar = new JButton("Voltar ao Perfil");
        btnVoltar.setBackground(Color.WHITE);
        btnVoltar.setForeground(new Color(106,143,123));
        btnVoltar.addActionListener(e -> {
            new TelaPerfil().setVisible(true);
            dispose();
        });
        card.add(btnVoltar,"height 45!");
    }
}