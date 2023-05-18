package gui;

import app.Usuario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaEditarPerfil extends JFrame {
    private JTextField txtNombre;
    private JPasswordField txtPassword;
    private JTextField txtEmail;
    private JButton btnVolver;
    private JButton btnGuardar;

    public VentanaEditarPerfil() {
    	
        setTitle("Editar perfil");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel lblNombre = new JLabel("Nombre de usuario:");
        txtNombre = new JTextField(20);

        JLabel lblPassword = new JLabel("Contraseña:");
        txtPassword = new JPasswordField(20);

        JLabel lblEmail = new JLabel("Correo electrónico:");
        txtEmail = new JTextField(20);

        btnVolver = new JButton("Volver");
        btnGuardar = new JButton("Guardar cambios");

        panel.add(lblNombre);
        panel.add(txtNombre);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(btnVolver);
        panel.add(btnGuardar);

        getContentPane().add(panel);
        
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ex) {
            	MenuSocio menu = new MenuSocio();
				menu.setVisible(true);
				dispose();
            }
        });

        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ex) {
                String nombre = txtNombre.getText();
                String password = new String(txtPassword.getPassword());
                String email = txtEmail.getText();

                Usuario usuario = new Usuario(nombre, password, email);

                JOptionPane.showMessageDialog(null, "Usuario registrado:\n" + usuario.toString());
            }
        });
        
        pack();
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
        	@Override
            public void run() {
            	VentanaEditarPerfil ventana = new VentanaEditarPerfil();
                ventana.setVisible(true);
            }
        });
    }
}
