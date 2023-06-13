package gestor;

import db.DocumentoMaxDB;
import app.Documento;
import app.Pelicula;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class VentanaAltaPelicula extends JFrame {
	private JPanel contentPane;
	private JLabel lblAlta, lblImagen, lblTituloDatos, lblDirector, lblActores, lblPremios, lblDuracion, lblFormato;
	private JTextField txtDirector, txtActores, txtPremios, txtDureacion;
	private JButton btnVolver, btnAceptar;
	private JComboBox<Object> cmbFormato;
	
	private String director, actoresPrincipales, premios, formato;
	private int duracion;
	static Documento documento;
	
	public VentanaAltaPelicula() {
		setTitle("Biblioteca App");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaAltaPelicula.class.getResource("/img/icono32.png")));
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
		panelPrincipal.setBackground(new Color(0, 128, 192));
		panelPrincipal.setForeground(new Color(0, 0, 0));
		panelPrincipal.setBorder(null);
		panelPrincipal.setBounds(0, 0, 592, 70);
		panelPrincipal.setLayout(null);
		
		lblAlta = new JLabel("ALTA PEL·LÍCULA");
		lblAlta.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlta.setBounds(125, 11, 320, 48);
		lblAlta.setForeground(new Color(255, 255, 255));
		lblAlta.setFont(new Font("Dialog", Font.BOLD, 40));
		
		lblImagen = new JLabel("");
		lblImagen.setIcon(new ImageIcon(MenuGestor.class.getResource("/img/icono64.png")));
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagen.setBounds(455, 0, 84, 70);

		panelPrincipal.add(lblAlta);
		panelPrincipal.add(lblImagen);
		contentPane.add(panelPrincipal);

		JPanel panelSecundario = new JPanel();
		panelSecundario.setLayout(null);
		panelSecundario.setForeground(new Color(238, 238, 236));
		panelSecundario.setBackground(SystemColor.window);
		panelSecundario.setBorder(null);
		panelSecundario.setBounds(0, 70, 592, 297);
				
		lblTituloDatos = new JLabel("Introdueix les dades");
		lblTituloDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloDatos.setForeground(new Color(0, 0, 0));
		lblTituloDatos.setFont(new Font("Dialog", Font.BOLD, 25));
		lblTituloDatos.setBounds(0, 28, 584, 28);

		lblDirector = new JLabel("Director");
		lblDirector.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDirector.setBounds(35, 79, 79, 17);

		//Director
		txtDirector = new JTextField();
		txtDirector.setFont(new Font("Dialog", Font.BOLD, 14));
		txtDirector.setForeground(new Color(255, 255, 255));
		txtDirector.setBackground(new Color(0, 128, 192));
		txtDirector.setBounds(109, 79, 158, 20);
		txtDirector.setColumns(10);

		lblActores = new JLabel("Actors");
		lblActores.setFont(new Font("Dialog", Font.BOLD, 15));
		lblActores.setBounds(318, 79, 79, 17);
		
		//Actores
		txtActores = new JTextField();
		txtActores.setFont(new Font("Dialog", Font.BOLD, 14));
		txtActores.setForeground(new Color(255, 255, 255));
		txtActores.setBackground(new Color(0, 128, 192));
		txtActores.setColumns(10);
		txtActores.setBounds(390, 79, 158, 20);

		lblPremios = new JLabel("Premis");
		lblPremios.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPremios.setBounds(35, 126, 79, 17);
		
		//Premios
		txtPremios = new JTextField();
		txtPremios.setFont(new Font("Dialog", Font.BOLD, 14));
		txtPremios.setForeground(new Color(255, 255, 255));
		txtPremios.setBackground(new Color(0, 128, 192));
		txtPremios.setColumns(10);
		txtPremios.setBounds(109, 126, 158, 20);

		lblDuracion = new JLabel("Duració");
		lblDuracion.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDuracion.setBounds(318, 123, 79, 17);
		
		//Duracion
		txtDureacion = new JTextField();
		txtDureacion.setFont(new Font("Dialog", Font.BOLD, 14));
		txtDureacion.setForeground(new Color(255, 255, 255));
		txtDureacion.setBackground(new Color(0, 128, 192));
		txtDureacion.setColumns(10);
		txtDureacion.setBounds(390, 123, 158, 20);

		lblFormato = new JLabel("Formato");
		lblFormato.setFont(new Font("Dialog", Font.BOLD, 15));
		lblFormato.setBounds(159, 176, 79, 17);
		
		//Formato
		cmbFormato = new JComboBox<Object>();
		cmbFormato.setFont(new Font("Dialog", Font.BOLD, 14));
		cmbFormato.setForeground(new Color(255, 255, 255));
		cmbFormato.setBackground(new Color(0, 128, 192));
		cmbFormato.setModel(new DefaultComboBoxModel<Object>(new String[] {"Físic", "Digital"}));
		cmbFormato.setBounds(238, 174, 158, 22);
		
		//Volver
		btnVolver = new JButton("Tornar");
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVolver.setBackground(new Color(0, 128, 192));
		btnVolver.setBounds(12, 257, 283, 28);
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);

		//Alta
		btnAceptar = new JButton("Acceptar");
		btnAceptar.setBounds(307, 257, 279, 28);
		btnAceptar.setFocusPainted(false);
		btnAceptar.setBorderPainted(false);
		btnAceptar.setForeground(new Color(255, 255, 255));
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
		panelSecundario.add(txtDureacion);
		panelSecundario.add(lblFormato);
		panelSecundario.add(cmbFormato);
		panelSecundario.add(btnVolver);
		panelSecundario.add(btnAceptar);
		contentPane.add(panelSecundario);
		
		btnVolver.addActionListener(new ActionListener() {
			//función para cambiar de ventana haciendo click en el boton
			public void actionPerformed(ActionEvent e) {    
				MenuGestor frame = new MenuGestor();
				frame.setVisible(true);
				dispose();
			}// actionPerformed
		});

		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtDirector.getText().isEmpty() | txtActores.getText().isEmpty() | txtPremios.getText().isEmpty()
						| txtDureacion.getText().isEmpty()) {
					JOptionPane.showMessageDialog(panelSecundario, "Si us plau, introdueix tots els camps.", "Alerta", JOptionPane.WARNING_MESSAGE);
				} else {
					director = txtDirector.getText().toString();
					actoresPrincipales = txtActores.getText();
					premios = txtPremios.getText();
					duracion = Integer.parseInt(txtDureacion.getText());
					formato = (String) cmbFormato.getSelectedItem();
					
					Pelicula pelicula = new Pelicula(documento.getISBN(), director, actoresPrincipales, premios, duracion, formato);

					DocumentoMaxDB docDB = new DocumentoMaxDB();
					if (docDB.insertDocPel(documento, pelicula)) {
						JOptionPane.showMessageDialog(panelSecundario, "Registre exitós.", "Pel·lícula", JOptionPane.INFORMATION_MESSAGE);
						MenuGestor menu = new MenuGestor();
						menu.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(panelSecundario, "Hi ha hagut un error en introduir les dades a la base de dades.", "Error", JOptionPane.ERROR_MESSAGE);
					}//if else
				}// if else
			}//actionPerformed
		});
	}
}// VentanaAltaPelicula
