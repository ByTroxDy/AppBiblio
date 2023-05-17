package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "jdbc:mysql//localhost:3306/id20457447_biblioteca";
    private static final String USERNAME = "id20457447_admin";
    private static final String PASSWORD = "Password123#@!";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

