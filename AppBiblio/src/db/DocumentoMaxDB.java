package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

import app.Documental;
import app.Documento;
import app.Libro;
import app.Musica;
import app.Pelicula;
import app.Prestamos;
import app.Reservas;
import socio.VentanaConsultarDocumento;

public class DocumentoMaxDB {
	VentanaConsultarDocumento consultarDocumento = new VentanaConsultarDocumento();
	
	private int isbn, replicas, diasRetardo, diasPendientes;
	private String nombre, type;
	private Date fechaPrestamo, fechaDevolucion, fechaReserva;

	private Connection conn;

	public DocumentoMaxDB() {
		try {
			conn = ConexionDB.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Documento> consultarDocumentosPorNombre(String titulo) {
	    ArrayList<Documento> documentos = new ArrayList<>();
		String query = "SELECT * FROM documentos WHERE titulo LIKE ? AND fecha_baja IS NULL";

		try (PreparedStatement statement = conn.prepareStatement(query)) {

			statement.setString(1, "%" + titulo + "%");
			ResultSet resultSet = statement.executeQuery();

			// Recorrer los resultados y crear objetos Documento
			while (resultSet.next()) {
				isbn = resultSet.getInt("isbn");
				nombre = resultSet.getString("titulo");
				type = resultSet.getString("tipo");
				replicas = resultSet.getInt("replicas");

				Documento documento = new Documento(isbn, nombre, type, replicas);
				documentos.add(documento);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return documentos;
	}

	public ArrayList<Documento> consultarDocumentosPorTipo(String tipo) {
	    ArrayList<Documento> documentos = new ArrayList<>();
	    String query = "SELECT * FROM documentos WHERE tipo = ? AND fecha_baja IS NULL";
	    
	    try (PreparedStatement statement = conn.prepareStatement(query)) {
	        
	        statement.setString(1, tipo);
	        ResultSet resultSet = statement.executeQuery();

	        // Recorrer los resultados y crear objetos Documento
	        while (resultSet.next()) {
	        	isbn = resultSet.getInt("isbn");
				nombre = resultSet.getString("titulo");
				type = resultSet.getString("tipo");
				replicas = resultSet.getInt("replicas");

				Documento documento = new Documento(isbn, nombre, type, replicas);
				documentos.add(documento);
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return documentos;
	}

	public ArrayList<Documento> consultarDocumentosPorNombreYTipo(String titulo, String tipo) {
	    ArrayList<Documento> documentos = new ArrayList<>();
	    String query = "SELECT * FROM documentos WHERE titulo LIKE ? AND tipo = ? AND fecha_baja IS NULL";
	    
	    try (PreparedStatement statement = conn.prepareStatement(query)) {
	        
	        statement.setString(1, "%" + titulo + "%");
	        statement.setString(2, tipo);
	        ResultSet resultSet = statement.executeQuery();

	        // Recorrer los resultados y crear objetos Documento
	        while (resultSet.next()) {
	        	isbn = resultSet.getInt("isbn");
				nombre = resultSet.getString("titulo");
				type = resultSet.getString("tipo");
				replicas = resultSet.getInt("replicas");

				Documento documento = new Documento(isbn, nombre, type, replicas);
				documentos.add(documento);
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    return documentos;
	}

	public ArrayList<Documento> consultarTodosDocumentos() {
		ArrayList<Documento> documentos = new ArrayList<>();
		String query = "SELECT * FROM documentos WHERE fecha_baja IS NULL";

		try (PreparedStatement statement = conn.prepareStatement(query)) {

			ResultSet resultSet = statement.executeQuery();

			// Recorrer los resultados y crear objetos Documento
			while (resultSet.next()) {
				isbn = resultSet.getInt("isbn");
				nombre = resultSet.getString("titulo");
				type = resultSet.getString("tipo");
				replicas = resultSet.getInt("replicas");

				Documento documento = new Documento(isbn, nombre, type, replicas);
				documentos.add(documento);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return documentos;
	}

	
	public ArrayList<Prestamos> consultarMisPrestamos(String myUser) {
		ArrayList<Prestamos> prestamos = new ArrayList<>();
		String query = "SELECT * FROM prestamos WHERE usuario = ?";

		try (PreparedStatement statement = conn.prepareStatement(query)) {

			statement.setString(1, myUser);
			ResultSet resultSet = statement.executeQuery();

			// Recorrer los resultados y crear objetos Reserva
			while (resultSet.next()) {
				isbn = resultSet.getInt("isbn");
				fechaPrestamo = resultSet.getDate("fecha_prestamo");
				fechaDevolucion = resultSet.getDate("fecha_devolucion");
				diasRetardo = resultSet.getInt("dias_retardo");

				Prestamos prestamo = new Prestamos(isbn, fechaPrestamo, fechaDevolucion, diasRetardo);
				prestamos.add(prestamo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return prestamos;
	}

	public ArrayList<Reservas> consultarMisReservas(String myUser) {
		ArrayList<Reservas> reservas = new ArrayList<>();
		String query = "SELECT * FROM reservas WHERE usuario = ?";

		try (PreparedStatement statement = conn.prepareStatement(query)) {

			statement.setString(1, myUser);
			ResultSet resultSet = statement.executeQuery();

			// Recorrer los resultados y crear objetos Reserva
			while (resultSet.next()) {
				isbn = resultSet.getInt("isbn");
				fechaReserva = resultSet.getDate("fecha_reserva");
				diasPendientes = resultSet.getInt("dias_pendientes");

				Reservas reserva = new Reservas(isbn, fechaReserva, diasPendientes);
				reservas.add(reserva);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return reservas;
	}

	public void prestamoDocumento(String usuario, int isbn) {
		// Verificar límite de préstamos del usuario
		String countQuery = "SELECT COUNT(*) FROM prestamos WHERE usuario = ?";
		try (PreparedStatement countStatement = conn.prepareStatement(countQuery)) {

			countStatement.setString(1, usuario);
			ResultSet countResult = countStatement.executeQuery();
			countResult.next();
			int cantidadPrestamos = countResult.getInt(1);

			if (cantidadPrestamos >= 5) {
				JOptionPane.showMessageDialog(consultarDocumento, "Has aconseguit el límit de préstecs permesos.",
						"Avís", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}

		// Verificar si el usuario ya tiene prestado el documento seleccionado
		String checkQuery = "SELECT COUNT(*) FROM prestamos WHERE usuario = ? AND isbn = ?";
		try (PreparedStatement checkStatement = conn.prepareStatement(checkQuery)) {

			checkStatement.setString(1, usuario);
			checkStatement.setInt(2, isbn);
			ResultSet checkResult = checkStatement.executeQuery();
			checkResult.next();
			int cantidadPrestamosDocumento = checkResult.getInt(1);

			if (cantidadPrestamosDocumento > 0) {
				JOptionPane.showMessageDialog(consultarDocumento, "Ja has demanat aquest document.",
						"Avís", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}

		// Calcular fecha de devolución (15 días desde la fecha actual)
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, 15);
		Date fechaDevolucion = calendar.getTime();

		// Insertar nuevo préstamo en la tabla 'prestamos'
		String query = "INSERT INTO prestamos (usuario, isbn, fecha_prestamo, fecha_devolucion) VALUES (?, ?, ?, ?)";
		try (PreparedStatement statement = conn.prepareStatement(query)) {

			statement.setString(1, usuario);
			statement.setInt(2, isbn);
			statement.setDate(3, new java.sql.Date(new Date().getTime())); // Fecha actual
			statement.setDate(4, new java.sql.Date(fechaDevolucion.getTime())); // Fecha de devolución calculada
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}

		// Actualizar la tabla 'replicas'
		String updateQuery = "UPDATE documentos SET replicas = replicas - 1 WHERE isbn = ?";
		try (PreparedStatement updateStatement = conn.prepareStatement(updateQuery)) {

			updateStatement.setInt(1, isbn);
			updateStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}

		JOptionPane.showMessageDialog(consultarDocumento, "Préstec realitzat amb èxit.",
				"Préstec", JOptionPane.INFORMATION_MESSAGE);
	}

	public boolean reservarDocumento(String usuario, int isbn) {
		// Verificar si el usuario ya tiene reservado el documento seleccionado
		String checkQuery = "SELECT COUNT(*) FROM reservas WHERE usuario = ? AND isbn = ?";
		try (PreparedStatement checkStatement = conn.prepareStatement(checkQuery)) {

			checkStatement.setString(1, usuario);
			checkStatement.setInt(2, isbn);
			ResultSet checkResult = checkStatement.executeQuery();
			checkResult.next();
			int cantidadReservasDocumento = checkResult.getInt(1);

			if (cantidadReservasDocumento > 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		// Insertar nueva reserva en la tabla 'reservas'
		String query = "INSERT INTO reservas (usuario, isbn, fecha_reserva) VALUES (?, ?, ?)";
		try (PreparedStatement statement = conn.prepareStatement(query)) {

			statement.setString(1, usuario);
			statement.setInt(2, isbn);
			statement.setDate(3, new java.sql.Date(new Date().getTime())); // Fecha actual
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean deletePrestamo(String usuario, int isbn) {
		// Eliminar un préstamo en la tabla 'prestamos'
		String query = "DELETE FROM prestamos WHERE usuario = ? AND isbn = ?";
		try (PreparedStatement statement = conn.prepareStatement(query)) {

			statement.setString(1, usuario);
			statement.setInt(2, isbn);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		// Actualizar la tabla 'replicas'
		String updateQuery = "UPDATE documentos SET replicas = replicas + 1 WHERE isbn = ?";
		try (PreparedStatement updateStatement = conn.prepareStatement(updateQuery)) {

			updateStatement.setInt(1, isbn);
			updateStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public boolean deleteReserva(String usuario, int isbn) {
		// Eliminar un reserva en la tabla 'reservas'
		String query = "DELETE FROM reservas WHERE usuario = ? AND isbn = ?";
		try (PreparedStatement statement = conn.prepareStatement(query)) {

			statement.setString(1, usuario);
			statement.setInt(2, isbn);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
    public void guardarComentario(int isbn, String usuario, String comentario) {
    	// Insertar nuevo comentario en la tabla 'comentarios'
		String query = "INSERT INTO comentarios (isbn, usuario, optinion) VALUES (?, ?, ?)";
		try (PreparedStatement statement = conn.prepareStatement(query)) {

			statement.setInt(1, isbn);
			statement.setString(2, usuario);
			statement.setString(3, comentario);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
    }
    
	// OBTENER	
	public boolean comprobarIsbn(int isbn) {
		String query = ("SELECT COUNT(*) FROM documentos WHERE isbn = ?");
		try(PreparedStatement statement = conn.prepareStatement(query)){
			int varisbn;
			
			statement.setInt(1, isbn);			
			ResultSet checkResult = statement.executeQuery();
			checkResult.next();
			varisbn = checkResult.getInt(1);
			
			if (varisbn > 0) {
				return true;
			}//if
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}//try catch
	}//comprobarIsbn
	
	public String getTipo(int isbn) {
		String query = ("SELECT tipo FROM documentos WHERE isbn = ?");
		try  (PreparedStatement statement = conn.prepareStatement(query)) {
			
			statement.setInt(1, isbn);
			ResultSet checkResult = statement.executeQuery();
			checkResult.next();
			type = checkResult.getString(1);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}//try catch
		return type;
	}//getTipo
	
	// UPDATE
	public boolean checkDocumento(int isbn) {
		String checkQuery = "SELECT count(*) FROM documentos WHERE isbn = ?";
		try (PreparedStatement checkStatement = conn.prepareStatement(checkQuery)) {
			
			checkStatement.setInt(1, isbn);
			ResultSet checkResult = checkStatement.executeQuery();
			checkResult.next();
			int varisbn = checkResult.getInt(1);
			
			if (varisbn > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}//try catch
		return false;
	}//checkDocumento
	
	public boolean comprobarFechaBaja(int isbn) {
		String checkQuery = "SELECT fecha_baja FROM documentos WHERE isbn = ?";
		try (PreparedStatement checkStatement = conn.prepareStatement(checkQuery)) {
			
			checkStatement.setInt(1, isbn);
			ResultSet checkResult = checkStatement.executeQuery();
			checkResult.next();
			String varBaja = checkResult.getString(1);
			
			if (varBaja != null) {
				String updateQuery = "UPDATE documentos SET fecha_alta = ?, fecha_baja = NULL WHERE isbn = ?";
				try (PreparedStatement updateStatement = conn.prepareStatement(updateQuery)) {
					
					updateStatement.setDate(1, new java.sql.Date(new Date().getTime()));
					updateStatement.setInt(2, isbn);
					updateStatement.executeUpdate();
					
					return true;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean bajaDocumento(int isbn) {
		String query = ("UPDATE documentos SET fecha_baja = ? WHERE isbn = ?");
		try (PreparedStatement statement = conn.prepareStatement(query)) {
			
			statement.setDate(1, new java.sql.Date(new Date().getTime()));
			statement.setInt(2, isbn);
			statement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}//try catch
	}//bajaDocumento
	
	//INSERTS
	public boolean insertDocLib(Documento doc, Libro lib) {
		try {
			conn.setAutoCommit(false);
			insertarDocumento(doc);
			insertarLibro(lib);
			conn.commit();
			return true;
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				return false;
			}
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public boolean insertDocPel(Documento doc, Pelicula pel) {
		try {
			conn.setAutoCommit(false);
			insertarDocumento(doc);
			insertarPelicula(pel);
			conn.commit();
			return true;
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				return false;
			}
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public boolean insertDocDocl(Documento doc, Documental docl) {
		try {
			conn.setAutoCommit(false);
			insertarDocumento(doc);
			insertarDocumental(docl);
			conn.commit();
			return true;
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				return false;
			}
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public boolean insertDocMus(Documento doc, Musica mus) {
		try {
			conn.setAutoCommit(false);
			insertarDocumento(doc);
			insertarMusica(mus);
			conn.commit();
			return true;
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				return false;
			}
	        e.printStackTrace();
	        return false;
	    }
	}

	public void insertarDocumento(Documento documento) {
		String query = "INSERT INTO documentos (isbn, tipo, titulo, autor, replicas, biblioteca, fecha_alta) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement statement = conn.prepareStatement(query)) {
			
			statement.setInt(1, documento.getISBN());
			statement.setString(2, documento.getTipo());
			statement.setString(3, documento.getTitulo());
			statement.setString(4, documento.getAutor());
			statement.setInt(5, documento.getReplicas());
			statement.setString(6, documento.getBiblioteca());
			statement.setDate(7, new java.sql.Date(new Date().getTime()));
			statement.executeUpdate();
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}//try catch
	}//insertarDocumento

	public void insertarLibro(Libro libro) {
		String query = "INSERT INTO libros (isbn, editorial, npaginas, tematica) VALUES (?, ?, ?, ?)";
		try (PreparedStatement statement = conn.prepareStatement(query);) {

			statement.setInt(1, libro.getISBN());
			statement.setString(2, libro.getEditorial());
			statement.setInt(3, libro.getNumeroPaginas());
			statement.setString(4, libro.getTematica());
			statement.executeUpdate();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void insertarPelicula(Pelicula pelicula) {
		String query = "INSERT INTO peliculas (isbn, director, actores, premios, duracion, formato) VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement statement = conn.prepareStatement(query)) {

			statement.setInt(1, pelicula.getISBN());
			statement.setString(2, pelicula.getDirector());
			statement.setString(3, pelicula.getActoresPrincipales());
			statement.setString(4, pelicula.getPremiosConseguidos());
			statement.setInt(5, pelicula.getDuracion());
			statement.setString(6, pelicula.getFormato());
			statement.executeUpdate();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void insertarDocumental(Documental documental) {
		String query = "INSERT INTO documentales (isbn, productora, premios, doc_relacionados, duracion, formato) VALUES (?, ?, ?, ?, ?, ?)";
		try (PreparedStatement statement = conn.prepareStatement(query)) {
			
			statement.setInt(1, documental.getISBN());
			statement.setString(2, documental.getProductora());
			statement.setString(3, documental.getPremiosConcedidos());
			statement.setString(4, documental.getDocumentalesRelacionados());
			statement.setInt(5, documental.getDuracion());
			statement.setString(6, documental.getFormato());
			statement.executeUpdate();
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}//try catch
	}//insertarDocumental

	public void insertarMusica(Musica musica) {
		String query = "INSERT INTO musica (isbn, lugar, fecha, duracion, formato) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement statement = conn.prepareStatement(query)) {

			statement.setInt(1, musica.getISBN());
			statement.setString(2, musica.getLugar());
			statement.setDate(3, new java.sql.Date(musica.getFecha().getTime()));
			statement.setInt(4, musica.getDuracion());
			statement.setString(5, musica.getFormato());
			statement.executeUpdate();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}//try catch
	}//insertarMusica
	
	// UPDATES
	public boolean updateDocLib(Documento doc, Libro lib) {
		try {
			conn.setAutoCommit(false);
			updateDocumento(doc);
			updateLibro(lib);
			conn.commit();
			return true;
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				return false;
			}
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public boolean updateDocPel(Documento doc, Pelicula peli) {
		try {
			conn.setAutoCommit(false);
			updateDocumento(doc);
			updatePelicula(peli);
			conn.commit();
			return true;
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				return false;
			}
	        e.printStackTrace();
	        return false;
	    }
	}

	public boolean updateDocMus(Documento doc, Musica mus) {
		try {
			conn.setAutoCommit(false);
			updateDocumento(doc);
			updateMusica(mus);
			conn.commit();
			return true;
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				return false;
			}
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public boolean updateDocDocl(Documento doc, Documental docl) {
		try {
			conn.setAutoCommit(false);
			updateDocumento(doc);
			updateDocumental(docl);
			conn.commit();
			return true;
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				return false;
			}
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public void updateDocumento(Documento documento){
		String query = "UPDATE documentos SET tipo = ?, titulo = ?, autor = ?, replicas = ? WHERE isbn = ?";
		try (PreparedStatement statement = conn.prepareStatement(query)) {
			
			statement.setString(1, documento.getTipo());
			statement.setString(2, documento.getTitulo());
			statement.setString(3, documento.getAutor());
			statement.setInt(4, documento.getReplicas());	
			statement.setInt(5, documento.getISBN());
			statement.executeUpdate();
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}//try catch
	}//modificarDocumento
		
	public void updateLibro(Libro libro){
		String query = "UPDATE libros SET editorial = ?, npaginas = ?, tematica = ? WHERE isbn = ?";
		try (PreparedStatement statement = conn.prepareStatement(query)) {
			
			statement.setString(1, libro.getEditorial());
			statement.setInt(2, libro.getNumeroPaginas());
			statement.setString(3, libro.getTematica());
			statement.setInt(4, libro.getISBN());
			statement.executeUpdate();
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}//try catch
	}//modificarDocumento
	
	public void updatePelicula(Pelicula peli){
		String query = "UPDATE peliculas SET director = ?, actores = ?, premios = ?, duracion = ?, formato = ? WHERE isbn = ?";
		try (PreparedStatement statement = conn.prepareStatement(query)) {
			
			statement.setString(1, peli.getDirector());
			statement.setString(2, peli.getActoresPrincipales());
			statement.setString(3, peli.getPremiosConseguidos());
			statement.setInt(4, peli.getDuracion());
			statement.setString(5, peli.getFormato());
			statement.setInt(6, peli.getISBN());
			statement.executeUpdate();
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}//try catch
	}//modificarDocumento
	
	public void updateMusica(Musica musica){
		String query = "UPDATE musica SET lugar = ?, fecha = ?, duracion = ?, formato = ? WHERE isbn = ?";
		try (PreparedStatement statement = conn.prepareStatement(query)) {
			
			statement.setString(1, musica.getLugar());
			statement.setDate(2, new java.sql.Date(musica.getFecha().getTime()));
			statement.setInt(3, musica.getDuracion());
			statement.setString(4, musica.getFormato());
			statement.setInt(5, musica.getISBN());
			statement.executeUpdate();
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}// try catch
	}// updateMusica
	
	public void updateDocumental(Documental documental){
		String query = "UPDATE documentales SET productora = ?, premios = ?, doc_relacionados = ?, duracion = ?, formato = ? WHERE isbn = ?";
		try (PreparedStatement statement = conn.prepareStatement(query)) {
			
			statement.setString(1, documental.getProductora());
			statement.setString(2, documental.getPremiosConcedidos());
			statement.setString(3, documental.getDocumentalesRelacionados());
			statement.setInt(4, documental.getDuracion());
			statement.setString(5, documental.getFormato());
			statement.setInt(6, documental.getISBN());
			statement.executeUpdate();
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}// try catch
	}// updateDocumental
	
	// CLOSE
	public void cerrarConexion() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
