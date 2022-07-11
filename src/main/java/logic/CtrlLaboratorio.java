package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.LaboratorioDao;
import entities.Laboratorio;

public class CtrlLaboratorio {
	private LaboratorioDao ldao;
	
	public CtrlLaboratorio() {
		 this.ldao=new LaboratorioDao();
	}
	
	public Laboratorio getOne(Laboratorio l) throws SQLException {
		return ldao.getOne(l);
	}
	
	public LinkedList<Laboratorio> getAll() throws SQLException {
		return ldao.getAll();
	}
	
	public void add(Laboratorio l) throws SQLException {
		ldao.add(l);
	}
	
	public void update(Laboratorio l) throws SQLException{
		ldao.update(l);
	}
	
	public void delete(Laboratorio l) throws SQLException {
		ldao.delete(l);
	}
}
