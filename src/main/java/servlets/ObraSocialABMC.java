package servlets;

import java.io.IOException;
import java.rmi.AccessException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.management.ServiceNotFoundException;

import data.ObrasSocialesDao;
import entities.Droga;
import entities.ObraSocial;
import entities.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.CtrlObraSocial;
import ourLib.Parsers.ExceptionDispacher;
import ourLib.Parsers.JsonMaker;
import ourLib.Parsers.RequestParameterParser;
import ourLib.servletAbstraction.DefaultServlet;
import ourLib.servletAbstraction.Operation;

/**
 * Servlet implementation class ObraSocialABMC
 */
public class ObraSocialABMC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CtrlObraSocial con= new CtrlObraSocial();


	private ObraSocial getObraSocial(RequestParameterParser parser) {
		ObraSocial os= new ObraSocial();
		os.setId(parser.getInt("id_os"));
		os.setNombre(parser.getString("name_os"));
		os.setEmail(parser.getString("email_os"));
		os.setTelefono(parser.getString("phone_os"));
		os.setDescuento(parser.getDouble("discount_os"));
		return os;
	}
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   
		ObraSocial os= getObraSocial(new RequestParameterParser(request));
		Usuario user=Usuario.factory(request);
		
		try {
			switch (request.getPathInfo().substring(1)) {
			case "all": {
				LinkedList<ObraSocial> arr = con.getAll(user);
				request.setAttribute("all", arr);
				request.getRequestDispatcher("/ui-obraSocial/showAllObrasSociales.jsp").forward(request, response);
				break;
			}
			case "getbyname": {
				if(os.getNombre().length()<2) {
		    		response.sendError(400, "largo insificuente");
		    		return;
				}
				LinkedList<ObraSocial> arr=con.getAllByName(os);
				response.setStatus(200);
				response.setContentType("application/json");
				
				String JsonArr=JsonMaker.getJsonArray(arr);
				response.getWriter().append(JsonArr);
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
    
    	ObraSocial os= getObraSocial(new RequestParameterParser(request));
		Usuario user=Usuario.factory(request);
		
		try {
			switch (request.getPathInfo().substring(1)) {
			case "add": {
				con.add(os, user);
				response.setStatus(201);
				request.setAttribute("addedObject", os);
				request.getRequestDispatcher("/ui-obraSocial/ConfirmarAltaObraSocial.jsp").forward(request, response);
				break;
			}
			case "update": {
				con.update(os, user);
				response.setStatus(200);
				break;
			}
			case "delete": {
				con.delete(os, user);
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
