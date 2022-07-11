package logic;

import java.sql.SQLException;

import data.ObrasSocialesDao;
import entities.ObraSocial;

public class CtrlObraSocial {

	private ObrasSocialesDao osDao;
	
	public CtrlObraSocial() {
		osDao= new ObrasSocialesDao();
	}
	
	public void add(ObraSocial os) throws SQLException {
		osDao.add(os);
	}
	
	
}
