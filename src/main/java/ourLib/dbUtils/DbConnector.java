package ourLib.dbUtils;

import java.sql.*;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;



public class DbConnector {

	private static DbConnector instancia;
	
	private String driver="com.mysql.jdbc.Driver";
	private String host="us-cdbr-east-06.cleardb.net";
	private String port="3306";
	private String user=System.getenv("JAVA_BD_USER");
	private String password=System.getenv("JAVA_BD_PSW");
	private String db="heroku_f46f78fc841fab8";

	private int conectados=0;
	private Connection conn=null;
	
	
	
	private DbConnector() {
		
		try {
            Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			
		} 
	}
	
	
	public static DbConnector getInstancia() throws SQLException {
		if (instancia == null) {
			instancia = new DbConnector();
		}
		
		if(instancia==null) {
			throw new SQLException("No se pudo establecer la conexion a la base de datos, verificar configeracionde DbConnector.java");
		}
		return instancia;
	}
	
	public  void tryRollback() {
		try { if(conn!=null) { conn.rollback(); } } 
		catch (SQLException e1) { e1.printStackTrace(); }
	}
	
	public Connection getConn() {
		try {
			if(conn==null || conn.isClosed()) {
				conn=DriverManager.getConnection("jdbc:mysql://"+host+":"+port+"/"+db, user, password);
				conectados=0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conectados++;
		return conn;
	}
	
	public void releaseConn() {
		conectados--;
		try {
			if (conectados<=0) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
