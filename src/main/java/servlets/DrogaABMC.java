package servlets;


import logic.CtrlDroga;
import ourLib.Parsers.RequestParameterParser;
import ourLib.Parsers.ExceptionDispacher;
import ourLib.Parsers.JsonMaker;

import java.io.IOException;
import java.rmi.AccessException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.management.ServiceNotFoundException;

import entities.Droga;
import entities.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class altaDroga
 */
public class DrogaABMC extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CtrlDroga con=new CtrlDroga();;
	

	private Droga getDroga(RequestParameterParser parser) {
		Droga drug= new Droga();
		drug.setCod(parser.getInt("cod_droga"));
		drug.setNombre(parser.getString("name_droga"));
		return drug;
	}
       
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   
		Droga drug= getDroga(new RequestParameterParser(request));
		Usuario user=Usuario.factory(request);
		
		try {
			switch (request.getPathInfo().substring(1)) {
			case "all": {
				LinkedList<Droga> arr = con.getAll(user);
				request.setAttribute("all", arr);
				request.getRequestDispatcher("/WEB-INF/ui-droga/getAllDroga.jsp").forward(request, response);
				break;
			}
			case "getbyname": {
				if(drug.getNombre().length()<2) {
		    		response.sendError(400, "largo insuficiente");
		    		return;
				}
				LinkedList<Droga> arr=con.getByPartialName(drug);
				String JsonArr=JsonMaker.getJsonArray(arr);
				response.getWriter().append(JsonArr);

				response.setStatus(200);
				response.setContentType("application/json");
				break;
			}
			case "redirectAddDroga": {
				request.getRequestDispatcher("/WEB-INF/ui-droga/altaDroga.html").forward(request, response);
				break;
			}
			
			default:
				throw new ServiceNotFoundException("no hay");
			}
		}
		catch (Exception e) {
			ExceptionDispacher.manage(e, response);
		}
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    
		Droga drug= getDroga(new RequestParameterParser(request));
		Usuario user=Usuario.factory(request);
		
		try {
			switch (request.getPathInfo().substring(1)) {
			case "add": {
				con.add(drug, user);
				response.setStatus(201);
				request.getRequestDispatcher("/WEB-INF/ui-droga/getAllDroga.jsp").forward(request, response);
				break;
			}
			case "update": {
				con.update(drug, user);
				response.setStatus(200);
				break;
			}
			case "delete": {
				con.delete(drug, user);
				response.setStatus(202);
				break;
			}
			
			default:
				throw new ServiceNotFoundException("no hay");
			}
		}
		catch (Exception e) {
			ExceptionDispacher.manage(e, response);
		}
    }
    
    
   
}
