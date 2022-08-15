package logic;

import java.rmi.AccessException;
import java.sql.SQLException;
import java.time.LocalDate;

import data.DosisDao;
import data.LaboratorioDao;
import data.MedicamentoDao;
import data.PrecioDao;
import entities.Dosis;
import entities.Laboratorio;
import entities.Medicamento;
import entities.Precio;
import entities.Usuario;
import ourLib.LogicAbstraction.BasicCtrl;

public class CtrlMedicamento extends BasicCtrl<Medicamento, MedicamentoDao>{
	
	public CtrlMedicamento() {
		this.miDao=new MedicamentoDao();
	}
	
	public void add(Medicamento med, Usuario user) throws SQLException, AccessException{
		if(!user.hasAccess(Usuario.ADMIN)) {throw new AccessException("Debe ser admin");}
		
		//Requiero guardar la clave primaria en el registro de la base datos, no me sirve tener solo en nombre
		Laboratorio mi_lab= new LaboratorioDao().getOneByName(med.getLaboratorio());
		if(mi_lab==null) {throw new SQLException("No se encontro el laboratorio"); }
		med.setLaboratorio(mi_lab);
		
		miDao.add(med);
		
		DosisDao ddao=new DosisDao();
		for (Dosis dose : med.getAllDosis().values()) {
			ddao.add(med,dose);			
		}
		
		PrecioDao pdao=new PrecioDao();
		Precio precio=new Precio(LocalDate.now(),med.getPrecio());
		pdao.add(med,precio);
	}
}
