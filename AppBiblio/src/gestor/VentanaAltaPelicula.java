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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import app.Pelicula;
import db.DocumentoDB;

public class VentanaAltaPelicula extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_actores;
	private JTextField textField_Director;
	private JTextField textField_duracion;
	private JTextField textField_premios;
	private int isbn;
	private String director;
	private String actores;
	private String premios;
	private int dutracion;
	private String formato;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAltaPelicula frame = new VentanaAltaPelicula();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaAltaPelicula() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 323);
		contentPane = new JPanel();
		contentPane.setForeground(UIManager.getColor("Panel.foreground"));
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		//------------------------- PANELES ------------------------- //
		//DOCUMENTO
		JPanel s = new JPanel();
		s.setBackground(SystemColor.window);
		s.setForeground(new Color(0, 0, 0));
		s.setBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0, 0), 3)));
		s.setBounds(53, 14, 359, 44);
		contentPane.add(s);
		
		
		//------------------------- CAMPOS DOCUMENTO ------------------------- //
		
		//Titulo del panel creado
		JLabel lblAlta = new JLabel("ALTA PELÍCULA");
		lblAlta.setForeground(new Color(0, 0, 0));
		lblAlta.setFont(new Font("Dialog", Font.BOLD, 20));
		s.add(lblAlta);
		
		//Creación de panel de contenido
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(new Color(238, 238, 236));
		panel_1.setBackground(SystemColor.window);
		panel_1.setBorder(new TitledBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0, 0), 3, true)), "Bienvenido", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(53, 70, 360, 208);
		contentPane.add(panel_1);
		
		//Titulo Introduce los datos
		JLabel lblIntroduceLosDatos = new JLabel("Introduce los datos");
		lblIntroduceLosDatos.setForeground(Color.BLACK);
		lblIntroduceLosDatos.setFont(new Font("Dialog", Font.BOLD, 18));
		lblIntroduceLosDatos.setBounds(88, 26, 189, 28);
		panel_1.add(lblIntroduceLosDatos);
		
		//Labels y textField para la introducción de datos
		JLabel lblIsbn = new JLabel("Director");
		lblIsbn.setBounds(38, 75, 60, 17);
		panel_1.add(lblIsbn);
		
		//Director
		textField_Director = new JTextField();
		textField_Director.setFont(new Font("Dialog", Font.PLAIN, 10));
		textField_Director.setColumns(10);
		textField_Director.setBounds(98, 73, 69, 21);
		panel_1.add(textField_Director);
		

		JLabel lblAutor = new JLabel("Actores");
		lblAutor.setBounds(38, 115, 87, 17);
		panel_1.add(lblAutor);
		
		//Nombre de actores
		textField_actores = new JTextField();
		textField_actores.setFont(new Font("Dialog", Font.PLAIN, 10));
		textField_actores.setColumns(10);
		textField_actores.setBounds(98, 113, 69, 21);
		panel_1.add(textField_actores);
		
		JLabel lblPremios = new JLabel("Premios");
		lblPremios.setBounds(185, 113, 60, 17);
		panel_1.add(lblPremios);
		
		//Premios
		textField_premios = new JTextField();
		textField_premios.setFont(new Font("Dialog", Font.PLAIN, 10));
		textField_premios.setColumns(10);
		textField_premios.setBounds(255, 113, 69, 21);
		panel_1.add(textField_premios);
		
		JLabel lblTitulo = new JLabel("Duración");
		lblTitulo.setBounds(185, 75, 60, 17);
		panel_1.add(lblTitulo);
		
		//Duración
		textField_duracion = new JTextField();
		textField_duracion.setFont(new Font("Dialog", Font.PLAIN, 10));
		textField_duracion.setColumns(10);
		textField_duracion.setBounds(250, 73, 69, 21);
		panel_1.add(textField_duracion);
		
		

		JLabel lblTipo = new JLabel("Formato");
		lblTipo.setBounds(122, 150, 60, 17);
		panel_1.add(lblTipo);
		
		//Formato
		JComboBox seleccionFormato = new JComboBox();
		seleccionFormato.setModel(new DefaultComboBoxModel(new String[] {"MP4", "MP3"}));
		seleccionFormato.setBounds(171, 145, 74, 26);
		panel_1.add(seleccionFormato);
		
		//Volver a la ventana principal
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			//función para cambiar de ventana haciendo click en el boton
			public void actionPerformed(ActionEvent e) {    
				VentanaGestor frame = new VentanaGestor();
				frame.setVisible(true);
				dispose();
			}
		});
		btnVolver.setForeground(Color.BLACK);
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 12));
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);
		btnVolver.setBackground(UIManager.getColor("Button.darkShadow"));
		btnVolver.setBounds(12, 167, 79, 28);
		panel_1.add(btnVolver);
		

		//------------------------- DAR DE ALTA PELICULA  ------------------------- //
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setForeground(Color.BLACK);
		btnAceptar.setFont(new Font("Dialog", Font.BOLD, 12));
		btnAceptar.setFocusPainted(false);
		btnAceptar.setBorderPainted(false);
		btnAceptar.setBackground(UIManager.getColor("Button.darkShadow"));
		btnAceptar.setBounds(261, 167, 87, 28);
		btnAceptar.addActionListener(new ActionListener() {
		//función para crear objeto Pelicula y llamada a función inserar
		public void actionPerformed(ActionEvent e) {  

			VentanaAltaDocumento altaDocumento = new VentanaAltaDocumento();
			isbn = altaDocumento.getISBN();
			director = textField_duracion.getText().toString();
			actores = textField_actores.getText().toString();
			premios = textField_premios.getText().toString();
			dutracion = Integer.parseInt(textField_duracion.getText());
			formato = seleccionFormato.getSelectedItem().toString();

			Pelicula pelicula = new Pelicula(isbn,director, actores, premios, dutracion, formato);
			
			DocumentoDB docDB = new DocumentoDB();
			docDB.insertarPelicula(pelicula);

			}	
		});			
		panel_1.add(btnAceptar);
		

	}

}
