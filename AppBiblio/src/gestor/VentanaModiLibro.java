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
public class VentanaModiLibro extends JFrame {
	private JPanel contentPane;
	private JLabel lblAlta, lblImagen, lblTituloDatos, lblEditorial, lblPginas, lblTemtica;
	private JTextField txtEditorial, txtPaginas;
	private JButton btnVolver, btnAceptar;
	private JComboBox<Object> cmbTematica;
	
	private int numpaginas;
	private String editorial, tematica;
	static Documento documento;
	public static String grupo;

	public VentanaModiLibro() {
		setTitle("Biblioteca App");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaModiLibro.class.getResource("/img/icono32.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setForeground(UIManager.getColor("Panel.foreground"));
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(null);
		panelPrincipal.setBackground(new Color(0, 128, 192));
		panelPrincipal.setForeground(new Color(0, 0, 0));
		panelPrincipal.setBounds(0, 0, 592, 75);

		lblAlta = new JLabel("MODIFICAR LLIBRE");
		lblAlta.setBounds(94, 0, 385, 75);
		lblAlta.setForeground(new Color(238, 238, 236));
		lblAlta.setFont(new Font("Dialog", Font.BOLD, 40));
		
		lblImagen = new JLabel("");
		lblImagen.setIcon(new ImageIcon(MenuGestor.class.getResource("/img/icono64.png")));
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagen.setBounds(492, 0, 60, 75);

		panelPrincipal.add(lblAlta);
		panelPrincipal.add(lblImagen);
		contentPane.add(panelPrincipal);

		JPanel panelSecundario = new JPanel();
		panelSecundario.setLayout(null);
		panelSecundario.setForeground(new Color(238, 238, 236));
		panelSecundario.setBackground(SystemColor.window);
		panelSecundario.setBounds(0, 70, 592, 297);

		lblTituloDatos = new JLabel("Introdeix les noves dades");
		lblTituloDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloDatos.setForeground(new Color(0, 0, 0));
		lblTituloDatos.setFont(new Font("Dialog", Font.BOLD, 25));
		lblTituloDatos.setBounds(0, 29, 592, 28);

		lblEditorial = new JLabel("Editorial");
		lblEditorial.setFont(new Font("Dialog", Font.BOLD, 15));
		lblEditorial.setBounds(168, 84, 79, 17);

		txtEditorial = new JTextField();
		txtEditorial.setBackground(new Color(0, 128, 192));
		txtEditorial.setForeground(new Color(238, 238, 236));
		txtEditorial.setFont(new Font("Dialog", Font.BOLD, 14));
		txtEditorial.setBounds(247, 84, 183, 21);
		txtEditorial.setColumns(10);

		lblPginas = new JLabel("Págines");
		lblPginas.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPginas.setBounds(168, 131, 79, 17);

		txtPaginas = new JTextField();
		txtPaginas.setBackground(new Color(0, 128, 192));
		txtPaginas.setForeground(new Color(238, 238, 236));
		txtPaginas.setFont(new Font("Dialog", Font.BOLD, 14));
		txtPaginas.setColumns(10);
		txtPaginas.setBounds(247, 131, 183, 21);

		lblTemtica = new JLabel("Temática");
		lblTemtica.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTemtica.setBounds(168, 185, 79, 17);

		cmbTematica = new JComboBox<Object>();
		cmbTematica.setBackground(new Color(0, 128, 192));
		cmbTematica.setForeground(new Color(238, 238, 236));
		cmbTematica.setFont(new Font("Dialog", Font.BOLD, 14));
		cmbTematica.setModel(new DefaultComboBoxModel<Object>(new String[] {"Ciencies", "Historia", "Lliteratura", "Filosofía", "Técnics", "Altres"}));
		cmbTematica.setBounds(247, 182, 183, 26);

		btnVolver = new JButton("Tornar");
		btnVolver.setForeground(new Color(238, 238, 236));
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVolver.setBackground(new Color(0, 128, 192));
		btnVolver.setBounds(12, 262, 291, 23);
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);

		btnAceptar = new JButton("Acceptar");
		btnAceptar.setBounds(315, 261, 265, 24);
		btnAceptar.setFocusPainted(false);
		btnAceptar.setBorderPainted(false);
		btnAceptar.setForeground(new Color(238, 238, 236));
		btnAceptar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnAceptar.setBackground(new Color(0, 128, 192));

		panelSecundario.add(lblTituloDatos);
		panelSecundario.add(lblEditorial);
		panelSecundario.add(txtEditorial);
		panelSecundario.add(lblPginas);
		panelSecundario.add(txtPaginas);
		panelSecundario.add(lblTemtica);
		panelSecundario.add(cmbTematica);
		panelSecundario.add(btnVolver);
		panelSecundario.add(btnAceptar);
		contentPane.add(panelSecundario);

		//Funciones
		btnVolver.addActionListener(new ActionListener() {
			// función para cambiar de ventana haciendo click en el boton
			public void actionPerformed(ActionEvent e) {
				MenuGestor frame = new MenuGestor();
				frame.setVisible(true);
				dispose();
			}
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

					if (docDB.updateDocLib(documento, libro)) {
						JOptionPane.showMessageDialog(panelSecundario, "Actualització exitosa.", "Llibre", JOptionPane.INFORMATION_MESSAGE);
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
			}//actionPerformed
		});
	}
}// VentanaModificarLibro