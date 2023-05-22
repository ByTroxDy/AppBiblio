package gui;

import javax.swing.*;

import app.ConexionDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VentanaCambiarEmail extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JTextField txtEmail;
    private JButton btnCancelar;
    private JButton btnGuardarCambios;

    public VentanaCambiarEmail() {
        setTitle("Cambiar Email");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblUsuario = new JLabel("Nombre de Usuario:");
        txtUsuario = new JTextField();

        JLabel lblContrasena = new JLabel("Contraseña:");
        txtContrasena = new JPasswordField();

        JLabel lblEmail = new JLabel("Email:");
        txtEmail = new JTextField();
        
        btnCancelar = new JButton("Cancelar");
        btnGuardarCambios = new JButton("Guardar Cambios");

        panel.add(lblUsuario);
        panel.add(txtUsuario);
        panel.add(lblContrasena);
        panel.add(txtContrasena);
        panel.add(lblEmail);
        panel.add(txtEmail);
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
                String contrasena = new String(txtContrasena.getPassword());
                String email = txtEmail.getText();

                cambiarEmail(usuario, contrasena, email);
            }
        });

        pack();
        setLocationRelativeTo(null);
    }

    private void cambiarEmail(String usuario, String contrasena, String email) {
    	
    	VentanaCambiarNombreUsuario ventana = new VentanaCambiarNombreUsuario();
    	
        // Validar la cuenta para verificar si el usuario y la contraseña coinciden
        boolean cuentaValida = ventana.validarCuenta(usuario, contrasena);

        if (!cuentaValida) {
            JOptionPane.showMessageDialog(this, "La contraseña actual es incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = ConexionDB.getConnection()) {

            String query = "UPDATE usuarios SET email = ? WHERE usuario = ?";
            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, usuario);

            // Ejecutar la consulta
            statement.executeUpdate();

            JOptionPane.showMessageDialog(this, "Email actualizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
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
                VentanaCambiarEmail ventana = new VentanaCambiarEmail();
                ventana.setVisible(true);
            }
        });
    }
}
