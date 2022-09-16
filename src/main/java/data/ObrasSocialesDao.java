package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.ObraSocial;
import ourLib.dbUtils.Dao;
import ourLib.dbUtils.StatementWrapper;

public class ObrasSocialesDao extends Dao<ObraSocial>{

	@Override
	protected ObraSocial mapFromResulset(ResultSet rs) throws SQLException {
		ObraSocial os=  new ObraSocial();
		os.setId(rs.getInt("id"));
		os.setNombre(rs.getString("nombre"));
		os.setEmail(rs.getString("email"));
		os.setTelefono(rs.getString("telefono"));
		os.setDescuento(rs.getDouble("descuentoGeneral"));
		return os;
	}

 

	@Override
	public LinkedList<ObraSocial> getAll() throws SQLException {
		return doFindAll(new StatementWrapper("select * from obras_sociales"));
	}

	@Override
	public void add(ObraSocial os) throws SQLException {
		StatementWrapper stw=new StatementWrapper("insert into obras_sociales (nombre,telefono,email,descuentoGeneral) values (?,?,?,?)");
		stw.push(os.getNombre())
			.push(os.getTelefono())
			.push(os.getEmail())
			.push(os.getDescuento());
		doAddWithGeneratedKeys(stw, os);
	}

	@Override
	public void update(ObraSocial os) throws SQLException {
		StatementWrapper stw=new StatementWrapper("update obras_sociales set nombre=?, telefono=?, email=?, descuentoGeneral=?  where id=?")
				.push(os.getNombre())
				.push(os.getTelefono())
				.push(os.getEmail())
				.push(os.getDescuento())
				.push(os.getId());
		doModification(stw);
	}

	@Override
	public void delete(ObraSocial p) throws SQLException {
		doModification(new StatementWrapper("delete from obras_sociales where id=?").push(p.getId()));
	}

	public LinkedList<ObraSocial> getAllByName(ObraSocial os) throws SQLException {
		return doFindAll(new StatementWrapper("select * from obras_sociales where nombre like ?").push(os.getNombre()+"%"));
	}



	@Override
	public ObraSocial getOne(ObraSocial p) throws SQLException {
		StatementWrapper stw=new StatementWrapper("select * from obras_sociales  where id=?")
				.push(p.getId());
			 return doGetOne(stw);
	}


}
