package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.CtrlLaboratorio;
import ourLib.Parsers.ExceptionDispacher;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;


import entities.Laboratorio;
import entities.Usuario;

/**
 * Servlet implementation class modifyDroga
 */
public class modifyLab extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CtrlLaboratorio cad = new CtrlLaboratorio();
		LinkedList<Laboratorio> arr;
		try {
			arr = cad.getAll();
			response.setStatus(200);
			request.setAttribute("listalab", arr);
			request.getRequestDispatcher("menuLaboratorio.jsp").forward(request, response);
		} 
		catch (Exception e) {
			ExceptionDispacher.manage(e, response);
		}
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Usuario user= Usuario.factory(request);
		
		if(user.hasAccess(Usuario.ADMIN)) {
			response.setStatus(401);
			response.sendRedirect("modifylab");
			return;
		}
			Integer cod=Integer.parseInt( request.getParameter("codModifiedLab") );
			String name=request.getParameter("newName");
			String email=request.getParameter("newEmail");
			String telefono=request.getParameter("newTelefono");
			
			Laboratorio lab = new Laboratorio();
			lab.setCodigo(cod);
			lab.setEmail(email);
			lab.setNombre(name);
			lab.setTelefono(telefono);
			
			CtrlLaboratorio con= new CtrlLaboratorio();
			
			try {
				con.update(lab);
				response.setStatus(200);
				response.sendRedirect("modifylab");
			} catch (Exception e) {
				ExceptionDispacher.manage(e, response);
			}
		
	}

}
