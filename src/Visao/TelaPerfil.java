package Visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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
import Modelo.Usuario;
import Modelo.DadosCompartilhados;
import Controle.UsuarioDAO;

public class TelaPerfil extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTextField txtNome;
    private JTextField txtEmail;
    private JTextField txtTelefone;
    private JComboBox<String> comboEnderecos; 

    public TelaPerfil() {
        this.setBackground(new Color(106, 143, 123));
        this.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.setLayout(new MigLayout("wrap, fillx, insets 20", "[grow, center]", "[]40[]"));

        JPanel menu = new JPanel();
        menu.setBackground(new Color(106, 143, 123));
        menu.setBorder(new LineBorder(Color.WHITE, 1, true));
        menu.setLayout(new MigLayout("insets 10, fillx, aligny center", "[left][grow][center][center][center][center][grow][right]", "[]"));

        JLabel logo = new JLabel("WeStyle");
        logo.setFont(new Font("Arial", Font.BOLD, 30));
        logo.setForeground(Color.WHITE);
        menu.add(logo, "cell 0 0");

        JLabel espacador1 = new JLabel("");
        menu.add(espacador1, "cell 1 0, growx");

        menu.add(criarBotaoNav("Inicio"), "cell 2 0");
        menu.add(criarBotaoNav("Catálogo"), "cell 3 0");
        menu.add(criarBotaoNav("Personalizar"), "cell 4 0");
        
        Usuario usuarioLogado = Sessao.getUsuario(); 
        if (usuarioLogado == null || !"CRIADOR".equals(usuarioLogado.getTipo())) {
            menu.add(criarBotaoNav("Carrinho"), "cell 5 0");
        }

        JLabel espacador2 = new JLabel("");
        menu.add(espacador2, "cell 6 0, growx");

        JButton btnPerfilMenu = criarBotaoNav("Perfil");
        menu.add(btnPerfilMenu, "cell 7 0");

        this.add(menu, "growx, h 90!");

        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        card.setLayout(new MigLayout("wrap 2, insets 40, gap 20", "[grow,fill][grow,fill]", "[]40[][][][][][][]"));

        JLabel lblTitulo = new JLabel("Perfil do Usuário");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 32));
        lblTitulo.setForeground(new Color(106, 143, 123));
        card.add(lblTitulo, "cell 0 0 2 1,alignx center,gapy 10 30");

        JLabel label = new JLabel("Nome Completo");
        label.setForeground(new Color(106, 143, 123));
        card.add(label, "cell 0 1");
        JLabel label_2 = new JLabel("E-mail");
        label_2.setForeground(new Color(106, 143, 123));
        card.add(label_2, "cell 1 1");

        txtNome = new JTextField();
        card.add(txtNome, "cell 0 2,height 40!");

        txtEmail = new JTextField();
        card.add(txtEmail, "cell 1 2,height 40!");

        JLabel label_1 = new JLabel("Telefone");
        label_1.setForeground(new Color(106, 143, 123));
        card.add(label_1, "cell 0 3");
        JLabel label_3 = new JLabel("Endereço de Entrega");
        label_3.setForeground(new Color(106, 143, 123));
        card.add(label_3, "cell 1 3");

        txtTelefone = new JTextField();
        card.add(txtTelefone, "cell 0 4,height 40!");

        comboEnderecos = new JComboBox<>();
        comboEnderecos.setBackground(Color.WHITE);
        card.add(comboEnderecos, "cell 1 4,height 40!");
        
        if (usuarioLogado != null) {
            txtNome.setText(usuarioLogado.getNome());
            txtEmail.setText(usuarioLogado.getEmail());
            txtTelefone.setText(usuarioLogado.getTelefone());
            if (usuarioLogado.getEndereco() != null && !usuarioLogado.getEndereco().isEmpty()) {
                comboEnderecos.addItem(usuarioLogado.getEndereco());
                comboEnderecos.setSelectedItem(usuarioLogado.getEndereco());
            }
        }
        
        JButton btnAlterarSenha = new JButton("Alterar Senha");
        btnAlterarSenha.setForeground(Color.WHITE);
        btnAlterarSenha.setBackground(new Color(106, 143, 123));
        btnAlterarSenha.addActionListener(e -> {
            JanelaPrincipal.mudarTela("alterarSenha");
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
        btnSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (usuarioLogado != null) {
                    usuarioLogado.setNome(txtNome.getText());
                    usuarioLogado.setEmail(txtEmail.getText());
                    usuarioLogado.setTelefone(txtTelefone.getText());
                    Object selected = comboEnderecos.getSelectedItem();
                    if (selected != null) {
                        usuarioLogado.setEndereco(selected.toString());
                        DadosCompartilhados.enderecoEntrega = selected.toString();
                    }
                    UsuarioDAO dao = new UsuarioDAO();
                    if (dao.atualizarPerfil(usuarioLogado)) {
                        new TelaMensagem("Perfil saved successfully!", "sucesso");
                        JanelaPrincipal.mudarTela("catalogo");
                    } else {
                        new TelaMensagem("Erro ao salvar no banco.", "erro");
                    }
                }
            }
        });
        card.add(btnSalvar, "cell 0 6,height 50!,gapy 30");

        JButton btnLogoff = new JButton("Deslogar");
        btnLogoff.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnLogoff.setBackground(new Color(106, 143, 123));
        btnLogoff.setForeground(Color.WHITE);
        btnLogoff.addActionListener(e -> efetuarsair());
        card.add(btnLogoff, "cell 1 6,height 50!,gapy 30");

        JButton btnVoltarEscolha = new JButton("Voltar");
        btnVoltarEscolha.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnVoltarEscolha.setBackground(new Color(106, 143, 123));
        btnVoltarEscolha.setForeground(Color.WHITE);
        btnVoltarEscolha.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JanelaPrincipal.mudarTela("escolha");
            }
        });
        card.add(btnVoltarEscolha, "cell 0 7 2 1,height 45!,gapy 15,alignx center");

        this.add(card, "w 800!, h 700!");
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
            if(texto.equals("Catálogo")) {
                JanelaPrincipal.mudarTela("catalogo");
            } else if(texto.equals("Inicio")) {
                JanelaPrincipal.mudarTela("escolha");
            } else if(texto.equals("Personalizar")) {
                JanelaPrincipal.mudarTela("personalizar");
            } else if(texto.equals("Carrinho")) {
                JanelaPrincipal.mudarTela("carrinho");
            } else if(texto.equals("Perfil")) {
                JanelaPrincipal.mudarTela("perfil");
            }
        });
        return btn;
    }

    private void efetuarsair() {
        UIManager.put("OptionPane.yesButtonText", "Sim");
        UIManager.put("OptionPane.noButtonText", "Não");
        int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Logoff", JOptionPane.YES_NO_OPTION);
        if (confirmacao == JOptionPane.YES_OPTION) {
            Sessao.encerrarSessao();
            new TelaMensagem("Deslogado com sucesso!", "sucesso");
            JanelaPrincipal.mudarTela("principal");
        }
    }
}