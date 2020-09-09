package main;

import java.sql.*;

public class ConMrg {
	public static Connection getCon() {
		Connection con = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
			con = DriverManager.getConnection(url, "DB_project", "qkr21730");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
