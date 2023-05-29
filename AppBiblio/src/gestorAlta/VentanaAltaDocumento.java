package gestorAlta;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.Documento;
import gestor.VentanaGestor;

public class VentanaAltaDocumento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldTitulo;
	private JTextField textFieldIsbn;
	private JTextField textFieldAutor;
	private int isbn;
	private String titulo;
	private String autor;
	private String tipo;

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
		setTitle("Alta Documento");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 323);
		contentPane = new JPanel();
		contentPane.setForeground(UIManager.getColor("Panel.foreground"));
		contentPane.setBackground(SystemColor.window);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		// Panel
		JPanel principal = new JPanel();
		principal.setBackground(SystemColor.window);
		principal.setForeground(new Color(0, 0, 0));
		principal.setBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0, 0), 3)));
		principal.setBounds(53, 14, 359, 44);
		contentPane.add(principal);

		// Introduccion de datos

		// Titulo del panel creado
		JLabel lblAlta = new JLabel("ALTA DOCUMENTO");
		lblAlta.setForeground(new Color(0, 0, 0));
		lblAlta.setFont(new Font("Dialog", Font.BOLD, 20));
		principal.add(lblAlta);

		// Creación de panel de contenido
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setForeground(new Color(238, 238, 236));
		panel_1.setBackground(SystemColor.window);
		panel_1.setBorder(new TitledBorder(new CompoundBorder(null, new LineBorder(new Color(0, 0, 0), 3, true)),
				"Bienvenido", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(53, 70, 360, 208);
		contentPane.add(panel_1);

		// Titulo Introduce los datos
		JLabel lblIntroduceLosDatos = new JLabel("Introduce los datos");
		lblIntroduceLosDatos.setForeground(Color.BLACK);
		lblIntroduceLosDatos.setFont(new Font("Dialog", Font.BOLD, 18));
		lblIntroduceLosDatos.setBounds(88, 26, 189, 28);
		panel_1.add(lblIntroduceLosDatos);

		// Labels y textField para la introducción de datos
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(46, 95, 60, 17);
		panel_1.add(lblTitulo);

		// TITULO
		textFieldTitulo = new JTextField();
		textFieldTitulo.setFont(new Font("Dialog", Font.PLAIN, 10));
		textFieldTitulo.setColumns(10);
		textFieldTitulo.setBounds(99, 98, 69, 21);
		panel_1.add(textFieldTitulo);

		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setBounds(46, 66, 60, 17);
		panel_1.add(lblIsbn);

		// isbn
		textFieldIsbn = new JTextField();
		textFieldIsbn.setFont(new Font("Dialog", Font.PLAIN, 10));
		textFieldIsbn.setColumns(10);
		textFieldIsbn.setBounds(99, 65, 69, 21);
		panel_1.add(textFieldIsbn);

		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setBounds(195, 66, 60, 17);
		panel_1.add(lblAutor);

		// AUTOR
		textFieldAutor = new JTextField();
		textFieldAutor.setFont(new Font("Dialog", Font.PLAIN, 10));
		textFieldAutor.setColumns(10);
		textFieldAutor.setBounds(245, 65, 69, 21);
		panel_1.add(textFieldAutor);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			// función para cambiar de ventana haciendo click en el boton
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
		btnVolver.setBounds(12, 172, 153, 23);
		panel_1.add(btnVolver);

		JComboBox<Object> tipoBox = new JComboBox<Object>();
		tipoBox.setModel(
				new DefaultComboBoxModel<Object>(new String[] { "Libro", "Pelicula", "Musica", "Documental" }));
		tipoBox.setBounds(245, 94, 74, 26);
		panel_1.add(tipoBox);

		JLabel lblTipo = new JLabel("tipo");
		lblTipo.setBounds(195, 99, 60, 17);
		panel_1.add(lblTipo);

		// ------------------------- SELECCIÓN TIPO ------------------------- //
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setForeground(Color.BLACK);
		btnAceptar.setFont(new Font("Dialog", Font.BOLD, 12));
		btnAceptar.setFocusPainted(false);
		btnAceptar.setBorderPainted(false);
		btnAceptar.setBackground(UIManager.getColor("Button.darkShadow"));
		btnAceptar.setBounds(195, 172, 153, 23);
		btnAceptar.addActionListener(new ActionListener() {
			// función para crear objeto Documento y llamada a función inserar
			public void actionPerformed(ActionEvent e) {
				if (textFieldIsbn.getText().isEmpty() | textFieldTitulo.getText().isEmpty()
						| textFieldAutor.getText().isEmpty()) {
					JOptionPane.showMessageDialog(panel_1, "Introduce todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					isbn = Integer.parseInt(textFieldIsbn.getText());
					titulo = textFieldTitulo.getText();
					autor = textFieldAutor.getText();
					tipo = tipoBox.getSelectedItem().toString();
					
					if (tipo == "Libro") {
						ventanaAltaLibro frame = new ventanaAltaLibro();
						// El meu document
						frame.setDocument(new Documento(isbn, titulo, autor));
						frame.setVisible(true);
						dispose();
					} else if (tipo == "Pelicula") {
						VentanaAltaPelicula frame = new VentanaAltaPelicula();
						// El meu document
						frame.setDocument(new Documento(isbn, titulo, autor));
						frame.setVisible(true);
						dispose();
					} else if (tipo == "Musica") {
						VentanaAltaMusica frame = new VentanaAltaMusica();
						// El meu document
						frame.setDocument(new Documento(isbn, titulo, autor));
						frame.setVisible(true);
						dispose();
					} else if (tipo == "Documental") {
						VentanaAltaDocumental frame = new VentanaAltaDocumental();
						// El meu document
						frame.setDocument(new Documento(isbn, titulo, autor));
						frame.setVisible(true);
						dispose();
					}//if
				}//if else
			}// actionPerformed
		});
		panel_1.add(btnAceptar);
	}
}
