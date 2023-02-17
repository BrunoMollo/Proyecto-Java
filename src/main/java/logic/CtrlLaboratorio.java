package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.LaboratorioDao;
import entities.Laboratorio;
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
	
}
