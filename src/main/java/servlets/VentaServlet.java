package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.CtrlObraSocial;
import logic.CtrlVenta;
import ourLib.AppException;
import ourLib.Parsers.ExceptionDispacher;
import ourLib.Parsers.RequestParameterParser;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;

import javax.management.ServiceNotFoundException;

import entities.Cliente;
import entities.LineaVenta;
import entities.ObraSocial;
import entities.Usuario;
import entities.Venta;
import ourLib.AppException;



public class VentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
    public VentaServlet() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Usuario user=Usuario.factory(request);
		CtrlVenta con= (CtrlVenta) request.getSession().getAttribute("CtrlVenta");
		String query= request.getPathInfo();
		try {
			switch (request.getPathInfo()) {
			case "/iniciarVentaLibre": {
					con = new CtrlVenta();
					con.iniciarVentaLibre(user);
					request.getSession().setAttribute("CtrlVenta", con);
					request.getRequestDispatcher("/WEB-INF/ui-venta/agregarMedicamentos.jsp").forward(request, response);
				break;
			}
			case "/listaVentas": {
				LinkedList<ObraSocial> obraSociales = new CtrlObraSocial().getAll(user);
				con = new CtrlVenta();
				request.getSession().setAttribute("CtrlVenta", con);
				request.setAttribute("listOS", obraSociales);
				request.getRequestDispatcher("/WEB-INF/ui-venta/busquedaVentas.jsp").forward(request, response);
				break;
			}
			case "/detalle": {
				con = new CtrlVenta();
				request.getSession().setAttribute("CtrlVenta", con);
				String[] queryString = request.getQueryString().split("=");
				Venta v= new Venta();
				v.setNroVenta(Integer.parseInt(queryString[1]));;
				v = con.getOne(user, v);
				LinkedList<LineaVenta> lventa= con.detalleVenta(user,v);
				request.setAttribute("lventa", lventa);
				request.setAttribute("venta", v);
				request.getRequestDispatcher("/WEB-INF/ui-venta/detalle.jsp").forward(request, response);
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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Usuario user=Usuario.factory(request);
		CtrlVenta con= (CtrlVenta) request.getSession().getAttribute("CtrlVenta");
		
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
			case "/cerrarVenta": {
				con.cerrarVenta();
				response.sendRedirect("../Redirect");//volver al menu de vendedro �?
				
				break;
			}
			case "/OSSales": {
				LocalDate fechaDesde= LocalDate.parse(request.getParameter("fechaDesde"), Cliente.dFormat);
				LocalDate fechaHasta = LocalDate.parse(request.getParameter("fechaHasta"), Cliente.dFormat);
				ObraSocial os = new ObraSocial();
				os.setId(Integer.parseInt(request.getParameter("id_os")));
				os = new CtrlObraSocial().getOne(os,user);
				LinkedList<Venta> ventas=con.listarVentas(user, fechaDesde, fechaHasta, os);
				request.setAttribute("ventas", ventas);
				request.setAttribute("fechaDesde", fechaDesde);
				request.setAttribute("fechaHasta", fechaHasta);
				request.setAttribute("os", os);
				request.getRequestDispatcher("/WEB-INF/ui-venta/listaVentas.jsp").forward(request, response);
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


