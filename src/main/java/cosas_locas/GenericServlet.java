package cosas_locas;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;



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
	
	protected abstract ENTITY getEntityFromRequest(RequestParameterParser parser);

	private HashMap<String, operationExecution<ENTITY>> postOperations;
	private HashMap<String, operationExecution<ENTITY>> getOperations;
	
	
	public GenericServlet() { 
		super(); 
		getOperations=new HashMap<String, operationExecution<ENTITY>>();
		getOperations.put("all", allEntities);

		postOperations= new HashMap<String, operationExecution<ENTITY>>();
		postOperations.put("add", addEntity );
		postOperations.put("update", updateEntity);
		postOperations.put("delete", deleteEntity);
		
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestParameterParser dataParser= new RequestParameterParser(request);
		ENTITY obj= getEntityFromRequest(dataParser);
		String opt=request.getPathInfo().substring(1);
		System.out.println(opt);
		
		operationExecution<ENTITY> e=getOperations.get(opt);
		if(e!=null) {
			e.execute(obj, request, response);
		}
		else {
			response.sendError(400,"no se que mandaste");
		}
				
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestParameterParser dataParser= new RequestParameterParser(request);
		ENTITY obj= getEntityFromRequest(dataParser);
		String opt=request.getPathInfo().substring(1);
		
		operationExecution<ENTITY> e=postOperations.get(opt);
		if(e!=null) {
			e.execute(obj, request, response);
		}
		else {
			response.sendError(400,"no se que mandaste");
		}
		
	
	}
	
	
	
	//-----------------------------------------------------
	
	//Estos no son metodos, sino atributos del tipo operationExecution(interfaz funcional)
	//los declare asi para poder pasarlos facilmente adentro del HashMap en el constructor
	
	
	//GET-----------------------
	private operationExecution<ENTITY> allEntities=(obj, request,  response) ->{
		try {
			LinkedList<ENTITY> arr = con.getAll();
			request.setAttribute("all", arr);
			System.out.println(request);
			request.getRequestDispatcher(JSPGetAll).forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(500, e.getMessage());
		}	
	};
	
	
	
	
	
	//POST-------------------
	private operationExecution<ENTITY> addEntity=(obj, request,  response) ->{
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
	
	
	
	private  operationExecution<ENTITY> updateEntity=(obj, request, response)->{
				try {
					con.update(obj);
					response.setStatus(200);
					this.allEntities.execute(obj, request, response);
				} catch (SQLException e) {
					response.sendError(500, e.getMessage());
					e.printStackTrace();
				}
	};
	
	
			
	private operationExecution<ENTITY> deleteEntity=(obj, request,  response)-> {
			try {
				con.delete(obj);
				response.setStatus(202);
				this.allEntities.execute(obj, request, response);
			} catch (SQLException e) {
				response.sendError(500, e.getMessage());
				e.printStackTrace();//
			}
	};
	

}
