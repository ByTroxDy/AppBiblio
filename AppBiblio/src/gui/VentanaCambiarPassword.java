package gui;

import javax.swing.*;

import app.ConexionDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VentanaCambiarPassword extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtUsuario;
    private JPasswordField txtContrasenaActual;
    private JPasswordField txtNuevaContrasena;
    private JButton btnCancelar;
    private JButton btnGuardarCambios;

    public VentanaCambiarPassword() {
        setTitle("Cambiar Contraseña");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblUsuario = new JLabel("Nombre de Usuario:");
        txtUsuario = new JTextField();

        JLabel lblContrasenaActual = new JLabel("Contraseña Actual:");
        txtContrasenaActual = new JPasswordField();

        JLabel lblNuevaContrasena = new JLabel("Nueva Contraseña:");
        txtNuevaContrasena = new JPasswordField();
        
        btnCancelar = new JButton("Cancelar");
        btnGuardarCambios = new JButton("Cambiar Contraseña");

        panel.add(lblUsuario);
        panel.add(txtUsuario);
        panel.add(lblContrasenaActual);
        panel.add(txtContrasenaActual);
        panel.add(lblNuevaContrasena);
        panel.add(txtNuevaContrasena);
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
                String usuario = txtUsuario.getText();
                String contrasenaActual = new String(txtContrasenaActual.getPassword());
                String nuevaContrasena = new String(txtNuevaContrasena.getPassword());

                cambiarContrasena(usuario, contrasenaActual, nuevaContrasena);
            }
        });

        pack();
        setLocationRelativeTo(null);
    }

    private void cambiarContrasena(String usuario, String contrasenaActual, String nuevaContrasena) {
    	
    	VentanaCambiarNombreUsuario ventana = new VentanaCambiarNombreUsuario();
    	
        // Validar la cuenta para verificar si el usuario y la contraseña coinciden
        boolean cuentaValida = ventana.validarCuenta(usuario, contrasenaActual);

        if (!cuentaValida) {
            JOptionPane.showMessageDialog(this, "La contraseña actual es incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = ConexionDB.getConnection()) {

            String query = "UPDATE usuarios SET password = ? WHERE usuario = ?";
            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, nuevaContrasena);
            statement.setString(2, usuario);

            // Ejecutar la consulta
            statement.executeUpdate();

            JOptionPane.showMessageDialog(this, "Contraseña actualizada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	try {
				ConexionDB.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                VentanaCambiarPassword ventana = new VentanaCambiarPassword();
                ventana.setVisible(true);
            }
        });
    }
}
