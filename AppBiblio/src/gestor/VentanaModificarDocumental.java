package gestor;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import app.Documental;
import app.Documento;
import db.DocumentoMaxDB;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class VentanaModificarDocumental extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldProductora, textFieldPremios, textFieldDocRelacionados, textFieldDuracion;
	private String productora, premios, documentalesRealcionados, formato;
	private int duracion;
	static Documento documento;
	
	public VentanaModificarDocumental() {
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
		panel.setBackground(SystemColor.window);
		panel.setLayout(null);
		panel.setBackground(new Color(0, 128, 192));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 592, 73);
		contentPane.add(panel);
		
		JLabel lblAlta = new JLabel("MODIFICAR DOCUMENTAL");
		lblAlta.setBackground(new Color(238, 238, 236));
		lblAlta.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlta.setBounds(30, 0, 488, 73);
		lblAlta.setForeground(new Color(238, 238, 236));
		lblAlta.setFont(new Font("Dialog", Font.BOLD, 35));
		panel.add(lblAlta);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("/img/icono32.png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(506, 0, 60, 73);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(new Color(238, 238, 236));
		panel_1.setBackground(SystemColor.window);
		panel_1.setBounds(0, 70, 592, 297);
		contentPane.add(panel_1);
				
		JLabel lblIntroducDatos = new JLabel("Introdueix les noves dades");
		lblIntroducDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblIntroducDatos.setForeground(new Color(0, 0, 0));
		lblIntroducDatos.setFont(new Font("Dialog", Font.BOLD, 25));
		lblIntroducDatos.setBounds(0, 25, 592, 31);
		panel_1.add(lblIntroducDatos);

		JLabel lblProductora = new JLabel("Productora");
		lblProductora.setFont(new Font("Dialog", Font.BOLD, 15));
		lblProductora.setBounds(32, 90, 92, 17);
		panel_1.add(lblProductora);
		
		//Proudctora
		textFieldProductora = new JTextField();
		textFieldProductora.setFont(new Font("Dialog", Font.BOLD, 14));
		textFieldProductora.setBackground(new Color(0, 128, 192));
		textFieldProductora.setBounds(126, 90, 149, 20);
		panel_1.add(textFieldProductora);
		textFieldProductora.setColumns(10);

		JLabel lblPremios = new JLabel("Premios");
		lblPremios.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPremios.setBounds(32, 132, 79, 17);
		panel_1.add(lblPremios);
		
		//Premios
		textFieldPremios = new JTextField();
		textFieldPremios.setFont(new Font("Dialog", Font.BOLD, 14));
		textFieldPremios.setBackground(new Color(0, 128, 192));
		textFieldPremios.setColumns(10);
		textFieldPremios.setBounds(126, 132, 149, 20);
		panel_1.add(textFieldPremios);
		
		JLabel lblDocumentalesRealcionados = new JLabel("Documentales realcionados");
		lblDocumentalesRealcionados.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDocumentalesRealcionados.setBounds(89, 175, 229, 17);
		panel_1.add(lblDocumentalesRealcionados);
		
		textFieldDocRelacionados = new JTextField();
		textFieldDocRelacionados.setFont(new Font("Dialog", Font.BOLD, 14));
		textFieldDocRelacionados.setBackground(new Color(0, 128, 192));
		textFieldDocRelacionados.setColumns(10);
		textFieldDocRelacionados.setBounds(308, 174, 149, 20);
		panel_1.add(textFieldDocRelacionados);
		
		JLabel lblDuracion = new JLabel("Duración");
		lblDuracion.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDuracion.setBounds(319, 90, 79, 17);
		panel_1.add(lblDuracion);
		
		//Duracion
		textFieldDuracion = new JTextField();
		textFieldDuracion.setFont(new Font("Dialog", Font.BOLD, 14));
		textFieldDuracion.setBackground(new Color(0, 128, 192));
		textFieldDuracion.setColumns(10);
		textFieldDuracion.setBounds(396, 90, 149, 20);
		panel_1.add(textFieldDuracion);
		
		JLabel lblFormato = new JLabel("Formato");
		lblFormato.setFont(new Font("Dialog", Font.BOLD, 15));
		lblFormato.setBounds(319, 132, 79, 17);
		panel_1.add(lblFormato);
		
		//Formato
		JComboBox<Object> formatoBox = new JComboBox<Object>();
		formatoBox.setForeground(new Color(238, 238, 236));
		formatoBox.setFont(new Font("Dialog", Font.BOLD, 14));
		formatoBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"Físico", "Digital"}));
		formatoBox.setBounds(396, 130, 149, 22);
		formatoBox.setBackground(new Color(0, 128, 192));
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
		btnVolver.setForeground(new Color(238, 238, 236));
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVolver.setBackground(new Color(0, 128, 192));
		btnVolver.setBounds(7, 270, 284, 23);
		panel_1.add(btnVolver);
		
		//Aceptar
		JButton btnNewButton = new JButton("Modificar");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setBounds(308, 270, 279, 24);
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldProductora.getText().isEmpty() | textFieldPremios.getText().isEmpty() | textFieldDocRelacionados.getText().isEmpty()
						| textFieldDuracion.getText().isEmpty()) {
					JOptionPane.showMessageDialog(panel_1, "Introduce todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					productora = textFieldProductora.getText().toString();
					premios = textFieldPremios.getText().toString();
					documentalesRealcionados = textFieldDocRelacionados.getText().toString();
					duracion = Integer.parseInt(textFieldDuracion.getText());
					formato = formatoBox.getSelectedItem().toString();
					
					Documental documental = new Documental(documento.getISBN(), productora, premios, documentalesRealcionados, duracion, formato);
					DocumentoMaxDB docDB = new DocumentoMaxDB();
					
					if (docDB.updateDocDocl(documento, documental)) {
						JOptionPane.showMessageDialog(panel_1, "Actualización exitoso", "Documental", JOptionPane.INFORMATION_MESSAGE);
						MenuGestor menu = new MenuGestor();
						menu.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(panel_1, "Error al actualizar datos en la DB", "Error", JOptionPane.ERROR_MESSAGE);
					}//if else
				}// if else
			}//actionPerformed
		});
		btnNewButton.setForeground(new Color(238, 238, 236));
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton.setBackground(new Color(0, 128, 192));
	}// VentanaModificarDocumental
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaModificarDocumental frame = new VentanaModificarDocumental();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}// try catch
			}// run
		});
	}// main
}// VentanaModificarDocumental
