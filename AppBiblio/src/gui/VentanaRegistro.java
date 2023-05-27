package gui;

import app.Usuario;
import db.UsuarioDB;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class VentanaRegistro extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnVolver, btnRegistro;
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
        
        btnVolver = new JButton("Volver");
        btnRegistro = new JButton("Registrarse");
        
		mainPanel.add(lblUsuario);
		mainPanel.add(txtUsuario);
		mainPanel.add(lblPassword);
		mainPanel.add(txtPassword);
		mainPanel.add(btnVolver);
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
		
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
				VentanaInicioSesion app = new VentanaInicioSesion();
				app.setVisible(true);
				dispose();
			}
		});
        
        btnRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = txtUsuario.getText();
                String password = new String(txtPassword.getPassword());

                Usuario nuevoUsuario = new Usuario(nombre, password, null, false);
                usuarios.add(nuevoUsuario);
                
                UsuarioDB usuDB = new UsuarioDB();
                boolean exito = usuDB.guardarRegistro(nombre, password);
                
                if (exito) {
    		        VentanaInicioSesion app = new VentanaInicioSesion();
    	            app.setVisible(true);
    	            dispose();
                }
                
				// Limpiar los campos de texto después de intentar iniciar sesión
				txtUsuario.setText("");
				txtPassword.setText("");
            }
        });

		pack();
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
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
