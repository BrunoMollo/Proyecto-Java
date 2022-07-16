package logic;

import data.LaboratorioDao;
import entities.Laboratorio;
import ourLib.LogicAbstraction.BasicCtrl;

public class CtrlLaboratorio extends BasicCtrl<Laboratorio>{
	
	public CtrlLaboratorio() {
		 this.miDao=new LaboratorioDao();
	}
	
}
