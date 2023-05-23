package gui;

import javax.swing.*;

import db.UsuarioDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCambiarPassword extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtUsuario;
    private JPasswordField txtContrasenaActual;
    private JPasswordField txtNuevaContrasena;
    private JButton btnCancelar;
    private JButton btnGuardarCambios;

    public VentanaCambiarPassword() {
        setTitle("Cambiar Contraseña");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblUsuario = new JLabel("Nombre de Usuario:");
        txtUsuario = new JTextField();

        JLabel lblContrasenaActual = new JLabel("Contraseña Actual:");
        txtContrasenaActual = new JPasswordField();

        JLabel lblNuevaContrasena = new JLabel("Nueva Contraseña:");
        txtNuevaContrasena = new JPasswordField();
        
        btnCancelar = new JButton("Cancelar");
        btnGuardarCambios = new JButton("Cambiar Contraseña");

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
                String usuario = txtUsuario.getText();
                String contrasenaActual = new String(txtContrasenaActual.getPassword());
                String nuevaContrasena = new String(txtNuevaContrasena.getPassword());

                UsuarioDB usuDB = new UsuarioDB();
                usuDB.cambiarContrasena(usuario, contrasenaActual, nuevaContrasena);
            }
        });

        pack();
        setLocationRelativeTo(null);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                VentanaCambiarPassword ventana = new VentanaCambiarPassword();
                ventana.setVisible(true);
            }
        });
    }
}
