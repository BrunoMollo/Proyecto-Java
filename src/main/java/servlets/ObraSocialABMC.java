package servlets;

import cosas_locas.GenericServlet;
import cosas_locas.RequestParameterParser;
import entities.ObraSocial;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import logic.CtrlObraSocial;

/**
 * Servlet implementation class ObraSocialABMC
 */
public class ObraSocialABMC extends GenericServlet<ObraSocial> {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObraSocialABMC() {
        super();
        this.con=new CtrlObraSocial();
        this.JSPGetAll="/ui-obraSocial/showAllObrasSociales.jsp";
        this.redirectAdd="/ui-obraSocial/ConfirmarAltaObraSocial.jsp";
    }

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
