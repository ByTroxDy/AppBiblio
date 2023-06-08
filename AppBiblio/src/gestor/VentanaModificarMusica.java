package gestor;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import app.Documento;
import app.Musica;
import db.DocumentoMaxDB;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class VentanaModificarMusica extends JFrame {
	private static final long serialVersionUID = 1L;
	static Documento documento;

	private JPanel contentPane;
	private JLabel lblAlta, lblImagen, lblTituloDatos, lblLugar, lblFecha, lblDuracion, lblFormat;
	private JTextField textFieldLloc, textFieldDuracio;
	private JButton btnVolver, btnAceptar;
	private JComboBox<Object> formatBox;
	private JDateChooser dateChooser;

	private String lugar, formato;
	private Date fecha;
	private int duracion;
	
	
	public VentanaModificarMusica() {
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
		lblImagen.setIcon(new ImageIcon(VentanaModificarMusica.class.getResource("/img/icono64.png")));
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
		
		lblLugar = new JLabel("Llloc");
		lblLugar.setFont(new Font("Dialog", Font.BOLD, 15));
		lblLugar.setBounds(83, 102, 79, 17);

		//Lugar
		textFieldLloc = new JTextField();
		textFieldLloc.setBackground(new Color(0, 128, 192));
		textFieldLloc.setForeground(new Color(238, 238, 236));
		textFieldLloc.setFont(new Font("Dialog", Font.BOLD, 14));
		textFieldLloc.setBounds(165, 102, 119, 20);
		textFieldLloc.setColumns(10);
		
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
		textFieldDuracio = new JTextField();
		textFieldDuracio.setBackground(new Color(0, 128, 192));
		textFieldDuracio.setForeground(new Color(238, 238, 236));
		textFieldDuracio.setFont(new Font("Dialog", Font.BOLD, 14));
		textFieldDuracio.setColumns(10);
		textFieldDuracio.setBounds(165, 147, 119, 20);
		
		lblFormat = new JLabel("Format");
		lblFormat.setFont(new Font("Dialog", Font.BOLD, 15));
		lblFormat.setBounds(310, 100, 79, 17);
		
		//Formato
		formatBox = new JComboBox<Object>();
		formatBox.setBackground(new Color(0, 128, 192));
		formatBox.setForeground(new Color(238, 238, 236));
		formatBox.setFont(new Font("Dialog", Font.BOLD, 14));
		formatBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"Digital", "Físic"}));
		formatBox.setBounds(389, 100, 119, 22);
		
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
		panelSecundario.add(textFieldLloc);
		panelSecundario.add(lblFecha);
        panelSecundario.add(dateChooser);
		panelSecundario.add(lblDuracion);
		panelSecundario.add(textFieldDuracio);
		panelSecundario.add(lblFormat);
		panelSecundario.add(formatBox);
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
				if (textFieldLloc.getText().isEmpty() | textFieldDuracio.getText().isEmpty()) {
					JOptionPane.showMessageDialog(panelSecundario, "Introduce todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					lugar = textFieldLloc.getText().toString();
					fecha = dateChooser.getDate();
					duracion = Integer.parseInt(textFieldDuracio.getText());
					formato = formatBox.getSelectedItem().toString();

					Musica musica = new Musica(documento.getISBN(), lugar, fecha, duracion, formato);
					DocumentoMaxDB docDB = new DocumentoMaxDB();
			        
					try {
						if (docDB.updateDocMus(documento, musica)) {
							JOptionPane.showMessageDialog(panelSecundario, "Actualización exitoso", "Música", JOptionPane.INFORMATION_MESSAGE);
							MenuGestor menu = new MenuGestor();
							menu.setVisible(true);
							dispose();
						} else {
							JOptionPane.showMessageDialog(panelSecundario, "Error al actualizar datos en la DB", "Error", JOptionPane.ERROR_MESSAGE);
						}//if else
						textFieldLloc.setText("");
						dateChooser.setDate(null);
						textFieldDuracio.setText("");
			        } catch (NullPointerException ex) {
			        	JOptionPane.showMessageDialog(panelSecundario, "La fecha no tiene sentido", "Fecha incorrecta", JOptionPane.ERROR_MESSAGE);
			        	dateChooser.setDate(null);
			        }// try catch
				}// if else
			}// actionPerformed
		});
	}// VentanaModificarMusica

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaModificarMusica frame = new VentanaModificarMusica();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}// try catch
			}// run
		});
	}// main
}
