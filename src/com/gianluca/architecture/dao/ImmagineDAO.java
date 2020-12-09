package com.gianluca.architecture.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import com.gianluca.bc.model.Immagine;

public class ImmagineDAO extends ImmagineDAOAdapter implements DAOConstants{

	public static ImmagineDAO getFactory() throws DAOException {
		return new ImmagineDAO();
	}
	
	private CachedRowSet rowSet;
	
	private ImmagineDAO() throws DAOException {
		try {
			rowSet = RowSetProvider.newFactory().createCachedRowSet();
		} catch (SQLException e) {
			throw new DAOException(e);
		} 
	}
	
	@Override
	public void create(Connection conn, Immagine entity) throws DAOException {
		try {
			rowSet.setCommand(SELECT_IMMAGINE);
			rowSet.execute(conn);
			rowSet.moveToInsertRow();
			rowSet.updateLong(1, entity.getIdImg());
			rowSet.updateString(2, entity.getUrl());
			rowSet.updateString(3, entity.getDescrizione());
			rowSet.insertRow();
			rowSet.moveToCurrentRow();
			rowSet.acceptChanges();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}	
	}

	@Override
	public void update(Connection conn, Immagine entity) throws DAOException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(UPDATE_IMMAGINE);
			ps.setString(1, entity.getUrl());
			ps.setString(2, entity.getDescrizione());
			ps.setLong(3, entity.getIdImg());
			ps.execute();
			conn.commit();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}		
	}

	@Override
	public Immagine getById(Connection conn, Long id) throws DAOException {
		PreparedStatement ps = null;
		Immagine i = null;
		try {
			ps = conn.prepareStatement(SELECT_IMMAGINE_BYID);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				i = new Immagine();
				i.setIdImg(rs.getLong(1));
				i.setUrl(rs.getString(2));
				i.setDescrizione(rs.getString(3));
			}
			rs.close();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
		return i;
	}

	@Override
	public Immagine[] getAll(Connection conn) throws DAOException {
		Immagine[] immagini = null;
		try {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(SELECT_IMMAGINE);
			rs.last();
			immagini = new Immagine[rs.getRow()];
			rs.beforeFirst();
			for (int i = 0; rs.next(); i++) {
				Immagine im = new Immagine();
				im.setIdImg(rs.getLong(1));
				im.setUrl(rs.getString(2));
				im.setDescrizione(rs.getString(3));
				immagini[i] = im;
			}
			rs.close();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
		return immagini;
	}


	public Immagine[] getForDisplay(Connection conn) throws DAOException {
		Immagine[] immagini = null;
		try {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(SELECT_IMMAGINE_ORD);
			rs.last();
			immagini = new Immagine[rs.getRow()];
			rs.beforeFirst();
			for (int i = 0; rs.next(); i++) {
				Immagine im = new Immagine();
				im.setIdImg(rs.getLong(1));
				im.setUrl(rs.getString(2));
				im.setDescrizione(rs.getString(3));
				immagini[i] = im;
			}
			rs.close();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
		return immagini;
	}
}
