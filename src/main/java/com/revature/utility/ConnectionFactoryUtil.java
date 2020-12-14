package com.revature.utility;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryUtil {
	private static Connection conn;
	
	public static Connection getConnection() {
		try {
			conn = DriverManager.getConnection(
					"jdbc:postgresql://mybankdatabase.cxcq8ud9ekvx.us-east-2.rds.amazonaws.com:5432/Bank",
					"rupeshg",
					"database"
					);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
		
	}
}