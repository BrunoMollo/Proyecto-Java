package cosas_locas;

import java.sql.SQLException;
import java.util.LinkedList;

import dbUtils.Dao;

public class BasicCtrl<ENTITY> {
protected Dao<ENTITY> miDao;
	
	public ENTITY getOne(ENTITY e) throws SQLException {
		return miDao.getOne(e);
	}

	public void add(ENTITY e) throws SQLException{
		miDao.add(e);
	}
	
	public LinkedList<ENTITY> getAll() throws SQLException{
		return miDao.getAll();
	}
	
	public void update(ENTITY e) throws SQLException {
		miDao.update(e);
	}

	public void delete(ENTITY e) throws SQLException {
		miDao.delete(e);
	}
	
}
