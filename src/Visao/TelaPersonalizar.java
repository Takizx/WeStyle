package Visao;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import net.miginfocom.swing.MigLayout;
import Controle.EstampaDAO;
import Controle.ProdutoController;

public class TelaPersonalizar extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel previewCamisa;
    private JLabel lblEstampaPreview;
    private JTextField txtNomePeca;
    private JTextField txtPrecoPeca;
    private JComboBox<String> comboEstampas;
    private ProdutoController produtoController = new ProdutoController();
    private boolean ehEdicao = false;
    private String nomeAntigo = "";

    Color verde = new Color(106, 143, 123);
    Color linha = new Color(200, 220, 210);

    public TelaPersonalizar() {
        configurarTela();
        carregarEstampas();
    }

    public TelaPersonalizar(String nome, String preco, String corHex, String nomeEstampaCompleto) {
        this();
        this.ehEdicao = true;
        this.nomeAntigo = nome;
        txtNomePeca.setText(nome);
        txtPrecoPeca.setText(preco);
        try {
            previewCamisa.setBackground(Color.decode("#" + corHex));
        } catch (Exception e) {
            previewCamisa.setBackground(Color.WHITE);
        }

        if (nomeEstampaCompleto != null && !nomeEstampaCompleto.isEmpty()) {
            String nomeEstampaLimpo = nomeEstampaCompleto.replace("Estampa ", "").replace(".png", "").replace(".jpg", "").trim();
            for (int i = 0; i < comboEstampas.getItemCount(); i++) {
                if (comboEstampas.getItemAt(i).equalsIgnoreCase(nomeEstampaLimpo)) {
                    comboEstampas.setSelectedIndex(i);
                    carregarImagemEstampa(comboEstampas.getItemAt(i));
                    break;
                }
            }
        }
    }

    public void atualizarListaEstampas() {
        carregarEstampas();
    }

    private void carregarEstampas() {
        EstampaDAO dao = new EstampaDAO();
        List<String> nomes = dao.listarNomesEstampas();
        comboEstampas.removeAllItems();
        comboEstampas.addItem("Selecione sua estampa...");
        if (nomes != null) {
            for (String nome : nomes) {
                comboEstampas.addItem(nome);
            }
        }
    }

    private void configurarTela() {
        this.setLayout(new BorderLayout());

        JPanel navbar = new JPanel(new MigLayout("insets 15, fillx", "[left]push[center][center][center]push[right]", ""));
        navbar.setBackground(verde);
        navbar.setBorder(new MatteBorder(0, 0, 1, 0, linha));

        JLabel logo = new JLabel("WeStyle");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Arial", Font.BOLD, 30));
        navbar.add(logo);

        navbar.add(criarBotaoNav("Inicio"));
        navbar.add(criarBotaoNav("Catálogo"));
        navbar.add(criarBotaoNav("Carrinho"));
        navbar.add(criarBotaoNav("Perfil"));
        this.add(navbar, BorderLayout.NORTH);

        JPanel fundo = new JPanel(new MigLayout("align center center, insets 20", "[650!][500!]", "[]"));
        fundo.setBackground(verde);
        this.add(fundo, BorderLayout.CENTER);

        JPanel previewPanel = new JPanel(new MigLayout("wrap, insets 25", "[center]", ""));
        previewPanel.setOpaque(false);
        previewPanel.setBorder(new LineBorder(linha));

        JLabel lblPreview = new JLabel("Preview em Tempo Real");
        lblPreview.setForeground(Color.WHITE);
        lblPreview.setFont(new Font("Arial", Font.BOLD, 20));
        previewPanel.add(lblPreview);

        previewCamisa = new JPanel(new BorderLayout());
        previewCamisa.setBackground(Color.WHITE);
        previewCamisa.setBorder(new LineBorder(Color.LIGHT_GRAY));

        lblEstampaPreview = new JLabel();
        lblEstampaPreview.setHorizontalAlignment(SwingConstants.CENTER);
        previewCamisa.add(lblEstampaPreview, BorderLayout.CENTER);
        previewPanel.add(previewCamisa, "width 450!, height 550!");
        fundo.add(previewPanel);

        JPanel painelDireito = new JPanel(new MigLayout("wrap, gap 15", "[grow,fill]", ""));
        painelDireito.setOpaque(false);

        JLabel titulo = new JLabel("Personalize Sua Peça");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        painelDireito.add(titulo);

        JPanel cores = new JPanel(new MigLayout("wrap 4, insets 10", "[]10[]10[]10[]", "[]10[]"));
        cores.setBackground(verde);
        cores.setBorder(new LineBorder(linha));

        Color[] listaCores = {
                Color.WHITE, Color.BLACK, Color.RED, Color.BLUE,
                new Color(26, 188, 156), new Color(241, 196, 15),
                new Color(155, 89, 182), new Color(231, 76, 160)
        };

        for (Color c : listaCores) {
            JPanel p = new JPanel();
            p.setPreferredSize(new Dimension(60, 60));
            p.setBackground(c);
            p.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    previewCamisa.setBackground(c);
                }
            });
            cores.add(p);
        }
        painelDireito.add(cores);

        painelDireito.add(new JLabel("Estampa") {{ setForeground(Color.WHITE); }});

        JButton btnCriarEstampa = new JButton("Criar estampa");
        btnCriarEstampa.setBackground(Color.WHITE);
        btnCriarEstampa.setForeground(verde);
        btnCriarEstampa.addActionListener(e -> {
            JanelaPrincipal.mudarTela("estampa");
        });
        painelDireito.add(btnCriarEstampa, "h 35!");

        comboEstampas = new JComboBox<>();
        comboEstampas.setBackground(Color.WHITE);
        comboEstampas.setForeground(verde);

        painelDireito.add(new JLabel("Selecione sua estampa") {{ setForeground(Color.WHITE); }});
        painelDireito.add(comboEstampas, "h 35!");

        comboEstampas.addActionListener(e -> {
            String nomeEstampa = (String) comboEstampas.getSelectedItem();
            if (nomeEstampa != null && !nomeEstampa.equals("Selecione sua estampa...")) {
                carregarImagemEstampa(nomeEstampa);
            }
        });

        painelDireito.add(new JLabel("Nome da Criação") {{ setForeground(Color.WHITE); }});
        txtNomePeca = new JTextField();
        txtNomePeca.setForeground(verde);
        painelDireito.add(txtNomePeca, "h 35!");

        painelDireito.add(new JLabel("Preço (R$)") {{ setForeground(Color.WHITE); }});
        txtPrecoPeca = new JTextField();
        txtPrecoPeca.setForeground(verde);
        painelDireito.add(txtPrecoPeca, "h 35!");

        JButton btnEnviar = new JButton(ehEdicao ? "Salvar Alterações" : "Enviar para o Catálogo");
        btnEnviar.setBackground(Color.WHITE);
        btnEnviar.setForeground(verde);
        btnEnviar.setFont(new Font("Arial", Font.BOLD, 18));
        btnEnviar.addActionListener(e -> {
            String nome = txtNomePeca.getText();
            String preco = txtPrecoPeca.getText();
            Color corPreview = previewCamisa.getBackground();
            String hexCor = String.format("%02x%02x%02x", corPreview.getRed(), corPreview.getGreen(), corPreview.getBlue());

            String estampaSelecionada = (String) comboEstampas.getSelectedItem();
            String nomeImagemEstampa = "";

            if (estampaSelecionada != null && !estampaSelecionada.equals("Selecione sua estampa...")) {
                EstampaDAO estampaDAO = new EstampaDAO();
                nomeImagemEstampa = estampaDAO.buscarImagemPorNome(estampaSelecionada);
                if (nomeImagemEstampa == null || nomeImagemEstampa.isEmpty()) {
                    nomeImagemEstampa = "Estampa " + estampaSelecionada + ".png";
                }
            }

            if (nome.isEmpty() || preco.isEmpty()) {
                new TelaMensagem("Por favor, preencha todos os campos.", "erro");
            } else {
                boolean ok = ehEdicao
                        ? produtoController.atualizarProduto(nomeAntigo, nome, preco, hexCor, nomeImagemEstampa)
                        : produtoController.cadastrarProduto(nome, preco, hexCor, nomeImagemEstampa);

                if (ok) {
                    new TelaMensagem(ehEdicao ? "Alterações salvas com sucesso!" : "Produto enviado para o catálogo com sucesso!", "sucesso");
                    Modelo.DadosCompartilhados.produtoSelecionado = "";
                    JanelaPrincipal.mudarTela("catalogo");
                } else {
                    new TelaMensagem("Erro ao salvar os dados.", "erro");
                }
            }
        });
        painelDireito.add(btnEnviar, "gapy 20, h 50!");
        fundo.add(painelDireito);
    }

    private void carregarImagemEstampa(String nomeEstampa) {
        try {
            EstampaDAO dao = new EstampaDAO();
            String nomeArquivoBanco = dao.buscarImagemPorNome(nomeEstampa);
            
            File arquivo = null;
            
            if (nomeArquivoBanco != null && !nomeArquivoBanco.isEmpty()) {
                String caminhoBase = new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
                File pastaDestino = new File(caminhoBase, "Imagens");
                arquivo = new File(pastaDestino, nomeArquivoBanco);
            }
            
            if (arquivo == null || !arquivo.exists()) {
                arquivo = new File("Imagens/Estampa " + nomeEstampa + ".png");
            }
            if (!arquivo.exists()) {
                arquivo = new File("Imagens/Estampa " + nomeEstampa + ".jpg");
            }

            if (!arquivo.exists()) {
                return;
            }

            ImageIcon icon = new ImageIcon(arquivo.getAbsolutePath());
            Image imagemRedimensionada = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
            lblEstampaPreview.setIcon(new ImageIcon(imagemRedimensionada));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JButton criarBotaoNav(String texto) {
        JButton b = new JButton(texto);
        b.setFont(new Font("Tahoma", Font.PLAIN, 20));
        b.setForeground(Color.WHITE);
        b.setBackground(verde);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setContentAreaFilled(false);
        b.addActionListener(e -> {
            if (texto.equalsIgnoreCase("Inicio")) {
                JanelaPrincipal.mudarTela("escolha");
            } else if (texto.equalsIgnoreCase("Catálogo") || texto.equalsIgnoreCase("Catalogo")) {
                JanelaPrincipal.mudarTela("catalogo");
            } else if (texto.equalsIgnoreCase("Carrinho")) {
                JanelaPrincipal.mudarTela("carrinho");
            } else if (texto.equalsIgnoreCase("Perfil")) {
                JanelaPrincipal.mudarTela("perfil");
            }
        });
        return b;
    }
}