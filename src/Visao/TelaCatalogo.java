package Visao;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import net.miginfocom.swing.MigLayout;

public class TelaCatalogo extends JFrame {

	private JPanel contentPane;
	private JPanel catalogo;
	
	
	private JCheckBox chk1, chk2, chk3, chk4;

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
		navbar.setLayout(new MigLayout("insets 15", "[left][grow][center][center][center][grow]", ""));

		JLabel logo = new JLabel("WeStyle");
		logo.setForeground(Color.WHITE);
		logo.setFont(new Font("Arial", Font.BOLD, 30));
		navbar.add(logo);
		navbar.add(new JLabel(""), "grow");
		navbar.add(criarBotaoNav("Início"));
		navbar.add(criarBotaoNav("Personalizar"));
		navbar.add(criarBotaoNav("Pedidos"));
		contentPane.add(navbar, BorderLayout.NORTH);

		
		JPanel fundo = new JPanel();
		fundo.setBackground(verde);
		fundo.setLayout(new MigLayout("wrap, fill, insets 20", "[center]", "[][][grow][]"));
		contentPane.add(fundo, BorderLayout.CENTER);

		JLabel titulo = new JLabel("Catálogo WeStyle");
		titulo.setForeground(Color.WHITE);
		titulo.setFont(new Font("Arial", Font.BOLD, 39));
		fundo.add(titulo, "cell 0 0");
		
		JLabel subtitulo = new JLabel("Selecione os produtos que deseja e clique em ir para o carrinho");
		subtitulo.setForeground(Color.WHITE);
		fundo.add(subtitulo, "cell 0 1");

		
		catalogo = new JPanel();
		catalogo.setOpaque(false);
		catalogo.setLayout(new MigLayout("insets 0, gap 20, align center", "[270!][270!][270!][270!]", "[]"));
		fundo.add(catalogo, "cell 0 2, growx");

		
		JPanel card1 = new JPanel(new MigLayout("wrap, insets 10, align center", "[center]", "[]10[]5[]5[]"));
		card1.setBackground(verde);
		card1.setBorder(new LineBorder(linha));
		JPanel prev1 = new JPanel();
		prev1.setBackground(new Color(246, 100, 100));
		JLabel nome1 = new JLabel("Sunset Vibes");
		nome1.setForeground(Color.WHITE);
		nome1.setFont(new Font("Arial", Font.BOLD, 17));
		JLabel preco1 = new JLabel("R$ 89.90");
		preco1.setForeground(Color.WHITE);
		chk1 = new JCheckBox("Selecionar");
		chk1.setBackground(verde);
		chk1.setForeground(Color.WHITE);
		card1.add(prev1, "width 230!, height 230!");
		card1.add(nome1);
		card1.add(preco1);
		card1.add(chk1);
		catalogo.add(card1);

		
		JPanel card2 = new JPanel(new MigLayout("wrap, insets 10, align center", "[center]", "[]10[]5[]5[]"));
		card2.setBackground(verde);
		card2.setBorder(new LineBorder(linha));
		JPanel prev2 = new JPanel();
		prev2.setBackground(new Color(86, 184, 177));
		JLabel nome2 = new JLabel("Minimal Wave");
		nome2.setForeground(Color.WHITE);
		nome2.setFont(new Font("Arial", Font.BOLD, 17));
		JLabel preco2 = new JLabel("R$ 75.00");
		preco2.setForeground(Color.WHITE);
		chk2 = new JCheckBox("Selecionar");
		chk2.setBackground(verde);
		chk2.setForeground(Color.WHITE);
		card2.add(prev2, "width 230!, height 230!");
		card2.add(nome2);
		card2.add(preco2);
		card2.add(chk2);
		catalogo.add(card2);

		
		JPanel card3 = new JPanel(new MigLayout("wrap, insets 10, align center", "[center]", "[]10[]5[]5[]"));
		card3.setBackground(verde);
		card3.setBorder(new LineBorder(linha));
		JPanel prev3 = new JPanel();
		prev3.setBackground(Color.BLACK);
		JLabel nome3 = new JLabel("Urban Street");
		nome3.setForeground(Color.WHITE);
		nome3.setFont(new Font("Arial", Font.BOLD, 17));
		JLabel preco3 = new JLabel("R$ 110.00");
		preco3.setForeground(Color.WHITE);
		chk3 = new JCheckBox("Selecionar");
		chk3.setBackground(verde);
		chk3.setForeground(Color.WHITE);
		card3.add(prev3, "width 230!, height 230!");
		card3.add(nome3);
		card3.add(preco3);
		card3.add(chk3);
		catalogo.add(card3);

		
		JPanel card4 = new JPanel(new MigLayout("wrap, insets 10, align center", "[center]", "[]10[]5[]5[]"));
		card4.setBackground(verde);
		card4.setBorder(new LineBorder(linha));
		JPanel prev4 = new JPanel();
		prev4.setBackground(new Color(155, 89, 182));
		JLabel nome4 = new JLabel("Floral Dreams");
		nome4.setForeground(Color.WHITE);
		nome4.setFont(new Font("Arial", Font.BOLD, 17));
		JLabel preco4 = new JLabel("R$ 95.00");
		preco4.setForeground(Color.WHITE);
		chk4 = new JCheckBox("Selecionar");
		chk4.setBackground(verde);
		chk4.setForeground(Color.WHITE);
		card4.add(prev4, "width 230!, height 230!");
		card4.add(nome4);
		card4.add(preco4);
		card4.add(chk4);
		catalogo.add(card4);

		
		JButton btnIrCarrinho = new JButton("IR PARA O CARRINHO");
		btnIrCarrinho.setFont(new Font("Arial", Font.BOLD, 18));
		btnIrCarrinho.setBackground(Color.WHITE);
		btnIrCarrinho.setForeground(verde);
		btnIrCarrinho.setPreferredSize(new Dimension(300, 50));
		
		btnIrCarrinho.addActionListener(e -> {
			List<String[]> selecionados = new ArrayList<>();

			
			if (chk1.isSelected()) selecionados.add(new String[]{"Sunset Vibes", "89.90"});
			if (chk2.isSelected()) selecionados.add(new String[]{"Minimal Wave", "75.00"});
			if (chk3.isSelected()) selecionados.add(new String[]{"Urban Street", "110.00"});
			if (chk4.isSelected()) selecionados.add(new String[]{"Floral Dreams", "95.00"});

		
			new TelaCarrinho(selecionados).setVisible(true);
			dispose();
		});
		
		fundo.add(btnIrCarrinho, "cell 0 3, gapy 20, align center");
	}

	private JButton criarBotaoNav(String texto) {
		JButton b = new JButton(texto);
		b.setFont(new Font("Tahoma", Font.PLAIN, 20));
		b.setForeground(Color.WHITE);
		b.setBackground(verde);
		b.setBorder(null);
		return b;
	}
}