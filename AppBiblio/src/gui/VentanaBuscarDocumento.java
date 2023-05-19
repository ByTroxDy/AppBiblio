package gui;

import app.Documento;
import app.ConexionDB;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class VentanaBuscarDocumento extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTextField txtTituloDocumento;
	private JComboBox<String> cmbTipoDocumento;
	private JButton btnVolver;
	private JButton btnBuscar;

	private String titulo;
	private String tipoDocumento;

	public VentanaBuscarDocumento() {
		setTitle("Buscar Documento");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2, 10, 10));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));

		JLabel lblTitulo = new JLabel("Título");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		txtTituloDocumento = new JTextField(20);
		txtTituloDocumento.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblTipoDocumento = new JLabel("Tipo de Documento");
		lblTipoDocumento.setHorizontalAlignment(SwingConstants.CENTER);
		String[] tiposDocumento = { "Todos", "Pelicula", "Musica", "Libro", "Documentales" };
		cmbTipoDocumento = new JComboBox<>(tiposDocumento);

		btnVolver = new JButton("Volver");
		btnBuscar = new JButton("Buscar");

		panel.add(lblTitulo);
		panel.add(txtTituloDocumento);
		panel.add(lblTipoDocumento);
		panel.add(cmbTipoDocumento);
		panel.add(btnVolver);
		panel.add(btnBuscar);

		getContentPane().add(panel);

		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
				MenuSocio menu = new MenuSocio();
				menu.setVisible(true);
				dispose();
			}
		});

		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ex) {
				titulo = txtTituloDocumento.getText();
				tipoDocumento = (String) cmbTipoDocumento.getSelectedItem();

				consultarDocumentos(titulo, tipoDocumento);
				dispose();
			}
		});

		pack();
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
	}

	public String getTitulo() {
		return titulo;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void consultarDocumentos(String titulo, String tipo) {
		ArrayList<Documento> documentos;

		if (!titulo.isEmpty() && tipo.equals("Todos")) {
			documentos = consultarDocumentosPorNombre(titulo);
		} else if (titulo.isEmpty() && !tipo.equals("Todos")) {
			documentos = consultarDocumentosPorTipo(tipo);
		} else if (!titulo.isEmpty() && !tipo.equals("Todos")) {
			documentos = consultarDocumentosPorNombreYTipo(titulo, tipo);
		} else {
			documentos = consultarTodosDocumentos();
		}

		if (documentos.isEmpty()) {
			JOptionPane.showMessageDialog(this,
					"No se encontraron documentos que coincidan con los criterios de búsqueda.", "Aviso",
					JOptionPane.INFORMATION_MESSAGE);
		} else {

			JFrame ventanaResultados = new JFrame("Resultados de la consulta");

			// Crear un modelo de tabla para los documentos
			DefaultTableModel modeloTabla = new DefaultTableModel();
			modeloTabla.addColumn("ID");
			modeloTabla.addColumn("Titulo");
			modeloTabla.addColumn("Autor");

			// Llenar el modelo de tabla con los datos de los documentos
			for (Documento documento : documentos) {
				Object[] fila = new Object[3];
				fila[0] = documento.getId();
				fila[1] = documento.getNombre();
				fila[2] = documento.getAutor();

				modeloTabla.addRow(fila);
			}

			JTable tablaDocumentos = new JTable(modeloTabla);
			JScrollPane scrollPane = new JScrollPane(tablaDocumentos);

			JButton btnVolverBuscar = new JButton("Volver a Buscar");
			JButton btnPedirPrestamo = new JButton("Pedir Préstamo");
			JButton btnReservar = new JButton("Reservar Documento");

			// Configurar el panel de botones
			JPanel panelBotones = new JPanel();
			panelBotones.setLayout(new FlowLayout());
			panelBotones.add(btnVolverBuscar);
			panelBotones.add(btnPedirPrestamo);
			panelBotones.add(btnReservar);

			// Configurar el panel principal
			JPanel panelPrincipal = new JPanel();
			panelPrincipal.setLayout(new BorderLayout());
			panelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
			panelPrincipal.add(scrollPane, BorderLayout.CENTER);
			panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

			ventanaResultados.getContentPane().add(panelPrincipal);
			ventanaResultados.pack();
			ventanaResultados.setLocationRelativeTo(null); // Centrar la ventana en la pantalla
			ventanaResultados.setVisible(true);

			btnVolverBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ex) {
					ventanaResultados.dispose();
					VentanaBuscarDocumento ventanaBuscarDocumento = new VentanaBuscarDocumento();
					ventanaBuscarDocumento.setVisible(true);
				}
			});

			btnPedirPrestamo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ex) {
//					int filaSeleccionada = tablaDocumentos.getSelectedRow();
//					if (filaSeleccionada == -1) {
//						JOptionPane.showMessageDialog(ventanaResultados, "Selecciona un documento de la tabla.",
//								"Error", JOptionPane.ERROR_MESSAGE);
//					} else {
//						int idDocumento = (int) tablaDocumentos.getValueAt(filaSeleccionada, 0);
//						// Lógica para pedir préstamo del documento con el ID seleccionado
//						pedirPrestamoDocumento(idDocumento);
//						ventanaResultados.dispose();
//					}
					
					// Lógica para reservar un documento y mostrar el resultado
					JOptionPane.showMessageDialog(VentanaBuscarDocumento.this, "Funcionalidad en desarrollo", "En construcción",
							JOptionPane.INFORMATION_MESSAGE);
				}
			});

			btnReservar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ex) {
//					int filaSeleccionada = tablaDocumentos.getSelectedRow();
//					if (filaSeleccionada == -1) {
//						JOptionPane.showMessageDialog(ventanaResultados, "Selecciona un documento de la tabla.",
//								"Error", JOptionPane.ERROR_MESSAGE);
//					} else {
//						int idDocumento = (int) tablaDocumentos.getValueAt(filaSeleccionada, 0);
//						// Lógica para reservar el documento con el ID seleccionado
//						reservarDocumento(idDocumento);
//						ventanaResultados.dispose();
//					}
					
					// Lógica para reservar un documento y mostrar el resultado
					JOptionPane.showMessageDialog(VentanaBuscarDocumento.this, "Funcionalidad en desarrollo", "En construcción",
							JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}
	}

	public ArrayList<Documento> consultarDocumentosPorNombre(String titulo) {
		ArrayList<Documento> documentos = new ArrayList<>();

		try (Connection conn = ConexionDB.getConnection()) {

			String query = "SELECT * FROM documentos WHERE nombre_documento LIKE ?";

			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, "%" + titulo + "%");
			ResultSet resultSet = statement.executeQuery();

			// Recorrer los resultados y crear objetos Documento
			while (resultSet.next()) {
				int id = resultSet.getInt("id_documento");
				String nombre = resultSet.getString("nombre_documento");
				String autor = resultSet.getString("autor");
				Date fechaPublicacion = resultSet.getDate("fecha_publicacion");

				Documento documento = new Documento(id, nombre, autor, fechaPublicacion);
				documentos.add(documento);
			}

			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
        	try {
				ConexionDB.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }

		return documentos;
	}

	public ArrayList<Documento> consultarDocumentosPorTipo(String tipo) {
		ArrayList<Documento> documentos = new ArrayList<>();

		try (Connection conn = ConexionDB.getConnection()) {

			String query = "SELECT * FROM documentos WHERE tipo_documento = ?";

			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, tipo);
			ResultSet resultSet = statement.executeQuery();

			// Recorrer los resultados y crear objetos Documento
			while (resultSet.next()) {
				int id = resultSet.getInt("id_documento");
				String nombre = resultSet.getString("nombre_documento");
				String autor = resultSet.getString("autor");
				Date fechaPublicacion = resultSet.getDate("fecha_publicacion");

				Documento documento = new Documento(id, nombre, autor, fechaPublicacion);
				documentos.add(documento);
			}

			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
        	try {
				ConexionDB.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }

		return documentos;
	}

	public ArrayList<Documento> consultarDocumentosPorNombreYTipo(String titulo, String tipo) {
		ArrayList<Documento> documentos = new ArrayList<>();

		try (Connection conn = ConexionDB.getConnection()) {

			String query = "SELECT * FROM documentos WHERE nombre_documento LIKE ? AND tipo_documento = ?";

			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, "%" + titulo + "%");
			statement.setString(2, tipo);
			ResultSet resultSet = statement.executeQuery();

			// Recorrer los resultados y crear objetos Documento
			while (resultSet.next()) {
				int id = resultSet.getInt("id_documento");
				String nombre = resultSet.getString("nombre_documento");
				String autor = resultSet.getString("autor");
				Date fechaPublicacion = resultSet.getDate("fecha_publicacion");

				Documento documento = new Documento(id, nombre, autor, fechaPublicacion);
				documentos.add(documento);
			}

			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
        	try {
				ConexionDB.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }

		return documentos;
	}

	public ArrayList<Documento> consultarTodosDocumentos() {
		ArrayList<Documento> documentos = new ArrayList<>();

		try (Connection conn = ConexionDB.getConnection()) {

			String query = "SELECT * FROM documentos";

			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			// Recorrer los resultados y crear objetos Documento
			while (resultSet.next()) {
				int id = resultSet.getInt("id_documento");
				String nombre = resultSet.getString("nombre_documento");
				String autor = resultSet.getString("autor");
				Date fechaPublicacion = resultSet.getDate("fecha_publicacion");

				Documento documento = new Documento(id, nombre, autor, fechaPublicacion);
				documentos.add(documento);
			}

			resultSet.close();
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
        	try {
				ConexionDB.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }

		return documentos;
	}

	public void pedirPrestamoDocumento(int idDocumento) {
		// Lógica para pedir préstamo del documento con el ID proporcionado
		// Aquí puedes agregar tu código para guardar la información del préstamo en la
		// base de datos
		JOptionPane.showMessageDialog(null, "Préstamo solicitado para el documento con ID: " + idDocumento, "Préstamo",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void reservarDocumento(int idDocumento) {
		// Lógica para reservar el documento con el ID proporcionado
		// Aquí puedes agregar tu código para guardar la información de la reserva en la
		// base de datos
		JOptionPane.showMessageDialog(null, "Reserva realizada para el documento con ID: " + idDocumento, "Reserva",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				VentanaBuscarDocumento ventana = new VentanaBuscarDocumento();
				ventana.setVisible(true);
			}
		});
	}
}

