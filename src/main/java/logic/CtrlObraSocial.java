package logic;


import java.rmi.AccessException;
import java.sql.SQLException;
import java.util.LinkedList;

import data.ObrasSocialesDao;
import entities.ObraSocial;
import ourLib.LogicAbstraction.BasicCtrl;

public class CtrlObraSocial extends BasicCtrl<ObraSocial, ObrasSocialesDao>{

	public CtrlObraSocial() {
		this.miDao= new ObrasSocialesDao();
	}

	public LinkedList<ObraSocial> getAllByName(ObraSocial os) throws SQLException {
		return miDao.getAllByName(os);
	}
	
	
	
}
