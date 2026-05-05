package Visao;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Files;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import net.miginfocom.swing.MigLayout;
import Controle.EstampaDAO; 

public class TelaEstampa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNomeEstampa;
	private JTextField txtCaminhoArquivo;
	
	Color verdeWeStyle = new Color(106, 143, 123);
	Color linha = new Color(200, 220, 210);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEstampa frame = new TelaEstampa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TelaEstampa() {
		setTitle("WeStyle - Cadastrar Estampa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 750);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		contentPane = new JPanel();
		contentPane.setBackground(verdeWeStyle);
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		JPanel navbar = new JPanel();
		navbar.setBackground(verdeWeStyle);
		navbar.setBorder(new MatteBorder(0, 0, 1, 0, linha));
		navbar.setLayout(new MigLayout("insets 15", "[left][grow][center][grow]", ""));

		JLabel logo = new JLabel("WeStyle");
		logo.setForeground(Color.WHITE);
		logo.setFont(new Font("Arial", Font.BOLD, 30));
		navbar.add(logo);
		navbar.add(new JLabel(""), "growx");
		
		JButton btnVoltarNav = new JButton("Voltar ao Personalizar");
		btnVoltarNav.setForeground(Color.WHITE);
		btnVoltarNav.setBackground(verdeWeStyle);
		btnVoltarNav.setBorder(null);
		btnVoltarNav.setFont(new Font("Arial", Font.PLAIN, 18));
		btnVoltarNav.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnVoltarNav.addActionListener(e -> {
			new TelaPersonalizar().setVisible(true);
			dispose();
		});
		navbar.add(btnVoltarNav);
		navbar.add(new JLabel(""), "growx");
		
		contentPane.add(navbar, BorderLayout.NORTH);

		JPanel fundo = new JPanel();
		fundo.setBackground(verdeWeStyle);
		fundo.setLayout(new MigLayout("fill, align center center", "[center]", "[center]"));
		contentPane.add(fundo, BorderLayout.CENTER);

		JPanel card = new JPanel();
		card.setBackground(Color.WHITE);
		card.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		card.setLayout(new MigLayout("wrap, insets 40, gap 15", "[grow,fill]", "[]10[]20[]5[]20[]5[]30[]"));
		fundo.add(card, "w 500!, h 550!");

		JLabel lblTitulo = new JLabel("Nova Estampa", SwingConstants.CENTER);
		lblTitulo.setForeground(verdeWeStyle);
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 26));
		card.add(lblTitulo, "alignx center");

		JLabel lblSub = new JLabel("Envie sua arte para personalizar sua peça");
		lblSub.setFont(new Font("Arial", Font.PLAIN, 14));
		lblSub.setForeground(verdeWeStyle);
		card.add(lblSub, "alignx center");

		JLabel lblNome = new JLabel("Nome da Estampa");
		lblNome.setForeground(verdeWeStyle);
		lblNome.setFont(new Font("Arial", Font.BOLD, 14));
		card.add(lblNome);

		txtNomeEstampa = new JTextField();
		txtNomeEstampa.setFont(new Font("Arial", Font.PLAIN, 14));
		card.add(txtNomeEstampa, "height 40!");

		JLabel lblArquivo = new JLabel("Arquivo da Imagem");
		lblArquivo.setForeground(verdeWeStyle);
		lblArquivo.setFont(new Font("Arial", Font.BOLD, 14));
		card.add(lblArquivo);

		JPanel painelArquivo = new JPanel(new MigLayout("insets 0", "[grow,fill][]", ""));
		painelArquivo.setOpaque(false);

		txtCaminhoArquivo = new JTextField();
		txtCaminhoArquivo.setEditable(false);
		txtCaminhoArquivo.setFont(new Font("Arial", Font.PLAIN, 12));
		painelArquivo.add(txtCaminhoArquivo, "height 40!");

		JButton btnProcurar = new JButton("Procurar...");
		btnProcurar.setBackground(verdeWeStyle);
		btnProcurar.setForeground(Color.WHITE);
		btnProcurar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnProcurar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser seletor = new JFileChooser();
				int resultado = seletor.showOpenDialog(null);
				if (resultado == JFileChooser.APPROVE_OPTION) {
					File arquivo = seletor.getSelectedFile();
					txtCaminhoArquivo.setText(arquivo.getAbsolutePath());
				}
			}
		});
		painelArquivo.add(btnProcurar, "height 40!");
		card.add(painelArquivo);

		JButton btnSalvar = new JButton("Salvar Estampa");
		btnSalvar.setBackground(verdeWeStyle);
		btnSalvar.setForeground(Color.WHITE);
		btnSalvar.setFont(new Font("Arial", Font.BOLD, 16));
		btnSalvar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnSalvar.addActionListener(e -> {
		    if(txtNomeEstampa.getText().isEmpty() || txtCaminhoArquivo.getText().isEmpty()) {
		        JOptionPane.showMessageDialog(null, "Preencha o nome e selecione um arquivo!");
		    } else {
		        try {
		            String nomeEstampa = txtNomeEstampa.getText();
		            File origem = new File(txtCaminhoArquivo.getText());

		            String caminhoBase = new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
		            File pastaDestino = new File(caminhoBase, "Imagens");
		            if (!pastaDestino.exists()) pastaDestino.mkdirs();

		            String extensao = origem.getName().substring(origem.getName().lastIndexOf("."));
		            File destino = new File(pastaDestino, nomeEstampa + extensao);
		            
		            int contador = 1;
		            while (destino.exists()) {
		                destino = new File(pastaDestino, nomeEstampa + "(" + contador + ")" + extensao);
		                contador++;
		            }

		            Files.copy(origem.toPath(), destino.toPath());

		            EstampaDAO dao = new EstampaDAO();
		            boolean sucessoBanco = dao.cadastrarEstampa(nomeEstampa, destino.getName());

		            if (sucessoBanco) {
		                JOptionPane.showMessageDialog(null, "Estampa '" + nomeEstampa + "' salva com sucesso!");
		                new TelaPersonalizar().setVisible(true);
		                dispose();
		            } else {
		                JOptionPane.showMessageDialog(null, "Erro ao registrar estampa");
		            }

		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Erro ao processar estampa: " + ex.getMessage());
		        }
		    }
		});
		card.add(btnSalvar, "height 50!, gapy 20");
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBorder(null);
		btnCancelar.setBackground(Color.WHITE);
		btnCancelar.setForeground(verdeWeStyle);
		btnCancelar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCancelar.addActionListener(e -> {
			new TelaPersonalizar().setVisible(true);
			dispose();
		});
		card.add(btnCancelar, "alignx center");
	}
}