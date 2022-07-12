package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.CtrlObraSocial;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;

import com.mysql.cj.xdevapi.JsonParser;

import entities.ObraSocial;

/**
 * Servlet implementation class ModifyObraSocial
 */
public class ModifyObraSocial extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyObraSocial() { super(); }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CtrlObraSocial con= new CtrlObraSocial();
		
		try {
			LinkedList<ObraSocial> arr= con.getAll();
			
			request.setAttribute("obrasSociales", arr);
			request.getRequestDispatcher("showAllObrasSociales.jsp").forward(request, response);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		JSONParser parser = new JSONParser(req.getReader().readLine());
		ObraSocial os= new ObraSocial();
		CtrlObraSocial con = new CtrlObraSocial();
		
		try {
			LinkedHashMap<String, Object> data = parser.object();
			
			
			os.setId( Integer.parseInt(data.get("id").toString()) );
			os.setNombre(data.get("nombre").toString());
			os.setTelefono(data.get("telefono").toString());
			os.setEmail(data.get("email").toString());
			os.setDescuento( Double.parseDouble(data.get("discount").toString()) );
			
			con.update(os);
			
			resp.setStatus(200);
			
		} 
		catch (ParseException e) {
			resp.setStatus(490);
			e.printStackTrace();
		}
		catch (SQLException e) {
			resp.setStatus(510);
			e.printStackTrace();
		}

	}

}
