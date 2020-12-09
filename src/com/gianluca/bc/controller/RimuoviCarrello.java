package com.gianluca.bc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gianluca.bc.model.Carrello;

@WebServlet("/rimuovi")
public class RimuoviCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		Carrello carrello = (Carrello)session.getAttribute("carrello");
		if(id != null) {
			carrello.rimuoviArticolo(id);			
		}
		response.sendRedirect("visualizza.jsp");
	}
}
