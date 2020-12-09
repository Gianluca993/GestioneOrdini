package com.gianluca.architecture.dao;

import java.sql.Connection;

import com.gianluca.bc.model.Immagine;

public abstract class ImmagineDAOAdapter implements GenericDAO<Immagine>, DAOConstants {

	@Override
	public void create(Connection conn, Immagine entity) throws DAOException {
	
	}

	@Override
	public void update(Connection conn, Immagine entity) throws DAOException {
	
	}

	@Override
	public void delete(Connection conn, Long id) throws DAOException {

	}

	@Override
	public Immagine getById(Connection conn, Long id) throws DAOException {
		return null;
	}

	@Override
	public Immagine[] getAll(Connection conn) throws DAOException {
		return null;
	}

}
