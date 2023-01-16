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
import java.util.stream.Stream;

import data.VentaDao;
import entities.ObraSocial;
import entities.Usuario;


public class EmailObrasSociales extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		VentaDao VDao=new VentaDao();
		
		ObraSocial os= new ObraSocial();
		os.setId(24);
		
		
		FileInputStream stream = null;
		
		try {
			
			Usuario user = Usuario.factory(request);
			if(!user.hasAccess(Usuario.ADMIN)) {throw new AppException("Debe ser admin", 401);}
			
			
			File file = VDao.getVentasOSasCSV(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 2, 1) , os);
			
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
