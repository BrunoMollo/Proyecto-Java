package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Medicamento;
import entities.Precio;
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
	public Precio getOne(Precio p) throws SQLException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new UnsupportedOperationException("Not Implemented "+funcName);
	}
	
	public Double getLatestPrice(Medicamento med) throws SQLException {
		StatementWrapper stw=new StatementWrapper("select * from precio_medicamento "
				+ "where codigoMedicamento=? order by fecha desc limit 1 ");
		stw.push(med.getCodigoBarra());
		return doGetOne(stw).getMonto();
	}

	@Override
	public LinkedList<Precio> getAll() throws SQLException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new UnsupportedOperationException("Not Implemented "+funcName);
	}
	
	public void add(Medicamento med, Precio p) throws SQLException {
		StatementWrapper stw=new StatementWrapper("insert into precio_medicamento "
				+ "(codigoMedicamento, fecha, monto) values (?,?,?)");
		stw.push(med.getCodigoBarra());
		stw.push(p.getFecha());
		stw.push(p.getMonto());
		doModification(stw);
	}
	
	@Override
	public void add(Precio p) throws SQLException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new UnsupportedOperationException("Not Implemented "+funcName);
	}

	@Override
	public void update(Precio p) throws SQLException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new UnsupportedOperationException("Not Implemented "+funcName);
	}

	@Override
	public void delete(Precio p) throws SQLException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new UnsupportedOperationException("Not Implemented "+funcName);
	}

}
