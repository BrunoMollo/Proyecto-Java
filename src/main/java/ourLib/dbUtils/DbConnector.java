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
	
	private String user="b126a3f12fcb58";
	private String password="b0c47748";
	private String driver="com.mysql.cj.jdbc.Driver";
	private String host="us-cdbr-east-06.cleardb.net";
	private String port="3306";
	private String db="heroku_5c56859fce36a7d";

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
