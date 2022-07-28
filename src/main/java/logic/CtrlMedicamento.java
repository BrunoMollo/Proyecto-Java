package logic;

import java.rmi.AccessException;
import java.sql.SQLException;

import data.DosisDao;
import data.LaboratorioDao;
import data.MedicamentoDao;
import entities.Dosis;
import entities.Medicamento;
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
	}
}
