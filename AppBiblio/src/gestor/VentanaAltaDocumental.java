package gestor;

import db.DocumentoMaxDB;
import app.Documental;
import app.Documento;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAltaDocumental extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String productora, premios, documentalesRealcionados, formato;
	private int duracion;
	private JTextField textFieldProductora, textFieldPremios, textFieldDocRelacionados, textFieldDuracion;
	static Documento documento;

	public VentanaAltaDocumental() {
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
		
		JLabel lblAlta = new JLabel("ALTA DOCUMENTALES");
		lblAlta.setForeground(new Color(0, 0, 0));
		lblAlta.setFont(new Font("Dialog", Font.BOLD, 20));
		panel.add(lblAlta);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(new Color(238, 238, 236));
		panel_1.setBackground(SystemColor.window);
		panel_1.setBorder(new TitledBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0, 0), 3, true)), "Bienvenido", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(53, 70, 360, 208);
		contentPane.add(panel_1);
				
		JLabel lblIntroducDatos = new JLabel("Introduce los datos");
		lblIntroducDatos.setForeground(new Color(0, 0, 0));
		lblIntroducDatos.setFont(new Font("Dialog", Font.BOLD, 20));
		lblIntroducDatos.setBounds(89, 11, 189, 45);
		panel_1.add(lblIntroducDatos);
		
		
		JLabel lblProductora = new JLabel("Productora");
		lblProductora.setFont(new Font("Dialog", Font.BOLD, 15));
		lblProductora.setBounds(12, 67, 79, 17);
		panel_1.add(lblProductora);
		
		//Proudctora
		textFieldProductora = new JTextField();
		textFieldProductora.setBounds(96, 67, 79, 20);
		panel_1.add(textFieldProductora);
		textFieldProductora.setColumns(10);
		
		
		JLabel lblPremios = new JLabel("Premios");
		lblPremios.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPremios.setBounds(12, 96, 79, 17);
		panel_1.add(lblPremios);
		
		//Premios
		textFieldPremios = new JTextField();
		textFieldPremios.setColumns(10);
		textFieldPremios.setBounds(96, 96, 79, 20);
		panel_1.add(textFieldPremios);
		
		JLabel lblDocumentalesRealcionados = new JLabel("Documentales realcionados");
		lblDocumentalesRealcionados.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDocumentalesRealcionados.setBounds(12, 126, 206, 17);
		panel_1.add(lblDocumentalesRealcionados);
		
		textFieldDocRelacionados = new JTextField();
		textFieldDocRelacionados.setColumns(10);
		textFieldDocRelacionados.setBounds(228, 126, 120, 20);
		panel_1.add(textFieldDocRelacionados);
		
		JLabel lblDuracion = new JLabel("Duración");
		lblDuracion.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDuracion.setBounds(199, 67, 79, 17);
		panel_1.add(lblDuracion);
		
		//Duracion
		textFieldDuracion = new JTextField();
		textFieldDuracion.setColumns(10);
		textFieldDuracion.setBounds(276, 67, 74, 20);
		panel_1.add(textFieldDuracion);
		
		JLabel lblFormato = new JLabel("Formato");
		lblFormato.setFont(new Font("Dialog", Font.BOLD, 15));
		lblFormato.setBounds(199, 96, 79, 17);
		panel_1.add(lblFormato);
		
		//Formato
		JComboBox<Object> formatoBox = new JComboBox<Object>();
		formatoBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"Físico", "Digital"}));
		formatoBox.setBounds(269, 95, 79, 22);
		panel_1.add(formatoBox);
		
		//Volver
		JButton btnVolver = new JButton("Volver");
		btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		btnVolver.setBackground(new Color(255, 255, 255));
		btnVolver.setBounds(12, 174, 168, 23);
		btnVolver.setBorder(new BorderRedondo(20)); 
		panel_1.add(btnVolver);
		
		//Aceptar
		JButton btnNewButton = new JButton("Alta");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBounds(190, 173, 158, 24);
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				productora = textFieldProductora.getText().toString();
				premios = textFieldPremios.getText().toString();
				documentalesRealcionados = textFieldDocRelacionados.getText().toString();
				duracion = Integer.parseInt(textFieldDuracion.getText());
				formato = formatoBox.getSelectedItem().toString();
				
				Documental documental = new Documental(documento.getISBN(), productora, premios, documentalesRealcionados, duracion, formato);
				
				DocumentoMaxDB docDB = new DocumentoMaxDB();
				if (docDB.insertDocDocl(documento, documental)) {
					JOptionPane.showMessageDialog(panel_1, "Registro exitoso", "Libro", JOptionPane.INFORMATION_MESSAGE);
					MenuGestor menu = new MenuGestor();
					menu.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(panel_1, "Error al introducir datos en la DB", "Error", JOptionPane.ERROR_MESSAGE);
				}//if else
			}//actionPerformed
		});
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 12));
		btnNewButton.setBackground(UIManager.getColor("Button.darkShadow"));
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAltaDocumental frame = new VentanaAltaDocumental();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
