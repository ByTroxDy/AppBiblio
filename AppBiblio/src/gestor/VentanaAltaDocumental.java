package gestor;

import db.DocumentoMaxDB;
import app.Documental;
import app.Documento;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import admin.MenuAdmin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class VentanaAltaDocumental extends JFrame {
	private JPanel contentPane;
	private JLabel lblTituloAlta, lblImagen, lblIntroducDatos, lblProductora, lblPremios, lblDocumentalesRealcionados, lblDuracion, lblFormato;
	private JTextField txtProductora, txtPremios, txtDocRelacionados, txtDuracion;
	private JButton btnVolver, btnAceptar;
	private JComboBox<Object> cmbFormato;
	
	private String productora, premios, documentalesRealcionados, formato;
	private int duracion;
	static Documento documento;
	public static String grupo;

	public VentanaAltaDocumental() {
		setTitle("Biblioteca App");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaAltaDocumental.class.getResource("/img/icono32.png")));
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
		txtProductora = new JTextField();
		txtProductora.setForeground(new Color(255, 255, 255));
		txtProductora.setFont(new Font("Dialog", Font.BOLD, 14));
		txtProductora.setBackground(new Color(0, 128, 192));
		txtProductora.setBounds(108, 77, 162, 20);
		txtProductora.setColumns(10);
		
		lblPremios = new JLabel("Premis");
		lblPremios.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPremios.setBounds(21, 128, 79, 17);
		
		//Premis
		txtPremios = new JTextField();
		txtPremios.setForeground(new Color(255, 255, 255));
		txtPremios.setFont(new Font("Dialog", Font.BOLD, 14));
		txtPremios.setBackground(new Color(0, 128, 192));
		txtPremios.setColumns(10);
		txtPremios.setBounds(108, 128, 162, 20);
		
		lblDocumentalesRealcionados = new JLabel("Documentals relacionats");
		lblDocumentalesRealcionados.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDocumentalesRealcionados.setBounds(105, 176, 195, 17);
		
		// documentals relacionats
		txtDocRelacionados = new JTextField();
		txtDocRelacionados.setForeground(new Color(255, 255, 255));
		txtDocRelacionados.setFont(new Font("Dialog", Font.BOLD, 14));
		txtDocRelacionados.setBackground(new Color(0, 128, 192));
		txtDocRelacionados.setColumns(10);
		txtDocRelacionados.setBounds(311, 175, 162, 20);
		
		lblDuracion = new JLabel("Duració");
		lblDuracion.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDuracion.setBounds(321, 76, 79, 17);
		
		//Duració
		txtDuracion = new JTextField();
		txtDuracion.setForeground(new Color(255, 255, 255));
		txtDuracion.setFont(new Font("Dialog", Font.BOLD, 14));
		txtDuracion.setBackground(new Color(0, 128, 192));
		txtDuracion.setColumns(10);
		txtDuracion.setBounds(398, 76, 162, 20);
		
		lblFormato = new JLabel("Format");
		lblFormato.setFont(new Font("Dialog", Font.BOLD, 15));
		lblFormato.setBounds(321, 127, 79, 17);
		
		//Format
		cmbFormato = new JComboBox<Object>();
		cmbFormato.setForeground(new Color(255, 255, 255));
		cmbFormato.setFont(new Font("Dialog", Font.BOLD, 14));
		cmbFormato.setBackground(new Color(0, 128, 192));
		cmbFormato.setModel(new DefaultComboBoxModel<Object>(new String[] {"Físic", "Digital"}));
		cmbFormato.setBounds(391, 126, 169, 22);
		
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
		panelSecundario.add(txtProductora);
		panelSecundario.add(lblPremios);
		panelSecundario.add(txtPremios);
		panelSecundario.add(lblDocumentalesRealcionados);
		panelSecundario.add(txtDocRelacionados);
		panelSecundario.add(lblDuracion);
		panelSecundario.add(txtDuracion);
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
				if (txtProductora.getText().isEmpty() | txtPremios.getText().isEmpty() | txtPremios.getText().isEmpty() | 
						txtDocRelacionados.getText().isEmpty() | txtDuracion.getText().isEmpty()) {
					JOptionPane.showMessageDialog(panelSecundario, "Si us plau, introdueix tots els camps.", "Alerta", JOptionPane.WARNING_MESSAGE);
				} else {
					productora = txtProductora.getText();
					premios = txtPremios.getText();
					documentalesRealcionados = txtDocRelacionados.getText();
					duracion = Integer.parseInt(txtDuracion.getText());
					formato = (String) cmbFormato.getSelectedItem();
					
					Documental documental = new Documental(documento.getISBN(), productora, premios, documentalesRealcionados, duracion, formato);
					DocumentoMaxDB docDB = new DocumentoMaxDB();
					
					if (docDB.insertDocDocl(documento, documental)) {
						JOptionPane.showMessageDialog(panelSecundario, "Registre exitós.", "Documental", JOptionPane.INFORMATION_MESSAGE);
						if (grupo.equals("gestor")) {
							MenuGestor menu = new MenuGestor();
							menu.setVisible(true);
						} else if (grupo.equals("admin")) {
							MenuAdmin menu = new MenuAdmin();
							menu.setVisible(true);
						}
					} else {
						JOptionPane.showMessageDialog(panelSecundario, "Hi ha hagut un error en introduir les dades a la base de dades.", "Error", JOptionPane.ERROR_MESSAGE);
					}//if else
				}// if else
			}//actionPerformed
		});
	}
}// VentanaAltaDocumental
