package servlets;


import logic.CtrlDroga;


import cosas_locas.GenericServlet;
import cosas_locas.RequestParameterParser;
import entities.Droga;

/**
 * Servlet implementation class altaDroga
 */
public class DrogaABMC extends GenericServlet<Droga> {
	private static final long serialVersionUID = 1L;
       
    public DrogaABMC() { 
    	super();
    	this.con=new CtrlDroga();
    	this.JSPGetAll="/ui-droga/getAllDroga.jsp";
    	this.redirectAdd="/ui-droga/ConfirmarAltaDroga.jsp";
    	this.redirectUpdate="/lafarmacia/ABMC-droga/all";
    	this.redirectDelete="/lafarmacia/ABMC-droga/all";
    }


	@Override
	protected Droga getEntityFromRequest(RequestParameterParser parser) {
		Droga drug= new Droga();
		drug.setCod(parser.getInt("cod-droga"));
		drug.setNombre(parser.getString("name-droga"));
		return drug;
	}

}
