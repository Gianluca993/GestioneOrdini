package com.gianluca.bc;

import java.io.IOException;

import com.gianluca.architecture.dao.DAOException;
import com.gianluca.bc.model.Articolo;
import com.gianluca.bc.model.Immagine;
import com.gianluca.bc.model.Ordine;
import com.gianluca.bc.model.OrdineArticolo;
import com.gianluca.bc.model.Report;
import com.gianluca.bc.model.Utente;

public class AdminFacade {
	private static AdminFacade adminFacade;
	private UtenteBC utenteBC;
	private ArticoloBC articoloBC;
	private OrdineBC ordineBC;
	private OrdineArticoloBC ordArtBC;
	private ImmagineBC immagineBC;
	private ReportBC reportBC;

	private AdminFacade() throws DAOException, ClassNotFoundException, IOException {
		utenteBC = new UtenteBC();
		articoloBC = new ArticoloBC();
		ordineBC = new OrdineBC();
		ordArtBC = new OrdineArticoloBC();
		immagineBC = new ImmagineBC();
		reportBC = new ReportBC();
	}

	public static AdminFacade getInstance() throws DAOException, ClassNotFoundException, IOException {
		if (adminFacade == null)
			adminFacade = new AdminFacade();
		return adminFacade;
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

	public Utente[] getUtenti() throws DAOException {
		return utenteBC.getUtenti();
	}
	
	public Utente[] cercaUtenti(String query) throws DAOException {
		return utenteBC.searchUtente(query);
	}
	
	public void deleteUtente(String username) throws DAOException {
		utenteBC.delete(username);
	}

	public void createOrUpdateArticolo(Articolo articolo) throws DAOException, ClassNotFoundException, IOException {
		articoloBC.createOrUpdate(articolo);
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

	public void deleteArticolo(long id) throws DAOException {
		articoloBC.delete(id);
	}

	public void createOrdine(Ordine ordine) throws DAOException, ClassNotFoundException, IOException {
		ordineBC.create(ordine);
	}

	public void deleteOrdine(long id) throws DAOException {
		ordineBC.delete(id);
	}

	public void createOrdineArticolo(OrdineArticolo ordArt) throws DAOException {
		ordArtBC.create(ordArt);
	}

	public void createOrUpdateImmagine(Immagine img, Articolo art) throws DAOException {
		immagineBC.createOrUpdate(img, art);
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
	
	public Report[] getReports() throws DAOException {
		return reportBC.getReport();
	}
	
	public Report[] searchReports(String query) throws DAOException {
		return reportBC.searchReport(query);
	}
}
