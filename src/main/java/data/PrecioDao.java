package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Medicamento;
import entities.Precio;
import ourLib.AppException;
import ourLib.dbUtils.Dao;
import ourLib.dbUtils.StatementWrapper;

public class PrecioDao extends Dao<Precio>{

	@Override
	protected Precio mapFromResulset(ResultSet rs) throws SQLException {
		Precio precio=new Precio();
		precio.setFecha(rs.getDate("fecha").toLocalDate());
		precio.setMonto(rs.getDouble("monto"));
		return precio;
	}

	@Override
	public Precio getOne(Precio p) throws AppException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new AppException("Not Implemented "+funcName, 500);
	}
	
	public Double getLatestPrice(Medicamento med) throws AppException {
		StatementWrapper stw=new StatementWrapper("select * from precio_medicamento "
				+ "where codigoMedicamento=? order by fecha desc limit 1 ");
		stw.push(med.getCodigoBarra());
		return doGetOne(stw).getMonto();
	}

	@Override
	public LinkedList<Precio> getAll() throws AppException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new AppException("Not Implemented "+funcName, 500);
	}
	
	public void add(Medicamento med, Precio p) throws AppException {
		StatementWrapper stw=new StatementWrapper("insert into precio_medicamento "
				+ "(codigoMedicamento, fecha, monto) values (?,?,?)");
		stw.push(med.getCodigoBarra());
		stw.push(p.getFecha());
		stw.push(p.getMonto());
		doModification(stw);
	}
	
	@Override
	public void add(Precio p) throws AppException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new AppException("Not Implemented "+funcName, 500);
	}

	@Override
	public void update(Precio p) throws AppException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new AppException("Not Implemented "+funcName, 500);
	}

	@Override
	public void delete(Precio p) throws AppException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new AppException("Not Implemented "+funcName, 500);
	}

	public LinkedList<Precio> getPrices(Medicamento med) throws AppException {
		return doFindAll(new StatementWrapper("select * from precio_medicamento where codigoMedicamento=? "
				+ "order by fecha desc").push(med.getCodigoBarra()));
	}

}
