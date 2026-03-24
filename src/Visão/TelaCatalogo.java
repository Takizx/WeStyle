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

		contentPane = new JPanel(new BorderLayout());
		setContentPane(contentPane);

		

		JPanel navbar = new JPanel();
		navbar.setBackground(verde);
		navbar.setBorder(new MatteBorder(0,0,1,0,linha));
		navbar.setLayout(new MigLayout("insets 15", "[left][grow][center][center][center][center][grow]", ""));

		JLabel logo = new JLabel("WeStyle");
		logo.setForeground(Color.WHITE);
		logo.setFont(new Font("Arial", Font.BOLD, 30));

		JButton btnInicio = new JButton("Início");
		JButton btnPersonalizar = new JButton("Personalizar");
		JButton btnCatalogo = new JButton("Catálogo");
		JButton btnPedidos = new JButton("Pedidos");

		JButton[] botoes = {btnInicio, btnPersonalizar, btnCatalogo, btnPedidos};

		for (JButton b : botoes) {
			b.setFont(new Font("Tahoma", Font.PLAIN, 25));
			b.setForeground(Color.WHITE);
			b.setBackground(verde);
			b.setBorder(null);
		}

		navbar.add(logo);
		navbar.add(new JLabel(""));
		navbar.add(btnInicio);
		navbar.add(btnPersonalizar);
		navbar.add(btnCatalogo);
		navbar.add(btnPedidos);

		contentPane.add(navbar, BorderLayout.NORTH);

		

		JPanel fundo = new JPanel();
		fundo.setBackground(verde);
		fundo.setLayout(new MigLayout(
				"wrap, align center top, gap 30",
				"[1100]",   
				"[]20[]"
		));

		contentPane.add(fundo, BorderLayout.CENTER);

		

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

		

		JPanel catalogo = new JPanel();
		catalogo.setOpaque(false);
		catalogo.setLayout(new MigLayout("wrap 3, gap 25", "[300][300][300]", ""));

		fundo.add(catalogo);

		

		JPanel card1 = new JPanel();
		card1.setBackground(verde);
		card1.setBorder(new LineBorder(linha));
		card1.setLayout(new MigLayout("wrap, insets 10", "[center]", ""));

		JPanel preview1 = new JPanel();
		preview1.setBackground(new Color(246,100,100));
		preview1.setPreferredSize(new Dimension(260,260));

		JLabel nome1 = new JLabel("Sunset Vibes");
		nome1.setForeground(Color.WHITE);

		JButton btn1 = new JButton("Ver Detalhes");

		card1.add(preview1);
		card1.add(nome1);
		card1.add(btn1);

		

		JPanel card2 = new JPanel();
		card2.setBackground(verde);
		card2.setBorder(new LineBorder(linha));
		card2.setLayout(new MigLayout("wrap, insets 10", "[center]", ""));

		JPanel preview2 = new JPanel();
		preview2.setBackground(new Color(86,184,177));
		preview2.setPreferredSize(new Dimension(260,260));

		JLabel nome2 = new JLabel("Minimal Wave");
		nome2.setForeground(Color.WHITE);

		JButton btn2 = new JButton("Ver Detalhes");

		card2.add(preview2);
		card2.add(nome2);
		card2.add(btn2);

		

		JPanel card3 = new JPanel();
		card3.setBackground(verde);
		card3.setBorder(new LineBorder(linha));
		card3.setLayout(new MigLayout("wrap, insets 10", "[center]", ""));

		JPanel preview3 = new JPanel();
		preview3.setBackground(Color.BLACK);
		preview3.setPreferredSize(new Dimension(260,260));

		JLabel nome3 = new JLabel("Urban Street");
		nome3.setForeground(Color.WHITE);

		JButton btn3 = new JButton("Ver Detalhes");

		card3.add(preview3);
		card3.add(nome3);
		card3.add(btn3);

		

		JPanel card4 = new JPanel();
		card4.setBackground(verde);
		card4.setBorder(new LineBorder(linha));
		card4.setLayout(new MigLayout("wrap, insets 10", "[center]", ""));

		JPanel preview4 = new JPanel();
		preview4.setBackground(new Color(155,89,182));
		preview4.setPreferredSize(new Dimension(260,260));

		JLabel nome4 = new JLabel("Floral Dreams");
		nome4.setForeground(Color.WHITE);

		JButton btn4 = new JButton("Ver Detalhes");

		card4.add(preview4);
		card4.add(nome4);
		card4.add(btn4);

		

		JPanel card5 = new JPanel();
		card5.setBackground(verde);
		card5.setBorder(new LineBorder(linha));
		card5.setLayout(new MigLayout("wrap, insets 10", "[center]", ""));

		JPanel preview5 = new JPanel();
		preview5.setBackground(new Color(241,196,15));
		preview5.setPreferredSize(new Dimension(260,260));

		JLabel nome5 = new JLabel("Retro Dots");
		nome5.setForeground(Color.WHITE);

		JButton btn5 = new JButton("Ver Detalhes");

		card5.add(preview5);
		card5.add(nome5);
		card5.add(btn5);

		

		JPanel card6 = new JPanel();
		card6.setBackground(verde);
		card6.setBorder(new LineBorder(linha));
		card6.setLayout(new MigLayout("wrap, insets 10", "[center]", ""));

		JPanel preview6 = new JPanel();
		preview6.setBackground(new Color(66,133,244));
		preview6.setPreferredSize(new Dimension(260,260));

		JLabel nome6 = new JLabel("Ocean Blue");
		nome6.setForeground(Color.WHITE);

		JButton btn6 = new JButton("Ver Detalhes");

		card6.add(preview6);
		card6.add(nome6);
		card6.add(btn6);

		

		catalogo.add(card1);
		catalogo.add(card2);
		catalogo.add(card3);
		catalogo.add(card4);
		catalogo.add(card5);
		catalogo.add(card6);
	}
}
