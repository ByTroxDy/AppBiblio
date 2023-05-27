package gui;

import db.UsuarioDB;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class VentanaInicioSesion extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnRegistro, btnLogin, btnBuscarDocumento;
    
    public String usuario, password;

    public void setNombreUsuario(String usuario) {
        this.usuario = usuario;
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
        btnBuscarDocumento = new JButton("Buscar Documento");
        
		mainPanel.add(lblUsuario);
		mainPanel.add(txtUsuario);
		mainPanel.add(lblPassword);
		mainPanel.add(txtPassword);
		mainPanel.add(btnRegistro);
		mainPanel.add(btnLogin);
        
		getContentPane().add(mainPanel);
		
		JPanel docPanel = new JPanel();
		docPanel.setLayout(new BorderLayout(0, 0));
		docPanel.setBorder(new EmptyBorder(0, 10, 10, 10));
		
		getContentPane().add(docPanel, BorderLayout.SOUTH);
		docPanel.add(btnBuscarDocumento);

		btnBuscarDocumento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
				dispose();
				VentanaBuscarDocumento ventana = new VentanaBuscarDocumento();
				ventana.setVisible(true);
			}
		});
		
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
				usuario = txtUsuario.getText();
				password = new String(txtPassword.getPassword());
				
				UsuarioDB usuDB = new UsuarioDB();
				boolean exito = usuDB.iniciarSesion(usuario, password);
				
                if (exito) {
                	MenuSocio menu = new MenuSocio();
    	            menu.setVisible(true);
    	            dispose();
                }

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
