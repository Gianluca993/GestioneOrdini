package com.gianluca.architecture.dao;

import java.sql.SQLException;

public class DAOException extends SQLException{

	private static final long serialVersionUID = 6398888066785563816L;

	private static final int ORA1017 = 1017; //username o pw errata
	private static final int ORA17002 = 17002; // connessione fallita
	private static final int ORA00001 = 00001; //violazione di vincolo
	
	private String message;
	
	public DAOException(SQLException e) {
		String chiave = "";
		if(e != null) {
			switch(e.getErrorCode()) {
			case ORA1017:
				chiave = "Username/Password errati";
				log(e);
				break;
			case ORA17002:
				chiave = "IO Exception Oracle DB. Impossibile Connettersi";
				log(e);
				break;
			case ORA00001:
				chiave = "Violazione di vincolo";
				log(e);
				break;
			default:
				chiave = "Eccezione SQL non prevista";
				log(e);
			}
		}
		message = chiave;
	}
	
	private void log(SQLException e) {
		e.printStackTrace();
		System.err.println("\nMotivo: " + e.getMessage());
		System.err.println("Stato app: " + e.getSQLState());
		System.err.println("Codice Errore: " + e.getErrorCode());
		System.err.println("Causa: " + e.getCause());
	}

	@Override
	public String getMessage() {
		return message;
	}
}
