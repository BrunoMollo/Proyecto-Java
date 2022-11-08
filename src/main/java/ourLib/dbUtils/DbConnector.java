package ourLib.dbUtils;

import java.sql.*;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import ourLib.AppException;

import javax.xml.parsers.*;
import java.io.*;



public class DbConnector {

	private static DbConnector instancia;
	
	private String driver="com.mysql.cj.jdbc.Driver";
	private String host="containers-us-west-34.railway.app";
	private String port="7921";
	private String user=System.getenv("JAVA_BD_USER");
	private String password=System.getenv("JAVA_BD_PSW");
	private String db="railway";

	private int conectados=0;
	private Connection conn=null;
	
	
	
	private DbConnector() {
		
		try {
            Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			
		} 
	}
	
	
	public static DbConnector getInstancia() throws AppException {
		if (instancia == null) {
			instancia = new DbConnector();
		}
		
		if(instancia==null) {
			throw new AppException("No se pudo establecer la conexion a la base de datos, verificar configeracionde DbConnector.java", 500);
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
