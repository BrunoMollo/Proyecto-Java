package ourLib.LogicAbstraction;


import java.util.LinkedList;

import entities.Usuario;
import ourLib.AppException;
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
	
	private int defualtPermision=Usuario.ADMIN;
	
	public ENTITY getOne(ENTITY e, Usuario user) throws AppException {
		if(!user.hasAccess(defualtPermision)) {throw new AppException("Debe ser admin", 401);}
		return miDao.getOne(e);
	}

	public void add(ENTITY e, Usuario user) throws AppException{
		if(!user.hasAccess(defualtPermision)) {throw new AppException("Debe ser admin", 401);}
		miDao.add(e);
	}
	
	public LinkedList<ENTITY> getAll(Usuario user) throws AppException{
		if(!user.hasAccess(defualtPermision)) {throw new AppException("Debe ser admin", 401);}
		return miDao.getAll();
	}
	
	public void update(ENTITY e, Usuario user) throws AppException {
		if(!user.hasAccess(defualtPermision)) {throw new AppException("Debe ser admin", 401);}
		miDao.update(e);
	}

	public void delete(ENTITY e, Usuario user) throws AppException {
		if(!user.hasAccess(defualtPermision)) {throw new AppException("Debe ser admin",401);}
		miDao.delete(e);
	}
	
}
