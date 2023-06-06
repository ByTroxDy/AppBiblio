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
	private JLabel lblAlta, lblNewLabel, lblIntroducDatos, lblProductora, lblPremios, lblDocumentalesRealcionados, lblDuracion, lblFormato;
	private JTextField textFieldProductora, textFieldPremios, textFieldDocRelacionados, textFieldDuracion;
	private JButton btnVolver, btnAceptar;
	
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

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(0, 128, 192));
		panelPrincipal.setForeground(new Color(0, 0, 0));
		panelPrincipal.setBorder(null);
		panelPrincipal.setBounds(0, 0, 592, 70);
		panelPrincipal.setLayout(null);
		contentPane.add(panelPrincipal);
		
		lblAlta = new JLabel("ALTA DOCUMENTAL");
		lblAlta.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlta.setForeground(new Color(255, 255, 255));
		lblAlta.setBounds(63, 11, 427, 48);
		lblAlta.setFont(new Font("Dialog", Font.BOLD, 40));
		panelPrincipal.add(lblAlta);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("img/icono64.png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(485, 0, 71, 73);
		panelPrincipal.add(lblNewLabel);

		JPanel panelSecundario = new JPanel();
		panelSecundario.setLayout(null);
		panelSecundario.setForeground(new Color(238, 238, 236));
		panelSecundario.setBackground(SystemColor.window);
		panelSecundario.setBorder(null);
		panelSecundario.setBounds(0, 70, 592, 297);
		contentPane.add(panelSecundario);
				
		lblIntroducDatos = new JLabel("Introdueix les dades");
		lblIntroducDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroducDatos.setForeground(new Color(0, 0, 0));
		lblIntroducDatos.setFont(new Font("Dialog", Font.BOLD, 25));
		lblIntroducDatos.setBounds(0, 11, 584, 45);
		panelSecundario.add(lblIntroducDatos);
		
		lblProductora = new JLabel("Productora");
		lblProductora.setFont(new Font("Dialog", Font.BOLD, 15));
		lblProductora.setBounds(21, 77, 79, 17);
		panelSecundario.add(lblProductora);
		
		//Proudctora
		textFieldProductora = new JTextField();
		textFieldProductora.setForeground(new Color(255, 255, 255));
		textFieldProductora.setFont(new Font("Dialog", Font.BOLD, 14));
		textFieldProductora.setBackground(new Color(0, 128, 192));
		textFieldProductora.setBounds(105, 77, 162, 20);
		panelSecundario.add(textFieldProductora);
		textFieldProductora.setColumns(10);
		
		
		lblPremios = new JLabel("Premis");
		lblPremios.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPremios.setBounds(21, 128, 79, 17);
		panelSecundario.add(lblPremios);
		
		//Premis
		textFieldPremios = new JTextField();
		textFieldPremios.setForeground(new Color(255, 255, 255));
		textFieldPremios.setFont(new Font("Dialog", Font.BOLD, 14));
		textFieldPremios.setBackground(new Color(0, 128, 192));
		textFieldPremios.setColumns(10);
		textFieldPremios.setBounds(105, 128, 162, 20);
		panelSecundario.add(textFieldPremios);
		
		lblDocumentalesRealcionados = new JLabel("Documentals relacionats");
		lblDocumentalesRealcionados.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDocumentalesRealcionados.setBounds(105, 176, 195, 17);
		panelSecundario.add(lblDocumentalesRealcionados);
		
		// documentals relacionats
		textFieldDocRelacionados = new JTextField();
		textFieldDocRelacionados.setForeground(new Color(255, 255, 255));
		textFieldDocRelacionados.setFont(new Font("Dialog", Font.BOLD, 14));
		textFieldDocRelacionados.setBackground(new Color(0, 128, 192));
		textFieldDocRelacionados.setColumns(10);
		textFieldDocRelacionados.setBounds(311, 175, 162, 20);
		panelSecundario.add(textFieldDocRelacionados);
		
		lblDuracion = new JLabel("Duració");
		lblDuracion.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDuracion.setBounds(321, 76, 79, 17);
		panelSecundario.add(lblDuracion);
		
		//Duració
		textFieldDuracion = new JTextField();
		textFieldDuracion.setForeground(new Color(255, 255, 255));
		textFieldDuracion.setFont(new Font("Dialog", Font.BOLD, 14));
		textFieldDuracion.setBackground(new Color(0, 128, 192));
		textFieldDuracion.setColumns(10);
		textFieldDuracion.setBounds(398, 76, 162, 20);
		panelSecundario.add(textFieldDuracion);
		
		lblFormato = new JLabel("Format");
		lblFormato.setFont(new Font("Dialog", Font.BOLD, 15));
		lblFormato.setBounds(321, 127, 79, 17);
		panelSecundario.add(lblFormato);
		
		//Format
		JComboBox<Object> formatoBox = new JComboBox<Object>();
		formatoBox.setForeground(new Color(255, 255, 255));
		formatoBox.setFont(new Font("Dialog", Font.BOLD, 14));
		formatoBox.setBackground(new Color(0, 128, 192));
		formatoBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"Físic", "Digital"}));
		formatoBox.setBounds(391, 126, 169, 22);
		panelSecundario.add(formatoBox);
		
		//Tornar
		btnVolver = new JButton("Tornar");
		btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		btnVolver.setBounds(12, 257, 277, 23);
		panelSecundario.add(btnVolver);
		
		//Acceptar
		btnAceptar = new JButton("Acceptar");
		btnAceptar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAceptar.setBounds(299, 256, 275, 24);
		panelSecundario.add(btnAceptar);
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
		btnAceptar.setForeground(new Color(255, 255, 255));
		btnAceptar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnAceptar.setBackground(new Color(0, 128, 192));
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
