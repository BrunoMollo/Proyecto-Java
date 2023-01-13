package data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import entities.Cliente;
import entities.ObraSocial;
import ourLib.AppException;
import ourLib.dbUtils.Dao;
import ourLib.dbUtils.StatementWrapper;

public class ClienteDao extends Dao<Cliente>{

		@Override
		protected Cliente mapFromResulset(ResultSet rs) throws SQLException, AppException {
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
			cli.setNroAfiliado(rs.getString("nroAfiliado"));
			return cli;
		}

		@Override
		public LinkedList<Cliente> getAll() throws AppException {
			return doFindAll(new StatementWrapper("select * from clientes"));
		}

		@Override
		public void add(Cliente cli) throws AppException {
			StatementWrapper stw=new StatementWrapper("insert into clientes (dni,nombre,apellido,email,telefono,localidad,provincia,fechaNac,id_obraSocial,direccion, nroAfiliado) values (?,?,?,?,?,?,?,?,?,?,?)")
				.push(cli.getDni())
				.push(cli.getNombre())
				.push(cli.getApellido())
				.push(cli.getEmail())
				.push(cli.getTelefono())
				.push(cli.getLocalidad())
				.push(cli.getProvincia())
				.push(cli.getFechaNacimiento())
				.push(cli.getObraSocial().getId())
				.push(cli.getDireccion())
				.push(cli.getNroAfiliado());
			doModification(stw);
		}

		@Override
		public void update(Cliente cli) throws AppException {
			StatementWrapper stw=new StatementWrapper("update clientes set nombre=?, apellido=? ,telefono=?, email=?, localidad=? ,"
					+ "provincia=?,fechaNac=?, id_obraSocial=? ,direccion=?,nroAfiliado=? where dni=?")
					.push(cli.getNombre())
					.push(cli.getApellido())
					.push(cli.getTelefono())
					.push(cli.getEmail())
					.push(cli.getLocalidad())
					.push(cli.getProvincia())
					.push(cli.getFechaNacimiento())
					.push(cli.getObraSocial().getId())
					.push(cli.getDireccion())
					.push(cli.getNroAfiliado())
					.push(cli.getDni());
			doModification(stw);
		}

		@Override
		public void delete(Cliente cli) throws AppException {
			doModification(new StatementWrapper("delete from clientes where dni=?").push(cli.getDni()));
		}

		public LinkedList<Cliente> getAllByLastName(Cliente cli) throws AppException {
			return doFindAll(new StatementWrapper("select * from clientes where apellido like ?").push(cli.getApellido()+"%"));
		}

		@Override
		public Cliente getOne(Cliente cli) throws AppException {
			StatementWrapper stw=new StatementWrapper("select * from clientes  where dni=?")
					.push(cli.getDni());
				 return doGetOne(stw);
		}
		public Cliente getByNroAfiliado(Cliente c) throws AppException {
			StatementWrapper stw=new StatementWrapper("select * from clientes where nroAfiliado=?")
					.push(c.getNroAfiliado());
				 return doGetOne(stw);
		}

	}

