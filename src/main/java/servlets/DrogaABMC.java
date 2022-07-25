package servlets;


import logic.CtrlDroga;
import ourLib.Parsers.RequestParameterParser;
import ourLib.Parsers.JsonMaker;

import java.io.IOException;
import java.rmi.AccessException;
import java.sql.SQLException;
import java.util.LinkedList;



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
	

	protected Droga getDroga(RequestParameterParser parser) {
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
    					request.getRequestDispatcher("/ui-droga/getAllDroga.jsp").forward(request, response);
    					break;
    				}
    				case "getbyname": {
    					if(drug.getNombre().length()<2) {
    			    		response.sendError(400, "largo insificuente");
    			    		return;
    					}
    					LinkedList<Droga> arr=con.getByPartialName(drug);
    					response.setStatus(200);
    					response.setContentType("application/json");
    					
    					String JsonArr=JsonMaker.getJsonArray(arr);
    					response.getWriter().append(JsonArr);
    					break;
    				}
    				
    				default:
    					response.sendError(404, "no hay");
    				}
    			}
    			catch(SQLException e) {
    				response.sendError(500, e.getMessage());
    			}
    			catch (AccessException e) {
    				response.sendError(403, e.getMessage());
				}
    			catch (Exception e) {
    				e.printStackTrace();
				}
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    
		Droga drug= getDroga(new RequestParameterParser(request));
		Usuario user=Usuario.factory(request);
		
		try {
			switch (request.getPathInfo().substring(1)) {
			case "add": {
				con.add(drug, user);
				response.setStatus(201);
				request.setAttribute("addedObject", drug);
				request.getRequestDispatcher("/ui-droga/ConfirmarAltaDroga.jsp").forward(request, response);
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
				response.sendError(404, "no hay");
			}
		}
		catch(SQLException e) {
			response.sendError(500, e.getMessage());
		}
		catch (AccessException e) {
			response.sendError(403, e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    
   
}
