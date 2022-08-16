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
	
	private String driver;
	private String host;
	private String port;
	private String user;
	private String password;
	private String db;
	private static final String CONFIG = "dbConfig.xml";
	private int conectados=0;
	private Connection conn=null;
	
	
	
	private DbConnector() {
		
		try {
			Properties prop = new Properties();
			prop.loadFromXML(new FileInputStream(CONFIG));
			driver = prop.getProperty("driver");
            host=prop.getProperty("host"); 
            port=prop.getProperty("port");
            user=prop.getProperty("user");
            password=prop.getProperty("password");
            db=prop.getProperty("db_name");
            
            Class.forName(driver);
            
		} catch (IOException | ClassNotFoundException e1) {
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
