package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ourLib.AppException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

import data.VentaDao;
import entities.ObraSocial;
import entities.Usuario;


public class DescargarListadoVentas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		VentaDao VDao=new VentaDao();
			
		FileInputStream stream = null;
		
		try {
			
			Usuario user = Usuario.factory(request);
			if(!user.hasAccess(Usuario.ADMIN)) {throw new AppException("Debe ser admin", 401);}
			
			
			Month thisMonth=LocalDate.now().getMonth();
			int thisYear=LocalDate.now().getYear();
			
			LocalDate startOfThisMonth=LocalDate.of(thisYear, thisMonth, 1);  
			LocalDate startOfNextMonth=LocalDate.now().plusMonths(1);
			
			
			System.out.println(getServletContext().getRealPath("/"));
			
			File file = 
					VDao.getVentasOSasCSV(startOfThisMonth, startOfNextMonth
							,"Listado Ventas "+thisMonth.getValue()+"-"+thisYear+".csv"
							,"C://");
			
			 response.setContentType("application/csv");
		     response.setHeader("Content-Disposition","attachment; filename=\""+ file.getAbsolutePath() + "\"");
			
			
			stream=new FileInputStream(file);
			
			int in;
	        while ((in = stream.read()) != -1) {
	        	response.getWriter().write(in);
	        }
			
			
		} catch (AppException e) {
			e.printStackTrace();
		}
		finally {
			if(stream!=null) {
				stream.close();				
			}
		}
	}


}
