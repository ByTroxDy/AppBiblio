package gestor;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.Documento;
import db.DocumentoDB;

public class VentanaAltaDocumento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private int isbn;
	private String titulo;
	private String autor;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAltaDocumento frame = new VentanaAltaDocumento();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaAltaDocumento() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 323);
		contentPane = new JPanel();
		contentPane.setForeground(UIManager.getColor("Panel.foreground"));
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		//Creación de panel Titulo
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.window);
		panel.setForeground(new Color(0, 0, 0));
		panel.setBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0, 0), 3)));
		panel.setBounds(53, 14, 359, 44);
		contentPane.add(panel);
		
		//Titulo del panel creado
		JLabel lblAlta = new JLabel("ALTA DOCUMENTO");
		lblAlta.setForeground(new Color(0, 0, 0));
		lblAlta.setFont(new Font("Dialog", Font.BOLD, 20));
		panel.add(lblAlta);
		
		//Creación de panel de contenido
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(new Color(238, 238, 236));
		panel_1.setBackground(SystemColor.window);
		panel_1.setBorder(new TitledBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0, 0), 3, true)), "Bienvenido", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(53, 70, 360, 208);
		contentPane.add(panel_1);
				
		//Labels y textField para la introducción de datos
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(131, 36, 60, 17);
		panel_1.add(lblTitulo);
		
		JLabel lblIsbn = new JLabel("isbn");
		lblIsbn.setBounds(53, 36, 60, 17);
		panel_1.add(lblIsbn);
		
		
		JLabel lblAutor = new JLabel("autor");
		lblAutor.setBounds(240, 36, 60, 17);
		panel_1.add(lblAutor);
		
		//TITULO
		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 10));
		textField.setColumns(10);
		textField.setBounds(122, 65, 69, 21);
		panel_1.add(textField);
		
		//isbn
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Dialog", Font.PLAIN, 10));
		textField_1.setColumns(10);
		textField_1.setBounds(31, 65, 69, 21);
		panel_1.add(textField_1);

		//AUTOR
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Dialog", Font.PLAIN, 10));
		textField_2.setColumns(10);
		textField_2.setBounds(220, 65, 69, 21);
		panel_1.add(textField_2);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			//función para cambiar de ventana haciendo click en el boton
			public void actionPerformed(ActionEvent e) {    
				VentanaGestor frame = new VentanaGestor();
				frame.setVisible(true);
				dispose();
			}
		});
		
		btnVolver.setForeground(Color.BLACK);
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 12));
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);
		btnVolver.setBackground(UIManager.getColor("Button.darkShadow"));
		btnVolver.setBounds(12, 167, 79, 28);
		panel_1.add(btnVolver);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setForeground(Color.BLACK);
		btnAceptar.setFont(new Font("Dialog", Font.BOLD, 12));
		btnAceptar.setFocusPainted(false);
		btnAceptar.setBorderPainted(false);
		btnAceptar.setBackground(UIManager.getColor("Button.darkShadow"));
		btnAceptar.setBounds(261, 167, 87, 28);
		btnAceptar.addActionListener(new ActionListener() {
			//función para crear objeto Documento y llamada a función inserar
			public void actionPerformed(ActionEvent e) {  
				titulo = textField.getText();
				isbn = Integer.parseInt(textField_1.getText());
				autor = textField_2.getText();
					
				Documento documento = new Documento(isbn, titulo, autor);
				DocumentoDB docDB = new DocumentoDB();
				docDB.insertarDocumento(documento);
				
			}
		});		panel_1.add(btnAceptar);
		
	}
}
