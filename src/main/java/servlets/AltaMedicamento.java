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
import ourLib.Parsers.JsonMaker;
import ourLib.Parsers.RequestParameterParser;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import entities.Dosis;
import entities.Droga;
import entities.Laboratorio;
import entities.Medicamento;
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
				}}
				 catch (Exception e) {
					ExceptionDispacher.manage(e, response);
				}		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Medicamento med=null;
		Usuario user=Usuario.factory(request);
		CtrlMedicamento ctrlmed = new CtrlMedicamento();		
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
				
				med=(Medicamento)request.getSession().getAttribute("medicamento");
				
				ctrlmed.add(med, user);
				response.setStatus(201);
				request.setAttribute("medicamento", med);
				request.getRequestDispatcher("/WEB-INF/ui-medicamento/ConfirmarAltaMedicamento.jsp").forward(request, response);
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
