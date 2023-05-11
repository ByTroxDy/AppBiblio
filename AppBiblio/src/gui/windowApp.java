package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class windowApp extends JFrame {

	private JPanel contentPane;
	private JTextField textUsuario;
	private JTextField textField_1;
	private JTextField textPadron;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					windowApp frame = new windowApp();
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
	public windowApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(111, 65, 46, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblPasswd = new JLabel("Contraseña");
		lblPasswd.setBounds(111, 90, 68, 14);
		contentPane.add(lblPasswd);
		
		JLabel lblPadron = new JLabel("Padrón Municipal");
		lblPadron.setBounds(111, 115, 106, 14);
		contentPane.add(lblPadron);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(227, 59, 86, 20);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(227, 84, 86, 20);
		contentPane.add(password);
		
		textPadron = new JTextField();
		textPadron.setBounds(227, 109, 86, 20);
		contentPane.add(textPadron);
		textPadron.setColumns(10);
		
		JButton btnValidar = new JButton("Validar");
		btnValidar.setBounds(111, 155, 89, 23);
		contentPane.add(btnValidar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(227, 155, 89, 23);
		contentPane.add(btnCancelar);
		
	}
}