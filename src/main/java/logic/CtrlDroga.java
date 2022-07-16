package logic;

import data.DrogasDao;
import entities.Droga;
import ourLib.LogicAbstraction.BasicCtrl;

public class CtrlDroga extends BasicCtrl<Droga>{

	public CtrlDroga() {
		this.miDao=new DrogasDao();
	}

}
