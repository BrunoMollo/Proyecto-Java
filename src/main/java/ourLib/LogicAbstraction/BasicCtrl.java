package ourLib.LogicAbstraction;

import java.rmi.AccessException;
import java.sql.SQLException;
import java.util.LinkedList;

import entities.Usuario;
import ourLib.dbUtils.Dao;


/**
* <p>Esta clase es una abstraccion de los controladores que hacen operaciones basicas de ABMC.
* Ofrece implemetaciones por defecto de estas operaciones sin validar reglas de negocio, en caso de requerir dichas 
* validaciones es necesario reescribir los metodos.</p>
* <p>
* La existencia de esta clase nos permite ahorarnos muchos "pasamanos" en la capa de logica, ademas de otorgar una interfaz para 
* la clase GenericServlet que implementa operaciones por defecto con los controladores.
* </p>
*/
public abstract class BasicCtrl<ENTITY, ENTIYT_DAO extends Dao<ENTITY>> {
	protected ENTIYT_DAO miDao;
	
	
	public ENTITY getOne(ENTITY e, Usuario user) throws SQLException, AccessException {
		if(!user.hasAccess(Usuario.ADMIN)) {throw new AccessException("Debe ser admin");}
		return miDao.getOne(e);
	}

	public void add(ENTITY e, Usuario user) throws SQLException, AccessException{
		if(!user.hasAccess(Usuario.ADMIN)) {throw new AccessException("Debe ser admin");}
		miDao.add(e);
	}
	
	public LinkedList<ENTITY> getAll(Usuario user) throws SQLException, AccessException{
		if(!user.hasAccess(Usuario.ADMIN)) {throw new AccessException("Debe ser admin");}
		return miDao.getAll();
	}
	
	public void update(ENTITY e, Usuario user) throws SQLException, AccessException {
		if(!user.hasAccess(Usuario.ADMIN)) {throw new AccessException("Debe ser admin");}
		miDao.update(e);
	}

	public void delete(ENTITY e, Usuario user) throws SQLException, AccessException {
		if(!user.hasAccess(Usuario.ADMIN)) {throw new AccessException("Debe ser admin");}
		miDao.delete(e);
	}
	
	
	
	//estan para tener compatiblidad hacia atras con servlert de lab, hay que sacarlos despues
	
	@Deprecated
	public ENTITY getOne(ENTITY e) throws SQLException {
		return miDao.getOne(e);
	}
	@Deprecated
	public void add(ENTITY e) throws SQLException{
		miDao.add(e);
	}
	@Deprecated
	public LinkedList<ENTITY> getAll() throws SQLException, AccessException{
		return miDao.getAll();
	}
	@Deprecated
	public void update(ENTITY e) throws SQLException, AccessException {
		miDao.update(e);
	}
	@Deprecated
	public void delete(ENTITY e) throws SQLException {
		miDao.delete(e);
	}

	
}
