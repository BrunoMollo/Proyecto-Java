package servlets;


import logic.CtrlDroga;
import ourLib.servletAbstraction.GenericServlet;
import ourLib.servletAbstraction.RequestParameterParser;
import entities.Droga;

/**
 * Servlet implementation class altaDroga
 */
public class DrogaABMC extends GenericServlet<Droga> {
	private static final long serialVersionUID = 1L;
       
    public DrogaABMC() { 
    	super();
    	this.con=new CtrlDroga();
    	this.jspGetAll="/ui-droga/getAllDroga.jsp";
    	this.jspAddSuccess="/ui-droga/ConfirmarAltaDroga.jsp";
    }


	@Override
	protected Droga getEntityFromRequest(RequestParameterParser parser) {
		Droga drug= new Droga();
		drug.setCod(parser.getInt("cod-droga"));
		drug.setNombre(parser.getString("name-droga"));
		return drug;
	}

}
