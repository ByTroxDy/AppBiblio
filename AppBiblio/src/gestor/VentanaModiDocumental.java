package gestor;

import db.DocumentoMaxDB;
import app.Documento;
import app.Documental;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import admin.MenuAdmin;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class VentanaModiDocumental extends JFrame {
	private JPanel contentPane;
	private JLabel lblAlta, lblImagen, lblTituloDatos, lblProductora, lblPremios, lblDocumentalesRealcionados, lblDuracion, lblFormato;
	private JTextField txtProductora, txtPremios, txtDocRelacionados, txtDuracion;
	private JButton btnVolver, btnAceptar;
	private JComboBox<Object> cmbFormato;
	
	private int duracion;
	private String productora, premios, documentalesRealcionados, formato;
	static Documento documento;
	public static String grupo;
	
	public VentanaModiDocumental() {
		setTitle("Biblioteca App");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaModiDocumental.class.getResource("/img/icono32.png")));
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
		panelPrincipal.setBackground(SystemColor.window);
		panelPrincipal.setLayout(null);
		panelPrincipal.setBackground(new Color(0, 128, 192));
		panelPrincipal.setForeground(new Color(0, 0, 0));
		panelPrincipal.setBounds(0, 0, 592, 73);
		
		lblAlta = new JLabel("MODIFICAR DOCUMENTAL");
		lblAlta.setBackground(new Color(238, 238, 236));
		lblAlta.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlta.setBounds(30, 0, 488, 73);
		lblAlta.setForeground(new Color(238, 238, 236));
		lblAlta.setFont(new Font("Dialog", Font.BOLD, 35));
		
		lblImagen = new JLabel("");
		lblImagen.setIcon(new ImageIcon(MenuGestor.class.getResource("/img/icono64.png")));
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagen.setBounds(506, 0, 60, 73);

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
		lblTituloDatos.setBounds(0, 25, 592, 31);

		lblProductora = new JLabel("Productora");
		lblProductora.setFont(new Font("Dialog", Font.BOLD, 15));
		lblProductora.setBounds(32, 90, 92, 17);
		
		//Proudctora
		txtProductora = new JTextField();
		txtProductora.setFont(new Font("Dialog", Font.BOLD, 14));
		txtProductora.setBackground(new Color(0, 128, 192));
		txtProductora.setBounds(126, 90, 149, 20);
		txtProductora.setColumns(10);

		lblPremios = new JLabel("Premis");
		lblPremios.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPremios.setBounds(32, 132, 79, 17);
		
		//Premios
		txtPremios = new JTextField();
		txtPremios.setFont(new Font("Dialog", Font.BOLD, 14));
		txtPremios.setBackground(new Color(0, 128, 192));
		txtPremios.setColumns(10);
		txtPremios.setBounds(126, 132, 149, 20);
		
		lblDocumentalesRealcionados = new JLabel("Documentals relacionats");
		lblDocumentalesRealcionados.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDocumentalesRealcionados.setBounds(89, 175, 229, 17);
		
		txtDocRelacionados = new JTextField();
		txtDocRelacionados.setFont(new Font("Dialog", Font.BOLD, 14));
		txtDocRelacionados.setBackground(new Color(0, 128, 192));
		txtDocRelacionados.setColumns(10);
		txtDocRelacionados.setBounds(308, 174, 149, 20);
		
		lblDuracion = new JLabel("Duració");
		lblDuracion.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDuracion.setBounds(319, 90, 79, 17);
		
		//Duracion
		txtDuracion = new JTextField();
		txtDuracion.setFont(new Font("Dialog", Font.BOLD, 14));
		txtDuracion.setBackground(new Color(0, 128, 192));
		txtDuracion.setColumns(10);
		txtDuracion.setBounds(396, 90, 149, 20);
		
		lblFormato = new JLabel("Format");
		lblFormato.setFont(new Font("Dialog", Font.BOLD, 15));
		lblFormato.setBounds(319, 132, 79, 17);
		
		//Formato
		cmbFormato = new JComboBox<Object>();
		cmbFormato.setForeground(new Color(238, 238, 236));
		cmbFormato.setFont(new Font("Dialog", Font.BOLD, 14));
		cmbFormato.setModel(new DefaultComboBoxModel<Object>(new String[] {"Físico", "Digital"}));
		cmbFormato.setBounds(396, 130, 149, 22);
		cmbFormato.setBackground(new Color(0, 128, 192));
		
		//Volver
		btnVolver = new JButton("Tornar");
		btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVolver.setForeground(new Color(238, 238, 236));
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVolver.setBackground(new Color(0, 128, 192));
		btnVolver.setBounds(7, 270, 284, 23);
		
		//Aceptar
		btnAceptar = new JButton("Modificar");
		btnAceptar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAceptar.setBounds(308, 270, 279, 24);
		btnAceptar.setForeground(new Color(238, 238, 236));
		btnAceptar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnAceptar.setBackground(new Color(0, 128, 192));
		
		panelSecundario.add(lblTituloDatos);
		panelSecundario.add(txtProductora);
		panelSecundario.add(lblProductora);
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
				if (txtProductora.getText().isEmpty() | txtPremios.getText().isEmpty() | txtDocRelacionados.getText().isEmpty()
						| txtDuracion.getText().isEmpty()) {
					JOptionPane.showMessageDialog(panelSecundario, "Si us plau, introdueix tots els camps.", "Alerta", JOptionPane.WARNING_MESSAGE);
				} else {
					productora = txtProductora.getText();
					premios = txtPremios.getText();
					documentalesRealcionados = txtDocRelacionados.getText();
					duracion = Integer.parseInt(txtDuracion.getText());
					formato = (String) cmbFormato.getSelectedItem();
					
					Documental documental = new Documental(documento.getISBN(), productora, premios, documentalesRealcionados, duracion, formato);
					DocumentoMaxDB docDB = new DocumentoMaxDB();
					
					if (docDB.updateDocDocl(documento, documental)) {
						JOptionPane.showMessageDialog(panelSecundario, "Actualització exitosa.", "Documental", JOptionPane.INFORMATION_MESSAGE);
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
}// VentanaModificarDocumental
