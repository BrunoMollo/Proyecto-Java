package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.LaboratorioDao;
import entities.Laboratorio;
import ourLib.LogicAbstraction.BasicCtrl;

public class CtrlLaboratorio extends BasicCtrl<Laboratorio, LaboratorioDao>{
	
	public CtrlLaboratorio() {
		 this.miDao=new LaboratorioDao();
	}

	public LinkedList<Laboratorio> getByPartialName(Laboratorio lab) throws SQLException {
		return miDao.getByPartialName(lab);
	}
	
}
