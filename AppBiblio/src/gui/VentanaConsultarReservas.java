package gui;

import app.Reservas;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaConsultarReservas extends JDialog {
	private static final long serialVersionUID = 1L;
	private JButton btnVolver;

	public VentanaConsultarReservas(ArrayList<Reservas> reservas) {
		setTitle("Resultados de la consulta");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);

		// Configurar el panel
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));

		// Crear un modelo de tabla para las reservas
		DefaultTableModel modeloTabla = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false; // Desactivar la edición de todas las celdas
			}
		};

		modeloTabla.addColumn("ISBN");
		modeloTabla.addColumn("Usuario");
		modeloTabla.addColumn("Fecha Reserva");
		modeloTabla.addColumn("Dias Pendientes");

		// Llenar el modelo de tabla con los datos de los documentos
		for (Reservas reserva : reservas) {
			Object[] fila = new Object[4];
			fila[0] = reserva.getISBN();
			fila[1] = reserva.getUsuario();
			fila[2] = reserva.getFechaReserva();
			fila[3] = reserva.getDiasPendientes();

			modeloTabla.addRow(fila);
		}

		btnVolver = new JButton("Volver");

		// Configurar el panel de botones
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout());
		panelBotones.add(btnVolver);

		JTable tablaReservas = new JTable(modeloTabla);
		JScrollPane scrollPane = new JScrollPane(tablaReservas);

		panel.add(scrollPane, BorderLayout.CENTER);
		panel.add(panelBotones, BorderLayout.SOUTH);

		getContentPane().add(panel);

		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
				MenuSocio menu = new MenuSocio();
				menu.setVisible(true);
				dispose();
			}
		});

		pack();
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla

	}

//	public static void main(String[] args) {
//		SwingUtilities.invokeLater(new Runnable() {
//			public void run() {
//				VentanaConsultarReservas ventana = new VentanaConsultarReservas();
//				ventana.setVisible(true);
//			}
//		});
//	}
}
