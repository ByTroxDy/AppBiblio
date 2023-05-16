package app;

import java.sql.*;

public class MysqlConn {

	public static void main(String args[]) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo", "root", "root");
			// here sonoo is database name, root is username and password
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from emp");
			while (rs.next())
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
			conn.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
		
	}

}
