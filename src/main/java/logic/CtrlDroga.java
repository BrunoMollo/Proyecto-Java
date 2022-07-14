package logic;

import cosas_locas.BasicCtrl;
import data.DrogasDao;
import entities.Droga;

public class CtrlDroga extends BasicCtrl<Droga>{

	public CtrlDroga() {
		this.miDao=new DrogasDao();
	}

}
