package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaEditarPerfil extends JFrame {
    private JTextField txtNombre;
    private JPasswordField txtPassword;
    private JTextField txtEmail;
    private JButton btnVolver;
    private JButton btnGuardar;

    public VentanaEditarPerfil() {
        setTitle("Editar perfil");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));

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
                // Aquí puedes agregar la lógica para guardar los cambios en la base de datos
                JOptionPane.showMessageDialog(null, "Cambios guardados exitosamente");
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
