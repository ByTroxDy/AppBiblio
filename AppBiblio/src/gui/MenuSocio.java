package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import app.Reservas;
import db.DocumentoDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MenuSocio extends JFrame {
	private static final long serialVersionUID = 1L;
	private JButton btnEditarPerfil, btnConsultarDocumentos, btnDevolverDocumento, btnConsultarReservas, btnCerrarSesion;

	static String usuario;

	public MenuSocio() {
		setTitle("Menu Socio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(6, 2, 10, 10));
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		JLabel lblTitulo = new JLabel("Bienvenido a la Biblioteca App");
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

		btnEditarPerfil = new JButton("Editar Perfil");
		btnConsultarDocumentos = new JButton("Consultar Documentos");
		btnDevolverDocumento = new JButton("Devolver Documento");
		btnConsultarReservas = new JButton("Consultar Mis Reservas");
		btnCerrarSesion = new JButton("Cerrar Sesión");

		mainPanel.add(lblTitulo);
		mainPanel.add(btnEditarPerfil);
		mainPanel.add(btnConsultarDocumentos);
		mainPanel.add(btnDevolverDocumento);
		mainPanel.add(btnConsultarReservas);
		mainPanel.add(btnCerrarSesion);

		getContentPane().add(mainPanel);

		// Agregar listeners a los botones
		btnEditarPerfil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ex) {
				VentanaEditarPerfil ventana = new VentanaEditarPerfil();
				ventana.setVisible(true);
				dispose();
			}
		});

		btnConsultarDocumentos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ex) {
				VentanaConsultarDocumento ventana = new VentanaConsultarDocumento();
				ventana.setVisible(true);
				dispose();
			}
		});
		
		btnDevolverDocumento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ex) {
				JOptionPane.showMessageDialog(mainPanel, "Funcionalidad en desarrollo", "En construcción",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		btnConsultarReservas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ex) {
				ArrayList<Reservas> reservas;
				DocumentoDB docDB = new DocumentoDB();

				reservas = docDB.consultarMisReservas(usuario);

				if (reservas.isEmpty()) {
					JOptionPane.showMessageDialog(mainPanel, "No tienes reservas almacenadas.", "Aviso",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					VentanaConsultarReservas ventana = new VentanaConsultarReservas(reservas);
					ventana.setVisible(true);
					dispose();
				}
			}
		});

		btnCerrarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ex) {
				VentanaInicioSesion app = new VentanaInicioSesion();
				app.setVisible(true);
				dispose();
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
