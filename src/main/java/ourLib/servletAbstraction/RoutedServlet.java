package ourLib.servletAbstraction;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ourLib.Parsers.RequestParameterParser;

import java.io.IOException;







/**
*<p>Esta clase es una abstraccion de los servelts para agregar subrutas a los urls. Se el puede definir una url base, 
*seguida de posibles bifuraciones, por ejemplo si creo un RoutedServlet&ltCosa&gt:
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
*La idea es tambien realizar funciones de validacion de usuarios en esta clase.
*</p>
*/
@Deprecated
public abstract class RoutedServlet<ENTITY> extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
	/**
	* <p>Es necesario reescribir este metodo para que el servlet pueda converir los datos
	* que recibe de la request en la entidad del dominio corresponiente, para luego ser pasado
	* al controlador. </p>
	* <p>Los paramtros de la request estan encapsulados en el objeto RequestParameterParser que se pasa por parametro
	* el mismo ofrece metodos para acceder a esta informacion ya parseada.</p>
	*/
	protected abstract ENTITY getEntityFromRequest(RequestParameterParser parser);

	protected final RouteManager<ENTITY> POST=new RouteManager<ENTITY>();
	protected final RouteManager<ENTITY> GET=new RouteManager<ENTITY>();
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Mapea los datos de la request a un objeto java
		RequestParameterParser dataParser= new RequestParameterParser(request);
		ENTITY obj= getEntityFromRequest(dataParser);
		
		//Selecciona la operacion a ejecutar dependiendo del path que llege en el url
		String opt=request.getPathInfo().substring(1);
		Operation<ENTITY> e = GET.getOperation(opt);
		
		if(e!=null) {
			e.execute(obj, request, response);
		}
		else { response.sendError(400,"no se que mandaste"); }
	}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Mapea los datos de la request a un objeto java
		RequestParameterParser dataParser= new RequestParameterParser(request);
		ENTITY obj= getEntityFromRequest(dataParser);
		
		//Selecciona la operacion a ejecutar dependiendo del path que llege en el url
		String opt=request.getPathInfo().substring(1);
		Operation<ENTITY> e=POST.getOperation(opt);
		
		if(e!=null) {
			e.execute(obj, request, response);
		}
		else { response.sendError(400,"no se que mandaste"); }
	}
	
	
	
}
