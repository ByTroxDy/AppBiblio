package gestor;

import app.Documento;
import admin.MenuAdmin;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class VentanaAltaDoc extends JFrame {
	private JPanel contentPane;
	private JLabel lblAlta, lblImagen, lblTituloDatos, lblTitulo, lblAutor, lblTipo, lblReplicas, lblBiblioteca;
	private JTextField textFieldTitulo, textFieldAutor, textField, txtBenicarlo;
	private JButton btnVolver, btnAceptar;
	private JComboBox<Object> tipoBox;

	private int replicas;
	private String titulo, autor, tipo, biblioteca;
	public static int isbn;
	public static String grupo;

	public VentanaAltaDoc() {
		setTitle("Alta Document");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setForeground(UIManager.getColor("Panel.foreground"));
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		// panel Principal
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(0, 128, 192));
		panelPrincipal.setForeground(new Color(0, 0, 0));
		panelPrincipal.setBorder(null);
		panelPrincipal.setBounds(0, 0, 592, 69);
		panelPrincipal.setLayout(null);

		// Titulo del panel Princial
		lblAlta = new JLabel("ALTA DOCUMENT");
		lblAlta.setForeground(new Color(255, 255, 255));
		lblAlta.setFont(new Font("Dialog", Font.BOLD, 40));
		lblAlta.setBounds(95, 11, 375, 47);

		// Imagen del titulo
		lblImagen = new JLabel("");
		lblImagen.setIcon(new ImageIcon(VentanaAltaDoc.class.getResource("/img/icono64.png")));
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagen.setBounds(480, 0, 60, 69);

		panelPrincipal.add(lblAlta);
		panelPrincipal.add(lblImagen);
		contentPane.add(panelPrincipal);

		// panel de contenido
		JPanel panelSecundario = new JPanel();
		panelSecundario.setLayout(null);
		panelSecundario.setForeground(new Color(238, 238, 236));
		panelSecundario.setBackground(SystemColor.window);
		panelSecundario.setBorder(null);
		panelSecundario.setBounds(0, 70, 592, 297);

		// Titulo Introduce los datos
		lblTituloDatos = new JLabel("Introdueix les dades");
		lblTituloDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloDatos.setForeground(Color.BLACK);
		lblTituloDatos.setFont(new Font("Dialog", Font.BOLD, 25));
		lblTituloDatos.setBounds(0, 26, 584, 28);

		// Labels y textField para la introducción de datos
		lblTitulo = new JLabel("Títol");
		lblTitulo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 14));
		lblTitulo.setBounds(36, 134, 60, 17);

		// TITULO
		textFieldTitulo = new JTextField();
		textFieldTitulo.setForeground(new Color(255, 255, 255));
		textFieldTitulo.setBackground(new Color(0, 128, 192));
		textFieldTitulo.setFont(new Font("Dialog", Font.BOLD, 12));
		textFieldTitulo.setColumns(10);
		textFieldTitulo.setBounds(106, 132, 173, 21);

		lblAutor = new JLabel("Autor");
		lblAutor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAutor.setFont(new Font("Dialog", Font.BOLD, 14));
		lblAutor.setBounds(148, 82, 60, 17);

		// AUTOR
		textFieldAutor = new JTextField();
		textFieldAutor.setForeground(new Color(255, 255, 255));
		textFieldAutor.setBackground(new Color(0, 128, 192));
		textFieldAutor.setFont(new Font("Dialog", Font.BOLD, 12));
		textFieldAutor.setColumns(10);
		textFieldAutor.setBounds(216, 80, 192, 21);

		lblTipo = new JLabel("Tipus");
		lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipo.setFont(new Font("Dialog", Font.BOLD, 14));
		lblTipo.setBounds(294, 134, 60, 17);

		tipoBox = new JComboBox<Object>();
		tipoBox.setFont(new Font("Dialog", Font.BOLD, 12));
		tipoBox.setForeground(new Color(255, 255, 255));
		tipoBox.setBackground(new Color(0, 128, 192));
		tipoBox.setModel(
				new DefaultComboBoxModel<Object>(new String[] { "Llibre", "Pel·lícula", "Documental", "Música" }));
		tipoBox.setBounds(364, 127, 192, 26);

		lblReplicas = new JLabel("Repliques");
		lblReplicas.setHorizontalAlignment(SwingConstants.RIGHT);
		lblReplicas.setFont(new Font("Dialog", Font.BOLD, 14));
		lblReplicas.setBounds(22, 183, 71, 17);

		// replicas
		textField = new JTextField();
		textField.setForeground(new Color(255, 255, 255));
		textField.setBackground(new Color(0, 128, 192));
		textField.setFont(new Font("Dialog", Font.BOLD, 12));
		textField.setColumns(10);
		textField.setBounds(106, 181, 173, 21);

		lblBiblioteca = new JLabel("Biblio");
		lblBiblioteca.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBiblioteca.setFont(new Font("Dialog", Font.BOLD, 14));
		lblBiblioteca.setBounds(294, 183, 60, 17);

		// biblioteca
		txtBenicarlo = new JTextField();
		txtBenicarlo.setForeground(new Color(255, 255, 255));
		txtBenicarlo.setBackground(new Color(0, 128, 192));
		txtBenicarlo.setEditable(false);
		txtBenicarlo.setText("Benicarlo");
		txtBenicarlo.setFont(new Font("Dialog", Font.BOLD, 12));
		txtBenicarlo.setColumns(10);
		txtBenicarlo.setBounds(364, 182, 192, 21);

		btnVolver = new JButton("Tornar");
		btnVolver.setForeground(new Color(255, 255, 255));
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 16));
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);
		btnVolver.setBackground(new Color(0, 128, 192));
		btnVolver.setBounds(10, 262, 230, 23);

		btnAceptar = new JButton("Acceptar");
		btnAceptar.setForeground(new Color(255, 255, 255));
		btnAceptar.setFont(new Font("Dialog", Font.BOLD, 16));
		btnAceptar.setFocusPainted(false);
		btnAceptar.setBorderPainted(false);
		btnAceptar.setBackground(new Color(0, 128, 192));
		btnAceptar.setBounds(354, 262, 230, 23);

		panelSecundario.add(lblTituloDatos);
		panelSecundario.add(lblTitulo);
		panelSecundario.add(textFieldTitulo);
		panelSecundario.add(lblAutor);
		panelSecundario.add(textFieldAutor);
		panelSecundario.add(lblTipo);
		panelSecundario.add(tipoBox);
		panelSecundario.add(lblReplicas);
		panelSecundario.add(textField);
		panelSecundario.add(lblBiblioteca);
		panelSecundario.add(txtBenicarlo);
		panelSecundario.add(btnVolver);
		panelSecundario.add(btnAceptar);
		contentPane.add(panelSecundario);

		// Funciones
		btnVolver.addActionListener(new ActionListener() {
			// función para cambiar de ventana haciendo click en el boton
			public void actionPerformed(ActionEvent e) {
				if (grupo.equals("gestor")) {
					MenuGestor menu = new MenuGestor();
					menu.setVisible(true);
				} else if (grupo.equals("admin")) {
					MenuAdmin menu = new MenuAdmin();
					menu.setVisible(true);
				}
				dispose();
			}// actionPerformed
		});

		btnAceptar.addActionListener(new ActionListener() {
			// función para crear objeto Documento y llamada a función inserar
			public void actionPerformed(ActionEvent e) {
				if (textFieldTitulo.getText().isEmpty() | textFieldAutor.getText().isEmpty()) {
					JOptionPane.showMessageDialog(panelSecundario, "Si us plau, introdueix tots els camps.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					titulo = textFieldTitulo.getText();
					autor = textFieldAutor.getText();
					tipo = tipoBox.getSelectedItem().toString();
					replicas = Integer.parseInt(textField.getText());
					biblioteca = txtBenicarlo.getText().toString();

					Documento doc = new Documento(isbn, tipo, titulo, autor, replicas, biblioteca);

					if (tipo == "Llibre") {
						VentanaAltaLibro.documento = doc;
						VentanaAltaLibro frame = new VentanaAltaLibro();
						frame.setVisible(true);
					} else if (tipo == "Pel·lícula") {
						VentanaAltaPelicula.documento = doc;
						VentanaAltaPelicula frame = new VentanaAltaPelicula();
						frame.setVisible(true);
					} else if (tipo == "Música") {
						VentanaAltaMusica.documento = doc;
						VentanaAltaMusica frame = new VentanaAltaMusica();
						frame.setVisible(true);
					} else if (tipo == "Documental") {
						VentanaAltaDocumental.documento = doc;
						VentanaAltaDocumental frame = new VentanaAltaDocumental();
						frame.setVisible(true);
					} // if
					dispose();
				} // if else
			}// actionPerformed
		});
	}

}// VentanaAltaDocumento
