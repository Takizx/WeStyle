package Visao;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import net.miginfocom.swing.MigLayout;
import Modelo.DadosCompartilhados; 

public class TelaCatalogo extends JFrame {

	private static final long serialVersionUID = 1L;
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
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPane = new JPanel(new BorderLayout());
		contentPane.setBackground(verde); 
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
		fundo.setLayout(new MigLayout("wrap, fill, insets 20", "[center]", "[][][:50:][grow][]"));
		contentPane.add(fundo, BorderLayout.CENTER);

		JLabel titulo = new JLabel("Catálogo WeStyle");
		titulo.setForeground(Color.WHITE);
		titulo.setFont(new Font("Arial", Font.BOLD, 39));
		fundo.add(titulo, "cell 0 0");
		
		JLabel subtitulo = new JLabel("Selecione os produtos que deseja e clique em ir para o carrinho");
		subtitulo.setForeground(Color.WHITE);
		fundo.add(subtitulo, "cell 0 1");

		catalogo = new JPanel();
		catalogo.setOpaque(true);
		catalogo.setBackground(verde);
		catalogo.setLayout(new MigLayout("insets 30 0 0 0, gap 20, align center, wrap 4", "[270!][270!][270!][270!]", "[]"));
		
		JScrollPane scroll = new JScrollPane(catalogo);
		scroll.setBackground(verde);
		scroll.setOpaque(true);
		scroll.getViewport().setBackground(verde);
		scroll.getViewport().setOpaque(true);
		scroll.setBorder(null);
		fundo.add(scroll, "cell 0 3, grow"); 


		JPanel card1 = new JPanel(new MigLayout("wrap, insets 10, align center", "[center]", "[]10[]5[]5[]"));
		card1.setBackground(verde);
		card1.setBorder(new LineBorder(linha));
		JPanel prev1 = new JPanel();
		Color cor1 = new Color(246, 100, 100);
		prev1.setBackground(cor1);
		JLabel nome1 = new JLabel("Sunset Vibes");
		nome1.setForeground(Color.WHITE);
		nome1.setFont(new Font("Arial", Font.BOLD, 17));
		JLabel preco1 = new JLabel("R$ 89.90");
		preco1.setForeground(Color.WHITE);
		chk1 = new JCheckBox("Selecionar");
		chk1.setBackground(verde);
		chk1.setForeground(Color.WHITE);
		JButton btnDet1 = new JButton("Ver Detalhes");
		btnDet1.setBackground(Color.WHITE);
		btnDet1.setForeground(verde);
		btnDet1.addActionListener(e -> { new TelaDetalhes("Sunset Vibes", cor1, "89.90").setVisible(true); dispose(); });
		card1.add(prev1, "width 230!, height 230!");
		card1.add(nome1);
		card1.add(preco1);
		card1.add(chk1, "split 2");
		card1.add(btnDet1);
		catalogo.add(card1);

		JPanel card2 = new JPanel(new MigLayout("wrap, insets 10, align center", "[center]", "[]10[]5[]5[]"));
		card2.setBackground(verde);
		card2.setBorder(new LineBorder(linha));
		JPanel prev2 = new JPanel();
		Color cor2 = new Color(86, 184, 177);
		prev2.setBackground(cor2);
		JLabel nome2 = new JLabel("Minimal Wave");
		nome2.setForeground(Color.WHITE);
		nome2.setFont(new Font("Arial", Font.BOLD, 17));
		JLabel preco2 = new JLabel("R$ 75.00");
		preco2.setForeground(Color.WHITE);
		chk2 = new JCheckBox("Selecionar");
		chk2.setBackground(verde);
		chk2.setForeground(Color.WHITE);
		JButton btnDet2 = new JButton("Ver Detalhes");
		btnDet2.setBackground(Color.WHITE);
		btnDet2.setForeground(verde);
		btnDet2.addActionListener(e -> { new TelaDetalhes("Minimal Wave", cor2, "75.00").setVisible(true); dispose(); });
		card2.add(prev2, "width 230!, height 230!");
		card2.add(nome2);
		card2.add(preco2);
		card2.add(chk2, "split 2");
		card2.add(btnDet2);
		catalogo.add(card2);

		JPanel card3 = new JPanel(new MigLayout("wrap, insets 10, align center", "[center]", "[]10[]5[]5[]"));
		card3.setBackground(verde);
		card3.setBorder(new LineBorder(linha));
		JPanel prev3 = new JPanel();
		Color cor3 = Color.BLACK;
		prev3.setBackground(cor3);
		JLabel nome3 = new JLabel("Urban Street");
		nome3.setForeground(Color.WHITE);
		nome3.setFont(new Font("Arial", Font.BOLD, 17));
		JLabel preco3 = new JLabel("R$ 110.00");
		preco3.setForeground(Color.WHITE);
		chk3 = new JCheckBox("Selecionar");
		chk3.setBackground(verde);
		chk3.setForeground(Color.WHITE);
		JButton btnDet3 = new JButton("Ver Detalhes");
		btnDet3.setBackground(Color.WHITE);
		btnDet3.setForeground(verde);
		btnDet3.addActionListener(e -> { new TelaDetalhes("Urban Street", cor3, "110.00").setVisible(true); dispose(); });
		card3.add(prev3, "width 230!, height 230!");
		card3.add(nome3);
		card3.add(preco3);
		card3.add(chk3, "split 2");
		card3.add(btnDet3);
		catalogo.add(card3);

		JPanel card4 = new JPanel(new MigLayout("wrap, insets 10, align center", "[center]", "[]10[]5[]5[]"));
		card4.setBackground(verde);
		card4.setBorder(new LineBorder(linha));
		JPanel prev4 = new JPanel();
		Color cor4 = new Color(155, 89, 182);
		prev4.setBackground(cor4);
		JLabel nome4 = new JLabel("Floral Dreams");
		nome4.setForeground(Color.WHITE);
		nome4.setFont(new Font("Arial", Font.BOLD, 17));
		JLabel preco4 = new JLabel("R$ 95.00");
		preco4.setForeground(Color.WHITE);
		chk4 = new JCheckBox("Selecionar");
		chk4.setBackground(verde);
		chk4.setForeground(Color.WHITE);
		JButton btnDet4 = new JButton("Ver Detalhes");
		btnDet4.setBackground(Color.WHITE);
		btnDet4.setForeground(verde);
		btnDet4.addActionListener(e -> { new TelaDetalhes("Floral Dreams", cor4, "95.00").setVisible(true); dispose(); });
		card4.add(prev4, "width 230!, height 230!");
		card4.add(nome4);
		card4.add(preco4);
		card4.add(chk4, "split 2");
		card4.add(btnDet4);
		catalogo.add(card4);

		for (int i = 0; i < DadosCompartilhados.pecasCriadas.size(); i++) {
			DadosCompartilhados.PecaPersonalizada peca = DadosCompartilhados.pecasCriadas.get(i);
			final int index = i;

			JPanel cardNovo = new JPanel(new MigLayout("wrap, insets 10, align center", "[center]", "[]10[]5[]5[]5[]"));
			cardNovo.setBackground(verde);
			cardNovo.setBorder(new LineBorder(linha));
			
			JPanel prevNovo = new JPanel();
			prevNovo.setBackground(peca.cor); 
			
			JLabel nomeNovo = new JLabel(peca.nome); 
			nomeNovo.setForeground(Color.WHITE);
			nomeNovo.setFont(new Font("Arial", Font.BOLD, 17));
			
			JLabel precoNovo = new JLabel("R$ " + peca.preco); 
			precoNovo.setForeground(Color.WHITE);
			
			JButton btnDetNovo = new JButton("Ver Detalhes");
			btnDetNovo.setBackground(Color.WHITE);
			btnDetNovo.setForeground(verde);
			btnDetNovo.addActionListener(e -> { 
				new TelaDetalhes(peca.nome, peca.cor, peca.preco).setVisible(true); 
				dispose(); 
			});

			cardNovo.add(prevNovo, "width 230!, height 230!");
			cardNovo.add(nomeNovo);
			cardNovo.add(precoNovo);

			JButton btnAlterar = new JButton("Alterar");
			btnAlterar.setBackground(Color.WHITE);
			btnAlterar.setForeground(verde);
			btnAlterar.addActionListener(e -> {
				new TelaPersonalizar(index, peca.nome, peca.cor, peca.preco).setVisible(true);
				dispose();
			});

			JButton btnExcluir = new JButton("Excluir");
			btnExcluir.setBackground(Color.WHITE);
			btnExcluir.setForeground(verde);
			btnExcluir.addActionListener(e -> {
				DadosCompartilhados.pecasCriadas.remove(index);
				DadosCompartilhados.salvarDados();
				new TelaCatalogo().setVisible(true);
				dispose();
			});

			cardNovo.add(btnDetNovo, "split 3");
			cardNovo.add(btnAlterar);
			cardNovo.add(btnExcluir);
			
			catalogo.add(cardNovo);
		}
	}

	private JButton criarBotaoNav(String texto) {
		JButton b = new JButton(texto);
		b.setFont(new Font("Tahoma", Font.PLAIN, 20));
		b.setForeground(Color.WHITE);
		b.setBackground(verde);
		b.setBorder(null);
		b.setCursor(new Cursor(Cursor.HAND_CURSOR));
		b.addActionListener(e -> {
			if(texto.equals("Personalizar")) {
				new TelaPersonalizar().setVisible(true);
				dispose();
			}
		});
		return b;
	}
}