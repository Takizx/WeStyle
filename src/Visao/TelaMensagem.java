package Visao;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Window;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import net.miginfocom.swing.MigLayout;

public class TelaMensagem extends JPanel {

    private static final long serialVersionUID = 1L;
    private Color verdeWeStyle = new Color(106, 143, 123);

    public TelaMensagem(String mensagem, String tipo) {
        this.setLayout(new MigLayout("wrap, align center center, insets 20", "[center, grow]", "[][][]"));
        this.setBackground(Color.WHITE);
        this.setBorder(new LineBorder(verdeWeStyle, 2));

        JLabel lblStatus = new JLabel(tipo.equalsIgnoreCase("sucesso") ? "SUCESSO" : "ERRO");
        lblStatus.setFont(new Font("Arial", Font.BOLD, 20));
        lblStatus.setForeground(tipo.equalsIgnoreCase("sucesso") ? new Color(46, 139, 87) : Color.RED);
        this.add(lblStatus, "gapbottom 10");

        JLabel lblMsg = new JLabel("<html><div style='text-align: center;'>" + mensagem + "</div></html>", SwingConstants.CENTER);
        lblMsg.setFont(new Font("Arial", Font.PLAIN, 16));
        lblMsg.setForeground(verdeWeStyle);
        this.add(lblMsg, "growx, gapbottom 20, width 350!");
        
        JButton btnOk = new JButton("OK");
        btnOk.setBackground(verdeWeStyle);
        btnOk.setForeground(Color.WHITE);
        btnOk.setFont(new Font("Arial", Font.BOLD, 14));
        btnOk.setFocusPainted(false);
        btnOk.setBorderPainted(false);
        btnOk.setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.add(btnOk, "width 120!, height 40!");

        Window ancestral = SwingUtilities.getWindowAncestor(this);
        JDialog dialogo = new JDialog(ancestral, "", Dialog.ModalityType.APPLICATION_MODAL);
        dialogo.setUndecorated(true);
        dialogo.setSize(400, 220);
        dialogo.setLocationRelativeTo(ancestral);
        dialogo.setAlwaysOnTop(true);
        dialogo.getContentPane().add(this);

        btnOk.addActionListener(e -> dialogo.dispose());
        
        dialogo.setVisible(true);
    }
}