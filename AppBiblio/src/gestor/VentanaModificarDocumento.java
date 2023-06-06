package gestor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

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
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setForeground(UIManager.getColor("Panel.foreground"));
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(0, 128, 192));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBounds(0, 0, 592, 70);
		panel.setLayout(null);
		contentPane.add(panel);
		
		JLabel lblAlta = new JLabel("MODIFICAR DOCUMENT");
		lblAlta.setBounds(20, 0, 484, 70);
		lblAlta.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlta.setForeground(new Color(255, 255, 255));
		lblAlta.setFont(new Font("Dialog", Font.BOLD, 40));
		panel.add(lblAlta);
		
		JLabel lblImagen = new JLabel("");
		lblImagen.setIcon(new ImageIcon(MenuGestor.class.getResource("/img/icono64.png")));
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagen.setBounds(514, 0, 46, 70);
		panel.add(lblImagen);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(new Color(238, 238, 236));
		panel_1.setBackground(SystemColor.window);
		panel_1.setBorder(null);
		panel_1.setBounds(0, 70, 592, 297);
		contentPane.add(panel_1);

		JLabel lblIntroduceElIsbn = new JLabel("ISBN del document");
		lblIntroduceElIsbn.setForeground(new Color(0, 0, 0));
		lblIntroduceElIsbn.setFont(new Font("Dialog", Font.BOLD, 20));
		lblIntroduceElIsbn.setBounds(78, 26, 191, 20);
		panel_1.add(lblIntroduceElIsbn);
		
		// Labels y textField para la introducción de datos
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitulo.setBounds(88, 114, 60, 17);
		panel_1.add(lblTitulo);

		// TITULO
		textFieldTitulo = new JTextField();
		textFieldTitulo.setBackground(new Color(0, 128, 192));
		textFieldTitulo.setForeground(new Color(255, 255, 255));
		textFieldTitulo.setFont(new Font("Dialog", Font.BOLD, 13));
		textFieldTitulo.setColumns(10);
		textFieldTitulo.setBounds(150, 111, 128, 21);
		panel_1.add(textFieldTitulo);

		// isbn
		textFieldIsbn = new JTextField();
		textFieldIsbn.setBackground(new Color(0, 128, 192));
		textFieldIsbn.setForeground(new Color(255, 255, 255));
		textFieldIsbn.setFont(new Font("Dialog", Font.BOLD, 13));
		textFieldIsbn.setColumns(10);
		textFieldIsbn.setBounds(287, 28, 180, 21);
		panel_1.add(textFieldIsbn);

		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAutor.setBounds(288, 115, 60, 17);
		panel_1.add(lblAutor);

		// AUTOR
		textFieldAutor = new JTextField();
		textFieldAutor.setBackground(new Color(0, 128, 192));
		textFieldAutor.setForeground(new Color(255, 255, 255));
		textFieldAutor.setFont(new Font("Dialog", Font.BOLD, 13));
		textFieldAutor.setColumns(10);
		textFieldAutor.setBounds(339, 113, 149, 21);
		panel_1.add(textFieldAutor);

		JLabel lblReplicas = new JLabel("Replicas");
		lblReplicas.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblReplicas.setBounds(88, 177, 60, 17);
		panel_1.add(lblReplicas);
		
		//replicas
		textFieldReplicas = new JTextField();
		textFieldReplicas.setBackground(new Color(0, 128, 192));
		textFieldReplicas.setForeground(new Color(255, 255, 255));
		textFieldReplicas.setFont(new Font("Dialog", Font.BOLD, 13));
		textFieldReplicas.setColumns(10);
		textFieldReplicas.setBounds(150, 174, 128, 21);
		panel_1.add(textFieldReplicas);

		JLabel lblBiblioteca = new JLabel("Biblio");
		lblBiblioteca.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBiblioteca.setBounds(287, 177, 60, 17);
		panel_1.add(lblBiblioteca);
		
		//biblioteca
		txtBenicarlo = new JTextField();
		txtBenicarlo.setForeground(new Color(255, 255, 255));
		txtBenicarlo.setBackground(new Color(0, 128, 192));
		txtBenicarlo.setEditable(false);
		txtBenicarlo.setText("Benicarlo");
		txtBenicarlo.setFont(new Font("Dialog", Font.BOLD, 14));
		txtBenicarlo.setColumns(10);
		txtBenicarlo.setBounds(339, 174, 149, 21);
		panel_1.add(txtBenicarlo);
		
		//Volver
		JButton btnVolver = new JButton("Tornar");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuGestor frame = new MenuGestor();
				frame.setVisible(true);
				dispose();
			}// actionPerformed
		});
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);
		btnVolver.setBackground(new Color(0, 128, 192));
		btnVolver.setBounds(10, 257, 280, 28);
		panel_1.add(btnVolver);

		//Modificar
		JButton btnNewButton = new JButton("Acceptar");
		btnNewButton.setBounds(316, 257, 264, 28);
		panel_1.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldIsbn.getText().isEmpty() | textFieldTitulo.getText().isEmpty()
						| textFieldAutor.getText().isEmpty() |textFieldReplicas.getText().isEmpty() ) {
					JOptionPane.showMessageDialog(panel_1, "Introduce todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					isbn = Integer.parseInt(textFieldIsbn.getText());
					DocumentoMaxDB docDB = new DocumentoMaxDB();
					if (!docDB.comprobarIsbn(isbn)) {
						JOptionPane.showMessageDialog(panel_1, "El ISBN introducido no existe", "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						tipo = docDB.getTipo(isbn);
						titulo = textFieldTitulo.getText();
						autor = textFieldAutor.getText();
						replicas = Integer.parseInt(textFieldReplicas.getText());
						biblioteca = txtBenicarlo.getText().toString();
						
						Documento doc = new Documento(isbn, tipo, titulo, autor, replicas, biblioteca);
					
						if (tipo.equals("Libro")) {
							VentanaModificarLibro.documento = doc;
							VentanaModificarLibro frame = new VentanaModificarLibro();
							frame.setVisible(true);
						} else if (tipo.equals("Pelicula")) {
							VentanaModificarPelicula.documento = doc;
							VentanaModificarPelicula frame = new VentanaModificarPelicula();
							frame.setVisible(true);
						} else if (tipo.equals("Musica")) {
							VentanaModificarMusica.documento = doc;
							VentanaModificarMusica frame = new VentanaModificarMusica();
							frame.setVisible(true);
						} else if (tipo.equals("Documental")) {
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
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 14));
		btnNewButton.setBackground(new Color(0, 128, 192));
		
		JLabel lblNuevosDatos = new JLabel("Noves dades");
		lblNuevosDatos.setForeground(Color.BLACK);
		lblNuevosDatos.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNuevosDatos.setBounds(207, 61, 167, 28);
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
