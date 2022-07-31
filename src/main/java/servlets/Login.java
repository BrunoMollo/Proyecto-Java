package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.CtrlLogin;

import java.io.IOException;
import java.sql.SQLException;

import entities.Usuario;

/**
 * Servlet implementation class SignIn
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario user=Usuario.factory(request);
		response.getWriter().append(user.toJson());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
				request.getRequestDispatcher("index.html").forward(request, response);
			} else {
				response.sendRedirect("login.html");
			}
		} catch (SQLException e) {
			response.sendError(500, e.getLocalizedMessage());
		}
		catch(UnsupportedOperationException e) {
			response.sendError(501, e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		
	}
}
