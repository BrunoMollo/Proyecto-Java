package data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import entities.Cliente;
import entities.LineaVenta;
import entities.ObraSocial;
import entities.Venta;
import ourLib.AppException;
import ourLib.PipeResulsetToCSV;
import ourLib.dbUtils.Dao;
import ourLib.dbUtils.DbConnector;
import ourLib.dbUtils.StatementWrapper;

public class VentaDao extends Dao<Venta>{

	private ClienteDao cliDao= new ClienteDao();
	private LineaVentaDao lvDao= new LineaVentaDao();
	
	@Override
	protected Venta mapFromResulset(ResultSet rs) throws SQLException, AppException {
		Venta venta=new Venta();
		
		venta.setNroReceta(rs.getInt("nroVenta"));
		
		Cliente miCliente= new Cliente();
		miCliente.setDni(rs.getInt("dni"));
		miCliente=cliDao.getOne(miCliente);
		venta.setCliente(miCliente);
		
		return venta;
	}

	public File getVentasOSasCSV(LocalDate fechaDesde, LocalDate fechaHasta, String FileName)throws AppException, IOException{
		Connection con =DbConnector.getInstancia().getConn();
		
		String basePath="D://";
		
		
		File file = new File(basePath+FileName);
		file.createNewFile();
		BufferedWriter writer= new BufferedWriter(new FileWriter(basePath+FileName));
		
		try {
			PreparedStatement st= con.prepareStatement("select v.fecha\r\n"
					+ ", m.nombre as 'medicamento'\r\n"
					+ ", m.size as 'tamaño'\r\n"
					+ ", m.unidad \r\n"
					+ ", lv.cantidad\r\n"
					+ ", coalesce(concat(c.nombre,c.apellido),'NA') as 'cliente',\r\n"
					+ "coalesce(os.nombre, 'NA') as 'obra Social' \r\n"
					+ "from ventas v\r\n"
					+ "left join clientes c\r\n"
					+ "	on  v.dniCLiente=c.dni\r\n"
					+ "left join obras_sociales os\r\n"
					+ "	on os.id=c.id_obraSocial\r\n"
					+ "inner join linea_ventas lv\r\n"
					+ "	on lv.nroVenta=v.nroVenta\r\n"
					+ "inner join medicamentos m\r\n"
					+ "	on m.codigoBarra=lv.codBarra\r\n"
					+ "where v.fecha between ? and ?;");
			st.setObject(1, fechaDesde);
			st.setObject(2, fechaHasta);
			
			ResultSet rs= st.executeQuery();
			
			PipeResulsetToCSV pipe= new PipeResulsetToCSV();
			
			
			pipe.pipe(rs, writer);

			return file;
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("Internal Database error", 500);
		}
		finally {
			writer.close();
		}
	}
	
	
	
	public File getVentasOSasCSV(LocalDate fechaDesde, LocalDate fechaHasta, ObraSocial os) throws AppException, IOException {
		Connection con =DbConnector.getInstancia().getConn();
		
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
	    String periodo = "("+ fechaDesde.format(myFormatObj)+" a "+ fechaHasta.format(myFormatObj) + ")";  
	 
		String basePath="D://";
		String name="Ventas "+os.getNombre()+" "+periodo+".csv";
		
		File file = new File(basePath+name);
		file.createNewFile();
		BufferedWriter writer= new BufferedWriter(new FileWriter(basePath+name));
		
		try {
			PreparedStatement st= con.prepareStatement(
				"select v.fecha\r\n"
				+ ",concat(m.nombre, \"(\",m.size,\" \", m.unidad ,\")\") as 'medicamento'\r\n"
				+ ", lv.cantidad\r\n"
				+ ", v.nroReceta\r\n"
				+ ", concat(c.nombre,c.apellido) as 'cliente'\r\n"
				+ ", c.nroAfiliado \r\n"
				+ ", \"hay que ver como se marca el total y el desuento\" as \"TODO\" \r\n"
				+ "from ventas v\r\n"
				+ "left join clientes c\r\n"
				+ "	on  v.dniCLiente=c.dni\r\n"
				+ "left join obras_sociales os\r\n"
				+ "	on os.id=c.id_obraSocial\r\n"
				+ "inner join linea_ventas lv\r\n"
				+ "	on lv.nroVenta=v.nroVenta\r\n"
				+ "inner join medicamentos m\r\n"
				+ "	on m.codigoBarra=lv.codBarra\r\n"
				+ " where c.id_obraSocial= ?\r\n"
				+ " and v.fecha between ? and ?\r\n;"
			);
			st.setInt(1, os.getId());
			st.setObject(2, fechaDesde);
			st.setObject(3, fechaHasta);
			
			ResultSet rs= st.executeQuery();
			
			PipeResulsetToCSV pipe= new PipeResulsetToCSV();
			
			pipe.pipe(rs, writer);

			return file;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("Internal Database error", 500);
		}
		finally {
			writer.close();
		}
		
		
	}
	
	
	@Override
	public Venta getOne(Venta p) throws AppException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new AppException("Not Implemented "+funcName, 500);
	}

	@Override
	public LinkedList<Venta> getAll() throws AppException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new AppException("Not Implemented "+funcName, 500);
	}

	@Override
	public void add(Venta v) throws AppException {
		StatementWrapper tablaVenta= new StatementWrapper("INSERT INTO ventas"
				+ "( fecha, total, dniCliente, nroReceta, vendidoPor) VALUES"
				+ "(?,?,?,?,?)")
						.push(v.getFechaVenta())
						.push(v.getTotal())
						.push((v.getCliente()!=null)? v.getCliente().getDni() : null)
						.push((v.getNroReceta()!=null)? v.getNroReceta() : null)
						.push(v.getVendidoPor().getUsuario());
		
		doAddWithGeneratedKeys(tablaVenta, v);
		
		for(LineaVenta lv : v.getLineas()) {
			lvDao.add(lv, v);
		}			
	}

	@Override
	public void update(Venta p) throws AppException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new AppException("Not Implemented "+funcName, 500);
	}

	@Override
	public void delete(Venta p) throws AppException {
		// TODO Auto-generated method stub
		String funcName=new Throwable().getStackTrace()[0].getMethodName();
		throw new AppException("Not Implemented "+funcName, 500);
	}

}
