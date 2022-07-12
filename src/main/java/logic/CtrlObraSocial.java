package logic;

import java.sql.SQLException;
import java.util.LinkedList;

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

	public LinkedList<ObraSocial> getAll() throws SQLException {
		return osDao.getAll();
	}

	public void update(ObraSocial os) throws SQLException {
		osDao.update(os);
	}
	
	
}
