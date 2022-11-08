package logic;

import ourLib.AppException;
import data.LoginDao;
import entities.Usuario;

public class CtrlLogin{
	
	LoginDao miDao=new LoginDao();
	
	
	public Usuario validateLogin(Usuario ingresado) throws AppException {
		return miDao.getUP(ingresado);	
	}
	
	//Faltan el resto de metodos heredados del BasicCtrl
	
}
