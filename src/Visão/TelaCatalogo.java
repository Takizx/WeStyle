package Visão;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import net.miginfocom.swing.MigLayout;

public class TelaCatalogo extends JFrame {

	private JPanel contentPane;

	Color verde = new Color(106,143,123);
	Color linha = new Color(200,220,210);

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

		setTitle("WeStyle - Catálogo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,1400,900);

		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		/* NAVBAR */

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

		/* FUNDO */

		JPanel fundo = new JPanel();
		fundo.setBackground(verde);
		fundo.setLayout(new MigLayout("wrap, align center top, gap 30", "[1100]", "[]20[]"));

		contentPane.add(fundo, BorderLayout.CENTER);

		/* TITULO */

		JLabel titulo = new JLabel("Catálogo WeStyle");
		titulo.setForeground(Color.WHITE);
		titulo.setFont(new Font("Arial", Font.BOLD, 32));

		JLabel subtitulo = new JLabel("Explore nossos designs disponíveis");
		subtitulo.setForeground(Color.WHITE);

		JPanel tituloPanel = new JPanel(new MigLayout("wrap"));
		tituloPanel.setOpaque(false);
		tituloPanel.add(titulo);
		tituloPanel.add(subtitulo);

		fundo.add(tituloPanel);

		/* GRID DO CATALOGO */

		JPanel catalogo = new JPanel();
		catalogo.setOpaque(false);
		catalogo.setLayout(new MigLayout("wrap 3, gap 25", "[grow][grow][grow]", ""));

		catalogo.add(criarCard("Sunset Vibes", new Color(246,100,100)));
		catalogo.add(criarCard("Minimal Wave", new Color(86,184,177)));
		catalogo.add(criarCard("Urban Street", Color.BLACK));
		catalogo.add(criarCard("Floral Dreams", new Color(155,89,182)));
		catalogo.add(criarCard("Retro Dots", new Color(241,196,15)));
		catalogo.add(criarCard("Ocean Blue", new Color(66,133,244)));

		fundo.add(catalogo);
	}

	private JButton criarBotao(String texto) {

		JButton btn = new JButton(texto);
		btn.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btn.setForeground(Color.WHITE);
		btn.setBackground(new Color(106,143,123));
		btn.setBorder(null);

		return btn;
	}

	private JPanel criarCard(String nome, Color cor) {

		JPanel card = new JPanel();
		card.setBackground(verde);
		card.setBorder(new LineBorder(linha));
		card.setLayout(new MigLayout("wrap, insets 10", "[center]", ""));

		JPanel preview = new JPanel();
		preview.setBackground(cor);
		preview.setPreferredSize(new Dimension(300,300));
		preview.setBorder(new LineBorder(Color.DARK_GRAY));

		JLabel titulo = new JLabel(nome);
		titulo.setForeground(Color.WHITE);
		titulo.setFont(new Font("Arial", Font.BOLD, 18));

		JButton detalhes = new JButton("Ver Detalhes");

		/* ABRIR TELA DETALHES */

		detalhes.addActionListener(e -> {
			new TelaDetalhes(nome).setVisible(true);
			dispose();
		});

		card.add(preview);
		card.add(titulo);
		card.add(detalhes);

		return card;
	}
}