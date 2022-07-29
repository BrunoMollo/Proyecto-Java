package data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import ourLib.dbUtils.Dao;
import ourLib.dbUtils.StatementWrapper;
import entities.Usuario;

public class LoginDao extends Dao<Usuario>{

	@Override
	protected Usuario mapFromResulset(ResultSet rs) throws SQLException {
		Usuario u= new Usuario();
		u.setUsuario(rs.getString("usuario"));
		u.setContrasena(rs.getString("contrasena"));
		u.setNombre(rs.getString("nombre"));
		u.setApellido(rs.getString("apellido"));
		u.setRol(rs.getInt("rol"));
		return u;
	}


	@Override
	public Usuario getOne(Usuario p) throws SQLException {
		StatementWrapper stw=new StatementWrapper("select * from usuarios where usuario=?");
		stw.push(p.getUsuario());
		return doGetOne(stw);
		
	}
	
	public Usuario getUP(Usuario p) throws SQLException {
		StatementWrapper stw=new StatementWrapper("select * from usuarios where usuario=? and contrasena=?");
		stw.push(p.getUsuario());
		stw.push(p.getContrasena());
		return doGetOne(stw);	
	}

	@Override
	public LinkedList<Usuario> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Usuario p) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Usuario p) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Usuario p) throws SQLException {
		// TODO Auto-generated method stub
		
	}
}
