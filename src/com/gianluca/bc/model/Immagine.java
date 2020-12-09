package com.gianluca.bc.model;

public class Immagine {

	private long idImg; //references idArticolo
	private String url;
	private String descrizione;
	
	
	public long getIdImg() {
		return idImg;
	}
	public void setIdImg(long idImg) {
		this.idImg = idImg;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	@Override
	public String toString() {
		return "Immagine [idImg=" + idImg + ", url=" + url + ", descrizione=" + descrizione + "]";
	}
	
}
