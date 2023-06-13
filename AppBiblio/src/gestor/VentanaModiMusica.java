package gestor;

import db.DocumentoMaxDB;
import app.Documento;
import app.Musica;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import com.toedter.calendar.JDateChooser;

import admin.MenuAdmin;

@SuppressWarnings("serial")
public class VentanaModiMusica extends JFrame {
	private JPanel contentPane;
	private JLabel lblAlta, lblImagen, lblTituloDatos, lblLugar, lblFecha, lblDuracion, lblFormat;
	private JTextField txtLugar, txtDuracion;
	private JButton btnVolver, btnAceptar;
	private JComboBox<Object> cmbFormato;
	private JDateChooser dateChooser;

	private String lugar, formato;
	private Date fecha;
	private int duracion;
	
	static Documento documento;
	public static String grupo;
	
	public VentanaModiMusica() {
		setTitle("Biblioteca App");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaModiMusica.class.getResource("/img/icono32.png")));
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
		panelPrincipal.setBounds(0, 0, 592, 68);

		lblAlta = new JLabel("MODIFICAR MÚSCIA");
		lblAlta.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlta.setBounds(61, 0, 402, 68);
		lblAlta.setForeground(new Color(238, 238, 236));
		lblAlta.setFont(new Font("Dialog", Font.BOLD, 40));
		
		lblImagen = new JLabel("");
		lblImagen.setIcon(new ImageIcon(VentanaModiMusica.class.getResource("/img/icono64.png")));
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagen.setBounds(475, 0, 60, 68);

		panelPrincipal.add(lblAlta);
		panelPrincipal.add(lblImagen);
		contentPane.add(panelPrincipal);
		
		JPanel panelSecundario = new JPanel();
		panelSecundario.setLayout(null);
		panelSecundario.setForeground(new Color(238, 238, 236));
		panelSecundario.setBackground(SystemColor.window);
		panelSecundario.setBorder(null);
		panelSecundario.setBounds(0, 70, 592, 297);
				
		lblTituloDatos = new JLabel("Introdueix les noves dades");
		lblTituloDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloDatos.setForeground(new Color(0, 0, 0));
		lblTituloDatos.setFont(new Font("Dialog", Font.BOLD, 25));
		lblTituloDatos.setBounds(0, 28, 592, 28);
		
		lblLugar = new JLabel("Lloc");
		lblLugar.setFont(new Font("Dialog", Font.BOLD, 15));
		lblLugar.setBounds(83, 102, 79, 17);

		//Lugar
		txtLugar = new JTextField();
		txtLugar.setBackground(new Color(0, 128, 192));
		txtLugar.setForeground(new Color(238, 238, 236));
		txtLugar.setFont(new Font("Dialog", Font.BOLD, 14));
		txtLugar.setBounds(165, 102, 119, 20);
		txtLugar.setColumns(10);
		
		lblFecha = new JLabel("Data");
		lblFecha.setFont(new Font("Dialog", Font.BOLD, 15));
		lblFecha.setBounds(310, 147, 79, 17);
		
		//Fecha
		dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.setBounds(389, 147, 119, 20);
		
		lblDuracion = new JLabel("Duració");
		lblDuracion.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDuracion.setBounds(83, 150, 79, 17);
		
		//Duracion
		txtDuracion = new JTextField();
		txtDuracion.setBackground(new Color(0, 128, 192));
		txtDuracion.setForeground(new Color(238, 238, 236));
		txtDuracion.setFont(new Font("Dialog", Font.BOLD, 14));
		txtDuracion.setColumns(10);
		txtDuracion.setBounds(165, 147, 119, 20);
		
		lblFormat = new JLabel("Format");
		lblFormat.setFont(new Font("Dialog", Font.BOLD, 15));
		lblFormat.setBounds(310, 100, 79, 17);
		
		//Formato
		cmbFormato = new JComboBox<Object>();
		cmbFormato.setBackground(new Color(0, 128, 192));
		cmbFormato.setForeground(new Color(238, 238, 236));
		cmbFormato.setFont(new Font("Dialog", Font.BOLD, 14));
		cmbFormato.setModel(new DefaultComboBoxModel<Object>(new String[] {"Digital", "Físic"}));
		cmbFormato.setBounds(389, 100, 119, 22);
		
		//Volver
		btnVolver = new JButton("Tornar");
		btnVolver.setForeground(new Color(238, 238, 236));
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVolver.setBackground(new Color(0, 128, 192));
		btnVolver.setBounds(12, 257, 274, 28);
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);

		//Alta
		btnAceptar = new JButton("Acceptar");
		btnAceptar.setBounds(312, 257, 268, 28);
		btnAceptar.setFocusPainted(false);
		btnAceptar.setBorderPainted(false);
		btnAceptar.setForeground(new Color(238, 238, 236));
		btnAceptar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnAceptar.setBackground(new Color(0, 128, 192));
		
		panelSecundario.add(lblTituloDatos);
		panelSecundario.add(lblLugar);
		panelSecundario.add(txtLugar);
		panelSecundario.add(lblFecha);
        panelSecundario.add(dateChooser);
		panelSecundario.add(lblDuracion);
		panelSecundario.add(txtDuracion);
		panelSecundario.add(lblFormat);
		panelSecundario.add(cmbFormato);
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
			}
		});
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtLugar.getText().isEmpty() | txtDuracion.getText().isEmpty()) {
					JOptionPane.showMessageDialog(panelSecundario, "Si us plau, introdueix tots els camps.", "Alerta", JOptionPane.WARNING_MESSAGE);
				} else {
					lugar = txtLugar.getText();
					fecha = dateChooser.getDate();
					duracion = Integer.parseInt(txtDuracion.getText());
					formato = (String) cmbFormato.getSelectedItem();

					Musica musica = new Musica(documento.getISBN(), lugar, fecha, duracion, formato);
					DocumentoMaxDB docDB = new DocumentoMaxDB();
			        
					try {
						if (docDB.updateDocMus(documento, musica)) {
							JOptionPane.showMessageDialog(panelSecundario, "Actualització exitosa.", "Música", JOptionPane.INFORMATION_MESSAGE);
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
						txtLugar.setText("");
						dateChooser.setDate(null);
						txtDuracion.setText("");
			        } catch (NullPointerException ex) {
			        	JOptionPane.showMessageDialog(panelSecundario, "La data no té sentit.", "Data incorrecta", JOptionPane.ERROR_MESSAGE);
			        	dateChooser.setDate(null);
			        }// try catch
				}// if else
			}// actionPerformed
		});
	}
}// VentanaModificarMusica
