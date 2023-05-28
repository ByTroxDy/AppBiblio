package db;

import app.Documental;
import app.Documento;
import app.Libro;
import app.Musica;
import app.Pelicula;
import app.Reservas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DocumentoDB {
	private int isbn, replicas, diasPendientes;
	private String nombre, autor, usuario;
	private Date fechaReserva;
	
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
	
	public ArrayList<Reservas> consultarMisReservas(String myUser) {
		ArrayList<Reservas> reservas = new ArrayList<>();

		try (Connection conn = ConexionDB.getConnection()) {

			String query = "SELECT * FROM reservas WHERE usuario = ?";

			PreparedStatement statement = conn.prepareStatement(query);
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

		return reservas;
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
	            return;
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
	    	
	        String query = "INSERT INTO reservas (usuario, isbn, fecha_reserva, dias_pendientes) VALUES (?, ?, ?, ?)";
	        
	        PreparedStatement statement = conn.prepareStatement(query);
	        statement.setString(1, usuario);
	        statement.setInt(2, isbn);
	        statement.setDate(3, new java.sql.Date(new Date().getTime())); // Fecha actual
	        statement.setDate(4, null);
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
	
	/* PUBLIC FUNCTIONS */	
	//Insertar documento libro
	public void insertDocumentLlibre(Documento documento, Libro libro) throws SQLException  {
		Connection conn = ConexionDB.getConnection();
		try {
			conn.setAutoCommit(false);
			insertarDocumento(documento, conn);
			insertarLibro(libro, conn);
			
			conn.commit();
		}catch (SQLException e) {
			conn.rollback();
	        e.printStackTrace();
	        return;
	    } finally {
	        try {
	            ConexionDB.closeConnection();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }// try catch finally
	}//insertDocumentLlibre
	
	//Insertar documento pelicula
	public void insertDocumentPelicula(Documento documento, Pelicula pelicula) throws SQLException  {
		Connection conn = ConexionDB.getConnection();
		try {
			conn.setAutoCommit(false);
			insertarDocumento(documento, conn);
			insertarPelicula(pelicula, conn);
			
			conn.commit();
		}catch (SQLException e) {
			conn.rollback();
	        e.printStackTrace();
	        return;
	    } finally {
	        try {
	            ConexionDB.closeConnection();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }// try catch finally
	}//insertDocumentPelicula
	
	
	//Insertar documento musica
	public boolean insertDocumentMusica(Documento documento, Musica musica) throws SQLException  {
		Connection conn = ConexionDB.getConnection();
		try {
			conn.setAutoCommit(false);
			insertarDocumento(documento, conn);
			insertarMusica(musica, conn);
			conn.commit();
			return true;
		}catch (SQLException e) {
			conn.rollback();
	        e.printStackTrace();
	        return false;
	    } finally {
	        try {
	            ConexionDB.closeConnection();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }// try catch finally
	}//insertDocumentMusica
	
	//Insertar documento musica
		public void insertDocumentDocumental(Documento documento, Documental documental) throws SQLException  {
			Connection conn = ConexionDB.getConnection();
			try {
				conn.setAutoCommit(false);
				insertarDocumento(documento, conn);
				insertarDocumental(documental, conn);
				
				conn.commit();
			}catch (SQLException e) {
				conn.rollback();
		        e.printStackTrace();
		        return;
		    } finally {
		        try {
		            ConexionDB.closeConnection();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }// try catch finally
		}//insertDocumentMusica

	/* PRIVATE FUNCTIONS */
	//Insertar documento
	private void insertarDocumento(Documento documento, Connection conn) throws SQLException {
	    try  {
	        String query = "INSERT INTO documentos (isbn, titulo , autor, biblioteca) VALUES (?, ?, ?, ?)";
	        
	        PreparedStatement statement = conn.prepareStatement(query);
	        statement.setInt(1, documento.getISBN());
	        statement.setString(2, documento.getTitulo());
	        statement.setString(3, documento.getAutor());
	        statement.setString(4, "Benicarlo");
	        statement.executeUpdate();
	        statement.close();
	        
	    } catch (SQLException e) {
	    	conn.rollback();
	        e.printStackTrace();
	        return;
	    }//try cath
	}//insertarDocumento
	
	//Insertar libro
	private void insertarLibro(Libro libro, Connection conn) throws SQLException {
	    try  {
	        String query = "INSERT INTO libros (isbn, editorial, npaginas , tematica) VALUES (?, ?, ?, ?)";
	        
	        PreparedStatement statement = conn.prepareStatement(query);
	        statement.setInt(1, libro.getISBN());
	        statement.setString(2, libro.getEditorial());
	        statement.setInt(3, libro.getNumeroPaginas());
	        statement.setString(4, libro.getTematica());
	        statement.executeUpdate();
	        statement.close();
	        
	    } catch (SQLException e) {
	    	conn.rollback();
	        e.printStackTrace();
	        return;
	    }//try cath
	}//insertarLibro

	//Insertar Pelicula
	public void insertarPelicula(Pelicula pelicula, Connection conn) throws SQLException {
	    try {
	        String query = "INSERT INTO peliculas (isbn, director, actores , premios, duracion, formato) VALUES (?, ?, ?, ?, ?, ?)";
	        
	        PreparedStatement statement = conn.prepareStatement(query);
	        statement.setInt(1, pelicula.getISBN());
	        statement.setString(2, pelicula.getDirector());
	        statement.setString(3, pelicula.getActoresPrincipales());
	        statement.setString(4, pelicula.getPremiosConseguidos());
	        statement.setInt(5, pelicula.getDuracion());
	        statement.setString(6, pelicula.getFormato());

	        statement.executeUpdate();
	        statement.close();
	        
	    } catch (SQLException e) {
	    	conn.rollback();
	        e.printStackTrace();
	        return;
	    }//try cath
	}//insertarPelicula
	
	//Insertar Musica
	public void insertarMusica(Musica musica, Connection conn) throws SQLException {
	    try {
	        String query = "INSERT INTO musicas (isbn, lugar, fecha , duracion, formato) VALUES (?, ?, ?, ?, ?)";
	        
	        PreparedStatement statement = conn.prepareStatement(query);
	        statement.setInt(1, musica.getISBN());
	        statement.setString(2, musica.getLugar());
	        statement.setDate(3, new java.sql.Date(musica.getFecha().getTime()));
	        statement.setInt(4, musica.getDuracion());
	        statement.setString(5, musica.getFormato());

	        statement.executeUpdate();
	        statement.close();
	        
	    } catch (SQLException e) {
	    	conn.rollback();
	        e.printStackTrace();
	        return;
	    }//try cath
	}//insertarMusica
	
	
	//Insertar Documental
	public void insertarDocumental(Documental documental, Connection conn) throws SQLException {
	    try {
	        String query = "INSERT INTO documental (isbn, productora, premiosConcedidos , documentalesRelacionados, duracion, formato) VALUES (?, ?, ?, ?, ?. ?)";
	        
	        PreparedStatement statement = conn.prepareStatement(query);
	        statement.setInt(1, documental.getISBN());
	        statement.setString(2, documental.getProductora());
	        statement.setString(3, documental.getPremiosConcedidos());
	        statement.setString(4, documental.getDocumentalesRelacionados());
	        statement.setInt(5, documental.getDuracion());
	        statement.setString(6, documental.getFormato());

	        statement.executeUpdate();
	        statement.close();
	        
	    } catch (SQLException e) {
	    	conn.rollback();
	        e.printStackTrace();
	        return;
	    }//try cath
	}//insertarDocumental
}//DocumentoDB
