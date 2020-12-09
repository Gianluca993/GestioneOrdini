package com.gianluca.bc.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gianluca.architecture.dao.DAOException;
import com.gianluca.bc.ClientFacade;
import com.gianluca.bc.model.Utente;
import com.gianluca.bc.security.Algoritmo;

/**
 * Servlet implementation class ModificaUtente
 */
@WebServlet("/modificautente")
public class ModificaUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Utente utente = ClientFacade.getInstance().getUtenteByUsername((String)session.getAttribute("username"));
			String nome = request.getParameter("nome");
			if(nome == null) nome = utente.getNome();
			String cognome =request.getParameter("cognome");
			if(cognome == null) cognome = utente.getCognome();
			String indirizzo = request.getParameter("indirizzo");
			if(indirizzo == null) indirizzo = utente.getIndirizzo();
			String cap = request.getParameter("cap");
			if(cap == null) cap = utente.getCap();
			String nascita = request.getParameter("nascita");
			if(nascita == null) nascita = format.format(utente.getNascita());
			String oldPassword = Algoritmo.generaMD5(request.getParameter("oldpassword"));
			String newPassword = Algoritmo.generaMD5(request.getParameter("password"));
			if(newPassword == null) newPassword = utente.getPassword();
			else if(!oldPassword.equals(utente.getPassword())) response.sendError(401);
			String email = request.getParameter("email");
			if(email == null) email = utente.getEmail();
			
			utente.setNome(nome);
			utente.setCognome(cognome);
			utente.setIndirizzo(indirizzo);
			utente.setCap(cap);
			utente.setNascita(format.parse(nascita));
			utente.setUsername((String)session.getAttribute("username"));
			utente.setPassword(newPassword);
			utente.setEmail(email);	
			ClientFacade.getInstance().updateUtente(utente);
			response.sendRedirect("infoutente.jsp");
		} catch (ParseException | DAOException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		}
	}
}
