package gestor;

import db.DocumentoMaxDB;
import app.Documento;
import app.Pelicula;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAltaPelicula extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblAlta, lblImagen, lblTituloDatos, lblDirector, lblActores,lblPremis, lblDuracion, lblFormato;
	private JTextField textFieldDirector, textFieldActores, textFieldPremios, textFieldDureacion;
	private JButton btnVolver, btnAceptar;
	private JComboBox<Object> formatoBox;
	
	private String director, actoresPrincipales, premios, formato;
	private int duracion;
	
	static Documento documento;
	
	public VentanaAltaPelicula() {
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
		textFieldDirector = new JTextField();
		textFieldDirector.setFont(new Font("Dialog", Font.BOLD, 14));
		textFieldDirector.setForeground(new Color(255, 255, 255));
		textFieldDirector.setBackground(new Color(0, 128, 192));
		textFieldDirector.setBounds(109, 79, 158, 20);
		textFieldDirector.setColumns(10);

		lblActores = new JLabel("Actors");
		lblActores.setFont(new Font("Dialog", Font.BOLD, 15));
		lblActores.setBounds(318, 79, 79, 17);
		
		//Actores
		textFieldActores = new JTextField();
		textFieldActores.setFont(new Font("Dialog", Font.BOLD, 14));
		textFieldActores.setForeground(new Color(255, 255, 255));
		textFieldActores.setBackground(new Color(0, 128, 192));
		textFieldActores.setColumns(10);
		textFieldActores.setBounds(390, 79, 158, 20);

		lblPremis = new JLabel("Premis");
		lblPremis.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPremis.setBounds(35, 126, 79, 17);
		
		//Premios
		textFieldPremios = new JTextField();
		textFieldPremios.setFont(new Font("Dialog", Font.BOLD, 14));
		textFieldPremios.setForeground(new Color(255, 255, 255));
		textFieldPremios.setBackground(new Color(0, 128, 192));
		textFieldPremios.setColumns(10);
		textFieldPremios.setBounds(109, 126, 158, 20);

		lblDuracion = new JLabel("Duració");
		lblDuracion.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDuracion.setBounds(318, 123, 79, 17);
		
		//Duracion
		textFieldDureacion = new JTextField();
		textFieldDureacion.setFont(new Font("Dialog", Font.BOLD, 14));
		textFieldDureacion.setForeground(new Color(255, 255, 255));
		textFieldDureacion.setBackground(new Color(0, 128, 192));
		textFieldDureacion.setColumns(10);
		textFieldDureacion.setBounds(390, 123, 158, 20);

		lblFormato = new JLabel("Formato");
		lblFormato.setFont(new Font("Dialog", Font.BOLD, 15));
		lblFormato.setBounds(159, 176, 79, 17);
		
		//Formato
		formatoBox = new JComboBox<Object>();
		formatoBox.setFont(new Font("Dialog", Font.BOLD, 14));
		formatoBox.setForeground(new Color(255, 255, 255));
		formatoBox.setBackground(new Color(0, 128, 192));
		formatoBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"Físic", "Digital"}));
		formatoBox.setBounds(238, 174, 158, 22);
		
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
		panelSecundario.add(textFieldDirector);
		panelSecundario.add(lblActores);
		panelSecundario.add(textFieldActores);
		panelSecundario.add(lblPremis);
		panelSecundario.add(textFieldPremios);
		panelSecundario.add(lblDuracion);
		panelSecundario.add(textFieldDureacion);
		panelSecundario.add(lblFormato);
		panelSecundario.add(formatoBox);
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
				if (textFieldDirector.getText().isEmpty() | textFieldActores.getText().isEmpty() | textFieldPremios.getText().isEmpty()
						| textFieldDureacion.getText().isEmpty()) {
					JOptionPane.showMessageDialog(panelSecundario, "Si us plau, introdueix tots els camps.", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					director = textFieldDirector.getText().toString();
					actoresPrincipales = textFieldActores.getText().toString();
					premios = textFieldPremios.getText().toString();
					duracion = Integer.parseInt(textFieldDureacion.getText());
					formato = formatoBox.getSelectedItem().toString();
					
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
	}// VentanaAltaPelicula
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAltaPelicula frame = new VentanaAltaPelicula();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}// try catch
			}// run
		});
	}// main
}// VentanaAltaPelicula
