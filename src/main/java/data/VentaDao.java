package data;

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
import ourLib.Csv;
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
		
		venta.setNroVenta(rs.getInt("nroVenta"));
		
		Cliente miCliente= new Cliente();
		Integer dniCliente=rs.getInt("dniCLiente");
		if(dniCliente!=0) 
		{
			miCliente.setDni(dniCliente);
			miCliente=cliDao.getOne(miCliente);
			venta.setCliente(miCliente);
		}		
		venta.setFechaVenta(rs.getObject("fecha", LocalDateTime.class));
		venta.setTotal(rs.getDouble("total"));
		return venta;
	}


	public Csv getMasVendidosAsCSV(LocalDate fechaDesde, LocalDate fechaHasta, String FileName)throws AppException, IOException{
		Connection con =DbConnector.getInstancia().getConn();
		
		try {
			PreparedStatement st= con.prepareStatement(
					"select concat(m.nombre, ' (',m.size,' ', m.unidad ,')') as 'Medicamento'\n"
					+ ", sum(lv.cantidad) as 'Cantidad vendida'\n"
					+ "from ventas v\n"
					+ "inner join linea_ventas lv\n"
					+ "	on lv.nroVenta=v.nroVenta\n"
					+ "inner join medicamentos m\n"
					+ "	on m.codigoBarra=lv.codBarra\n"
					+ "where v.fecha between ? and ?\n"
					+ "group by m.codigoBarra, m.nombre, m.size, m.unidad\n"
					+ "order by 'Cantidad vendida' DESC \n"
					+ ";\n"
					+ "");
			st.setObject(1, fechaDesde);
			st.setObject(2, fechaHasta);
			
			ResultSet rs= st.executeQuery();
			
			Csv csv= new Csv();
			csv.setName(FileName);
			csv.setRawData(new PipeResulsetToCSV().pipe(rs));
			return csv;
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("Internal Database error", 500);
		}

	}
	
	
	
	public Csv getVentasOSasCSV(LocalDate fechaDesde, LocalDate fechaHasta, ObraSocial os) throws AppException, IOException {
		Connection con =DbConnector.getInstancia().getConn();
		
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");  
	    String periodo = "("+ fechaDesde.format(myFormatObj)+" a "+ fechaHasta.format(myFormatObj) + ")";  
		String name="Ventas "+os.getNombre()+" "+periodo+".csv";
		
		
		try {
			PreparedStatement st= con.prepareStatement(
				"select v.fecha\n"
				+ ",concat(lv.cantidad  ,' x ',m.nombre, ' (',m.size,' ', m.unidad ,')') as 'medicamento'\n"
				+ ", concat(c.nombre,c.apellido) as 'cliente'\n"
				+ ", concat('$', lv.precioUnidad *lv.cantidad) as 'importe'\n"
				+ ", concat('%', os.descuentoGeneral) 'descuento'\n"
				+ ", concat('$',lv.precioUnidad *lv.cantidad  * os.descuentoGeneral/100) as 'a reintegrar'\n"
				+ ", v.nroReceta\n"
				+ ", c.nroAfiliado \n"
				+ "from ventas v\n"
				+ "left join clientes c\n"
				+ "	on  v.dniCLiente=c.dni\n"
				+ "left join obras_sociales os\n"
				+ "	on os.id=c.id_obraSocial\n"
				+ "inner join linea_ventas lv\n"
				+ "	on lv.nroVenta=v.nroVenta\n"
				+ "inner join medicamentos m\n"
				+ "	on m.codigoBarra=lv.codBarra\n"
				+ " where c.id_obraSocial=?\n"
				+ " and v.fecha between ? and ?\n"
				+ ";"
			);
			st.setInt(1, os.getId());
			st.setObject(2, fechaDesde);
			st.setObject(3, fechaHasta);
			
			ResultSet rs= st.executeQuery();
			
			Csv csv= new Csv();
			csv.setName(name);
			csv.setRawData(new PipeResulsetToCSV().pipe(rs));
			return csv;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("Internal Database error", 500);
		}
		
		
	}
	
	
	@Override
	public Venta getOne(Venta v) throws AppException {
		StatementWrapper stw=new StatementWrapper("select * from ventas  where nroVenta=?")
				.push(v.getNroVenta());
			 return doGetOne(stw);
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

	public LinkedList<Venta> listarVentasOS(LocalDate fechaDesde, LocalDate fechaHasta, ObraSocial os) throws AppException {
		StatementWrapper st= new StatementWrapper("SELECT nroVenta, dniCliente, fecha, total FROM "
				+ "ventas v inner join clientes c "
				+ "on (v.dniCLiente = c.dni) "
				+ " where (v.fecha>=?) and (v.fecha<=?)"
				+ "and c.id_obraSocial=?")
				.push(fechaDesde)
				.push(fechaHasta)
				.push(os.getId());
		LinkedList<Venta> ventas = doFindAll(st);
		return ventas;
	}
	public LinkedList<Venta> listarVentasParticular(LocalDate fechaDesde, LocalDate fechaHasta) throws AppException {
		StatementWrapper st= new StatementWrapper("SELECT nroVenta, dniCliente, fecha, total FROM "
				+ "ventas v"
				+ " where (v.fecha>=?) and (v.fecha<=?)"
				+ "and (v.dniCLiente is NULL)")
				.push(fechaDesde)
				.push(fechaHasta)
				;
		LinkedList<Venta> ventas = doFindAll(st);
		return ventas;
	}

}
