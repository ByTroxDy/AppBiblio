package gui;

import app.Usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class BibliotecaApp extends JFrame {
	private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnRegistro;
    private String nombreUsuario;
    private ArrayList<Usuario> usuarios;

	public BibliotecaApp() {
		super("Biblioteca App - Iniciar sesión o Registrarse");
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
				char[] password = txtPassword.getPassword();

				// Verificar credenciales y realizar las acciones correspondientes
				if (verificarCredenciales(usuario, password)) {
					nombreUsuario = usuario;
					JOptionPane.showMessageDialog(BibliotecaApp.this, "Inicio de sesión exitoso", "Bienvenido", JOptionPane.INFORMATION_MESSAGE);
					// Mostrar la ventana principal de la aplicación
					mostrarVentanaPrincipal();
				} else {
					JOptionPane.showMessageDialog(BibliotecaApp.this, "Credenciales inválidas",
							"Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
				}

				// Limpiar los campos de texto después de intentar iniciar sesión
				txtUsuario.setText("");
				txtPassword.setText("");
			}
		});
        
        btnRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = txtUsuario.getText();
                String password = new String(txtPassword.getPassword());

                Usuario nuevoUsuario = new Usuario(nombre, password, null);
                usuarios.add(nuevoUsuario);
                
                JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente");
            }
        });

		pack();
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
	}
	
	private boolean verificarCredenciales(String usuario, char[] password) {
	    // Convertir el arreglo de caracteres del password a String
	    String passwordString = new String(password);

	    for (Usuario u : usuarios) {
	        if (u.getNombre().equals(usuario) && u.getPassword().equals(passwordString)) {
	            return true;
	        }
	    }
	    return false;
	}


	public void mostrarVentanaPrincipal() {
		
		MenuSocio menu = new MenuSocio();
		menu.setVisible(true);
		dispose();
		
	}
	
	public String getNombreUsuario() {
        return nombreUsuario;
    }

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				BibliotecaApp app = new BibliotecaApp();
				app.setVisible(true);
			}
		});
	}

}
