package Visao;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import net.miginfocom.swing.MigLayout;

public class TelaFinalizar extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblFrete;
    private JLabel lblTotal;

    private double subtotal = 0.0; 
    private double valorFrete = 0.0;
    private List<String[]> itensPedido;

    public TelaFinalizar() {
        this(new ArrayList<>(), 0.0);
    }

    public TelaFinalizar(List<String[]> itens, double valorSubtotal) {
        this.itensPedido = itens;
        this.subtotal = valorSubtotal;

        setTitle("WeStyle - Finalizar Compra");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 700); 

        contentPane = new JPanel();
        contentPane.setBackground(new Color(106, 143, 123));
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(new MigLayout("", "[grow]", "[grow]"));
        setContentPane(contentPane);

        JPanel container = new JPanel(new MigLayout("wrap, align center", "[center]", "[]20[]"));
        container.setOpaque(false);
        contentPane.add(container, "cell 0 0, align center");

        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        card.setLayout(new MigLayout("wrap, insets 30, gap 10", "[grow,fill]", ""));
        container.add(card, "w 450!, h 550!");

        JLabel titulo = new JLabel("Finalizar Compra");
        titulo.setForeground(new Color(106, 143, 123));
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        card.add(titulo, "align center, gapy 10");

        card.add(new JLabel("Resumo do Pedido:"), "gapy 10");

        for (String[] item : itensPedido) {
            JLabel lblItem = new JLabel("• " + item[0] + " (R$ " + item[1] + ")");
            lblItem.setForeground(Color.DARK_GRAY);
            lblItem.setFont(new Font("Arial", Font.PLAIN, 13));
            card.add(lblItem);
        }

        card.add(new JLabel("------------------------------------------"), "gapy 5");

        JLabel lblSubtotal = new JLabel("Subtotal: R$ " + String.format("%.2f", subtotal));
        lblSubtotal.setForeground(new Color(106, 143, 123));
        lblSubtotal.setFont(new Font("Arial", Font.BOLD, 14));
        card.add(lblSubtotal);

        lblFrete = new JLabel("Frete: Selecione a região...");
        lblFrete.setForeground(new Color(106, 143, 123));
        card.add(lblFrete);

        lblTotal = new JLabel("Total: R$ " + String.format("%.2f", subtotal));
        lblTotal.setForeground(new Color(106, 143, 123));
        lblTotal.setFont(new Font("Arial", Font.BOLD, 18));
        card.add(lblTotal, "gapy 5");

        card.add(new JLabel("Região de Entrega"), "gapy 10");
        String[] regioes = {"Selecione...", "Sul", "Sudeste", "Centro-Oeste", "Nordeste", "Norte"};
        JComboBox<String> comboRegiao = new JComboBox<>(regioes);
        comboRegiao.setForeground(new Color(106, 143, 123));
        card.add(comboRegiao, "h 35!");

        card.add(new JLabel("Forma de pagamento"), "gapy 5");
        String[] pagamentos = {"Pix", "Cartão de Crédito", "Cartão de Débito"};
        JComboBox<String> comboPagamento = new JComboBox<>(pagamentos);
        comboPagamento.setForeground(new Color(106, 143, 123));
        card.add(comboPagamento, "h 35!");

        comboRegiao.addActionListener(e -> {
            String regiao = (String) comboRegiao.getSelectedItem();
            switch (regiao) {
                case "Sul": valorFrete = 15.00; break;
                case "Sudeste": valorFrete = 25.00; break;
                case "Centro-Oeste": valorFrete = 35.00; break;
                case "Nordeste": valorFrete = 50.00; break;
                case "Norte": valorFrete = 65.00; break;
                default: valorFrete = 0.0; break;
            }
            if (valorFrete > 0) {
                lblFrete.setText("Frete: R$ " + String.format("%.2f", valorFrete));
                lblTotal.setText("Total: R$ " + String.format("%.2f", (subtotal + valorFrete)));
            }
        });

        JButton btnFinalizar = new JButton("Confirmar Pagamento");
        btnFinalizar.setBackground(new Color(106, 143, 123)); 
        btnFinalizar.setForeground(Color.WHITE);
        btnFinalizar.setFont(new Font("Arial", Font.BOLD, 14));
        
        btnFinalizar.addActionListener(e -> {
            if(comboRegiao.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Selecione a região!");
                return;
            }
            JOptionPane.showMessageDialog(null, "Compra de R$ " + lblTotal.getText() + " realizada!\nObrigado por comprar na WeStyle!");
            new TelaEscolha().setVisible(true);
            dispose();
        });

        container.add(btnFinalizar, "align center, w 220!, h 45!");
    }
}