package com.gianluca.bc.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gianluca.architecture.dao.DAOException;
import com.gianluca.bc.ClientFacade;
import com.gianluca.bc.model.Carrello;
import com.gianluca.bc.model.Ordine;
import com.gianluca.bc.model.OrdineArticolo;

@WebServlet("/conferma")
public class ConfermaOrdine extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Carrello carrello = (Carrello) session.getAttribute("carrello");
		Ordine ordine = new Ordine();
		ordine.setTotale(carrello.totaleComplessivo());
		ordine.setUsername((String)session.getAttribute("username"));
		Enumeration<String[]> prodotti = carrello.listaProdotti();
		try {
			ClientFacade.getInstance().createOrdine(ordine);
			OrdineArticolo ordArt = null;
			while(prodotti.hasMoreElements()) {
				String[] prodotto = prodotti.nextElement();
				ordArt = new OrdineArticolo();
				ordArt.setIdOrdine(ordine.getIdOrdine());
				ordArt.setIdArticolo(Long.parseLong(prodotto[4]));
				ordArt.setQuantita(Integer.parseInt(prodotto[3]));
				ClientFacade.getInstance().createOrdineArticolo(ordArt);
			}
			session.setAttribute("idOrdine", ordine.getIdOrdine());
			response.sendRedirect("ordineconf.jsp");
		} catch(DAOException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new ServletException(e.getMessage() + " " + e.getStackTrace());
		}
	}

}
