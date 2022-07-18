package logic;


import data.ObrasSocialesDao;
import entities.ObraSocial;
import ourLib.LogicAbstraction.BasicCtrl;

public class CtrlObraSocial extends BasicCtrl<ObraSocial, ObrasSocialesDao>{

	public CtrlObraSocial() {
		this.miDao= new ObrasSocialesDao();
	}
	
}
