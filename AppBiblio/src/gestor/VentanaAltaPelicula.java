package gestor;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

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

import app.Documento;
import app.Pelicula;
import db.DocumentoDB;

public class VentanaAltaPelicula extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Documento documento;
	private String director;
	private String actoresPrincipales;
	private String premios;
	private int duracion;
	private String formato;
	private JTextField textFieldDirector;
	private JTextField textFieldActores;
	private JTextField textFieldPremios;
	private JTextField textFieldDureacion;

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

	public void setDocument(Documento myDoc) {
		this.documento = myDoc;
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

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.window);
		panel.setForeground(new Color(0, 0, 0));
		panel.setBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0, 0), 3)));
		panel.setBounds(53, 14, 359, 44);
		contentPane.add(panel);
		
		JLabel lblAlta = new JLabel("ALTA PELICULA");
		lblAlta.setForeground(new Color(0, 0, 0));
		lblAlta.setFont(new Font("Century Schoolbook L", Font.BOLD | Font.ITALIC, 20));
		panel.add(lblAlta);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(new Color(238, 238, 236));
		panel_1.setBackground(SystemColor.window);
		panel_1.setBorder(new TitledBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0, 0), 3, true)), "Bienvenido", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(53, 70, 360, 208);
		contentPane.add(panel_1);
				
		JLabel lblIntroduceDatos = new JLabel("Introduce los datos");
		lblIntroduceDatos.setForeground(new Color(0, 0, 0));
		lblIntroduceDatos.setFont(new Font("Century Schoolbook L", Font.BOLD | Font.ITALIC, 20));
		lblIntroduceDatos.setBounds(66, 28, 212, 28);
		panel_1.add(lblIntroduceDatos);
		
		
		JLabel lblDirector = new JLabel("Director");
		lblDirector.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDirector.setBounds(12, 67, 79, 17);
		panel_1.add(lblDirector);

		//Director
		textFieldDirector = new JTextField();
		textFieldDirector.setBounds(74, 67, 86, 20);
		panel_1.add(textFieldDirector);
		textFieldDirector.setColumns(10);
		
		
		JLabel lblActorees = new JLabel("Actores");
		lblActorees.setFont(new Font("Dialog", Font.BOLD, 15));
		lblActorees.setBounds(170, 67, 79, 17);
		panel_1.add(lblActorees);
		
		//Actores
		textFieldActores = new JTextField();
		textFieldActores.setColumns(10);
		textFieldActores.setBounds(246, 67, 86, 20);
		panel_1.add(textFieldActores);
		
		
		JLabel lblPremios = new JLabel("Premios");
		lblPremios.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPremios.setBounds(12, 98, 79, 17);
		panel_1.add(lblPremios);
		
		//Premios
		textFieldPremios = new JTextField();
		textFieldPremios.setColumns(10);
		textFieldPremios.setBounds(74, 98, 86, 20);
		panel_1.add(textFieldPremios);
		
		
		JLabel lblDuracion = new JLabel("Duracion");
		lblDuracion.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDuracion.setBounds(170, 95, 79, 17);
		panel_1.add(lblDuracion);
		
		//Duracion
		textFieldDureacion = new JTextField();
		textFieldDureacion.setColumns(10);
		textFieldDureacion.setBounds(246, 95, 86, 20);
		panel_1.add(textFieldDureacion);
		
		
		JLabel lblFormato = new JLabel("Formato");
		lblFormato.setFont(new Font("Dialog", Font.BOLD, 15));
		lblFormato.setBounds(94, 129, 79, 17);
		panel_1.add(lblFormato);
		
		//Formato
		JComboBox formatoBox = new JComboBox();
		formatoBox.setModel(new DefaultComboBoxModel(new String[] {"Digital", "Fisico"}));
		formatoBox.setBounds(157, 128, 92, 22);
		panel_1.add(formatoBox);
		
		//Volver
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
		btnVolver.setBackground(UIManager.getColor("Button.darkShadow"));
		btnVolver.setBounds(12, 169, 79, 28);
		panel_1.add(btnVolver);
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);
		
		
		//Alta
		JButton btnNewButton = new JButton("Alta");
		btnNewButton.setBounds(269, 168, 79, 28);
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				director = textFieldDirector.getText().toString();
				actoresPrincipales = textFieldActores.getText().toString();
				premios = textFieldPremios.getText().toString();
				duracion = Integer.parseInt(textFieldDureacion.getText());
				formato = formatoBox.getSelectedItem().toString();
				
				Pelicula pelicula = new Pelicula(documento.getISBN(), director, actoresPrincipales, premios, duracion, formato);
				
				// Primer Document
				DocumentoDB docDB = new DocumentoDB();
				
				try {
					docDB.insertDocumentPelicula(documento, pelicula);
				} catch (SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 12));
		btnNewButton.setBackground(UIManager.getColor("Button.darkShadow"));
	}
}
