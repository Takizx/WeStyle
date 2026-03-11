package Projetofinal;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaCarrinho extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public TelaCarrinho() {

		setTitle("WeStyle - Carrinho");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 700);

		contentPane = new JPanel(new BorderLayout());
		contentPane.setBackground(new Color(245,245,245));
		setContentPane(contentPane);

		criarNavbar();
		criarConteudo();
	}

	private void criarNavbar() {

		JPanel navbar = new JPanel(new MigLayout("insets 10", "[][grow][][][][][][]", "[]"));
		navbar.setBackground(new Color(106, 143, 123));
		navbar.setBorder(new MatteBorder(0,0,1,0,new Color(220,220,220)));

		JLabel logo = new JLabel("WeStyle");
		logo.setFont(new Font("Arial", Font.BOLD, 16));
		logo.setOpaque(true);
		logo.setBackground(new Color(106,143,123));
		logo.setForeground(Color.WHITE);
		logo.setBorder(new EmptyBorder(5,15,5,15));

		JButton entrar = new JButton("Entrar");
		entrar.setBackground(new Color(106, 143, 123));
		entrar.setForeground(new Color(255, 255, 255));
		entrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		navbar.add(logo, "cell 0 0");
		navbar.add(new JLabel(), "cell 1 0");
		
		JButton btnNewButton_3 = new JButton("Início");
		btnNewButton_3.setBackground(new Color(106, 143, 123));
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		navbar.add(btnNewButton_3, "cell 3 0");
		
		JButton btnNewButton_1 = new JButton("Carrinho");
		btnNewButton_1.setBackground(new Color(106, 143, 123));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		navbar.add(btnNewButton_1, "cell 4 0");
		
		JButton btnNewButton = new JButton("Minhas Criações");
		btnNewButton.setBackground(new Color(106, 143, 123));
		btnNewButton.setForeground(new Color(255, 255, 255));
		navbar.add(btnNewButton, "cell 5 0");
		
		JButton btnNewButton_2 = new JButton("Personalizar");
		btnNewButton_2.setBackground(new Color(106, 143, 123));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		navbar.add(btnNewButton_2, "cell 6 0");
		navbar.add(entrar, "cell 7 0");

		contentPane.add(navbar, BorderLayout.NORTH);
	}

	private void criarConteudo() {

		JPanel area = new JPanel(new MigLayout("wrap, insets 30","[grow,fill]",""));
		area.setBackground(new Color(106, 143, 123));

		JLabel titulo = new JLabel("Carrinho de Compras");
		titulo.setForeground(new Color(255, 255, 255));
		titulo.setFont(new Font("Arial",Font.BOLD,20));

		area.add(titulo);

		JPanel card = new JPanel(new MigLayout("wrap, insets 20, gap 15","[grow,fill]",""));
		card.setBackground(new Color(106, 143, 123));
		card.setBorder(new LineBorder(new Color(220,220,220),1,true));

		card.add(criarItem("Camiseta Oversize","M","R$ 89,90"));
		card.add(criarItem("Moletom Streetwear","G","R$ 179,90"));
		card.add(criarItem("Jaqueta Minimalista","M","R$ 249,90"));

		JPanel totalPanel = new JPanel(new MigLayout("","[grow][right]",""));
		totalPanel.setOpaque(false);

		JLabel total = new JLabel("Total: R$ 518,70");
		total.setForeground(new Color(255, 255, 255));
		total.setFont(new Font("Arial",Font.BOLD,16));

		JButton finalizar = new JButton("Finalizar Compra");
		finalizar.setBackground(new Color(255, 255, 255));
		finalizar.setForeground(new Color(106, 143, 123));


		totalPanel.add(total);
		totalPanel.add(finalizar);

		card.add(totalPanel);

		area.add(card);

		contentPane.add(area, BorderLayout.CENTER);
	}

	private JPanel criarItem(String nome, String tamanho, String preco){

		JPanel item = new JPanel(new MigLayout("insets 10","[][grow][right]","[][]"));
		item.setBorder(new MatteBorder(0,0,1,0,new Color(230,230,230)));
		item.setBackground(new Color(106, 143, 123));

		JLabel imagem = new JLabel("IMG");
		imagem.setForeground(new Color(255, 255, 255));
		imagem.setPreferredSize(new Dimension(60,60));
		imagem.setHorizontalAlignment(SwingConstants.CENTER);
		imagem.setBorder(new LineBorder(Color.LIGHT_GRAY));

		JLabel nomeLabel = new JLabel(nome);
		nomeLabel.setForeground(new Color(255, 255, 255));
		nomeLabel.setFont(new Font("Arial",Font.BOLD,14));

		JLabel tamanhoLabel = new JLabel("Tamanho: " + tamanho);
		tamanhoLabel.setForeground(new Color(255, 255, 255));

		JLabel precoLabel = new JLabel(preco);
		precoLabel.setForeground(new Color(255, 255, 255));
		precoLabel.setFont(new Font("Arial",Font.BOLD,14));

		item.add(imagem,"span 1 2");
		item.add(nomeLabel,"wrap");
		item.add(tamanhoLabel);
		item.add(precoLabel,"cell 2 0,alignx right");

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