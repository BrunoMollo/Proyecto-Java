package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.LineaVenta;
import ourLib.dbUtils.Dao;

public class LineaVentaDao extends Dao<LineaVenta>{

	@Override
	protected LineaVenta mapFromResulset(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new UnsupportedOperationException("Not Implemented "+funcName);
	}
	@Override
	public LineaVenta getOne(LineaVenta p) throws SQLException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new UnsupportedOperationException("Not Implemented "+funcName);
	}

	@Override
	public LinkedList<LineaVenta> getAll() throws SQLException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new UnsupportedOperationException("Not Implemented "+funcName);
	}

	@Override
	public void add(LineaVenta p) throws SQLException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new UnsupportedOperationException("Not Implemented "+funcName);
	}

	@Override
	public void update(LineaVenta p) throws SQLException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new UnsupportedOperationException("Not Implemented "+funcName);
	}

	@Override
	public void delete(LineaVenta p) throws SQLException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new UnsupportedOperationException("Not Implemented "+funcName);
	}

}
