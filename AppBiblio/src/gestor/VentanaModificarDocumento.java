package gestor;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import app.Documento;
import db.DocumentoMaxDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaModificarDocumento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldIsbn, textFieldTitulo, textFieldAutor, textFieldReplicas, txtBenicarlo;
	private String tipo, titulo, autor, biblioteca;
	private int isbn, replicas;
	
	public VentanaModificarDocumento() {
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
		
		JLabel lblAlta = new JLabel("MODIFICAR DOCUMENTO");
		lblAlta.setForeground(new Color(0, 0, 0));
		lblAlta.setFont(new Font("Dialog", Font.BOLD, 20));
		panel.add(lblAlta);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(new Color(238, 238, 236));
		panel_1.setBackground(SystemColor.window);
		panel_1.setBorder(new TitledBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0, 0), 3, true)), "Verificaci\u00F3n de la existencia", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(53, 70, 360, 208);
		contentPane.add(panel_1);
		

		JLabel lblIntroduceElIsbn = new JLabel("ISBN documento");
		lblIntroduceElIsbn.setForeground(new Color(0, 0, 0));
		lblIntroduceElIsbn.setFont(new Font("Dialog", Font.BOLD, 17));
		lblIntroduceElIsbn.setBounds(20, 21, 132, 28);
		panel_1.add(lblIntroduceElIsbn);
		
		// Labels y textField para la introducción de datos
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(40, 100, 60, 17);
		panel_1.add(lblTitulo);

		// TITULO
		textFieldTitulo = new JTextField();
		textFieldTitulo.setFont(new Font("Dialog", Font.PLAIN, 10));
		textFieldTitulo.setColumns(10);
		textFieldTitulo.setBounds(99, 98, 69, 21);
		panel_1.add(textFieldTitulo);

		// isbn
		textFieldIsbn = new JTextField();
		textFieldIsbn.setFont(new Font("Dialog", Font.PLAIN, 10));
		textFieldIsbn.setColumns(10);
		textFieldIsbn.setBounds(162, 28, 143, 21);
		panel_1.add(textFieldIsbn);

		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setBounds(195, 100, 60, 17);
		panel_1.add(lblAutor);

		// AUTOR
		textFieldAutor = new JTextField();
		textFieldAutor.setFont(new Font("Dialog", Font.PLAIN, 10));
		textFieldAutor.setColumns(10);
		textFieldAutor.setBounds(236, 98, 69, 21);
		panel_1.add(textFieldAutor);

		JLabel lblTipo = new JLabel("tipo");
		lblTipo.setBounds(195, 127, 60, 17);
		panel_1.add(lblTipo);
		
		JComboBox<Object> tipoBox = new JComboBox<Object>();
		tipoBox.setModel(
				new DefaultComboBoxModel<Object>(new String[] { "Libro", "Pelicula", "Musica", "Documental" }));
		tipoBox.setBounds(236, 122, 74, 26);
		panel_1.add(tipoBox);

		
		JLabel lblReplicas = new JLabel("Replicas");
		lblReplicas.setBounds(40, 127, 60, 17);
		panel_1.add(lblReplicas);
		
		//replicas
		textFieldReplicas = new JTextField();
		textFieldReplicas.setFont(new Font("Dialog", Font.PLAIN, 10));
		textFieldReplicas.setColumns(10);
		textFieldReplicas.setBounds(99, 124, 69, 21);
		panel_1.add(textFieldReplicas);
		
		
		JLabel lblBiblioteca = new JLabel("Biblio");
		lblBiblioteca.setBounds(126, 175, 60, 17);
		panel_1.add(lblBiblioteca);
		
		//biblioteca
		txtBenicarlo = new JTextField();
		txtBenicarlo.setEditable(false);
		txtBenicarlo.setText("Benicarlo");
		txtBenicarlo.setFont(new Font("Dialog", Font.PLAIN, 10));
		txtBenicarlo.setColumns(10);
		txtBenicarlo.setBounds(162, 173, 69, 21);
		panel_1.add(txtBenicarlo);
		
		//Volver
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuGestor frame = new MenuGestor();
				frame.setVisible(true);
				dispose();
			}
		});
		btnVolver.setForeground(Color.BLACK);
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 12));
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);
		btnVolver.setBackground(UIManager.getColor("Button.darkShadow"));
		btnVolver.setBounds(12, 168, 79, 28);
		panel_1.add(btnVolver);

		//Modificar
		JButton btnNewButton = new JButton("Modificar\n");
		btnNewButton.setBounds(257, 168, 91, 28);
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldIsbn.getText().isEmpty() | textFieldTitulo.getText().isEmpty()
						| textFieldAutor.getText().isEmpty() |textFieldReplicas.getText().isEmpty() ) {
					JOptionPane.showMessageDialog(panel_1, "Introduce todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					isbn = Integer.parseInt(textFieldIsbn.getText());
					tipo = tipoBox.getSelectedItem().toString();
					titulo = textFieldTitulo.getText();
					autor = textFieldAutor.getText();
					replicas = Integer.parseInt(textFieldReplicas.getText());
					biblioteca = txtBenicarlo.getText().toString();
					
					Documento doc = new Documento(isbn, titulo, autor, replicas, biblioteca);

					DocumentoMaxDB docDB = new DocumentoMaxDB();
					if (!docDB.comprobarIsbn(isbn)) {
						JOptionPane.showMessageDialog(panel_1, "El ISBN introducido no existe", "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						if (tipo == "Libro") {
							VentanaModificarLibro.documento = doc;
							VentanaModificarLibro frame = new VentanaModificarLibro();
							frame.setVisible(true);
						} else if (tipo == "Pelicula") {
							VentanaModificarPelicula.documento = doc;
							VentanaModificarPelicula frame = new VentanaModificarPelicula();
							frame.setVisible(true);
						} else if (tipo == "Musica") {
							VentanaModificarMusica.documento = doc;
							VentanaModificarMusica frame = new VentanaModificarMusica();
							frame.setVisible(true);
						} else if (tipo == "Documental") {
							VentanaModificarDocumental.documento = doc;
							VentanaModificarDocumental frame = new VentanaModificarDocumental();
							frame.setVisible(true);
						}// if					
						dispose();
					}// if else
				}// if else
			}// actionPerformed
		});
		btnNewButton.setFocusPainted(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 12));
		btnNewButton.setBackground(UIManager.getColor("Button.darkShadow"));
		
		JLabel lblNuevosDatos = new JLabel("Nuevos datos");
		lblNuevosDatos.setForeground(Color.BLACK);
		lblNuevosDatos.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNuevosDatos.setBounds(126, 61, 120, 28);
		panel_1.add(lblNuevosDatos);
	}// VentanaModificarDocumento

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaModificarDocumento frame = new VentanaModificarDocumento();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}// try catch
			}// run
		});
	}// main
}// VentanaModificarDocumento
