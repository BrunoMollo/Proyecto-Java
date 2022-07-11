package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.DrogasDao;
import entities.Droga;

public class CtrlDroga {
	private DrogasDao drogaDao;
	
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

	public void delete(Droga drug) throws SQLException {
		// TODO Auto-generated method stub
		drogaDao.delete(drug);
	}
	

}
