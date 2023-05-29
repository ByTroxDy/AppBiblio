package gestor;

import db.DocumentoMaxDB;
import app.Documento;
import app.Pelicula;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

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
		setBounds(100, 100, 478, 329);
		contentPane = new JPanel();
		contentPane.setForeground(UIManager.getColor("Panel.foreground"));
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panelTitle = new JPanel();
		panelTitle.setBackground(SystemColor.window);
		panelTitle.setForeground(new Color(0, 0, 0));
		panelTitle.setBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0, 0), 3)));
		panelTitle.setBounds(53, 14, 359, 44);
		contentPane.add(panelTitle);
		
		JLabel lblAlta = new JLabel("ALTA PELICULA");
		lblAlta.setForeground(new Color(0, 0, 0));
		lblAlta.setFont(new Font("Century Schoolbook L", Font.BOLD | Font.ITALIC, 20));
		panelTitle.add(lblAlta);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setForeground(new Color(238, 238, 236));
		panel.setBackground(SystemColor.window);
		panel.setBorder(new TitledBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0, 0), 3, true)), "Bienvenido", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(53, 70, 360, 208);
		contentPane.add(panel);
				
		JLabel lblIntroduceDatos = new JLabel("Introduce los datos");
		lblIntroduceDatos.setForeground(new Color(0, 0, 0));
		lblIntroduceDatos.setFont(new Font("Century Schoolbook L", Font.BOLD | Font.ITALIC, 20));
		lblIntroduceDatos.setBounds(66, 28, 212, 28);
		panel.add(lblIntroduceDatos);
		
		
		JLabel lblDirector = new JLabel("Director");
		lblDirector.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDirector.setBounds(12, 67, 79, 17);
		panel.add(lblDirector);

		//Director
		textFieldDirector = new JTextField();
		textFieldDirector.setBounds(74, 67, 86, 20);
		panel.add(textFieldDirector);
		textFieldDirector.setColumns(10);
		
		
		JLabel lblActorees = new JLabel("Actores");
		lblActorees.setFont(new Font("Dialog", Font.BOLD, 15));
		lblActorees.setBounds(170, 67, 79, 17);
		panel.add(lblActorees);
		
		//Actores
		textFieldActores = new JTextField();
		textFieldActores.setColumns(10);
		textFieldActores.setBounds(246, 67, 86, 20);
		panel.add(textFieldActores);
		
		
		JLabel lblPremios = new JLabel("Premios");
		lblPremios.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPremios.setBounds(12, 98, 79, 17);
		panel.add(lblPremios);
		
		//Premios
		textFieldPremios = new JTextField();
		textFieldPremios.setColumns(10);
		textFieldPremios.setBounds(74, 98, 86, 20);
		panel.add(textFieldPremios);
		
		
		JLabel lblDuracion = new JLabel("Duracion");
		lblDuracion.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDuracion.setBounds(170, 95, 79, 17);
		panel.add(lblDuracion);
		
		//Duracion
		textFieldDureacion = new JTextField();
		textFieldDureacion.setColumns(10);
		textFieldDureacion.setBounds(246, 95, 86, 20);
		panel.add(textFieldDureacion);
		
		
		JLabel lblFormato = new JLabel("Formato");
		lblFormato.setFont(new Font("Dialog", Font.BOLD, 15));
		lblFormato.setBounds(94, 129, 79, 17);
		panel.add(lblFormato);
		
		//Formato
		JComboBox<Object> formatoBox = new JComboBox<Object>();
		formatoBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"Digital", "Fisico"}));
		formatoBox.setBounds(157, 128, 92, 22);
		panel.add(formatoBox);
		
		//Volver
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			//función para cambiar de ventana haciendo click en el boton
			public void actionPerformed(ActionEvent e) {    
				MenuGestor frame = new MenuGestor();
				frame.setVisible(true);
				dispose();
			}
		});
		btnVolver.setForeground(Color.BLACK);
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 12));
		btnVolver.setBackground(UIManager.getColor("Button.darkShadow"));
		btnVolver.setBounds(12, 169, 79, 28);
		panel.add(btnVolver);
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);
		
		
		//Alta
		JButton btnNewButton = new JButton("Alta");
		btnNewButton.setBounds(269, 168, 79, 28);
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
				} else {
					JOptionPane.showMessageDialog(panel, "Error al introducir datos en la DB", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 12));
		btnNewButton.setBackground(UIManager.getColor("Button.darkShadow"));
		
	}
	
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
}
