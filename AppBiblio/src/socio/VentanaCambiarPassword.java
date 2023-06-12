package socio;

import db.UsuarioMaxDB;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class VentanaCambiarPassword extends JFrame {
	private JTextField txtUsuario;
    private JPasswordField txtContrasenaActual, txtNuevaContrasena;
    private JButton btnCancelar, btnGuardarCambios;
    
    private String contrasenaActual, nuevaContrasena;
    static String usuario;

    public VentanaCambiarPassword() {
    	super("Biblioteca App - Canviar Contrasenya");
        setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaCambiarPassword.class.getResource("/img/icono32.png")));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblUsuario = new JLabel("Nom d'usuari:");
        txtUsuario = new JTextField(20);
        txtUsuario.setEditable(false);
        txtUsuario.setText(usuario);

        JLabel lblContrasenaActual = new JLabel("Contrasenya Actual:");
        txtContrasenaActual = new JPasswordField(20);

        JLabel lblNuevaContrasena = new JLabel("Nova Contrasenya:");
        txtNuevaContrasena = new JPasswordField(20);
        
        btnCancelar = new JButton("Cancel·la");
        btnGuardarCambios = new JButton("Desa");

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
                usuario = txtUsuario.getText();
                contrasenaActual = new String(txtContrasenaActual.getPassword());
                nuevaContrasena = new String(txtNuevaContrasena.getPassword());
                
                if (nuevaContrasena.length() < 8) {
                	JOptionPane.showMessageDialog(panel, "La contrasenya ha de contenir 8 caràcters.", "Registre", JOptionPane.WARNING_MESSAGE);
                	txtContrasenaActual.setText("");
                	txtNuevaContrasena.setText("");
                } else {
                
	                UsuarioMaxDB usuDB = new UsuarioMaxDB();
	                if (!usuDB.validarCuenta(usuario, contrasenaActual)) {
	                	JOptionPane.showMessageDialog(panel, "La contrasenya actual és incorrecta.", "Error", JOptionPane.ERROR_MESSAGE);
	                } else if (usuDB.cambiarContrasena(usuario, nuevaContrasena)) {
	                	JOptionPane.showMessageDialog(panel, "Contrasenya actualitzada correctament.", "Èxit", JOptionPane.INFORMATION_MESSAGE);
	                }
	                
	                txtContrasenaActual.setText("");
	                txtNuevaContrasena.setText("");
                }
            }
        });

        pack();
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    }
}
