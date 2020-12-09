package com.gianluca.architecture.dao;

import java.sql.Connection;

import com.gianluca.bc.model.Utente;

public abstract class UtenteDAOAdapter implements GenericDAO<Utente>{

	@Override
	public void create(Connection conn, Utente entity) throws DAOException {
		
	}

	@Override
	public void update(Connection conn, Utente entity) throws DAOException {
		
	}

	@Override
	public void delete(Connection conn, Long id) throws DAOException {
		
	}

	@Override
	public Utente getById(Connection conn, Long id) throws DAOException {
		return null;
	}

	@Override
	public Utente[] getAll(Connection conn) throws DAOException {
		return null;
	}

}
