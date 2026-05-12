package Visao;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Cursor;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import net.miginfocom.swing.MigLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Controle.ItensPedidoDAO;

public class TelaDetalhes extends JFrame {

	private JPanel contentPane;
	private JPanel previewCamisa;
	Color verde = new Color(106, 143, 123);
	Color linha = new Color(200, 220, 210);

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
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

		JPanel cores = new JPanel(new MigLayout("wrap 4, insets 10", "[]10[]10[]10[]", "[]10[]"));
		cores.setBackground(verde);
		cores.setBorder(new LineBorder(linha));

		Color[] cArray = {
			Color.WHITE, Color.BLACK, Color.RED, Color.BLUE,
			new Color(26, 188, 156), new Color(241, 196, 15), new Color(155, 89, 182), new Color(231, 76, 160)
		};

		for (Color c : cArray) {
			JPanel p = new JPanel();
			p.setPreferredSize(new Dimension(75, 75)); 
			p.setBackground(c);
			p.setBorder(new LineBorder(Color.LIGHT_GRAY));
			p.setCursor(new Cursor(Cursor.HAND_CURSOR));
			p.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					previewCamisa.setBackground(c);
				}
			});
			cores.add(p, "width 75!, height 75!");
		}
		painelDireito.add(cores, "gapbottom 15");

		JLabel lblTamanho = new JLabel("Tamanho");
		lblTamanho.setForeground(Color.WHITE);
		painelDireito.add(lblTamanho);

		JComboBox<String> comboTamanho = new JComboBox<>(new String[]{"P", "M", "G", "GG"});
		comboTamanho.setForeground(verde);
		painelDireito.add(comboTamanho, "h 35!, gapbottom 20");

		JButton comprar = new JButton("Adicionar ao Carrinho");
		comprar.setFont(new Font("Arial", Font.BOLD, 18));
		comprar.setForeground(verde);
		comprar.setBackground(Color.WHITE);
		comprar.addActionListener(e -> {
			String tamanho = (String) comboTamanho.getSelectedItem();
			ItensPedidoDAO dao = new ItensPedidoDAO();
			int idPedido = dao.obterPedidoAtivo();
			int idProd = dao.buscarIdProduto(nomeRoupa);
			
			double preco = 0.0;
			try {
				preco = Double.parseDouble(precoRoupa.replace("R$", "").replace(",", ".").trim());
			} catch (Exception ex) {}

			if (idPedido != -1 && idProd != -1) {
				dao.incluirItem(idPedido, idProd, 1, preco, tamanho);
				new TelaCarrinho().setVisible(true);
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Erro: Produto não encontrado no banco.");
			}
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
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.addActionListener(e -> {
			if(texto.equals("Catálogo")) { new TelaCatalogo().setVisible(true); dispose(); }
			else if(texto.equals("Início")) { new Telaprincipal().setVisible(true); dispose(); }
			else if(texto.equals("Personalizar")) { new TelaPersonalizar().setVisible(true); dispose(); }
		});
		return btn;
	}
}