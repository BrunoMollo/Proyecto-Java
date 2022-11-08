package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.DrogasDao;
import entities.Droga;
import entities.Usuario;
import ourLib.AppException;
import ourLib.LogicAbstraction.BasicCtrl;

public class CtrlDroga extends BasicCtrl<Droga, DrogasDao>{

	public CtrlDroga() {
		this.miDao=new DrogasDao();
	}
	
	public LinkedList<Droga> getByPartialName(Droga obj) throws AppException {
		return this.miDao.getAllByPartialName(obj);
	}

	public Droga getByName(Droga drug, Usuario user) throws AppException {
		return miDao.getOneByName(drug);
		
	}

}
