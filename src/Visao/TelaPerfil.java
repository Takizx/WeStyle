package Visao;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
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
import Modelo.DadosCompartilhados;

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

        contentPane.setLayout(new MigLayout("wrap, fillx, insets 20", "[grow, center]", "[]40[]"));

        JPanel menu = new JPanel();
        menu.setBackground(new Color(106, 143, 123));
        menu.setBorder(new LineBorder(Color.WHITE, 1, true));
        menu.setLayout(new MigLayout("insets 10", "[left]push[center][center][center][center][center]", "[]"));

        JLabel logo = new JLabel("WeStyle");
        logo.setFont(new Font("Arial", Font.BOLD, 30));
        logo.setForeground(Color.WHITE);
        menu.add(logo);

        menu.add(criarBotaoNav("Inicio"));
        menu.add(criarBotaoNav("Carrinho"));
        menu.add(criarBotaoNav("Minhas Criações"));
        menu.add(criarBotaoNav("Personalizar"));

        JButton btnSairMenu = criarBotaoNav("Sair");
        btnSairMenu.addActionListener(e -> efetuarLogoff());
        menu.add(btnSairMenu);

        contentPane.add(menu, "growx, h 80!");

        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        card.setLayout(new MigLayout("wrap 2, insets 40, gap 20", "[grow,fill][grow,fill]", "[]40[][][][][][]"));

        JLabel lblTitulo = new JLabel("Perfil do Usuário");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(new Color(106, 143, 123));
        card.add(lblTitulo, "cell 0 0 2 1,alignx center,gapy 10 30");

        card.add(new JLabel("Nome Completo"), "cell 0 1");
        card.add(new JLabel("E-mail"), "cell 1 1");

        txtNome = new JTextField();
        card.add(txtNome, "cell 0 2,height 40!");

        txtEmail = new JTextField();
        card.add(txtEmail, "cell 1 2,height 40!");

        card.add(new JLabel("Telefone"), "cell 0 3");
        card.add(new JLabel("Endereço de Entrega"), "cell 1 3");

        txtTelefone = new JTextField();
        card.add(txtTelefone, "cell 0 4,height 40!");

        comboEnderecos = new JComboBox<>();
        comboEnderecos.setBackground(Color.WHITE);
        card.add(comboEnderecos, "cell 1 4,height 40!");
        
        JButton btnAlterarSenha = new JButton("Alterar Senha");
        btnAlterarSenha.setForeground(Color.WHITE);
        btnAlterarSenha.setBackground(new Color(106, 143, 123));
        btnAlterarSenha.addActionListener(e -> {
            new TelaAlterarSenha().setVisible(true);
            dispose();
        });
        card.add(btnAlterarSenha, "cell 0 5,height 35!");
        
        JButton btnNovoEndereco = new JButton("Adicionar Novo Endereço");
        btnNovoEndereco.setBackground(new Color(106, 143, 123));
        btnNovoEndereco.setForeground(Color.WHITE);
        btnNovoEndereco.addActionListener(e -> {
            String novoEndereco = JOptionPane.showInputDialog(null, "Digite o endereço completo:");
            if (novoEndereco != null && !novoEndereco.trim().isEmpty()) {
                comboEnderecos.addItem(novoEndereco);
                comboEnderecos.setSelectedItem(novoEndereco);
            }
        });
        card.add(btnNovoEndereco, "cell 1 5,height 35!");

        JButton btnSalvar = new JButton("Salvar Perfil");
        btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnSalvar.setBackground(new Color(106, 143, 123));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.addActionListener(e -> {
            Object selected = comboEnderecos.getSelectedItem();
            if (selected != null) {
                DadosCompartilhados.enderecoEntrega = selected.toString();
            }
            JOptionPane.showMessageDialog(null, "Perfil salvo com sucesso!");
            new TelaCatalogo().setVisible(true);
            dispose();
        });
        card.add(btnSalvar, "cell 0 6,height 50!,gapy 30");

        JButton btnLogoff = new JButton("Deslogar");
        btnLogoff.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnLogoff.setBackground(new Color(106, 143, 123));
        btnLogoff.setForeground(Color.WHITE);
        btnLogoff.addActionListener(e -> efetuarLogoff());
        card.add(btnLogoff, "cell 1 6,height 50!,gapy 30");

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
        btn.addActionListener(e -> {
            if(texto.equals("Catálogo") || texto.equals("Inicio")) {
                new TelaCatalogo().setVisible(true);
                dispose();
            } else if(texto.equals("Personalizar")) {
                new TelaPersonalizar().setVisible(true);
                dispose();
            }
        });
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