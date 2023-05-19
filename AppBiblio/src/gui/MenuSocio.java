package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuSocio extends JFrame {
	private static final long serialVersionUID = 1L;
	private JButton btnEditarPerfil;
	private JButton btnConsultarDocumentos;
	private JButton btnConsultarReservas;
	private JButton btnCerrarSesion;
	
	public MenuSocio() {
		setTitle("Menu Socio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(5, 2, 10, 10));
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		JLabel lblTitulo = new JLabel("Bienvenido a la Biblioteca App");
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

		btnEditarPerfil = new JButton("Editar Perfil");
		btnConsultarDocumentos = new JButton("Consultar Documentos");
		btnConsultarReservas = new JButton("Consultar Mis Reservas");
		btnCerrarSesion = new JButton("Cerrar Sesión");

		mainPanel.add(lblTitulo);
		mainPanel.add(btnEditarPerfil);
		mainPanel.add(btnConsultarDocumentos);
		mainPanel.add(btnConsultarReservas);
		mainPanel.add(btnCerrarSesion);

		getContentPane().add(mainPanel);

		// Agregar listeners a los botones
		btnEditarPerfil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ex) {
				VentanaEditarPerfil editarPerfil = new VentanaEditarPerfil();
				editarPerfil.setVisible(true);
				dispose();
			}
		});
		
		btnConsultarDocumentos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ex) {
				VentanaBuscarDocumento buscarDoc = new VentanaBuscarDocumento();
				buscarDoc.setVisible(true);
				dispose();
			}
		});
		
		btnConsultarReservas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ex) {
				// Lógica para reservar un documento y mostrar el resultado
				JOptionPane.showMessageDialog(MenuSocio.this, "Funcionalidad en desarrollo", "En construcción",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		btnCerrarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ex) {
				dispose();
				VentanaInicioSesion app = new VentanaInicioSesion();
				app.setVisible(true);
			}
		});

		pack();
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MenuSocio menu = new MenuSocio();
				menu.setVisible(true);
			}
		});
	}

}
