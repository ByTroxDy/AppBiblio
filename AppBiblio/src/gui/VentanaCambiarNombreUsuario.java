package gui;

import javax.swing.*;

import app.ConexionDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

                // Validar la cuenta del usuario actual
                if (!validarCuenta(usuarioActual, contrasena)) {
                    JOptionPane.showMessageDialog(VentanaCambiarNombreUsuario.this,
                            "El usuario actual y la contraseña no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Verificar si el nuevo usuario ya está en uso
                if (nombreUsuarioEnUso(nuevoUsuario)) {
                    JOptionPane.showMessageDialog(VentanaCambiarNombreUsuario.this,
                            "El nuevo nombre de usuario ya está en uso. Por favor, elija otro.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Actualizar el usuario en la base de datos
                actualizarNombreUsuario(usuarioActual, nuevoUsuario);

                JOptionPane.showMessageDialog(VentanaCambiarNombreUsuario.this,
                        "El nombre de usuario se ha actualizado correctamente.", "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

                dispose();
            }
        });

        pack();
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    }

    public boolean validarCuenta(String usuarioActual, String contrasena) {
    	try (Connection conn = ConexionDB.getConnection()) {

            String query = "SELECT COUNT(*) FROM usuarios WHERE usuario = ? AND password = ?";
            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, usuarioActual);
            statement.setString(2, contrasena);

            // Ejecutar la consulta y obtener el resultado
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	try {
				ConexionDB.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }

        return false;
    }

    private boolean nombreUsuarioEnUso(String nuevoUsuario) {
    	try (Connection conn = ConexionDB.getConnection()) {

            // Crear la consulta SQL para verificar el nuevo usuario
            String query = "SELECT COUNT(*) FROM usuarios WHERE usuario = ?";
            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, nuevoUsuario);

            // Ejecutar la consulta y obtener el resultado
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	try {
				ConexionDB.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }

        return false;
    }

    private void actualizarNombreUsuario(String usuarioActual, String nuevoUsuario) {
    	try (Connection conn = ConexionDB.getConnection()) {

            // Crear la consulta SQL para actualizar el usuario
            String query = "UPDATE usuarios SET usuario = ? WHERE usuario = ?";
            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, nuevoUsuario);
            statement.setString(2, usuarioActual);

            // Ejecutar la consulta
            statement.executeUpdate();
            
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
                VentanaCambiarNombreUsuario ventana = new VentanaCambiarNombreUsuario();
                ventana.setVisible(true);
            }
        });
    }
}
