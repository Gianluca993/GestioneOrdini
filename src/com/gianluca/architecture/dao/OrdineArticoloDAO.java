package com.gianluca.architecture.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import com.gianluca.bc.model.OrdineArticolo;

public class OrdineArticoloDAO extends OrdineArticoloDAOAdapter implements DAOConstants {

	public static OrdineArticoloDAO getFactory() throws DAOException {
		return new OrdineArticoloDAO();
	}

	private CachedRowSet rowSet;

	private OrdineArticoloDAO() throws DAOException {
		try {
			rowSet = RowSetProvider.newFactory().createCachedRowSet();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}

	@Override
	public void create(Connection conn, OrdineArticolo entity) throws DAOException {
		try {
			rowSet.setCommand(SELECT_ORDINE_ARTICOLO);
			rowSet.execute(conn);
			rowSet.moveToInsertRow();
			rowSet.updateLong(1, entity.getIdOrdine());
			rowSet.updateLong(2, entity.getIdArticolo());
			rowSet.updateInt(3, entity.getQuantita());
			rowSet.insertRow();
			rowSet.moveToCurrentRow();
			rowSet.acceptChanges();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}

	@Override
	public OrdineArticolo getById(Connection conn, Long id) throws DAOException {
		OrdineArticolo oa = null;
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(SELECT_ORDINEARTICOLO_BY_ORDINEID);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				oa = new OrdineArticolo();
				oa.setIdOrdine(rs.getLong(1));
				oa.setIdArticolo(rs.getLong(2));
				oa.setQuantita(rs.getInt(3));
			}
			rs.close();
			stmt.close();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
		return oa;
	}
	
	
}
