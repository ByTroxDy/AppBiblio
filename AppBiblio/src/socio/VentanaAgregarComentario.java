package socio;

import db.DocumentoMaxDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAgregarComentario extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtUsuario, txtComentario;
    private JButton btnGuardar;
    
    static int isbn;
    static String usuario;

    public VentanaAgregarComentario() {
        setTitle("Agregar Comentario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblUsuario = new JLabel("Usuario:");
        txtUsuario = new JTextField(20);
        txtUsuario.setEditable(false);
        txtUsuario.setText(usuario);

        JLabel lblComentario = new JLabel("Comentario:");
        txtComentario = new JTextField(20);
        
        btnGuardar = new JButton("Guardar");

        panel.add(lblUsuario);
        panel.add(txtUsuario);
        panel.add(lblComentario);
        panel.add(txtComentario);
        panel.add(btnGuardar);

        getContentPane().add(panel);

        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ex) {
                String comentario = txtComentario.getText();
                DocumentoMaxDB docDB = new DocumentoMaxDB();

                docDB.guardarComentario(isbn, usuario, comentario);

                JOptionPane.showMessageDialog(panel,
                        "Comentario guardado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        pack();
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                VentanaAgregarComentario ventana = new VentanaAgregarComentario();
                ventana.setVisible(true);
            }
        });
    }
}
