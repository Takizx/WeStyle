package Visao;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.BorderLayout;

import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import net.miginfocom.swing.MigLayout;

public class TelaPersonalizar extends JFrame {

	private JPanel contentPane;
	private JPanel previewCamisa;

	Color verde = new Color(106,143,123);
	Color linha = new Color(200,220,210);

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

		setTitle("WeStyle - Personalizar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 900);

		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		
		JPanel navbar = new JPanel();
		navbar.setBackground(verde);
		navbar.setBorder(new MatteBorder(0,0,1,0,linha));
		navbar.setLayout(new MigLayout("insets 15", "[left][grow][center][center][center][center][grow]", ""));

		JLabel logo = new JLabel("WeStyle");
		logo.setForeground(Color.WHITE);
		logo.setFont(new Font("Arial", Font.BOLD, 30));

		JButton btnInicio = new JButton("Início");
		btnInicio.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnInicio.setForeground(Color.WHITE);
		btnInicio.setBackground(verde);
	
		JButton btnCarrinho = new JButton("Carrinho");
		btnCarrinho.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnCarrinho.setForeground(Color.WHITE);
		btnCarrinho.setBackground(verde);
	

		JButton btnCriacoes = new JButton("Minhas Criações");
		btnCriacoes.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnCriacoes.setBackground(verde);
		btnCriacoes.setForeground(Color.WHITE);
		

		JButton btnPersonalizar = new JButton("Personalizar");
		btnPersonalizar.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnPersonalizar.setBackground(verde);
		btnPersonalizar.setForeground(Color.WHITE);
		

		navbar.add(logo);
		navbar.add(new JLabel(""));
		navbar.add(btnInicio, "align center");
		navbar.add(btnCarrinho, "align center");
		navbar.add(btnCriacoes, "align center");
		navbar.add(btnPersonalizar, "align center");

		contentPane.add(navbar, BorderLayout.NORTH);

		
		JPanel fundo = new JPanel();
		fundo.setBackground(verde);
		fundo.setLayout(new MigLayout("align center center", "[650][650]", ""));
		contentPane.add(fundo, BorderLayout.CENTER);

		
		JPanel previewPanel = new JPanel();
		previewPanel.setBackground(verde);
		previewPanel.setBorder(new LineBorder(linha));
		previewPanel.setLayout(new MigLayout("wrap, insets 25", "[center]", ""));

		JLabel lblPreview = new JLabel("Preview em Tempo Real");
		lblPreview.setForeground(Color.WHITE);
		lblPreview.setFont(new Font("Arial", Font.BOLD, 20));

		previewCamisa = new JPanel();
		previewCamisa.setPreferredSize(new Dimension(500,600));
		previewCamisa.setBackground(Color.WHITE);
		previewCamisa.setBorder(new LineBorder(Color.LIGHT_GRAY));

		previewPanel.add(lblPreview);
		previewPanel.add(previewCamisa);
		fundo.add(previewPanel);

		
		JPanel painelDireito = new JPanel();
		painelDireito.setOpaque(false);
		painelDireito.setLayout(new MigLayout("wrap, gap 25", "[grow,fill]", ""));

		JLabel titulo = new JLabel("Personalize Sua Peça");
		titulo.setForeground(Color.WHITE);
		titulo.setFont(new Font("Arial", Font.BOLD, 30));

		JLabel subtitulo = new JLabel("Crie uma peça única que expressa sua identidade");
		subtitulo.setForeground(Color.WHITE);

		painelDireito.add(titulo);
		painelDireito.add(subtitulo);

		
		JPanel modelos = new JPanel();
		modelos.setBackground(verde);
		modelos.setBorder(new LineBorder(linha));
		modelos.setLayout(new MigLayout("wrap 2", "[grow][grow]", ""));

		JRadioButton camiseta = new JRadioButton("T-Shirt");
		camiseta.setForeground(new Color(106, 143, 123));
		JRadioButton manga = new JRadioButton("Oversize");
		manga.setForeground(new Color(106, 143, 123));
		JRadioButton moletom = new JRadioButton("Moletom");
		moletom.setForeground(new Color(106, 143, 123));
		JRadioButton regata = new JRadioButton("Regata");
		regata.setForeground(new Color(106, 143, 123));

		ButtonGroup grupo = new ButtonGroup();
		grupo.add(camiseta);
		grupo.add(manga);
		grupo.add(moletom);
		grupo.add(regata);

		modelos.add(camiseta);
		modelos.add(manga);
		modelos.add(moletom);
		modelos.add(regata);
		painelDireito.add(modelos);

		
		JPanel cores = new JPanel();
		cores.setBackground(verde);
		cores.setBorder(new LineBorder(linha));
		cores.setLayout(new MigLayout("wrap 4", "", ""));

		cores.add(criarCor(Color.WHITE));
		cores.add(criarCor(Color.BLACK));
		cores.add(criarCor(Color.RED));
		cores.add(criarCor(Color.BLUE));
		cores.add(criarCor(new Color(26,188,156)));
		cores.add(criarCor(new Color(241,196,15)));
		cores.add(criarCor(new Color(155,89,182)));
		cores.add(criarCor(new Color(231,76,160)));

		painelDireito.add(cores);

	
		JPanel tamanhoPanel = new JPanel();
		tamanhoPanel.setBackground(verde);
		tamanhoPanel.setBorder(new LineBorder(linha));
		tamanhoPanel.setLayout(new MigLayout("wrap", "[grow]", ""));

		JLabel lblTamanho = new JLabel("Tamanho");
		lblTamanho.setForeground(Color.WHITE);

		JComboBox<String> tamanho = new JComboBox<>(new String[]{"P","M","G","GG"});
		tamanho.setForeground(new Color(106, 143, 123));
		tamanhoPanel.add(lblTamanho);
		tamanhoPanel.add(tamanho,"growx");

		painelDireito.add(tamanhoPanel);

		
		JPanel estampaPanel = new JPanel();
		estampaPanel.setBackground(verde);
		estampaPanel.setBorder(new LineBorder(linha));
		estampaPanel.setLayout(new MigLayout("wrap", "[grow]", ""));

		JLabel lblEstampa = new JLabel("Estampa");
		lblEstampa.setForeground(Color.WHITE);

		JComboBox<String> estampa = new JComboBox<>(new String[]{
				"Sem estampa",
				"Estampa florida",
				"Estampa de bolinhas"
		});
		estampa.setForeground(new Color(106, 143, 123));
		estampaPanel.add(lblEstampa);
		estampaPanel.add(estampa,"growx");

		painelDireito.add(estampaPanel);
		fundo.add(painelDireito);
	}

	private JPanel criarCor(Color cor) {

		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(80,80));
		p.setBackground(cor);
		p.setBorder(new LineBorder(Color.GRAY));

		
		p.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				previewCamisa.setBackground(cor);
			}
		});

		return p;
	}
}