package logic;

import java.rmi.AccessException;
import java.sql.SQLException;
import java.time.LocalDate;

import data.DosisDao;
import data.MedicamentoDao;
import data.PrecioDao;
import entities.Dosis;
import entities.Medicamento;
import entities.Precio;
import entities.Usuario;
import ourLib.LogicAbstraction.BasicCtrl;

public class CtrlMedicamento extends BasicCtrl<Medicamento, MedicamentoDao>{
	
	public CtrlMedicamento() {
		this.miDao=new MedicamentoDao();
	}
	
	public void add(Medicamento e, Usuario user) throws SQLException, AccessException{
		super.add(e,user);
		
		DosisDao ddao=new DosisDao();
		for (Dosis dose : e.getAllDosis().values()) {
			ddao.add(e,dose);			
		}
		
		PrecioDao pdao=new PrecioDao();
		Precio precio=new Precio(LocalDate.now(),e.getPrecio());
		pdao.add(e,precio);
	}
}
