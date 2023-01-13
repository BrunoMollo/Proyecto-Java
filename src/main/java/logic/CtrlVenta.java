package logic;

import java.sql.SQLException;
import java.time.LocalDateTime;

import data.MedicamentoDao;
import data.VentaDao;
import entities.Cliente;
import entities.Medicamento;
import entities.Usuario;
import entities.Venta;
import ourLib.AppException;

public class CtrlVenta {

	private VentaDao vDao= new VentaDao();
	private MedicamentoDao mDao=new MedicamentoDao();
	
	private Venta ventaActual;
	private Usuario user;
	
	public Venta getVenta() {
		return ventaActual;
	}
	
	public void iniciarVenta(Usuario _user) throws AppException {
		user=_user;
		if(!user.hasAccess(Usuario.VENDEDOR)) {throw new AppException("Debe ser vendedor", 401);}
		ventaActual=new Venta();
		ventaActual.setVendidoPor(user);
	}
	
	public void setCliente(Cliente cli) {
		ventaActual.setCliente(cli);
	}
	
	
	public Boolean addMedicamento(String nombreMed, Integer cant) throws AppException {
		if(!user.hasAccess(Usuario.VENDEDOR)) {throw new AppException("Debe ser vendedor", 401);}
		Medicamento med= new Medicamento();
		med.setNombre(nombreMed);
		med=mDao.getByName(med);
		
		if(med==null)  {return false; }
		
		ventaActual.addMedicamento(med, cant);
		return true;
	}	
	
	
	public void cerrarVenta() throws AppException {
		if(!user.hasAccess(Usuario.VENDEDOR)) {throw new AppException("Debe ser vendedor", 401);}
		ventaActual.setFechaVenta(LocalDateTime.now());
		ventaActual.cacularTotal();
		
		vDao.add(ventaActual);
	}
	
}
