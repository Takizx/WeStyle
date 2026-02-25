package Projetofinal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JInternalFrame;
import javax.swing.UIManager;
import javax.swing.JTextField;

public class Telacadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Telacadastro frame = new Telacadastro();
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
	public Telacadastro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1212, 1010);
		contentPane = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) contentPane.getLayout();
		flowLayout_1.setHgap(280);
		flowLayout_1.setVgap(200);
		contentPane.setBackground(new Color(106, 143, 123));
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(UIManager.getBorder("ComboBox.editorBorder"));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(200);
		flowLayout.setHgap(280);
		contentPane.add(panel);

	}

}
