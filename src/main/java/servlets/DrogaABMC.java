package servlets;


import logic.CtrlDroga;
import ourLib.servletAbstraction.DefaultServlet;
import ourLib.servletAbstraction.Operation;
import ourLib.servletAbstraction.RequestParameterParser;

import java.sql.SQLException;
import java.util.LinkedList;


import data.DrogasDao;
import entities.Droga;

/**
 * Servlet implementation class altaDroga
 */
public class DrogaABMC extends DefaultServlet<Droga,CtrlDroga, DrogasDao> {
	private static final long serialVersionUID = 1L;
	
       
    public DrogaABMC() { 
    	super();
    	this.con=new CtrlDroga();
    	this.jspGetAll="/ui-droga/getAllDroga.jsp";
    	this.jspAddSuccess="/ui-droga/ConfirmarAltaDroga.jsp";
    	
    	this.getOperations.put("getbyname", getByNameAsync);
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
			
			res.getWriter().append("[");
			for(int i=0; i<arr.size(); i++) {
				res.getWriter().append("{\"cod_droga\": "+arr.get(i).getCod()+", \"name_droga\": \""+arr.get(i).getNombre()+"\"}");
				if(i<arr.size()-1) {
					res.getWriter().append(", ");
				}
			}
			res.getWriter().append("]");
			
		} catch (SQLException e) {
			res.sendError(500, e.getMessage());
			e.printStackTrace();
		}
};
    		
    		
    		
    		


	@Override
	protected Droga getEntityFromRequest(RequestParameterParser parser) {
		Droga drug= new Droga();
		drug.setCod(parser.getInt("cod_droga"));
		drug.setNombre(parser.getString("name_droga"));
		return drug;
	}

}
