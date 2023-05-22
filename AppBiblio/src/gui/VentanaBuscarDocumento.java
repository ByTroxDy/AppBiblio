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
import java.util.Calendar;
import java.util.Date;

public class VentanaBuscarDocumento extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTextField txtTituloDocumento;
	private JComboBox<String> cmbTipoDocumento;
	private JButton btnVolver;
	private JButton btnBuscar;

	private String titulo;
	private String tipoDocumento;
	private boolean disponible;

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
			DefaultTableModel modeloTabla = new DefaultTableModel() {
				private static final long serialVersionUID = 1L;

				@Override
	            public boolean isCellEditable(int row, int column) {
	                return false; // Desactivar la edición de todas las celdas
	            }
	        };
			
			modeloTabla.addColumn("ISBN");
			modeloTabla.addColumn("Titulo");
			modeloTabla.addColumn("Tipo");
			modeloTabla.addColumn("Estado");

			// Llenar el modelo de tabla con los datos de los documentos
			for (Documento documento : documentos) {
				Object[] fila = new Object[4];
				fila[0] = documento.getISBN();
				fila[1] = documento.getTitulo();
				fila[2] = documento.getTipo();
				fila[3] = documento.getReplicas();
				
				if (documento.getReplicas() == 0) {
	                fila[3] = "No disponible";
	                disponible = false;
	            } else {
	            	fila[3] = "Disponible";
	            }

				modeloTabla.addRow(fila);
			}

			JTable tablaDocumentos = new JTable(modeloTabla);
			JScrollPane scrollPane = new JScrollPane(tablaDocumentos);

			JButton btnVolverBuscar = new JButton("Volver a Buscar");
			JButton btnPedir = new JButton("Pedir");
			JButton btnReservar = new JButton("Reservar Documento");

			// Configurar el panel de botones
			JPanel panelBotones = new JPanel();
			panelBotones.setLayout(new FlowLayout());
			panelBotones.add(btnVolverBuscar);
			panelBotones.add(btnPedir);
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
			
			btnPedir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ex) {
					int filaSeleccionada = tablaDocumentos.getSelectedRow();
					if (filaSeleccionada == -1) {
						JOptionPane.showMessageDialog(ventanaResultados, "Selecciona un documento de la tabla.",
								"Error", JOptionPane.ERROR_MESSAGE);
					} else if (!disponible) {
							JOptionPane.showMessageDialog(ventanaResultados, "Este documento no esta disponible",
									"Aviso", JOptionPane.INFORMATION_MESSAGE);
					} else {
						int isbn = (int) tablaDocumentos.getValueAt(filaSeleccionada, 0);
						// Lógica para pedir préstamo del documento con el ISBN seleccionado
						reservarDocumento(isbn);
						ventanaResultados.dispose();
					}
				}
			});

			btnReservar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ex) {
					int filaSeleccionada = tablaDocumentos.getSelectedRow();
					if (filaSeleccionada == -1) {
						JOptionPane.showMessageDialog(ventanaResultados, "Selecciona un documento de la tabla.",
								"Error", JOptionPane.ERROR_MESSAGE);
					} else {
						int isbn = (int) tablaDocumentos.getValueAt(filaSeleccionada, 0);
						// Lógica para pedir préstamo del documento con el ISBN seleccionado
						reservarDocumento(isbn);
						ventanaResultados.dispose();
					}
				}
			});
		}
	}

	public ArrayList<Documento> consultarDocumentosPorNombre(String titulo) {
		ArrayList<Documento> documentos = new ArrayList<>();

		try (Connection conn = ConexionDB.getConnection()) {

			String query = "SELECT * FROM documentos WHERE titulo LIKE ?";

			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, "%" + titulo + "%");
			ResultSet resultSet = statement.executeQuery();

			// Recorrer los resultados y crear objetos Documento
			while (resultSet.next()) {
				int isbn = resultSet.getInt("isbn");
				String nombre = resultSet.getString("titulo");
				String type = resultSet.getString("tipo");
				int replicas = resultSet.getInt("replicas");

				Documento documento = new Documento(isbn, nombre, type, replicas);
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

			String query = "SELECT * FROM documentos WHERE tipo = ?";

			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, tipo);
			ResultSet resultSet = statement.executeQuery();

			// Recorrer los resultados y crear objetos Documento
			while (resultSet.next()) {
				int isbn = resultSet.getInt("isbn");
				String nombre = resultSet.getString("titulo");
				String type = resultSet.getString("tipo");
				int replicas = resultSet.getInt("replicas");

				Documento documento = new Documento(isbn, nombre, type, replicas);
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

			String query = "SELECT * FROM documentos WHERE titulo LIKE ? AND tipo = ?";

			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, "%" + titulo + "%");
			statement.setString(2, tipo);
			ResultSet resultSet = statement.executeQuery();

			// Recorrer los resultados y crear objetos Documento
			while (resultSet.next()) {
				int isbn = resultSet.getInt("isbn");
				String nombre = resultSet.getString("titulo");
				String type = resultSet.getString("tipo");
				int replicas = resultSet.getInt("replicas");

				Documento documento = new Documento(isbn, nombre, type, replicas);
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
				int isbn = resultSet.getInt("isbn");
				String nombre = resultSet.getString("titulo");
				String type = resultSet.getString("tipo");
				int replicas = resultSet.getInt("replicas");

				Documento documento = new Documento(isbn, nombre, type, replicas);
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

	public void reservarDocumento(int isbn) {
		
		String usuario = VentanaInicioSesion.getNombreUsuario();
		
	    // Verificar límite de préstamos del usuario
	    try (Connection conn = ConexionDB.getConnection()) {
	    	
	        String countQuery = "SELECT COUNT(*) FROM prestamos WHERE usuario = ?";
	        
	        PreparedStatement countStatement = conn.prepareStatement(countQuery);
	        countStatement.setString(1, usuario);
	        ResultSet countResult = countStatement.executeQuery();
	        countResult.next();
	        int cantidadPrestamos = countResult.getInt(1);
	        countResult.close();
	        countStatement.close();

	        if (cantidadPrestamos >= 5) {
	            System.out.println("Has alcanzado el límite de préstamos permitidos.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return;
	    } finally {
	        try {
	            ConexionDB.closeConnection();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    // Verificar si el usuario ya tiene prestado el documento seleccionado
	    try (Connection conn = ConexionDB.getConnection()) {
	    	
	        String checkQuery = "SELECT COUNT(*) FROM prestamos WHERE usuario = ? AND isbn = ?";
	        
	        PreparedStatement checkStatement = conn.prepareStatement(checkQuery);
	        checkStatement.setString(1, usuario);
	        checkStatement.setInt(2, isbn);
	        ResultSet checkResult = checkStatement.executeQuery();
	        checkResult.next();
	        int cantidadPrestamosDocumento = checkResult.getInt(1);
	        checkResult.close();
	        checkStatement.close();

	        if (cantidadPrestamosDocumento > 0) {
	            System.out.println("Ya has pedido prestado este documento.");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return;
	    } finally {
	        try {
	            ConexionDB.closeConnection();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    // Calcular fecha de devolución (15 días desde la fecha actual)
	    Calendar calendar = Calendar.getInstance();
	    calendar.add(Calendar.DATE, 15);
	    Date fechaDevolucion = calendar.getTime();

	    // Insertar nuevo préstamo en la tabla 'prestamos'
	    try (Connection conn = ConexionDB.getConnection()) {
	    	
	        String query = "INSERT INTO prestamos (usuario, isbn, fecha_prestamo, fecha_devolucion) VALUES (?, ?, ?, ?)";
	        
	        PreparedStatement statement = conn.prepareStatement(query);
	        statement.setString(1, usuario);
	        statement.setInt(2, isbn);
	        statement.setDate(3, new java.sql.Date(new Date().getTime())); // Fecha actual
	        statement.setDate(4, new java.sql.Date(fechaDevolucion.getTime())); // Fecha de devolución calculada
	        statement.executeUpdate();
	        statement.close();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return;
	    } finally {
	        try {
	            ConexionDB.closeConnection();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    // Actualizar la tabla 'replicas'
	    try (Connection conn = ConexionDB.getConnection()) {
	    	
	        String updateQuery = "UPDATE documentos SET replicas = replicas - 1 WHERE isbn = ?";
	        
	        PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
	        updateStatement.setInt(1, isbn);
	        updateStatement.executeUpdate();
	        updateStatement.close();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return;
	    } finally {
	        try {
	            ConexionDB.closeConnection();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    System.out.println("Préstamo realizado con éxito.");
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
