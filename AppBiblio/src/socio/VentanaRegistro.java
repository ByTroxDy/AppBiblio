package socio;

import app.Usuario;
import db.UsuarioMaxDB;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VentanaRegistro extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtUsuario, txtEmail;
    private JPasswordField txtPassword;
    private JButton btnVolver, btnRegistro;
    private ArrayList<Usuario> usuarios;
    
    private String nombre, password, email;

	public VentanaRegistro() {
		super("Biblioteca App - Registrar-se");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        usuarios = new ArrayList<>();
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        JLabel lblUsuario = new JLabel("Nom d'usuari:");
        txtUsuario = new JTextField(20);
        
        JLabel lblPassword = new JLabel("Contrasenya:");
        txtPassword = new JPasswordField(20);
        
        JLabel lblEmail = new JLabel("Email");
        txtEmail = new JTextField(20);
        
        btnVolver = new JButton("Enrere");
        btnRegistro = new JButton("Registrar-se");
        
        panel.add(lblUsuario);
		panel.add(txtUsuario);
		panel.add(lblPassword);
		panel.add(txtPassword);
		panel.add(lblEmail);
		panel.add(txtEmail);
		panel.add(btnVolver);
		panel.add(btnRegistro);
        
		getContentPane().add(panel);
		
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
                nombre = txtUsuario.getText().toLowerCase();
                password = new String(txtPassword.getPassword());
                email = txtEmail.getText();
                
                if (password.length() < 6) {
                	JOptionPane.showMessageDialog(panel, "La contrasenya ha de contenir 6 caràcters.", "Registre", JOptionPane.WARNING_MESSAGE);
                	txtPassword.setText("");
                } else if (!validarEmail(email)) {
                	JOptionPane.showMessageDialog(panel, "El correu electrònic ingressat és invàlid.", "Registre", JOptionPane.WARNING_MESSAGE);
                	txtEmail.setText("");
                } else {
                	Usuario nuevoUsuario = new Usuario(nombre, password, email, false);
                    usuarios.add(nuevoUsuario);
                    
                    UsuarioMaxDB usuDB = new UsuarioMaxDB();
                    
                    if (usuDB.guardarRegistro(nombre, password, email)) {
                    	JOptionPane.showMessageDialog(panel, "Registre exitós.", "Registre", JOptionPane.INFORMATION_MESSAGE);
        		        VentanaInicioSesion app = new VentanaInicioSesion();
        	            app.setVisible(true);
        	            dispose();
                    } else {
        				txtUsuario.setText("");
        				txtPassword.setText("");
                    }
                }
            }
        });

		pack();
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
	}
	
	public boolean validarEmail(String email) {
		// Patrón para validar el email
        Pattern pattern = Pattern.compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+");
 
        Matcher mather = pattern.matcher(email);
 
        if (mather.find() == true) {
            return true;
        } else {
            return false;
        }
	}
	
//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				VentanaRegistro registro = new VentanaRegistro();
//				registro.setVisible(true);
//			}
//		});
//	}
}
