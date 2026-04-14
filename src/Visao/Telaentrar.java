package Visao;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.prefs.Preferences; 

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;

import Controle.UsuarioDAO;
import Modelo.Usuario;
import Modelo.Sessao;

public class Telaentrar extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldEmail;
    private JTextField textFieldSenha;
    
    private Preferences prefs = Preferences.userNodeForPackage(Telaentrar.class);

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
        UIManager.put("OptionPane.okButtonText", "Ok");
        UIManager.put("OptionPane.cancelButtonText", "Cancelar");

        setTitle("WeStyle - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 700);
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(106, 143, 123));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        contentPane.setLayout(new MigLayout("fill, align center center", "[center]", "[center]"));
        setContentPane(contentPane);

        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        card.setLayout(new MigLayout("wrap, insets 40, gap 15", "[grow,fill]", "[][][][][][][][][][]"));
        contentPane.add(card, "w 450!, h 550!");

        JLabel lblTitulo = new JLabel("WeStyle", SwingConstants.CENTER);
        lblTitulo.setForeground(new Color(106, 143, 123));
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        card.add(lblTitulo, "alignx center");

        JLabel lblSub = new JLabel("Bem-vindo de volta!");
        lblSub.setFont(new Font("Arial", Font.BOLD, 17));
        lblSub.setForeground(new Color(106, 143, 123));
        card.add(lblSub, "alignx center");

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setForeground(new Color(106, 143, 132));
        card.add(lblEmail);

        textFieldEmail = new JTextField();
        card.add(textFieldEmail, "height 40!");

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setForeground(new Color(106, 143, 132));
        card.add(lblSenha);

        textFieldSenha = new JTextField();
        card.add(textFieldSenha, "height 40!");

        JCheckBox chkLembrar = new JCheckBox("Lembrar de mim");
        chkLembrar.setForeground(new Color(106, 143, 132));
        chkLembrar.setBackground(Color.WHITE);
        card.add(chkLembrar, "split 2");
        
        String emailSalvo = prefs.get("email", "");
        String senhaSalva = prefs.get("senha", "");
        if (!emailSalvo.isEmpty()) {
            textFieldEmail.setText(emailSalvo);
            textFieldSenha.setText(senhaSalva);
            chkLembrar.setSelected(true);
        }

        JButton btnEsqueceu = new JButton("Esqueceu a senha?");
        btnEsqueceu.setBorder(null);
        btnEsqueceu.setBackground(Color.WHITE);
        btnEsqueceu.setForeground(new Color(106, 143, 123));
        btnEsqueceu.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEsqueceu.setFont(new Font("Arial", Font.PLAIN, 12));
        
        btnEsqueceu.addActionListener(e -> {
            String emailRecuperar = (String) JOptionPane.showInputDialog(this, "Digite seu e-mail cadastrado:", "Recuperar Senha", JOptionPane.PLAIN_MESSAGE, null, null, "");
            
            if (emailRecuperar != null && !emailRecuperar.trim().isEmpty()) {
                int codigoGerado = 1000 + (int)(Math.random() * 9000);
                
                JOptionPane.showMessageDialog(this, "Seu código é: " + codigoGerado, "Recuperar Senha", JOptionPane.INFORMATION_MESSAGE);
                
                String codigo = (String) JOptionPane.showInputDialog(this, "Digite o código enviado:", "Recuperar Senha", JOptionPane.PLAIN_MESSAGE, null, null, "");
                
                if (String.valueOf(codigoGerado).equals(codigo)) {
                    String novaSenha = (String) JOptionPane.showInputDialog(this, "Código aceito! Digite sua nova senha:", "Recuperar Senha", JOptionPane.PLAIN_MESSAGE, null, null, "");
                    
                    if (novaSenha != null && novaSenha.length() >= 4) {
                        UsuarioDAO dao = new UsuarioDAO();
                        if (dao.atualizarSenha(emailRecuperar, novaSenha)) {
                            JOptionPane.showMessageDialog(this, "Senha redefinida com sucesso!");
                        } else {
                            JOptionPane.showMessageDialog(this, "E-mail não encontrado no sistema.");
                        }
                    } else if (novaSenha != null) {
                        JOptionPane.showMessageDialog(this, "A senha deve ter pelo menos 4 caracteres.");
                    }
                } else if (codigo != null) {
                    JOptionPane.showMessageDialog(this, "Código incorreto!");
                }
            }
        });
        card.add(btnEsqueceu, "gapleft push");

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBackground(new Color(106, 143, 123));
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.setFont(new Font("Arial", Font.BOLD, 16));
        btnEntrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
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
                    if (chkLembrar.isSelected()) {
                        prefs.put("email", email);
                        prefs.put("senha", senha);
                    } else {
                        prefs.remove("email");
                        prefs.remove("senha");
                    }

                    Sessao.setUsuario(usuarioEncontrado);
                    JOptionPane.showMessageDialog(null, "Login realizado com sucesso! Bem-vindo, " + usuarioEncontrado.getNome());
                    new TelaEscolha().setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "E-mail ou senha incorretos.", "Erro de Login", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        card.add(btnEntrar, "height 45!, gapy 10");
        
        JButton btnIrCadastro = new JButton("Não tem conta? Cadastre-se");
        btnIrCadastro.setBorder(null);
        btnIrCadastro.setBackground(Color.WHITE);
        btnIrCadastro.setForeground(new Color(106, 143, 123));
        btnIrCadastro.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnIrCadastro.addActionListener(e -> {
            new Telacadastro().setVisible(true);
            dispose();
        });
        card.add(btnIrCadastro, "alignx center");

        JButton btnVoltar = new JButton("Voltar ao início");
        btnVoltar.setBorder(null);
        btnVoltar.setBackground(Color.WHITE);
        btnVoltar.setForeground(new Color(106, 143, 123));
        btnVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVoltar.addActionListener(e -> {
            new Telaprincipal().setVisible(true);
            dispose();
        });
        card.add(btnVoltar, "alignx center, gapy 5");
    }
}