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

public class TelaPersonalizar extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JPanel previewCamisa;

    // LABEL DA ESTAMPA
    private JLabel lblEstampaPreview;

    private JTextField txtNomePeca;
    private JTextField txtPrecoPeca;

    private JComboBox<String> comboEstampas;

    private ProdutoController produtoController =
            new ProdutoController();

    private boolean ehEdicao = false;

    private String nomeAntigo = "";

    Color verde = new Color(106, 143, 123);

    Color linha = new Color(200, 220, 210);

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            try {

                TelaPersonalizar frame =
                        new TelaPersonalizar();

                frame.setVisible(true);

            } catch (Exception e) {

                e.printStackTrace();
            }
        });
    }

    public TelaPersonalizar() {

        configurarTela();

        carregarEstampas();
    }

    public TelaPersonalizar(
            String nome,
            String preco,
            String corHex
    ) {

        this();

        this.ehEdicao = true;

        this.nomeAntigo = nome;

        txtNomePeca.setText(nome);

        txtPrecoPeca.setText(preco);

        try {

            previewCamisa.setBackground(
                    Color.decode("#" + corHex)
            );

        } catch (Exception e) {

            previewCamisa.setBackground(Color.WHITE);
        }
    }

    private void carregarEstampas() {

        EstampaDAO dao = new EstampaDAO();

        List<String> nomes =
                dao.listarNomesEstampas();

        comboEstampas.removeAllItems();

        comboEstampas.addItem(
                "Selecione sua estampa..."
        );

        if (nomes != null) {

            for (String nome : nomes) {

                comboEstampas.addItem(nome);
            }
        }
    }

    private void configurarTela() {

        setTitle("WeStyle - Personalizar");

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setBounds(100, 100, 1400, 900);

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        contentPane =
                new JPanel(new BorderLayout());

        setContentPane(contentPane);

        // NAVBAR

        JPanel navbar = new JPanel(

                new MigLayout(
                        "insets 15, fillx",
                        "[left]push[center][center][center]push[right]",
                        ""
                )
        );

        navbar.setBackground(verde);

        navbar.setBorder(

                new MatteBorder(
                        0,
                        0,
                        1,
                        0,
                        linha
                )
        );

        JLabel logo = new JLabel("WeStyle");

        logo.setForeground(Color.WHITE);

        logo.setFont(

                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        navbar.add(logo);

        navbar.add(criarBotaoNav("Inicio"));

        navbar.add(criarBotaoNav("Catálogo"));

        navbar.add(criarBotaoNav("Carrinho"));

        navbar.add(criarBotaoNav("Perfil"));

        contentPane.add(
                navbar,
                BorderLayout.NORTH
        );

        // FUNDO

        JPanel fundo = new JPanel(

                new MigLayout(
                        "align center center, insets 20",
                        "[650!][500!]",
                        "[]"
                )
        );

        fundo.setBackground(verde);

        contentPane.add(
                fundo,
                BorderLayout.CENTER
        );

        // PREVIEW

        JPanel previewPanel = new JPanel(

                new MigLayout(
                        "wrap, insets 25",
                        "[center]",
                        ""
                )
        );

        previewPanel.setOpaque(false);

        previewPanel.setBorder(
                new LineBorder(linha)
        );

        JLabel lblPreview =
                new JLabel("Preview em Tempo Real");

        lblPreview.setForeground(Color.WHITE);

        lblPreview.setFont(

                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        previewPanel.add(lblPreview);

        // CAMISA

        previewCamisa =
                new JPanel(new BorderLayout());

        previewCamisa.setBackground(Color.WHITE);

        previewCamisa.setBorder(
                new LineBorder(Color.LIGHT_GRAY)
        );

        // LABEL DA ESTAMPA

        lblEstampaPreview = new JLabel();

        lblEstampaPreview.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        previewCamisa.add(
                lblEstampaPreview,
                BorderLayout.CENTER
        );

        previewPanel.add(
                previewCamisa,
                "width 450!, height 550!"
        );

        fundo.add(previewPanel);

        // PAINEL DIREITO

        JPanel painelDireito = new JPanel(

                new MigLayout(
                        "wrap, gap 15",
                        "[grow,fill]",
                        ""
                )
        );

        painelDireito.setOpaque(false);

        JLabel titulo =
                new JLabel("Personalize Sua Peça");

        titulo.setForeground(Color.WHITE);

        titulo.setFont(

                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        painelDireito.add(titulo);

        // CORES

        JPanel cores = new JPanel(

                new MigLayout(
                        "wrap 4, insets 10",
                        "[]10[]10[]10[]",
                        "[]10[]"
                )
        );

        cores.setBackground(verde);

        cores.setBorder(
                new LineBorder(linha)
        );

        Color[] listaCores = {

                Color.WHITE,
                Color.BLACK,
                Color.RED,
                Color.BLUE,

                new Color(26, 188, 156),
                new Color(241, 196, 15),
                new Color(155, 89, 182),
                new Color(231, 76, 160)
        };

        for (Color c : listaCores) {

            JPanel p = new JPanel();

            p.setPreferredSize(
                    new Dimension(60, 60)
            );

            p.setBackground(c);

            p.addMouseListener(
                    new MouseAdapter() {

                        public void mouseClicked(
                                MouseEvent e
                        ) {

                            previewCamisa.setBackground(c);
                        }
                    }
            );

            cores.add(p);
        }

        painelDireito.add(cores);

        // ESTAMPA

        painelDireito.add(

                new JLabel("Estampa") {{

                    setForeground(Color.WHITE);
                }}
        );

        JButton btnCriarEstampa =
                new JButton("Criar estampa");

        btnCriarEstampa.setBackground(Color.WHITE);

        btnCriarEstampa.setForeground(verde);

        btnCriarEstampa.addActionListener(e -> {

            new TelaEstampa().setVisible(true);

            dispose();
        });

        painelDireito.add(
                btnCriarEstampa,
                "h 35!"
        );

        comboEstampas = new JComboBox<>();

        comboEstampas.setBackground(Color.WHITE);

        comboEstampas.setForeground(verde);

        painelDireito.add(

                new JLabel("Selecione sua estampa") {{

                    setForeground(Color.WHITE);
                }}
        );

        painelDireito.add(
                comboEstampas,
                "h 35!"
        );

        // EVENTO DO COMBO

        comboEstampas.addActionListener(e -> {

            String nomeEstampa =
                    (String)
                            comboEstampas.getSelectedItem();

            if (
                    nomeEstampa != null
                            &&
                            !nomeEstampa.equals(
                                    "Selecione sua estampa..."
                            )
            ) {

                carregarImagemEstampa(nomeEstampa);
            }
        });

        // NOME

        painelDireito.add(

                new JLabel("Nome da Criação") {{

                    setForeground(Color.WHITE);
                }}
        );

        txtNomePeca = new JTextField();

        txtNomePeca.setForeground(verde);

        painelDireito.add(
                txtNomePeca,
                "h 35!"
        );

        // PREÇO

        painelDireito.add(

                new JLabel("Preço (R$)") {{

                    setForeground(Color.WHITE);
                }}
        );

        txtPrecoPeca = new JTextField();

        txtPrecoPeca.setForeground(verde);

        painelDireito.add(
                txtPrecoPeca,
                "h 35!"
        );

        // BOTÃO

        JButton btnEnviar = new JButton(

                ehEdicao
                        ?
                        "Salvar Alterações"
                        :
                        "Enviar para o Catálogo"
        );

        btnEnviar.setBackground(Color.WHITE);

        btnEnviar.setForeground(verde);

        btnEnviar.setFont(

                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        btnEnviar.addActionListener(e -> {

            String nome =
                    txtNomePeca.getText();

            String preco =
                    txtPrecoPeca.getText();

            Color corPreview =
                    previewCamisa.getBackground();

            String hexCor =
                    String.format(
                            "%02x%02x%02x",
                            corPreview.getRed(),
                            corPreview.getGreen(),
                            corPreview.getBlue()
                    );

            if (
                    nome.isEmpty()
                            ||
                            preco.isEmpty()
            ) {

                JOptionPane.showMessageDialog(
                        null,
                        "Preencha tudo!"
                );

            } else {

                boolean ok =
                        ehEdicao
                                ?
                                produtoController.atualizarProduto(
                                        nomeAntigo,
                                        nome,
                                        preco,
                                        hexCor
                                )
                                :
                                produtoController.cadastrarProduto(
                                        nome,
                                        preco,
                                        hexCor
                                );

                if (ok) {

                    new TelaCatalogo().setVisible(true);

                    dispose();

                } else {

                    JOptionPane.showMessageDialog(
                            null,
                            "Erro ao salvar."
                    );
                }
            }
        });

        painelDireito.add(
                btnEnviar,
                "gapy 20, h 50!"
        );

        fundo.add(painelDireito);
    }

    // CARREGAR IMAGEM DA ESTAMPA

    private void carregarImagemEstampa(
            String nomeEstampa
    ) {

        try {

            String caminhoPng =
                    "Imagens/Estampa "
                            +
                            nomeEstampa
                            +
                            ".png";

            String caminhoJpg =
                    "Imagens/Estampa "
                            +
                            nomeEstampa
                            +
                            ".jpg";

            File arquivo =
                    new File(caminhoPng);

            // SE NÃO EXISTIR PNG
            // TESTA JPG

            if (!arquivo.exists()) {

                arquivo =
                        new File(caminhoJpg);
            }

            // SE NÃO EXISTIR NENHUM

            if (!arquivo.exists()) {

                JOptionPane.showMessageDialog(
                        null,
                        "Imagem não encontrada."
                );

                return;
            }

            ImageIcon icon =
                    new ImageIcon(
                            arquivo.getAbsolutePath()
                    );

            Image imagemRedimensionada =
                    icon.getImage().getScaledInstance(
                            300,
                            300,
                            Image.SCALE_SMOOTH
                    );

            lblEstampaPreview.setIcon(

                    new ImageIcon(
                            imagemRedimensionada
                    )
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Erro ao carregar imagem."
            );

            e.printStackTrace();
        }
    }

    private JButton criarBotaoNav(
            String texto
    ) {

        JButton b = new JButton(texto);

        b.setFont(

                new Font(
                        "Tahoma",
                        Font.PLAIN,
                        20
                )
        );

        b.setForeground(Color.WHITE);

        b.setBackground(verde);

        b.setBorderPainted(false);

        b.setFocusPainted(false);

        b.setContentAreaFilled(false);

        b.addActionListener(e -> {

            if (texto.equals("Inicio")) {

                new TelaEscolha().setVisible(true);

                dispose();

            } else if (
                    texto.equals("Catálogo")
            ) {

                new TelaCatalogo().setVisible(true);

                dispose();

            } else if (
                    texto.equals("Carrinho")
            ) {

                new TelaCarrinho().setVisible(true);

                dispose();

            } else if (
                    texto.equals("Perfil")
            ) {

                new TelaPerfil().setVisible(true);

                dispose();
            }
        });

        return b;
    }
}