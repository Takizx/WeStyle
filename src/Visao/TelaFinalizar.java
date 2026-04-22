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
        card.setLayout(new MigLayout("wrap, insets 30, gap 10", "[grow,fill]", ""));
        container.add(card, "w 450!, h 580!");

        JLabel titulo = new JLabel("Finalizar Compra");
        titulo.setForeground(verdeWeStyle);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        card.add(titulo, "align center, gapy 10");

        JLabel label_2 = new JLabel("Resumo do Pedido:");
        label_2.setForeground(new Color(106, 143, 123));
        card.add(label_2, "gapy 10");

        for (String[] item : itensPedido) {
            JLabel lblItem = new JLabel("• " + item[0] + " (R$ " + item[1] + ")");
            lblItem.setForeground(Color.DARK_GRAY);
            lblItem.setFont(new Font("Arial", Font.PLAIN, 13));
            card.add(lblItem);
        }

        JLabel label_3 = new JLabel("------------------------------------------");
        label_3.setForeground(new Color(106, 143, 123));
        card.add(label_3, "gapy 5");

        JLabel lblSubtotal = new JLabel("Subtotal: R$ " + String.format("%.2f", subtotal));
        lblSubtotal.setForeground(verdeWeStyle);
        lblSubtotal.setFont(new Font("Arial", Font.BOLD, 14));
        card.add(lblSubtotal);

        lblFrete = new JLabel("Frete: Selecione a região...");
        lblFrete.setForeground(verdeWeStyle);
        card.add(lblFrete);

        lblTotal = new JLabel("Total: R$ " + String.format("%.2f", subtotal));
        lblTotal.setForeground(verdeWeStyle);
        lblTotal.setFont(new Font("Arial", Font.BOLD, 18));
        card.add(lblTotal, "gapy 5");

        JLabel label = new JLabel("Região de Entrega");
        label.setForeground(new Color(106, 143, 123));
        card.add(label, "gapy 10");
        String[] regioes = {"Selecione...", "Sul", "Sudeste", "Centro-Oeste", "Nordeste", "Norte"};
        JComboBox<String> comboRegiao = new JComboBox<>(regioes);
        comboRegiao.setForeground(new Color(106, 143, 123));
        card.add(comboRegiao, "h 35!");

        JLabel label_1 = new JLabel("Forma de pagamento");
        label_1.setForeground(new Color(106, 143, 123));
        card.add(label_1, "gapy 5");
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
            lblFrete.setText("Frete: R$ " + String.format("%.2f", valorFrete));
            lblTotal.setText("Total: R$ " + String.format("%.2f", (subtotal + valorFrete)));
        });

        JButton btnFinalizar = new JButton("Confirmar Pagamento");
        btnFinalizar.setBackground(new Color(255, 255, 255)); 
        btnFinalizar.setForeground(new Color(106, 143, 123));
        btnFinalizar.setFont(new Font("Arial", Font.BOLD, 14));
        
        btnFinalizar.addActionListener(e -> {
            if(comboRegiao.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Selecione a região antes de pagar!");
                return;
            }
            abrirJanelaPagamento(comboPagamento.getSelectedItem().toString());
        });

        container.add(btnFinalizar, "align center, w 220!, h 45!");
    }

    private void abrirJanelaPagamento(String metodo) {
        JDialog janelaPagto = new JDialog(this, "Pagamento WeStyle", true);
        janelaPagto.setSize(400, 450);
        janelaPagto.setLocationRelativeTo(this);
        
        JPanel pnl = new JPanel(new MigLayout("wrap, align center, insets 20", "[grow, fill]"));
        pnl.setBackground(Color.WHITE);
        janelaPagto.getContentPane().add(pnl);

        if (metodo.equals("Pix")) {
            JLabel lblPix = new JLabel("Escaneie o QR Code para pagar:");
            lblPix.setFont(new Font("Arial", Font.BOLD, 14));
            pnl.add(lblPix, "align center");
            
            JPanel qrCode = new JPanel();
            qrCode.setBackground(Color.BLACK);
            qrCode.setPreferredSize(new Dimension(180, 180));
            pnl.add(qrCode, "align center, w 180!, h 180!");
            
            pnl.add(new JLabel("Chave: westyle@pagamentos.com"), "align center");
        } else {
            pnl.add(new JLabel("Número do Cartão:"));
            pnl.add(new JTextField(), "h 30!");
            pnl.add(new JLabel("Titular:"));
            pnl.add(new JTextField(), "h 30!");
            pnl.add(new JLabel("Validade / CVV:"), "split 2");
            pnl.add(new JTextField(), "h 30!");
        }

        JButton btnConfirmar = new JButton("Finalizar Pagamento");
        btnConfirmar.setBackground(Color.WHITE);
        btnConfirmar.setForeground(verdeWeStyle);
        btnConfirmar.setFont(new Font("Arial", Font.BOLD, 14));
        btnConfirmar.addActionListener(ev -> {
            janelaPagto.dispose();
            JOptionPane.showMessageDialog(null, "<html><center><b>Pagamento Realizado com Sucesso!</b><br>Muito obrigado por comprar na WeStyle!</center></html>");
            new TelaEscolha().setVisible(true);
            dispose();
        });
        
        pnl.add(btnConfirmar, "gapy 20, h 40!");
        janelaPagto.setVisible(true);
    }
}