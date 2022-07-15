package servlets;


import jakarta.servlet.http.HttpServletRequest;
import logic.CtrlDroga;



import cosas_locas.GenericServlet;
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
	protected Droga getEntityFromRequest(HttpServletRequest request) {
		String name= request.getParameter("name-droga");
		
		Integer cod=null;
		if(request.getParameter("cod-droga")!=null) {
			cod=Integer.parseInt(request.getParameter("cod-droga"));
		}
		Droga drug= new Droga();
		drug.setCod(cod);
		drug.setNombre(name);
		return drug;
	}

}
