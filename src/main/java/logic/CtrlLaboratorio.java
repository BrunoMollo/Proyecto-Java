package logic;

import java.util.LinkedList;

import data.LaboratorioDao;
import entities.Laboratorio;
import ourLib.LogicAbstraction.BasicCtrl;

public class CtrlLaboratorio extends BasicCtrl<Laboratorio, LaboratorioDao>{
	
	public CtrlLaboratorio() {
		 this.miDao=new LaboratorioDao();
	}

	public LinkedList<Laboratorio> getByPartialName(Laboratorio lab) {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new UnsupportedOperationException("Manga de vagos, implementen "+funcName);
	}
	
}
