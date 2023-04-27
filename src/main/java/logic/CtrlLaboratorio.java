package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.LaboratorioDao;
import entities.Laboratorio;
import entities.Usuario;
import ourLib.AppException;
import ourLib.LogicAbstraction.BasicCtrl;

public class CtrlLaboratorio extends BasicCtrl<Laboratorio, LaboratorioDao>{
	
	public CtrlLaboratorio() {
		 this.miDao=new LaboratorioDao();
	}

	public LinkedList<Laboratorio> getByPartialName(Laboratorio lab) throws AppException {
		return miDao.getByPartialName(lab);
	}
	
	public Laboratorio getOneByName(Laboratorio l) throws AppException {
		return miDao.getOneByName(l);
	}
	
	public void verifyUser(Usuario user) throws AppException {
		if(!user.hasAccess(Usuario.ADMIN)) {throw new AppException("Debe ser admin", 401);
	}}
	
}