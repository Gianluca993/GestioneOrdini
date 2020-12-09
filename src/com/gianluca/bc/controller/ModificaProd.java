package com.gianluca.bc.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.gianluca.architecture.dao.DAOException;
import com.gianluca.bc.AdminFacade;
import com.gianluca.bc.ClientFacade;
import com.gianluca.bc.model.Articolo;
import com.gianluca.bc.model.Immagine;

@WebServlet("/admin/modificaprod")
@MultipartConfig
public class ModificaProd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		Articolo a = new Articolo();
		long id = Long.parseLong(request.getParameter("id"));
		a.setIdArticolo(id);
		a.setMarca(request.getParameter("marca"));
		a.setModello(request.getParameter("modello"));
		a.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
		
		Part imgPart = request.getPart("immagine");
		try {		
			AdminFacade.getInstance().createOrUpdateArticolo(a);
			Immagine img = ClientFacade.getInstance().getImmagineById(id);
			if(img == null)
				img = new Immagine();
			System.out.println(img);
			if(imgPart != null) {
				String fileName = Paths.get(imgPart.getSubmittedFileName()).getFileName().toString();
				File file = new File(context.getInitParameter("directory") + "\\" + fileName);
				if(!file.createNewFile()) {
					response.sendRedirect("prodotti.jsp");
					return;
				}					
				else {
					InputStream in = imgPart.getInputStream();
					OutputStream out = 	new FileOutputStream(file);
					in.transferTo(out);
					in.close();
					out.close();
					img.setIdImg(id);
					img.setUrl("img" + "/" + fileName);
					img.setDescrizione(request.getParameter("descrizione"));
					System.out.println(img);
					AdminFacade.getInstance().createOrUpdateImmagine(img, a);
					response.sendRedirect("prodotti.jsp");
				}
			} else
				response.sendRedirect("prodotti.jsp");
		} catch (DAOException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
			throw new ServletException();
		}
	}

}
