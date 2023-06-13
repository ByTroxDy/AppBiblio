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
public class VentanaAltaMusica extends JFrame {
	private JPanel contentPane;
	private JLabel lblAlta, lblImagen, lblTituloDatos, lblLugar, lblFecha, lblDuracion, lblFormato;
	private JTextField txtLugar, txtDuracion;
	private JButton btnVolver, btnAceptar;
	private JComboBox<Object> cmbformato;
	private JDateChooser dateChooser;

	private String lugar, formato;
	private Date fecha;
	private int duracion;
	static Documento documento;
	public static String grupo;

	public VentanaAltaMusica() {
		setTitle("Biblioteca App");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaAltaMusica.class.getResource("/img/icono32.png")));
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
		panelPrincipal.setBounds(0, 0, 592, 71);
		panelPrincipal.setLayout(null);
		
		lblAlta = new JLabel("ALTA MÚSCIA");
		lblAlta.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlta.setForeground(new Color(255, 255, 255));
		lblAlta.setFont(new Font("Dialog", Font.BOLD, 40));
		lblAlta.setBounds(145, 11, 293, 49);

		lblImagen = new JLabel("");
		lblImagen.setIcon(new ImageIcon(MenuGestor.class.getResource("/img/icono64.png")));
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagen.setBounds(441, 0, 74, 71);

		panelPrincipal.add(lblAlta);
		panelPrincipal.add(lblImagen);
		contentPane.add(panelPrincipal);

		JPanel panelSecundario = new JPanel();
		panelSecundario.setLayout(null);
		panelSecundario.setForeground(new Color(238, 238, 236));
		panelSecundario.setBackground(SystemColor.window);
		panelSecundario.setBorder(null);
		panelSecundario.setBounds(0, 70, 592, 297);
				
		lblTituloDatos = new JLabel("Introdueix les dades");
		lblTituloDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloDatos.setForeground(new Color(0, 0, 0));
		lblTituloDatos.setFont(new Font("Dialog", Font.BOLD, 25));
		lblTituloDatos.setBounds(0, 28, 584, 28);

		lblLugar = new JLabel("Lloc");
		lblLugar.setFont(new Font("Dialog", Font.BOLD, 14));
		lblLugar.setBounds(129, 75, 79, 17);

		//Lugar
		txtLugar = new JTextField();
		txtLugar.setFont(new Font("Dialog", Font.BOLD, 14));
		txtLugar.setForeground(new Color(255, 255, 255));
		txtLugar.setBackground(new Color(0, 128, 192));
		txtLugar.setBounds(197, 75, 252, 20);
		txtLugar.setColumns(10);

		lblFecha = new JLabel("Data");
		lblFecha.setFont(new Font("Dialog", Font.BOLD, 14));
		lblFecha.setBounds(129, 198, 79, 17);
		
		//Fecha
		dateChooser = new JDateChooser();
		dateChooser.setBackground(new Color(0, 128, 192));
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.setBounds(197, 195, 252, 20);

		lblDuracion = new JLabel("Duració");
		lblDuracion.setFont(new Font("Dialog", Font.BOLD, 14));
		lblDuracion.setBounds(129, 161, 79, 17);
		
		//Duracion
		txtDuracion = new JTextField();
		txtDuracion.setFont(new Font("Dialog", Font.BOLD, 14));
		txtDuracion.setForeground(new Color(255, 255, 255));
		txtDuracion.setBackground(new Color(0, 128, 192));
		txtDuracion.setColumns(10);
		txtDuracion.setBounds(197, 158, 252, 20);

		lblFormato = new JLabel("Format");
		lblFormato.setFont(new Font("Dialog", Font.BOLD, 14));
		lblFormato.setBounds(129, 118, 79, 17);
		
		//Formato
		cmbformato = new JComboBox<Object>();
		cmbformato.setFont(new Font("Dialog", Font.BOLD, 14));
		cmbformato.setForeground(new Color(255, 255, 255));
		cmbformato.setBackground(new Color(0, 128, 192));
		cmbformato.setModel(new DefaultComboBoxModel<Object>(new String[] {"Digital", "Fisico"}));
		cmbformato.setBounds(197, 113, 252, 22);
		
		//Volver
		btnVolver = new JButton("Tornar");
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVolver.setBackground(new Color(0, 128, 192));
		btnVolver.setBounds(10, 257, 292, 28);
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);

		//Alta
		btnAceptar = new JButton("Acceptar");
		btnAceptar.setBounds(314, 257, 270, 28);
		btnAceptar.setFocusPainted(false);
		btnAceptar.setBorderPainted(false);
		btnAceptar.setForeground(new Color(255, 255, 255));
		btnAceptar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnAceptar.setBackground(new Color(0, 128, 192));
		
		panelSecundario.add(lblTituloDatos);
		panelSecundario.add(lblLugar);
		panelSecundario.add(txtLugar);
		panelSecundario.add(lblFecha);
        panelSecundario.add(dateChooser);
		panelSecundario.add(lblDuracion);
		panelSecundario.add(txtDuracion);
		panelSecundario.add(lblFormato);
		panelSecundario.add(cmbformato);
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
				if (txtLugar.getText().isEmpty() | txtDuracion.getText().isEmpty()) {
					JOptionPane.showMessageDialog(panelSecundario, "Si us plau, introdueix tots els camps.", "Alerta", JOptionPane.WARNING_MESSAGE);
				} else {
					lugar = txtLugar.getText();
					fecha = dateChooser.getDate();
					duracion = Integer.parseInt(txtDuracion.getText());
					formato = (String) cmbformato.getSelectedItem();

					Musica musica = new Musica(documento.getISBN(), lugar, fecha, duracion, formato);
					DocumentoMaxDB docDB = new DocumentoMaxDB();
			        
					try {
						if (docDB.insertDocMus(documento, musica)) {
							JOptionPane.showMessageDialog(panelSecundario, "Registre exitós.", "Música", JOptionPane.INFORMATION_MESSAGE);
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
}// VentanaAltaMusica
