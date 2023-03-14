package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ourLib.AppException;
import ourLib.Csv;
import ourLib.EmailService;
import ourLib.Parsers.ExceptionDispacher;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import data.ObrasSocialesDao;
import data.VentaDao;
import entities.ObraSocial;
import entities.Usuario;


class EmailThread extends Thread{
	
	private Boolean successFlag=true;
	public boolean didSucceeded() {
		return successFlag;
	}
	
	private ObraSocial os;
	private EmailService email;
	private VentaDao VDao;
	
	
	public EmailThread( ObraSocial _os, EmailService _email, VentaDao _VDao) {
		os=_os;
		email=_email;
		VDao=_VDao;
	}
	
	@Override
	public void run() {
		System.out.println("Inicio envio para "+os.getNombre());
		
		Csv file;
		try {
			Month month=LocalDate.now().getMonth();
			file = VDao.getVentasOSasCSV(LocalDate.of(2023, month, 1), LocalDate.of(2023, month.plus(1), 1) , os);
			
			email.send(os.getEmail(),
					"Ventas a reintegrar de "+os.getNombre(),
					"Adjuntamos a este mail el listado de ventas realizadas por sus afiliados \n Atte. lafarmacia",
					file
				);
		} catch (AppException | IOException | SQLException e) {
			successFlag=false;
			e.printStackTrace();
		}
		
		System.out.println("Fin envio para "+os.getNombre());
	}
	
	
}



public class EmailObrasSociales extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ObrasSocialesDao osDao= new ObrasSocialesDao();
		EmailService email=new EmailService();
		VentaDao VDao=new VentaDao();
		
		try {
			
			Usuario user = Usuario.factory(request);
			if(!user.hasAccess(Usuario.ADMIN)) {throw new AppException("Debe ser admin", 401);}
			
			LinkedList<ObraSocial> obrasSociales=osDao.getAll();
			
			
			Boolean success=true;
			LinkedList<EmailThread> threads= new LinkedList<EmailThread>();
			
			
			for(ObraSocial os: obrasSociales) {
				EmailThread t=new EmailThread(os,email,VDao);
				threads.add(t);
				t.start();
			}
			
			for(EmailThread t: threads) {
				t.join();
			}
			
			for(EmailThread t: threads) {
				if(t.didSucceeded()==false) {
					response.setStatus(500);
					return;
				}
			}
			
			response.setStatus(200);				
			
			
		} 
		catch (Exception e) {
			ExceptionDispacher.manage(e, response);
		}

		
		
	}


}
