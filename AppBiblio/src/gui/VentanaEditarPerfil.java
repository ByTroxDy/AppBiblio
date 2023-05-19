package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaEditarPerfil extends JFrame {
	private static final long serialVersionUID = 1L;
	private JButton btnCambiarUsuario;
    private JButton btnCambiarContrasena;
    private JButton btnCambiarEmail;
    private JButton btnVolver;

    public VentanaEditarPerfil() {
        setTitle("Cuenta");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel lblTitulo = new JLabel("Editar Perfil");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        btnCambiarUsuario = new JButton("Cambiar Nombre de Usuario");
        btnCambiarContrasena = new JButton("Cambiar Contraseña");
        btnCambiarEmail = new JButton("Cambiar Email");
        btnVolver = new JButton("Volver");

        panel.add(lblTitulo);
        panel.add(btnCambiarUsuario);
        panel.add(btnCambiarContrasena);
        panel.add(btnCambiarEmail);
        panel.add(btnVolver);

        getContentPane().add(panel);

        btnCambiarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ex) {
                VentanaCambiarNombreUsuario cambiarNombreUsuario = new VentanaCambiarNombreUsuario();
                cambiarNombreUsuario.setVisible(true);
                dispose();
            }
        });

        btnCambiarContrasena.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ex) {
            	VentanaCambiarPassword cambiarPassword = new VentanaCambiarPassword();
            	cambiarPassword.setVisible(true);
                dispose();
            }
        });

        btnCambiarEmail.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ex) {
            	VentanaCambiarEmail cambiarEmail = new VentanaCambiarEmail();
            	cambiarEmail.setVisible(true);
                dispose();
            }
        });
        
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ex) {
            	MenuSocio menu = new MenuSocio();
				menu.setVisible(true);
                dispose();
            }
        });

        pack();
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                VentanaEditarPerfil ventana = new VentanaEditarPerfil();
                ventana.setVisible(true);
            }
        });
    }
}
