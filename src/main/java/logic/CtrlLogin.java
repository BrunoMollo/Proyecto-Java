package logic;

import java.sql.SQLException;

import ourLib.LogicAbstraction.BasicCtrl;
import data.LoginDao;
import entities.Usuario;

public class CtrlLogin extends BasicCtrl<Usuario>{
	static LoginDao miDao;
	public CtrlLogin() {
		 miDao=new LoginDao();
	}
	
	public Usuario validateLogin(Usuario ingresado) throws SQLException {
		return miDao.getUP(ingresado);	
	}
	
	//Faltan el resto de metodos heredados del BasicCtrl
	
}
