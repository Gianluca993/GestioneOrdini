package com.gianluca.architecture.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBAccess {

	private static Connection conn;

	public static synchronized Connection getConnection() throws DAOException, ClassNotFoundException, IOException {
		try {
			// classloader consente di caricare le properties come se fosse una classe
			// serve perché altrimenti dopo il deploy le classi non potrebbero leggere da cartelle 
			// al di fuori della cartella Classes
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream input = classLoader.getResourceAsStream("properties/config.properties");
			Properties p = new Properties();
			p.load(input);
			Class.forName(p.getProperty("jdbcDriver"));
			conn = DriverManager.getConnection(
					p.getProperty("jdbcUrl"), 
					p.getProperty("jdbcUsername"),
					p.getProperty("jdbcPassword"));
			conn.setAutoCommit(false);
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
		return conn;
	}

	public static void closeConnection() throws DAOException {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException sql) {
				throw new DAOException(sql);
			}
		}
	}

}
