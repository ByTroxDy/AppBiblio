package gestor;

import db.DocumentoMaxDB;
import app.Documento;
import app.Pelicula;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import admin.MenuAdmin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class VentanaModiPelicula extends JFrame {
	private JPanel contentPane;
	private JLabel lblAlta, lblImagen, lblTituloDatos, lblDirector, lblActores, lblPremios, lblDuracion, lblFormato;
	private JTextField txtDirector, txtActores, txtPremios, txtDuracion;
	private JButton btnVolver, btnAceptar;
	private JComboBox<Object> cmbFormato;
	
	private int duracion;
	private String director, actoresPrincipales, premios, formato;
	static Documento documento;
	public static String grupo;

	public VentanaModiPelicula() {
		setTitle("Biblioteca App");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaModiPelicula.class.getResource("/img/icono32.png")));
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
		panelPrincipal.setBorder(null);
		panelPrincipal.setBounds(0, 0, 592, 74);
		
		lblAlta = new JLabel("MODIFICAR PELÍCULA");
		lblAlta.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlta.setBounds(36, 0, 444, 74);
		lblAlta.setForeground(new Color(238, 238, 236));
		lblAlta.setFont(new Font("Dialog", Font.BOLD, 40));
		
		lblImagen = new JLabel("");
		lblImagen.setIcon(new ImageIcon(VentanaModiPelicula.class.getResource("/img/icono64.png")));
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagen.setBounds(492, 0, 60, 74);

		panelPrincipal.add(lblAlta);
		panelPrincipal.add(lblImagen);
		contentPane.add(panelPrincipal);

		JPanel panelSecundario = new JPanel();
		panelSecundario.setLayout(null);
		panelSecundario.setForeground(new Color(238, 238, 236));
		panelSecundario.setBackground(SystemColor.window);
		panelSecundario.setBounds(0, 70, 592, 297);
				
		lblTituloDatos = new JLabel("Introdueix les noves dades");
		lblTituloDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloDatos.setForeground(new Color(0, 0, 0));
		lblTituloDatos.setFont(new Font("Dialog", Font.BOLD, 25));
		lblTituloDatos.setBounds(0, 28, 592, 28);

		lblDirector = new JLabel("Director");
		lblDirector.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDirector.setBounds(65, 82, 79, 17);

		//Director
		txtDirector = new JTextField();
		txtDirector.setBackground(new Color(0, 128, 192));
		txtDirector.setForeground(new Color(238, 238, 236));
		txtDirector.setFont(new Font("Dialog", Font.BOLD, 14));
		txtDirector.setBounds(141, 82, 129, 20);
		txtDirector.setColumns(10);

		lblActores = new JLabel("Actors");
		lblActores.setFont(new Font("Dialog", Font.BOLD, 15));
		lblActores.setBounds(311, 82, 79, 17);
		
		//Actores
		txtActores = new JTextField();
		txtActores.setBackground(new Color(0, 128, 192));
		txtActores.setForeground(new Color(238, 238, 236));
		txtActores.setFont(new Font("Dialog", Font.BOLD, 14));
		txtActores.setColumns(10);
		txtActores.setBounds(387, 82, 129, 20);

		lblPremios = new JLabel("Premis");
		lblPremios.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPremios.setBounds(65, 131, 79, 17);
		
		//Premios
		txtPremios = new JTextField();
		txtPremios.setBackground(new Color(0, 128, 192));
		txtPremios.setForeground(new Color(238, 238, 236));
		txtPremios.setFont(new Font("Dialog", Font.BOLD, 14));
		txtPremios.setColumns(10);
		txtPremios.setBounds(141, 131, 129, 20);

		lblDuracion = new JLabel("Duració");
		lblDuracion.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDuracion.setBounds(311, 128, 79, 17);
		
		//Duracion
		txtDuracion = new JTextField();
		txtDuracion.setBackground(new Color(0, 128, 192));
		txtDuracion.setForeground(new Color(238, 238, 236));
		txtDuracion.setFont(new Font("Dialog", Font.BOLD, 14));
		txtDuracion.setColumns(10);
		txtDuracion.setBounds(387, 128, 129, 20);

		lblFormato = new JLabel("Format");
		lblFormato.setFont(new Font("Dialog", Font.BOLD, 15));
		lblFormato.setBounds(182, 185, 79, 17);
		
		//Formato
		cmbFormato = new JComboBox<Object>();
		cmbFormato.setBackground(new Color(0, 128, 192));
		cmbFormato.setForeground(new Color(238, 238, 236));
		cmbFormato.setFont(new Font("Dialog", Font.BOLD, 14));
		cmbFormato.setModel(new DefaultComboBoxModel<Object>(new String[] {"Digital", "Físic"}));
		cmbFormato.setBounds(255, 183, 129, 22);
		
		//Volver
		btnVolver = new JButton("Tornar");
		btnVolver.setForeground(new Color(238, 238, 236));
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVolver.setBackground(new Color(0, 128, 192));
		btnVolver.setBounds(12, 257, 282, 28);
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);

		//Alta
		btnAceptar = new JButton("Acceptar");
		btnAceptar.setBounds(311, 257, 269, 28);
		btnAceptar.setFocusPainted(false);
		btnAceptar.setBorderPainted(false);
		btnAceptar.setForeground(new Color(238, 238, 236));
		btnAceptar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnAceptar.setBackground(new Color(0, 128, 192));
		
		panelSecundario.add(lblTituloDatos);
		panelSecundario.add(lblDirector);
		panelSecundario.add(txtDirector);
		panelSecundario.add(lblActores);
		panelSecundario.add(txtActores);
		panelSecundario.add(lblPremios);
		panelSecundario.add(txtPremios);
		panelSecundario.add(lblDuracion);
		panelSecundario.add(txtDuracion);
		panelSecundario.add(lblFormato);
		panelSecundario.add(cmbFormato);
		panelSecundario.add(btnVolver);
		panelSecundario.add(btnAceptar);
		contentPane.add(panelSecundario);

		//Funciones
		btnVolver.addActionListener(new ActionListener() {
			//función para cambiar de ventana haciendo click en el boton
			public void actionPerformed(ActionEvent e) {    
				MenuGestor frame = new MenuGestor();
				frame.setVisible(true);
				dispose();
			}//actionPerformed
		});
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtDirector.getText().isEmpty() | txtActores.getText().isEmpty() | txtPremios.getText().isEmpty() | txtDuracion.getText().isEmpty()) {
					JOptionPane.showMessageDialog(panelSecundario, "Si us plau, introdueix tots els camps.", "Alerta", JOptionPane.WARNING_MESSAGE);
				} else {
					director = txtDirector.getText();
					actoresPrincipales = txtActores.getText();
					premios = txtPremios.getText();
					duracion = Integer.parseInt(txtDuracion.getText());
					formato = (String) cmbFormato.getSelectedItem();
					
					Pelicula pelicula = new Pelicula(documento.getISBN(), director, actoresPrincipales, premios, duracion, formato);
					DocumentoMaxDB docDB = new DocumentoMaxDB();
					
					if (docDB.updateDocPel(documento, pelicula)) {
						JOptionPane.showMessageDialog(panelSecundario, "Actualització exitosa.", "Pel·lícula", JOptionPane.INFORMATION_MESSAGE);
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
}// VentanaModificarPelicula
