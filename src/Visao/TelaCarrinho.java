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
		JPanel navbar = new JPanel(new MigLayout("insets 10", "[][grow][][][][]", "[]"));
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
			} else if(texto.equals("Personalizar")) {
				new TelaPersonalizar().setVisible(true);
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
			for (int i = 0; i < itensNoCarrinho.size(); i++) {
				String[] item = itensNoCarrinho.get(i);
				card.add(criarItemUI(item[0], item[1], i));
				
				try {
					String precoLimpo = item[1].replace("R$", "").replace(",", ".").trim();
					if (!precoLimpo.isEmpty()) {
						somaTotal += Double.parseDouble(precoLimpo);
					}
				} catch (Exception e) {
					System.err.println("Erro ao converter preco do item: " + item[1]);
				}
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

	private JPanel criarItemUI(String nome, String preco, int index) {
		JPanel item = new JPanel(new MigLayout("insets 10", "[][grow][right][right]", "[]"));
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

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBackground(Color.WHITE);
		btnAlterar.setForeground(verde);
		btnAlterar.addActionListener(e -> {
			new TelaCatalogo().setVisible(true);
			dispose();
		});

		JButton btnRemover = new JButton("Remover");
		btnRemover.setBackground(Color.WHITE);
		btnRemover.setForeground(verde);
		btnRemover.addActionListener(e -> {
			itensNoCarrinho.remove(index);
			new TelaCarrinho(itensNoCarrinho).setVisible(true);
			dispose();
		});

		item.add(icone, "gapx 10");
		item.add(nomeLabel, "gapx 15");
		item.add(precoLabel, "gapx 15");
		item.add(btnAlterar);
		item.add(btnRemover);

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