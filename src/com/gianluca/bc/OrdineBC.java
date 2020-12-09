package com.gianluca.bc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import com.gianluca.architecture.dao.DAOException;
import com.gianluca.architecture.dao.DBAccess;
import com.gianluca.architecture.dao.OrdineDAO;
import com.gianluca.bc.idgenerator.OrdineIdGenerator;
import com.gianluca.bc.model.Ordine;

public class OrdineBC {
	//per regola il BC passa la connessione al dao
	private Connection conn;
	private OrdineIdGenerator idGen;
	
	public OrdineBC() throws DAOException, ClassNotFoundException, IOException {
		conn = DBAccess.getConnection();
	}
	
	public void create(Ordine ordine) throws DAOException, ClassNotFoundException, IOException {
		try {
			idGen = OrdineIdGenerator.getInstance();
			ordine.setIdOrdine(idGen.nextId());
			ordine.setData(new Date());
			OrdineDAO.getFactory().create(conn, ordine);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}
	
	public void delete(long id) throws DAOException {
		try {
			OrdineDAO.getFactory().delete(conn, id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}
	
	public Ordine[] getOrdini() throws DAOException {
		Ordine[] ordini = null;
		try {
			ordini = OrdineDAO.getFactory().getAll(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
		return ordini;
	}
}
