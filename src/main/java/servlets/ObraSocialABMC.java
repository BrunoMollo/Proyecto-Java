package servlets;

import java.sql.SQLException;
import java.util.LinkedList;

import data.ObrasSocialesDao;
import entities.ObraSocial;
import jakarta.servlet.http.HttpServlet;
import logic.CtrlObraSocial;
import ourLib.Parsers.JsonMaker;
import ourLib.Parsers.RequestParameterParser;
import ourLib.servletAbstraction.DefaultServlet;
import ourLib.servletAbstraction.Operation;

/**
 * Servlet implementation class ObraSocialABMC
 */
public class ObraSocialABMC extends DefaultServlet<ObraSocial, CtrlObraSocial, ObrasSocialesDao> {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObraSocialABMC() {
        super();
        this.con=new CtrlObraSocial();
        this.jspGetAll="/ui-obraSocial/showAllObrasSociales.jsp";
        this.jspAddSuccess="/ui-obraSocial/ConfirmarAltaObraSocial.jsp";
        
        this.getOperations.put("getbyname", getbyName);
        
    }
    
    private Operation<ObraSocial> getbyName=(os, req, res)->{
    	try {
			LinkedList<ObraSocial> obrasSociales=con.getAllByName(os);
			String JsonArr = JsonMaker.getJsonArray(obrasSociales);
			
			res.setStatus(200);
			res.setContentType("application/json");
			res.getWriter().append(JsonArr);
			
		} catch (SQLException e) {
			res.sendError(500, e.getMessage());
			e.printStackTrace();
		}
    	
    	
    	
    };
    
    

	@Override
	protected ObraSocial getEntityFromRequest(RequestParameterParser parser) {
		ObraSocial os= new ObraSocial();
		os.setId(parser.getInt("id_os"));
		os.setNombre(parser.getString("name_os"));
		os.setEmail(parser.getString("email_os"));
		os.setTelefono(parser.getString("phone_os"));
		os.setDescuento(parser.getDouble("discount_os"));
		return os;
	}

}
