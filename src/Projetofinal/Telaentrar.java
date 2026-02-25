package Projetofinal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;

public class Telaentrar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Telaentrar frame = new Telaentrar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Telaentrar() {
		setBackground(new Color(106, 143, 123));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1216, 1010);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(106, 143, 123));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(106, 143, 123));
		contentPane.add(panel, "name_5024366473200");
		panel.setLayout(new CardLayout(280, 200));
		
		JPanel quadrobrancomaior = new JPanel();
		quadrobrancomaior.setBorder(new CompoundBorder(new CompoundBorder(null, null), null));
		FlowLayout flowLayout = (FlowLayout) quadrobrancomaior.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		panel.add(quadrobrancomaior, "name_5128580973900");

	}
}

