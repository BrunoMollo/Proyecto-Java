package ourLib.servletAbstraction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ourLib.LogicAbstraction.BasicCtrl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;



@FunctionalInterface
interface operationExecution<T>{
	void execute(T obj,HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException;
}

/**
*<p>Esta clase es una abstraccion de los servlets que realizan operaciones de alta, baja, modificacion, y consulta basicos.</p>
*<p>Se el puede definir una url base, seguida de posibles bifuraciones, por ejemplo si creo un GenericServlet&ltCosa&gt:
*	la url base seria: /ABMC-Cosa/*
*	de la cual se pueden invocar urls como:
*	<ul>
*		<li>/ABMC-Cosa/all</li>
*		<li>/ABMC-Cosa/delete</li>
*		<li>/ABMC-Cosa/add</li>
*		<li>/ABMC-Cosa/miurlcopado</li>
*	</ul>
*</p>
*<p>
*Esta clase abstracta ya tiene implemetada las siguinetes rutas por defecto:
*	<ul>
*		<li>
*		Con GET:
*			<ul>
*			<li>all</li>
*			</ul>
*		</li>
*		<li>
*		Con Post:
*			<ul>
*			<li>add</li>
*			<li>update</li>
*			<li>delete</li>
*			</ul>
*		</li>
*	</ul>
*Es posible implemetar nuevas rutas o reescribir las existentes.
*		
*</p>
*
*
*/
public abstract class GenericServlet<ENTITY> extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected BasicCtrl<ENTITY> con;
	protected String jspAddSuccess;
	protected String jspGetAll;
	
	
	
	/**
	* <p>Es necesario reescribir este metodo para que el servlet pueda converir los datos
	* que recibe de la request en la entidad del dominio corresponiente, para luego ser pasado
	* al controlador. </p>
	* <p>Los paramtros de la request estan encapsulados en el objeto RequestParameterParser que se pasa por parametro
	* el mismo ofrece metodos para acceder a esta informacion ya parseada.</p>
	*/
	protected abstract ENTITY getEntityFromRequest(RequestParameterParser parser);

	protected HashMap<String, operationExecution<ENTITY>> postOperations;
	protected HashMap<String, operationExecution<ENTITY>> getOperations;
	
	
	/**
	* <p>Inicailiza los hashMaps usados para el manejo de las urls y carga las urls por defecto </p>
	* <p>Al ser redefinido, es necesario eleguir un controlador que herede de la calse BasicCtrl<>, 
	* ademas de definir los archivos jsp pedidos por defecto: (jspAddSuccess y jspGetAll)
	* </p>
	*/
	public GenericServlet() { 
		super(); 
		getOperations=new HashMap<String, operationExecution<ENTITY>>();
		getOperations.put("all", allEntities);

		postOperations= new HashMap<String, operationExecution<ENTITY>>();
		postOperations.put("add", addEntity );
		postOperations.put("update", updateEntity);
		postOperations.put("delete", deleteEntity);
		
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestParameterParser dataParser= new RequestParameterParser(request);
		ENTITY obj= getEntityFromRequest(dataParser);
		String opt=request.getPathInfo().substring(1);
		
		operationExecution<ENTITY> e=getOperations.get(opt);
		if(e!=null) {
			e.execute(obj, request, response);
		}
		else {
			response.sendError(400,"no se que mandaste");
		}
				
		
	}


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
			request.getRequestDispatcher(jspGetAll).forward(request, response);
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
			request.getRequestDispatcher(jspAddSuccess).forward(request, response);
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
