package com.gianluca.bc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gianluca.architecture.dao.DAOException;
import com.gianluca.bc.AdminFacade;

/**
 * Servlet implementation class DeleteOrdine
 */
@WebServlet("/admin/deleteordine")
public class DeleteOrdine extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			AdminFacade.getInstance().deleteOrdine(Long.parseLong(request.getParameter("id")));
			response.sendRedirect("ordini.jsp");
		} catch (NumberFormatException | DAOException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
