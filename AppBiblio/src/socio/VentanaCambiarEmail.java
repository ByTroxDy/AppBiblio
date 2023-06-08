package socio;

import javax.swing.*;

import db.UsuarioMaxDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCambiarEmail extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtUsuario, txtEmail;
    private JPasswordField txtContrasena;
    private JButton btnCancelar, btnGuardarCambios;
    
    private String usuario, contrasena, email;

    public VentanaCambiarEmail() {
        setTitle("Canviar Email");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblUsuario = new JLabel("Nom d'usuari:");
        txtUsuario = new JTextField();

        JLabel lblContrasena = new JLabel("Contrasenya:");
        txtContrasena = new JPasswordField();

        JLabel lblEmail = new JLabel("Email:");
        txtEmail = new JTextField();
        
        btnCancelar = new JButton("Cancel·la");
        btnGuardarCambios = new JButton("Desa Canvis");

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
                } else if (usuDB.cambiarEmail(usuario, email)) {
                	JOptionPane.showMessageDialog(panel, "Email actualitzat correctament.", "Èxit", JOptionPane.INFORMATION_MESSAGE);
                }
                
                txtUsuario.setText("");
                txtContrasena.setText("");
                txtEmail.setText("");
            }
        });

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                VentanaCambiarEmail ventana = new VentanaCambiarEmail();
                ventana.setVisible(true);
            }
        });
    }
}
