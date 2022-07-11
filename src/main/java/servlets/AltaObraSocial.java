package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.CtrlObraSocial;

import java.io.IOException;
import java.sql.SQLException;

import entities.ObraSocial;

/**
 * Servlet implementation class AltaObraSocial
 */
public class AltaObraSocial extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaObraSocial() {
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
		
		String name=request.getParameter("name");
		String phone= request.getParameter("phone");
		String email= request.getParameter("email");
		Double discount=Double.parseDouble( request.getParameter("discount") );
		
		ObraSocial os= new ObraSocial(name, phone, email, discount);
		
		CtrlObraSocial con= new CtrlObraSocial();
		
		try {
			con.add(os);
			response.setStatus(201);
			response.getWriter().append("Se dio de alta").append(request.getContextPath());
			
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(500, e.getMessage());
		}
		
		
		
		
		
		
		
		
		
		
	}

}
