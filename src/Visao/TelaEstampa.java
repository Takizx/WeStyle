package Visao;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaEstampa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField jtfCaminhoArquivo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaEstampa frame = new TelaEstampa();
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
	public TelaEstampa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 713, 557);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[594px]", "[184px][186px]"));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 0,alignx center,aligny center");
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel("New label");
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, "cell 0 1,alignx center,aligny center");
		
		jtfCaminhoArquivo = new JTextField();
		jtfCaminhoArquivo.setEditable(false);
		panel_1.add(jtfCaminhoArquivo);
		jtfCaminhoArquivo.setColumns(40);
		
		JButton btnNewButton = new JButton("Enviar arquivo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser seletor = new JFileChooser();
				int resultado = seletor.showOpenDialog(null); // Abre a janela de seleção

				if (resultado == JFileChooser.APPROVE_OPTION) {
				    java.io.File arquivoSelecionado = seletor.getSelectedFile();
				    jtfCaminhoArquivo.setText(arquivoSelecionado.getAbsolutePath());
				}
			}
		});
		panel_1.add(btnNewButton);

	}

}
