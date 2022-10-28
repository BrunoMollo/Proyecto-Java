package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.LineaVenta;
import entities.Venta;
import ourLib.dbUtils.Dao;
import ourLib.dbUtils.StatementWrapper;

public class LineaVentaDao extends Dao<LineaVenta>{

	@Override
	protected LineaVenta mapFromResulset(ResultSet rs) throws SQLException {
		LineaVenta lv=new LineaVenta();
		lv.setNroVenta(rs.getInt("nroVenta"));
		lv.setCodBarra(rs.getInt("codBarra"));
		lv.setCantidad(rs.getInt("cantidad"));
		lv.setPrecioUnidad(rs.getDouble("precioUnidad"));
		return lv;
	}
	public LinkedList<LineaVenta> getAllFromVenta(Venta v) throws SQLException {
		return doFindAll(new StatementWrapper("select * from linea_ventas where nroVenta=?").push(v.getNroVenta()));
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
