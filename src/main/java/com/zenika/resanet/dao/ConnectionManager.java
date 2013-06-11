package com.zenika.resanet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	private static ConnectionManager theInstance;

	private final static String DB_DRIVER = "com.mysql.jdbc.Driver"; 
	//private final static String DB_URL = "jdbc:mysql://localhost:3306/resanet";
	private final static String DB_URL = "jdbc:mysql://192.168.1.71:3306/resanet";
	//192.168.1.71:3306

	private final static String DB_USERNAME = "root"; 
	private final static String DB_PASSWORD = "";
//	private final static String DB_PASSWORD = "admin"; 

	private ConnectionManager() {
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			throw new DaoException(e);
		}
	}

	public Connection getConnection() {
		try {
			Connection result = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			result.setAutoCommit(false);
			return result;
			
		} catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	public static ConnectionManager getInstance() {
		if (theInstance == null) {
			theInstance = new ConnectionManager();
		}
		return theInstance;
	}
}
