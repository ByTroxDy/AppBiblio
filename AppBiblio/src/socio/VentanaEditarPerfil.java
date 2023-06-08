package socio;

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
        setTitle("Compte");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel lblTitulo = new JLabel("Editar Perfil");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

        btnCambiarUsuario = new JButton("Canvia nom d'usuari");
        btnCambiarContrasena = new JButton("Canviar contrasenya");
        btnCambiarEmail = new JButton("Canviar email");
        btnVolver = new JButton("Enrere");

        panel.add(lblTitulo);
        panel.add(btnCambiarUsuario);
        panel.add(btnCambiarContrasena);
        panel.add(btnCambiarEmail);
        panel.add(btnVolver);

        getContentPane().add(panel);

        btnCambiarUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ex) {
                VentanaCambiarNombreUsuario ventana = new VentanaCambiarNombreUsuario();
                ventana.setVisible(true);
                dispose();
            }
        });

        btnCambiarContrasena.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ex) {
            	VentanaCambiarPassword ventana = new VentanaCambiarPassword();
            	ventana.setVisible(true);
                dispose();
            }
        });

        btnCambiarEmail.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ex) {
            	VentanaCambiarEmail ventana = new VentanaCambiarEmail();
            	ventana.setVisible(true);
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
