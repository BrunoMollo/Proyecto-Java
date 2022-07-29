package data;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Laboratorio;
import ourLib.dbUtils.Dao;
import ourLib.dbUtils.StatementWrapper;

public class LaboratorioDao extends Dao<Laboratorio> {

	@Override
	protected Laboratorio mapFromResulset(ResultSet rs) throws SQLException {
		Laboratorio l = new Laboratorio();
		l.setCodigo(rs.getInt("codigo"));
		l.setNombre(rs.getString("nombre"));
		l.setEmail(rs.getString("email"));
		l.setTelefono(rs.getString("telefono"));
		return l;
	}


	@Override
	public Laboratorio getOne(Laboratorio l) throws SQLException {
		StatementWrapper stw=new StatementWrapper("select * from laboratorios where codigo=?");
		stw.push(l.getCodigo());
		return doGetOne(stw);				
	}

	@Override
	public LinkedList<Laboratorio> getAll() throws SQLException {
		return doFindAll(new StatementWrapper("select * from laboratorios"));
	}

	@Override
	public void add(Laboratorio p) throws SQLException {
		StatementWrapper stw= new StatementWrapper("insert into laboratorios (nombre, telefono, email) values (?,?,?)");
		stw.push(p.getNombre());
		stw.push(p.getTelefono());
		stw.push(p.getEmail());
		doAddWithGeneratedKeys(stw, p);
	}

	@Override
	public void update(Laboratorio p) throws SQLException {
		StatementWrapper stw = new StatementWrapper("update laboratorios set nombre=?, email=?, telefono=? where codigo=?");
			stw.push(p.getNombre());
			stw.push(p.getEmail());
			stw.push(p.getTelefono());
			stw.push(p.getCodigo());
		doModification(stw);
			
	}

	@Override
	public void delete(Laboratorio p) throws SQLException {
		StatementWrapper stw = new StatementWrapper("delete from laboratorios where codigo=?");
			stw.push(p.getCodigo());
		doModification(stw);
		
	}

}
