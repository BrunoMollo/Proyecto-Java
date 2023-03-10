package logic;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;

import data.DosisDao;
import data.LaboratorioDao;
import data.MedicamentoDao;
import data.PrecioDao;
import entities.Dosis;
import entities.Laboratorio;
import entities.Medicamento;
import entities.Precio;
import entities.Usuario;
import ourLib.AppException;
import ourLib.LogicAbstraction.BasicCtrl;

public class CtrlMedicamento extends BasicCtrl<Medicamento, MedicamentoDao>{
	
	public CtrlMedicamento() {
		this.miDao=new MedicamentoDao();
	}
	
	
	public LinkedList<Medicamento> getByPartialName(Medicamento obj) throws SQLException, AppException {
		return this.miDao.getAllByPartialName(obj);
	}
	public LinkedList<Medicamento> getAll() throws  AppException {
		return this.miDao.getAll();
	}
	
	public void add(Medicamento med, Usuario user) throws AppException{
		if(!user.hasAccess(Usuario.ADMIN)) {throw new AppException("Debe ser admin", 401);}
		
		//Requiero guardar la clave primaria en el registro de la base datos, no me sirve tener solo en nombre
		Laboratorio mi_lab= new LaboratorioDao().getOneByName(med.getLaboratorio());
		if(mi_lab==null) {throw new AppException("No se encontro el laboratorio",401); }
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

	public LinkedList<Precio> getAllPrecios(Medicamento med) throws AppException {
		PrecioDao pdao=new PrecioDao();
		return pdao.getPrices(med);
	}
	
	public void addPrecioNuevo(Medicamento med, Precio precioNuevo) throws AppException{
		PrecioDao pdao=new PrecioDao();
		pdao.add(med, precioNuevo);
		
	}
	
	public Boolean checkBarcode(Medicamento m) throws AppException {
		return (miDao.checkBarcode(m)==null)?true:false;		
	}
	
}
