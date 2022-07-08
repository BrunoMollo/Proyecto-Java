package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.CtrlAltaDroga;

import java.io.IOException;
import java.sql.SQLException;

import entities.Droga;

/**
 * Servlet implementation class altaDroga
 */
public class altaDroga extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public altaDroga() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at get: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cod= request.getParameter("cod");
		String name= request.getParameter("name");
		
		
		
		Droga drug= new Droga();
		drug.setCod(cod);
		drug.setNombre(name);
		
		CtrlAltaDroga con= new CtrlAltaDroga();
		
		try {
			con.addDroga(drug);
			
			response.setStatus(201);
			request.getRequestDispatcher("WEB-INF/ConfirmarAltaDroga.jsp").forward(request, response);
			
		} catch (SQLException e) {
			response.sendError(500, e.getMessage());
			e.printStackTrace();
		}
		
		
	}

}