package servlets;


import logic.CtrlDroga;
import ourLib.Parsers.RequestParameterParser;
import ourLib.servletAbstraction.DefaultServlet;
import ourLib.servletAbstraction.Operation;
import ourLib.Parsers.JsonMaker;

import java.sql.SQLException;
import java.util.LinkedList;


import data.DrogasDao;
import entities.Droga;
import entities.Usuario;

/**
 * Servlet implementation class altaDroga
 */
public class DrogaABMC extends DefaultServlet<Droga,CtrlDroga, DrogasDao> {
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected Droga getEntityFromRequest(RequestParameterParser parser) {
		Droga drug= new Droga();
		drug.setCod(parser.getInt("cod_droga"));
		drug.setNombre(parser.getString("name_droga"));
		return drug;
	}
       
    public DrogaABMC() { 
    	super();
    	this.con=new CtrlDroga();
    	this.jspGetAll="/ui-droga/getAllDroga.jsp";
    	this.jspAddSuccess="/ui-droga/ConfirmarAltaDroga.jsp";
    	
    	this.GET.setPath("getbyname", getByNameAsync, null);
    }
    
    private Operation<Droga> getByNameAsync= (obj, req, res)-> {
    	if(obj.getNombre().length()<2) {
    		res.sendError(400, "largo insificuente");
    		return;
    	}
		try {
			LinkedList<Droga> arr=con.getByPartialName(obj);
			res.setStatus(200);
			res.setContentType("application/json");
			
			String JsonArr=JsonMaker.getJsonArray(arr);
			res.getWriter().append(JsonArr);
			
		} catch (SQLException e) {
			res.sendError(500, e.getMessage());
			e.printStackTrace();
		}
};
    		


}
