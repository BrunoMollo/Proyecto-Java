package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.DrogasDao;
import entities.Droga;
import ourLib.LogicAbstraction.BasicCtrl;

public class CtrlDroga extends BasicCtrl<Droga, DrogasDao>{

	public CtrlDroga() {
		this.miDao=new DrogasDao();
	}
	
	public LinkedList<Droga> getByPartialName(Droga obj) throws SQLException {
		return this.miDao.getByPartialName(obj);
	}

}
