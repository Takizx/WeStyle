package Visao;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

public class TelaPerfil extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNome;
    private JTextField txtEmail;
    private JTextField txtTelefone;
    private JComboBox<String> comboEnderecos; 

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaPerfil frame = new TelaPerfil();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaPerfil() {

        setTitle("Perfil - WeStyle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 750);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(106, 143, 123));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);

        contentPane.setLayout(new MigLayout(
                "wrap, align center",
                "[grow]",
                "[]push[]push"
        ));

        JPanel menu = new JPanel();
        menu.setBackground(new Color(106, 143, 123));
        menu.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        menu.setLayout(new MigLayout("", "[][][][][][][][][][][][]push[]push[][]", "[]"));

        contentPane.add(menu, "growx");

        JLabel logo = new JLabel("WeStyle");
        logo.setFont(new Font("Arial", Font.BOLD, 30));
        logo.setForeground(new Color(255, 255, 255));
        menu.add(logo, "cell 0 0");

        JButton btnInicio = new JButton("Inicio");
        btnInicio.setFont(new Font("Tahoma", Font.PLAIN, 25));
        btnInicio.setForeground(new Color(255, 255, 255));
        btnInicio.setBackground(new Color(106, 143, 123));

        JLabel espacamento = new JLabel("                                                                                                                        ");
        menu.add(espacamento, "cell 7 0");
        menu.add(btnInicio, "cell 8 0");

        JButton btnCarrinho = new JButton("Carrinho");
        btnCarrinho.setFont(new Font("Tahoma", Font.PLAIN, 25));
        btnCarrinho.setForeground(new Color(255, 255, 255));
        btnCarrinho.setBackground(new Color(106, 143, 123));
        menu.add(btnCarrinho, "cell 9 0");

        JButton btnCriacoes = new JButton("Minhas Criações");
        btnCriacoes.setFont(new Font("Tahoma", Font.PLAIN, 25));
        btnCriacoes.setForeground(new Color(255, 255, 255));
        btnCriacoes.setBackground(new Color(106, 143, 123));
        menu.add(btnCriacoes, "cell 10 0");

        JButton btnPersonalizar = new JButton("Personalizar");
        btnPersonalizar.setFont(new Font("Tahoma", Font.PLAIN, 25));
        btnPersonalizar.setBackground(new Color(106, 143, 123));
        btnPersonalizar.setForeground(new Color(255, 255, 255));
        menu.add(btnPersonalizar, "cell 11 0");

        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));

        card.setLayout(new MigLayout(
                "wrap 2, insets 40, gap 20",
                "[grow][grow]",
                "[][][][][][][]"
        ));

        contentPane.add(card, "align center, w 550!, h 550!");

        JLabel lblTitulo = new JLabel("Perfil do Usuário");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitulo.setForeground(new Color(106, 143, 123));
        card.add(lblTitulo, "span, align center");

        JLabel labelNome = new JLabel("Nome Completo");
        labelNome.setForeground(new Color(106, 143, 132));
        card.add(labelNome);
        
        JLabel labelEmail = new JLabel("E-mail");
        labelEmail.setForeground(new Color(106, 143, 132));
        card.add(labelEmail);

        txtNome = new JTextField();
        card.add(txtNome, "growx,h 35!");

        txtEmail = new JTextField();
        card.add(txtEmail, "growx,h 35!");

        JLabel labelTelefone = new JLabel("Telefone");
        labelTelefone.setForeground(new Color(106, 143, 132));
        card.add(labelTelefone);
        
        JLabel labelEndereco = new JLabel("Endereço de Entrega");
        labelEndereco.setForeground(new Color(106, 143, 132));
        card.add(labelEndereco);

        txtTelefone = new JTextField();
        card.add(txtTelefone, "growx,h 35!");

        comboEnderecos = new JComboBox<>();
        comboEnderecos.setBackground(Color.WHITE);
        card.add(comboEnderecos, "growx,h 35!");

        JButton btnNovoEndereco = new JButton("Adicionar Novo Endereço");
        btnNovoEndereco.setBackground(new Color(106, 143, 123));
        btnNovoEndereco.setForeground(Color.WHITE);
        btnNovoEndereco.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String novoEndereco = (String) JOptionPane.showInputDialog(
                        null, 
                        "Digite o novo endereço completo:", 
                        "Adicionar Endereço", 
                        JOptionPane.PLAIN_MESSAGE, 
                        null, 
                        null, 
                        ""
                );
                
                if (novoEndereco != null && !novoEndereco.trim().isEmpty()) {
                    comboEnderecos.addItem(novoEndereco);
                    comboEnderecos.setSelectedItem(novoEndereco);
                }
            }
        });
        
        card.add(new JLabel("")); 
        card.add(btnNovoEndereco, "growx, h 35!");
        // ---------------------------

        JButton btnSalvar = new JButton("Salvar Perfil");
        btnSalvar.setBackground(new Color(106, 143, 123));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 14));
        card.add(btnSalvar, "span,align center,w 200!,h 40!, gapy 10");

    }
}