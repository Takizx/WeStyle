package Visao;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import net.miginfocom.swing.MigLayout;

public class TelaCarrinho extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private List<String[]> itensNoCarrinho; 
	private double somaTotal = 0.0;

	Color verde = new Color(106, 143, 123);

	public TelaCarrinho() {
		this(new ArrayList<>());
	}

	public TelaCarrinho(List<String[]> itens) {
		this.itensNoCarrinho = itens;

		setTitle("WeStyle - Carrinho");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPane = new JPanel(new BorderLayout());
		contentPane.setBackground(verde);
		setContentPane(contentPane);

		criarNavbar();
		criarConteudo();
	}

	private void criarNavbar() {
		JPanel navbar = new JPanel(new MigLayout("insets 10", "[][grow][][][][][]", "[]"));
		navbar.setBackground(verde);
		navbar.setBorder(new MatteBorder(0, 0, 1, 0, Color.WHITE));

		JLabel logo = new JLabel("WeStyle");
		logo.setFont(new Font("Arial", Font.BOLD, 22));
		logo.setForeground(Color.WHITE);
		
		navbar.add(logo, "cell 0 0, gapx 20");
		navbar.add(new JLabel(), "growx");
		
		navbar.add(criarBotaoNavbar("Início"), "cell 3 0");
		navbar.add(criarBotaoNavbar("Catálogo"), "cell 4 0");
		navbar.add(criarBotaoNavbar("Personalizar"), "cell 5 0");

		contentPane.add(navbar, BorderLayout.NORTH);
	}

	private JButton criarBotaoNavbar(String texto) {
		JButton btn = new JButton(texto);
		btn.setBackground(verde);
		btn.setForeground(Color.WHITE);
		btn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btn.setBorder(null);
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		btn.addActionListener(e -> {
			if(texto.equals("Catálogo")) {
				new TelaCatalogo().setVisible(true);
				dispose();
			}
		});
		return btn;
	}

	private void criarConteudo() {
		JPanel area = new JPanel(new MigLayout("wrap, insets 30", "[grow,fill]", ""));
		area.setBackground(verde);

		JLabel titulo = new JLabel("Carrinho de Compras");
		titulo.setForeground(Color.WHITE);
		titulo.setFont(new Font("Arial", Font.BOLD, 26));
		area.add(titulo, "gapy 10");

		JPanel card = new JPanel(new MigLayout("wrap, insets 20, gap 15", "[grow,fill]", ""));
		card.setBackground(verde);
		card.setBorder(new LineBorder(Color.WHITE, 1, true));

		somaTotal = 0; 

		if (itensNoCarrinho == null || itensNoCarrinho.isEmpty()) {
			JLabel vazio = new JLabel("Seu carrinho está vazio...");
			vazio.setForeground(Color.WHITE);
			vazio.setFont(new Font("Arial", Font.ITALIC, 20));
			vazio.setHorizontalAlignment(SwingConstants.CENTER);
			card.add(vazio, "align center, gapy 50");
		} else {
			for (String[] item : itensNoCarrinho) {
				String nomeComTamanho = item[0]; // Ex: "Sunset Vibes (M)"
				String precoStr = item[1];
				
				// Adiciona o item na interface
				card.add(criarItemUI(nomeComTamanho, precoStr));
				
				// Soma o valor
				somaTotal += Double.parseDouble(precoStr);
			}
		}

		JPanel totalPanel = new JPanel(new MigLayout("", "[grow][right]", ""));
		totalPanel.setOpaque(false);

		JLabel lblTotal = new JLabel("Total: R$ " + String.format("%.2f", somaTotal));
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setFont(new Font("Arial", Font.BOLD, 22));

		JButton btnFinalizar = new JButton("Finalizar Compra");
		btnFinalizar.setFont(new Font("Arial", Font.BOLD, 14));
		btnFinalizar.setBackground(Color.WHITE);
		btnFinalizar.setForeground(verde);
		btnFinalizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		btnFinalizar.addActionListener(e -> {
			if (itensNoCarrinho == null || itensNoCarrinho.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Adicione pelo menos um item para continuar!");
			} else {
				// Envia os itens e o valor total para a tela finalizar
				new TelaFinalizar(itensNoCarrinho, somaTotal).setVisible(true);
				dispose();
			}
		});

		totalPanel.add(lblTotal);
		totalPanel.add(btnFinalizar, "width 200!, height 45!");

		card.add(totalPanel, "gapy 30");
		area.add(card);
		contentPane.add(area, BorderLayout.CENTER);
	}

	private JPanel criarItemUI(String nome, String preco) {
		JPanel item = new JPanel(new MigLayout("insets 10", "[][grow][right]", "[]"));
		item.setBorder(new MatteBorder(0, 0, 1, 0, Color.WHITE));
		item.setBackground(verde);

		JLabel icone = new JLabel("👕"); 
		icone.setFont(new Font("Arial", Font.PLAIN, 25));

		JLabel nomeLabel = new JLabel(nome);
		nomeLabel.setForeground(Color.WHITE);
		nomeLabel.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel precoLabel = new JLabel("R$ " + preco);
		precoLabel.setForeground(Color.WHITE);
		precoLabel.setFont(new Font("Arial", Font.BOLD, 16));

		item.add(icone, "gapx 10");
		item.add(nomeLabel, "gapx 15");
		item.add(precoLabel);

		return item;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				TelaCarrinho frame = new TelaCarrinho();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}