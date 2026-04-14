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
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;
import Modelo.Sessao;

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
        setBounds(100, 100, 1200, 850);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(106, 143, 123));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);

        contentPane.setLayout(new MigLayout(
                "wrap, fillx, insets 20",
                "[grow, center]",
                "[]40[]"
        ));

        JPanel menu = new JPanel();
        menu.setBackground(new Color(106, 143, 123));
        menu.setBorder(new LineBorder(Color.WHITE, 1, true));
        menu.setLayout(new MigLayout("insets 10", "[left]push[center][center][center][center][center]", "[]"));

        JLabel logo = new JLabel("WeStyle");
        logo.setFont(new Font("Arial", Font.BOLD, 30));
        logo.setForeground(Color.WHITE);
        menu.add(logo);

        JButton btnInicio = criarBotaoNav("Inicio");
        menu.add(btnInicio);

        JButton btnCarrinho = criarBotaoNav("Carrinho");
        menu.add(btnCarrinho);

        JButton btnCriacoes = criarBotaoNav("Minhas Criações");
        menu.add(btnCriacoes);

        JButton btnPersonalizar = criarBotaoNav("Personalizar");
        menu.add(btnPersonalizar);

        JButton btnSairMenu = criarBotaoNav("Sair");
        btnSairMenu.addActionListener(e -> efetuarLogoff());
        menu.add(btnSairMenu);

        contentPane.add(menu, "growx, h 80!");

        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        card.setLayout(new MigLayout(
                "wrap 2, insets 40, gap 20",
                "[grow, fill][grow, fill]",
                "[]40[][][][][][]"
        ));

        JLabel lblTitulo = new JLabel("Perfil do Usuário");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(new Color(106, 143, 123));
        card.add(lblTitulo, "span, align center, gapy 10 30");

        JLabel labelNome = new JLabel("Nome Completo");
        labelNome.setFont(new Font("Arial", Font.BOLD, 14));
        labelNome.setForeground(new Color(106, 143, 123));
        card.add(labelNome);
        
        JLabel labelEmail = new JLabel("E-mail");
        labelEmail.setFont(new Font("Arial", Font.BOLD, 14));
        labelEmail.setForeground(new Color(106, 143, 123));
        card.add(labelEmail);

        txtNome = new JTextField();
        card.add(txtNome, "h 40!");

        txtEmail = new JTextField();
        card.add(txtEmail, "h 40!");

        JLabel labelTelefone = new JLabel("Telefone");
        labelTelefone.setFont(new Font("Arial", Font.BOLD, 14));
        labelTelefone.setForeground(new Color(106, 143, 123));
        card.add(labelTelefone);
        
        JLabel labelEndereco = new JLabel("Endereço de Entrega");
        labelEndereco.setFont(new Font("Arial", Font.BOLD, 14));
        labelEndereco.setForeground(new Color(106, 143, 123));
        card.add(labelEndereco);

        txtTelefone = new JTextField();
        card.add(txtTelefone, "h 40!");

        comboEnderecos = new JComboBox<>();
        comboEnderecos.setBackground(Color.WHITE);
        card.add(comboEnderecos, "h 40!");

        card.add(new JLabel("")); 

        JButton btnNovoEndereco = new JButton("Adicionar Novo Endereço");
        btnNovoEndereco.setBackground(new Color(106, 143, 123));
        btnNovoEndereco.setForeground(Color.WHITE);
        btnNovoEndereco.setFont(new Font("Arial", Font.BOLD, 12));
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
        card.add(btnNovoEndereco, "h 35!");

        JPanel panelBotoes = new JPanel(new MigLayout("insets 0", "[grow][grow]", "[]"));
        panelBotoes.setOpaque(false);

        JButton btnSalvar = new JButton("Salvar Perfil");
        btnSalvar.setBackground(new Color(106, 143, 123));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 16));
        panelBotoes.add(btnSalvar, "growx, h 50!");

        JButton btnLogoff = new JButton("Deslogar");
        btnLogoff.setBackground(new Color(106, 143, 123));
        btnLogoff.setForeground(Color.WHITE);
        btnLogoff.setFont(new Font("Arial", Font.BOLD, 16));
        btnLogoff.addActionListener(e -> efetuarLogoff());
        panelBotoes.add(btnLogoff, "growx, h 50!");

        card.add(panelBotoes, "span, growx, gapy 30");

        contentPane.add(card, "w 800!, h 650!");
    }

    private JButton criarBotaoNav(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(106, 143, 123));
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        return btn;
    }

    private void efetuarLogoff() {
        UIManager.put("OptionPane.yesButtonText", "Sim");
        UIManager.put("OptionPane.noButtonText", "Não");
        
        int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Logoff", JOptionPane.YES_NO_OPTION);
        
        if (confirmacao == JOptionPane.YES_OPTION) {
            Sessao.encerrarSessao();
            JOptionPane.showMessageDialog(null, "Deslogado com sucesso!");
            new Telaprincipal().setVisible(true);
            dispose();
        }
    }
}