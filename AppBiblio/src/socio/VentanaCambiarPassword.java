package socio;

import db.UsuarioMaxDB;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaCambiarPassword extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtUsuario;
    private JPasswordField txtContrasenaActual, txtNuevaContrasena;
    private JButton btnCancelar, btnGuardarCambios;
    
    String usuario, contrasenaActual, nuevaContrasena;

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
                usuario = txtUsuario.getText();
                contrasenaActual = new String(txtContrasenaActual.getPassword());
                nuevaContrasena = new String(txtNuevaContrasena.getPassword());
                
                if (nuevaContrasena.length() < 8) {
                	JOptionPane.showMessageDialog(panel, "La contraseña debe contener 8 caracteres.", "Registro", JOptionPane.WARNING_MESSAGE);
                	txtContrasenaActual.setText("");
                	txtNuevaContrasena.setText("");
                } else {
                
	                UsuarioMaxDB usuDB = new UsuarioMaxDB();
	                if (!usuDB.validarCuenta(usuario, contrasenaActual)) {
	                	JOptionPane.showMessageDialog(panel, "La contraseña actual es incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
	                } else if (usuDB.cambiarContrasena(usuario, nuevaContrasena)) {
	                	JOptionPane.showMessageDialog(panel, "Contraseña actualizada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	                }
	                
	                txtUsuario.setText("");
	                txtContrasenaActual.setText("");
	                txtNuevaContrasena.setText("");
                }
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
