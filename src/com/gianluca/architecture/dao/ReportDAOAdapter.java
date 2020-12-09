package com.gianluca.architecture.dao;

import java.sql.Connection;

import com.gianluca.bc.model.Report;

public abstract class ReportDAOAdapter implements GenericDAO<Report> {

	@Override
	public void create(Connection conn, Report entity) throws DAOException {
		
	}

	@Override
	public void update(Connection conn, Report entity) throws DAOException {
		
	}

	@Override
	public void delete(Connection conn, Long id) throws DAOException {
		
	}

	@Override
	public Report getById(Connection conn, Long id) throws DAOException {
		return null;
	}

	@Override
	public Report[] getAll(Connection conn) throws DAOException {
		return null;
	}

	
	
}
