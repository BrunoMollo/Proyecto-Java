package ourLib.Parsers;

import java.io.IOException;
import java.sql.SQLException;

import javax.management.ServiceNotFoundException;

import jakarta.servlet.http.HttpServletResponse;
import ourLib.AppException;



public class ExceptionDispacher{

	
	public static void manage(Exception ex, HttpServletResponse response) throws IOException {
		try {
			throw ex;
		}
		catch(AppException e) {
			e.sendErrorPage(response); 
			e.printStackTrace();
		}
		catch(SQLException e) {
			response.sendError(500, e.getMessage());
		}
		catch(UnsupportedOperationException e) {
			response.sendError(501, e.getMessage());
		}
		catch(ServiceNotFoundException e){
			response.sendError(404, e.getMessage());
		}
		catch (Exception e) {
			response.sendError(500, e.getMessage());
			e.printStackTrace();
		}
	}


}
