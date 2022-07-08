package dbUtils;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;


public abstract class Dao<ENTITY> {
	
	private PreparedStatement st=null;
	private ResultSet rs=null;
	private Connection con=null;
	
	abstract protected ENTITY mapFromResulset(ResultSet rs) throws SQLException;
	protected abstract void mapAutogeneratedKey(ENTITY e, ResultSet rs) throws SQLException;
	
	
	protected void closeResourses(AutoCloseable ...resources){
		try {
			for(AutoCloseable r: resources) {
				if(r!=null) {r.close();}
			}
			DbConnector.getInstancia().releaseConn();
		} 
		catch (SQLException e) { e.printStackTrace();} 
		catch (Exception e) { e.printStackTrace(); }
	}
	
	
	//deberia retornar algo???
//	public void executeDelete(queryLambda query) {
//		
//		try {
//			con=DbConnector.getInstancia().getConn();
//			con.setAutoCommit(false);
//			query.apply();
//			con.commit();
//		}
//		catch (SQLException e) { 
//			e.printStackTrace(); 
//			DbConnector.getInstancia().tryRollback();
//			} 
//		finally { closeResourses(st,rs); }
//	}
	
	
	
	public LinkedList<ENTITY> executeFindAll(StatementWrapper stw) {
		LinkedList<ENTITY> outputFound= new LinkedList<>();
		try {
			con=DbConnector.getInstancia().getConn();
			st=stw.makeStatement(con);
			
			rs= st.executeQuery();
			
			while(rs!=null && rs.next()) {
				outputFound.add(mapFromResulset(rs));
			}	
		}
		catch (SQLException e) { e.printStackTrace(); } 
		finally { closeResourses(st,rs); }
		
		return outputFound;
	}
	

	protected ENTITY executeGetOne(StatementWrapper stw) {
		ENTITY outputFound=null;
		try {
			con=DbConnector.getInstancia().getConn();
			
			st=stw.makeStatement(con);
		
			rs=st.executeQuery();
		
			if(rs!=null && rs.next()) {
				outputFound=mapFromResulset(rs);
			}
		} 
		catch (SQLException e) { e.printStackTrace(); } 
		finally { closeResourses(st,rs); }
		return outputFound;//DEBERIA RETORNAR NULL????
	}
	
	
	
	protected void executeSimpleAdd(StatementWrapper stw, ENTITY ent) {
		try {
			Connection con=DbConnector.getInstancia().getConn();
			PreparedStatement st= stw.makeStatement(con);
			st.executeUpdate();
			
			rs=st.getGeneratedKeys();
            if(rs!=null && rs.next()){
               mapAutogeneratedKey(ent,rs);
            }	
               
		}  
		catch (SQLException e) { e.printStackTrace(); } 
		finally { closeResourses(); }
    }


	
	
	
}