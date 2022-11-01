package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.CtrlDroga;
import logic.CtrlLaboratorio;
import logic.CtrlMedicamento;
import ourLib.Parsers.ExceptionDispacher;
import ourLib.Parsers.RequestParameterParser;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;

import entities.Dosis;
import entities.Droga;
import entities.Laboratorio;
import entities.Medicamento;
import entities.Precio;
import entities.Usuario;

/**
 * Servlet implementation class AltaMedicamento
 */
public class AltaMedicamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Medicamento med=null;
		Usuario user=Usuario.factory(request);
		CtrlMedicamento ctrlmed;
				
		try {
		switch (request.getPathInfo()){
			case "/inicializarmedicamento":
				med = mapMedicamento(request);				
				request.getSession().setAttribute("medicamento", med);
				request.getRequestDispatcher("/WEB-INF/ui-medicamento/cargaDrogas.jsp").forward(request, response);
				break;
				
			case "/cargadosis":
				String name_droga=request.getParameter("name_droga");
				Double cant_dr=Double.parseDouble(request.getParameter("cantDrug"));
				String unidad=request.getParameter("unit_dose");
				med=(Medicamento)request.getSession().getAttribute("medicamento");
				
				CtrlDroga ctrld=new CtrlDroga();
				Droga drug=new Droga();
				drug.setNombre(name_droga);
						
				Dosis dose=new Dosis(ctrld.getByName(drug,user),cant_dr,unidad);
				med.addDosis(dose);
				request.getSession().setAttribute("medicamento", med);
				request.getRequestDispatcher("/WEB-INF/ui-medicamento/cargaDrogas.jsp").forward(request, response);
				break;
			case "/guardarmedicamento":
				ctrlmed = new CtrlMedicamento();
				med=(Medicamento)request.getSession().getAttribute("medicamento");
				
				ctrlmed.add(med, user);
				response.setStatus(201);
				request.setAttribute("addedObject", med);
				request.getRequestDispatcher("/WEB-INF/ui-medicamento/ConfirmarAltaMedicamento.jsp").forward(request, response);
				break;
				
			case "/getmedicprecios":
				ctrlmed = new CtrlMedicamento();
				med = mapMedicamento(request);		
				med = ctrlmed.getOne(med,user);
				LinkedList<Precio> listaPrecios=ctrlmed.getAllPrecios(med);
				request.getSession().setAttribute("medicamento", med);
				request.getSession().setAttribute("listaPrecios", listaPrecios);
				request.getRequestDispatcher("/WEB-INF/ui-medicamento/agregarPrecio.jsp").forward(request, response);
				break;
				
			case "/addnuevoprecio":
				ctrlmed = new CtrlMedicamento();
				med = mapMedicamento(request);	
				Precio nuevo =new Precio();
				
				nuevo.setFecha((LocalDate.parse(request.getParameter("fechaNuevo"))));
				nuevo.setMonto(Double.parseDouble(request.getParameter("precioNuevo")));
				
				ctrlmed.addPrecioNuevo(med,nuevo);
				response.sendRedirect("indexLog.html");
				break;
		}
		
		} catch (Exception e) {
			ExceptionDispacher.manage(e, response);
		}
	}
	
	
	
	
	
	private Medicamento mapMedicamento(HttpServletRequest req) throws SQLException {
		Medicamento mdic=new Medicamento();
		RequestParameterParser parser=new RequestParameterParser(req);
		mdic.setCodigoBarra(parser.getInt("cod_med")); 
		mdic.setNombre(parser.getString("name_med"));
		mdic.setPrecio(parser.getDouble("size_med"));
		mdic.setSize(parser.getDouble("price_med"));
		mdic.setUnidad(parser.getString("unit_med")); 
		
		Laboratorio l=new Laboratorio();
		l.setNombre(parser.getString("name_lab"));
		mdic.setLaboratorio(l);
		
		return mdic;
	}

}
