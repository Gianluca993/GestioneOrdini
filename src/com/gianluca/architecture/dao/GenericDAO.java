package com.gianluca.architecture.dao;

import java.sql.Connection;

public interface GenericDAO<T> {

	void create(Connection conn, T entity) throws DAOException;
	void update(Connection conn, T entity) throws DAOException;
	void delete(Connection conn, Long id) throws DAOException;
	T getById(Connection conn, Long id) throws DAOException;
	T[] getAll(Connection conn) throws DAOException;
}
