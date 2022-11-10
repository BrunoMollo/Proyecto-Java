package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.CtrlLogin;
import ourLib.Parsers.ExceptionDispacher;

import java.io.IOException;
import java.sql.SQLException;

import entities.Usuario;

/**
 * Servlet implementation class SignIn
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario user=Usuario.factory(request);
		response.getWriter().append(user.toJson());
	}

	
	
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
				if(user.getRol()==Usuario.ADMIN) {
					request.getRequestDispatcher("index.html").forward(request, response);
				}
				if(user.getRol()==Usuario.VENDEDOR) {
					request.getRequestDispatcher("home.html").forward(request, response);
				}
			} else {
				response.sendRedirect("login.html");
			}
		} 
		catch (Exception e) {
			ExceptionDispacher.manage(e, response);
		}

		
	}
}
