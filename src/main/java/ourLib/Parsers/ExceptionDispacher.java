package ourLib.Parsers;

import java.io.IOException;
import java.rmi.AccessException;
import java.sql.SQLException;

import javax.management.ServiceNotFoundException;

import jakarta.servlet.http.HttpServletResponse;



public class ExceptionDispacher{

	
	public static void manage(Exception ex, HttpServletResponse response) throws IOException {
		try {
			throw ex;
		}
		catch(SQLException e) {
			response.sendError(500, e.getMessage());
		}
		catch (AccessException e) {
			response.sendError(403, e.getMessage());
		}
		catch(UnsupportedOperationException e) {
			response.sendError(501, e.getMessage());
		}
		catch(ServiceNotFoundException e){
			response.sendError(404, e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}


}
