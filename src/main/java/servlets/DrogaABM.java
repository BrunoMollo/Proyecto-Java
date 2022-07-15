package servlets;


import logic.CtrlDroga;


import cosas_locas.GenericServlet;
import cosas_locas.RequestParameterParser;
import entities.Droga;

/**
 * Servlet implementation class altaDroga
 */
public class DrogaABM extends GenericServlet<Droga> {
	private static final long serialVersionUID = 1L;
       
    public DrogaABM() { 
    	super();
    	this.con=new CtrlDroga();
    	this.JSPGetAll="getAllDroga.jsp";
    	this.redirectAdd="ConfirmarAltaDroga.jsp";
    	this.redirectUpdate="ABM-droga";
    	this.redirectDelete="ABM-droga";
    }


	@Override
	protected Droga getEntityFromRequest(RequestParameterParser parser) {
		Droga drug= new Droga();
		drug.setCod(parser.getInt("cod-droga"));
		drug.setNombre(parser.getString("name-droga"));
		return drug;
	}

}
