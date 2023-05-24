package db;

import app.Documento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DocumentoDB {
	int isbn, replicas;
	String nombre, autor;
	
	public ArrayList<Documento> consultarDocumentosPorNombre(String titulo) {
		ArrayList<Documento> documentos = new ArrayList<>();

		try (Connection conn = ConexionDB.getConnection()) {

			String query = "SELECT * FROM documentos WHERE titulo LIKE ?";

			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, "%" + titulo + "%");
			ResultSet resultSet = statement.executeQuery();

			// Recorrer los resultados y crear objetos Documento
			while (resultSet.next()) {
				isbn = resultSet.getInt("isbn");
				nombre = resultSet.getString("titulo");
				autor = resultSet.getString("autor");
				replicas = resultSet.getInt("replicas");

				Documento documento = new Documento(isbn, nombre, autor, replicas);
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

	public ArrayList<Documento> consultarDocumentosPorAutor(String autor2) {
		ArrayList<Documento> documentos = new ArrayList<>();

		try (Connection conn = ConexionDB.getConnection()) {

			String query = "SELECT * FROM documentos WHERE autor LIKE ?";

			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, "%" + autor2 + "%");
			ResultSet resultSet = statement.executeQuery();

			// Recorrer los resultados y crear objetos Documento
			while (resultSet.next()) {
				isbn = resultSet.getInt("isbn");
				nombre = resultSet.getString("titulo");
				autor = resultSet.getString("autor");
				replicas = resultSet.getInt("replicas");

				Documento documento = new Documento(isbn, nombre, autor, replicas);
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

	public ArrayList<Documento> consultarDocumentosPorNombreYAutor(String titulo, String autor2) {
		ArrayList<Documento> documentos = new ArrayList<>();

		try (Connection conn = ConexionDB.getConnection()) {

			String query = "SELECT * FROM documentos WHERE titulo LIKE ? AND autor LIKE ?";

			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, "%" + titulo + "%");
			statement.setString(2, "%" + autor2 + "%");
			ResultSet resultSet = statement.executeQuery();

			// Recorrer los resultados y crear objetos Documento
			while (resultSet.next()) {
				isbn = resultSet.getInt("isbn");
				nombre = resultSet.getString("titulo");
				autor = resultSet.getString("autor");
				replicas = resultSet.getInt("replicas");

				Documento documento = new Documento(isbn, nombre, autor, replicas);
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
				isbn = resultSet.getInt("isbn");
				nombre = resultSet.getString("titulo");
				autor = resultSet.getString("autor");
				replicas = resultSet.getInt("replicas");

				Documento documento = new Documento(isbn, nombre, autor, replicas);
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
	
	public void prestamoDocumento(String usuario, int isbn) {
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
	
	public void reservarDocumento(String usuario, int isbn) {
	    // Verificar si el usuario ya tiene reservado el documento seleccionado
	    try (Connection conn = ConexionDB.getConnection()) {
	    	
	        String checkQuery = "SELECT COUNT(*) FROM reservas WHERE usuario = ? AND isbn = ?";
	        
	        PreparedStatement checkStatement = conn.prepareStatement(checkQuery);
	        checkStatement.setString(1, usuario);
	        checkStatement.setInt(2, isbn);
	        ResultSet checkResult = checkStatement.executeQuery();
	        checkResult.next();
	        int cantidadReservasDocumento = checkResult.getInt(1);
	        checkResult.close();
	        checkStatement.close();

	        if (cantidadReservasDocumento > 0) {
	            System.out.println("Ya has reservado este documento.");
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

	    // Insertar nueva reserva en la tabla 'reservas'
	    try (Connection conn = ConexionDB.getConnection()) {
	    	
	        String query = "INSERT INTO reservas (usuario, isbn, fecha_reserva) VALUES (?, ?, ?)";
	        
	        PreparedStatement statement = conn.prepareStatement(query);
	        statement.setString(1, usuario);
	        statement.setInt(2, isbn);
	        statement.setDate(3, new java.sql.Date(new Date().getTime())); // Fecha actual
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

	    System.out.println("Reserva realizada con éxito.");
	}

	public void insertarDocumento(Documento documento) {
		// Insertar nuevo documento en la tabla 'documentos'
	    try (Connection conn = ConexionDB.getConnection()) {
	    	
	        String query = "INSERT INTO documentos (isbn, titulo , autor) VALUES (?, ?, ?)";
	        
	        PreparedStatement statement = conn.prepareStatement(query);
	        statement.setInt(1, documento.getISBN());
	        statement.setString(2, documento.getTitulo());
	        statement.setString(3, documento.getAutor());
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
	}
//	public void insertarPelicula(Documento documento) {
//		// Insertar nueva pelicula en la tabla 'peliculas'
//	    try (Connection conn = ConexionDB.getConnection()) {
//	    	
//	        String query = "INSERT INTO documentos (isbn, titulo , autor) VALUES (?, ?, ?)";
//	        
//	        PreparedStatement statement = conn.prepareStatement(query);
//	        statement.setInt(1, Pelicula.getISBN());
//	        statement.setString(2, documento.getTitulo());
//	        statement.setString(3, documento.getAutor());
//	        statement.executeUpdate();
//	        statement.close();
//	        
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	        return;
//	    } finally {
//	        try {
//	            ConexionDB.closeConnection();
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        }
//	    }
//	}
}
