package ourLib.dbUtils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;



public class DbConnector {

	private static DbConnector instancia;
	
	private String driver;//="com.mysql.cj.jdbc.Driver";
	private String host;//="us-cdbr-east-06.cleardb.net";
	private String port;//="3306";
	private String user;//="b18e27e75b75d1";
	private String password;//="b64eaac1";
	private String db;//="heroku_f46f78fc841fab8";
	private static final String CONFIG = "dbConfig.xml";
	private int conectados=0;
	private Connection conn=null;
	
	
	
	private DbConnector() {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder=factory.newDocumentBuilder();
			Document doc=builder.parse(new File("dbConfig.xml"));
			doc.getDocumentElement().normalize();
			System.out.println(doc.getElementById("host"));
			
			
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    Properties prop = new Properties();
        try(InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIG)){
            prop.loadFromXML(in);
            driver = prop.getProperty("driver");
            host=prop.getProperty("host"); 
            port=prop.getProperty("port");
            user=prop.getProperty("user");
            password=prop.getProperty("password");
            db=prop.getProperty("db_name");
        } catch (IOException e) {
            e.printStackTrace();
        }
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
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
