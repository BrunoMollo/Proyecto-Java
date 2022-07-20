package ourLib.Parsers;

import jakarta.servlet.http.HttpServletRequest;



/**
* <p>Esta clase es un Wrapper para los objetos HttpServletRequest, para acceder a sus parametros 
* ya parseados sin tener que estar validando si los mismos son nulos.</p>
*/
public class RequestParameterParser {
	
	private HttpServletRequest req;

	public RequestParameterParser(HttpServletRequest req) {
		super();
		this.req = req;
	}
	
	public String getString(String name) {
		return req.getParameter(name);
	}
	
	public Integer getInt(String name) {
		try {
			return Integer.parseInt(req.getParameter(name));
		} catch (NumberFormatException  | NullPointerException e) {
			return null;
		}
	}
	
	public Double getDouble(String name) {
		try {
			return Double.parseDouble(req.getParameter(name));
		} catch (NumberFormatException | NullPointerException e ) {
			return null;
		}
	}
	
	
	
}
