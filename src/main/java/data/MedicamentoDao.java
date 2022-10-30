package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;


import entities.Laboratorio;
import entities.Medicamento;
import ourLib.dbUtils.Dao;
import ourLib.dbUtils.StatementWrapper;

public class MedicamentoDao extends Dao<Medicamento>{

	LaboratorioDao ldao = new LaboratorioDao();
	DosisDao ddao= new DosisDao();
	PrecioDao pDao=new PrecioDao();
	
	@Override
	protected Medicamento mapFromResulset(ResultSet rs) throws SQLException {
		Medicamento med=new Medicamento();
		Laboratorio lab = new Laboratorio();
		
		lab.setCodigo(rs.getInt("codigoLaboratorio"));
		
		med.setCodigoBarra(rs.getInt("codigoBarra"));
		med.setLaboratorio(ldao.getOne(lab));
		med.setNombre(rs.getString("nombre"));
		//En la DB no la tabla medicamento no tiene precio. Consultar
		//med.setPrecio(rs.getDouble("precio"));
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
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new UnsupportedOperationException("Manga de vagos, implementen "+funcName);
		//aca deberiamos hacer un join con la tabla precios para obtener el ultimo o algo asi
		
	}
	
	public Medicamento getByName(Medicamento m) throws SQLException{
		Medicamento med=doGetOne(
				new StatementWrapper( "select * from medicamentos where nombre like ?")
					.push(m.getNombre()+"%" )
				);
		med.setPrecio(pDao.getLatestPrice(med));
		
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
	
	public LinkedList<Medicamento> getAllByPartialName(Medicamento obj) throws SQLException {
		return doFindAll(new StatementWrapper("select * from medicamentos  where nombre like ?")
				.push(obj.getNombre()+"%"));
				
		
	}



}
