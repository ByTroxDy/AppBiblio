package socio;

import db.UsuarioMaxDB;
import a.Inicio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class VentanaCambiarNombreUsuario extends JFrame {
	private JTextField txtUsuarioActual, txtNuevoUsuario;
	private JPasswordField txtContrasena;
	private JButton btnCancelar, btnGuardarCambios;

	private String nuevoUsuario, contrasena;
	static String usuarioActual;

	public VentanaCambiarNombreUsuario() {
		super("Biblioteca App - Canvia nom d'usuari");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaCambiarNombreUsuario.class.getResource("/img/icono32.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 2, 10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblUsuarioActual = new JLabel("Usuari actual:");
        txtUsuarioActual = new JTextField(20);
        txtUsuarioActual.setEditable(false);
        txtUsuarioActual.setText(usuarioActual);

		JLabel lblNuevoUsuario = new JLabel("Nou usuari:");
		txtNuevoUsuario = new JTextField(20);

		JLabel lblContrasena = new JLabel("Contrasenya:");
		txtContrasena = new JPasswordField(20);

		btnCancelar = new JButton("Cancel·la");
		btnGuardarCambios = new JButton("Desa");

		panel.add(lblUsuarioActual);
		panel.add(txtUsuarioActual);
		panel.add(lblNuevoUsuario);
		panel.add(txtNuevoUsuario);
		panel.add(lblContrasena);
		panel.add(txtContrasena);
		panel.add(btnCancelar);
		panel.add(btnGuardarCambios);

		getContentPane().add(panel);

		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
				VentanaEditarPerfil ventana = new VentanaEditarPerfil();
				ventana.setVisible(true);
				dispose();
			}
		});

		btnGuardarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
				usuarioActual = txtUsuarioActual.getText();
				nuevoUsuario = txtNuevoUsuario.getText();
				contrasena = new String(txtContrasena.getPassword());

				UsuarioMaxDB usuDB = new UsuarioMaxDB();

				// Validar la cuenta del usuario actual
				if (!usuDB.validarCuenta(usuarioActual, contrasena)) {
					JOptionPane.showMessageDialog(panel, "L'usuari actual i la contrasenya no coincideixen.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else // Verificar si el nuevo usuario ya está en uso
				if (usuDB.nombreUsuarioEnUso(nuevoUsuario)) {
					JOptionPane.showMessageDialog(panel,
							"El nom d'usuari nou ja està en ús. Si us plau, escolliu un altre.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else { // Actualizar el usuario en la base de datos
					usuDB.actualizarNombreUsuario(usuarioActual, nuevoUsuario);
					JOptionPane.showMessageDialog(panel, "S'ha actualitzat correctament el nom d'usuari.",
							"Èxit", JOptionPane.INFORMATION_MESSAGE);
					
					Inicio app = new Inicio();
					app.setVisible(true);
					dispose();
				}

				txtUsuarioActual.setText("");
				txtNuevoUsuario.setText("");
				txtContrasena.setText("");
			}
		});

		pack();
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
	}
}
