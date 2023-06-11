package socio;

import db.UsuarioMaxDB;
import gestor.MenuGestor;
import gestor.VentanaAltaDocumento;
import gestor.VentanaRespaldo;
import gestor.VentanaModificarDocumento;
import admin.MenuAdmin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class VentanaInicioSesion extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	private JButton btnRegistro, btnLogin, btnConsultarDocumento;

	public String usuario, password, grupo;

	public VentanaInicioSesion() {
		super("Biblioteca App - Iniciar sessió");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(3, 2, 10, 10));
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		JLabel lblUsuario = new JLabel("Nom d'usuari:");
		txtUsuario = new JTextField(20);

		JLabel lblPassword = new JLabel("Contrasenya:");
		txtPassword = new JPasswordField(20);

		btnLogin = new JButton("Iniciar sessió");
		btnRegistro = new JButton("Registrar-se");
		btnConsultarDocumento = new JButton("Consultar Document");

		mainPanel.add(lblUsuario);
		mainPanel.add(txtUsuario);
		mainPanel.add(lblPassword);
		mainPanel.add(txtPassword);
		mainPanel.add(btnRegistro);
		mainPanel.add(btnLogin);

		getContentPane().add(mainPanel);

		JPanel docPanel = new JPanel();
		docPanel.setLayout(new BorderLayout(0, 0));
		docPanel.setBorder(new EmptyBorder(0, 10, 10, 10));

		docPanel.add(btnConsultarDocumento);
		getContentPane().add(docPanel, BorderLayout.SOUTH);

		txtPassword.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent ex) {
			}

			@Override
			public void keyPressed(KeyEvent ex) {
				if (ex.getKeyCode() == KeyEvent.VK_ENTER) {
					btnLogin.doClick();
				}
			}

			@Override
			public void keyReleased(KeyEvent ex) {
			}
		});

		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ex) {
				usuario = txtUsuario.getText().toLowerCase();
				password = new String(txtPassword.getPassword());

				UsuarioMaxDB usuDB = new UsuarioMaxDB();
				grupo = usuDB.obtenerGrupo(usuario);

				if (usuDB.iniciarSesion(usuario, password)) {
					
					saveUserAndGroup();

					if (grupo.equals("socio")) {
						MenuSocio menu = new MenuSocio();
						menu.setVisible(true);
					} else if (grupo.equals("gestor")) {
						MenuGestor menu = new MenuGestor();
						menu.setVisible(true);
					} else if (grupo.equals("admin")) {
						MenuAdmin menu = new MenuAdmin();
						menu.setVisible(true);
					}

					dispose();
				}

				// Limpiar los campos de texto después de intentar iniciar sesión
				txtUsuario.setText("");
				txtPassword.setText("");
			}
		});

		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
				VentanaRegistro ventana = new VentanaRegistro();
				ventana.setVisible(true);
				dispose();
			}
		});

		btnConsultarDocumento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
				VentanaConsultarDocumento.usuario = null;
				VentanaConsultarDocumento.grupo = null;
				VentanaConsultarDocumento ventana = new VentanaConsultarDocumento();
				ventana.setVisible(true);
				dispose();
			}
		});

		pack();
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
	}
	
	public void saveUserAndGroup() {
		MenuSocio.usuario = usuario;
		VentanaConsultarDocumento.usuario = usuario;
		VentanaDevolverDocumento.usuario = usuario;
		VentanaAgregarComentario.usuario = usuario;
		VentanaCambiarNombreUsuario.usuarioActual = usuario;
		VentanaCambiarPassword.usuario = usuario;
		VentanaCambiarEmail.usuario = usuario;
		VentanaConsultarDocumento.grupo = grupo;
		VentanaAltaDocumento.grupo = grupo;
		VentanaModificarDocumento.grupo = grupo;
		VentanaRespaldo.grupo = grupo;
	}
}
