package gestor;

import db.DocumentoMaxDB;
import app.Documental;
import app.Documento;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAltaDocumental extends JFrame {
	private static final long serialVersionUID = 1L;
	static Documento documento;

	private JPanel contentPane;
	private JLabel lblTituloAlta, lblImagen, lblIntroducDatos, lblProductora, lblPremios, lblDocumentalesRealcionados, lblDuracion, lblFormato;
	private JTextField textFieldProductora, textFieldPremios, textFieldDocRelacionados, textFieldDuracion;
	private JButton btnVolver, btnAceptar;
	private JComboBox<Object> formatoBox;
	
	private String productora, premios, documentalesRealcionados, formato;
	private int duracion;

	public VentanaAltaDocumental() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setForeground(UIManager.getColor("Panel.foreground"));
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		// panel Principal
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(0, 128, 192));
		panelPrincipal.setForeground(new Color(0, 0, 0));
		panelPrincipal.setBorder(null);
		panelPrincipal.setBounds(0, 0, 592, 70);
		panelPrincipal.setLayout(null);
		
		// Titulo panel Principal
		lblTituloAlta = new JLabel("ALTA DOCUMENTAL");
		lblTituloAlta.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloAlta.setForeground(new Color(255, 255, 255));
		lblTituloAlta.setBounds(63, 11, 427, 48);
		lblTituloAlta.setFont(new Font("Dialog", Font.BOLD, 40));
		
		// Imagen del panel Principal
		lblImagen = new JLabel("");
		lblImagen.setIcon(new ImageIcon(MenuGestor.class.getResource("/img/icono64.png")));
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagen.setBounds(485, 0, 71, 73);
		
		panelPrincipal.add(lblTituloAlta);
		panelPrincipal.add(lblImagen);
		contentPane.add(panelPrincipal);
		
		JPanel panelSecundario = new JPanel();
		panelSecundario.setLayout(null);
		panelSecundario.setForeground(new Color(238, 238, 236));
		panelSecundario.setBackground(SystemColor.window);
		panelSecundario.setBorder(null);
		panelSecundario.setBounds(0, 70, 592, 297);
		
		// titulo panel Secundario
		lblIntroducDatos = new JLabel("Introdueix les dades");
		lblIntroducDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroducDatos.setForeground(new Color(0, 0, 0));
		lblIntroducDatos.setFont(new Font("Dialog", Font.BOLD, 25));
		lblIntroducDatos.setBounds(0, 11, 584, 45);
		
		lblProductora = new JLabel("Productora");
		lblProductora.setFont(new Font("Dialog", Font.BOLD, 15));
		lblProductora.setBounds(21, 77, 91, 17);
		
		//Proudctora
		textFieldProductora = new JTextField();
		textFieldProductora.setForeground(new Color(255, 255, 255));
		textFieldProductora.setFont(new Font("Dialog", Font.BOLD, 14));
		textFieldProductora.setBackground(new Color(0, 128, 192));
		textFieldProductora.setBounds(108, 77, 162, 20);
		textFieldProductora.setColumns(10);
		
		lblPremios = new JLabel("Premis");
		lblPremios.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPremios.setBounds(21, 128, 79, 17);
		
		//Premis
		textFieldPremios = new JTextField();
		textFieldPremios.setForeground(new Color(255, 255, 255));
		textFieldPremios.setFont(new Font("Dialog", Font.BOLD, 14));
		textFieldPremios.setBackground(new Color(0, 128, 192));
		textFieldPremios.setColumns(10);
		textFieldPremios.setBounds(108, 128, 162, 20);
		
		lblDocumentalesRealcionados = new JLabel("Documentals relacionats");
		lblDocumentalesRealcionados.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDocumentalesRealcionados.setBounds(105, 176, 195, 17);
		
		// documentals relacionats
		textFieldDocRelacionados = new JTextField();
		textFieldDocRelacionados.setForeground(new Color(255, 255, 255));
		textFieldDocRelacionados.setFont(new Font("Dialog", Font.BOLD, 14));
		textFieldDocRelacionados.setBackground(new Color(0, 128, 192));
		textFieldDocRelacionados.setColumns(10);
		textFieldDocRelacionados.setBounds(311, 175, 162, 20);
		
		lblDuracion = new JLabel("Duració");
		lblDuracion.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDuracion.setBounds(321, 76, 79, 17);
		
		//Duració
		textFieldDuracion = new JTextField();
		textFieldDuracion.setForeground(new Color(255, 255, 255));
		textFieldDuracion.setFont(new Font("Dialog", Font.BOLD, 14));
		textFieldDuracion.setBackground(new Color(0, 128, 192));
		textFieldDuracion.setColumns(10);
		textFieldDuracion.setBounds(398, 76, 162, 20);
		
		lblFormato = new JLabel("Format");
		lblFormato.setFont(new Font("Dialog", Font.BOLD, 15));
		lblFormato.setBounds(321, 127, 79, 17);
		
		//Format
		formatoBox = new JComboBox<Object>();
		formatoBox.setForeground(new Color(255, 255, 255));
		formatoBox.setFont(new Font("Dialog", Font.BOLD, 14));
		formatoBox.setBackground(new Color(0, 128, 192));
		formatoBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"Físic", "Digital"}));
		formatoBox.setBounds(391, 126, 169, 22);
		
		//Tornar
		btnVolver = new JButton("Tornar");
		btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));	
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVolver.setBackground(new Color(0, 128, 192));
		btnVolver.setBounds(12, 257, 277, 23);
		
		//Acceptar
		btnAceptar = new JButton("Acceptar");
		btnAceptar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAceptar.setBounds(299, 256, 275, 24);
		btnAceptar.setForeground(new Color(255, 255, 255));
		btnAceptar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnAceptar.setBackground(new Color(0, 128, 192));

		panelSecundario.add(lblIntroducDatos);
		panelSecundario.add(lblProductora);
		panelSecundario.add(textFieldProductora);
		panelSecundario.add(lblPremios);
		panelSecundario.add(textFieldPremios);
		panelSecundario.add(lblDocumentalesRealcionados);
		panelSecundario.add(textFieldDocRelacionados);
		panelSecundario.add(lblDuracion);
		panelSecundario.add(textFieldDuracion);
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
				productora = textFieldProductora.getText().toString();
				premios = textFieldPremios.getText().toString();
				documentalesRealcionados = textFieldDocRelacionados.getText().toString();
				duracion = Integer.parseInt(textFieldDuracion.getText());
				formato = formatoBox.getSelectedItem().toString();
				
				Documental documental = new Documental(documento.getISBN(), productora, premios, documentalesRealcionados, duracion, formato);
				DocumentoMaxDB docDB = new DocumentoMaxDB();
				
				if (docDB.insertDocDocl(documento, documental)) {
					JOptionPane.showMessageDialog(panelSecundario, "Registro exitoso", "Libro", JOptionPane.INFORMATION_MESSAGE);
					MenuGestor menu = new MenuGestor();
					menu.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(panelSecundario, "Error al introducir datos en la DB", "Error", JOptionPane.ERROR_MESSAGE);
				}//if else
			}//actionPerformed
		});
	}// VentanaAltaDocumental
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAltaDocumental frame = new VentanaAltaDocumental();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}// try catch
			}// run
		});
	}// main
}// VentanaAltaDocumental
