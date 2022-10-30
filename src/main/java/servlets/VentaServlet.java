package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.CtrlVenta;
import ourLib.Parsers.ExceptionDispacher;
import ourLib.Parsers.RequestParameterParser;

import java.io.IOException;

import javax.management.ServiceNotFoundException;

import entities.Usuario;
import entities.Venta;



public class VentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	private CtrlVenta con= new CtrlVenta();
	
	
    public VentaServlet() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Usuario user=Usuario.factory(request);
		Venta ventaActual= (Venta) request.getSession().getAttribute("venta");
		
		try {
			switch (request.getPathInfo()) {
			case "/iniciarVentaLibre": {
					Venta nuevaVenta= con.iniciarVentaLibre(user);
					request.getSession().setAttribute("venta", nuevaVenta);
					request.getRequestDispatcher("/WEB-INF/ui-venta/agregarMedicamentos.jsp").forward(request, response);
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
		Venta ventaActual= (Venta) request.getSession().getAttribute("venta");
		
		try {
			switch (request.getPathInfo()) {
			case "/addMedicamento": {
				
				Integer cantidad= Integer.parseInt(request.getParameter("cantidad")); 
				String nombreMed = request.getParameter("name_med"); 
				
				ventaActual=con.addMedicamento(ventaActual, nombreMed, cantidad, user); 
				
				request.getRequestDispatcher("/WEB-INF/ui-venta/agregarMedicamentos.jsp").forward(request, response);
				break;
			}
			case "/cerrarVenta": {
				ventaActual=con.cerrarVenta(ventaActual, user);
				request.getRequestDispatcher("/WEB-INF/ui-venta/NOSE.jsp").forward(request, response); //TODO que mieda pongo aca????
				
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


