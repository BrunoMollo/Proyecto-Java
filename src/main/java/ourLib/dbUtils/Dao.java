package ourLib.dbUtils;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;


public abstract class Dao<ENTITY>{
	
	private PreparedStatement st=null;
	private ResultSet rs=null;
	private Connection con=null;
	
	abstract protected ENTITY mapFromResulset(ResultSet rs) throws SQLException;
	abstract protected void mapAutogeneratedKey(ENTITY e, ResultSet rs) throws SQLException;
	abstract public ENTITY getOne(ENTITY p) throws SQLException;
	abstract public LinkedList<ENTITY> getAll() throws SQLException;
	abstract public void add(ENTITY p) throws SQLException;
	abstract public void update(ENTITY p) throws SQLException;
	abstract public void delete(ENTITY p) throws SQLException;
	
	
	protected void closeResourses(AutoCloseable ...resources) throws SQLException{
		try {
			for(AutoCloseable r: resources) {
				if(r!=null) {r.close();}
			}
			DbConnector.getInstancia().releaseConn();
		} 
		catch (Exception e) { throw (SQLException) e; }
	}
	
	
	protected LinkedList<ENTITY> executeFindAll(StatementWrapper stw) throws SQLException {
		LinkedList<ENTITY> outputFound= new LinkedList<>();
		try {
			con=DbConnector.getInstancia().getConn();
			st=stw.makeStatement(con);
			
			rs= st.executeQuery();
			
			while(rs!=null && rs.next()) {
				outputFound.add(mapFromResulset(rs));
			}	
		}
		catch (SQLException e) { throw e; } 
		finally { closeResourses(st,rs); }
		
		return outputFound;
	}
	

	protected ENTITY executeGetOne(StatementWrapper stw) throws SQLException {
		ENTITY outputFound=null;
		try {
			con=DbConnector.getInstancia().getConn();
			
			st=stw.makeStatement(con);
		
			rs=st.executeQuery();
		
			if(rs!=null && rs.next()) {
				outputFound=mapFromResulset(rs);
			}
		} 
		catch (SQLException e) { throw e; } 
		finally { closeResourses(st,rs); }
		return outputFound;//DEBERIA RETORNAR NULL????
	}
	
	
	protected void executeModification(StatementWrapper stw) throws SQLException {
		try {
			con=DbConnector.getInstancia().getConn();
			st=stw.makeStatement(con);
			st.executeUpdate();
		} 
		catch (SQLException e) { throw e; } 
		finally { closeResourses(st); }
	}
	
	
	protected void executeAddWithGeneratedKeys(StatementWrapper stw, ENTITY ent) throws SQLException {
		try {
			Connection con=DbConnector.getInstancia().getConn();
			PreparedStatement st= stw.makeStatement(con, PreparedStatement.RETURN_GENERATED_KEYS);
			st.executeUpdate();
			
			rs=st.getGeneratedKeys();
            if(rs!=null && rs.next()){
               mapAutogeneratedKey(ent,rs);
            }	   
		}  
		catch (SQLException e) { throw e; } 
		finally { closeResourses(st, rs); }
    }


	

}