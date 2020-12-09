package com.gianluca.bc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gianluca.architecture.dao.ArticoloDAO;
import com.gianluca.architecture.dao.DAOException;
import com.gianluca.architecture.dao.DBAccess;
import com.gianluca.bc.idgenerator.ArticoloIdGenerator;
import com.gianluca.bc.model.Articolo;

public class ArticoloBC {
	//per regola il BC passa la connessione al dao
	private Connection conn;
	private ArticoloIdGenerator idGen;
	
	public ArticoloBC() throws DAOException, ClassNotFoundException, IOException {
		conn = DBAccess.getConnection();
	}
	
	public Articolo[] searchArticolo(String query) throws DAOException {
		ArrayList<Articolo> lista = new ArrayList<>();
		String[] criterioRicerca = query.toLowerCase().trim().split(" ");
		for(Articolo a : getArticoli()) {
			for(String s : criterioRicerca) {
				if(a.getMarca().toLowerCase().contains(s) ||
						a.getModello().toLowerCase().contains(s)) {
					lista.add(a);
				}
			}
		}
		return lista.toArray(new Articolo[lista.size()]);
	}
	
	public void createOrUpdate(Articolo art) throws DAOException, ClassNotFoundException, IOException {
		try {
			if(art.getIdArticolo() > 0) {
				ArticoloDAO.getFactory().update(conn, art);
			} else {
				idGen = ArticoloIdGenerator.getInstance();
				art.setIdArticolo(idGen.nextId());
				ArticoloDAO.getFactory().create(conn, art);								
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}
	
	public void delete(long id) throws DAOException {
		try {
			ArticoloDAO.getFactory().delete(conn, id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}
	
	public Articolo getArticoloById(long id) throws DAOException {
		Articolo articolo = null;
		try {
			articolo = ArticoloDAO.getFactory().getById(conn, id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
		return articolo;
	}
	
	public Articolo[] getArticoli() throws DAOException {
		Articolo[] articoli = null;
		try {
			articoli = ArticoloDAO.getFactory().getAll(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
		return articoli;
	}
	
	public Articolo[] getArticoliForDisplay() throws DAOException {
		Articolo[] articoli = null;
		try {
			articoli = ArticoloDAO.getFactory().getForDisplay(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
		return articoli;
	}
}
