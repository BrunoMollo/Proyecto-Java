package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Cliente;
import entities.LineaVenta;
import entities.Venta;
import ourLib.dbUtils.Dao;
import ourLib.dbUtils.StatementWrapper;

public class VentaDao extends Dao<Venta>{

	private ClienteDao cliDao= new ClienteDao();
	private LineaVentaDao lvDao= new LineaVentaDao();
	
	@Override
	protected Venta mapFromResulset(ResultSet rs) throws SQLException {
		Venta venta=new Venta();
		
		venta.setNroReceta(rs.getInt("nroVenta"));
		
		Cliente miCliente= new Cliente();
		miCliente.setDni(rs.getInt("dni"));
		miCliente=cliDao.getOne(miCliente);
		venta.setCliente(miCliente);
		
		return venta;
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
	public void add(Venta v) throws SQLException {
		StatementWrapper tablaVenta= new StatementWrapper("INSERT INTO ventas"
				+ "( fecha, total, dniCliente, nroReceta, vendidoPor) VALUES"
				+ "(?,?,?,?,?)")
						.push(v.getFechaVenta())
						.push(v.getTotal())
						.push((v.getCliente()!=null)? v.getCliente().getDni() : null)
						.push(v.getNroReceta())
						.push(v.getVendidoPor().getUsuario());
		
		doAddWithGeneratedKeys(tablaVenta, v);
		
		for( LineaVenta lv : v.getLineas()) {
			lvDao.add(lv, v);
		}			
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
