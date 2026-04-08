package Visao;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

import net.miginfocom.swing.MigLayout;

import Controle.UsuarioDAO;
import Modelo.Usuario;
import Modelo.Sessao;

public class Telaentrar extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldEmail;
    private JTextField textFieldSenha;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Telaentrar frame = new Telaentrar();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Telaentrar() {
        setTitle("WeStyle - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 700);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(106, 143, 123));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(new MigLayout("align center center", "", ""));
        setContentPane(contentPane);

        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        card.setLayout(new MigLayout("wrap, insets 40, gap 15", "[grow,fill]", "[][][][][][][][]"));
        contentPane.add(card, "w 450!, h 500!");

        JLabel lblTitulo = new JLabel("WeStyle", JLabel.CENTER);
        lblTitulo.setForeground(new Color(106, 143, 123));
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        card.add(lblTitulo, "cell 0 0,alignx center");

        JLabel lblSub = new JLabel("Bem-vindo de volta!");
        lblSub.setFont(new Font("Arial", Font.BOLD, 17));
        lblSub.setForeground(new Color(106, 143, 123));
        card.add(lblSub, "cell 0 1,alignx center");

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setForeground(new Color(106, 143, 132));
        card.add(lblEmail, "cell 0 2");

        textFieldEmail = new JTextField();
        card.add(textFieldEmail, "cell 0 3,height 40!");

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setForeground(new Color(106, 143, 132));
        card.add(lblSenha, "cell 0 4");

        textFieldSenha = new JTextField();
        card.add(textFieldSenha, "cell 0 5,height 40!");

        JCheckBox chkLembrar = new JCheckBox("Lembrar de mim");
        chkLembrar.setForeground(new Color(106, 143, 132));
        chkLembrar.setBackground(Color.WHITE);
        card.add(chkLembrar, "cell 0 6, split 2");

        JLabel lblEsqueceu = new JLabel("Esqueceu a senha?");
        lblEsqueceu.setForeground(new Color(106, 143, 123));
        card.add(lblEsqueceu, "gapleft push");

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBackground(new Color(106, 143, 123));
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setFont(new Font("Arial", Font.BOLD, 16));
        
        btnEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = textFieldEmail.getText();
                String senha = textFieldSenha.getText();
                
                if (email.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos!");
                    return;
                }
                
                UsuarioDAO dao = new UsuarioDAO();
                Usuario usuarioEncontrado = dao.validarLogin(email, senha);
                
                if (usuarioEncontrado != null) {
                
                    Sessao.setUsuario(usuarioEncontrado);
                    
                    JOptionPane.showMessageDialog(null, "Login realizado com sucesso! Bem-vindo, " + usuarioEncontrado.getNome());
                    
                   
                    new TelaEscolha().setVisible(true);
                    
                    
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "E-mail ou senha incorretos.", "Erro de Login", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        card.add(btnEntrar, "cell 0 7,height 45!,gapy 10");
        
       
        JButton btnIrCadastro = new JButton("Não tem conta? Cadastre-se");
        btnIrCadastro.setBorder(null);
        btnIrCadastro.setBackground(Color.WHITE);
        btnIrCadastro.setForeground(new Color(106, 143, 123));
        btnIrCadastro.addActionListener(e -> {
            new Telacadastro().setVisible(true);
            dispose();
        });
        card.add(btnIrCadastro, "cell 0 8, alignx center");
    }
}