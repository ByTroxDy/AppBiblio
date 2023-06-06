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

public class VentanaAltaMusica extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String lugar, formato;
	private Date fecha;
	private int duracion;
	private JTextField textFieldLugar, textFieldDuracion;
	private JDateChooser dateChooser;

	static Documento documento;

	public VentanaAltaMusica() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setForeground(UIManager.getColor("Panel.foreground"));
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 192));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBorder(null);
		panel.setBounds(0, 0, 592, 71);
		panel.setLayout(null);
		contentPane.add(panel);
		
		JLabel lblAlta = new JLabel("ALTA MÚSCIA");
		lblAlta.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlta.setForeground(new Color(255, 255, 255));
		lblAlta.setFont(new Font("Dialog", Font.BOLD, 40));
		lblAlta.setBounds(145, 11, 293, 49);
		panel.add(lblAlta);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("/img/icono32.png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(441, 0, 74, 71);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(new Color(238, 238, 236));
		panel_1.setBackground(SystemColor.window);
		panel_1.setBorder(null);
		panel_1.setBounds(0, 70, 592, 297);
		contentPane.add(panel_1);
				
		JLabel lblIntroduceDatos = new JLabel("Introdueix les dades");
		lblIntroduceDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroduceDatos.setForeground(new Color(0, 0, 0));
		lblIntroduceDatos.setFont(new Font("Dialog", Font.BOLD, 25));
		lblIntroduceDatos.setBounds(0, 28, 584, 28);
		panel_1.add(lblIntroduceDatos);

		JLabel lblLugar = new JLabel("Lloc");
		lblLugar.setFont(new Font("Dialog", Font.BOLD, 14));
		lblLugar.setBounds(129, 75, 79, 17);
		panel_1.add(lblLugar);

		//Lugar
		textFieldLugar = new JTextField();
		textFieldLugar.setFont(new Font("Dialog", Font.BOLD, 14));
		textFieldLugar.setForeground(new Color(255, 255, 255));
		textFieldLugar.setBackground(new Color(0, 128, 192));
		textFieldLugar.setBounds(197, 75, 252, 20);
		panel_1.add(textFieldLugar);
		textFieldLugar.setColumns(10);

		JLabel lblFecha = new JLabel("Data");
		lblFecha.setFont(new Font("Dialog", Font.BOLD, 14));
		lblFecha.setBounds(129, 198, 79, 17);
		panel_1.add(lblFecha);
		
		//Fecha
		dateChooser = new JDateChooser();
		dateChooser.setBackground(new Color(0, 128, 192));
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.setBounds(197, 195, 252, 20);
        panel_1.add(dateChooser);

		JLabel lblDuracion = new JLabel("Duració");
		lblDuracion.setFont(new Font("Dialog", Font.BOLD, 14));
		lblDuracion.setBounds(129, 161, 79, 17);
		panel_1.add(lblDuracion);
		
		//Duracion
		textFieldDuracion = new JTextField();
		textFieldDuracion.setFont(new Font("Dialog", Font.BOLD, 14));
		textFieldDuracion.setForeground(new Color(255, 255, 255));
		textFieldDuracion.setBackground(new Color(0, 128, 192));
		textFieldDuracion.setColumns(10);
		textFieldDuracion.setBounds(197, 158, 252, 20);
		panel_1.add(textFieldDuracion);

		JLabel lblIsbn_3 = new JLabel("Format");
		lblIsbn_3.setFont(new Font("Dialog", Font.BOLD, 14));
		lblIsbn_3.setBounds(129, 118, 79, 17);
		panel_1.add(lblIsbn_3);
		
		//Formato
		JComboBox<Object> formatoBox = new JComboBox<Object>();
		formatoBox.setFont(new Font("Dialog", Font.BOLD, 14));
		formatoBox.setForeground(new Color(255, 255, 255));
		formatoBox.setBackground(new Color(0, 128, 192));
		formatoBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"Digital", "Fisico"}));
		formatoBox.setBounds(197, 113, 252, 22);
		panel_1.add(formatoBox);
		
		//Volver
		JButton btnVolver = new JButton("Tornar");
		btnVolver.addActionListener(new ActionListener() {
			//función para cambiar de ventana haciendo click en el boton
			public void actionPerformed(ActionEvent e) {    
				MenuGestor frame = new MenuGestor();
				frame.setVisible(true);
				dispose();
			}
		});
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVolver.setBackground(new Color(0, 128, 192));
		btnVolver.setBounds(10, 257, 292, 28);
		panel_1.add(btnVolver);
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);

		//Alta
		JButton btnNewButton = new JButton("Acceptar");
		btnNewButton.setBounds(314, 257, 270, 28);
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lugar = textFieldLugar.getText().toString();
				fecha = dateChooser.getDate();
				duracion = Integer.parseInt(textFieldDuracion.getText());
				formato = formatoBox.getSelectedItem().toString();

				Musica musica = new Musica(documento.getISBN(), lugar, fecha, duracion, formato);
				DocumentoMaxDB docDB = new DocumentoMaxDB();
		        
				try {
					if (docDB.insertDocMus(documento, musica)) {
						JOptionPane.showMessageDialog(panel_1, "Registro exitoso", "Música", JOptionPane.INFORMATION_MESSAGE);
						MenuGestor menu = new MenuGestor();
						menu.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(panel_1, "Error al introducir datos en la DB", "Error", JOptionPane.ERROR_MESSAGE);
					}
					textFieldLugar.setText("");
					dateChooser.setDate(null);
					textFieldDuracion.setText("");
		        } catch (NullPointerException ex) {
		        	JOptionPane.showMessageDialog(panel_1, "La fecha no tiene sentido", "Fecha incorrecta", JOptionPane.ERROR_MESSAGE);
		        	dateChooser.setDate(null);
		        }// try catch
			}// actionPerformed
		});
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton.setBackground(new Color(0, 128, 192));
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAltaMusica frame = new VentanaAltaMusica();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}// try catch
			}// run
		});
	}// main
}
