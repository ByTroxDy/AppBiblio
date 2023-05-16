package gui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JToggleButton;
import javax.swing.JSeparator;
import java.awt.SystemColor;
import java.awt.Window.Type;
import java.awt.Toolkit;
import java.awt.Font;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage("/home/migrio01/Documents/GitHub/AppBiblio/Iconos/icono.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 270);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setTitle("Editar Perfil");
		
		JLabel lbl = new JLabel("Usuario");
		lbl.setForeground(SystemColor.textText);
		lbl.setBounds(121, 71, 46, 14);
		contentPane.add(lbl);
		
		JLabel lblPasswd = new JLabel("Contraseña");
		lblPasswd.setForeground(SystemColor.textText);
		lblPasswd.setBounds(121, 96, 68, 14);
		contentPane.add(lblPasswd);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(237, 65, 86, 20);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(237, 90, 86, 20);
		contentPane.add(password);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Century Schoolbook L", Font.BOLD, 12));
		btnAceptar.setBackground(SystemColor.window);
		btnAceptar.setForeground(SystemColor.desktop);
		btnAceptar.setBounds(121, 127, 89, 23);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Century Schoolbook L", Font.BOLD, 12));
		btnCancelar.setBackground(SystemColor.window);
		btnCancelar.setForeground(SystemColor.desktop);
		btnCancelar.setBounds(237, 127, 89, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblModificar = new JLabel("MODIFICAR");
		lblModificar.setForeground(SystemColor.activeCaption);
		lblModificar.setBounds(121, 42, 89, 17);
		contentPane.add(lblModificar);
		
	}
}
