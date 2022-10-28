package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Venta;
import ourLib.dbUtils.Dao;

public class VentaDao extends Dao<Venta>{

	@Override
	protected Venta mapFromResulset(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new UnsupportedOperationException("Not Implemented "+funcName);
	}

	@Override
	public Venta getOne(Venta p) throws SQLException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new UnsupportedOperationException("Not Implemented "+funcName);
	}

	@Override
	public LinkedList<Venta> getAll() throws SQLException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new UnsupportedOperationException("Not Implemented "+funcName);
	}

	@Override
	public void add(Venta p) throws SQLException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new UnsupportedOperationException("Not Implemented "+funcName);
	}

	@Override
	public void update(Venta p) throws SQLException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new UnsupportedOperationException("Not Implemented "+funcName);
	}

	@Override
	public void delete(Venta p) throws SQLException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new UnsupportedOperationException("Not Implemented "+funcName);
	}

}
