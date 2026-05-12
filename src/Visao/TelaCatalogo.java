package Visao;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import net.miginfocom.swing.MigLayout;
import Controle.ProdutoController;

public class TelaCatalogo extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel catalogo;
	private ProdutoController controller;

	Color verde = new Color(106, 143, 123);
	Color linha = new Color(200, 220, 210);

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				TelaCatalogo frame = new TelaCatalogo();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public TelaCatalogo() {
		this.controller = new ProdutoController();
		
		setTitle("WeStyle - Catálogo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 900);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPane = new JPanel(new BorderLayout());
		contentPane.setBackground(verde); 
		setContentPane(contentPane);

		JPanel navbar = new JPanel(new MigLayout("insets 15, fillx", "[left][grow][center][center][center][grow]", ""));
		navbar.setBackground(verde);
		navbar.setBorder(new MatteBorder(0, 0, 1, 0, linha));

		JLabel logo = new JLabel("WeStyle");
		logo.setForeground(Color.WHITE);
		logo.setFont(new Font("Arial", Font.BOLD, 30));
		navbar.add(logo);
		navbar.add(new JLabel(""), "growx");
		navbar.add(criarBotaoNav("Início"));
		navbar.add(criarBotaoNav("Personalizar"));
		navbar.add(criarBotaoNav("Pedidos"));
		contentPane.add(navbar, BorderLayout.NORTH);

		JPanel fundo = new JPanel(new MigLayout("wrap, fillx, insets 20", "[center]", "[][][grow]"));
		fundo.setBackground(verde);
		contentPane.add(fundo, BorderLayout.CENTER);

		JLabel titulo = new JLabel("Catálogo WeStyle");
		titulo.setForeground(Color.WHITE);
		titulo.setFont(new Font("Arial", Font.BOLD, 39));
		fundo.add(titulo, "gapy 10");
		
		JLabel subtitulo = new JLabel("Selecione os produtos que deseja e clique em ver detalhes");
		subtitulo.setForeground(Color.WHITE);
		fundo.add(subtitulo, "gapy 5, gapbottom 20");

		catalogo = new JPanel(new MigLayout("insets 10, gap 25, center, wrap 4", "", ""));
		catalogo.setOpaque(false);
		
		JScrollPane scroll = new JScrollPane(catalogo);
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		scroll.setBorder(null);
		fundo.add(scroll, "grow, push"); 

		renderizarProdutos();
	}

	private void renderizarProdutos() {
		catalogo.removeAll();
		List<String[]> produtos = controller.obterProdutosParaCatalogo();

		for (String[] p : produtos) {
			final String nome = p[0];
			final String preco = p[1];
			String corHex = p[2];
			
			Color corTemp;
			try {
				corTemp = Color.decode("#" + corHex);
			} catch (Exception e) {
				corTemp = Color.LIGHT_GRAY;
			}
			final Color corFinal = corTemp;

			JPanel card = new JPanel(new MigLayout("wrap, insets 15, align center", "[center]", "[]10[]5[]10[]"));
			card.setBackground(verde);
			card.setBorder(new LineBorder(linha, 1));

			JPanel preview = new JPanel();
			preview.setBackground(corFinal);
			
			JLabel lblNome = new JLabel(nome);
			lblNome.setForeground(Color.WHITE);
			lblNome.setFont(new Font("Arial", Font.BOLD, 17));
			
			JLabel lblPreco = new JLabel("R$ " + preco);
			lblPreco.setForeground(Color.WHITE);
			lblPreco.setFont(new Font("Arial", Font.PLAIN, 15));
			
			JButton btnDet = new JButton("Ver Detalhes");
			btnDet.setBackground(Color.WHITE);
			btnDet.setForeground(verde);
			btnDet.setFont(new Font("Arial", Font.BOLD, 13));
			btnDet.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btnDet.addActionListener(e -> { 
				new TelaDetalhes(nome, corFinal, preco).setVisible(true); 
				dispose(); 
			});

			card.add(preview, "width 250!, height 250!"); 
			card.add(lblNome);
			card.add(lblPreco);
			card.add(btnDet, "width 160!, height 35!");
			
			catalogo.add(card);
		}
		catalogo.revalidate();
		catalogo.repaint();
	}

	private JButton criarBotaoNav(String texto) {
		JButton b = new JButton(texto);
		b.setFont(new Font("Tahoma", Font.PLAIN, 20));
		b.setForeground(Color.WHITE);
		b.setBackground(verde);
		b.setBorder(null);
		b.setCursor(new Cursor(Cursor.HAND_CURSOR));
		b.addActionListener(e -> {
			if(texto.equals("Personalizar")) { new TelaPersonalizar().setVisible(true); dispose(); }
			else if(texto.equals("Início")) { new Telaprincipal().setVisible(true); dispose(); }
		});
		return b;
	}
}