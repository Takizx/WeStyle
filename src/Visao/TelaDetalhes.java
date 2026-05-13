package Visao;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import net.miginfocom.swing.MigLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Controle.ItensPedidoDAO;

public class TelaDetalhes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel previewCamisa;
	Color verde = new Color(106, 143, 123);
	Color linha = new Color(200, 220, 210);

	public TelaDetalhes(String nomeRoupa, Color corInicial, String precoRoupa) {
		setTitle("WeStyle - Detalhes: " + nomeRoupa);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 900);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPane = new JPanel(new BorderLayout());
		contentPane.setBackground(verde);
		setContentPane(contentPane);

		JPanel navbar = new JPanel(new MigLayout("insets 15, fillx", "[left][grow][center][center][center][center][grow]", ""));
		navbar.setBackground(verde);
		navbar.setBorder(new MatteBorder(0, 0, 1, 0, linha));

		JLabel logo = new JLabel("WeStyle");
		logo.setForeground(Color.WHITE);
		logo.setFont(new Font("Arial", Font.BOLD, 30));
		navbar.add(logo);
		navbar.add(new JLabel(""), "growx");
		
		navbar.add(criarBotaoNav("Início"));
		navbar.add(criarBotaoNav("Catálogo"));
		navbar.add(criarBotaoNav("Carrinho"));
		navbar.add(criarBotaoNav("Personalizar"));
		navbar.add(new JLabel(""), "growx");
		
		contentPane.add(navbar, BorderLayout.NORTH);

		JPanel fundo = new JPanel(new MigLayout("align center center, insets 30", "[600!][500!]", "[]"));
		fundo.setOpaque(false);
		contentPane.add(fundo, BorderLayout.CENTER);

		JPanel painelEsquerdo = new JPanel(new MigLayout("wrap, insets 20, center", "[center]", "[]15[]"));
		painelEsquerdo.setOpaque(false);
		painelEsquerdo.setBorder(new LineBorder(linha, 1));

		JLabel lblNomeDinamico = new JLabel(nomeRoupa); 
		lblNomeDinamico.setForeground(Color.WHITE);
		lblNomeDinamico.setFont(new Font("Arial", Font.BOLD, 28));
		painelEsquerdo.add(lblNomeDinamico);

		previewCamisa = new JPanel();
		previewCamisa.setBackground(corInicial);
		previewCamisa.setBorder(new LineBorder(Color.WHITE, 1));
		painelEsquerdo.add(previewCamisa, "width 480!, height 600!");
		
		fundo.add(painelEsquerdo);

		JPanel painelDireito = new JPanel(new MigLayout("wrap, insets 0", "[grow,fill]", "[]20[]10[]20[]20[]20[]"));
		painelDireito.setOpaque(false);

		JButton btnVoltar = new JButton("<-- Voltar para o Catálogo");
		btnVoltar.setBackground(Color.WHITE);
		btnVoltar.setForeground(verde);
		btnVoltar.setFont(new Font("Arial", Font.BOLD, 14));
		btnVoltar.addActionListener(e -> { new TelaCatalogo().setVisible(true); dispose(); });
		painelDireito.add(btnVoltar, "width 220!, height 35, align left");

		JLabel lblDetalhes = new JLabel("Detalhes da Peça");
		lblDetalhes.setForeground(Color.WHITE);
		lblDetalhes.setFont(new Font("Arial", Font.BOLD, 32));
		painelDireito.add(lblDetalhes);

		JLabel lblInstrucao = new JLabel("Escolha a cor e o tamanho desejado para sua peça");
		lblInstrucao.setForeground(Color.WHITE);
		lblInstrucao.setFont(new Font("Arial", Font.PLAIN, 14));
		painelDireito.add(lblInstrucao);

		JPanel painelCores = new JPanel(new MigLayout("wrap 4, insets 10, gap 10", "[]", "[]"));
		painelCores.setOpaque(false);
		painelCores.setBorder(new LineBorder(linha, 1));

		Color[] coresDisponiveis = {
			Color.WHITE, Color.BLACK, Color.RED, Color.BLUE,
			new Color(26, 188, 156), new Color(241, 196, 15), new Color(155, 89, 182), new Color(231, 76, 160)
		};

		for (Color c : coresDisponiveis) {
			JPanel p = new JPanel();
			p.setBackground(c);
			p.setBorder(new LineBorder(Color.GRAY, 1));
			p.setCursor(new Cursor(Cursor.HAND_CURSOR));
			p.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) { previewCamisa.setBackground(c); }
			});
			painelCores.add(p, "width 80!, height 80!");
		}
		painelDireito.add(painelCores);

		JLabel lblTamanho = new JLabel("Tamanho Selecionado:");
		lblTamanho.setForeground(Color.WHITE);
		lblTamanho.setFont(new Font("Arial", Font.BOLD, 14));
		painelDireito.add(lblTamanho);

		JComboBox<String> comboTamanho = new JComboBox<>(new String[]{"P", "M", "G", "GG"});
		comboTamanho.setFont(new Font("Arial", Font.PLAIN, 16));
		painelDireito.add(comboTamanho, "height 45!");

		JButton btnAdd = new JButton("Adicionar ao Carrinho");
		btnAdd.setBackground(Color.WHITE);
		btnAdd.setForeground(verde);
		btnAdd.setFont(new Font("Arial", Font.BOLD, 18));
		btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnAdd.addActionListener(e -> {
			ItensPedidoDAO dao = new ItensPedidoDAO();
			int idPed = dao.obterPedidoAtivo();
			int idProd = dao.buscarIdProduto(nomeRoupa);
			try {
				double prc = Double.parseDouble(precoRoupa);
				dao.incluirItem(idPed, idProd, 1, prc, (String)comboTamanho.getSelectedItem());
				new TelaCarrinho().setVisible(true);
				dispose();
			} catch (Exception ex) {
				new TelaMensagem("Erro ao processar preço ou adicionar item.", "erro");
			}
		});
		painelDireito.add(btnAdd, "height 55!, gapy 20");

		fundo.add(painelDireito);
	}

	private JButton criarBotaoNav(String texto) {
		JButton b = new JButton(texto);
		b.setFont(new Font("Tahoma", Font.PLAIN, 18));
		b.setForeground(Color.WHITE);
		b.setBackground(verde);
		b.setBorder(null);
		b.setCursor(new Cursor(Cursor.HAND_CURSOR));
		b.addActionListener(e -> {
			if(texto.equals("Personalizar")) { new TelaPersonalizar().setVisible(true); dispose(); }
			else if(texto.equals("Catálogo")) { new TelaCatalogo().setVisible(true); dispose(); }
			else if(texto.equals("Início")) { new Telaprincipal().setVisible(true); dispose(); }
			else if(texto.equals("Carrinho")) { new TelaCarrinho().setVisible(true); dispose(); }
		});
		return b;
	}
}