package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;

import entities.Cliente;
import entities.LineaVenta;
import entities.ObraSocial;
import entities.Venta;
import ourLib.AppException;
import ourLib.dbUtils.Dao;
import ourLib.dbUtils.StatementWrapper;

public class VentaDao extends Dao<Venta>{

	private ClienteDao cliDao= new ClienteDao();
	private LineaVentaDao lvDao= new LineaVentaDao();
	
	@Override
	protected Venta mapFromResulset(ResultSet rs) throws SQLException, AppException {
		Venta venta=new Venta();
		
		venta.setNroVenta(rs.getInt("nroVenta"));
		
		Cliente miCliente= new Cliente();
		Integer dniCliente=rs.getInt("dniCLiente");
		if(dniCliente!=0) 
		{
			miCliente.setDni(dniCliente);
			miCliente=cliDao.getOne(miCliente);
			venta.setCliente(miCliente);
		}		
		venta.setFechaVenta(rs.getObject("fecha", LocalDateTime.class));
		venta.setTotal(rs.getDouble("total"));
		return venta;
	}

	@Override
	public Venta getOne(Venta p) throws AppException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new AppException("Not Implemented "+funcName, 500);
	}

	@Override
	public LinkedList<Venta> getAll() throws AppException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new AppException("Not Implemented "+funcName, 500);
	}

	@Override
	public void add(Venta v) throws AppException {
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
	public void update(Venta p) throws AppException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new AppException("Not Implemented "+funcName, 500);
	}

	@Override
	public void delete(Venta p) throws AppException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new AppException("Not Implemented "+funcName, 500);
	}

	public LinkedList<Venta> listarVentasOS(LocalDate fechaDesde, LocalDate fechaHasta, ObraSocial os) throws AppException {
		StatementWrapper st= new StatementWrapper("SELECT nroVenta, dniCliente, fecha, total FROM "
				+ "ventas v inner join clientes c "
				+ "on (v.dniCLiente = c.dni) "
				+ " where (v.fecha>=?) and (v.fecha<=?)"
				+ "and c.id_obraSocial=?")
				.push(fechaDesde)
				.push(fechaHasta)
				.push(os.getId());
		LinkedList<Venta> ventas = doFindAll(st);
		return ventas;
	}
	public LinkedList<Venta> listarVentasParticular(LocalDate fechaDesde, LocalDate fechaHasta) throws AppException {
		StatementWrapper st= new StatementWrapper("SELECT nroVenta, dniCliente, fecha, total FROM "
				+ "ventas v"
				+ " where (v.fecha>=?) and (v.fecha<=?)"
				+ "and (v.dniCLiente is NULL)")
				.push(fechaDesde)
				.push(fechaHasta)
				;
		LinkedList<Venta> ventas = doFindAll(st);
		return ventas;
	}
}
