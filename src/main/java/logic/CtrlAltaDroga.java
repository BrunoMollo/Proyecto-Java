package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.DrogasDao;
import entities.Droga;

public class CtrlAltaDroga {
	DrogasDao drogaDao;
	
	public CtrlAltaDroga() {
		this.drogaDao=new DrogasDao();
	}
	

	public void addDroga(Droga drug) throws SQLException{
		drogaDao.add(drug);
	}
	public LinkedList<Droga> getAll(){
		return drogaDao.getAll();
	}

}
