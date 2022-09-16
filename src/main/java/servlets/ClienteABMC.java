package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.CtrlCliente;
import ourLib.Parsers.ExceptionDispacher;
import ourLib.Parsers.JsonMaker;
import ourLib.Parsers.RequestParameterParser;
import java.io.IOException;
import java.util.LinkedList;

import javax.management.ServiceNotFoundException;

import entities.Cliente;
import entities.ObraSocial;
import entities.Usuario;
 

/**
 * Servlet implementation class ClienteABMC
 */
public class ClienteABMC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	private CtrlCliente con= new CtrlCliente();

	private Cliente getCliente(RequestParameterParser parser)  {
		Cliente cli= new Cliente();
		cli.setDni(parser.getInt("dni"));
		cli.setNombre(parser.getString("nombre"));
		cli.setApellido(parser.getString("apellido"));
		cli.setTelefono(parser.getString("telefono"));
		cli.setEmail(parser.getString("email"));
		cli.setLocalidad(parser.getString("localidad"));
		cli.setProvincia(parser.getString("provincia"));
		cli.setFechaNacimiento(parser.getFecha("fechaNac"));
		
		//CONSULTAR POSIBLE CAMBIO..
		ObraSocial os=new ObraSocial();
		os.setId(parser.getInt("id_os"));
		cli.setObraSocial(os);
		
		return cli;
		}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente cli= getCliente(new RequestParameterParser(request));
		Usuario user=Usuario.factory(request);
		
		try {
			switch (request.getPathInfo().substring(1)) {
			case "all": {
				LinkedList<Cliente> arr = con.getAll(user);
				request.setAttribute("all", arr);
				request.getRequestDispatcher("/WEB-INF/ui-obraSocial/showClientes.jsp").forward(request, response);
				break;
			}
			case "getbylastname": {
				if(cli.getApellido().length()<2) {
		    		response.sendError(400, "largo insificuente");
		    		return;
				}
				LinkedList<Cliente> arr=con.getAllByLastName(cli,user);
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
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cliente cli= getCliente(new RequestParameterParser(request));
		Usuario user=Usuario.factory(request);
		
		try {
			switch (request.getPathInfo().substring(1)) {
			case "add": {
				con.add(cli, user);
				response.setStatus(201);
				request.setAttribute("addedObject", cli);
				request.getRequestDispatcher("/WEB-INF/ui-cliente/confirmarCliente.jsp").forward(request, response);
				break;
			}
			case "update": {
				con.update(cli, user);
				response.setStatus(200);
				break;
			}
			case "delete": {
				con.delete(cli, user);
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


