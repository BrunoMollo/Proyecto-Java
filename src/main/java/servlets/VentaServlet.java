package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.CtrlCliente;
import logic.CtrlObraSocial;
import logic.CtrlVenta;
import ourLib.AppException;
import ourLib.Parsers.ExceptionDispacher;
import ourLib.Parsers.RequestParameterParser;

import java.io.Console;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;

import javax.management.ServiceNotFoundException;


import data.ClienteDao;
import entities.Cliente;
import entities.LineaVenta;
import entities.ObraSocial;
import entities.Usuario;
import entities.Venta;



public class VentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
    public VentaServlet() { super(); }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Usuario user=Usuario.factory(request);

		CtrlVenta con= (CtrlVenta) request.getSession().getAttribute("CtrlVenta");
		String query= request.getPathInfo();
		try {
			switch (query) {
				case "/iniciarVentaLibre": {
					request.getSession().setAttribute("cliente", null);
					con = new CtrlVenta();
					con.iniciarVenta(user);
					request.getSession().setAttribute("CtrlVenta", con);
					request.getRequestDispatcher("/WEB-INF/ui-venta/agregarMedicamentos.jsp").forward(request, response);
					break;
				}
				case "/iniciarVentaOS": {
					request.getSession().setAttribute("cliente", null);
					con = new CtrlVenta();
					con.iniciarVenta(user);
					request.getSession().setAttribute("CtrlVenta", con);
					request.getRequestDispatcher("/WEB-INF/ui-venta/buscarCliente.html").forward(request, response);
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
		
		CtrlVenta con= (CtrlVenta) request.getSession().getAttribute("CtrlVenta");
		CtrlCliente ccli = new CtrlCliente();
		CtrlObraSocial ctrlOS= new CtrlObraSocial();
		Usuario user=Usuario.factory(request);
		
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
				Double dcto = con.getVenta().getCliente().getObraSocial().getDescuento();	
				Boolean medEncontrado=con.addMedicamento(nombreMed, cantidad,dcto); 		
				request.setAttribute("medEncontrado", medEncontrado);			
				request.getRequestDispatcher("/WEB-INF/ui-venta/agregarMedicamentosOS.jsp").forward(request, response);
				break;
			}
			case "/buscarCliente": {
				Integer dni= Integer.parseInt(request.getParameter("dniCliente"));
				Cliente c = ccli.getOne(new Cliente(dni));
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
				Integer cantProd=con.getVenta().getLineas().size();
				
				if (cantProd == 0) {
					String action = strNroRec == null ? "agregarMedicamentos.jsp" : "agregarMedicamentosOS.jsp";
					request.getRequestDispatcher("/WEB-INF/ui-venta/" + action).forward(request, response);
				} else {
					con.getVenta().setNroReceta(nroRec);
					con.cerrarVenta();			
					request.getRequestDispatcher("/WEB-INF/ui-venta/imprimirVenta.jsp").forward(request, response);	
				}	
				
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


