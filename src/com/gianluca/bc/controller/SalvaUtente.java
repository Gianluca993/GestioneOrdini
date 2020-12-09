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

@WebServlet("/salvautente")
public class SalvaUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utente utente = new Utente();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			utente.setNome(request.getParameter("nome"));
			utente.setCognome(request.getParameter("cognome"));
			utente.setIndirizzo(request.getParameter("indirizzo"));
			utente.setCap(request.getParameter("cap"));
			utente.setNascita(format.parse(request.getParameter("nascita")));
			String username = request.getParameter("username");
			utente.setUsername(username);
			utente.setPassword(Algoritmo.generaMD5(request.getParameter("password")));
			utente.setEmail(request.getParameter("email"));			
			ClientFacade.getInstance().createUtente(utente);
			session.setAttribute("username", username);
			response.sendRedirect("acquisti.jsp");
		} catch (ParseException | DAOException | ClassNotFoundException e) {
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		}
	}

}
