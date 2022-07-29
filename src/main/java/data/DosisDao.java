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
		Dosis dose=new Dosis(ddao.getOne(drug),rs.getDouble("cantidad"));
		return dose;
				
	}


	@Override
	public Dosis getOne(Dosis p) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedList<Dosis> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Dosis p) throws SQLException {
				
	}
	
	public void add(Medicamento med, Dosis dose) throws SQLException {
		StatementWrapper stw= new StatementWrapper("insert into dosis (codigoDroga, "
				+ "codigoMedicamento, cantidad) values (?,?,?)");
		stw.push(dose.getCodigoDroga());
		stw.push(med.getCodigoBarra());
		stw.push(dose.getCant());
		doModification(stw);
	}

	@Override
	public void update(Dosis p) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Dosis p) throws SQLException {
		// TODO Auto-generated method stub
		
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

}
