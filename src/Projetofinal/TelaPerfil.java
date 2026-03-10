package Projetofinal;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPerfil extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JTextField txtNome;
	private JTextField txtEmail;
	private JTextField txtTelefone;
	private JTextField txtLocalizacao;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				TelaPerfil frame = new TelaPerfil();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public TelaPerfil() {

		setTitle("Perfil - WeStyle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 706);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(106,143,123));
		contentPane.setBorder(new EmptyBorder(20,20,20,20));
		setContentPane(contentPane);

		// layout principal
		contentPane.setLayout(new MigLayout(
				"wrap, align center",
				"[grow]",
				"[]push[]push"
		));

		// ===== MENU SUPERIOR =====

		JPanel menu = new JPanel();
		menu.setBackground(new Color(106, 143, 123));
		menu.setBorder(new LineBorder(Color.LIGHT_GRAY,1,true));

		menu.setLayout(new MigLayout("", "[][][][][][][][][][][][]push[]push[][]", "[]"));

		contentPane.add(menu,"growx");

		JLabel logo = new JLabel("WeStyle");
		logo.setFont(new Font("Arial",Font.BOLD,16));
		logo.setForeground(new Color(255, 255, 255));
		menu.add(logo, "cell 0 0");
		
		JButton btnNewButton_1 = new JButton("Inicio");
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(106, 143, 123));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel lblNewLabel = new JLabel("                adasdadasd");
		lblNewLabel.setForeground(new Color(106, 143, 123));
		lblNewLabel.setBackground(new Color(106, 143, 123));
		menu.add(lblNewLabel, "cell 7 0");
		menu.add(btnNewButton_1, "cell 8 0");
		
		JButton btnNewButton = new JButton("Carrinho");
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(106, 143, 123));
		menu.add(btnNewButton, "cell 9 0");
		
		JButton btnNewButton_3 = new JButton("Minhas Criações");
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setBackground(new Color(106, 143, 123));
		menu.add(btnNewButton_3, "cell 10 0");
		
		JButton btnNewButton_2 = new JButton("Personalizar");
		btnNewButton_2.setBackground(new Color(106, 143, 123));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		menu.add(btnNewButton_2, "cell 11 0");

		// ===== CARD CENTRAL =====

		JPanel card = new JPanel();
		card.setBackground(Color.WHITE);
		card.setBorder(new LineBorder(Color.LIGHT_GRAY,1,true));

		card.setLayout(new MigLayout(
				"wrap 2, insets 40, gap 20",
				"[grow][grow]",
				"[][][][][][]"
		));

		// MESMO TAMANHO DAS OUTRAS TELAS
		contentPane.add(card,"align center, w 450!, h 500!");

		// ===== TITULO =====

		JLabel lblTitulo = new JLabel("Perfil do Usuário");
		lblTitulo.setFont(new Font("Arial",Font.BOLD,26));
		lblTitulo.setForeground(new Color(106,143,123));

		card.add(lblTitulo,"span, align center");

		// ===== CAMPOS =====

		card.add(new JLabel("Nome Completo"));
		card.add(new JLabel("E-mail"));

		txtNome = new JTextField();
		card.add(txtNome,"growx,h 35!");

		txtEmail = new JTextField();
		card.add(txtEmail,"growx,h 35!");

		card.add(new JLabel("Telefone"));
		card.add(new JLabel("Localização"));

		txtTelefone = new JTextField();
		card.add(txtTelefone,"growx,h 35!");

		txtLocalizacao = new JTextField();
		card.add(txtLocalizacao,"growx,h 35!");

		// ===== BOTÕES =====

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSalvar.setBackground(new Color(106,143,123));
		btnSalvar.setForeground(Color.WHITE);
		btnSalvar.setFont(new Font("Arial",Font.BOLD,14));

		card.add(btnSalvar,"span,align center,w 200!,h 40!");

		JButton btnSenha = new JButton("Alterar Senha");
		btnSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSenha.setBackground(Color.WHITE);
		btnSenha.setForeground(new Color(106,143,123));
		btnSenha.setBorder(new LineBorder(new Color(106,143,123),1,true));
		btnSenha.setFont(new Font("Arial",Font.BOLD,14));

		card.add(btnSenha,"span,align center,w 200!,h 40!");
	}
}
