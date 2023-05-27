package gui;

import app.Documento;
import db.DocumentoDB;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VentanaConsultarDocumento extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTextField txtTitulo, txtAutor;
	private JButton btnVolver, btnBuscar;

	private int filaSeleccionada, isbn;
	private String titulo, autor, replicas;
	static String usuario;

	public VentanaConsultarDocumento() {
		setTitle("Buscar Documento");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2, 10, 10));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));

		JLabel lblTitulo = new JLabel("Título");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		txtTitulo = new JTextField(20);
		txtTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setHorizontalAlignment(SwingConstants.CENTER);
		txtAutor = new JTextField(20);
		txtAutor.setHorizontalAlignment(SwingConstants.CENTER);

		btnVolver = new JButton("Volver");
		btnBuscar = new JButton("Buscar");

		panel.add(lblTitulo);
		panel.add(txtTitulo);
		panel.add(lblAutor);
		panel.add(txtAutor);
		panel.add(btnVolver);
		panel.add(btnBuscar);

		getContentPane().add(panel);

		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
				if (usuario == null) {
					VentanaInicioSesion app = new VentanaInicioSesion();
					app.setVisible(true);
					dispose();
				} else {
					MenuSocio menu = new MenuSocio();
					menu.setVisible(true);
					dispose();
				}
			}
		});

		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
				titulo = txtTitulo.getText();
				autor = txtAutor.getText();

				consultarDocumentos(titulo, autor);
				dispose();
			}
		});

		pack();
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
	}
	
	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void consultarDocumentos(String titulo, String autor) {
		ArrayList<Documento> documentos;
		DocumentoDB docDB = new DocumentoDB();

		if (!titulo.isEmpty() && autor.isEmpty()) {
			documentos = docDB.consultarDocumentosPorNombre(titulo);
		} else if (titulo.isEmpty() && !autor.isEmpty()) {
			documentos = docDB.consultarDocumentosPorAutor(autor);
		} else if (!titulo.isEmpty() && !autor.isEmpty()) {
			documentos = docDB.consultarDocumentosPorNombreYAutor(titulo, autor);
		} else {
			documentos = docDB.consultarTodosDocumentos();
		}

		if (documentos.isEmpty()) {
			JOptionPane.showMessageDialog(this,
					"No se encontraron documentos que coincidan con los criterios de búsqueda.", "Aviso",
					JOptionPane.INFORMATION_MESSAGE);
		} else {

			JFrame ventanaResultados = new JFrame("Resultados de la consulta");

			// Crear un modelo de tabla para los documentos
			DefaultTableModel modeloTabla = new DefaultTableModel() {
				private static final long serialVersionUID = 1L;

				@Override
	            public boolean isCellEditable(int row, int column) {
	                return false; // Desactivar la edición de todas las celdas
	            }
	        };
			
			modeloTabla.addColumn("ISBN");
			modeloTabla.addColumn("Titulo");
			modeloTabla.addColumn("Autor");
			modeloTabla.addColumn("Estado");

			// Llenar el modelo de tabla con los datos de los documentos
			for (Documento documento : documentos) {
				Object[] fila = new Object[4];
				fila[0] = documento.getISBN();
				fila[1] = documento.getTitulo();
				fila[2] = documento.getAutor();
				fila[3] = documento.getReplicas();
				
				if (documento.getReplicas() != 0) {
	                fila[3] = "Libre";
	            } else {
	            	fila[3] = "Reservado";
	            }

				modeloTabla.addRow(fila);
			}

			JTable tablaDocumentos = new JTable(modeloTabla);
			JScrollPane scrollPane = new JScrollPane(tablaDocumentos);

			JButton btnVolverBuscar = new JButton("Volver a Buscar");
			JButton btnPedirReserva = new JButton("Pedir Reserva");

			// Configurar el panel de botones
			JPanel panelBotones = new JPanel();
			panelBotones.setLayout(new FlowLayout());
			panelBotones.add(btnVolverBuscar);
			panelBotones.add(btnPedirReserva);

			// Configurar el panel principal
			JPanel panelPrincipal = new JPanel();
			panelPrincipal.setLayout(new BorderLayout());
			panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
			panelPrincipal.add(scrollPane, BorderLayout.CENTER);
			panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

			ventanaResultados.getContentPane().add(panelPrincipal);
			ventanaResultados.pack();
			ventanaResultados.setLocationRelativeTo(null);
			ventanaResultados.setVisible(true);
			
			if (usuario == null) {
				btnPedirReserva.setVisible(false);
			}

			btnVolverBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ex) {
					VentanaConsultarDocumento ventana = new VentanaConsultarDocumento();
					ventana.setVisible(true);
					ventanaResultados.dispose();
				}
			});
			
			btnPedirReserva.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ex) {
					filaSeleccionada = tablaDocumentos.getSelectedRow();
					isbn = (int) tablaDocumentos.getValueAt(filaSeleccionada, 0);
					replicas = (String) tablaDocumentos.getValueAt(filaSeleccionada, 3);
					
					if (filaSeleccionada == -1) {
						JOptionPane.showMessageDialog(ventanaResultados, "Selecciona un documento de la tabla.",
								"Error", JOptionPane.ERROR_MESSAGE);
					} else if (replicas == "Libre") {
						docDB.prestamoDocumento(usuario, isbn);
						ventanaResultados.dispose();
					} else {
						docDB.reservarDocumento(usuario, isbn);
						ventanaResultados.dispose();
					}
				}
			});
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				VentanaConsultarDocumento ventana = new VentanaConsultarDocumento();
				ventana.setVisible(true);
			}
		});
	}
}
