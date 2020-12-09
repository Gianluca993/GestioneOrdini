package com.gianluca.architecture.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import com.gianluca.bc.model.Utente;

public class UtenteDAO extends UtenteDAOAdapter implements DAOConstants {

	public static UtenteDAO getFactory() throws DAOException {
		return new UtenteDAO();
	}

	private CachedRowSet rowSet;

	private UtenteDAO() throws DAOException {
		try {
			rowSet = RowSetProvider.newFactory().createCachedRowSet();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}

	@Override
	public void create(Connection conn, Utente entity) throws DAOException {
		try {
			rowSet.setCommand(SELECT_UTENTE);
			rowSet.execute(conn);
			rowSet.moveToInsertRow();
			rowSet.updateString(1, entity.getNome());
			rowSet.updateString(2, entity.getCognome());
			rowSet.updateString(3, entity.getIndirizzo());
			rowSet.updateString(4, entity.getCap());
			rowSet.updateDate(5, new java.sql.Date(entity.getNascita().getTime()));
			rowSet.updateString(6, entity.getUsername());
			rowSet.updateString(7, entity.getPassword());
			rowSet.updateString(8, entity.getEmail());
			rowSet.insertRow();
			rowSet.moveToCurrentRow();
			rowSet.acceptChanges();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}

	}

	@Override
	public void update(Connection conn, Utente entity) throws DAOException {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(UPDATE_UTENTE);
			ps.setString(1, entity.getNome());
			ps.setString(2, entity.getCognome());
			ps.setString(3, entity.getIndirizzo());
			ps.setString(4, entity.getCap());
			ps.setDate(5, new java.sql.Date(entity.getNascita().getTime()));
			ps.setString(6, entity.getPassword());
			ps.setString(7, entity.getEmail());
			ps.setString(8, entity.getUsername());
			ps.execute();
			conn.commit();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}

	public void delete(Connection conn, String username) throws DAOException {
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(DELETE_UTENTE);
			stmt.setString(1, username);
			stmt.execute();
			conn.commit();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}

	public Utente getByUsername(Connection conn, String username) throws DAOException {
		Utente u = null;
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(SELECT_UTENTE_BYUSERNAME);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				u = new Utente();
				u.setNome(rs.getString(1));
				u.setCognome(rs.getString(2));
				u.setIndirizzo(rs.getString(3));
				u.setCap(rs.getString(4));
				u.setNascita(new Date(rs.getDate(5).getTime()));
				u.setUsername(rs.getString(6));
				u.setPassword(rs.getString(7));
				u.setEmail(rs.getString(8));
			}
			rs.close();
			stmt.close();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
		return u;
	}

	@Override
	public Utente[] getAll(Connection conn) throws DAOException {
		Utente[] utenti = null;
		try {
			Statement stmt = conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(SELECT_UTENTE);
			rs.last();
			utenti = new Utente[rs.getRow()];
			rs.beforeFirst();
			for(int i = 0; rs.next(); i++) {
				Utente u = new Utente();
				u.setNome(rs.getString(1));
				u.setCognome(rs.getString(2));
				u.setIndirizzo(rs.getString(3));
				u.setCap(rs.getString(4));
				u.setNascita(new Date(rs.getDate(5).getTime()));
				u.setUsername(rs.getString(6));
				u.setPassword(rs.getString(7));
				u.setEmail(rs.getString(8));
				utenti[i] = u;
			}
			rs.close();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
		return utenti;
	}

}
