package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.CtrlCliente;
import logic.CtrlVenta;
import ourLib.AppException;
import ourLib.Parsers.ExceptionDispacher;
import ourLib.Parsers.RequestParameterParser;

import java.io.Console;
import java.io.IOException;

import javax.management.ServiceNotFoundException;

import data.ClienteDao;
import entities.Cliente;
import entities.Usuario;
import entities.Venta;
import ourLib.AppException;



public class VentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
    public VentaServlet() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Usuario user=Usuario.factory(request);
		CtrlVenta con= (CtrlVenta) request.getSession().getAttribute("CtrlVenta");
		
		try {
			switch (request.getPathInfo()) {
				case "/iniciarVentaLibre": {
					con = new CtrlVenta();
					con.iniciarVenta(user);
					request.getSession().setAttribute("CtrlVenta", con);
					request.getRequestDispatcher("/WEB-INF/ui-venta/agregarMedicamentos.jsp").forward(request, response);
					break;
				}
				case "/iniciarVentaOS": {
					con = new CtrlVenta();
					con.iniciarVenta(user);
					request.getSession().setAttribute("CtrlVenta", con);
					request.getRequestDispatcher("/WEB-INF/ui-venta/buscarCliente.html").forward(request, response);
					break;
				}
				default:
					throw new AppException("no hay",404);
			}
		} catch (Exception e) {
			ExceptionDispacher.manage(e, response);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Usuario user=Usuario.factory(request);
		CtrlVenta con= (CtrlVenta) request.getSession().getAttribute("CtrlVenta");
		CtrlCliente ccli = new CtrlCliente();
		
		try {
			switch (request.getPathInfo()) {
			case "/addMedicamento": {			
				Integer cantidad= Integer.parseInt(request.getParameter("cantidad")); 
				String nombreMed = request.getParameter("name_med"); 			
				Boolean medEncontrado=con.addMedicamento(nombreMed, cantidad); 		
				request.setAttribute("medEncontrado", medEncontrado);			
				request.getRequestDispatcher("/WEB-INF/ui-venta/agregarMedicamentos.jsp").forward(request, response);
				break;
			}
			case "/addMedicamentoOS": {			
				Integer cantidad= Integer.parseInt(request.getParameter("cantidad")); 
				String nombreMed = request.getParameter("name_med"); 			
				Boolean medEncontrado=con.addMedicamento(nombreMed, cantidad); 		
				request.setAttribute("medEncontrado", medEncontrado);			
				request.getRequestDispatcher("/WEB-INF/ui-venta/agregarMedicamentosOS.jsp").forward(request, response);
				break;
			}
			case "/buscarCliente": {
				ClienteDao cdao = new ClienteDao();
				Integer dni= Integer.parseInt(request.getParameter("dniCliente"));
				Cliente c = ccli.getOne(new Cliente(dni),user);
				if(c!=null) {
					request.getSession().setAttribute("cliente", c);
					con.setCliente(c);
					request.getRequestDispatcher("/WEB-INF/ui-venta/agregarMedicamentosOS.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("/WEB-INF/ui-venta/buscarCliente.html").forward(request, response);
				}
				break;
			}
			case "/buscarAfiliado": {
				ClienteDao cdao = new ClienteDao();
				String nroAfiliado= request.getParameter("nroAfiliado");
				Cliente c = new Cliente();
				c.setNroAfiliado(nroAfiliado);
				c = ccli.getByNroAfiliado(c);
				if(c!=null) {
					request.getSession().setAttribute("cliente", c);
					con.setCliente(c);
					request.getRequestDispatcher("/WEB-INF/ui-venta/agregarMedicamentosOS.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("/WEB-INF/ui-venta/buscarCliente.html").forward(request, response);
				}
				break;
			}
			case "/cerrarVenta": {
				String strNroRec = request.getParameter("nroReceta");
				Integer nroRec = strNroRec==null?null:Integer.parseInt(request.getParameter("nroReceta"));
				con.getVenta().setNroReceta(nroRec);
				con.cerrarVenta();
				//Ver si no haria falta mostrar como una especie de factura antes de volver al menu principal
				response.sendRedirect("../Redirect");//volver al menu de vendedro ¿?				
				break;
				
			}
			
			default:
				throw new AppException("no hay",404);
			}
		}
		catch (Exception e) {
			ExceptionDispacher.manage(e, response);
		}
		
		
    }
	
	
		
}


