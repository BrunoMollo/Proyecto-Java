package cosas_locas;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.CtrlDroga;
import logic.CtrlLaboratorio;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.function.Function;

import org.w3c.dom.Entity;

import entities.Droga;
import entities.Laboratorio;


@FunctionalInterface
interface operationExecution<T>{
	void execute(T obj,HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException;
}


public abstract class GenericServlet<ENTITY> extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected BasicCtrl<ENTITY> con;
	protected String redirectAdd;
	protected String redirectUpdate;
	protected String JSPGetAll;
	protected String redirectDelete;
	
	protected abstract ENTITY getEntityFromRequest(HttpServletRequest request);

	private HashMap<String, operationExecution<ENTITY>> postOperations;
	
	
	public GenericServlet() { 
		super(); 
		postOperations= new HashMap<String, operationExecution<ENTITY>>();
		postOperations.put("add", addEntity );
		postOperations.put("update", updateEntity);
		postOperations.put("delete", deleteEntity);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			LinkedList<ENTITY> arr = con.getAll();
			request.setAttribute("all", arr);
			System.out.println(request);
			request.getRequestDispatcher(JSPGetAll).forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(500, e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ENTITY obj= getEntityFromRequest(request);
		
		String opt=request.getParameter("opt");
		
		try {
			postOperations.get(opt).execute(obj, request, response);
		} catch (NullPointerException e) {
			response.getWriter().append("operacion no soportada, consultar a bruno");
		}
	
	}
	
	
	
	//-----------------------------------------------------
	
	//Estos no son metodos, sino atributos del tipo operationExecution(interfaz funcional)
	//los declare asi para poder pasarlos facilmente adentro del HashMap en el constructor
	protected operationExecution<ENTITY> addEntity=(obj, request,  response) ->{
		try {
			con.add(obj);
			response.setStatus(201);
			request.setAttribute("addedObject", obj);
			request.getRequestDispatcher(redirectAdd).forward(request, response);
		} catch (SQLException e) {
			response.sendError(500, e.getMessage());
			e.printStackTrace();
		}
	};
	
	
	
	protected  operationExecution<ENTITY> updateEntity=(obj, request, response)->{
				try {
					con.update(obj);
					response.setStatus(200);
					response.sendRedirect(redirectUpdate);
				} catch (SQLException e) {
					response.sendError(500, e.getMessage());
					e.printStackTrace();
				}
	};
	
	
			
	protected operationExecution<ENTITY> deleteEntity=(obj, request,  response)-> {
			try {
				con.delete(obj);
				response.setStatus(202);
				response.sendRedirect(redirectDelete);
			} catch (SQLException e) {
				response.sendError(500, e.getMessage());
				e.printStackTrace();//
			}
	};
	

}
