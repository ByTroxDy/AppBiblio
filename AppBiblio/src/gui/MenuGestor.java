package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGestor extends JFrame {
	private static final long serialVersionUID = 1L;
	private JButton btnCerrarSesion;

	static String usuario;

	public MenuGestor() {
		setTitle("Menu Gestor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(2, 2, 10, 10));
		mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

		JLabel lblTitulo = new JLabel("Bienvenido a la Biblioteca App");
		lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);

		btnCerrarSesion = new JButton("Cerrar Sesión");

		mainPanel.add(lblTitulo);
		mainPanel.add(btnCerrarSesion);

		getContentPane().add(mainPanel);

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
				MenuGestor menu = new MenuGestor();
				menu.setVisible(true);
			}
		});
	}
}
