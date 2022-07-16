package logic;


import data.ObrasSocialesDao;
import entities.ObraSocial;
import ourLib.LogicAbstraction.BasicCtrl;

public class CtrlObraSocial extends BasicCtrl<ObraSocial>{

	public CtrlObraSocial() {
		this.miDao= new ObrasSocialesDao();
	}
	
}
