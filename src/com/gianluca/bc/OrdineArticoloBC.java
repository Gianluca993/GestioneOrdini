package com.gianluca.bc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.gianluca.architecture.dao.DAOException;
import com.gianluca.architecture.dao.DBAccess;
import com.gianluca.architecture.dao.OrdineArticoloDAO;
import com.gianluca.bc.model.OrdineArticolo;

public class OrdineArticoloBC {
	//per regola il BC passa la connessione al dao
	private Connection conn;
	
	public OrdineArticoloBC() throws DAOException, ClassNotFoundException, IOException {
		conn = DBAccess.getConnection();
	}
	
	public void create(OrdineArticolo ordArt) throws DAOException {
		try {
			OrdineArticoloDAO.getFactory().create(conn, ordArt);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}
}
