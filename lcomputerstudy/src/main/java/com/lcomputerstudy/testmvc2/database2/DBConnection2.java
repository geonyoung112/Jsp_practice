package com.lcomputerstudy.testmvc2.database2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection2 {
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection conn = null;
		
		String url = "jdbc:mysql://localhost:3306/lcom";
		String id = "root";
		String pw = "1234";
				
		Class.forName("org.mariadb.jdbc.Driver");
		conn = DriverManager.getConnection(url, id, pw);
		
		return conn;
	}
}
