package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.CtrlDroga;

import java.io.IOException;
import java.sql.SQLException;

import cosas_locas.GenericServlet;
import entities.Droga;

/**
 * Servlet implementation class altaDroga
 */
public class altaDroga extends GenericServlet<Droga> {
	private static final long serialVersionUID = 1L;
       
    public altaDroga() { 
    	super();
    	this.con=new CtrlDroga();
    	this.redirectAdd="ConfirmarAltaDroga.jsp";
    	this.redirectUpdate="ABM-droga";
    	this.JSPGetAll="getAllDroga.jsp";
    	this.redirectDelete="ABM-droga";
    }


	@Override
	protected Droga getEntityFromRequest(HttpServletRequest request) {
		String name= request.getParameter("name-droga");
		
		Integer cod=null;
		if(request.getParameter("cod-droga")!=null) {
			cod=Integer.parseInt(request.getParameter("cod-droga"));
		}
		Droga drug= new Droga();
		drug.setCod(cod);
		drug.setNombre(name);
		return drug;
	}

}
