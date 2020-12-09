package com.gianluca.architecture.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import com.gianluca.bc.model.Articolo;

public class ArticoloDAO implements GenericDAO<Articolo>, DAOConstants {

	public static ArticoloDAO getFactory() throws DAOException {
		return new ArticoloDAO();
	}

	private CachedRowSet rowSet;

	private ArticoloDAO() throws DAOException {
		try {
			rowSet = RowSetProvider.newFactory().createCachedRowSet();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}

	@Override
	public void create(Connection conn, Articolo entity) throws DAOException {
		try {
			rowSet.setCommand(SELECT_ARTICOLO);
			rowSet.execute(conn);
			rowSet.moveToInsertRow();
			rowSet.updateLong(1, entity.getIdArticolo());
			rowSet.updateString(2,entity.getMarca());
			rowSet.updateString(3, entity.getModello());
			rowSet.updateDouble(4, entity.getPrezzo());
			rowSet.insertRow();
			rowSet.moveToCurrentRow();
			rowSet.acceptChanges();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}

	@Override
	public void update(Connection conn, Articolo entity) throws DAOException {
		try {
			PreparedStatement ps = conn.prepareStatement(UPDATE_ARTICOLO);
			ps.setString(1, entity.getMarca());
			ps.setString(2, entity.getModello());
			ps.setDouble(3, entity.getPrezzo());
			ps.setLong(4, entity.getIdArticolo());
			ps.execute();
			conn.commit();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}

	@Override
	public void delete(Connection conn, Long id) throws DAOException {
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(DELETE_ARTICOLO);
			stmt.setLong(1, id);
			stmt.execute();
			conn.commit();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}

	@Override
	public Articolo getById(Connection conn, Long id) throws DAOException {
		Articolo a = null;
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(SELECT_ARTICOLO_BYID);
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				a = new Articolo();
				a.setIdArticolo(rs.getLong(1));
				a.setMarca(rs.getString(2));
				a.setModello(rs.getString(3));
				a.setPrezzo(rs.getDouble(4));
			}
			rs.close();
			stmt.close();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
		return a;
	}

	@Override
	public Articolo[] getAll(Connection conn) throws DAOException {
		Articolo[] articoli = null;
		try {
			Statement stmt = conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(SELECT_ARTICOLO);
			rs.last();
			articoli = new Articolo[rs.getRow()];
			rs.beforeFirst();
			for(int i = 0; rs.next(); i++) {
				Articolo a = new Articolo();
				a.setIdArticolo(rs.getLong(1));
				a.setMarca(rs.getString(2));
				a.setModello(rs.getString(3));
				a.setPrezzo(rs.getDouble(4));
				articoli[i] = a;
			}
			rs.close();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
		return articoli;
	}

	public Articolo[] getForDisplay(Connection conn) throws DAOException {
		Articolo[] articoli = null;
		try {
			Statement stmt = conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(SELECT_ARTICOLO_ORD);
			rs.last();
			articoli = new Articolo[rs.getRow()];
			rs.beforeFirst();
			for(int i = 0; rs.next(); i++) {
				Articolo a = new Articolo();
				a.setIdArticolo(rs.getLong(1));
				a.setMarca(rs.getString(2));
				a.setModello(rs.getString(3));
				a.setPrezzo(rs.getDouble(4));
				articoli[i] = a;
			}
			rs.close();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
		return articoli;
	}
}
