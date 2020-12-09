package com.gianluca.architecture.dao;

import java.sql.Connection;

import com.gianluca.bc.model.OrdineArticolo;

public abstract class OrdineArticoloDAOAdapter implements GenericDAO<OrdineArticolo>{

	@Override
	public void create(Connection conn, OrdineArticolo entity) throws DAOException {
		
	}

	@Override
	public void update(Connection conn, OrdineArticolo entity) throws DAOException {
	
	}

	@Override
	public void delete(Connection conn, Long id) throws DAOException {

	}

	@Override
	public OrdineArticolo getById(Connection conn, Long id) throws DAOException {

		return null;
	}

	@Override
	public OrdineArticolo[] getAll(Connection conn) throws DAOException {

		return null;
	}

	
	
}
