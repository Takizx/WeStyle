package Visao;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;
import net.miginfocom.swing.MigLayout;
import Controle.ItensPedidoDAO;

public class TelaFinalizar extends JPanel {

    private static final long serialVersionUID = 1L;
    private JLabel lblFrete;
    private JLabel lblTotal;
    private double subtotal = 0.0; 
    private double valorFrete = 0.0;
    private List<String[]> itensPedido;
    private int idPedidoAtual; 
    private ItensPedidoDAO dao = new ItensPedidoDAO();
    
    Color verdeWeStyle = new Color(106, 143, 123);

    public TelaFinalizar(List<String[]> itens, double valorSubtotal, int idPedido) {
        this.itensPedido = itens;
        this.subtotal = valorSubtotal;
        this.idPedidoAtual = idPedido;

        this.setBackground(verdeWeStyle);
        this.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.setLayout(new MigLayout("fill", "[grow]", "[grow]"));

        JPanel container = new JPanel(new MigLayout("wrap, align center", "[center]", "[]15[]15[]"));
        container.setOpaque(false);
        this.add(container, "center");

        JPanel card = new JPanel();
        card.setBackground(Color.WHITE);
        card.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        card.setLayout(new MigLayout("wrap, insets 30, gap 5", "[grow,fill]", ""));
        container.add(card, "w 450!, h 620!");

        JLabel titulo = new JLabel("Finalizar Compra");
        titulo.setForeground(verdeWeStyle);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        card.add(titulo, "align center, gapy 10");

        JLabel labelResumo = new JLabel("Resumo do Pedido:");
        labelResumo.setForeground(verdeWeStyle);
        labelResumo.setFont(new Font("Arial", Font.BOLD, 14));
        card.add(labelResumo, "gapy 10");
        
        for (String[] item : itensPedido) {
            JLabel lblItem = new JLabel("• " + item[0] + " (R$ " + item[1] + ")");
            lblItem.setForeground(verdeWeStyle); 
            lblItem.setFont(new Font("Arial", Font.PLAIN, 13));
            card.add(lblItem, "gapy 2");
        }

        card.add(new JLabel("------------------------------------------") {{ setForeground(verdeWeStyle); }}, "gapy 5");

        JLabel lblSubtotal = new JLabel("Subtotal: R$ " + String.format("%.2f", subtotal));
        lblSubtotal.setForeground(verdeWeStyle);
        card.add(lblSubtotal);

        lblFrete = new JLabel("Frete: Selecione a região...");
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
        comboRegiao.setForeground(verdeWeStyle);
        card.add(comboRegiao, "h 35!");

        JLabel lblPagamento = new JLabel("Forma de Pagamento");
        lblPagamento.setForeground(verdeWeStyle);
        card.add(lblPagamento, "gapy 5");
        
        String[] pagamentos = {"Pix", "Cartão de Crédito", "Cartão de Débito"};
        JComboBox<String> comboPagamento = new JComboBox<>(pagamentos);
        comboPagamento.setForeground(verdeWeStyle);
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
        btnFinalizar.setBackground(Color.WHITE); 
        btnFinalizar.setForeground(verdeWeStyle);
        btnFinalizar.setFont(new Font("Arial", Font.BOLD, 14));
        btnFinalizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnFinalizar.addActionListener(e -> {
            if(comboRegiao.getSelectedIndex() == 0) {
                new TelaMensagem("Por favor, selecione uma região de entrega.", "erro");
                return;
            }
            abrirJanelaPagamento(comboPagamento.getSelectedItem().toString(), (String) comboRegiao.getSelectedItem());
        });
        container.add(btnFinalizar, "align center, w 250!, h 50!");

        JButton btnVoltarInicio = new JButton("Voltar para o Início");
        btnVoltarInicio.setBackground(new Color(255, 255, 255)); 
        btnVoltarInicio.setForeground(new Color(106, 143, 123));
        btnVoltarInicio.setFont(new Font("Arial", Font.BOLD, 14));
        btnVoltarInicio.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVoltarInicio.setBorder(new LineBorder(Color.WHITE, 1));
        btnVoltarInicio.addActionListener(e -> {
            JanelaPrincipal.mudarTela("escolha");
        });
        container.add(btnVoltarInicio, "align center, w 180!, h 40!");
    }

    private void abrirJanelaPagamento(String metodo, String regiao) {
        Window ancestral = SwingUtilities.getWindowAncestor(this);
        JDialog janelaPagto = new JDialog(ancestral, "Pagamento WeStyle", Dialog.ModalityType.APPLICATION_MODAL);
        janelaPagto.setSize(400, 520);
        janelaPagto.setLocationRelativeTo(this);
        JPanel pnl = new JPanel(new MigLayout("wrap, align center, insets 20", "[grow, fill]"));
        pnl.setBackground(Color.WHITE);
        janelaPagto.getContentPane().add(pnl);

        JLabel lblMetodo = new JLabel("Pagamento via " + metodo);
        lblMetodo.setFont(new Font("Arial", Font.BOLD, 16));
        lblMetodo.setForeground(verdeWeStyle);
        pnl.add(lblMetodo, "align center, gapbottom 10");

        if (metodo.equals("Pix")) {
            JLabel lblInstrucao = new JLabel("Escaneie o QR Code abaixo para pagar:");
            lblInstrucao.setForeground(verdeWeStyle);
            pnl.add(lblInstrucao, "align center");
            
            JPanel qrCode = new JPanel() {{ setBackground(Color.BLACK); }};
            pnl.add(qrCode, "align center, w 180!, h 180!");
            
            JLabel lblChave = new JLabel("Chave: westyle@pagamentos.com");
            lblChave.setFont(new Font("Arial", Font.ITALIC, 13));
            lblChave.setForeground(verdeWeStyle);
            pnl.add(lblChave, "align center, gapy 5");
            
        } else {
            JLabel lblNum = new JLabel("Número do Cartão:");
            lblNum.setForeground(verdeWeStyle);
            pnl.add(lblNum);
            
            JTextField txtNum = new JTextField();
            txtNum.setForeground(verdeWeStyle);
            pnl.add(txtNum, "h 35!");
            
            JLabel lblTit = new JLabel("Nome do Titular:");
            lblTit.setForeground(verdeWeStyle);
            pnl.add(lblTit);
            
            JTextField txtTit = new JTextField();
            txtTit.setForeground(verdeWeStyle);
            pnl.add(txtTit, "h 35!");
            
            JLabel lblVal = new JLabel("Validade / CVV:");
            lblVal.setForeground(verdeWeStyle);
            pnl.add(lblVal, "split 2");
            
            JTextField txtVal1 = new JTextField();
            txtVal1.setForeground(verdeWeStyle);
            pnl.add(txtVal1, "h 35!");
            
            JTextField txtVal2 = new JTextField();
            txtVal2.setForeground(verdeWeStyle);
            pnl.add(txtVal2, "h 35!");
        }

        JButton btnConfirmar = new JButton("Finalizar Pagamento");
        btnConfirmar.setBackground(verdeWeStyle);
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setFont(new Font("Arial", Font.BOLD, 14));
        btnConfirmar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnConfirmar.addActionListener(ev -> {
            double valorTotalFinal = subtotal + valorFrete;
            boolean sucesso = dao.finalizarPedidoNoBanco(idPedidoAtual, subtotal, valorFrete, valorTotalFinal, regiao);
            
            if(sucesso) {
                janelaPagto.dispose();
                new TelaMensagem("Pedido finalizado com sucesso!", "sucesso");
            } else {
                new TelaMensagem("Erro!", "erro");
            }
        });
        
        pnl.add(btnConfirmar, "gapy 20, h 45!");
        janelaPagto.setVisible(true);
    }
}