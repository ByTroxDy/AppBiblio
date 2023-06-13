package gestor;

import db.DocumentoMaxDB;
import app.Documento;
import app.Libro;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import admin.MenuAdmin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class VentanaAltaLibro extends JFrame {
	private JPanel contentPane;
	private JLabel lblAlta, lblImagen, lblTituloIsbn, lblEditorial, lblPginas, lblTemtica;
	private JTextField txtEditorial, txtPaginas;
	private JButton btnVolver, btnAceptar;
	private JComboBox<Object> cmbTematica;
	
	private String editorial, tematica;
	private int numpaginas;
	static Documento documento;
	public static String grupo;
	
	public VentanaAltaLibro() {
		setTitle("Biblioteca App");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaAltaLibro.class.getResource("/img/icono32.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setForeground(UIManager.getColor("Panel.foreground"));
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		//panel Principal
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(0, 128, 192));
		panelPrincipal.setForeground(new Color(0, 0, 0));
		panelPrincipal.setBorder(null);
		panelPrincipal.setBounds(0, 0, 592, 72);
		panelPrincipal.setLayout(null);
		
		// Titulo de panel Principal
		lblAlta = new JLabel("ALTA LLIBRE");
		lblAlta.setForeground(new Color(255, 255, 255));
		lblAlta.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlta.setFont(new Font("Dialog", Font.BOLD, 40));
		lblAlta.setBounds(135, 11, 272, 50);

		// Imagen del panel principal
		lblImagen = new JLabel("");
		lblImagen.setIcon(new ImageIcon(MenuGestor.class.getResource("/img/icono64.png")));
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagen.setBounds(415, 0, 62, 72);

		panelPrincipal.add(lblAlta);
		panelPrincipal.add(lblImagen);
		contentPane.add(panelPrincipal);

		JPanel panelSecundario = new JPanel();
		panelSecundario.setLayout(null);
		panelSecundario.setForeground(new Color(238, 238, 236));
		panelSecundario.setBackground(SystemColor.window);
		panelSecundario.setBorder(null);
		panelSecundario.setBounds(0, 70, 592, 297);

		// Titulo de panel Secundario
		lblTituloIsbn = new JLabel("Introdueix les dades");
		lblTituloIsbn.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloIsbn.setForeground(new Color(0, 0, 0));
		lblTituloIsbn.setFont(new Font("Dialog", Font.BOLD, 25));
		lblTituloIsbn.setBounds(0, 28, 584, 28);

		lblEditorial= new JLabel("Editorial");
		lblEditorial.setFont(new Font("Dialog", Font.BOLD, 15));
		lblEditorial.setBounds(119, 82, 79, 17);

		// campo Editorial
		txtEditorial = new JTextField();
		txtEditorial.setFont(new Font("Dialog", Font.BOLD, 14));
		txtEditorial.setForeground(new Color(255, 255, 255));
		txtEditorial.setBackground(new Color(0, 128, 192));
		txtEditorial.setBounds(194, 81, 262, 21);
		txtEditorial.setColumns(10);

		lblPginas = new JLabel("Pàgines");
		lblPginas.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPginas.setBounds(119, 122, 79, 17);

		// campo numero de paginas
		txtPaginas = new JTextField();
		txtPaginas.setFont(new Font("Dialog", Font.BOLD, 14));
		txtPaginas.setForeground(new Color(255, 255, 255));
		txtPaginas.setBackground(new Color(0, 128, 192));
		txtPaginas.setColumns(10);
		txtPaginas.setBounds(194, 121, 262, 21);

		lblTemtica = new JLabel("Temàtica");
		lblTemtica.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTemtica.setBounds(119, 163, 79, 17);

		// campo Temática
		cmbTematica = new JComboBox<Object>();
		cmbTematica.setFont(new Font("Dialog", Font.BOLD, 14));
		cmbTematica.setForeground(new Color(255, 255, 255));
		cmbTematica.setBackground(new Color(0, 128, 192));
		cmbTematica.setModel(new DefaultComboBoxModel<Object>(new String[] {"Ciències", "Història", "Literatura", "Filosofia", "Tècnics", "Altres"}));
		cmbTematica.setBounds(194, 159, 262, 26);

		btnVolver = new JButton("Tornar");
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVolver.setBackground(new Color(0, 128, 192));
		btnVolver.setBounds(12, 262, 286, 23);
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);

		btnAceptar = new JButton("Acceptar");
		btnAceptar.setBounds(316, 261, 268, 24);
		btnAceptar.setFocusPainted(false);
		btnAceptar.setBorderPainted(false);
		btnAceptar.setForeground(new Color(255, 255, 255));
		btnAceptar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnAceptar.setBackground(new Color(0, 128, 192));
		
		panelSecundario.add(lblTituloIsbn);
		panelSecundario.add(lblEditorial);
		panelSecundario.add(txtEditorial);
		panelSecundario.add(lblPginas);
		panelSecundario.add(txtPaginas);
		panelSecundario.add(lblTemtica);
		panelSecundario.add(cmbTematica);
		panelSecundario.add(btnVolver);
		panelSecundario.add(btnAceptar);
		contentPane.add(panelSecundario);

		btnVolver.addActionListener(new ActionListener() {
			// función para cambiar de ventana haciendo click en el boton
			public void actionPerformed(ActionEvent e) {
				MenuGestor frame = new MenuGestor();
				frame.setVisible(true);
				dispose();
			}//a ctionPerformed
		});
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtEditorial.getText().isEmpty() | txtPaginas.getText().isEmpty()) {
					JOptionPane.showMessageDialog(panelSecundario, "Si us plau, introdueix tots els camps.", "Alerta", JOptionPane.WARNING_MESSAGE);
				} else {
					editorial = txtEditorial.getText();
					numpaginas = Integer.parseInt(txtPaginas.getText());
					tematica = (String) cmbTematica.getSelectedItem();

					Libro libro = new Libro(documento.getISBN(), editorial, numpaginas, tematica);
					DocumentoMaxDB docDB = new DocumentoMaxDB();

					if (docDB.insertDocLib(documento, libro)) {
						JOptionPane.showMessageDialog(panelSecundario, "Registre exitós.", "Llibre", JOptionPane.INFORMATION_MESSAGE);
						if (grupo.equals("gestor")) {
							MenuGestor menu = new MenuGestor();
							menu.setVisible(true);
						} else if (grupo.equals("admin")) {
							MenuAdmin menu = new MenuAdmin();
							menu.setVisible(true);
						}
					} else {
						JOptionPane.showMessageDialog(panelSecundario, "Hi ha hagut un error en introduir les dades a la base de dades.", "Error", JOptionPane.ERROR_MESSAGE);
					}// if else
				}// if else
			}// actionPerformed
		});
	}
}// VentanaAltaLibro