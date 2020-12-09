package com.gianluca.architecture.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import com.gianluca.bc.model.Ordine;
import com.gianluca.bc.model.Report;

public class ReportDAO extends ReportDAOAdapter implements DAOConstants {

	public static ReportDAO getFactory() throws DAOException{
		return new ReportDAO();
	}

	private CachedRowSet rowSet;
	
	private ReportDAO() throws DAOException {
		try {
			rowSet = RowSetProvider.newFactory().createCachedRowSet();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
	}
	
	@Override
	public Report[] getAll(Connection conn) throws DAOException {
		Report[] reports = null;
		try {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(SELECT_REPORT);
			rs.last();
			reports = new Report[rs.getRow()];
			rs.beforeFirst();
			for (int i = 0; rs.next(); i++) {
				Report r = new Report();
				r.setUsername(rs.getString(1));
				r.setEmail(rs.getString(2));
				r.setIdOrdine(rs.getLong(3));
				r.setData(new java.util.Date(rs.getDate(4).getTime()));
				r.setTotale(rs.getDouble(5));
				r.setQuantita(rs.getInt(6));
				reports[i] = r;
			}
			rs.close();
		} catch (SQLException sql) {
			throw new DAOException(sql);
		}
		return reports;
	}
}
