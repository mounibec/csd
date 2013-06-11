package com.zenika.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.zenika.resanet.dao.DaoException;

public class ConnectionManager {
	private static ConnectionManager theInstance;

	private final static String DB_DRIVER = "com.mysql.jdbc.Driver"; 

	private final static String DB_URL = "jdbc:mysql://localhost:8889/resanet-test";

	private final static String DB_USERNAME = "root"; 

	private final static String DB_PASSWORD = "root"; 

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
