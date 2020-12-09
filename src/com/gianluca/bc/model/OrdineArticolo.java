package com.gianluca.bc.model;

public class OrdineArticolo {

	private long idOrdine;
	private long idArticolo;
	private int quantita;
	
	
	public long getIdOrdine() {
		return idOrdine;
	}
	public void setIdOrdine(long idOrdine) {
		this.idOrdine = idOrdine;
	}
	public long getIdArticolo() {
		return idArticolo;
	}
	public void setIdArticolo(long idArticolo) {
		this.idArticolo = idArticolo;
	}
	public int getQuantita() {
		return quantita;
	}
	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}
	
	@Override
	public String toString() {
		return "OrdineArticolo [idOrdine=" + idOrdine + ", idArticolo=" + idArticolo + ", quantita=" + quantita + "]";
	}
	
}
