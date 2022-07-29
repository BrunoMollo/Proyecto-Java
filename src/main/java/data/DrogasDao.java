package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Droga;
import ourLib.dbUtils.Dao;
import ourLib.dbUtils.DbConnector;
import ourLib.dbUtils.StatementWrapper;

public class DrogasDao extends Dao<Droga> {
	
	@Override
	protected Droga mapFromResulset(ResultSet rs) throws SQLException {
		Droga drug = new Droga();
		drug.setCod(rs.getInt("codigo"));
		drug.setNombre(rs.getString("nombre"));
		return drug;
	}

	
	@Override
	public Droga getOne(Droga p) throws SQLException {
		return executeGetOne(
				new StatementWrapper("select * from drogas where codigo=?").push(p.getCod()));
	}

	@Override
	public LinkedList<Droga> getAll() throws SQLException{
		return executeFindAll(new StatementWrapper("select * from drogas"));
	}
	
	@Override
	public void add(Droga drug) throws SQLException {
		StatementWrapper stw= new StatementWrapper("insert into drogas(nombre) values(?)");
		stw.push(drug.getNombre());
		executeAddWithGeneratedKeys(stw, drug);
	}

	@Override
	public void update(Droga drug) throws SQLException {
		PreparedStatement pstm =null;	
		try {
			pstm= DbConnector.getInstancia().getConn()
					.prepareStatement("update drogas set nombre=? where codigo=?");
			pstm.setString(1,drug.getNombre());
			pstm.setInt(2,drug.getCod());
			pstm.execute();
			
		} 
		catch (SQLException e) { throw e;} 
		finally { closeResourses(pstm); }
	}
	
	@Override
	public void delete(Droga drug) throws SQLException{
		PreparedStatement pstm =null;
		try {
			pstm= DbConnector.getInstancia().getConn()
					.prepareStatement("delete from drogas where codigo=?");
			pstm.setInt(1,drug.getCod());
			pstm.execute();	
		} 
		catch (SQLException e) { throw e; } 
		finally { closeResourses(pstm); }
	}

	public LinkedList<Droga> getAllByPartialName(Droga obj) throws SQLException {
		return executeFindAll(new StatementWrapper("select * from drogas where nombre like ?").push(obj.getNombre()+'%'));
		
	}


	public Droga getOneByName(Droga drug) throws SQLException {
		return executeGetOne(new StatementWrapper("select * from drogas where nombre=?").push(drug.getNombre()));
	}

}
