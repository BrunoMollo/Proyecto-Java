package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.CtrlLogin;
import ourLib.Parsers.ExceptionDispacher;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Laboratorio;
import entities.Usuario;

/**
 * Servlet implementation class SignIn
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String usuario = request.getParameter("usuario");
		String contra = request.getParameter("contrasena");		
		
		Usuario user = new Usuario();
		user.setUsuario(usuario);
		user.setContrasena(contra);
		
				
		CtrlLogin ctrl=new CtrlLogin();

		try {
			user=ctrl.validateLogin(user);
			if(user!=null) {
				response.setStatus(200);
				request.getSession().setAttribute("user",user);
				response.sendRedirect("index");
			} else response.sendRedirect("./login.html");
		} 
		catch (Exception e) {
			ExceptionDispacher.manage(e, response);
		}

		
	}
}
