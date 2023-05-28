package gui;

import db.UsuarioMaxDB;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCambiarNombreUsuario extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtUsuarioActual, txtNuevoUsuario;
	private JPasswordField txtContrasena;
	private JButton btnCancelar, btnGuardarCambios;

	private String usuarioActual, nuevoUsuario, contrasena;

	public VentanaCambiarNombreUsuario() {
		setTitle("Cambiar Nombre de Usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 2, 10, 10));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JLabel lblUsuarioActual = new JLabel("Usuario actual:");
		txtUsuarioActual = new JTextField();

		JLabel lblNuevoUsuario = new JLabel("Nuevo usuario:");
		txtNuevoUsuario = new JTextField();

		JLabel lblContrasena = new JLabel("Contraseña:");
		txtContrasena = new JPasswordField();

		btnCancelar = new JButton("Cancelar");
		btnGuardarCambios = new JButton("Guardar Cambios");

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
					JOptionPane.showMessageDialog(panel, "El usuario actual y la contraseña no coinciden.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else // Verificar si el nuevo usuario ya está en uso
				if (usuDB.nombreUsuarioEnUso(nuevoUsuario)) {
					JOptionPane.showMessageDialog(panel,
							"El nuevo nombre de usuario ya está en uso. Por favor, elija otro.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else { // Actualizar el usuario en la base de datos
					usuDB.actualizarNombreUsuario(usuarioActual, nuevoUsuario);
					JOptionPane.showMessageDialog(panel, "El nombre de usuario se ha actualizado correctamente.",
							"Éxito", JOptionPane.INFORMATION_MESSAGE);
				}

				txtUsuarioActual.setText("");
				txtNuevoUsuario.setText("");
				txtContrasena.setText("");
			}
		});

		pack();
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				VentanaCambiarNombreUsuario ventana = new VentanaCambiarNombreUsuario();
				ventana.setVisible(true);
			}
		});
	}
}
