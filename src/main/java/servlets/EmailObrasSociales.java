package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ourLib.AppException;
import ourLib.EmailService;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import data.ObrasSocialesDao;
import data.VentaDao;
import entities.ObraSocial;
import entities.Usuario;


public class EmailObrasSociales extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		VentaDao VDao=new VentaDao();
		ObrasSocialesDao osDao= new ObrasSocialesDao();
		EmailService email=new EmailService();
		
		try {
			
			Usuario user = Usuario.factory(request);
			if(!user.hasAccess(Usuario.ADMIN)) {throw new AppException("Debe ser admin", 401);}
			
			LinkedList<ObraSocial> obrasSociales=osDao.getAll();
			
			for(ObraSocial os: obrasSociales) {
				
				File file = VDao.getVentasOSasCSV(LocalDate.of(2023, 1, 1), LocalDate.of(2023, 2, 1) , os);
				
				email.send(os.getEmail(),
						"Ventas a reintegrar de "+os.getNombre(),
						"Adjuntamos a este mail el listado de ventas realizadas por sus afiliados \n Atte. lafarmacia",
						file
					);
			}
			
			response.setStatus(200);
			
			
		} catch (AppException e) {
			e.printStackTrace();
		}

		
		
	}


}
