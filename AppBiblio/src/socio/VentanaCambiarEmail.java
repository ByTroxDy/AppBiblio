package socio;

import javax.swing.*;

import db.UsuarioMaxDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("serial")
public class VentanaCambiarEmail extends JFrame {
	private JTextField txtUsuario, txtEmail;
    private JPasswordField txtContrasena;
    private JButton btnCancelar, btnGuardarCambios;
    
    private String contrasena, email;
    static String usuario;

    public VentanaCambiarEmail() {
        setTitle("Canviar Email");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblUsuario = new JLabel("Nom d'usuari:");
        txtUsuario = new JTextField(20);
        txtUsuario.setEditable(false);
        txtUsuario.setText(usuario);

        JLabel lblContrasena = new JLabel("Contrasenya:");
        txtContrasena = new JPasswordField(20);

        JLabel lblEmail = new JLabel("Email:");
        txtEmail = new JTextField(20);
        
        btnCancelar = new JButton("Cancel·la");
        btnGuardarCambios = new JButton("Desa");

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
                usuario = txtUsuario.getText();
                contrasena = new String(txtContrasena.getPassword());
                email = txtEmail.getText();
                
                UsuarioMaxDB usuDB = new UsuarioMaxDB();
                if (!usuDB.validarCuenta(usuario, contrasena)) {
                	JOptionPane.showMessageDialog(panel, "La contrasenya actual és incorrecta.", "Error", JOptionPane.ERROR_MESSAGE);
                	txtContrasena.setText("");
                } else if (!validarEmail(email)) {
                	JOptionPane.showMessageDialog(panel, "El correu electrònic ingressat és invàlid.", "Error", JOptionPane.ERROR_MESSAGE);
                	txtEmail.setText("");
                } else if (usuDB.cambiarEmail(usuario, email)) {
                	JOptionPane.showMessageDialog(panel, "Email actualitzat correctament.", "Èxit", JOptionPane.INFORMATION_MESSAGE);
                }
                
                txtContrasena.setText("");
                txtEmail.setText("");
            }
        });

        pack();
        setLocationRelativeTo(null);
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
}
