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
		med.addAllDosis(ddao.getDosisOfMedicamento(med));
		return med;
		
	}



	@Override
	public void add(Medicamento p) throws SQLException {
		StatementWrapper stw=new StatementWrapper("insert into medicamentos (codigoLaboratorio"
				+ ",nombre,size,precio) values (?,?,?,?)");
		stw.push(p.getLaboratorio().getCodigo());
		stw.push(p.getNombre());
		stw.push(p.getSize());
		stw.push(p.getPrecio());	
		
		doAddWithGeneratedKeys(stw, p);
	}



	@Override
	public Medicamento getOne(Medicamento p) throws SQLException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new UnsupportedOperationException("Manga de vagos, implementen "+funcName);
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
