package gui;

import app.ConexionDB;

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

public class VentanaInicioSesion extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnRegistro;
    
    private static String usuario;

    public static String getNombreUsuario() {
        return usuario;
    }

	public VentanaInicioSesion() {
		super("Biblioteca App - Iniciar sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2, 10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JLabel lblUsuario = new JLabel("Nombre de usuario:");
        txtUsuario = new JTextField(20);
        
        JLabel lblPassword = new JLabel("Contraseña:");
        txtPassword = new JPasswordField(20);
        
        btnLogin = new JButton("Iniciar sesión");
        btnRegistro = new JButton("Registrarse");
        
		mainPanel.add(lblUsuario);
		mainPanel.add(txtUsuario);
		mainPanel.add(lblPassword);
		mainPanel.add(txtPassword);
		mainPanel.add(btnRegistro);
		mainPanel.add(btnLogin);
        
		getContentPane().add(mainPanel);
		
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
				String usuario = txtUsuario.getText();
				String password = new String(txtPassword.getPassword());
				
				iniciarSesion(usuario, password);

				// Limpiar los campos de texto después de intentar iniciar sesión
				txtUsuario.setText("");
				txtPassword.setText("");
			}
		});
        
        btnRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ex) {
            	VentanaRegistro registro = new VentanaRegistro();
            	registro.setVisible(true);
				dispose();
            }
        });

		pack();
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
	}

	public void iniciarSesion(String usuario, String password) {
	    try (Connection conn = ConexionDB.getConnection()) {

	        String query = "SELECT COUNT(*) FROM usuarios WHERE usuario = ? AND password = ?";
	        
	        PreparedStatement statement = conn.prepareStatement(query);
	        statement.setString(1, usuario);
	        statement.setString(2, password);
	        ResultSet resultSet = statement.executeQuery();
	        resultSet.next();
	        int count = resultSet.getInt(1);
	        if (count > 0) {
	        	VentanaInicioSesion.usuario = usuario;
	            MenuSocio menu = new MenuSocio();
	            menu.setVisible(true);
	            dispose();
	        } else {
	            // El usuario y la contraseña no coinciden, mostrar mensaje de error
	            JOptionPane.showMessageDialog(VentanaInicioSesion.this, "Usuario o contraseña incorrectos", "Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
	        }

	        statement.close();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(VentanaInicioSesion.this, "Error al iniciar sesión", "Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
	    } finally {
        	try {
				ConexionDB.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
	}

	public void mostrarVentanaPrincipal() {
		
		MenuSocio menu = new MenuSocio();
		menu.setVisible(true);
		dispose();
		
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				VentanaInicioSesion app = new VentanaInicioSesion();
				app.setVisible(true);
			}
		});
	}

}
