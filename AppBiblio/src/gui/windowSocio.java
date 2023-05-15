package gui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class windowSocio extends JFrame {

	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					windowSocio frame = new windowSocio();
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
	public windowSocio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle("Nombre del formulario");
		
		JLabel lbl = new JLabel("Usuario");
		lbl.setBounds(111, 65, 46, 14);
		contentPane.add(lbl);
		
		JLabel lblPasswd = new JLabel("Contraseña");
		lblPasswd.setBounds(111, 90, 68, 14);
		contentPane.add(lblPasswd);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(227, 59, 86, 20);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(227, 84, 86, 20);
		contentPane.add(password);
		
		JButton btnValidar = new JButton("Validar");
		btnValidar.setBounds(111, 155, 89, 23);
		contentPane.add(btnValidar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(227, 155, 89, 23);
		contentPane.add(btnCancelar);
		
	}
}
