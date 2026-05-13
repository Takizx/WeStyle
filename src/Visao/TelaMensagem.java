package Visao;

import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import net.miginfocom.swing.MigLayout;

public class TelaMensagem extends JFrame {

    private static final long serialVersionUID = 1L;
    private Color verdeWeStyle = new Color(106, 143, 123);

    public TelaMensagem(String mensagem, String tipo) {
        setUndecorated(true); 
        setSize(400, 220);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);

        JPanel content = new JPanel(new MigLayout("wrap, align center center, insets 20", "[center, grow]", "[][][]"));
        content.setBackground(Color.WHITE);
        content.setBorder(new LineBorder(verdeWeStyle, 2));
        setContentPane(content);

        JLabel lblStatus = new JLabel(tipo.equalsIgnoreCase("sucesso") ? "SUCESSO" : "ERRO");
        lblStatus.setFont(new Font("Arial", Font.BOLD, 20));
        lblStatus.setForeground(tipo.equalsIgnoreCase("sucesso") ? new Color(46, 139, 87) : Color.RED);
        content.add(lblStatus, "gapbottom 10");

        JLabel lblMsg = new JLabel("<html><div style='text-align: center;'>" + mensagem + "</div></html>", SwingConstants.CENTER);
        lblMsg.setFont(new Font("Arial", Font.PLAIN, 16));
        lblMsg.setForeground(verdeWeStyle);
        content.add(lblMsg, "growx, gapbottom 20, width 350!");
        
        JButton btnOk = new JButton("OK");
        btnOk.setBackground(verdeWeStyle);
        btnOk.setForeground(Color.WHITE);
        btnOk.setFont(new Font("Arial", Font.BOLD, 14));
        btnOk.setFocusPainted(false);
        btnOk.setBorderPainted(false);
        btnOk.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnOk.addActionListener(e -> dispose());

        content.add(btnOk, "width 120!, height 40!");
        
        setVisible(true);
    }
}