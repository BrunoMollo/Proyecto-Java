package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.CtrlDroga;
import logic.CtrlMedicamento;
import ourLib.Parsers.ExceptionDispacher;
import ourLib.Parsers.JsonMaker;
import ourLib.Parsers.RequestParameterParser;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;

import data.DosisDao;
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
			Medicamento med= getMedbyName(new RequestParameterParser(request));
			Usuario user=Usuario.factory(request);
			CtrlMedicamento ctrlmed = new CtrlMedicamento();	
			
			try {
				switch (request.getPathInfo().substring(1)){
				
				case "all": {
					LinkedList<Medicamento> arr = ctrlmed.getAll(user);
					response.setContentType("application/json");
					String JsonArr=JsonMaker.getJsonArray(arr);
					response.setStatus(200);	
					response.getWriter().append(JsonArr);							
					break;}
				
					case "getbyname": 
						if(med.getNombre().length()<2) {
				    		response.sendError(400, "largo insuficiente");
				    		return;
				    		}
						
						LinkedList<Medicamento> arr= ctrlmed.getByPartialName(med);
						String JsonArr=JsonMaker.getJsonArray(arr);
						response.getWriter().append(JsonArr);
						response.setStatus(200);
						response.setContentType("application/json");
						break;
					case "redirectAddMed": {
						request.getRequestDispatcher("/WEB-INF/ui-medicamento/altaMedicamento.html").forward(request, response);
						break;
					}
					case "redirectNewPrecio": {
						request.getRequestDispatcher("/WEB-INF/ui-medicamento/ModificarPrecioMedicamento.html").forward(request, response);
						break;
					}
					case "redirectUpdMed": {
						request.getRequestDispatcher("/WEB-INF/ui-medicamento/buscarMedicamento.html").forward(request, response);
						break;
					}
				}}
				 catch (Exception e) {
					ExceptionDispacher.manage(e, response);
				}		
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Medicamento med=null;
		Usuario user=Usuario.factory(request);

		try {
		switch (request.getPathInfo().substring(1)){
			case "inicializarmedicamento":
				med = mapMedicamento(request);				
				request.getSession().setAttribute("medicamento", med);
				request.getRequestDispatcher("/WEB-INF/ui-medicamento/cargaDrogas.jsp").forward(request, response);
				break;
				
			case "cargadosis":
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
				String update = request.getParameter("update");
				if (update == null) {
					request.getRequestDispatcher("/WEB-INF/ui-medicamento/cargaDrogas.jsp").forward(request, response);
				} else {
					request.getRequestDispatcher("/WEB-INF/ui-medicamento/updateDrogas.jsp").forward(request, response);
				}
				
				break;
	
			case "guardarmedicamento":
				CtrlMedicamento ctrlmed = new CtrlMedicamento();

				med=(Medicamento)request.getSession().getAttribute("medicamento");
				
				ctrlmed.add(med, user);
				response.setStatus(201);
				request.setAttribute("medicamento", med);
				request.getRequestDispatcher("/WEB-INF/ui-medicamento/ConfirmarAltaMedicamento.jsp").forward(request, response);
				break;
				
			case "getmedicprecios":
				CtrlMedicamento ctrlme = new CtrlMedicamento();
				Medicamento m = mapMedicamento(request);
				m = ctrlme.getOne(m,user);
				LinkedList<Precio> listaPrecios = ctrlme.getAllPrecios(m);
				request.getSession().setAttribute("medicamento", m);
				request.setAttribute("listaPrecios", listaPrecios);
				request.getRequestDispatcher("/WEB-INF/ui-medicamento/agregarPrecio.jsp").forward(request, response);
				break;
				
			case "addnuevoprecio":
				CtrlMedicamento ctrlm = new CtrlMedicamento();
				Medicamento medicam = (Medicamento) request.getSession().getAttribute("medicamento");	
				Precio nuevo =new Precio();				
				
				nuevo.setFecha((LocalDate.parse(request.getParameter("fechaNuevo"))));
				nuevo.setMonto(Double.parseDouble(request.getParameter("precioNuevo")));
				
				ctrlm.addPrecioNuevo(medicam,nuevo);
				response.sendRedirect("../index.html");
				break;
				
			case "buscarmedic":
				CtrlMedicamento ctrlMed = new CtrlMedicamento();
				String nameMedicamento = (String) request.getAttribute("nomMedicamento");
				Medicamento medicamento = new Medicamento();
				medicamento.setNombre(nameMedicamento);
				med = ctrlMed.getOne(medicamento, user);
				
				if (med == null) {				
					request.getRequestDispatcher("/WEB-INF/ui-medicamento/buscarMedicamento.html").forward(request, response);
				} else {
					request.getSession().setAttribute("medicamento", medicamento);
					request.getRequestDispatcher("/WEB-INF/ui-medicamento/updateMedicamento.jsp").forward(request, response);
				}
				
			case "updatemedicamento": 
				med = mapMedicamentoUpdate(request);				
				request.getSession().setAttribute("medicamento", med);
				request.getRequestDispatcher("/WEB-INF/ui-medicamento/updateDrogas.jsp").forward(request, response);
				break;
				
			case "updatedrogas":
				new CtrlMedicamento().update(med, user); 
				request.getRequestDispatcher("/WEB-INF/ui-medicamento/ConfirmarAltaMedicamento.jsp").forward(request, response);
				break;
		}

		
		} catch (Exception e) {
			ExceptionDispacher.manage(e, response);
		}
	}
	
	
	
	
	
	private Medicamento mapMedicamento(HttpServletRequest req) throws SQLException {
		Medicamento mdic=mapMedicamentoUpdate(req);
		RequestParameterParser parser=new RequestParameterParser(req);
		mdic.setCodigoBarra(parser.getInt("cod_med")); 
		return mdic;
	}
	
	private Medicamento mapMedicamentoUpdate (HttpServletRequest req) throws SQLException {
		Medicamento mdic=new Medicamento();
		RequestParameterParser parser=new RequestParameterParser(req);
		mdic.setNombre(parser.getString("name_med"));
		mdic.setPrecio(parser.getDouble("price_med"));
		mdic.setSize(parser.getDouble("size_med"));
		mdic.setUnidad(parser.getString("unit_med")); 
		
		Laboratorio l=new Laboratorio();
		l.setNombre(parser.getString("name_lab"));
		mdic.setLaboratorio(l);
		
		return mdic;
	}
	//CONSULTAR SI PASAMOS TODO AL METODO DE ABAJO O USAMOS EL DE ARRIBA. 
	private Medicamento getMedbyName(RequestParameterParser parser) {	
		Medicamento m =new Medicamento();
		//m.setCodigo(parser.getInt("cod_lab"));
		m.setNombre(parser.getString("name_med"));
		;
		return m;
	}

}
