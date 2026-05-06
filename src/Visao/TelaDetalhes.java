package Visao;

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
import java.util.ArrayList;
import java.util.List;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import net.miginfocom.swing.MigLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaDetalhes extends JFrame {

	private JPanel contentPane;
	private JPanel previewCamisa;

	Color verde = new Color(106, 143, 123);
	Color linha = new Color(200, 220, 210);

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				// Exemplo de teste
				TelaDetalhes frame = new TelaDetalhes("Camisa Exemplo", Color.WHITE, "59.90");
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public TelaDetalhes(String nomeRoupa, Color corInicial, String precoRoupa) {

		setTitle("WeStyle - Detalhes: " + nomeRoupa);
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
		navbar.add(new JLabel(""), "grow");
		navbar.add(criarBotaoNav("Início"));
		navbar.add(criarBotaoNav("Personalizar"));
		navbar.add(criarBotaoNav("Catálogo"));
		navbar.add(criarBotaoNav("Pedidos"));
		navbar.add(new JLabel(""), "grow");

		contentPane.add(navbar, BorderLayout.NORTH);

		JPanel fundo = new JPanel();
		fundo.setBackground(verde);
		fundo.setLayout(new MigLayout("align center center, insets 20", "[650!][500!]", "[]"));
		contentPane.add(fundo, BorderLayout.CENTER);

		JPanel previewPanel = new JPanel();
		previewPanel.setOpaque(false);
		previewPanel.setBorder(new LineBorder(linha));
		previewPanel.setLayout(new MigLayout("wrap, insets 25", "[center]", ""));

		JLabel lblNomeRoupa = new JLabel(nomeRoupa);
		lblNomeRoupa.setForeground(Color.WHITE);
		lblNomeRoupa.setFont(new Font("Arial", Font.BOLD, 26));

		previewCamisa = new JPanel();
		previewCamisa.setPreferredSize(new Dimension(450, 550));
		previewCamisa.setBackground(corInicial); 
		previewCamisa.setBorder(new LineBorder(Color.LIGHT_GRAY));

		previewPanel.add(lblNomeRoupa);
		previewPanel.add(previewCamisa, "width 450!, height 550!");
		fundo.add(previewPanel);

		JPanel painelDireito = new JPanel();
		painelDireito.setOpaque(false);
		painelDireito.setLayout(new MigLayout("wrap, insets 0", "[grow,fill]", ""));

		JButton voltar = new JButton("<-- Voltar para o Catálogo");
		voltar.setBackground(Color.WHITE);
		voltar.setForeground(verde);
		voltar.setFont(new Font("Arial", Font.BOLD, 16));
		voltar.addActionListener(e -> {
			new TelaCatalogo().setVisible(true);
			dispose();
		});
		painelDireito.add(voltar, "width 250!, gapbottom 10");

		JLabel titulo = new JLabel("Detalhes da Peça");
		titulo.setForeground(Color.WHITE);
		titulo.setFont(new Font("Arial", Font.BOLD, 30));
		painelDireito.add(titulo);

		JLabel descricao = new JLabel("Escolha a cor desejada:");
		descricao.setForeground(Color.WHITE);
		painelDireito.add(descricao, "gapbottom 5");


		JPanel cores = new JPanel(new MigLayout("wrap 4, insets 10, gap 10", "[60!]10[60!]10[60!]10[60!]", "[]10[]"));
		cores.setBackground(verde);
		cores.setBorder(new LineBorder(linha));

		// Cores (Mantidas conforme seu design)
		JPanel p1 = new JPanel(); p1.setPreferredSize(new Dimension(60, 60)); p1.setBackground(Color.WHITE); p1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		p1.addMouseListener(new MouseAdapter() { public void mouseClicked(MouseEvent e) { previewCamisa.setBackground(Color.WHITE); }});
		cores.add(p1, "width 60!, height 60!");

		JPanel p2 = new JPanel(); p2.setPreferredSize(new Dimension(60, 60)); p2.setBackground(Color.BLACK); p2.setBorder(new LineBorder(Color.LIGHT_GRAY));
		p2.addMouseListener(new MouseAdapter() { public void mouseClicked(MouseEvent e) { previewCamisa.setBackground(Color.BLACK); }});
		cores.add(p2, "width 60!, height 60!");

		JPanel p3 = new JPanel(); p3.setPreferredSize(new Dimension(60, 60)); p3.setBackground(Color.RED); p3.setBorder(new LineBorder(Color.LIGHT_GRAY));
		p3.addMouseListener(new MouseAdapter() { public void mouseClicked(MouseEvent e) { previewCamisa.setBackground(Color.RED); }});
		cores.add(p3, "width 60!, height 60!");

		JPanel p4 = new JPanel(); p4.setPreferredSize(new Dimension(60, 60)); p4.setBackground(Color.BLUE); p4.setBorder(new LineBorder(Color.LIGHT_GRAY));
		p4.addMouseListener(new MouseAdapter() { public void mouseClicked(MouseEvent e) { previewCamisa.setBackground(Color.BLUE); }});
		cores.add(p4, "width 60!, height 60!");

		JPanel p5 = new JPanel(); p5.setPreferredSize(new Dimension(60, 60)); p5.setBackground(new Color(26, 188, 156)); p5.setBorder(new LineBorder(Color.LIGHT_GRAY));
		p5.addMouseListener(new MouseAdapter() { public void mouseClicked(MouseEvent e) { previewCamisa.setBackground(new Color(26, 188, 156)); }});
		cores.add(p5, "width 60!, height 60!");

		JPanel p6 = new JPanel(); p6.setPreferredSize(new Dimension(60, 60)); p6.setBackground(new Color(241, 196, 15)); p6.setBorder(new LineBorder(Color.LIGHT_GRAY));
		p6.addMouseListener(new MouseAdapter() { public void mouseClicked(MouseEvent e) { previewCamisa.setBackground(new Color(241, 196, 15)); }});
		cores.add(p6, "width 60!, height 60!");

		JPanel p7 = new JPanel(); p7.setPreferredSize(new Dimension(60, 60)); p7.setBackground(new Color(155, 89, 182)); p7.setBorder(new LineBorder(Color.LIGHT_GRAY));
		p7.addMouseListener(new MouseAdapter() { public void mouseClicked(MouseEvent e) { previewCamisa.setBackground(new Color(155, 89, 182)); }});
		cores.add(p7, "width 60!, height 60!");

		JPanel p8 = new JPanel(); p8.setPreferredSize(new Dimension(60, 60)); p8.setBackground(new Color(231, 76, 160)); p8.setBorder(new LineBorder(Color.LIGHT_GRAY));
		p8.addMouseListener(new MouseAdapter() { public void mouseClicked(MouseEvent e) { previewCamisa.setBackground(new Color(231, 76, 160)); }});
		cores.add(p8, "width 60!, height 60!");

		painelDireito.add(cores, "width 290!, alignx left, gapbottom 15");

		JLabel lblTamanho = new JLabel("Tamanho");
		lblTamanho.setForeground(Color.WHITE);
		painelDireito.add(lblTamanho);

		JComboBox<String> comboTamanho = new JComboBox<>(new String[]{"P", "M", "G", "GG"});
		comboTamanho.setForeground(verde);
		painelDireito.add(comboTamanho, "h 35!, gapbottom 20");

		// --- LÓGICA DO BOTÃO ADICIONAR AO CARRINHO ---
		JButton comprar = new JButton("Adicionar ao Carrinho");
		comprar.setFont(new Font("Arial", Font.BOLD, 18));
		comprar.setForeground(verde);
		comprar.setBackground(Color.WHITE);
		comprar.addActionListener(e -> {
			String tamanho = (String) comboTamanho.getSelectedItem();
			
			// Criamos a lista que a TelaCarrinho espera: [Nome + Tamanho, Preço]
			List<String[]> listaCarrinho = new ArrayList<>();
			listaCarrinho.add(new String[] { nomeRoupa + " (" + tamanho + ")", precoRoupa });
			
			// Abre a tela do carrinho passando a lista
			new TelaCarrinho(listaCarrinho).setVisible(true);
			dispose();
		});
		painelDireito.add(comprar, "h 50!");

		fundo.add(painelDireito);
	}

	private JButton criarBotaoNav(String texto) {
		JButton btn = new JButton(texto);
		btn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn.setForeground(Color.WHITE);
		btn.setBackground(verde);
		btn.setBorder(null);
		btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btn.addActionListener(e -> {
			if(texto.equals("Catálogo")) { new TelaCatalogo().setVisible(true); dispose(); }
			else if(texto.equals("Início")) { new Telaprincipal().setVisible(true); dispose(); }
			else if(texto.equals("Personalizar")) { new TelaPersonalizar().setVisible(true); dispose(); }
		});
		return btn;
	}
}