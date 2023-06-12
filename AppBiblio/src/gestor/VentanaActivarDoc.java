package gestor;

import db.DocumentoMaxDB;
import admin.MenuAdmin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class VentanaActivarDoc extends JFrame {
	private JPanel contentPane;
	private JLabel lblAlta, lblImagen, lblIsbn;
	private JTextField textFieldIsbn;
	private JButton btnVolver, btnAceptar;
	
	private int isbn;
	public static String grupo;
	
	public VentanaActivarDoc() {
		setTitle("Activar Document");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaActivarDoc.class.getResource("/img/icono32.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setForeground(UIManager.getColor("Panel.foreground"));
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		// panel Principal
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(0, 128, 192));
		panelPrincipal.setForeground(new Color(0, 0, 0));
		panelPrincipal.setBorder(null);
		panelPrincipal.setBounds(0, 0, 388, 69);
		panelPrincipal.setLayout(null);
		
		// Imagen del titulo
		lblImagen = new JLabel("");
		lblImagen.setIcon(new ImageIcon(VentanaActivarDoc.class.getResource("/img/icono64.png")));
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagen.setBounds(307, 0, 60, 69);
		panelPrincipal.add(lblImagen);
		contentPane.add(panelPrincipal);
		
		// Titulo del panel Princial
		lblAlta = new JLabel("ACTIVAR DOCUMENT");
		lblAlta.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlta.setBounds(10, 11, 368, 47);
		panelPrincipal.add(lblAlta);
		lblAlta.setForeground(new Color(255, 255, 255));
		lblAlta.setFont(new Font("Dialog", Font.BOLD, 20));
		
		// panel de contenido
		JPanel panelSecundario = new JPanel();
		panelSecundario.setLayout(null);
		panelSecundario.setForeground(new Color(238, 238, 236));
		panelSecundario.setBackground(SystemColor.window);
		panelSecundario.setBorder(null);
		panelSecundario.setBounds(0, 70, 388, 195);

		lblIsbn = new JLabel("ISBN");
		lblIsbn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIsbn.setFont(new Font("Dialog", Font.BOLD, 14));
		lblIsbn.setBounds(57, 61, 70, 17);

		// isbn
		textFieldIsbn = new JTextField();
		textFieldIsbn.setForeground(new Color(255, 255, 255));
		textFieldIsbn.setBackground(new Color(0, 128, 192));
		textFieldIsbn.setFont(new Font("Dialog", Font.BOLD, 12));
		textFieldIsbn.setColumns(10);
		textFieldIsbn.setBounds(137, 59, 140, 21);
		
		btnVolver = new JButton("Tornar");
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 16));
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);
		btnVolver.setBackground(new Color(0, 128, 192));
		btnVolver.setBounds(10, 161, 140, 23);

		btnAceptar = new JButton("Acceptar");
		btnAceptar.setForeground(new Color(255, 255, 255));
		btnAceptar.setFont(new Font("Dialog", Font.BOLD, 16));
		btnAceptar.setFocusPainted(false);
		btnAceptar.setBorderPainted(false);
		btnAceptar.setBackground(new Color(0, 128, 192));
		btnAceptar.setBounds(238, 161, 140, 23);
		
		panelSecundario.add(lblIsbn);
		panelSecundario.add(textFieldIsbn);
		panelSecundario.add(btnVolver);
		panelSecundario.add(btnAceptar);
		contentPane.add(panelSecundario);

		//Funciones
		btnVolver.addActionListener(new ActionListener() {
			// función para cambiar de ventana haciendo click en el boton
			public void actionPerformed(ActionEvent e) {
				if (grupo.equals("gestor")) {
					MenuGestor menu = new MenuGestor();
					menu.setVisible(true);
				} else if (grupo.equals("admin")) {
					MenuAdmin menu = new MenuAdmin();
					menu.setVisible(true);
				}
				dispose();
			}// actionPerformed
		});
		
		btnAceptar.addActionListener(new ActionListener() {
			// función para crear objeto Documento y llamada a función inserar
			public void actionPerformed(ActionEvent e) {
				if (textFieldIsbn.getText().isEmpty()) {
					JOptionPane.showMessageDialog(panelSecundario, "Si us plau, introdueix tots els camps.", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					isbn = Integer.parseInt(textFieldIsbn.getText());
					DocumentoMaxDB docDB = new DocumentoMaxDB();
					if (docDB.checkDocumento(isbn)) {
						JOptionPane.showMessageDialog(panelSecundario, "S'ha actualizat correctament.", "Activar", JOptionPane.INFORMATION_MESSAGE);
					} else {
						VentanaAltaDoc.isbn = isbn;
						VentanaAltaDoc ventana = new VentanaAltaDoc();
						ventana.setVisible(true);
						dispose();
					}
				}//if else
			}//actionPerformed
		});
	}
	
}//VentanaAltaDocumento
