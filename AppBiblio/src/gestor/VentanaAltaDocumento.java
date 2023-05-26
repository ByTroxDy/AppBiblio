package gestor;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.Documento;
import db.DocumentoDB;

public class VentanaAltaDocumento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private int isbn;
	private String titulo;
	private String autor;


	public int getISBN() {
		return isbn;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public String getAutor() {
		return autor;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAltaDocumento frame = new VentanaAltaDocumento();
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
	public VentanaAltaDocumento() {
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
		JLabel lblAlta = new JLabel("ALTA DOCUMENTO");
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
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(46, 95, 60, 17);
		panel_1.add(lblTitulo);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setBounds(46, 66, 60, 17);
		panel_1.add(lblIsbn);
		
		
		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setBounds(195, 66, 60, 17);
		panel_1.add(lblAutor);
		
		//TITULO
		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 10));
		textField.setColumns(10);
		textField.setBounds(99, 98, 69, 21);
		panel_1.add(textField);
		
		//isbn
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Dialog", Font.PLAIN, 10));
		textField_1.setColumns(10);
		textField_1.setBounds(99, 65, 69, 21);
		panel_1.add(textField_1);

		//AUTOR
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Dialog", Font.PLAIN, 10));
		textField_2.setColumns(10);
		textField_2.setBounds(245, 65, 69, 21);
		panel_1.add(textField_2);
		
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
		

		JComboBox tipo = new JComboBox();
		tipo.setModel(new DefaultComboBoxModel(new String[] {"Libro", "Pelicula", "Música", "Documental"}));
		tipo.setBounds(245, 94, 74, 26);
		panel_1.add(tipo);
		
		JLabel lblTipo = new JLabel("tipo");
		lblTipo.setBounds(195, 99, 60, 17);
		panel_1.add(lblTipo);
		
		
		//------------------------- SELECCIÓN TIPO ------------------------- //
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setForeground(Color.BLACK);
		btnAceptar.setFont(new Font("Dialog", Font.BOLD, 12));
		btnAceptar.setFocusPainted(false);
		btnAceptar.setBorderPainted(false);
		btnAceptar.setBackground(UIManager.getColor("Button.darkShadow"));
		btnAceptar.setBounds(261, 167, 87, 28);
		btnAceptar.addActionListener(new ActionListener() {
		//función para crear objeto Documento y llamada a función inserar
			public void actionPerformed(ActionEvent e) {  
				titulo = textField.getText();
				isbn = Integer.parseInt(textField_1.getText());
				autor = textField_2.getText();
					
				String seleccion = tipo.getSelectedItem().toString();
				if (seleccion == "Libro") {
					ventanaAltaLibro frame = new ventanaAltaLibro();
					// El meu document
					frame.setDocument(new Documento(isbn, titulo, autor));
					frame.setVisible(true);
					dispose();
				} else if(seleccion == "Pelicula") {
					ventanaAltaLibro frame = new ventanaAltaLibro();
					frame.setVisible(true);
					dispose();
				}
	
			}//actionPerformed
		});			
		panel_1.add(btnAceptar);

	}
}
