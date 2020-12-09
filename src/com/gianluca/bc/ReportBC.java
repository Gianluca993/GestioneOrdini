package com.gianluca.bc;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import com.gianluca.architecture.dao.DAOException;
import com.gianluca.architecture.dao.DBAccess;
import com.gianluca.architecture.dao.ReportDAO;
import com.gianluca.bc.model.Report;

public class ReportBC {
	private Connection conn;
	
	public ReportBC() throws DAOException, ClassNotFoundException, IOException {
		conn = DBAccess.getConnection();
	}
	
	public Report[] searchReport(String query) throws DAOException {
		ArrayList<Report> lista = new ArrayList<>();
		String[] criterioRicerca = query.toLowerCase().trim().split(" ");
		for(Report r : getReport()) {
			for(String s : criterioRicerca) {
				if(r.getUsername().toLowerCase().contains(s) ||
						r.getEmail().toLowerCase().contains(s)) {
					lista.add(r);
				}
			}
		}
		return lista.toArray(new Report[lista.size()]);
	}

	public Report[] getReport() throws DAOException {
		Report[] reports = null;
		reports = ReportDAO.getFactory().getAll(conn);
		return reports;
	}
	
}
