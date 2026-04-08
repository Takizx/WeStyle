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


import Modelo.Usuario;
import Controle.UsuarioDAO;

public class Telacadastro extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textFieldNome;
    private JTextField textFieldEmail;
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

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 700);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(106, 143, 123));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));

        contentPane.setLayout(new MigLayout(
                "align center center",
                "",
                ""));

        setContentPane(contentPane);

        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));

        card.setLayout(new MigLayout("wrap, insets 40, gap 15", "[grow,fill]", "[][][][][][][][][][]"));

        contentPane.add(card, "w 450!, h 620!");

        JLabel lblTitulo = new JLabel("WeStyle", JLabel.CENTER);
        lblTitulo.setForeground(new Color(106, 143, 123));
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        card.add(lblTitulo, "alignx center");

        JLabel lblSub = new JLabel("Criar conta");
        lblSub.setFont(new Font("Arial", Font.BOLD, 17));
        lblSub.setForeground(new Color(106, 143, 123));
        card.add(lblSub, "alignx center");

        JLabel lblNome = new JLabel("Nome");
        lblNome.setForeground(new Color(106, 143, 132));
        card.add(lblNome);

        textFieldNome = new JTextField();
        card.add(textFieldNome, "height 40!");

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

        JLabel lblConfirmar = new JLabel("Confirmar senha");
        lblConfirmar.setForeground(new Color(106, 143, 132));
        card.add(lblConfirmar);

        textFieldConfirmarSenha = new JTextField();
        card.add(textFieldConfirmarSenha, "height 40!");

        JCheckBox chkTermos = new JCheckBox("Aceito os termos de uso");
        chkTermos.setForeground(new Color(106, 143, 132));
        chkTermos.setBackground(Color.WHITE);
        card.add(chkTermos);

        JButton btnCadastrar = new JButton("Cadastrar");
        
        
        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                String nome = textFieldNome.getText();
                String email = textFieldEmail.getText();
                String senha = textFieldSenha.getText();
                String confirmar = textFieldConfirmarSenha.getText();

              
                if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
                    return;
                }

                if (!senha.equals(confirmar)) {
                    JOptionPane.showMessageDialog(null, "As senhas não coincidem!");
                    return;
                }
                
                if (!chkTermos.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Você precisa aceitar os termos de uso.");
                    return;
                }

                
                Usuario novoUsuario = new Usuario();
                novoUsuario.setNome(nome);
                novoUsuario.setEmail(email);
                novoUsuario.setSenha(senha);

                
                UsuarioDAO dao = new UsuarioDAO();
                if (dao.cadastrarUsuario(novoUsuario)) {
                    JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
                    
                    
                    textFieldNome.setText("");
                    textFieldEmail.setText("");
                    textFieldSenha.setText("");
                    textFieldConfirmarSenha.setText("");
                    chkTermos.setSelected(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar no banco de dados. Verifique a conexão.");
                }
            }
        });
       

        btnCadastrar.setBackground(new Color(106, 143, 123));
        btnCadastrar.setForeground(Color.WHITE);
        card.add(btnCadastrar, "height 45!,gapy 10");

        JLabel lblLogin = new JLabel("Já possui conta? Entrar");
        lblLogin.setForeground(new Color(106, 143, 123));
        card.add(lblLogin, "alignx center");
    }
}