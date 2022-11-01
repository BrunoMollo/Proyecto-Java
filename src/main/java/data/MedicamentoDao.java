package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Dosis;
import entities.Laboratorio;
import entities.Medicamento;
import ourLib.dbUtils.Dao;
import ourLib.dbUtils.StatementWrapper;

public class MedicamentoDao extends Dao<Medicamento>{

	LaboratorioDao ldao = new LaboratorioDao();
	DosisDao ddao= new DosisDao();
	PrecioDao pdao=new PrecioDao();
	
	@Override
	protected Medicamento mapFromResulset(ResultSet rs) throws SQLException {
		Medicamento med=new Medicamento();
		Laboratorio lab = new Laboratorio();
		
		lab.setCodigo(rs.getInt("codigoLaboratorio"));
		
		med.setCodigoBarra(rs.getInt("codigoBarra"));
		med.setLaboratorio(ldao.getOne(lab));
		med.setNombre(rs.getString("nombre"));
		med.setPrecio(rs.getDouble("precio"));
		med.setSize(rs.getDouble("size"));
		med.setUnidad(rs.getString("unidad"));
		med.addAllDosis(ddao.getDosisOfMedicamento(med));
		return med;
		
	}



	@Override
	public void add(Medicamento p) throws SQLException {
		StatementWrapper stw=new StatementWrapper("insert into medicamentos "
				+ "(codigoBarra, codigoLaboratorio, nombre, size, unidad) values (?,?,?,?,?)");
		
		stw.push(p.getCodigoBarra());
		stw.push(p.getLaboratorio().getCodigo());
		stw.push(p.getNombre());
		stw.push(p.getSize());
		stw.push(p.getUnidad());	
		
		doModification(stw);
	}


	@Override
	public Medicamento getOne(Medicamento p) throws SQLException {
		Medicamento med=doGetOne(
				new StatementWrapper( "select * from medicamentos where codigoBarra=?")
					.push(p.getCodigoBarra())
				);
		med.setPrecio(pdao.getLatestPrice(p));
		return med;
	}


	@Override
	public LinkedList<Medicamento> getAll() throws SQLException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new UnsupportedOperationException("Manga de vagos, implementen "+funcName);
	}

	
	@Override
	public void update(Medicamento p) throws SQLException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new UnsupportedOperationException("Manga de vagos, implementen "+funcName);
	}



	@Override
	public void delete(Medicamento p) throws SQLException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new UnsupportedOperationException("Manga de vagos, implementen "+funcName);
	}
	
	


}
