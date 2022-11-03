package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.CtrlLaboratorio;
import ourLib.Parsers.ExceptionDispacher;
import ourLib.Parsers.JsonMaker;
import ourLib.Parsers.RequestParameterParser;

import java.io.IOException;
import java.util.LinkedList;

import javax.management.ServiceNotFoundException;

import entities.Droga;
import entities.Laboratorio;
import entities.Usuario;

/**
 * Servlet implementation class LaboratorioABMC
 */
public class LaboratorioABMC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	CtrlLaboratorio ctrl = new CtrlLaboratorio();

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Usuario user=Usuario.factory(request);
		Laboratorio lab = getLab(new RequestParameterParser(request));
		
		try {
			switch (request.getPathInfo().substring(1)) {
			case "all": {
				LinkedList<Laboratorio> arr = ctrl.getAll(user);
				response.setStatus(200);
				request.setAttribute("listalab", arr);
				request.getRequestDispatcher("/WEB-INF/ui-laboratorio/menuLaboratorio.jsp").forward(request, response);
				break;
			}
			case "getbyname": {
				if(lab.getNombre().length()<2) {
		    		response.sendError(400, "largo insuficiente");
		    		return;
				}
				LinkedList<Laboratorio> arr=ctrl.getByPartialName(lab);
				String JsonArr=JsonMaker.getJsonArray(arr);
				response.getWriter().append(JsonArr);

				response.setStatus(200);
				response.setContentType("application/json");
				break;
			}
			case "redirectAddLab": {
				request.getRequestDispatcher("/WEB-INF/ui-laboratorio/altaLaboratorio.html").forward(request, response);
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

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario user=Usuario.factory(request);
		Laboratorio lab = getLab(new RequestParameterParser(request));
		
		try {
			switch (request.getPathInfo().substring(1)) {
			case "add": {
				ctrl.add(lab, user);
				response.setStatus(201);
				request.getRequestDispatcher("/WEB-INF/ui-laboratorio/menuLaboratorio.jsp").forward(request, response);				
				break;
				
			}
			case "update": {
				ctrl.update(lab, user);
				response.setStatus(200);
				break;
			}
			case "delete": {
				ctrl.delete(lab, user);
				response.setStatus(200);
				break;
			}
			
			default:
				throw new ServiceNotFoundException("no hay :(");
			}
		}
		catch (Exception e) {
			ExceptionDispacher.manage(e, response);
		}
		
	}
	
	
	
	
	private Laboratorio getLab(RequestParameterParser parser) {	
		Laboratorio l =new Laboratorio();
		l.setCodigo(parser.getInt("cod_lab"));
		l.setNombre(parser.getString("name_lab"));
		l.setEmail(parser.getString("email_lab"));
		l.setTelefono(parser.getString("telefono_lab"));
		return l;
	}

}
