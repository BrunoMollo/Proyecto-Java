package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ourLib.AppException;
import ourLib.Csv;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import data.VentaDao;
import entities.Usuario;


public class DescargarListadoVentas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		VentaDao VDao=new VentaDao();
			
		
		try {
			
			Usuario user = Usuario.factory(request);
			if(!user.hasAccess(Usuario.ADMIN)) {throw new AppException("Debe ser admin", 401);}
			
			
			Month thisMonth=LocalDate.now().getMonth();
			int thisYear=LocalDate.now().getYear();
			
			LocalDate startOfThisMonth=LocalDate.of(thisYear, thisMonth, 1);  
			LocalDate startOfNextMonth=LocalDate.now().plusMonths(1);
			

	
			String filename="Listado Ventas "+thisMonth.getValue()+"-"+thisYear+".csv";
			Csv csv = 
					VDao.getMasVendidosAsCSV(startOfThisMonth, startOfNextMonth,filename);
			
			response.setContentType("application/csv");
		    response.setHeader("Content-Disposition","attachment; filename=\""+filename+"\"");
			
		    response.getWriter().append(csv.getRawData()); 
		    
		} catch (AppException e) {
			e.printStackTrace();
		}
	
	}


}
