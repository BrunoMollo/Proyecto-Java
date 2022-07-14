package logic;

import cosas_locas.BasicCtrl;
import data.LaboratorioDao;
import entities.Laboratorio;

public class CtrlLaboratorio extends BasicCtrl<Laboratorio>{
	
	public CtrlLaboratorio() {
		 this.miDao=new LaboratorioDao();
	}
	
}
