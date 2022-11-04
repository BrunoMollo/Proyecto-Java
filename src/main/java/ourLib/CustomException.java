package ourLib;

import java.io.IOException;

import jakarta.servlet.http.HttpServletResponse;

public class CustomException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private int statusCode;
	
	CustomException(String msj, int statusCode){
		super(msj);
		this.statusCode=statusCode;
	}
	
	public void sendErrorPage(HttpServletResponse response){
		try {
			response.sendError(statusCode, this.getMessage());
			this.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
