package socio;

import db.UsuarioMaxDB;
import a.Inicio;
import gestor.MenuGestor;
import gestor.VentanaActivarDoc;
import gestor.VentanaAltaDoc;
import gestor.VentanaModiDoc;
import gestor.VentanaRespaldo;
import admin.MenuAdmin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@SuppressWarnings("serial")
public class VentanaInicioSesion extends JFrame {
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	private JButton btnVolver, btnLogin, btnRecuperar;

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
		btnVolver = new JButton("Tornar");
		btnRecuperar = new JButton("He oblidat la meva contrasenya");

		mainPanel.add(lblUsuario);
		mainPanel.add(txtUsuario);
		mainPanel.add(lblPassword);
		mainPanel.add(txtPassword);
		mainPanel.add(btnVolver);
		mainPanel.add(btnLogin);

		getContentPane().add(mainPanel);

		JPanel docPanel = new JPanel();
		docPanel.setLayout(new BorderLayout(0, 0));
		docPanel.setBorder(new EmptyBorder(0, 10, 10, 10));

		docPanel.add(btnRecuperar);
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

		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
				Inicio app = new Inicio();
				app.setVisible(true);
				dispose();
			}
		});

		btnRecuperar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
				VentanaRecuperar ventana = new VentanaRecuperar();
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
		VentanaActivarDoc.grupo = grupo;
		VentanaAltaDoc.grupo = grupo;
		VentanaModiDoc.grupo = grupo;
		VentanaRespaldo.grupo = grupo;
	}
}
