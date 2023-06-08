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
	private JTextField textFieldDirector, textFieldActores, textFieldPremios, textFieldDureacion;	
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
		contentPane.add(panelPrincipal);
		
		JLabel lblAlta = new JLabel("ALTA PELÍCULA");
		lblAlta.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlta.setBounds(125, 11, 320, 48);
		lblAlta.setForeground(new Color(255, 255, 255));
		lblAlta.setFont(new Font("Dialog", Font.BOLD, 40));
		panelPrincipal.add(lblAlta);
		
		JLabel lblImagen = new JLabel("");
		lblImagen.setIcon(new ImageIcon(MenuGestor.class.getResource("/img/icono64.png")));
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagen.setBounds(455, 0, 84, 70);
		panelPrincipal.add(lblImagen);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setForeground(new Color(238, 238, 236));
		panel.setBackground(SystemColor.window);
		panel.setBorder(null);
		panel.setBounds(0, 70, 592, 297);
		contentPane.add(panel);
				
		JLabel lblIntroduceDatos = new JLabel("Introdueix les dades");
		lblIntroduceDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroduceDatos.setForeground(new Color(0, 0, 0));
		lblIntroduceDatos.setFont(new Font("Dialog", Font.BOLD, 25));
		lblIntroduceDatos.setBounds(0, 28, 584, 28);
		panel.add(lblIntroduceDatos);

		JLabel lblDirector = new JLabel("Director");
		lblDirector.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDirector.setBounds(35, 79, 79, 17);
		panel.add(lblDirector);

		//Director
		textFieldDirector = new JTextField();
		textFieldDirector.setFont(new Font("Dialog", Font.BOLD, 14));
		textFieldDirector.setForeground(new Color(255, 255, 255));
		textFieldDirector.setBackground(new Color(0, 128, 192));
		textFieldDirector.setBounds(97, 79, 158, 20);
		panel.add(textFieldDirector);
		textFieldDirector.setColumns(10);

		JLabel lblActorees = new JLabel("Actors");
		lblActorees.setFont(new Font("Dialog", Font.BOLD, 15));
		lblActorees.setBounds(318, 79, 79, 17);
		panel.add(lblActorees);
		
		//Actores
		textFieldActores = new JTextField();
		textFieldActores.setFont(new Font("Dialog", Font.BOLD, 14));
		textFieldActores.setForeground(new Color(255, 255, 255));
		textFieldActores.setBackground(new Color(0, 128, 192));
		textFieldActores.setColumns(10);
		textFieldActores.setBounds(380, 79, 158, 20);
		panel.add(textFieldActores);

		JLabel lblPremios = new JLabel("Premis");
		lblPremios.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPremios.setBounds(35, 126, 79, 17);
		panel.add(lblPremios);
		
		//Premios
		textFieldPremios = new JTextField();
		textFieldPremios.setFont(new Font("Dialog", Font.BOLD, 14));
		textFieldPremios.setForeground(new Color(255, 255, 255));
		textFieldPremios.setBackground(new Color(0, 128, 192));
		textFieldPremios.setColumns(10);
		textFieldPremios.setBounds(97, 126, 158, 20);
		panel.add(textFieldPremios);

		JLabel lblDuracion = new JLabel("Duració");
		lblDuracion.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDuracion.setBounds(318, 123, 79, 17);
		panel.add(lblDuracion);
		
		//Duracion
		textFieldDureacion = new JTextField();
		textFieldDureacion.setFont(new Font("Dialog", Font.BOLD, 14));
		textFieldDureacion.setForeground(new Color(255, 255, 255));
		textFieldDureacion.setBackground(new Color(0, 128, 192));
		textFieldDureacion.setColumns(10);
		textFieldDureacion.setBounds(380, 123, 158, 20);
		panel.add(textFieldDureacion);

		JLabel lblFormato = new JLabel("Formato");
		lblFormato.setFont(new Font("Dialog", Font.BOLD, 15));
		lblFormato.setBounds(159, 176, 79, 17);
		panel.add(lblFormato);
		
		//Formato
		JComboBox<Object> formatoBox = new JComboBox<Object>();
		formatoBox.setFont(new Font("Dialog", Font.BOLD, 14));
		formatoBox.setForeground(new Color(255, 255, 255));
		formatoBox.setBackground(new Color(0, 128, 192));
		formatoBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"Físic", "Digital"}));
		formatoBox.setBounds(222, 175, 158, 22);
		panel.add(formatoBox);
		
		//Volver
		JButton btnVolver = new JButton("Tornar");
		btnVolver.addActionListener(new ActionListener() {
			//función para cambiar de ventana haciendo click en el boton
			public void actionPerformed(ActionEvent e) {    
				MenuGestor frame = new MenuGestor();
				frame.setVisible(true);
				dispose();
			}// actionPerformed
		});
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVolver.setBackground(new Color(0, 128, 192));
		btnVolver.setBounds(12, 257, 283, 28);
		panel.add(btnVolver);
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);

		//Alta
		JButton btnNewButton = new JButton("Acceptar");
		btnNewButton.setBounds(307, 257, 279, 28);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				director = textFieldDirector.getText().toString();
				actoresPrincipales = textFieldActores.getText().toString();
				premios = textFieldPremios.getText().toString();
				duracion = Integer.parseInt(textFieldDureacion.getText());
				formato = formatoBox.getSelectedItem().toString();
				
				Pelicula pelicula = new Pelicula(documento.getISBN(), director, actoresPrincipales, premios, duracion, formato);

				DocumentoMaxDB docDB = new DocumentoMaxDB();
				if (docDB.insertDocPel(documento, pelicula)) {
					JOptionPane.showMessageDialog(panel, "Registro exitoso", "Pelicula", JOptionPane.INFORMATION_MESSAGE);
					MenuGestor menu = new MenuGestor();
					menu.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(panel, "Error al introducir datos en la DB", "Error", JOptionPane.ERROR_MESSAGE);
				}//if else
			}//actionPerformed
		});
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton.setBackground(new Color(0, 128, 192));
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
