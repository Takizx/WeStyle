package Visao;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;
import Modelo.Usuario;
import Controle.UsuarioDAO;

public class Telacadastro extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldNome;
    private JTextField textFieldEmail;
    private JTextField textFieldEndereco;
    private JTextField textFieldSenha;
    private JTextField textFieldConfirmarSenha;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Telacadastro frame = new Telacadastro();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Telacadastro() {
        setTitle("WeStyle - Criar Conta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 750);
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(106, 143, 123));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        contentPane.setLayout(new MigLayout("fill, align center center", "[center]", "[center]"));
        setContentPane(contentPane);

        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        card.setLayout(new MigLayout("wrap, insets 40, gap 12", "[grow,fill]", "[]"));
        contentPane.add(card, "w 450!, h 720!");

        JLabel lblTitulo = new JLabel("WeStyle", SwingConstants.CENTER);
        lblTitulo.setForeground(new Color(106, 143, 123));
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        card.add(lblTitulo, "alignx center");

        JLabel lblSub = new JLabel("Criar conta");
        lblSub.setFont(new Font("Arial", Font.BOLD, 17));
        lblSub.setForeground(new Color(106, 143, 123));
        card.add(lblSub, "alignx center, gapy 5");

        JLabel lblNome = new JLabel("Nome");
        lblNome.setForeground(new Color(106, 143, 132));
        card.add(lblNome, "gapy 5");

        textFieldNome = new JTextField();
        card.add(textFieldNome, "height 35!");

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setForeground(new Color(106, 143, 132));
        card.add(lblEmail);

        textFieldEmail = new JTextField();
        card.add(textFieldEmail, "height 35!");

        JLabel lblEndereco = new JLabel("Endereço");
        lblEndereco.setForeground(new Color(106, 143, 132));
        card.add(lblEndereco);

        textFieldEndereco = new JTextField();
        card.add(textFieldEndereco, "height 35!");

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setForeground(new Color(106, 143, 132));
        card.add(lblSenha);

        textFieldSenha = new JPasswordField(20);
        card.add(textFieldSenha, "height 35!");

        JLabel lblConfirmar = new JLabel("Confirmar senha");
        lblConfirmar.setForeground(new Color(106, 143, 132));
        card.add(lblConfirmar);

        textFieldConfirmarSenha = new JPasswordField(20);
        card.add(textFieldConfirmarSenha, "height 35!");

        JCheckBox chkTermos = new JCheckBox("Aceito os termos de uso");
        chkTermos.setForeground(new Color(106, 143, 132));
        chkTermos.setBackground(Color.WHITE);
        card.add(chkTermos, "gapy 5, gapbottom 10");

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBackground(new Color(106, 143, 123));
        btnCadastrar.setForeground(Color.WHITE);
        btnCadastrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = textFieldNome.getText();
                String email = textFieldEmail.getText();
                String endereco = textFieldEndereco.getText();
                String senha = textFieldSenha.getText();
                String confirmar = textFieldConfirmarSenha.getText();

                if (nome.isEmpty() || email.isEmpty() || endereco.isEmpty() || senha.isEmpty()) {
                    new TelaMensagem("Por favor, preencha todos os campos.", "erro");
                    return;
                }

                if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                    new TelaMensagem("Formato de e-mail inválido.", "erro");
                    return;
                }

                if (!senha.equals(confirmar)) {
                    new TelaMensagem("As senhas não coincidem!", "erro");
                    return;
                }
                
                if (!chkTermos.isSelected()) {
                    new TelaMensagem("Você precisa aceitar os termos de uso.", "erro");
                    return;
                }

                Usuario novoUsuario = new Usuario();
                novoUsuario.setNome(nome);
                novoUsuario.setEmail(email);
                novoUsuario.setEndereco(endereco);
                novoUsuario.setSenha(senha);

                UsuarioDAO dao = new UsuarioDAO();
                if (dao.cadastrarUsuario(novoUsuario)) {
                    new TelaMensagem("Usuário cadastrado com sucesso!", "sucesso");
                    new Telaentrar().setVisible(true);
                    dispose();
                } else {
                    new TelaMensagem("Erro ao cadastrar no banco.", "erro");
                }
            }
        });
        card.add(btnCadastrar, "height 45!, gapy 5");

        JButton btnIrLogin = new JButton("Já possui conta? Entre aqui");
        btnIrLogin.setBorder(null);
        btnIrLogin.setBackground(Color.WHITE);
        btnIrLogin.setForeground(new Color(106, 143, 123));
        btnIrLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnIrLogin.addActionListener(e -> {
            new Telaentrar().setVisible(true);
            dispose();
        });
        card.add(btnIrLogin, "alignx center, gapy 5");

        JButton btnVoltar = new JButton("Voltar ao início");
        btnVoltar.setBorder(null);
        btnVoltar.setBackground(Color.WHITE);
        btnVoltar.setForeground(new Color(106, 143, 123));
        btnVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVoltar.addActionListener(e -> {
            new Telaprincipal().setVisible(true);
            dispose();
        });
        card.add(btnVoltar, "alignx center");
    }
}