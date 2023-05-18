package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuSocio extends JFrame {
	JButton btnEditarPerfil;
	JButton btnConsultarDocumentos;
	JButton btnReservarDocumento;
	JButton btnPedirPrestamo;
	JButton btnCerrarSesion;
	
	public MenuSocio() {
		setTitle("Menu Socio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(6, 2, 10, 10));
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		JLabel tituloLabel = new JLabel("Bienvenido a la Biblioteca App");
		tituloLabel.setFont(new Font("Arial", Font.BOLD, 18));
		tituloLabel.setHorizontalAlignment(SwingConstants.CENTER);

		btnEditarPerfil = new JButton("Editar Perfil");
		btnConsultarDocumentos = new JButton("Consultar Documentos");
		btnPedirPrestamo = new JButton("Pedir Prestamo");
		btnReservarDocumento = new JButton("Reservar Documento");
		btnCerrarSesion = new JButton("Cerrar Sesión");

		mainPanel.add(tituloLabel);
		mainPanel.add(btnEditarPerfil);
		mainPanel.add(btnConsultarDocumentos);
		mainPanel.add(btnPedirPrestamo);
		mainPanel.add(btnReservarDocumento);
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
		
		btnPedirPrestamo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ex) {
				// Lógica para reservar un documento y mostrar el resultado
				JOptionPane.showMessageDialog(MenuSocio.this, "Funcionalidad en desarrollo", "En construcción",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		btnReservarDocumento.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ex) {
				String nombreUsuario = "admin";
				VentanaReservarDocumento ventana = new VentanaReservarDocumento(nombreUsuario);
				ventana.setVisible(true);
				dispose();
			}
		});

		btnCerrarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ex) {
				dispose();
				BibliotecaApp registro = new BibliotecaApp();
				registro.setVisible(true);
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
