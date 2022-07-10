package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.DrogasDao;
import entities.Droga;

public class CtrlDroga {
	DrogasDao drogaDao;
	
	public CtrlDroga() {
		this.drogaDao=new DrogasDao();
	}
	

	public void addDroga(Droga drug) throws SQLException{
		drogaDao.add(drug);
	}
	
	public LinkedList<Droga> getAll() throws SQLException{
		return drogaDao.getAll();
	}
	
	public void update(Droga d) throws SQLException {
		drogaDao.update(d);
	}
	

}
