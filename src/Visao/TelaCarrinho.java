package Visao;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import net.miginfocom.swing.MigLayout;
import Controle.ItensPedidoDAO;

public class TelaCarrinho extends JPanel {

	private static final long serialVersionUID = 1L;
	private List<String[]> itensNoCarrinho; 
	private double somaTotal = 0.0;
	private ItensPedidoDAO dao = new ItensPedidoDAO();
	private int idPedidoAtivo; 

	Color verde = new Color(106, 143, 123);

	public TelaCarrinho() {
		this.idPedidoAtivo = dao.obterPedidoAtivo();
		this.itensNoCarrinho = dao.listarItensDoCarrinho(idPedidoAtivo);

		this.setLayout(new BorderLayout());
		this.setBackground(verde);

		criarNavbar();
		criarConteudo();
	}

	public TelaCarrinho(List<String[]> itens) {
		this();
	}

	private void criarNavbar() {
		JPanel navbar = new JPanel(new MigLayout("insets 15, fillx", "[left]push[center][center][center]push[right]", ""));
		navbar.setBackground(verde);
		navbar.setBorder(new MatteBorder(0, 0, 1, 0, Color.WHITE));

		JLabel logo = new JLabel("WeStyle");
		logo.setFont(new Font("Arial", Font.BOLD, 30));
		logo.setForeground(Color.WHITE);
		navbar.add(logo);
		
		navbar.add(criarBotaoNav("Inicio"));
		navbar.add(criarBotaoNav("Catálogo"));
		navbar.add(criarBotaoNav("Personalizar"));
		navbar.add(criarBotaoNav("Perfil"));

		this.add(navbar, BorderLayout.NORTH);
	}

	private JButton criarBotaoNav(String texto) {
		JButton b = new JButton(texto);
		b.setFont(new Font("Tahoma", Font.PLAIN, 20));
		b.setForeground(Color.WHITE);
		b.setBackground(verde);
		b.setBorderPainted(false);
		b.setFocusPainted(false);
		b.setContentAreaFilled(false);
		b.setCursor(new Cursor(Cursor.HAND_CURSOR));
		b.addActionListener(e -> {
			if(texto.equals("Inicio")) {
				JanelaPrincipal.mudarTela("escolha");
			} else if(texto.equals("Catálogo")) { 
				JanelaPrincipal.mudarTela("catalogo");
			} else if(texto.equals("Personalizar")) {
				JanelaPrincipal.mudarTela("personalizar");
			} else if(texto.equals("Perfil")) {
				JanelaPrincipal.mudarTela("perfil");
			}
		});
		return b;
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

		if (itensNoCarrinho != null && !itensNoCarrinho.isEmpty()) {
			for (int i = 0; i < itensNoCarrinho.size(); i++) {
				String[] item = itensNoCarrinho.get(i);
				card.add(criarItemUI(item[0], item[1], Integer.parseInt(item[2])));
				try {
					somaTotal += Double.parseDouble(item[1]);
				} catch (Exception e) {}
			}
		} else {
			JLabel vazio = new JLabel("Seu carrinho está vazio...");
			vazio.setForeground(Color.WHITE);
			vazio.setFont(new Font("Arial", Font.ITALIC, 20));
			card.add(vazio, "align center, gapy 50");
		}

		JPanel totalPanel = new JPanel(new MigLayout("", "[grow][right]", ""));
		totalPanel.setOpaque(false);

		JLabel lblTotal = new JLabel("Total: R$ " + String.format("%.2f", somaTotal));
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setFont(new Font("Arial", Font.BOLD, 22));

		JButton btnPagamento = new JButton("Ir para o Pagamento");
		btnPagamento.setFont(new Font("Arial", Font.BOLD, 14));
		btnPagamento.setBackground(Color.WHITE);
		btnPagamento.setForeground(verde);
		btnPagamento.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		btnPagamento.addActionListener(e -> {
			if (itensNoCarrinho == null || itensNoCarrinho.isEmpty()) {
				JOptionPane.showMessageDialog(null, "O carrinho está vazio!");
			} else {
				Modelo.DadosCompartilhados.produtoSelecionado = String.valueOf(somaTotal);
				JanelaPrincipal.mudarTela("finalizar");
			}
		});

		totalPanel.add(lblTotal);
		totalPanel.add(btnPagamento, "width 220!, height 45!");

		card.add(totalPanel, "gapy 30");
		area.add(card);
		this.add(area, BorderLayout.CENTER);
	}

	private JPanel criarItemUI(String nome, String preco, int idItem) {
		JPanel item = new JPanel(new MigLayout("insets 10", "[][grow][right][right][right]", "[]"));
		item.setBorder(new MatteBorder(0, 0, 1, 0, Color.WHITE));
		item.setBackground(verde);

		JLabel nomeLabel = new JLabel(nome);
		nomeLabel.setForeground(Color.WHITE);
		nomeLabel.setFont(new Font("Arial", Font.BOLD, 16));

		JLabel precoLabel = new JLabel("R$ " + preco);
		precoLabel.setForeground(Color.WHITE);
		precoLabel.setFont(new Font("Arial", Font.BOLD, 16));

		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBackground(Color.WHITE);
		btnAlterar.setForeground(verde);
		btnAlterar.setFont(new Font("Arial", Font.BOLD, 12));
		btnAlterar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnAlterar.addActionListener(e -> {
			dao.excluirItem(idItem); 
			JanelaPrincipal.mudarTela("catalogo");
		});

		JButton btnRemover = new JButton("Remover");
		btnRemover.setBackground(Color.WHITE);
		btnRemover.setForeground(verde);
		btnRemover.setFont(new Font("Arial", Font.BOLD, 12));
		btnRemover.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnRemover.addActionListener(e -> {
			dao.excluirItem(idItem);
			JanelaPrincipal.mudarTela("carrinho");
		});

		item.add(new JLabel("👕"), "gapx 10");
		item.add(nomeLabel, "gapx 15");
		item.add(precoLabel, "gapx 15");
		item.add(btnAlterar, "width 100!, height 30!");
		item.add(btnRemover, "width 100!, height 30!, gapx 5");
        
		return item;
	}
}