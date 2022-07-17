package ourLib.servletAbstraction;


import ourLib.LogicAbstraction.BasicCtrl;

import java.sql.SQLException;
import java.util.LinkedList;




/**
*<p>Esta clase es una clase hija de RoutedServlet que ya tiene precargadas con las rutas por defecto 
* para realizar las operaciones de alta, baja, modificacion, y consulta basicos.</p>
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
*</p>
*/
public abstract class DefaultServlet<ENTITY> extends RoutedServlet<ENTITY> {
	private static final long serialVersionUID = 1L;
    
	protected BasicCtrl<ENTITY> con;
	protected String jspAddSuccess;
	protected String jspGetAll;
	
	/**
	* <p>Inicailiza los hashMaps usados para el manejo de las urls y carga las urls por defecto </p>
	* <p>Al ser redefinido, es necesario eleguir un controlador que herede de la calse BasicCtrl<>, 
	* ademas de definir los archivos jsp pedidos por defecto: (jspAddSuccess y jspGetAll)
	* </p>
	*/
	public DefaultServlet() { 
		super(); 
		
		getOperations.put("all", allEntities);

		postOperations.put("add", addEntity );
		postOperations.put("update", updateEntity);
		postOperations.put("delete", deleteEntity);
		
	}
	

	
	//Estos no son metodos, sino atributos del tipo operationExecution(interfaz funcional)
	//los declare asi para poder pasarlos facilmente adentro del HashMap en el constructor
	
	
	//GET-----------------------
	private Operation<ENTITY> allEntities=(obj, request,  response) ->{
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
	private Operation<ENTITY> addEntity=(obj, request,  response) ->{
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
	
	
	
	private  Operation<ENTITY> updateEntity=(obj, request, response)->{
				try {
					con.update(obj);
					response.setStatus(200);
					this.allEntities.execute(obj, request, response);
				} catch (SQLException e) {
					response.sendError(500, e.getMessage());
					e.printStackTrace();
				}
	};
	
	
			
	private Operation<ENTITY> deleteEntity=(obj, request,  response)-> {
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
