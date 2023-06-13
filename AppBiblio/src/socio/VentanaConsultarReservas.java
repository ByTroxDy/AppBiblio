package socio;

import app.Reservas;
import db.DocumentoMaxDB;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class VentanaConsultarReservas extends JDialog {
	private JButton btnVolver, btnCancelar;
	
	private int filaSeleccionada, isbn;
	static String usuario;

	public VentanaConsultarReservas(ArrayList<Reservas> reservas) {
		setTitle("Biblioteca App - Resultats de la consulta");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaConsultarReservas.class.getResource("/img/icono32.png")));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);

		// Configurar el panel
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));

		// Crear un modelo de tabla para las reservas
		DefaultTableModel modeloTabla = new DefaultTableModel();

		modeloTabla.addColumn("ISBN");
		modeloTabla.addColumn("Data Reserva");
		modeloTabla.addColumn("Dies Pendents");

		// Llenar el modelo de tabla con los datos de los documentos
		for (Reservas reserva : reservas) {
			Object[] fila = new Object[3];
			fila[0] = reserva.getISBN();
			fila[1] = reserva.getFechaReserva();
			fila[2] = reserva.getDiasPendientes();

			modeloTabla.addRow(fila);
		}
		
		JTable tablaReservas = new JTable(modeloTabla);
		tablaReservas.setDefaultEditor(Object.class, null); // Desactivar la edición de las celdas

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		tablaReservas.setDefaultRenderer(Object.class, centerRenderer); // Centrar el texto en las celdas

		tablaReservas.getTableHeader().setEnabled(false); // Desactivar arrastrar y soltar
		tablaReservas.getTableHeader().setResizingAllowed(false); // Desactivar la modificación de las columnas

		JScrollPane scrollPane = new JScrollPane(tablaReservas);

		btnVolver = new JButton("Enrere");
		btnCancelar = new JButton("Cancel·lar");

		// Configurar el panel de botones
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout());
		panelBotones.add(btnVolver);
		panelBotones.add(btnCancelar);

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
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
				DocumentoMaxDB docDB = new DocumentoMaxDB();
				
				filaSeleccionada = tablaReservas.getSelectedRow();
				if (filaSeleccionada == -1) {
					JOptionPane.showMessageDialog(panel, "Seleccioneu un document de la taula.",
							"Error", JOptionPane.ERROR_MESSAGE);
				} else {
					isbn = (int) tablaReservas.getValueAt(filaSeleccionada, 0);
					if (docDB.deleteReserva(usuario, isbn)) {
						JOptionPane.showMessageDialog(panel, "S'ha cancel·lat la reserva correctament", "Reserva",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});

		pack();
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla

	}
}
