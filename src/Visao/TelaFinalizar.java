package Visao;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import net.miginfocom.swing.MigLayout;

public class TelaFinalizar extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblFrete;
    private JLabel lblTotal;
    private double subtotal = 0.0; 
    private double valorFrete = 0.0;
    private List<String[]> itensPedido;
    
    Color verdeWeStyle = new Color(106, 143, 123);

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                List<String[]> listaVazia = new ArrayList<>();
                TelaFinalizar frame = new TelaFinalizar(listaVazia, 0.00);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaFinalizar(List<String[]> itens, double valorSubtotal) {
        this.itensPedido = itens;
        this.subtotal = valorSubtotal;

        setTitle("WeStyle - Finalizar Compra");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 750); 
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        contentPane = new JPanel();
        contentPane.setBackground(verdeWeStyle);
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentPane.setLayout(new MigLayout("fill", "[grow]", "[grow]"));
        setContentPane(contentPane);

        JPanel container = new JPanel(new MigLayout("wrap, align center", "[center]", "[]20[]"));
        container.setOpaque(false);
        contentPane.add(container, "center");

        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        card.setLayout(new MigLayout("wrap, insets 30, gap 5", "[grow,fill]", ""));
        container.add(card, "w 450!, h 620!");

        JLabel titulo = new JLabel("Finalizar Compra");
        titulo.setForeground(verdeWeStyle);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        card.add(titulo, "align center, gapy 10");

        JLabel label = new JLabel("Resumo do Pedido:");
        label.setForeground(verdeWeStyle);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        card.add(label, "gapy 10");
        
        if (itensPedido.isEmpty()) {
            JLabel lblVazio = new JLabel("Nenhum item selecionado");
            lblVazio.setForeground(verdeWeStyle); // Definido para o verde 106,143,123
            lblVazio.setFont(new Font("Arial", Font.ITALIC, 13));
            card.add(lblVazio, "gapy 5");
        } else {
            for (String[] item : itensPedido) {
                JLabel lblItem = new JLabel("• " + item[0] + " (R$ " + item[1] + ")");
                lblItem.setForeground(Color.DARK_GRAY);
                card.add(lblItem);
            }
        }

        JLabel label_1 = new JLabel("------------------------------------------");
        label_1.setForeground(new Color(106, 143, 123));
        card.add(label_1, "gapy 5");

        JLabel lblSubtotal = new JLabel("Subtotal: R$ " + String.format("%.2f", subtotal));
        lblSubtotal.setForeground(verdeWeStyle);
        card.add(lblSubtotal);

        lblFrete = new JLabel("Frete: R$ 0,00");
        lblFrete.setForeground(verdeWeStyle);
        card.add(lblFrete);

        lblTotal = new JLabel("Total: R$ " + String.format("%.2f", subtotal));
        lblTotal.setForeground(verdeWeStyle);
        lblTotal.setFont(new Font("Arial", Font.BOLD, 18));
        card.add(lblTotal, "gapy 5");

        JLabel lblRegiao = new JLabel("Região de Entrega");
        lblRegiao.setForeground(verdeWeStyle);
        card.add(lblRegiao, "gapy 10");
        
        String[] regioes = {"Selecione...", "Sul", "Sudeste", "Centro-Oeste", "Nordeste", "Norte"};
        JComboBox<String> comboRegiao = new JComboBox<>(regioes);
        comboRegiao.setForeground(new Color(106, 143, 123));
        card.add(comboRegiao, "h 35!");

        JLabel lblPagto = 
        		new JLabel("Forma de Pagamento");
        lblPagto.setForeground(verdeWeStyle);
        card.add(lblPagto, "gapy 5");
        
        String[] pagamentos = {"Pix", "Cartão de Crédito", "Cartão de Débito"};
        JComboBox<String> comboPagamento = new JComboBox<>(pagamentos);
        comboPagamento.setForeground(new Color(106, 143, 123));
        card.add(comboPagamento, "h 35!");

        comboRegiao.addActionListener(e -> {
            String regiao = (String) comboRegiao.getSelectedItem();
            switch (regiao) {
                case "Sul": valorFrete = 15.00; break;
                case "Sudeste": valorFrete = 22.50; break;
                case "Centro-Oeste": valorFrete = 30.00; break;
                case "Nordeste": valorFrete = 45.00; break;
                case "Norte": valorFrete = 55.00; break;
                default: valorFrete = 0.0; break;
            }
            lblFrete.setText("Frete: R$ " + String.format("%.2f", valorFrete));
            lblTotal.setText("Total: R$ " + String.format("%.2f", (subtotal + valorFrete)));
        });

        JButton btnFinalizar = new JButton("Confirmar Pagamento");
        btnFinalizar.setBackground(verdeWeStyle); 
        btnFinalizar.setForeground(Color.WHITE);
        btnFinalizar.setFont(new Font("Arial", Font.BOLD, 14));
        btnFinalizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnFinalizar.addActionListener(e -> {
            if(comboRegiao.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Por favor, selecione uma região para calcular o frete.");
            } else {
                JOptionPane.showMessageDialog(null, "Integração de pagamento em breve!");
            }
        });
        container.add(btnFinalizar, "align center, w 250!, h 50!");
    }
}