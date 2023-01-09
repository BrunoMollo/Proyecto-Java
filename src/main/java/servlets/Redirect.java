package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import entities.Usuario;

/**
 * Servlet implementation class Redirect
 */
public class Redirect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//Estamos duplicando codigo a lo pabote teniendo esto separado del servlet de login, pero funciona. fue 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario user= Usuario.factory(request);
		
		if(user.getNombre()==null) {
			response.sendRedirect("login.html");		
		}
		else {
			if(user.getRol()==Usuario.ADMIN) {
				request.getRequestDispatcher("WEB-INF/adminMenu.jsp").forward(request, response);
			}
			else if(user.getRol()==Usuario.VENDEDOR) {
				request.getRequestDispatcher("WEB-INF/vendedorMenu.jsp").forward(request, response);
			}
		}
		
	}



}
