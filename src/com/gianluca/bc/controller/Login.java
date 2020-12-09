package com.gianluca.bc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gianluca.architecture.dao.DAOException;
import com.gianluca.bc.security.Algoritmo;
import com.gianluca.bc.utilities.CheckLogin;

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = Algoritmo.generaMD5(request.getParameter("password"));
		String userpass = null;
		String adminpass = null;

		if (username != null && password != null) {
			try {
				CheckLogin controllo = new CheckLogin();
				userpass = controllo.getUserPass(username);
				adminpass = controllo.getAdminPass(username);

				if (userpass != null) {
					if (userpass.equals(password)) {
						session.setAttribute("username", username);
						response.sendRedirect("acquisti.jsp");
					} else {
						response.sendRedirect("accessonegato.jsp");
					}
				} else if (adminpass != null) {
					if (adminpass.equals(password)) {
						session.setAttribute("admin", username);
						response.sendRedirect("admin/admin.jsp");
					} else {
						response.sendRedirect("accessonegato.jsp");
					}
				} else {
					response.sendRedirect("accessonegato.jsp");
				}
			} catch (DAOException | ClassNotFoundException exc) {
				exc.printStackTrace();
				throw new ServletException(exc.getMessage());
			}
		}
	}
}
