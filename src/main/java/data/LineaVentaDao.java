package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.LineaVenta;
import entities.Medicamento;
import entities.Venta;
import ourLib.dbUtils.Dao;
import ourLib.dbUtils.StatementWrapper;

public class LineaVentaDao extends Dao<LineaVenta>{

	MedicamentoDao medDao= new MedicamentoDao();
	
	@Override
	protected LineaVenta mapFromResulset(ResultSet rs) throws SQLException {
		LineaVenta lv=new LineaVenta();
		
		Medicamento med= new Medicamento();
		med.setCodigoBarra(rs.getInt("codigoBarra"));
		med=medDao.getOne(med);
		
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

	
	public void add(LineaVenta lv, Venta v) throws SQLException {
		doModification(new StatementWrapper("INSERT INTO linea_ventas(nroVenta, codBarra, cantidad, precioUnidad) VALUES (?,?,?,?)")
						.push(v.getNroVenta())
						.push(lv.getMedicamento().getCodigoBarra())
						.push(lv.getCantidad())
						.push(lv.getPrecioUnidad())
				);
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
	@Override
	public void add(LineaVenta p) throws SQLException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new UnsupportedOperationException("Manga de vagos, implementen "+funcName);
	}

}
