package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import org.apache.catalina.User;

import data.ClienteDao;
import entities.Cliente;
import entities.Usuario;
import ourLib.LogicAbstraction.BasicCtrl;

public class CtrlCliente extends BasicCtrl<Cliente, ClienteDao>{

	
		public CtrlCliente() {
			this.miDao= new ClienteDao();
		}

		public LinkedList<Cliente> getAllByLastName(Cliente cli, Usuario user) throws SQLException {
			return miDao.getAllByLastName(cli);
		}

		
	}

