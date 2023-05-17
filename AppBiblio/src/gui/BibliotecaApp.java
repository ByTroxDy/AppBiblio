package gui;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BibliotecaApp extends JFrame {
	private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnRegistro;
    private JButton btnSalir;

	public BibliotecaApp() {
		super("Biblioteca App - Iniciar sesión o Registrarse");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 2, 10, 10));
        
        JLabel lblUsuario = new JLabel("Nombre de usuario:");
        txtUsuario = new JTextField(20);
        
        JLabel lblPassword = new JLabel("Contraseña:");
        txtPassword = new JPasswordField(20);
        
        btnLogin = new JButton("Iniciar sesión");
        btnRegistro = new JButton("Registrarse");
		btnSalir = new JButton("Salir");
        
		mainPanel.add(lblUsuario);
		mainPanel.add(txtUsuario);
		mainPanel.add(lblPassword);
		mainPanel.add(txtPassword);
		mainPanel.add(btnRegistro);
		mainPanel.add(btnLogin);
		mainPanel.add(new JLabel());
		mainPanel.add(btnSalir);
        
		getContentPane().add(mainPanel);
		
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ex) {
				String usuario = txtUsuario.getText();
				char[] password = txtPassword.getPassword();

				// Verificar credenciales y realizar las acciones correspondientes
				if (verificarCredenciales(usuario, password)) {
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
                // Aquí puedes agregar la lógica para registrar al usuario en la base de datos
                JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente");
            }
        });
		
		btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ex) {
                System.exit(0);
            }
        });

		pack();
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
	}
	
	private boolean verificarCredenciales(String usuario, char[] password) {
		// Convertir el arreglo de caracteres del password a String
		String passwordString = new String(password);

		// Verificar las credenciales del usuario
		if (usuario.equals("admin") && passwordString.equals("admin")) {
			return true; // Credenciales válidas para el usuario admin
		} else {
			return false; // Credenciales inválidas
		}
	}

	public void mostrarVentanaPrincipal() {
		
		MenuSocio menu = new MenuSocio();
		menu.setVisible(true);
		setVisible(false);
		
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
