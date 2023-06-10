package gestor;

import java.awt.Color;
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
import javax.swing.border.EmptyBorder;

import app.Documento;
import app.Libro;
import db.DocumentoMaxDB;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class VentanaModificarLibro extends JFrame {
	private static final long serialVersionUID = 1L;
	static Documento documento;

	private JPanel contentPane;
	private JTextField textFieldEditorial, textFieldPaginas;
	private JLabel lblAlta, lblImagen, lblTituloDatos, lblEditorial, lblPginas, lblTemtica;
	private JButton btnVolver, btnAceptar;
	private JComboBox<Object> tematicaBox;
	
	private String editorial, tematica;
	private int numpaginas;

	public VentanaModificarLibro() {
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
		panelPrincipal.setLayout(null);
		panelPrincipal.setBackground(new Color(0, 128, 192));
		panelPrincipal.setForeground(new Color(0, 0, 0));
		panelPrincipal.setBounds(0, 0, 592, 75);

		lblAlta = new JLabel("MODIFICAR LLIBRE");
		lblAlta.setBounds(94, 0, 385, 75);
		lblAlta.setForeground(new Color(238, 238, 236));
		lblAlta.setFont(new Font("Dialog", Font.BOLD, 40));
		
		lblImagen = new JLabel("");
		lblImagen.setIcon(new ImageIcon(MenuGestor.class.getResource("/img/icono64.png")));
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagen.setBounds(492, 0, 60, 75);

		panelPrincipal.add(lblAlta);
		panelPrincipal.add(lblImagen);
		contentPane.add(panelPrincipal);

		JPanel panelSecundario = new JPanel();
		panelSecundario.setLayout(null);
		panelSecundario.setForeground(new Color(238, 238, 236));
		panelSecundario.setBackground(SystemColor.window);
		panelSecundario.setBounds(0, 70, 592, 297);

		lblTituloDatos = new JLabel("Introdeix les noves dades");
		lblTituloDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloDatos.setForeground(new Color(0, 0, 0));
		lblTituloDatos.setFont(new Font("Dialog", Font.BOLD, 25));
		lblTituloDatos.setBounds(0, 29, 592, 28);

		lblEditorial = new JLabel("Editorial");
		lblEditorial.setFont(new Font("Dialog", Font.BOLD, 15));
		lblEditorial.setBounds(168, 84, 79, 17);

		textFieldEditorial = new JTextField();
		textFieldEditorial.setBackground(new Color(0, 128, 192));
		textFieldEditorial.setForeground(new Color(238, 238, 236));
		textFieldEditorial.setFont(new Font("Dialog", Font.BOLD, 14));
		textFieldEditorial.setBounds(247, 84, 183, 21);
		textFieldEditorial.setColumns(10);

		lblPginas = new JLabel("Págines");
		lblPginas.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPginas.setBounds(168, 131, 79, 17);

		textFieldPaginas = new JTextField();
		textFieldPaginas.setBackground(new Color(0, 128, 192));
		textFieldPaginas.setForeground(new Color(238, 238, 236));
		textFieldPaginas.setFont(new Font("Dialog", Font.BOLD, 14));
		textFieldPaginas.setColumns(10);
		textFieldPaginas.setBounds(247, 131, 183, 21);

		lblTemtica = new JLabel("Temática");
		lblTemtica.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTemtica.setBounds(168, 185, 79, 17);

		tematicaBox = new JComboBox<Object>();
		tematicaBox.setBackground(new Color(0, 128, 192));
		tematicaBox.setForeground(new Color(238, 238, 236));
		tematicaBox.setFont(new Font("Dialog", Font.BOLD, 14));
		tematicaBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"Ciencies", "Historia", "Lliteratura", "Filosofía", "Técnics", "Altres..."}));
		tematicaBox.setBounds(247, 182, 183, 26);

		btnVolver = new JButton("Tornar");
		btnVolver.setForeground(new Color(238, 238, 236));
		btnVolver.setFont(new Font("Dialog", Font.BOLD, 14));
		btnVolver.setBackground(new Color(0, 128, 192));
		btnVolver.setBounds(12, 262, 291, 23);
		btnVolver.setFocusPainted(false);
		btnVolver.setBorderPainted(false);

		btnAceptar = new JButton("Acceptar");
		btnAceptar.setBounds(315, 261, 265, 24);
		btnAceptar.setFocusPainted(false);
		btnAceptar.setBorderPainted(false);
		btnAceptar.setForeground(new Color(238, 238, 236));
		btnAceptar.setFont(new Font("Dialog", Font.BOLD, 14));
		btnAceptar.setBackground(new Color(0, 128, 192));

		panelSecundario.add(lblTituloDatos);
		panelSecundario.add(lblEditorial);
		panelSecundario.add(textFieldEditorial);
		panelSecundario.add(lblPginas);
		panelSecundario.add(textFieldPaginas);
		panelSecundario.add(lblTemtica);
		panelSecundario.add(tematicaBox);
		panelSecundario.add(btnVolver);
		panelSecundario.add(btnAceptar);
		contentPane.add(panelSecundario);

		//Funciones
		btnVolver.addActionListener(new ActionListener() {
			// función para cambiar de ventana haciendo click en el boton
			public void actionPerformed(ActionEvent e) {
				MenuGestor frame = new MenuGestor();
				frame.setVisible(true);
				dispose();
			}
		});
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldEditorial.getText().isEmpty() | textFieldPaginas.getText().isEmpty()) {
					JOptionPane.showMessageDialog(panelSecundario, "Introduce todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
				} else {
					editorial = textFieldEditorial.getText().toString();
					numpaginas = Integer.parseInt(textFieldPaginas.getText());
					tematica = tematicaBox.getSelectedItem().toString();

					Libro libro = new Libro(documento.getISBN(), editorial, numpaginas, tematica);
					DocumentoMaxDB docDB = new DocumentoMaxDB();

					if (docDB.updateDocLib(documento, libro)) {
						JOptionPane.showMessageDialog(panelSecundario, "Actualización exitoso", "Libro", JOptionPane.INFORMATION_MESSAGE);
						MenuGestor menu = new MenuGestor();
						menu.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(panelSecundario, "Error al intentar actualizar datos en la DB", "Error", JOptionPane.ERROR_MESSAGE);
					}// if else	
				}// if else
			}//actionPerformed
		});
	}// VentanaModificarLibro
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaModificarLibro frame = new VentanaModificarLibro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}// try catch
			}// run
		});
	}// main
}// VentanaModificarLibro