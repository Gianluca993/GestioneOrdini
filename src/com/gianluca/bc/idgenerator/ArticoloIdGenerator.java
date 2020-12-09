package com.gianluca.bc.idgenerator;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.gianluca.architecture.dao.DAOConstants;
import com.gianluca.architecture.dao.DAOException;
import com.gianluca.architecture.dao.DBAccess;

public class ArticoloIdGenerator implements IdGeneratorInterface, DAOConstants {

	private static ArticoloIdGenerator idGenerator;
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	private ArticoloIdGenerator() throws DAOException, ClassNotFoundException, IOException {
		conn = DBAccess.getConnection();
	}
	
	public static ArticoloIdGenerator getInstance() throws DAOException, ClassNotFoundException, IOException {
		if(idGenerator == null)
			idGenerator = new ArticoloIdGenerator();
		return idGenerator;
	}
	
	@Override
	public long nextId() throws ClassNotFoundException, IOException, DAOException {
		long id = 0;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECT_ARTICOLOSEQ);
			rs.next();
			id = rs.getLong(1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
		return id;
	}

	
}
