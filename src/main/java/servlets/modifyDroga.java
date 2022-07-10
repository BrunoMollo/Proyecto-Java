package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.CtrlDroga;

import java.io.IOException;
import java.sql.SQLException;

import entities.Droga;

/**
 * Servlet implementation class modifyDroga
 */
public class modifyDroga extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public modifyDroga() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer cod=Integer.parseInt( request.getParameter("codModifiedDrug") );
		String name=request.getParameter("newName");
		
		Droga drug= new Droga();
		drug.setCod(cod);
		drug.setNombre(name);
		
		CtrlDroga con= new CtrlDroga();
		
		try {
			con.update(drug);
			response.setStatus(200);
			response.getWriter().append("cod: "+cod+ "name: "+name).append(request.getContextPath());//TODO
		} catch (SQLException e) {
			response.sendError(500, e.getMessage());
			e.printStackTrace();
		}
		
		
		
	}

}
