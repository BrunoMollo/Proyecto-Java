package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.CtrlLaboratorio;

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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modifyLab() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CtrlLaboratorio cad = new CtrlLaboratorio();
		LinkedList<Laboratorio> arr;
		try {
			arr = cad.getAll();
			response.setStatus(200);
			request.setAttribute("listalab", arr);
			request.getRequestDispatcher("menuLaboratorio.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(500, e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Usuario user= (Usuario) request.getSession().getAttribute("usuario");
		
		if(user.getRol() < Usuario.ADMIN) {
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
			} catch (SQLException e) {
				response.sendError(500, e.getMessage());
				e.printStackTrace();
			}
		
	}

}
