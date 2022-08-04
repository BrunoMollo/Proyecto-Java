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

/**
 * Servlet implementation class AltaLaboratorio
 */
public class AltaLaboratorio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nombre=request.getParameter("name");
		String email=request.getParameter("email");
		String telef=request.getParameter("telefono");
		
		Laboratorio l =new Laboratorio();
		l.setNombre(nombre);
		l.setEmail(email);
		l.setTelefono(telef);
		
		CtrlLaboratorio ctrl = new CtrlLaboratorio();
		
		try {
			ctrl.add(l);
			response.setStatus(201);
			request.setAttribute("lab", l);
			request.getRequestDispatcher("ConfirmarAltaLaboratorio.jsp").forward(request, response);
		} catch (Exception e) {
			ExceptionDispacher.manage(e, response);
		}		
	}

}
