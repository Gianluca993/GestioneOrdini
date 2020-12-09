package com.gianluca.bc.utilities;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gianluca.architecture.dao.DAOConstants;
import com.gianluca.architecture.dao.DAOException;
import com.gianluca.architecture.dao.DBAccess;

public class CheckLogin implements DAOConstants{
	private Connection conn;
	
	
	public CheckLogin() throws DAOException, ClassNotFoundException, IOException {
		conn = DBAccess.getConnection();
	}
	
	public String getUserPass(String username) throws DAOException {
//		Utente u = UtenteDAO.getFactory().getByUsername(conn, username);
//		if(u != null) {
//			return u.getPassword();
//		}
//		return null;
		try {
			PreparedStatement ps = conn.prepareStatement(SELECT_USERPASS);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}		
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return null;
	}
	
	public String getAdminPass(String username) throws DAOException {
//		Admin a = AdminDAO.getFactory().getByUsername(conn, username);
//		if(a != null) {
//			return a.getPassword();
//		}
//		return null;
		try {
			PreparedStatement ps = conn.prepareStatement(SELECT_ADMINPASS);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}		
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		return null;
	}
}
