package Visão;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import net.miginfocom.swing.MigLayout;

public class TelaCatalogo extends JFrame {

	private JPanel contentPane;
	private JPanel catalogo;

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
		setTitle("WeStyle - Catálogo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 900);

		contentPane = new JPanel(new BorderLayout());
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
		contentPane.add(navbar, BorderLayout.NORTH);

		
		JPanel fundo = new JPanel();
		fundo.setBackground(verde);
		
		fundo.setLayout(new MigLayout("wrap, fill, insets 40 0 40 0", "[center]", "[][][][grow]"));
		contentPane.add(fundo, BorderLayout.CENTER);

		
		JPanel tituloPanel = new JPanel(new MigLayout("wrap, align center"));
		tituloPanel.setOpaque(false);
		
		fundo.add(tituloPanel, "cell 0 0,aligny top");
		JLabel titulo = new JLabel("Catálogo WeStyle");
		fundo.add(titulo, "cell 0 1");
		titulo.setForeground(Color.WHITE);
		titulo.setFont(new Font("Arial", Font.BOLD, 39));
		JLabel subtitulo = new JLabel("Explore nossos designs disponíveis");
		fundo.add(subtitulo, "cell 0 2");
		subtitulo.setForeground(Color.WHITE);

		
		catalogo = new JPanel();
		catalogo.setOpaque(false);
		catalogo.setLayout(new MigLayout("insets 0, gap 20, align center", "[270!][270!][270!][270!]", "[]"));
		
		fundo.add(catalogo, "cell 0 3,pushy,aligny center");

	
		JPanel card1 = new JPanel(new MigLayout("wrap, insets 10, align center", "[center]", "[]10[]10[]"));
		card1.setBackground(verde);
		card1.setBorder(new LineBorder(linha));
		JPanel prev1 = new JPanel();
		prev1.setBackground(new Color(246, 100, 100));
		prev1.setPreferredSize(new Dimension(250, 250));
		JLabel nome1 = new JLabel("Sunset Vibes");
		nome1.setForeground(Color.WHITE);
		nome1.setFont(new Font("Arial", Font.BOLD, 17));
		JButton btn1 = new JButton("Ver Detalhes");
		btn1.addActionListener(e -> abrirDetalhes("Sunset Vibes"));
		card1.add(prev1, "width 250!, height 250!");
		card1.add(nome1);
		card1.add(btn1);
		catalogo.add(card1);

		
		JPanel card2 = new JPanel(new MigLayout("wrap, insets 10, align center", "[center]", "[]10[]10[]"));
		card2.setBackground(verde);
		card2.setBorder(new LineBorder(linha));
		JPanel prev2 = new JPanel();
		prev2.setBackground(new Color(86, 184, 177));
		prev2.setPreferredSize(new Dimension(250, 250));
		JLabel nome2 = new JLabel("Minimal Wave");
		nome2.setForeground(Color.WHITE);
		nome2.setFont(new Font("Arial", Font.BOLD, 17));
		JButton btn2 = new JButton("Ver Detalhes");
		btn2.addActionListener(e -> abrirDetalhes("Minimal Wave"));
		card2.add(prev2, "width 250!, height 250!");
		card2.add(nome2);
		card2.add(btn2);
		catalogo.add(card2);

		
		JPanel card3 = new JPanel(new MigLayout("wrap, insets 10, align center", "[center]", "[]10[]10[]"));
		card3.setBackground(verde);
		card3.setBorder(new LineBorder(linha));
		JPanel prev3 = new JPanel();
		prev3.setBackground(Color.BLACK);
		prev3.setPreferredSize(new Dimension(250, 250));
		JLabel nome3 = new JLabel("Urban Street");
		nome3.setForeground(Color.WHITE);
		nome3.setFont(new Font("Arial", Font.BOLD, 17));
		JButton btn3 = new JButton("Ver Detalhes");
		btn3.addActionListener(e -> abrirDetalhes("Urban Street"));
		card3.add(prev3, "width 240!, height 240!");
		card3.add(nome3);
		card3.add(btn3);
		catalogo.add(card3);


		JPanel card4 = new JPanel(new MigLayout("wrap, insets 10, align center", "[center]", "[]10[]10[]"));
		card4.setBackground(verde);
		card4.setBorder(new LineBorder(linha));
		JPanel prev4 = new JPanel();
		prev4.setBackground(new Color(155, 89, 182));
		prev4.setPreferredSize(new Dimension(250, 250));
		JLabel nome4 = new JLabel("Floral Dreams");
		nome4.setForeground(Color.WHITE);
		nome4.setFont(new Font("Arial", Font.BOLD, 17));
		JButton btn4 = new JButton("Ver Detalhes");
		btn4.addActionListener(e -> abrirDetalhes("Floral Dreams"));
		card4.add(prev4, "width 250!, height 250!");
		card4.add(nome4);
		card4.add(btn4);
		catalogo.add(card4);
	}

	private void abrirDetalhes(String nomeProduto) {
		new TelaDetalhes(nomeProduto).setVisible(true);
		this.dispose();
	}

	private JButton criarBotaoNav(String texto) {
		JButton b = new JButton(texto);
		b.setFont(new Font("Tahoma", Font.PLAIN, 25));
		b.setForeground(Color.WHITE);
		b.setBackground(verde);
		b.setBorder(null);
		return b;
	}
}