package socio;

import db.DocumentoMaxDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAgregarComentario extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtUsuario, txtComentario;
    private JButton btnOpcion, btnEnviar;
    
    MenuSocio menu = new MenuSocio();
    
    static int isbn;
    static String usuario;

    public VentanaAgregarComentario() {
        setTitle("Afegir Comentari - Opcional");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblUsuario = new JLabel("Usuari:");
        txtUsuario = new JTextField(20);
        txtUsuario.setEditable(false);
        txtUsuario.setText(usuario);

        JLabel lblComentario = new JLabel("Comentari:");
        txtComentario = new JTextField(20);
        
        btnOpcion = new JButton("No gràcies");
        btnEnviar = new JButton("Enviar");

        panel.add(lblUsuario);
        panel.add(txtUsuario);
        panel.add(lblComentario);
        panel.add(txtComentario);
        panel.add(btnOpcion);
        panel.add(btnEnviar);

        getContentPane().add(panel);
        
        btnOpcion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ex) {
                menu.setVisible(true);
                dispose();
            }
        });

        btnEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ex) {
                String comentario = txtComentario.getText();
                DocumentoMaxDB docDB = new DocumentoMaxDB();

                docDB.guardarComentario(isbn, usuario, comentario);

                JOptionPane.showMessageDialog(panel,
                        "Comentari desat correctament.", "Èxit", JOptionPane.INFORMATION_MESSAGE);
                
                menu.setVisible(true);
                dispose();
            }
        });

        pack();
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    }
}
