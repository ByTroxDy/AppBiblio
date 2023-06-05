package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JOptionPane;

import gui.VentanaRegistro;

public class UsuarioMaxDB {
	VentanaRegistro ventanaR = new VentanaRegistro();

    private Connection conn;

    public UsuarioMaxDB() {
        try {
			conn = ConexionDB.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    public boolean guardarRegistro(String usuario, String password) {
        String queryVerificacion = "SELECT COUNT(*) FROM usuarios WHERE usuario = ?";
        try (PreparedStatement statementVerificacion = conn.prepareStatement(queryVerificacion)) {
            statementVerificacion.setString(1, usuario);
            try (ResultSet resultSet = statementVerificacion.executeQuery()) {
                resultSet.next();
                int count = resultSet.getInt(1);
                if (count > 0) {
                	JOptionPane.showMessageDialog(ventanaR, "El nombre de usuario ya está registrado", "Registro", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        String query = "INSERT INTO usuarios (usuario, password, rol) VALUES (?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, usuario);
            statement.setString(2, password);
            statement.setString(3, "socio");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean iniciarSesion(String usuario, String password) {
        String query = "SELECT COUNT(*) FROM usuarios WHERE usuario = ? AND password = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, usuario);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String obtenerGrupo(String usuario) {
        String query = "SELECT rol FROM usuarios WHERE usuario = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, usuario);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("rol");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean validarCuenta(String usuarioActual, String contrasena) {
        String query = "SELECT COUNT(*) FROM usuarios WHERE usuario = ? AND password = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, usuarioActual);
            statement.setString(2, contrasena);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean nombreUsuarioEnUso(String nuevoUsuario) {
        String query = "SELECT COUNT(*) FROM usuarios WHERE usuario = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, nuevoUsuario);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void actualizarNombreUsuario(String usuarioActual, String nuevoUsuario) {
        String query = "UPDATE usuarios SET usuario = ? WHERE usuario = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, nuevoUsuario);
            statement.setString(2, usuarioActual);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean cambiarContrasena(String usuario, String nuevaContrasena) {
        String query = "UPDATE usuarios SET password = ? WHERE usuario = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, nuevaContrasena);
            statement.setString(2, usuario);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean cambiarEmail(String usuario, String email) {
        String query = "UPDATE usuarios SET email = ? WHERE usuario = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, usuario);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
    }

    public void cerrarConexion() {
        try {
            if (conn != null) {
            	conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public boolean guardarRegistro2(String usuario, String password, String clas) {
        String queryVerificacion = "SELECT COUNT(*) FROM usuarios WHERE usuario = ?";
        try (PreparedStatement statementVerificacion = conn.prepareStatement(queryVerificacion)) {
            statementVerificacion.setString(1, usuario);
            try (ResultSet resultSet = statementVerificacion.executeQuery()) {
                resultSet.next();
                int count = resultSet.getInt(1);
                if (count > 0) {
                	JOptionPane.showMessageDialog(ventanaR, "El nombre de usuario ya está registrado", "Registro", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        String query = "INSERT INTO usuarios (usuario, password, rol) VALUES (?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, usuario);
            statement.setString(2, password);
            statement.setString(3, clas);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    
    public void actualizarRol(String usuarioActual, String rol) {
        String query = "UPDATE usuarios SET rol = ? WHERE usuario = ?";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, rol);
            statement.setString(2, usuarioActual);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
<<<<<<< Updated upstream
    public void bajaUsuario(String usuario) {
		String query = ("DELETE * FROM documentos WHERE usuario = ?");
		try (PreparedStatement statement = conn.prepareStatement(query)) {
			
			statement.setString(1, usuario);
			statement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
=======
    public boolean bajaUsuario(int isbn) {
		String query = ("UPDATE documentos SET fecha_baja = ? WHERE isbn = ?");
		try (PreparedStatement statement = conn.prepareStatement(query)) {
			
			statement.setDate(1, new java.sql.Date(new Date().getTime()));
			statement.setInt(2, isbn);
			statement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
>>>>>>> Stashed changes
		}//try catch
	}//bajaDocumento
}//end
