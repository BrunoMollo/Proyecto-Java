package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Dosis;
import entities.Laboratorio;
import entities.Medicamento;
import ourLib.AppException;
import ourLib.Csv;
import ourLib.PipeResulsetToCSV;
import ourLib.dbUtils.Dao;
import ourLib.dbUtils.DbConnector;
import ourLib.dbUtils.StatementWrapper;

public class MedicamentoDao extends Dao<Medicamento>{

	LaboratorioDao ldao = new LaboratorioDao();
	DosisDao ddao= new DosisDao();
	PrecioDao pDao=new PrecioDao();

	
	@Override
	protected Medicamento mapFromResulset(ResultSet rs) throws SQLException, AppException {
		Medicamento med=new Medicamento();
		Laboratorio lab = new Laboratorio();
		
		lab.setCodigo(rs.getInt("codigoLaboratorio"));
		
		med.setCodigoBarra(rs.getInt("codigoBarra"));
		med.setLaboratorio(ldao.getOne(lab));
		med.setNombre(rs.getString("nombre"));

		med.setPrecio(pDao.getLatestPrice(med));

		med.setSize(rs.getDouble("size"));
		med.setUnidad(rs.getString("unidad"));
		med.addAllDosis(ddao.getDosisOfMedicamento(med));
		return med;
		
	}



	@Override
	public void add(Medicamento p) throws AppException {
		StatementWrapper stw=new StatementWrapper("insert into medicamentos "
				+ "(codigoBarra, codigoLaboratorio, nombre, size, unidad) values (?,?,?,?,?)");
		
		stw.push(p.getCodigoBarra());
		stw.push(p.getLaboratorio().getCodigo());
		stw.push(p.getNombre());
		stw.push(p.getSize());
		stw.push(p.getUnidad());	
		
		doModification(stw);
	}


	@Override
	public Medicamento getOne(Medicamento p) throws AppException {
		Medicamento med=doGetOne(
				new StatementWrapper( "select * from medicamentos where nombre=? and baja is null")
					.push(p.getNombre())
				);
//		if(med!=null) {
//			med.setPrecio(pDao.getLatestPrice(p));
//		}	
		return med;
	}
	
	public Medicamento getByName(Medicamento m) throws AppException{
		Medicamento med=doGetOne(
				new StatementWrapper( "select * from medicamentos where nombre like ? and baja is null")
					.push(m.getNombre()+"%" )
				);
		if(med!=null) {
			med.setPrecio(pDao.getLatestPrice(med));			
		}
		
		return med;
	}
	
	
	public Medicamento checkBarcode(Medicamento m) throws AppException {
		Medicamento med=doGetOne(
				new StatementWrapper( "select * from medicamentos where codigoBarra = ?")
					.push(m.getCodigoBarra())
				);
		return med;
	}

	public Medicamento getByCode(Medicamento m) throws AppException {
		Medicamento med=checkBarcode(m);
				if(med!=null) {
					med.setPrecio(pDao.getLatestPrice(med));			
				}
		
		return med;
	}

	@Override
	public LinkedList<Medicamento> getAll() throws AppException {
		LinkedList<Medicamento> med=doFindAll(
				new StatementWrapper( "select * from medicamentos where baja is null"));
		if(med!=null) {
			for(Medicamento m:med) {
				m.setPrecio(pDao.getLatestPrice(m));
			}
		}
		
		return med;
	}
	
	public LinkedList<Medicamento> getEverything() throws AppException {
		LinkedList<Medicamento> med=doFindAll(
				new StatementWrapper( "select * from medicamentos"));
		if(med!=null) {
			for(Medicamento m:med) {
				m.setPrecio(pDao.getLatestPrice(m));
			}
		}
		
		return med;
	}

	
	@Override
	public void update(Medicamento p) throws AppException {
		StatementWrapper stw=new StatementWrapper(
				"update medicamentos set codigoLaboratorio=?, nombre=?, size=?, unidad=? where codigoBarra=?");	
		stw.push(p.getLaboratorio().getCodigo());
		stw.push(p.getNombre());
		stw.push(p.getSize());
		stw.push(p.getUnidad());
		stw.push(p.getCodigoBarra());	
		doModification(stw);
		
		stw=new StatementWrapper("delete from dosis where codigoMedicamento=?");
		stw.push(p.getCodigoBarra());
		doModification(stw);
		
		DosisDao ddao=new DosisDao();
		for (Dosis dose : p.getAllDosis().values()) {
			ddao.add(p,dose);			
		}
	}



	@Override
	public void delete(Medicamento p) throws AppException {
		StatementWrapper stw=new StatementWrapper(
				"update medicamentos set baja=now() where codigoBarra=?");
		stw.push(p.getCodigoBarra());
		doModification(stw);
	}
	
	
	//lo reescribi con la sintaxis de JDBC vanilla para que solo pedir el nombre del medicamenteo y asi optimizar la query
	public LinkedList<Medicamento> getAllByPartialName(Medicamento obj) throws AppException {
		Connection con =DbConnector.getInstancia().getConn();
		PreparedStatement st=null;
		ResultSet rs=null;
		try {
			st= con.prepareStatement("select nombre from medicamentos where nombre like ? and baja is null");
			st.setString(1, obj.getNombre()+"%");
			
			rs= st.executeQuery();
			LinkedList<Medicamento> arr= new LinkedList<>();
			while(rs.next()) {
				Medicamento m= new Medicamento(); 
				m.setNombre(rs.getString("nombre"));
				arr.push(m);
			}
			
			return arr;
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new AppException("Internal Database error", 500);
		}
		finally {
				try {
					if(st!=null)st.close();
					if(rs!=null)rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
		}
	}



}
