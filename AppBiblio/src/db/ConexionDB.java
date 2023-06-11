package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
//	private static final String URL = "jdbc:mysql://192.168.50.112/app_biblioteca";
	private static final String URL = "jdbc:mysql://db4free.net/app_biblioteca";
    private static final String USER = "biblio_admin";
    private static final String PASSWORD = "password";
    private static Connection conn;

    public static Connection getConnection() throws SQLException {
        if (conn == null) {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return conn;
    }

    public static void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
            conn = null;
        }
    }
}
