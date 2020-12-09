package com.gianluca.architecture.dao;

public interface DAOConstants {
	//Select Entity
	String SELECT_UTENTE = "select * from utente";
	String SELECT_ORDINE = "select * from ordine";
	String SELECT_ARTICOLO = "select * from articolo";
	String SELECT_ORDINE_ARTICOLO = "select * from ordine_articolo";
	String SELECT_IMMAGINE = "select * from immagine";
	
	//Select Entity for Display
	String SELECT_ORDINE_ORD = "select * from ordine order by id_ordine";
	String SELECT_ARTICOLO_ORD = "select * from articolo order by id_articolo";
	String SELECT_ORDINE_ARTICOLO_ORD = "select * from ordine_articolo order by id_ordine";
	String SELECT_IMMAGINE_ORD = "select * from immagine order by id_img";
	
	//Ricerca per ID
	String SELECT_UTENTE_BYUSERNAME = "select * from utente where username = ?";
	String SELECT_ARTICOLO_BYID = "select * from articolo where id_articolo = ?";
	String SELECT_ORDINE_BYID = "select * from ordine where id_ordine = ?";
	String SELECT_ORDINEARTICOLO_BY_ORDINEID = "select * from ordine_articolo where id_ordine = ?";
	String SELECT_IMMAGINE_BYID = "select * from immagine where id_img = ?";
	
	//Select report
	String SELECT_REPORT = "select * from report";
	
	//Select Sequences
	String SELECT_ORDINESEQ = "select ordine_seq.nextval from dual";
	String SELECT_ARTICOLOSEQ = "select articolo_seq.nextval from dual";
	
	//Delete Entity
	String DELETE_UTENTE = "delete from utente where username = ?";
	String DELETE_ORDINE = "delete from ordine where id_ordine = ?";
	String DELETE_ARTICOLO = "delete from articolo where id_articolo = ?";
	
	//Update Entity
	String UPDATE_UTENTE = "update utente set nome = ?, cognome = ?, indirizzo = ?, cap = ?, nascita = ?, password = ?, email = ? where username = ?";
	String UPDATE_ARTICOLO = "update articolo set marca = ?, modello = ?, prezzo = ? where id_articolo = ?";
	String UPDATE_ORDINE = "update ordine set totale = ?, data = ? where id_ordine = ?";
	String UPDATE_IMMAGINE = "update immagine set url = ?, descrizione = ? where id_img = ?";
	
	//Login
	String SELECT_USERPASS = "select password from utente where username = ?";
	String SELECT_ADMINPASS = "select password from amministratore where username = ?";
}
