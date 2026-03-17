package Visão;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.BorderLayout;

import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import net.miginfocom.swing.MigLayout;

public class TelaDetalhes extends JFrame {

	private JPanel contentPane;
	private JPanel previewCamisa;

	Color verde = new Color(106,143,123);
	Color linha = new Color(200,220,210);

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				TelaDetalhes frame = new TelaDetalhes("Preview");
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public TelaDetalhes(String nomeRoupa) {

		setTitle("WeStyle - Detalhes");
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

		JButton btnInicio = criarBotao("Início");
		JButton btnPersonalizar = criarBotao("Personalizar");
		JButton btnCatalogo = criarBotao("Catálogo");
		JButton btnPedidos = criarBotao("Pedidos");

		navbar.add(logo);
		navbar.add(new JLabel(""));
		navbar.add(btnInicio);
		navbar.add(btnPersonalizar);
		navbar.add(btnCatalogo);
		navbar.add(btnPedidos);

		contentPane.add(navbar, BorderLayout.NORTH);

		
		JPanel fundo = new JPanel();
		fundo.setBackground(verde);
		fundo.setLayout(new MigLayout("align center center", "[650][650]", ""));
		contentPane.add(fundo, BorderLayout.CENTER);

		
		JPanel previewPanel = new JPanel();
		previewPanel.setBackground(verde);
		previewPanel.setBorder(new LineBorder(linha));
		previewPanel.setLayout(new MigLayout("wrap, insets 25", "[center]", ""));

		JLabel lblNomeRoupa = new JLabel(nomeRoupa);
		lblNomeRoupa.setForeground(Color.WHITE);
		lblNomeRoupa.setFont(new Font("Arial", Font.BOLD, 26));

		previewCamisa = new JPanel();
		previewCamisa.setPreferredSize(new Dimension(500,600));
		previewCamisa.setBackground(Color.WHITE);
		previewCamisa.setBorder(new LineBorder(Color.LIGHT_GRAY));

		previewPanel.add(lblNomeRoupa);
		previewPanel.add(previewCamisa);

		fundo.add(previewPanel);

		
		JPanel painelDireito = new JPanel();
		painelDireito.setOpaque(false);
		painelDireito.setLayout(new MigLayout("wrap, gap 25", "[grow,fill]", ""));

		
		JButton voltar = new JButton("<-- Voltar");
		voltar.setBackground(new Color(255, 255, 255));
		voltar.setForeground(new Color(106, 143, 123));
		voltar.setFont(new Font("Arial", Font.BOLD, 16));
		
		painelDireito.add(voltar);

		JLabel titulo = new JLabel("Detalhes da Peça");
		titulo.setForeground(Color.WHITE);
		titulo.setFont(new Font("Arial", Font.BOLD, 30));

		JLabel descricao = new JLabel("Escolha a cor e o tamanho desejado");
		descricao.setForeground(Color.WHITE);

		painelDireito.add(titulo);
		painelDireito.add(descricao);

		
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
		tamanho.setBackground(new Color(255, 255, 255));
		tamanhoPanel.add(lblTamanho);
		tamanhoPanel.add(tamanho,"growx");

		painelDireito.add(tamanhoPanel);

		
		JButton comprar = new JButton("Adicionar ao Carrinho");
		comprar.setForeground(new Color(106, 143, 123));
		comprar.setBackground(new Color(255, 255, 255));
		
		painelDireito.add(comprar);

		fundo.add(painelDireito);
	}

	private JButton criarBotao(String texto) {
		JButton btn = new JButton(texto);
		btn.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btn.setForeground(Color.WHITE);
		btn.setBackground(new Color(106,143,123));
		btn.setBorder(null);
		
		return btn;
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