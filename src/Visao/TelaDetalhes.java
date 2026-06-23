package Visao;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import net.miginfocom.swing.MigLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import Controle.ItensPedidoDAO;

public class TelaDetalhes extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel previewCamisa;
	private JLabel labelImagemTransparente;
	private Color corSelecionada;
	Color verde = new Color(106, 143, 123);
	Color linha = new Color(200, 220, 210);

	public TelaDetalhes(String nomeRoupa, Color corInicial, String precoRoupa, String nomeEstampa) {
		this.corSelecionada = corInicial;

		this.setLayout(new BorderLayout());
		this.setBackground(verde);

		JPanel navbar = new JPanel(new MigLayout("insets 15, fillx", "[left]push[center][center][center]push[right]", ""));
		navbar.setBackground(verde);
		navbar.setBorder(new MatteBorder(0, 0, 1, 0, linha));

		JLabel logo = new JLabel("WeStyle");
		logo.setForeground(Color.WHITE);
		logo.setFont(new Font("Arial", Font.BOLD, 30));
		navbar.add(logo);
		
		navbar.add(criarBotaoNav("Inicio"));
		navbar.add(criarBotaoNav("Personalizar"));
		navbar.add(criarBotaoNav("Carrinho"));
		navbar.add(criarBotaoNav("Perfil"));
		
		this.add(navbar, BorderLayout.NORTH);

		JPanel fundo = new JPanel(new MigLayout("align center center, insets 30", "[600!][500!]", "[]"));
		fundo.setOpaque(false);
		this.add(fundo, BorderLayout.CENTER);

		JPanel painelEsquerdo = new JPanel(new MigLayout("wrap, insets 20, center", "[center]", "[]15[]"));
		painelEsquerdo.setOpaque(false);
		painelEsquerdo.setBorder(new LineBorder(linha, 1));

		JLabel lblNomeDinamico = new JLabel(nomeRoupa); 
		lblNomeDinamico.setForeground(Color.WHITE);
		lblNomeDinamico.setFont(new Font("Arial", Font.BOLD, 28));
		painelEsquerdo.add(lblNomeDinamico);

		previewCamisa = new JPanel(new BorderLayout());
		previewCamisa.setBackground(corSelecionada);
		previewCamisa.setBorder(new LineBorder(Color.WHITE, 1));
		
		labelImagemTransparente = new JLabel("", SwingConstants.CENTER);
		carregarImagemProduto(nomeRoupa, nomeEstampa);
		previewCamisa.add(labelImagemTransparente, BorderLayout.CENTER);
		
		painelEsquerdo.add(previewCamisa, "width 480!, height 600!");
		fundo.add(painelEsquerdo);

		JPanel painelDireito = new JPanel(new MigLayout("wrap, insets 0", "[grow,fill]", "[]20[]10[]20[]20[]20[]"));
		painelDireito.setOpaque(false);

		JButton btnVoltar = new JButton("<-- Voltar para o Catálogo");
		btnVoltar.setBackground(Color.WHITE);
		btnVoltar.setForeground(verde);
		btnVoltar.setFont(new Font("Arial", Font.BOLD, 14));
		btnVoltar.addActionListener(e -> { JanelaPrincipal.mudarTela("catalogo"); });
		painelDireito.add(btnVoltar, "width 220!, height 35, align left");

		JLabel lblDetalhes = new JLabel("Detalhes da Peça");
		lblDetalhes.setForeground(Color.WHITE);
		lblDetalhes.setFont(new Font("Arial", Font.BOLD, 32));
		painelDireito.add(lblDetalhes);

		JLabel lblInstrucao = new JLabel("Escolha a cor no círculo e o tamanho desejado para sua peça");
		lblInstrucao.setForeground(Color.WHITE);
		lblInstrucao.setFont(new Font("Arial", Font.PLAIN, 14));
		painelDireito.add(lblInstrucao);

		AnelCromatico circuloCromatico = new AnelCromatico();
		painelDireito.add(circuloCromatico, "width 320!, height 320!, align center");

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
			try {
				ItensPedidoDAO dao = new ItensPedidoDAO();
				int idPed = dao.obterPedidoAtivo();
				int idProd = dao.buscarIdProduto(nomeRoupa);

				if (idPed <= 0 || idProd <= 0) {
					JOptionPane.showMessageDialog(null, "Erro: Pedido ativo (" + idPed + ") ou Produto (" + idProd + ") não encontrado.", "Erro de Identificação", JOptionPane.ERROR_MESSAGE);
					return;
				}

				String precoLimpo = precoRoupa.replace("R$", "").replace(" ", "").replace(",", ".").trim();
				double prc = Double.parseDouble(precoLimpo);
				
				String hexadecimalCor = String.format("%02x%02x%02x", corSelecionada.getRed(), corSelecionada.getGreen(), corSelecionada.getBlue());
				String detalhesItem = "Tamanho: " + comboTamanho.getSelectedItem().toString() + " | Cor: #" + hexadecimalCor;
				
				if (dao.incluirItem(idPed, idProd, 1, prc, detalhesItem)) {
					JanelaPrincipal.mudarTela("carrinho");
				} else {
					JOptionPane.showMessageDialog(null, "O banco de dados recusou a inserção do item.", "Erro de Persistência", JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Falha no processamento: " + ex.getMessage(), "Erro Interno", JOptionPane.ERROR_MESSAGE);
			}
		});
		painelDireito.add(btnAdd, "height 55!, gapy 20");

		fundo.add(painelDireito);
	}

	private void carregarImagemProduto(String nomeRoupa, String nomeEstampa) {
		try {
			String nomeFormatado = nomeRoupa.toLowerCase().replace(" ", "_");
			String caminho = "imagens/" + nomeFormatado + ".png";
			File arquivo = new File(caminho);

			if (!arquivo.exists() && nomeEstampa != null && !nomeEstampa.isEmpty()) {
				caminho = "imagens/" + nomeEstampa.toLowerCase().replace(" ", "_") + ".png";
				arquivo = new File(caminho);
			}

			if (!arquivo.exists()) {
				File pastaImagens = new File("imagens");
				if (pastaImagens.exists() && pastaImagens.isDirectory()) {
					File[] arquivos = pastaImagens.listFiles((dir, nome) -> nome.toLowerCase().endsWith(".png"));
					if (arquivos != null) {
						for (File f : arquivos) {
							String nomeSemExtensao = f.getName().toLowerCase().replace(".png", "");
							if (nomeFormatado.contains(nomeSemExtensao) || nomeSemExtensao.contains(nomeFormatado)) {
								arquivo = f;
								break;
							}
						}
					}
				}
			}

			if (!arquivo.exists()) {
				File pastaImagens = new File("imagens");
				if (pastaImagens.exists() && pastaImagens.isDirectory()) {
					File[] arquivos = pastaImagens.listFiles((dir, nome) -> nome.toLowerCase().endsWith(".png"));
					if (arquivos != null && arquivos.length > 0) {
						for (File f : arquivos) {
							if (f.getName().toLowerCase().contains("caveira") || f.getName().toLowerCase().contains("skull")) {
								arquivo = f;
								break;
							}
						}
						if (!arquivo.exists()) {
							arquivo = arquivos[0];
						}
					}
				}
			}

			BufferedImage img = ImageIO.read(arquivo);
			labelImagemTransparente.setIcon(new ImageIcon(img));
		} catch (Exception e) {
			labelImagemTransparente.setText("[ Sem Imagem PNG ]");
			labelImagemTransparente.setForeground(Color.WHITE);
			labelImagemTransparente.setFont(new Font("Arial", Font.ITALIC, 14));
		}
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
			} else if(texto.equals("Personalizar")) { 
				JanelaPrincipal.mudarTela("personalizar"); 
			} else if(texto.equals("Carrinho")) {
				JanelaPrincipal.mudarTela("carrinho");
			} else if(texto.equals("Perfil")) {
				JanelaPrincipal.mudarTela("perfil");
			}
		});
		return b;
	}

	class AnelCromatico extends JPanel {
		private static final long serialVersionUID = 1L;
		private final int tamanhoAnel = 300;
		private final int raioMaximo = tamanhoAnel / 2;
		private final int raioMinimo = tamanhoAnel / 4;

		public AnelCromatico() {
			setOpaque(false);
			setCursor(new Cursor(Cursor.HAND_CURSOR));

			MouseAdapter mouseHandler = new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) { detectarCor(e.getX(), e.getY()); }
				@Override
				public void mouseDragged(MouseEvent e) { detectarCor(e.getX(), e.getY()); }
			};
			addMouseListener(mouseHandler);
			addMouseMotionListener(mouseHandler);
		}

		private void detectarCor(int mx, int my) {
			double dx = mx - raioMaximo;
			double dy = my - raioMaximo;
			double distancia = Math.sqrt(dx * dx + dy * dy);

			if (distancia >= raioMinimo && distancia <= raioMaximo) {
				double anguloRad = Math.atan2(-dy, dx);
				double anguloGraus = Math.toDegrees(anguloRad);
				if (anguloGraus < 0) {
					anguloGraus += 360;
				}

				float hue = (float) (anguloGraus / 360.0);
				corSelecionada = Color.getHSBColor(hue, 0.85f, 0.95f);
				
				previewCamisa.setBackground(corSelecionada);
				previewCamisa.repaint();
			}
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			for (int angulo = 0; angulo < 360; angulo++) {
				float hue = (float) angulo / 360.0f;
				g2d.setColor(Color.getHSBColor(hue, 1.0f, 1.0f));
				g2d.fillArc(0, 0, tamanhoAnel, tamanhoAnel, angulo, 2);
			}

			g2d.setColor(verde);
			int mioloPosicao = raioMaximo - raioMinimo;
			int mioloTamanho = raioMinimo * 2;
			g2d.fillOval(mioloPosicao, mioloPosicao, mioloTamanho, mioloTamanho);
		}
	}
}