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

public class TelaPersonalizar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel previewCamisa;
	private JTextField txtNomePeca;
	private JTextField txtPrecoPeca;

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
		setTitle("WeStyle - Personalizar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 900);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		// --- NAVBAR ---
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
		navbar.add(criarBotaoNav("Catálogo")); // Botão para voltar ao catálogo
		navbar.add(criarBotaoNav("Carrinho"));
		navbar.add(criarBotaoNav("Personalizar"));

		contentPane.add(navbar, BorderLayout.NORTH);

		// --- CONTEÚDO PRINCIPAL ---
		JPanel fundo = new JPanel();
		fundo.setBackground(verde);
		fundo.setLayout(new MigLayout("align center center, insets 20", "[650!][500!]", "[]"));
		contentPane.add(fundo, BorderLayout.CENTER);

		// --- PAINEL ESQUERDO (PREVIEW) ---
		JPanel previewPanel = new JPanel();
		previewPanel.setOpaque(false);
		previewPanel.setBorder(new LineBorder(linha));
		previewPanel.setLayout(new MigLayout("wrap, insets 25", "[center]", ""));

		JLabel lblPreview = new JLabel("Preview em Tempo Real");
		lblPreview.setForeground(Color.WHITE);
		lblPreview.setFont(new Font("Arial", Font.BOLD, 20));

		previewCamisa = new JPanel();
		previewCamisa.setPreferredSize(new Dimension(450, 550));
		previewCamisa.setBackground(Color.WHITE);
		previewCamisa.setBorder(new LineBorder(Color.LIGHT_GRAY));

		previewPanel.add(lblPreview);
		previewPanel.add(previewCamisa, "width 450!, height 550!");
		fundo.add(previewPanel);

		// --- PAINEL DIREITO (OPÇÕES) ---
		JPanel painelDireito = new JPanel();
		painelDireito.setOpaque(false);
		painelDireito.setLayout(new MigLayout("wrap, gap 15", "[grow,fill]", ""));

		JLabel titulo = new JLabel("Personalize Sua Peça");
		titulo.setForeground(Color.WHITE);
		titulo.setFont(new Font("Arial", Font.BOLD, 30));
		painelDireito.add(titulo);

		// 1. Modelos
		JPanel modelos = new JPanel(new MigLayout("wrap 2, insets 15", "[grow][grow]", ""));
		modelos.setBackground(verde);
		modelos.setBorder(new LineBorder(linha));
		
		JRadioButton camiseta = new JRadioButton("T-Shirt", true);
		JRadioButton manga = new JRadioButton("Oversize");
		JRadioButton moletom = new JRadioButton("Moletom");
		JRadioButton regata = new JRadioButton("Regata");
		
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(camiseta); grupo.add(manga); grupo.add(moletom); grupo.add(regata);
		modelos.add(camiseta); modelos.add(manga); modelos.add(moletom); modelos.add(regata);
		painelDireito.add(modelos);

		// 2. Cores
		JPanel cores = new JPanel(new MigLayout("wrap 4, insets 10", "[]10[]10[]10[]", "[]10[]"));
		cores.setBackground(verde);
		cores.setBorder(new LineBorder(linha));
		cores.add(criarCor(Color.WHITE));
		cores.add(criarCor(Color.BLACK));
		cores.add(criarCor(Color.RED));
		cores.add(criarCor(Color.BLUE));
		cores.add(criarCor(new Color(26, 188, 156)));
		cores.add(criarCor(new Color(241, 196, 15)));
		cores.add(criarCor(new Color(155, 89, 182)));
		cores.add(criarCor(new Color(231, 76, 160)));
		painelDireito.add(cores);

		// 3. Estampa e Tamanho
		JComboBox<String> comboTamanho = new JComboBox<>(new String[]{"P", "M", "G", "GG"});
		JComboBox<String> comboEstampa = new JComboBox<>(new String[]{"Sem estampa", "Estampa florida", "Estampa de bolinhas"});
		
		painelDireito.add(new JLabel("Tamanho") {{ setForeground(Color.WHITE); }});
		painelDireito.add(comboTamanho, "h 35!");
		painelDireito.add(new JLabel("Estampa") {{ setForeground(Color.WHITE); }});
		painelDireito.add(comboEstampa, "h 35!");

		// 4. Nome da Peça
		painelDireito.add(new JLabel("Nome da Criação") {{ setForeground(Color.WHITE); }}, "gapy 10");
		txtNomePeca = new JTextField();
		painelDireito.add(txtNomePeca, "h 35!");

		// 5. Preço da Peça
		painelDireito.add(new JLabel("Preço (R$)") {{ setForeground(Color.WHITE); }});
		txtPrecoPeca = new JTextField("99.90");
		painelDireito.add(txtPrecoPeca, "h 35!");

		// --- BOTÃO ENVIAR ---
		JButton btnEnviar = new JButton("Enviar para o Catálogo");
		btnEnviar.setBackground(Color.WHITE);
		btnEnviar.setForeground(verde);
		btnEnviar.setFont(new Font("Arial", Font.BOLD, 18));
		btnEnviar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		btnEnviar.addActionListener(e -> {
			String nome = txtNomePeca.getText().trim();
			String preco = txtPrecoPeca.getText().trim();
			
			if (nome.isEmpty() || preco.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Preencha o nome e o preço da sua peça!");
				return;
			}

			Color corEscolhida = previewCamisa.getBackground();
			String estampaEscolhida = comboEstampa.getSelectedItem().toString();

			// Salva na lista compartilhada
			DadosCompartilhados.pecasCriadas.add(
				new DadosCompartilhados.PecaPersonalizada(nome, corEscolhida, estampaEscolhida, preco)
			);

			JOptionPane.showMessageDialog(this, "Peça '" + nome + "' adicionada ao catálogo!");
			new TelaCatalogo().setVisible(true);
			dispose();
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

	private JPanel criarCor(Color cor) {
		JPanel p = new JPanel();
		p.setPreferredSize(new Dimension(60, 60));
		p.setBackground(cor);
		p.setBorder(new LineBorder(Color.LIGHT_GRAY));
		p.setCursor(new Cursor(Cursor.HAND_CURSOR));
		p.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				previewCamisa.setBackground(cor);
			}
		});
		return p;
	}
}