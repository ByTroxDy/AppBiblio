package gui;

import javax.swing.*;

import db.UsuarioDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCambiarNombreUsuario extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtUsuarioActual;
    private JPasswordField txtContrasena;
    private JTextField txtNuevoUsuario;
    private JButton btnCancelar;
    private JButton btnGuardarCambios;

    public VentanaCambiarNombreUsuario() {
        setTitle("Cambiar Nombre de Usuario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblUsuarioActual = new JLabel("Usuario actual:");
        txtUsuarioActual = new JTextField();
        JLabel lblContrasena = new JLabel("Contraseña:");
        txtContrasena = new JPasswordField();
        JLabel lblNuevoUsuario = new JLabel("Nuevo nombre de usuario:");
        txtNuevoUsuario = new JTextField();

        btnCancelar = new JButton("Cancelar");
        btnGuardarCambios = new JButton("Guardar Cambios");

        panel.add(lblUsuarioActual);
        panel.add(txtUsuarioActual);
        panel.add(lblContrasena);
        panel.add(txtContrasena);
        panel.add(lblNuevoUsuario);
        panel.add(txtNuevoUsuario);
        panel.add(btnCancelar);
        panel.add(btnGuardarCambios);

        getContentPane().add(panel);
        
        btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
				VentanaEditarPerfil editarPerfil = new VentanaEditarPerfil();
				editarPerfil.setVisible(true);
				dispose();
			}
		});

        btnGuardarCambios.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ex) {
                String usuarioActual = txtUsuarioActual.getText();
                String contrasena = new String(txtContrasena.getPassword());
                String nuevoUsuario = txtNuevoUsuario.getText();
                
                UsuarioDB usuDB = new UsuarioDB();

                // Validar la cuenta del usuario actual
                if (!usuDB.validarCuenta(usuarioActual, contrasena)) {
                    JOptionPane.showMessageDialog(VentanaCambiarNombreUsuario.this,
                            "El usuario actual y la contraseña no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Verificar si el nuevo usuario ya está en uso
                if (usuDB.nombreUsuarioEnUso(nuevoUsuario)) {
                    JOptionPane.showMessageDialog(VentanaCambiarNombreUsuario.this,
                            "El nuevo nombre de usuario ya está en uso. Por favor, elija otro.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Actualizar el usuario en la base de datos
                usuDB.actualizarNombreUsuario(usuarioActual, nuevoUsuario);

                JOptionPane.showMessageDialog(VentanaCambiarNombreUsuario.this,
                        "El nombre de usuario se ha actualizado correctamente.", "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

                dispose();
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
