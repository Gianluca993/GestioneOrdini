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
 * Servlet implementation class DeleteArticolo
 */
@WebServlet("/admin/deleteprod")
public class DeleteArticolo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id = Long.parseLong(request.getParameter("id"));
		try {
			AdminFacade.getInstance().deleteArticolo(id);
			System.out.println(id);
			response.sendRedirect("prodotti.jsp");
		} catch (DAOException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
			throw new ServletException();
		}
	}

}
