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
		String text=req.getParameter(name);
		if(text!=null) {
			return Integer.parseInt(text);
		}
		else {return null;}
	}
	
	public Double getDouble(String name) {
		String text=req.getParameter(name);
		if(text!=null) {
			return Double.parseDouble(text);
		}
		else {return null;}
	}
	
	
	
}
