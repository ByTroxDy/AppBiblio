package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import app.ConexionDB;
import app.Documento;

public class DocumentoDB {

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
