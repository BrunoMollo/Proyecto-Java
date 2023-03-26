package logic;

import java.util.LinkedList;


import data.ClienteDao;
import entities.Cliente;
import entities.Usuario;
import ourLib.AppException;
import ourLib.LogicAbstraction.BasicCtrl;

public class CtrlCliente extends BasicCtrl<Cliente, ClienteDao>{

	
		public CtrlCliente() {
			this.miDao= new ClienteDao();
		}

		public LinkedList<Cliente> getAllByLastName(Cliente cli) throws AppException {
			return miDao.getAllByLastName(cli);
		}

		public Cliente getByNroAfiliado(Cliente c) throws AppException {
			return miDao.getByNroAfiliado(c);
		}

		@Override
		public Cliente getOne(Cliente e, Usuario user) throws AppException {
			if(!user.hasAccess(Usuario.VENDEDOR)) {throw new AppException("Debe ser admin", 401);}
			return miDao.getOne(e);
		}

		@Override
		public void add(Cliente e, Usuario user) throws AppException{
			if(!user.hasAccess(Usuario.VENDEDOR)) {throw new AppException("Debe ser admin", 401);}
			miDao.add(e);
		}
		
		@Override
		public LinkedList<Cliente> getAll(Usuario user) throws AppException{
			if(!user.hasAccess(Usuario.VENDEDOR)) {throw new AppException("Debe ser admin", 401);}
			return miDao.getAll();
		}
		
		@Override
		public void update(Cliente e, Usuario user) throws AppException {
			if(!user.hasAccess(Usuario.VENDEDOR)) {throw new AppException("Debe ser admin", 401);}
			miDao.update(e);
		}

		@Override
		public void delete(Cliente e, Usuario user) throws AppException {
			if(!user.hasAccess(Usuario.VENDEDOR)) {throw new AppException("Debe ser admin",401);}
			miDao.delete(e);
		}
	}

