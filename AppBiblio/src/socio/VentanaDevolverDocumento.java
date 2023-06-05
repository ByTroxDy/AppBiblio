package socio;

import app.Prestamos;
import db.DocumentoMaxDB;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaDevolverDocumento extends JDialog {
	private static final long serialVersionUID = 1L;
	private JButton btnVolver, btnDevolver;
	
	private int filaSeleccionada, isbn;
	static String usuario;

	public VentanaDevolverDocumento(ArrayList<Prestamos> prestamos) {
		setTitle("Devolver Documento");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);

		// Configurar el panel
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));

		// Crear un modelo de tabla para las reservas
		DefaultTableModel modeloTabla = new DefaultTableModel();

		modeloTabla.addColumn("ISBN");
		modeloTabla.addColumn("Fecha Prestamo");
		modeloTabla.addColumn("Fecha Devolucion");
		modeloTabla.addColumn("Dias Retardo");

		// Llenar el modelo de tabla con los datos de los documentos
		for (Prestamos prestamo : prestamos) {
			Object[] fila = new Object[4];
			fila[0] = prestamo.getISBN();
			fila[1] = prestamo.getFechaPrestamo();
			fila[2] = prestamo.getFechaDevolucion();
			fila[3] = prestamo.getDiasRetardo();

			modeloTabla.addRow(fila);
		}
		
		JTable tablaPrestamos = new JTable(modeloTabla);
		tablaPrestamos.setDefaultEditor(Object.class, null); // Desactivar la edición de las celdas

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		tablaPrestamos.setDefaultRenderer(Object.class, centerRenderer); // Centrar el texto en las celdas

		tablaPrestamos.getTableHeader().setEnabled(false); // Desactivar arrastrar y soltar
		tablaPrestamos.getTableHeader().setResizingAllowed(false); // Desactivar la modificación de las columnas

		JScrollPane scrollPane = new JScrollPane(tablaPrestamos);

		btnVolver = new JButton("Volver");
		btnDevolver = new JButton("Devolver");

		// Configurar el panel de botones
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout());
		panelBotones.add(btnVolver);
		panelBotones.add(btnDevolver);

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
				DocumentoMaxDB docDB = new DocumentoMaxDB();
				
				filaSeleccionada = tablaPrestamos.getSelectedRow();
				if (filaSeleccionada == -1) {
					JOptionPane.showMessageDialog(panel, "Selecciona un documento de la tabla.",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else {
					isbn = (int) tablaPrestamos.getValueAt(filaSeleccionada, 0);
					if (docDB.deletePrestamo(usuario, isbn)) {
						JOptionPane.showMessageDialog(panel, "La devolucion ha sido efectuada.", "Devolucion",
								JOptionPane.INFORMATION_MESSAGE);
						VentanaAgregarComentario.isbn = isbn;
						VentanaAgregarComentario ventana = new VentanaAgregarComentario();
						ventana.setVisible(true);
						dispose();
					}
				}
				
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
