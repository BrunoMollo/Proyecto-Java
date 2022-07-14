package logic;


import cosas_locas.BasicCtrl;
import data.ObrasSocialesDao;
import entities.ObraSocial;

public class CtrlObraSocial extends BasicCtrl<ObraSocial>{

	public CtrlObraSocial() {
		this.miDao= new ObrasSocialesDao();
	}
	
}
