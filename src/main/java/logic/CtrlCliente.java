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

		
	}

