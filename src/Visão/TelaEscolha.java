package Visão;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import net.miginfocom.swing.MigLayout;

public class TelaEscolha extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				TelaEscolha frame = new TelaEscolha();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public TelaEscolha() {

		setTitle("WeStyle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 706);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(106,143,123));
		contentPane.setBorder(new EmptyBorder(20,20,20,20));

		contentPane.setLayout(new MigLayout(
				"align center center",
				"",
				""
		));

		setContentPane(contentPane);

		JPanel card = new JPanel();
		card.setBackground(Color.WHITE);
		card.setBorder(new LineBorder(Color.LIGHT_GRAY,1,true));

		card.setLayout(new MigLayout(
				"wrap 1, insets 40, gap 20",
				"[grow,center]",
				"[]20[]30[]20[]"
		));

		contentPane.add(card,"w 450!, h 500!");

		JLabel lblTitulo = new JLabel("Bem-vindo ao WeStyle!");
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
		lblTitulo.setForeground(new Color(106,143,123));
		card.add(lblTitulo);

		JLabel lblFrase = new JLabel("A moda começa com a sua ideia.");
		lblFrase.setFont(new Font("Arial", Font.ITALIC, 16));
		lblFrase.setForeground(new Color(106, 143, 132));
		card.add(lblFrase);

		JLabel lblPergunta = new JLabel("O que você gostaria de fazer hoje?");
		lblPergunta.setFont(new Font("Arial", Font.BOLD, 16));
		lblPergunta.setForeground(new Color(106,143,123));
		card.add(lblPergunta);

		JButton btnCatalogo = new JButton("Explorar Catálogo");

		// SEM AÇÃO

		btnCatalogo.setFont(new Font("Arial", Font.BOLD, 15));
		btnCatalogo.setBackground(new Color(106,143,123));
		btnCatalogo.setForeground(Color.WHITE);

		card.add(btnCatalogo,"width 240!, height 50!");

		JButton btnPersonalizar = new JButton("Criar Minha Peça");
		btnPersonalizar.setFont(new Font("Arial", Font.BOLD, 15));
		btnPersonalizar.setBackground(new Color(106, 143, 123));
		btnPersonalizar.setForeground(new Color(255, 255, 255));
		btnPersonalizar.setBorder(new LineBorder(new Color(106,143,123),1,true));

		card.add(btnPersonalizar,"width 240!, height 50!");

		JLabel lblExtra = new JLabel("Estilo é uma forma de dizer quem você é.");
		lblExtra.setFont(new Font("Arial", Font.ITALIC, 15));
		lblExtra.setForeground(new Color(106, 143, 132));

		card.add(lblExtra,"gapy 20");
	}
}