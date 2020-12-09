package com.gianluca.bc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.gianluca.architecture.dao.DAOException;
import com.gianluca.architecture.dao.DBAccess;
import com.gianluca.architecture.dao.ImmagineDAO;
import com.gianluca.bc.model.Articolo;
import com.gianluca.bc.model.Immagine;

public class ImmagineBC {

	private Connection conn;
	
	public ImmagineBC() throws DAOException, ClassNotFoundException, IOException {
		conn = DBAccess.getConnection();
	}
	
	public void createOrUpdate(Immagine img, Articolo art) throws DAOException {
		try {
			if(img.getIdImg() > 0) {
				ImmagineDAO.getFactory().update(conn, img);
			} else {
				img.setIdImg(art.getIdArticolo());
				ImmagineDAO.getFactory().create(conn, img);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
	}
	
	public Immagine getImmagineById(long id) throws DAOException {
		Immagine img = null;
		try {
			img = ImmagineDAO.getFactory().getById(conn, id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
		return img;
	}
	
	public Immagine[] getImmagini() throws DAOException {
		Immagine[] imgs = null;
		try {
			imgs = ImmagineDAO.getFactory().getAll(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
		return imgs;
	}
	
	public Immagine[] getImmaginiForDisplay() throws DAOException {
		Immagine[] imgs = null;
		try {
			imgs = ImmagineDAO.getFactory().getForDisplay(conn);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(e);
		}
		return imgs;
	}
}
