package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import app.Documental;
import app.Documento;
import app.Libro;
import app.Musica;
import app.Pelicula;
import app.Reservas;

public class DocumentoMaxDB {
	private int isbn, replicas, diasPendientes;
	private String nombre, autor, usuario;
	private Date fechaReserva;

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
				autor = resultSet.getString("autor");
				replicas = resultSet.getInt("replicas");

				Documento documento = new Documento(isbn, nombre, autor, replicas);
				documentos.add(documento);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return documentos;
	}

	public ArrayList<Documento> consultarDocumentosPorAutor(String autor2) {
		ArrayList<Documento> documentos = new ArrayList<>();
		String query = "SELECT * FROM documentos WHERE autor LIKE ? AND fecha_baja IS NULL";

		try (PreparedStatement statement = conn.prepareStatement(query)) {

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

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return documentos;
	}

	public ArrayList<Documento> consultarDocumentosPorNombreYAutor(String titulo, String autor2) {
		ArrayList<Documento> documentos = new ArrayList<>();
		String query = "SELECT * FROM documentos WHERE titulo LIKE ? AND autor LIKE ? AND fecha_baja IS NULL";

		try (PreparedStatement statement = conn.prepareStatement(query)) {

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
				autor = resultSet.getString("autor");
				replicas = resultSet.getInt("replicas");

				Documento documento = new Documento(isbn, nombre, autor, replicas);
				documentos.add(documento);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return documentos;
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
				usuario = resultSet.getString("usuario");
				fechaReserva = resultSet.getDate("fecha_reserva");
				diasPendientes = resultSet.getInt("dias_pendientes");

				Reservas reserva = new Reservas(isbn, usuario, fechaReserva, diasPendientes);
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
				System.out.println("Has alcanzado el límite de préstamos permitidos.");
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
				System.out.println("Ya has pedido prestado este documento.");
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

		System.out.println("Préstamo realizado con éxito.");
	}

	public void reservarDocumento(String usuario, int isbn) {
		// Verificar si el usuario ya tiene reservado el documento seleccionado
		String checkQuery = "SELECT COUNT(*) FROM reservas WHERE usuario = ? AND isbn = ?";
		try (PreparedStatement checkStatement = conn.prepareStatement(checkQuery)) {

			checkStatement.setString(1, usuario);
			checkStatement.setInt(2, isbn);
			ResultSet checkResult = checkStatement.executeQuery();
			checkResult.next();
			int cantidadReservasDocumento = checkResult.getInt(1);

			if (cantidadReservasDocumento > 0) {
				System.out.println("Ya has reservado este documento.");
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}

		// Insertar nueva reserva en la tabla 'reservas'
		String query = "INSERT INTO reservas (usuario, isbn, fecha_reserva, dias_pendientes) VALUES (?, ?, ?, ?)";
		try (PreparedStatement statement = conn.prepareStatement(query)) {

			statement.setString(1, usuario);
			statement.setInt(2, isbn);
			statement.setDate(3, new java.sql.Date(new Date().getTime())); // Fecha actual
			statement.setDate(4, null);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}

		System.out.println("Reserva realizada con éxito.");
	}
	
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
		String query = "INSERT INTO documentos (isbn, titulo, autor, replicas, biblioteca, fecha_baja) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement statement = conn.prepareStatement(query);) {

			statement.setInt(1, documento.getISBN());
			statement.setString(2, documento.getTitulo());
			statement.setString(3, documento.getAutor());
			statement.setInt(4, documento.getReplicas());
			statement.setString(5, documento.getBiblioteca());
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

	public void insertarLibro(Libro libro) {
		String query = "INSERT INTO libros (isbn, editorial, npaginas , tematica) VALUES (?, ?, ?, ?)";
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
		String query = "INSERT INTO documental (isbn, productora, premios, doc_relacionados, duracion, formato) VALUES (?, ?, ?, ?, ?. ?)";
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
		}
	}

	public void insertarMusica(Musica musica) {
		String query = "INSERT INTO musicas (isbn, lugar, fecha, duracion, formato) VALUES (?, ?, ?, ?, ?)";
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
		}
	}

	public boolean bajaDocumento(int isbn) {
		String query = ("UPDATE documentos SET fecha_baja = ? WHERE isbn = ?");
		try (PreparedStatement statement = conn.prepareStatement(query)) {
			
			statement.setDate(1,new java.sql.Date(new Date().getTime()) );
			statement.setInt(2, isbn);
			statement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void copiaSeguridad(String user, String pass, String backupName) {
		Process p;
		InputStream is;
		FileOutputStream fos;
		byte[] buffer = new byte[1000];
		int leido;
		
		try {
			p = Runtime.getRuntime().exec("mysqldump -u" + user + " -p" + pass + "app_biblioteca");
			is = p.getInputStream();
			fos = new FileOutputStream(backupName);
			leido = is.read(buffer);
			
			while(leido > 0) {
				fos.write(buffer, 0, leido);
				leido = is.read(buffer);
			}//while
			
		} catch (IOException e) {
			e.printStackTrace();
		}//try catch	
	}//copiaSeguridad
	

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
