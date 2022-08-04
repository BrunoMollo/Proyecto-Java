package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.CtrlLaboratorio;
import ourLib.Parsers.ExceptionDispacher;

import java.io.IOException;
import java.sql.SQLException;

import entities.Laboratorio;
import entities.Usuario;

/**
 * Servlet implementation class deleteLab
 */
public class deleteLab extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Usuario user= Usuario.factory(request);
		
		if(user.hasAccess(Usuario.ADMIN)) {
			response.setStatus(401);
			response.sendRedirect("modifylab");
			return;
		}
			Integer cod=Integer.parseInt( request.getParameter("codLab") );
			
			Laboratorio lab= new Laboratorio();
			lab.setCodigo(cod);
				
			CtrlLaboratorio con= new CtrlLaboratorio();
				
			try {
				con.delete(lab);
				response.setStatus(202);
				response.sendRedirect("modifylab");
			} 
			catch (Exception e) {
				ExceptionDispacher.manage(e, response);
			}
	}

}
