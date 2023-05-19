package gui;

import app.ConexionDB;
import app.Usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VentanaRegistro extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnRegistro;
    private ArrayList<Usuario> usuarios;

	public VentanaRegistro() {
		super("Biblioteca App - Registrarse");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        usuarios = new ArrayList<>();
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2, 10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JLabel lblUsuario = new JLabel("Nombre de usuario:");
        txtUsuario = new JTextField(20);
        
        JLabel lblPassword = new JLabel("Contraseña:");
        txtPassword = new JPasswordField(20);
        
        btnRegistro = new JButton("Registrarse");
        
		mainPanel.add(lblUsuario);
		mainPanel.add(txtUsuario);
		mainPanel.add(lblPassword);
		mainPanel.add(txtPassword);
		mainPanel.add(new JLabel());
		mainPanel.add(btnRegistro);
        
		getContentPane().add(mainPanel);
		
		txtPassword.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ex) {
            }

            @Override
            public void keyPressed(KeyEvent ex) {
                if (ex.getKeyCode() == KeyEvent.VK_ENTER) {
                	btnRegistro.doClick();
                }
            }

            @Override
            public void keyReleased(KeyEvent ex) {
            }
        });
        
        btnRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = txtUsuario.getText();
                String password = new String(txtPassword.getPassword());

                Usuario nuevoUsuario = new Usuario(nombre, password, null);
                usuarios.add(nuevoUsuario);
                
                guardarRegistro(nombre, password);
            }
        });

		pack();
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
	}

	public void guardarRegistro(String usuario, String password) {
	    try (Connection conn = ConexionDB.getConnection()) {
	    	
	    	// Verificar si el nombre de usuario ya existe
	        String queryVerificacion = "SELECT COUNT(*) FROM usuarios WHERE nombre_usuario = ?";
	        PreparedStatement statementVerificacion = conn.prepareStatement(queryVerificacion);
	        statementVerificacion.setString(1, usuario);
	        ResultSet resultSet = statementVerificacion.executeQuery();
	        resultSet.next();
	        int count = resultSet.getInt(1);
	        if (count > 0) {
	            // El nombre de usuario ya existe, mostrar mensaje de error
	            JOptionPane.showMessageDialog(VentanaRegistro.this, "El nombre de usuario ya está registrado", "Registro", JOptionPane.ERROR_MESSAGE);
	            statementVerificacion.close();
	        } else {
		        statementVerificacion.close();
		        
		        String query = "INSERT INTO usuarios (nombre_usuario, password, rol) VALUES (?, ?, ?)";
		        PreparedStatement statement = conn.prepareStatement(query);
		        statement.setString(1, usuario);
		        statement.setString(2, password);
		        statement.setString(3, "socio");
		        statement.executeUpdate();
	
		        statement.close();
	
		        JOptionPane.showMessageDialog(VentanaRegistro.this, "Registro exitoso", "Registro", JOptionPane.INFORMATION_MESSAGE);
		        
		        VentanaInicioSesion app = new VentanaInicioSesion();
	            app.setVisible(true);
	            dispose();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(VentanaRegistro.this, "Error al registrar usuario", "Registro", JOptionPane.ERROR_MESSAGE);
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
			@Override
			public void run() {
				VentanaRegistro registro = new VentanaRegistro();
				registro.setVisible(true);
			}
		});
	}

}
