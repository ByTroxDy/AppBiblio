package gestor;

import app.Documento;
import admin.MenuAdmin;
import db.DocumentoMaxDB;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class VentanaModiDoc extends JFrame {
	private JPanel contentPane;
	private JLabel lblAlta, lblImagen, lblTitulo, lblNuevosDatos, lblAutor, lblReplicas, lblBiblioteca;
	private JTextField txtTitulo, txtAutor, txtReplicas, txtBenicarlo;
	private JButton btnVolver, btnAceptar;
	
	private String tipo, titulo, autor, biblioteca;
	private int replicas;
	public static String grupo;
	public static int isbn;
	
	public VentanaModiDoc() {
		setTitle("Biblioteca App");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaModiDoc.class.getResource("/img/icono32.png")));
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
		
		lblTitulo = new JLabel("Títol");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitulo.setBounds(78, 115, 76, 17);

		lblNuevosDatos = new JLabel("Noves dades");
		lblNuevosDatos.setForeground(Color.BLACK);
		lblNuevosDatos.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNuevosDatos.setBounds(207, 61, 167, 28);
		
		// TITULO
		txtTitulo = new JTextField();
		txtTitulo.setBackground(new Color(0, 128, 192));
		txtTitulo.setForeground(new Color(255, 255, 255));
		txtTitulo.setFont(new Font("Dialog", Font.BOLD, 13));
		txtTitulo.setColumns(10);
		txtTitulo.setBounds(159, 111, 128, 21);

		lblAutor = new JLabel("Autor");
		lblAutor.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAutor.setBounds(305, 115, 60, 17);

		// AUTOR
		txtAutor = new JTextField();
		txtAutor.setBackground(new Color(0, 128, 192));
		txtAutor.setForeground(new Color(255, 255, 255));
		txtAutor.setFont(new Font("Dialog", Font.BOLD, 13));
		txtAutor.setColumns(10);
		txtAutor.setBounds(356, 113, 149, 21);

		lblReplicas = new JLabel("Repliques");
		lblReplicas.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblReplicas.setBounds(78, 178, 76, 17);
		
		//replicas
		txtReplicas = new JTextField();
		txtReplicas.setBackground(new Color(0, 128, 192));
		txtReplicas.setForeground(new Color(255, 255, 255));
		txtReplicas.setFont(new Font("Dialog", Font.BOLD, 13));
		txtReplicas.setColumns(10);
		txtReplicas.setBounds(159, 174, 128, 21);

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

		panelSecundario.add(lblTitulo);
		panelSecundario.add(lblNuevosDatos);
		panelSecundario.add(txtTitulo);
		panelSecundario.add(lblAutor);
		panelSecundario.add(txtAutor);
		panelSecundario.add(lblReplicas);
		panelSecundario.add(txtReplicas);
		panelSecundario.add(lblBiblioteca);
		panelSecundario.add(txtBenicarlo);
		panelSecundario.add(btnVolver);
		panelSecundario.add(btnAceptar);
		contentPane.add(panelSecundario);
		
		//Funciones
		btnVolver.addActionListener(new ActionListener() {
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
			public void actionPerformed(ActionEvent e) {
				if (txtTitulo.getText().isEmpty() | txtAutor.getText().isEmpty()) {
					JOptionPane.showMessageDialog(panelSecundario, "Si us plau, introdueix tots els camps.", "Alerta", JOptionPane.WARNING_MESSAGE);
				} else {
					DocumentoMaxDB docDB = new DocumentoMaxDB();
					tipo = docDB.getTipo(isbn);
					titulo = txtTitulo.getText();
					autor = txtAutor.getText();
					replicas = Integer.parseInt(txtReplicas.getText());
					biblioteca = txtBenicarlo.getText().toString();
					
					if (txtReplicas.getText().isEmpty()) {
						replicas = 1;
					}
					
					Documento doc = new Documento(isbn, tipo, titulo, autor, replicas, biblioteca);
				
					if (tipo.equals("Llibre")) {
						VentanaModiLibro.documento = doc;
						VentanaModiLibro frame = new VentanaModiLibro();
						frame.setVisible(true);
					} else if (tipo.equals("Pel·lícula")) {
						VentanaModiPelicula.documento = doc;
						VentanaModiPelicula frame = new VentanaModiPelicula();
						frame.setVisible(true);
					} else if (tipo.equals("Música")) {
						VentanaModiMusica.documento = doc;
						VentanaModiMusica frame = new VentanaModiMusica();
						frame.setVisible(true);
					} else if (tipo.equals("Documental")) {
						VentanaModiDocumental.documento = doc;
						VentanaModiDocumental frame = new VentanaModiDocumental();
						frame.setVisible(true);
					}// if					
					dispose();
				}// if else
				
			}// actionPerformed
		});
	}
}// VentanaModificarDocumento
