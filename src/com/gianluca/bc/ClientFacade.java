package com.gianluca.bc;

import java.io.IOException;

import com.gianluca.architecture.dao.DAOException;
import com.gianluca.bc.model.Articolo;
import com.gianluca.bc.model.Immagine;
import com.gianluca.bc.model.Ordine;
import com.gianluca.bc.model.OrdineArticolo;
import com.gianluca.bc.model.Utente;

public class ClientFacade {
	private static ClientFacade clientFacade;
	private UtenteBC utenteBC;
	private ArticoloBC articoloBC;
	private OrdineBC ordineBC;
	private OrdineArticoloBC ordArtBC;
	private ImmagineBC immagineBC;
	
	private ClientFacade() throws DAOException, ClassNotFoundException, IOException {
		utenteBC = new UtenteBC();
		articoloBC = new ArticoloBC();
		ordineBC = new OrdineBC();
		ordArtBC = new OrdineArticoloBC();
		immagineBC = new ImmagineBC();
	}
	
	public static ClientFacade getInstance() throws DAOException, ClassNotFoundException, IOException {
		if(clientFacade == null)
			clientFacade = new ClientFacade();
		return clientFacade;
	}
	
	public void createUtente(Utente utente) throws DAOException {
		utenteBC.create(utente);
	}
	
	public void updateUtente(Utente utente) throws DAOException {
		utenteBC.update(utente);
	}
	
	public Utente getUtenteByUsername(String username) throws DAOException {
		return utenteBC.getUtenteByUsername(username);
	}
	
	public Articolo[] getArticoli() throws DAOException {
		return articoloBC.getArticoli();
	}
	
	public Articolo[] getArticoliForDisplay() throws DAOException {
		return articoloBC.getArticoliForDisplay();
	}
	
	public Articolo[] cercaArticoli(String query) throws DAOException {
		return articoloBC.searchArticolo(query);
	}
	
	public void createOrdine(Ordine ordine) throws DAOException, ClassNotFoundException, IOException {
		ordineBC.create(ordine);
	}
	
	public void createOrdineArticolo(OrdineArticolo ordArt) throws DAOException {
		ordArtBC.create(ordArt);
	}
	
	public Immagine getImmagineById(long id) throws DAOException {
		return immagineBC.getImmagineById(id);
	}
	
	public Immagine[] getImmagini() throws DAOException {
		return immagineBC.getImmagini();
	}
	
	public Immagine[] getImmaginiForDisplay() throws DAOException {
		return immagineBC.getImmaginiForDisplay();
	}

}
