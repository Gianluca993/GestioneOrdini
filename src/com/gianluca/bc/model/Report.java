package com.gianluca.bc.model;

import java.util.Date;

public class Report {
	private String username;
	private String email;
	private long idOrdine;
	private Date data;
	private double totale;
	private int quantita;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getIdOrdine() {
		return idOrdine;
	}
	public void setIdOrdine(long idOrdine) {
		this.idOrdine = idOrdine;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public double getTotale() {
		return totale;
	}
	public void setTotale(double totale) {
		this.totale = totale;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
}
