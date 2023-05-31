package gestor;

import db.DocumentoMaxDB;
import app.Documento;
import app.Musica;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

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
		setBounds(100, 100, 478, 329);
		contentPane = new JPanel();
		contentPane.setForeground(UIManager.getColor("Panel.foreground"));
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.window);
		panel.setForeground(new Color(0, 0, 0));
		panel.setBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0, 0), 3)));
		panel.setBounds(53, 14, 359, 44);
		contentPane.add(panel);
		
		JLabel lblAlta = new JLabel("ALTA MÚSCIA");
		lblAlta.setForeground(new Color(0, 0, 0));
		lblAlta.setFont(new Font("Century Schoolbook L", Font.BOLD | Font.ITALIC, 20));
		panel.add(lblAlta);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(new Color(238, 238, 236));
		panel_1.setBackground(SystemColor.window);
		panel_1.setBorder(new TitledBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0, 0), 3, true)), "Bienvenido", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(53, 70, 360, 208);
		contentPane.add(panel_1);
				
		JLabel lblIntroduceDatos = new JLabel("Introduce los datos");
		lblIntroduceDatos.setForeground(new Color(0, 0, 0));
		lblIntroduceDatos.setFont(new Font("Century Schoolbook L", Font.BOLD | Font.ITALIC, 20));
		lblIntroduceDatos.setBounds(66, 28, 212, 28);
		panel_1.add(lblIntroduceDatos);
		
		
		JLabel lblLugar = new JLabel("Lugar");
		lblLugar.setFont(new Font("Dialog", Font.BOLD, 15));
		lblLugar.setBounds(12, 67, 79, 17);
		panel_1.add(lblLugar);

		//Lugar
		textFieldLugar = new JTextField();
		textFieldLugar.setBounds(87, 67, 86, 20);
		panel_1.add(textFieldLugar);
		textFieldLugar.setColumns(10);
		
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setFont(new Font("Dialog", Font.BOLD, 15));
		lblFecha.setBounds(185, 67, 79, 17);
		panel_1.add(lblFecha);
		
		//Fecha
		dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");
        dateChooser.setBounds(250, 67, 86, 20);
        panel_1.add(dateChooser);
		
		
		JLabel lblDuracion = new JLabel("Duración");
		lblDuracion.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDuracion.setBounds(12, 98, 79, 17);
		panel_1.add(lblDuracion);
		
		//Duracion
		textFieldDuracion = new JTextField();
		textFieldDuracion.setColumns(10);
		textFieldDuracion.setBounds(87, 95, 86, 20);
		panel_1.add(textFieldDuracion);
		
		
		JLabel lblIsbn_3 = new JLabel("Formato");
		lblIsbn_3.setFont(new Font("Dialog", Font.BOLD, 15));
		lblIsbn_3.setBounds(185, 98, 79, 17);
		panel_1.add(lblIsbn_3);
		
		//Formato
		JComboBox<Object> formatoBox = new JComboBox<Object>();
		formatoBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"Digital", "Fisico"}));
		formatoBox.setBounds(250, 98, 86, 22);
		panel_1.add(formatoBox);
		
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
		panel_1.add(btnVolver);
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);

		
		//Alta
		JButton btnNewButton = new JButton("Alta");
		btnNewButton.setBounds(269, 168, 79, 28);
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
					} else {
						JOptionPane.showMessageDialog(panel_1, "Error al introducir datos en la DB", "Error", JOptionPane.ERROR_MESSAGE);
					}
					textFieldLugar.setText("");
					dateChooser.setDate(null);
					textFieldDuracion.setText("");
		        } catch (NullPointerException ex) {
		        	JOptionPane.showMessageDialog(panel_1, "La fecha no tiene sentido", "Fecha incorrecta", JOptionPane.ERROR_MESSAGE);
		        	dateChooser.setDate(null);
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
					VentanaAltaMusica frame = new VentanaAltaMusica();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
