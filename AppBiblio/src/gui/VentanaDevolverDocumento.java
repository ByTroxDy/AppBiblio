package gui;

import app.Prestamos;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaDevolverDocumento extends JDialog {
	private static final long serialVersionUID = 1L;
	private JButton btnVolver, btnDevolver;

	public VentanaDevolverDocumento(ArrayList<Prestamos> prestamos) {
		setTitle("Devolver Documento");
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
		modeloTabla.addColumn("Fecha Prestamo");
		modeloTabla.addColumn("Fecha Devolucion");
		modeloTabla.addColumn("Dias Retardo");

		// Llenar el modelo de tabla con los datos de los documentos
		for (Prestamos prestamo : prestamos) {
			Object[] fila = new Object[5];
			fila[0] = prestamo.getISBN();
			fila[1] = prestamo.getUsuario();
			fila[2] = prestamo.getFechaPrestamo();
			fila[3] = prestamo.getFechaDevolucion();
			fila[4] = prestamo.getDiasRetardo();

			modeloTabla.addRow(fila);
		}

		btnVolver = new JButton("Volver");
		btnDevolver = new JButton("Devolver");

		// Configurar el panel de botones
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout());
		panelBotones.add(btnVolver);
		panelBotones.add(btnDevolver);

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
		
		btnDevolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
				JOptionPane.showMessageDialog(panel, "Funcionalidad en desarrollo", "En construcción",
						JOptionPane.INFORMATION_MESSAGE);
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
