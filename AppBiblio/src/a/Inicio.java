package a;

import socio.VentanaInicioSesion;
import socio.VentanaRegistro;
import socio.VentanaConsultarDocumento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class Inicio extends JFrame {
	private JButton btnLogin, btnSignIn, btnConsulta;

    public Inicio() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(Inicio.class.getResource("/img/icono32.png")));
        setTitle("App Biblioteca - Miguel | Ayoub | Paolo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        getContentPane().add(panel, BorderLayout.CENTER);

        JLabel titulo = new JLabel("App Biblioteca");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(titulo);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3, 10, 0));
        
        btnSignIn = new JButton("Sign In");
        btnSignIn.setFont(new Font("Arial", Font.PLAIN, 16));
        
        btnConsulta = new JButton("Consultar Document");
        btnConsulta.setFont(new Font("Arial", Font.PLAIN, 16));

        btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Arial", Font.PLAIN, 16));
        
        buttonPanel.add(btnSignIn);
        buttonPanel.add(btnConsulta);
        buttonPanel.add(btnLogin);
        panel.add(buttonPanel);
        panel.add(Box.createVerticalGlue());

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	VentanaInicioSesion ventana = new VentanaInicioSesion();
                ventana.setVisible(true);
                dispose();
            }
        });
        
        btnSignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	VentanaRegistro ventana = new VentanaRegistro();
                ventana.setVisible(true);
                dispose();
            }
        });
        
        btnConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	VentanaConsultarDocumento.usuario = null;
				VentanaConsultarDocumento.grupo = null;
            	VentanaConsultarDocumento ventana = new VentanaConsultarDocumento();
                ventana.setVisible(true);
                dispose();
            }
        });

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Inicio());
    }
}
