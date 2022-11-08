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

		public LinkedList<Cliente> getAllByLastName(Cliente cli, Usuario user) throws AppException {
			return miDao.getAllByLastName(cli);
		}

		
	}

