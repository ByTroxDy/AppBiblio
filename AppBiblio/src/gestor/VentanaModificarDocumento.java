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
	private JLabel lblAlta, lblImagen, lblIntroduceElIsbn, lblTitulo, lblNuevosDatos, lblAutor, lblReplicas, lblBiblioteca;
	private JButton btnVolver, btnAceptar;
	
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

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBorder(null);
		panelPrincipal.setBackground(new Color(0, 128, 192));
		panelPrincipal.setForeground(new Color(0, 0, 0));
		panelPrincipal.setBounds(0, 0, 592, 70);
		panelPrincipal.setLayout(null);
		
		lblAlta = new JLabel("MODIFICAR DOCUMENT");
		lblAlta.setBounds(20, 0, 484, 70);
		lblAlta.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlta.setForeground(new Color(255, 255, 255));
		lblAlta.setFont(new Font("Dialog", Font.BOLD, 40));
		
		lblImagen = new JLabel("");
		lblImagen.setIcon(new ImageIcon(MenuGestor.class.getResource("/img/icono64.png")));
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagen.setBounds(514, 0, 46, 70);
		
		panelPrincipal.add(lblAlta);
		panelPrincipal.add(lblImagen);
		contentPane.add(panelPrincipal);
		
		JPanel panelSecundario = new JPanel();
		panelSecundario.setLayout(null);
		panelSecundario.setForeground(new Color(238, 238, 236));
		panelSecundario.setBackground(SystemColor.window);
		panelSecundario.setBorder(null);
		panelSecundario.setBounds(0, 70, 592, 297);

		lblIntroduceElIsbn = new JLabel("ISBN del document");
		lblIntroduceElIsbn.setForeground(new Color(0, 0, 0));
		lblIntroduceElIsbn.setFont(new Font("Dialog", Font.BOLD, 20));
		lblIntroduceElIsbn.setBounds(78, 26, 191, 20);
		
		lblTitulo = new JLabel("Títol");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitulo.setBounds(78, 115, 76, 17);

		lblNuevosDatos = new JLabel("Noves dades");
		lblNuevosDatos.setForeground(Color.BLACK);
		lblNuevosDatos.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNuevosDatos.setBounds(207, 61, 167, 28);
		
		// TITULO
		textFieldTitulo = new JTextField();
		textFieldTitulo.setBackground(new Color(0, 128, 192));
		textFieldTitulo.setForeground(new Color(255, 255, 255));
		textFieldTitulo.setFont(new Font("Dialog", Font.BOLD, 13));
		textFieldTitulo.setColumns(10);
		textFieldTitulo.setBounds(159, 111, 128, 21);

		// isbn
		textFieldIsbn = new JTextField();
		textFieldIsbn.setBackground(new Color(0, 128, 192));
		textFieldIsbn.setForeground(new Color(255, 255, 255));
		textFieldIsbn.setFont(new Font("Dialog", Font.BOLD, 13));
		textFieldIsbn.setColumns(10);
		textFieldIsbn.setBounds(287, 28, 180, 21);

		lblAutor = new JLabel("Autor");
		lblAutor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAutor.setBounds(305, 115, 60, 17);

		// AUTOR
		textFieldAutor = new JTextField();
		textFieldAutor.setBackground(new Color(0, 128, 192));
		textFieldAutor.setForeground(new Color(255, 255, 255));
		textFieldAutor.setFont(new Font("Dialog", Font.BOLD, 13));
		textFieldAutor.setColumns(10);
		textFieldAutor.setBounds(356, 113, 149, 21);

		lblReplicas = new JLabel("Repliques");
		lblReplicas.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblReplicas.setBounds(78, 178, 76, 17);
		
		//replicas
		textFieldReplicas = new JTextField();
		textFieldReplicas.setBackground(new Color(0, 128, 192));
		textFieldReplicas.setForeground(new Color(255, 255, 255));
		textFieldReplicas.setFont(new Font("Dialog", Font.BOLD, 13));
		textFieldReplicas.setColumns(10);
		textFieldReplicas.setBounds(159, 174, 128, 21);

		lblBiblioteca = new JLabel("Biblio");
		lblBiblioteca.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBiblioteca.setBounds(304, 177, 60, 17);
		
		//biblioteca
		txtBenicarlo = new JTextField();
		txtBenicarlo.setForeground(new Color(255, 255, 255));
		txtBenicarlo.setBackground(new Color(0, 128, 192));
		txtBenicarlo.setEditable(false);
		txtBenicarlo.setText("Benicarlo");
		txtBenicarlo.setFont(new Font("Dialog", Font.BOLD, 14));
		txtBenicarlo.setColumns(10);
		txtBenicarlo.setBounds(356, 174, 149, 21);
		
		//Volver
		btnVolver = new JButton("Tornar");
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);
		btnVolver.setBackground(new Color(0, 128, 192));
		btnVolver.setBounds(10, 257, 280, 28);

		//Modificar
		btnAceptar = new JButton("Acceptar");
		btnAceptar.setBounds(316, 257, 264, 28);
		btnAceptar.setFocusPainted(false);
		btnAceptar.setBorderPainted(false);
		btnAceptar.setForeground(new Color(255, 255, 255));
		btnAceptar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnAceptar.setBackground(new Color(0, 128, 192));

		panelSecundario.add(lblIntroduceElIsbn);
		panelSecundario.add(lblTitulo);
		panelSecundario.add(lblNuevosDatos);
		panelSecundario.add(textFieldTitulo);
		panelSecundario.add(textFieldIsbn);
		panelSecundario.add(lblAutor);
		panelSecundario.add(textFieldAutor);
		panelSecundario.add(lblReplicas);
		panelSecundario.add(textFieldReplicas);
		panelSecundario.add(lblBiblioteca);
		panelSecundario.add(txtBenicarlo);
		panelSecundario.add(btnVolver);
		panelSecundario.add(btnAceptar);
		contentPane.add(panelSecundario);
		
		//Funciones
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuGestor frame = new MenuGestor();
				frame.setVisible(true);
				dispose();
			}// actionPerformed
		});
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textFieldIsbn.getText().isEmpty() | textFieldTitulo.getText().isEmpty()
						| textFieldAutor.getText().isEmpty() |textFieldReplicas.getText().isEmpty() ) {
					JOptionPane.showMessageDialog(panelSecundario, "Introduce todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					isbn = Integer.parseInt(textFieldIsbn.getText());
					DocumentoMaxDB docDB = new DocumentoMaxDB();
					if (!docDB.comprobarIsbn(isbn)) {
						JOptionPane.showMessageDialog(panelSecundario, "El ISBN introducido no existe", "Error", JOptionPane.ERROR_MESSAGE);
					} else {
						tipo = docDB.getTipo(isbn);
						titulo = textFieldTitulo.getText();
						autor = textFieldAutor.getText();
						replicas = Integer.parseInt(textFieldReplicas.getText());
						biblioteca = txtBenicarlo.getText().toString();
						
						Documento doc = new Documento(isbn, tipo, titulo, autor, replicas, biblioteca);
					
						if (tipo.equals("Llibre")) {
							VentanaModificarLibro.documento = doc;
							VentanaModificarLibro frame = new VentanaModificarLibro();
							frame.setVisible(true);
						} else if (tipo.equals("Pel·lícula")) {
							VentanaModificarPelicula.documento = doc;
							VentanaModificarPelicula frame = new VentanaModificarPelicula();
							frame.setVisible(true);
						} else if (tipo.equals("Música")) {
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
