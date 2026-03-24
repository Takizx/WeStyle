package Visão;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import net.miginfocom.swing.MigLayout;

public class TelaFinalizar extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private JLabel lblFrete;
    private JLabel lblTotal;

    private double subtotal = 300.00; //valor exemplo

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

        setTitle("Finalizar Compra - Supermercado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 700); 

        contentPane = new JPanel();
        contentPane.setBackground(new Color(106, 143, 123));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));

     
        contentPane.setLayout(new MigLayout(
                "",
                "[grow]",
                "[grow]"
        ));

        setContentPane(contentPane);

        
        JPanel container = new JPanel(new MigLayout(
                "wrap, align center",
                "[center]",
                "[]20[]"
        ));
        container.setOpaque(false);

        
        contentPane.add(container, "cell 0 0, align center");

        
        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));

        card.setLayout(new MigLayout(
                "wrap, insets 30, gap 10",
                "[grow,center]",
                ""
        ));

        container.add(card, "w 400!, h 350!");

        JLabel titulo = new JLabel("Finalizar Compra");
        titulo.setForeground(new Color(106, 143, 123));
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        card.add(titulo, "align center");

        JLabel label = new JLabel("Resumo do Pedido");
        label.setForeground(new Color(106, 143, 123));
        card.add(label);

        JLabel lblSubtotal = new JLabel("Subtotal: R$ " + subtotal);
        lblSubtotal.setForeground(new Color(106, 143, 123));
        card.add(lblSubtotal);

        lblFrete = new JLabel("Frete: R$ 0.00");
        lblFrete.setForeground(new Color(106, 143, 123));
        card.add(lblFrete);

        lblTotal = new JLabel("Total: R$ " + subtotal);
        lblTotal.setForeground(new Color(106, 143, 123));
        lblTotal.setFont(new Font("Arial", Font.BOLD, 14));
        card.add(lblTotal);

        
        JLabel label_1 = new JLabel("Estado");
        label_1.setForeground(new Color(106, 143, 123));
        card.add(label_1);

        String[] estados = {
                "Santa Catarina", "Paraná", "São Paulo",
                "Rio de Janeiro", "Minas Gerais",
                "Bahia", "Rio Grande do Sul"
        };

        JComboBox<String> comboEstado = new JComboBox<>(estados);
        comboEstado.setForeground(new Color(106, 143, 123));
        card.add(comboEstado, "w 200!");

       
        JLabel label_2 = new JLabel("Forma de pagamento");
        label_2.setForeground(new Color(106, 143, 123));
        card.add(label_2);

        String[] pagamentos = {
                "Pix", "Cartão de Crédito", "Cartão de Débito"
        };

        JComboBox<String> comboPagamento = new JComboBox<>(pagamentos);
        comboPagamento.setForeground(new Color(106, 143, 123));
        card.add(comboPagamento, "w 200!");

        // valculando frete
        comboEstado.addActionListener(e -> {
            String estado = (String) comboEstado.getSelectedItem();

            double frete = 0;

            switch (estado) {
                case "Santa Catarina": frete = 10; break;
                case "Paraná": frete = 15; break;
                case "São Paulo": frete = 20; break;
                case "Rio de Janeiro": frete = 25; break;
                case "Minas Gerais": frete = 30; break;
                case "Bahia": frete = 40; break;
                case "Rio Grande do Sul": frete = 18; break;
            }

            lblFrete.setText("Frete: R$ " + frete);
            lblTotal.setText("Total: R$ " + (subtotal + frete));
        });

        
        JButton btnFinalizar = new JButton("Finalizar Compra");
        btnFinalizar.setBackground(Color.WHITE);
        btnFinalizar.setForeground(new Color(106, 143, 123));
        btnFinalizar.setFont(new Font("Arial", Font.BOLD, 14));

        container.add(btnFinalizar, "align center, w 220!, h 45!");
    }
}