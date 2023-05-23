package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import gui.VentanaCambiarPassword;
import gui.VentanaInicioSesion;
import gui.VentanaRegistro;
import gui.MenuSocio;
import gui.VentanaCambiarEmail;

public class UsuarioDB {
	
	public void guardarRegistro(String usuario, String password) {
		VentanaRegistro ventana = new VentanaRegistro();
		
	    try (Connection conn = ConexionDB.getConnection()) {
	    	
	    	// Verificar si el nombre de usuario ya existe
	        String queryVerificacion = "SELECT COUNT(*) FROM usuarios WHERE usuario = ?";
	        PreparedStatement statementVerificacion = conn.prepareStatement(queryVerificacion);
	        statementVerificacion.setString(1, usuario);
	        ResultSet resultSet = statementVerificacion.executeQuery();
	        resultSet.next();
	        int count = resultSet.getInt(1);
	        if (count > 0) {
	            // El nombre de usuario ya existe, mostrar mensaje de error
	            JOptionPane.showMessageDialog(ventana, "El nombre de usuario ya está registrado", "Registro", JOptionPane.ERROR_MESSAGE);
	            statementVerificacion.close();
	        } else {
		        statementVerificacion.close();
		        
		        String query = "INSERT INTO usuarios (usuario, password, rol) VALUES (?, ?, ?)";
		        PreparedStatement statement = conn.prepareStatement(query);
		        statement.setString(1, usuario);
		        statement.setString(2, password);
		        statement.setString(3, "socio");
		        statement.executeUpdate();
	
		        statement.close();
	
		        JOptionPane.showMessageDialog(ventana, "Registro exitoso", "Registro", JOptionPane.INFORMATION_MESSAGE);
		        
		        VentanaInicioSesion app = new VentanaInicioSesion();
	            app.setVisible(true);
	            ventana.dispose();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(ventana, "Error al registrar usuario", "Registro", JOptionPane.ERROR_MESSAGE);
	    } finally {
        	try {
				ConexionDB.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
	}
	
	public void iniciarSesion(String usuario, String password) {
		VentanaInicioSesion ventana = new VentanaInicioSesion();
		
	    try (Connection conn = ConexionDB.getConnection()) {

	        String query = "SELECT COUNT(*) FROM usuarios WHERE usuario = ? AND password = ?";
	        
	        PreparedStatement statement = conn.prepareStatement(query);
	        statement.setString(1, usuario);
	        statement.setString(2, password);
	        ResultSet resultSet = statement.executeQuery();
	        resultSet.next();
	        int count = resultSet.getInt(1);
	        if (count > 0) {
	        	ventana.usuario = usuario;
	            MenuSocio menu = new MenuSocio();
	            menu.setVisible(true);
	            ventana.dispose();
	        } else {
	            // El usuario y la contraseña no coinciden, mostrar mensaje de error
	            JOptionPane.showMessageDialog(ventana, "Usuario o contraseña incorrectos", "Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
	        }

	        statement.close();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(ventana, "Error al iniciar sesión", "Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
	    } finally {
        	try {
				ConexionDB.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
	}
	
    public boolean validarCuenta(String usuarioActual, String contrasena) {
    	try (Connection conn = ConexionDB.getConnection()) {

            String query = "SELECT COUNT(*) FROM usuarios WHERE usuario = ? AND password = ?";
            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, usuarioActual);
            statement.setString(2, contrasena);

            // Ejecutar la consulta y obtener el resultado
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	try {
				ConexionDB.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }

        return false;
    }

    public boolean nombreUsuarioEnUso(String nuevoUsuario) {
    	try (Connection conn = ConexionDB.getConnection()) {

            // Crear la consulta SQL para verificar el nuevo usuario
            String query = "SELECT COUNT(*) FROM usuarios WHERE usuario = ?";
            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, nuevoUsuario);

            // Ejecutar la consulta y obtener el resultado
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	try {
				ConexionDB.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }

        return false;
    }

    public void actualizarNombreUsuario(String usuarioActual, String nuevoUsuario) {
    	try (Connection conn = ConexionDB.getConnection()) {

            // Crear la consulta SQL para actualizar el usuario
            String query = "UPDATE usuarios SET usuario = ? WHERE usuario = ?";
            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, nuevoUsuario);
            statement.setString(2, usuarioActual);

            // Ejecutar la consulta
            statement.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	try {
				ConexionDB.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
    }
    
    public void cambiarContrasena(String usuario, String contrasenaActual, String nuevaContrasena) {
    	
    	VentanaCambiarPassword ventana = new VentanaCambiarPassword();
    	
        // Validar la cuenta para verificar si el usuario y la contraseña coinciden
        boolean cuentaValida = validarCuenta(usuario, contrasenaActual);

        if (!cuentaValida) {
            JOptionPane.showMessageDialog(ventana, "La contraseña actual es incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = ConexionDB.getConnection()) {

            String query = "UPDATE usuarios SET password = ? WHERE usuario = ?";
            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, nuevaContrasena);
            statement.setString(2, usuario);

            // Ejecutar la consulta
            statement.executeUpdate();

            JOptionPane.showMessageDialog(ventana, "Contraseña actualizada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	try {
				ConexionDB.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
    }
	
	public void cambiarEmail(String usuario, String contrasena, String email) {
    	
    	VentanaCambiarEmail ventana = new VentanaCambiarEmail();
    	
        // Validar la cuenta para verificar si el usuario y la contraseña coinciden
        boolean cuentaValida = validarCuenta(usuario, contrasena);

        if (!cuentaValida) {
            JOptionPane.showMessageDialog(ventana, "La contraseña actual es incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection conn = ConexionDB.getConnection()) {

            String query = "UPDATE usuarios SET email = ? WHERE usuario = ?";
            
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, usuario);

            // Ejecutar la consulta
            statement.executeUpdate();

            JOptionPane.showMessageDialog(ventana, "Email actualizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	try {
				ConexionDB.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
    }

}
