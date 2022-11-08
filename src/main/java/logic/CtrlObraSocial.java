package logic;


import java.util.LinkedList;

import data.ObrasSocialesDao;
import entities.ObraSocial;
import ourLib.AppException;
import ourLib.LogicAbstraction.BasicCtrl;

public class CtrlObraSocial extends BasicCtrl<ObraSocial, ObrasSocialesDao>{

	public CtrlObraSocial() {
		this.miDao= new ObrasSocialesDao();
	}

	public LinkedList<ObraSocial> getAllByName(ObraSocial os) throws AppException {
		return miDao.getAllByName(os);
	}
	
	
	
}
