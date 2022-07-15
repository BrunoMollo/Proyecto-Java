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
    	this.JSPGetAll="getAllDroga.jsp";
    	this.redirectAdd="/ui-droga/ConfirmarAltaDroga.jsp";
    	this.redirectUpdate="/lafarmacia/ui-droga/ABMC-droga";
    	this.redirectDelete="/lafarmacia/ui-droga/ABMC-droga";
    }


	@Override
	protected Droga getEntityFromRequest(RequestParameterParser parser) {
		Droga drug= new Droga();
		drug.setCod(parser.getInt("cod-droga"));
		drug.setNombre(parser.getString("name-droga"));
		return drug;
	}

}
