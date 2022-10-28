package data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;

import entities.Cliente;
import entities.ObraSocial;
import logic.CtrlObraSocial;
import ourLib.dbUtils.Dao;
import ourLib.dbUtils.StatementWrapper;

public class ClienteDao extends Dao<Cliente>{

		@Override
		protected Cliente mapFromResulset(ResultSet rs) throws SQLException {
			Cliente cli=  new Cliente();
			ObrasSocialesDao osDao= new ObrasSocialesDao();
			cli.setDni(rs.getInt("dni"));
			cli.setNombre(rs.getString("nombre"));
			cli.setApellido(rs.getString("apellido"));
			cli.setEmail(rs.getString("email"));
			cli.setTelefono(rs.getString("telefono"));
			cli.setLocalidad(rs.getString("localidad"));
			cli.setProvincia(rs.getString("provincia"));
			cli.setFechaNacimiento(rs.getObject("fechaNac", LocalDate.class));
			cli.setObraSocial(osDao.getOne(new ObraSocial(rs.getInt("id_obraSocial"))));
			cli.setDireccion(rs.getString("direccion"));
			return cli;
		}

		@Override
		public LinkedList<Cliente> getAll() throws SQLException {
			return doFindAll(new StatementWrapper("select * from clientes"));
		}

		@Override
		public void add(Cliente cli) throws SQLException {
			StatementWrapper stw=new StatementWrapper("insert into clientes (dni,nombre,apellido,email,telefono,localidad,provincia,fechaNac,id_obraSocial,direccion) values (?,?,?,?,?,?,?,?,?,?)")
				.push(cli.getDni())
				.push(cli.getNombre())
				.push(cli.getApellido())
				.push(cli.getEmail())
				.push(cli.getTelefono())
				.push(cli.getLocalidad())
				.push(cli.getProvincia())
				.push(cli.getFechaNacimiento())
				.push(cli.getObraSocial().getId())
				.push(cli.getDireccion());
			doModification(stw);
		}

		@Override
		public void update(Cliente cli) throws SQLException {
			StatementWrapper stw=new StatementWrapper("update clientes set nombre=?, apellido=? ,telefono=?, email=?, localidad=? , provincia=?,fechaNac=?, id_obraSocial=? ,direccion=? where dni=?")
					.push(cli.getNombre())
					.push(cli.getApellido())
					.push(cli.getTelefono())
					.push(cli.getEmail())
					.push(cli.getLocalidad())
					.push(cli.getProvincia())
					.push(cli.getFechaNacimiento())
					.push(cli.getObraSocial().getId())
					.push(cli.getDireccion())
					.push(cli.getDni());
	
			doModification(stw);
		}

		@Override
		public void delete(Cliente cli) throws SQLException {
			doModification(new StatementWrapper("delete from clientes where dni=?").push(cli.getDni()));
		}

		public LinkedList<Cliente> getAllByLastName(Cliente cli) throws SQLException {
			return doFindAll(new StatementWrapper("select * from clientes where apellido like ?").push(cli.getApellido()+"%"));
		}



		@Override
		public Cliente getOne(Cliente cli) throws SQLException {
			StatementWrapper stw=new StatementWrapper("select * from clientes  where dni=?")
					.push(cli.getDni());
				 return doGetOne(stw);
		
		}

	}

