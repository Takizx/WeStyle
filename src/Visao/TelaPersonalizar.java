package Visao;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Cursor;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import net.miginfocom.swing.MigLayout;
import Modelo.DadosCompartilhados;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaPersonalizar extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel previewCamisa;
    private JTextField txtNomePeca;
    private JTextField txtPrecoPeca;
    private int indexEdicao = -1;

    Color verde = new Color(106, 143, 123);
    Color linha = new Color(200, 220, 210);

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaPersonalizar frame = new TelaPersonalizar();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaPersonalizar() {
        configurarTela();
    }

    public TelaPersonalizar(int index, String nome, Color cor, String preco) {
        this.indexEdicao = index;
        configurarTela();
        txtNomePeca.setText(nome);
        txtPrecoPeca.setText(preco);
        previewCamisa.setBackground(cor);
    }

    private void configurarTela() {
        setTitle("WeStyle - Personalizar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1400, 900);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        JPanel navbar = new JPanel();
        navbar.setBackground(verde);
        navbar.setBorder(new MatteBorder(0, 0, 1, 0, linha));
        navbar.setLayout(new MigLayout("insets 15", "[left][grow][center][center][center][center][grow]", ""));

        JLabel logo = new JLabel("WeStyle");
        logo.setForeground(Color.WHITE);
        logo.setFont(new Font("Arial", Font.BOLD, 30));
        navbar.add(logo);
        navbar.add(new JLabel(""), "growx");
        navbar.add(criarBotaoNav("Início"));
        navbar.add(criarBotaoNav("Catálogo"));
        navbar.add(criarBotaoNav("Carrinho"));
        navbar.add(criarBotaoNav("Personalizar"));
        contentPane.add(navbar, BorderLayout.NORTH);

        JPanel fundo = new JPanel();
        fundo.setBackground(verde);
        fundo.setLayout(new MigLayout("align center center, insets 20", "[650!][500!]", "[]"));
        contentPane.add(fundo, BorderLayout.CENTER);

        JPanel previewPanel = new JPanel();
        previewPanel.setOpaque(false);
        previewPanel.setBorder(new LineBorder(linha));
        previewPanel.setLayout(new MigLayout("wrap, insets 25", "[center]", ""));

        JLabel lblPreview = new JLabel("Preview em Tempo Real");
        lblPreview.setForeground(Color.WHITE);
        lblPreview.setFont(new Font("Arial", Font.BOLD, 20));
        previewPanel.add(lblPreview);

        previewCamisa = new JPanel();
        previewCamisa.setPreferredSize(new Dimension(450, 550));
        previewCamisa.setBackground(Color.WHITE);
        previewCamisa.setBorder(new LineBorder(Color.LIGHT_GRAY));
        previewPanel.add(previewCamisa, "width 450!, height 550!");
        fundo.add(previewPanel);

        JPanel painelDireito = new JPanel();
        painelDireito.setOpaque(false);
        painelDireito.setLayout(new MigLayout("wrap, gap 15", "[grow,fill]", ""));

        JLabel titulo = new JLabel("Personalize Sua Peça");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        painelDireito.add(titulo);

        JPanel modelos = new JPanel(new MigLayout("wrap 2, insets 15", "[grow][grow]", ""));
        modelos.setBackground(verde);
        modelos.setBorder(new LineBorder(linha));
        
        JRadioButton camiseta = new JRadioButton("T-Shirt", true);
        camiseta.setOpaque(false);
        camiseta.setForeground(Color.WHITE);
        JRadioButton manga = new JRadioButton("Oversize");
        manga.setOpaque(false);
        manga.setForeground(Color.WHITE);
        JRadioButton moletom = new JRadioButton("Moletom");
        moletom.setOpaque(false);
        moletom.setForeground(Color.WHITE);
        JRadioButton regata = new JRadioButton("Regata");
        regata.setOpaque(false);
        regata.setForeground(Color.WHITE);
        
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(camiseta); grupo.add(manga); grupo.add(moletom); grupo.add(regata);
        modelos.add(camiseta); modelos.add(manga); modelos.add(moletom); modelos.add(regata);
        painelDireito.add(modelos);

        JPanel cores = new JPanel(new MigLayout("wrap 4, insets 10", "[]10[]10[]10[]", "[]10[]"));
        cores.setBackground(verde);
        cores.setBorder(new LineBorder(linha));

        JPanel p1 = new JPanel();
        p1.setPreferredSize(new Dimension(60, 60));
        p1.setBackground(Color.WHITE);
        p1.setBorder(new LineBorder(Color.LIGHT_GRAY));
        p1.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) 
        { previewCamisa.setBackground(Color.WHITE); } });
        cores.add(p1);
        

        JPanel p2 = new JPanel();
        p2.setPreferredSize(new Dimension(60, 60));
        p2.setBackground(Color.BLACK);
        p2.setBorder(new LineBorder(Color.LIGHT_GRAY));
        p2.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) 
        { previewCamisa.setBackground(Color.BLACK); } });
        cores.add(p2);

        JPanel p3 = new JPanel();
        p3.setPreferredSize(new Dimension(60, 60));
        p3.setBackground(Color.RED);
        p3.setBorder(new LineBorder(Color.LIGHT_GRAY));
        p3.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) 
        { previewCamisa.setBackground(Color.RED); } });
        cores.add(p3);

        JPanel p4 = new JPanel();
        p4.setPreferredSize(new Dimension(60, 60));
        p4.setBackground(Color.BLUE);
        p4.setBorder(new LineBorder(Color.LIGHT_GRAY));
        p4.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e)
        { previewCamisa.setBackground(Color.BLUE); } });
        cores.add(p4);

        JPanel p5 = new JPanel();
        p5.setPreferredSize(new Dimension(60, 60));
        p5.setBackground(new Color(26, 188, 156));
        p5.setBorder(new LineBorder(Color.LIGHT_GRAY));
        p5.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) 
        { previewCamisa.setBackground(new Color(26, 188, 156)); } });
        cores.add(p5);

        JPanel p6 = new JPanel();
        p6.setPreferredSize(new Dimension(60, 60));
        p6.setBackground(new Color(241, 196, 15));
        p6.setBorder(new LineBorder(Color.LIGHT_GRAY));
        p6.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) 
        { previewCamisa.setBackground(new Color(241, 196, 15)); } });
        cores.add(p6);

        JPanel p7 = new JPanel();
        p7.setPreferredSize(new Dimension(60, 60));
        p7.setBackground(new Color(155, 89, 182));
        p7.setBorder(new LineBorder(Color.LIGHT_GRAY));
        p7.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) 
        { previewCamisa.setBackground(new Color(155, 89, 182)); } });
        cores.add(p7);

        JPanel p8 = new JPanel();
        p8.setPreferredSize(new Dimension(60, 60));
        p8.setBackground(new Color(231, 76, 160));
        p8.setBorder(new LineBorder(Color.LIGHT_GRAY));
        p8.addMouseListener(new MouseAdapter() { @Override public void mouseClicked(MouseEvent e) 
        { previewCamisa.setBackground(new Color(231, 76, 160)); } });
        cores.add(p8);

        painelDireito.add(cores);

        JLabel lblTamanho = new JLabel("Tamanho");
        lblTamanho.setForeground(Color.WHITE);
        painelDireito.add(lblTamanho);

        JComboBox<String> comboTamanho = new JComboBox<>(new String[]{"P", "M", "G", "GG"});
        comboTamanho.setForeground(new Color(106, 143, 123));
        painelDireito.add(comboTamanho, "h 35!");

        JLabel lblEstampa = new JLabel("Estampa");
        lblEstampa.setForeground(Color.WHITE);
        painelDireito.add(lblEstampa);

        JButton btnEscolherEstampa = new JButton("Personalizar Estampa");
        btnEscolherEstampa.setBackground(Color.WHITE);
        btnEscolherEstampa.setForeground(verde);
        btnEscolherEstampa.setFont(new Font("Arial", Font.BOLD, 14));
        btnEscolherEstampa.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEscolherEstampa.addActionListener(e -> {
            new TelaEstampa().setVisible(true);
            dispose();
        });
        painelDireito.add(btnEscolherEstampa, "h 35!");

        JLabel lblNome = new JLabel("Nome da Criação");
        lblNome.setForeground(Color.WHITE);
        painelDireito.add(lblNome);

        txtNomePeca = new JTextField();
        txtNomePeca.setForeground(new Color(106, 143, 123));
        painelDireito.add(txtNomePeca, "h 35!");

        JLabel lblPreco = new JLabel("Preço (R$)");
        lblPreco.setForeground(Color.WHITE);
        painelDireito.add(lblPreco);

        txtPrecoPeca = new JTextField("");
        txtPrecoPeca.setForeground(new Color(106, 143, 123));
        painelDireito.add(txtPrecoPeca, "h 35!");

        JButton btnEnviar = new JButton("Enviar para o Catálogo");
        btnEnviar.setBackground(Color.WHITE); 
        btnEnviar.setForeground(verde);       
        btnEnviar.setFont(new Font("Arial", Font.BOLD, 18));
        btnEnviar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEnviar.addActionListener(e -> {
        });
        painelDireito.add(btnEnviar, "gapy 20, h 50!");

        fundo.add(painelDireito);
    }

    private JButton criarBotaoNav(String texto) {
        JButton b = new JButton(texto);
        b.setFont(new Font("Tahoma", Font.PLAIN, 20));
        b.setForeground(Color.WHITE);
        b.setBackground(verde);
        b.setBorder(null);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b.addActionListener(e -> {
            if(texto.equals("Catálogo")) {
                new TelaCatalogo().setVisible(true);
                dispose();
            }
        });
        return b;
    }
}