package cosas_locas;

import jakarta.servlet.http.HttpServletRequest;

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
