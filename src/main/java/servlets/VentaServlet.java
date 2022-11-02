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

	
    public VentaServlet() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Usuario user=Usuario.factory(request);
		CtrlVenta con= (CtrlVenta) request.getSession().getAttribute("CtrlVenta");
		
		try {
			switch (request.getPathInfo()) {
			case "/iniciarVentaLibre": {
					con = new CtrlVenta();
					con.iniciarVentaLibre(user);
					request.getSession().setAttribute("CtrlVenta", con);
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
		CtrlVenta con= (CtrlVenta) request.getSession().getAttribute("CtrlVenta");
		
		try {
			switch (request.getPathInfo()) {
			case "/addMedicamento": {
				
				Integer cantidad= Integer.parseInt(request.getParameter("cantidad")); 
				String nombreMed = request.getParameter("name_med"); 
				
				con.addMedicamento(nombreMed, cantidad); 
				
				request.getRequestDispatcher("/WEB-INF/ui-venta/agregarMedicamentos.jsp").forward(request, response);
				break;
			}
			case "/cerrarVenta": {
				con.cerrarVenta();
				response.sendRedirect("/lafarmacia");//volver al menu de vendedro �?
				
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

