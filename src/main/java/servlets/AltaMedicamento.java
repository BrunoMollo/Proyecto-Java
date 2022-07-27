package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logic.CtrlDroga;
import logic.CtrlLaboratorio;

import java.io.IOException;
import java.sql.SQLException;

import entities.Dosis;
import entities.Droga;
import entities.Laboratorio;
import entities.Medicamento;

/**
 * Servlet implementation class AltaMedicamento
 */
public class AltaMedicamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AltaMedicamento() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
		switch (request.getPathInfo()){
			case "/inicializarmedicamento":
				String nombre=request.getParameter("name_med");
				Integer codLab=Integer.parseInt(request.getParameter("lab_med"));
				Double size=Double.parseDouble(request.getParameter("size_med"));
				Double precio=Double.parseDouble(request.getParameter("price_med"));
				
				Medicamento med = new Medicamento();
				med.setNombre(nombre);
				med.setPrecio(precio);
				med.setSize(size);
				
				CtrlLaboratorio ctrl = new CtrlLaboratorio();
				Laboratorio l =new Laboratorio();
				l.setCodigo(codLab);
		
				med.setLaboratorio(ctrl.getOne(l));
				request.getSession().setAttribute("medicamento", med);
				request.getRequestDispatcher("/ui-medicamento/cargaDrogas.jsp").forward(request, response);
				break;
				
			case "/cargadosis":
				Integer cod_dr=Integer.parseInt(request.getParameter("codDrug"));
				Double cant_dr=Double.parseDouble(request.getParameter("cantDrug"));
				Medicamento md=(Medicamento)request.getSession().getAttribute("medicamento");
				
				CtrlDroga ctrld=new CtrlDroga();
				Droga drug=new Droga();
				drug.setCod(cod_dr);
						
				Dosis dose=new Dosis(ctrld.getOne(drug),cant_dr);
				md.addDosis(dose);
				request.getSession().setAttribute("medicamento", md);
				request.getRequestDispatcher("/ui-medicamento/cargaDrogas.jsp").forward(request, response);
				break;
		}
		} catch (SQLException e) {
			response.sendError(500, e.getMessage());
			e.printStackTrace();
		}
	}

}
