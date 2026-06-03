package Visao;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import net.miginfocom.swing.MigLayout;
import Controle.UsuarioDAO;

public class TelaRecuperarSenha extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtEmail;
    private JTextField txtCodigo;
    private JTextField txtNovaSenha;
    
    private int codigoGerado = 0;
    private boolean codigoValidado = false;

    Color verdeWeStyle = new Color(106, 143, 123);

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaRecuperarSenha frame = new TelaRecuperarSenha();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaRecuperarSenha() {
        setTitle("WeStyle - Recuperar Senha");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 700);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        contentPane = new JPanel();
        contentPane.setBackground(verdeWeStyle);
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(new MigLayout("fill, align center center", "[center]", "[center]"));
        setContentPane(contentPane);

        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        card.setLayout(new MigLayout("wrap, insets 35 40 35 40, gap 12", "[grow,fill]", "[][][][][][][][][][]push[]"));
        contentPane.add(card, "w 450!, h 620!");

        JLabel lblTitulo = new JLabel("WeStyle", SwingConstants.CENTER);
        lblTitulo.setForeground(verdeWeStyle);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        card.add(lblTitulo, "alignx center");

        JLabel lblSub = new JLabel("Recuperação de Senha", SwingConstants.CENTER);
        lblSub.setFont(new Font("Arial", Font.BOLD, 16));
        lblSub.setForeground(verdeWeStyle);
        card.add(lblSub, "alignx center, gapbottom 10");

        JLabel lblEmail = new JLabel("E-mail Cadastrado:");
        lblEmail.setForeground(new Color(106, 143, 132));
        card.add(lblEmail);

        JPanel pnlEmailEnvio = new JPanel(new MigLayout("insets 0, fillx", "[grow][130!]"));
        pnlEmailEnvio.setOpaque(false);
        
        txtEmail = new JTextField();
        pnlEmailEnvio.add(txtEmail, "height 40!, growx");
        
        JButton btnEnviarCodigo = new JButton("Gerar Código");
        btnEnviarCodigo.setBackground(verdeWeStyle);
        btnEnviarCodigo.setForeground(Color.WHITE);
        btnEnviarCodigo.setFont(new Font("Arial", Font.BOLD, 12));
        btnEnviarCodigo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEnviarCodigo.addActionListener(e -> {
            String email = txtEmail.getText().trim();
            if (email.isEmpty()) {
                new TelaMensagem("Por favor, preencha o campo de e-mail!", "erro");
                return;
            }
            codigoGerado = 1000 + (int)(Math.random() * 9000);
            new TelaMensagem("Seu código de verificação é: " + codigoGerado, "sucesso");
            codigoValidado = false;
        });
        pnlEmailEnvio.add(btnEnviarCodigo, "height 40!");
        card.add(pnlEmailEnvio);

        JLabel lblCodigo = new JLabel("Código de Verificação:");
        lblCodigo.setForeground(new Color(106, 143, 132));
        card.add(lblCodigo);

        txtCodigo = new JTextField();
        card.add(txtCodigo, "height 40!");

        JLabel lblNovaSenha = new JLabel("Nova Senha:");
        lblNovaSenha.setForeground(new Color(106, 143, 132));
        card.add(lblNovaSenha);

        txtNovaSenha = new JTextField();
        card.add(txtNovaSenha, "height 40!, gapbottom 10");

        JButton btnFinalizar = new JButton("Redefinir Senha");
        btnFinalizar.setBackground(verdeWeStyle);
        btnFinalizar.setForeground(Color.WHITE);
        btnFinalizar.setFont(new Font("Arial", Font.BOLD, 16));
        btnFinalizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnFinalizar.addActionListener(e -> {
            String emailInformado = txtEmail.getText().trim();
            String codDigitado = txtCodigo.getText().trim();
            String novaSenha = txtNovaSenha.getText().trim();
            
            if (emailInformado.isEmpty()) {
                new TelaMensagem("Por favor, informe o seu e-mail cadastrado.", "erro");
                return;
            }
            if (codigoGerado == 0) {
                new TelaMensagem("Por favor, clique em 'Gerar Código' primeiro.", "erro");
                return;
            }
            if (codDigitado.isEmpty()) {
                new TelaMensagem("Por favor, digite o código de verificação recebido.", "erro");
                return;
            }
            if (!String.valueOf(codigoGerado).equals(codDigitado)) {
                new TelaMensagem("Código de verificação incorreto!", "erro");
                return;
            }
            if (novaSenha.isEmpty()) {
                new TelaMensagem("Por favor, digite a nova senha.", "erro");
                return;
            }
            if (novaSenha.length() < 4) {
                new TelaMensagem("A senha deve ter pelo menos 4 caracteres.", "erro");
                return;
            }
            
            UsuarioDAO dao = new UsuarioDAO();
            if (dao.atualizarSenha(emailInformado, novaSenha)) {
                new TelaMensagem("Senha redefinida com sucesso!", "sucesso");
                new Telaentrar().setVisible(true);
                dispose();
            } else {
                new TelaMensagem("Erro: E-mail não encontrado no sistema.", "erro");
            }
        });
        card.add(btnFinalizar, "height 45!");

        JButton btnVoltar = new JButton("Voltar ao Login");
        btnVoltar.setBorder(null);
        btnVoltar.setBackground(Color.WHITE);
        btnVoltar.setForeground(verdeWeStyle);
        btnVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVoltar.addActionListener(e -> {
            new Telaentrar().setVisible(true);
            dispose();
        });
        card.add(btnVoltar, "alignx center, gapy 5");
    }
}