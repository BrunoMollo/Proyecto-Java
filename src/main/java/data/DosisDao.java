package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

import entities.Dosis;
import entities.Droga;
import entities.Medicamento;
import ourLib.dbUtils.Dao;
import ourLib.dbUtils.StatementWrapper;

public class DosisDao extends Dao<Dosis>{
	
	DrogasDao ddao= new DrogasDao();

	@Override
	protected Dosis mapFromResulset(ResultSet rs) throws SQLException {
		
		Droga drug=new Droga();
		drug.setCod(rs.getInt("codigoDroga"));
		Dosis dose=new Dosis(ddao.getOne(drug),rs.getDouble("cantidad"),rs.getString("unidad"));
		return dose;
				
	}




	
	public void add(Medicamento med, Dosis dose) throws SQLException {
		StatementWrapper stw= new StatementWrapper("insert into dosis (codigoDroga, "
				+ "codigoMedicamento, cantidad, unidad) values (?,?,?,?)");
		stw.push(dose.getCodigoDroga());
		stw.push(med.getCodigoBarra());
		stw.push(dose.getCant());
		stw.push(dose.getUnidad());
		doModification(stw);
	}


	
	public HashMap<Integer, Dosis> getDosisOfMedicamento(Medicamento med) throws SQLException{
		LinkedList<Dosis> list = doFindAll(
				new StatementWrapper("select * from dosis where codigoMedicamento=?")
					.push(med.getCodigoBarra()));
		HashMap<Integer, Dosis> hash=new HashMap<Integer, Dosis>();
		
		for (Dosis dose : list) {
			hash.put(dose.getCodigoDroga(), dose);
		}
		
		return hash;
		
		
	}





	@Override
	public Dosis getOne(Dosis p) throws SQLException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new UnsupportedOperationException("Manga de vagos, implementen "+funcName);
	}





	@Override
	public LinkedList<Dosis> getAll() throws SQLException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new UnsupportedOperationException("Manga de vagos, implementen "+funcName);
	}





	@Override
	public void add(Dosis p) throws SQLException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new UnsupportedOperationException("Manga de vagos, implementen "+funcName);
	}





	@Override
	public void update(Dosis p) throws SQLException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new UnsupportedOperationException("Manga de vagos, implementen "+funcName);
	}





	@Override
	public void delete(Dosis p) throws SQLException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new UnsupportedOperationException("Manga de vagos, implementen "+funcName);
	}



	

}
