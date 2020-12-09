package com.gianluca.bc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gianluca.architecture.dao.DAOException;
import com.gianluca.architecture.dao.DBAccess;
import com.gianluca.architecture.dao.UtenteDAO;
import com.gianluca.bc.model.Articolo;
import com.gianluca.bc.model.Utente;

public class UtenteBC {
	//per regola il BC passa la connessione al dao
	private Connection conn;
	
	public UtenteBC() throws DAOException, ClassNotFoundException, IOException {
		conn = DBAccess.getConnection();
	}
	
	public Utente[] searchUtente(String query) throws DAOException {
		ArrayList<Utente> lista = new ArrayList<>();
		String[] criterioRicerca = query.toLowerCase().trim().split(" ");
		for(Utente u : getUtenti()) {
			for(String s : criterioRicerca) {
				if(u.getNome().toLowerCase().contains(s) ||
						u.getCognome().toLowerCase().contains(s) || 
						u.getUsername().toLowerCase().contains(s) || 
						u.getEmail().toLowerCase().contains(s)) {
					lista.add(u);
				}
			}
		}
		return lista.toArray(new Utente[lista.size()]);
	}
	
	public void create(Utente utente) throws DAOException {
		try {
			UtenteDAO.getFactory().create(conn, utente);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}
	
	public void update(Utente utente) throws DAOException {
		try {
			UtenteDAO.getFactory().update(conn, utente);
		} catch(SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
		
	}

	public void delete(String username) throws DAOException {
		try {
			UtenteDAO.getFactory().delete(conn, username);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public Utente getUtenteByUsername(String username) throws DAOException {
		try {
			return UtenteDAO.getFactory().getByUsername(conn, username);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}		
	}

	public Utente[] getUtenti() throws DAOException {
		return UtenteDAO.getFactory().getAll(conn);
	}
	
}
