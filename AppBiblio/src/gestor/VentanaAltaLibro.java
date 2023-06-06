package gestor;

import db.DocumentoMaxDB;
import app.Documento;
import app.Libro;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaAltaLibro extends JFrame {
	private static final long serialVersionUID = 1L;
	static Documento documento;

	private JPanel contentPane;
	private JLabel lblAlta, lblImagen, lblTituloIsbn, lblEditorial, lblPginas, lblTemtica;
	private JTextField textEditorial, textPaginas;
	private JButton btnVolver, btnAceptar;
	
	private String editorial, tematica;
	private int numpaginas;
	
	public VentanaAltaLibro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setForeground(UIManager.getColor("Panel.foreground"));
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		//panel Principal
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(0, 128, 192));
		panelPrincipal.setForeground(new Color(0, 0, 0));
		panelPrincipal.setBorder(null);
		panelPrincipal.setBounds(0, 0, 592, 72);
		panelPrincipal.setLayout(null);
		contentPane.add(panelPrincipal);
		
		// Titulo de panel Principal
		lblAlta = new JLabel("ALTA LLIBRE");
		lblAlta.setForeground(new Color(255, 255, 255));
		lblAlta.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlta.setFont(new Font("Dialog", Font.BOLD, 40));
		lblAlta.setBounds(135, 11, 272, 50);
		panelPrincipal.add(lblAlta);
		
		// Imagen del panel principal
		lblImagen = new JLabel("");
		lblImagen.setIcon(new ImageIcon("img/icono64.png"));
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagen.setBounds(415, 0, 62, 72);
		panelPrincipal.add(lblImagen);

		JPanel panelSecundario = new JPanel();
		panelSecundario.setLayout(null);
		panelSecundario.setForeground(new Color(238, 238, 236));
		panelSecundario.setBackground(SystemColor.window);
		panelSecundario.setBorder(null);
		panelSecundario.setBounds(0, 70, 592, 297);
		contentPane.add(panelSecundario);

		// Titulo de panel Secundario
		lblTituloIsbn = new JLabel("Introdueix les dades");
		lblTituloIsbn.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloIsbn.setForeground(new Color(0, 0, 0));
		lblTituloIsbn.setFont(new Font("Dialog", Font.BOLD, 25));
		lblTituloIsbn.setBounds(0, 28, 584, 28);
		panelSecundario.add(lblTituloIsbn);

		lblEditorial= new JLabel("Editorial");
		lblEditorial.setFont(new Font("Dialog", Font.BOLD, 15));
		lblEditorial.setBounds(119, 82, 79, 17);
		panelSecundario.add(lblEditorial);

		// campo Editorial
		textEditorial = new JTextField();
		textEditorial.setFont(new Font("Dialog", Font.BOLD, 14));
		textEditorial.setForeground(new Color(255, 255, 255));
		textEditorial.setBackground(new Color(0, 128, 192));
		textEditorial.setBounds(194, 81, 262, 21);
		textEditorial.setColumns(10);
		panelSecundario.add(textEditorial);

		lblPginas = new JLabel("Pàgines");
		lblPginas.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPginas.setBounds(119, 122, 79, 17);
		panelSecundario.add(lblPginas);

		// campo numero de paginas
		textPaginas = new JTextField();
		textPaginas.setFont(new Font("Dialog", Font.BOLD, 14));
		textPaginas.setForeground(new Color(255, 255, 255));
		textPaginas.setBackground(new Color(0, 128, 192));
		textPaginas.setColumns(10);
		textPaginas.setBounds(194, 121, 262, 21);
		panelSecundario.add(textPaginas);

		lblTemtica = new JLabel("Temàtica");
		lblTemtica.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTemtica.setBounds(119, 163, 79, 17);
		panelSecundario.add(lblTemtica);

		// campo Temática
		JComboBox<Object> comboBox = new JComboBox<Object>();
		comboBox.setFont(new Font("Dialog", Font.BOLD, 14));
		comboBox.setForeground(new Color(255, 255, 255));
		comboBox.setBackground(new Color(0, 128, 192));
		comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"Ciències", "Història", "Literatura", "Filosofia", "Tècnics", "Altres..."}));
		comboBox.setBounds(194, 159, 262, 26);
		panelSecundario.add(comboBox);

		btnVolver = new JButton("Tornar");
		btnVolver.addActionListener(new ActionListener() {
			// función para cambiar de ventana haciendo click en el boton
			public void actionPerformed(ActionEvent e) {
				MenuGestor frame = new MenuGestor();
				frame.setVisible(true);
				dispose();
			}//a ctionPerformed
		});
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVolver.setBackground(new Color(0, 128, 192));
		btnVolver.setBounds(12, 262, 286, 23);
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);
		panelSecundario.add(btnVolver);

		btnAceptar = new JButton("Acceptar");
		btnAceptar.setBounds(316, 261, 268, 24);
		panelSecundario.add(btnAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editorial = textEditorial.getText().toString();
				numpaginas = Integer.parseInt(textPaginas.getText());
				tematica = comboBox.getSelectedItem().toString();

				Libro libro = new Libro(documento.getISBN(), editorial, numpaginas, tematica);
				DocumentoMaxDB docDB = new DocumentoMaxDB();

				if (docDB.insertDocLib(documento, libro)) {
					JOptionPane.showMessageDialog(panelSecundario, "Registro exitoso", "Libro", JOptionPane.INFORMATION_MESSAGE);
					MenuGestor menu = new MenuGestor();
					menu.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(panelSecundario, "Error al introducir datos en la DB", "Error", JOptionPane.ERROR_MESSAGE);
				}// if else
			}// actionPerformed
		});
		btnAceptar.setFocusPainted(false);
		btnAceptar.setBorderPainted(false);
		btnAceptar.setForeground(new Color(255, 255, 255));
		btnAceptar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnAceptar.setBackground(new Color(0, 128, 192));
	}// VentanaAltaLibro
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAltaLibro frame = new VentanaAltaLibro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}// try catch
			}// run
		});
	}// main
}// VentanaAltaLibro