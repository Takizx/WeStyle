package Projetofinal;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaFinalizar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	JLabel lblFreteValor;
	JLabel lblTotalValor;

	double subtotal = 489.70;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				TelaFinalizar frame = new TelaFinalizar();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public TelaFinalizar() {

		setTitle("Finalizar Compra - WeStyle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,1000,706);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(106,143,123));
		contentPane.setBorder(new EmptyBorder(20,20,20,20));
		setContentPane(contentPane);

		contentPane.setLayout(new MigLayout(
				"align center center",
				"[grow,center]",
				"[grow,center]"
		));

		// CARD CENTRAL
		JPanel card = new JPanel();
		card.setBackground(Color.WHITE);
		card.setBorder(new LineBorder(Color.LIGHT_GRAY,1,true));

		card.setLayout(new MigLayout(
				"wrap 2, insets 40, gap 15",
				"[grow][grow]",
				"[][][][][][][][][][]"
		));

		contentPane.add(card,"w 450!, h 520!");

		// TÍTULO
		JLabel titulo = new JLabel("Finalizar Compra");
		titulo.setFont(new Font("Arial",Font.BOLD,26));
		titulo.setForeground(new Color(106,143,123));
		card.add(titulo,"span,align center");

		// RESUMO
		JLabel resumo = new JLabel("Resumo do Pedido");
		resumo.setFont(new Font("Arial",Font.BOLD,16));
		resumo.setForeground(new Color(106,143,123));
		card.add(resumo,"span");

		card.add(new JLabel("Produtos:"));
		card.add(new JLabel("3 itens"));

		card.add(new JLabel("Subtotal:"));
		card.add(new JLabel("R$ 489,70"));

		// ESTADO
		card.add(new JLabel("Estado para entrega:"));

		String[] estados = {
			"Santa Catarina",
			"Paraná",
			"Rio Grande do Sul",
			"São Paulo",
			"Rio de Janeiro",
			"Minas Gerais",
			"Bahia",
			"Pernambuco",
			"Ceará",
			"Pará",
			"Amazonas"
		};

		JComboBox<String> comboEstado = new JComboBox<>(estados);
		card.add(comboEstado,"growx");

		// FRETE
		card.add(new JLabel("Frete:"));

		lblFreteValor = new JLabel("R$ 10,00");
		card.add(lblFreteValor);

		// TOTAL
		card.add(new JLabel("Total:"));

		lblTotalValor = new JLabel("R$ 499,70");
		lblTotalValor.setFont(new Font("Arial",Font.BOLD,14));
		card.add(lblTotalValor);

		// PAGAMENTO
		JLabel pagamento = new JLabel("Forma de Pagamento");
		pagamento.setFont(new Font("Arial",Font.BOLD,16));
		pagamento.setForeground(new Color(106,143,123));
		card.add(pagamento,"span");

		JRadioButton pix = new JRadioButton("PIX");
		pix.setBackground(Color.WHITE);

		JRadioButton credito = new JRadioButton("Cartão de crédito");
		credito.setBackground(Color.WHITE);

		JRadioButton debito = new JRadioButton("Cartão de débito");
		debito.setBackground(Color.WHITE);

		ButtonGroup grupo = new ButtonGroup();
		grupo.add(pix);
		grupo.add(credito);
		grupo.add(debito);

		card.add(pix,"span");
		card.add(credito,"span");
		card.add(debito,"span");

		// BOTÃO FINAL
		JButton confirmar = new JButton("Confirmar Pedido");
		confirmar.setFont(new Font("Arial",Font.BOLD,16));
		confirmar.setBackground(new Color(106,143,123));
		confirmar.setForeground(Color.WHITE);

		card.add(confirmar,"span,align center,w 200!,h 45!,gapy 15");

		// EVENTO CALCULAR FRETE
		comboEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String estado = (String) comboEstado.getSelectedItem();
				double frete = 0;

				if(estado.equals("Santa Catarina")) frete = 10;
				else if(estado.equals("Paraná") || estado.equals("Rio Grande do Sul")) frete = 15;
				else if(estado.equals("São Paulo") || estado.equals("Rio de Janeiro") || estado.equals("Minas Gerais")) frete = 20;
				else if(estado.equals("Bahia") || estado.equals("Pernambuco") || estado.equals("Ceará")) frete = 30;
				else frete = 35;

				double total = subtotal + frete;

				lblFreteValor.setText("R$ " + String.format("%.2f", frete));
				lblTotalValor.setText("R$ " + String.format("%.2f", total));
			}
		});
	}
}