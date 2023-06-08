package gestor;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import app.Documento;
import app.Pelicula;
import db.DocumentoMaxDB;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class VentanaModificarPelicula extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldDirector, textFieldActores, textFieldPremios, textFieldDureacion;
	private JLabel lblAlta, lblImagen, lblTituloDatos, lblDirector, lblActores, lblPremios, lblDuracion, lblFormato;
	private JButton btnVolver, btnAceptar;
	private JComboBox<Object> formatoBox;
	
	private String director, actoresPrincipales, premios, formato;
	private int duracion;
	static Documento documento;


	public VentanaModificarPelicula() {
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
		lblImagen.setIcon(new ImageIcon(VentanaModificarPelicula.class.getResource("/img/icono64.png")));
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
		textFieldDirector = new JTextField();
		textFieldDirector.setBackground(new Color(0, 128, 192));
		textFieldDirector.setForeground(new Color(238, 238, 236));
		textFieldDirector.setFont(new Font("Dialog", Font.BOLD, 14));
		textFieldDirector.setBounds(141, 82, 129, 20);
		textFieldDirector.setColumns(10);

		lblActores = new JLabel("Actors");
		lblActores.setFont(new Font("Dialog", Font.BOLD, 15));
		lblActores.setBounds(311, 82, 79, 17);
		
		//Actores
		textFieldActores = new JTextField();
		textFieldActores.setBackground(new Color(0, 128, 192));
		textFieldActores.setForeground(new Color(238, 238, 236));
		textFieldActores.setFont(new Font("Dialog", Font.BOLD, 14));
		textFieldActores.setColumns(10);
		textFieldActores.setBounds(387, 82, 129, 20);

		lblPremios = new JLabel("Premis");
		lblPremios.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPremios.setBounds(65, 131, 79, 17);
		
		//Premios
		textFieldPremios = new JTextField();
		textFieldPremios.setBackground(new Color(0, 128, 192));
		textFieldPremios.setForeground(new Color(238, 238, 236));
		textFieldPremios.setFont(new Font("Dialog", Font.BOLD, 14));
		textFieldPremios.setColumns(10);
		textFieldPremios.setBounds(141, 131, 129, 20);

		lblDuracion = new JLabel("Duració");
		lblDuracion.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDuracion.setBounds(311, 128, 79, 17);
		
		//Duracion
		textFieldDureacion = new JTextField();
		textFieldDureacion.setBackground(new Color(0, 128, 192));
		textFieldDureacion.setForeground(new Color(238, 238, 236));
		textFieldDureacion.setFont(new Font("Dialog", Font.BOLD, 14));
		textFieldDureacion.setColumns(10);
		textFieldDureacion.setBounds(387, 128, 129, 20);

		lblFormato = new JLabel("Format");
		lblFormato.setFont(new Font("Dialog", Font.BOLD, 15));
		lblFormato.setBounds(182, 185, 79, 17);
		
		//Formato
		formatoBox = new JComboBox<Object>();
		formatoBox.setBackground(new Color(0, 128, 192));
		formatoBox.setForeground(new Color(238, 238, 236));
		formatoBox.setFont(new Font("Dialog", Font.BOLD, 14));
		formatoBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"Digital", "Físic"}));
		formatoBox.setBounds(255, 183, 129, 22);
		
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
		panelSecundario.add(textFieldDirector);
		panelSecundario.add(lblActores);
		panelSecundario.add(textFieldActores);
		panelSecundario.add(lblPremios);
		panelSecundario.add(textFieldPremios);
		panelSecundario.add(lblDuracion);
		panelSecundario.add(textFieldDureacion);
		panelSecundario.add(lblFormato);
		panelSecundario.add(formatoBox);
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
				director = textFieldDirector.getText().toString();
				actoresPrincipales = textFieldActores.getText().toString();
				premios = textFieldPremios.getText().toString();
				duracion = Integer.parseInt(textFieldDureacion.getText());
				formato = formatoBox.getSelectedItem().toString();
				
				Pelicula pelicula = new Pelicula(documento.getISBN(), director, actoresPrincipales, premios, duracion, formato);
				DocumentoMaxDB docDB = new DocumentoMaxDB();
				
				if (docDB.updateDocPel(documento, pelicula)) {
					JOptionPane.showMessageDialog(panelSecundario, "Actualización exitoso", "Pelicula", JOptionPane.INFORMATION_MESSAGE);
					MenuGestor menu = new MenuGestor();
					menu.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(panelSecundario, "Error al introducir datos en la DB", "Error", JOptionPane.ERROR_MESSAGE);
				}// if else
			}// actionPerformed
		});
	}// VentanaModificarPelicula
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaModificarPelicula frame = new VentanaModificarPelicula();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}// try catch
			}// run
		});
	}// main
}// VentanaModificarPelicula
