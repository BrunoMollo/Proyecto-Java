package logic;

import java.rmi.AccessException;
import java.sql.SQLException;
import java.time.LocalDateTime;

import data.MedicamentoDao;
import data.VentaDao;
import entities.Medicamento;
import entities.Usuario;
import entities.Venta;

public class CtrlVenta {

	private VentaDao vDao= new VentaDao();
	private MedicamentoDao mDao=new MedicamentoDao();
	
	private Venta ventaActual;
	private Usuario user;
	
	public Venta getVenta() {
		return ventaActual;
	}
	
	public void iniciarVentaLibre(Usuario _user) throws AccessException {
		user=_user;
		if(!user.hasAccess(Usuario.VENDEDOR)) {throw new AccessException("Debe ser vendedor");}
		ventaActual=new Venta();
		ventaActual.setVendidoPor(user);
	}
	
	
	public void addMedicamento(String nombreMed, Integer cant) throws SQLException, AccessException {
		if(!user.hasAccess(Usuario.VENDEDOR)) {throw new AccessException("Debe ser vendedor");}
		Medicamento med= new Medicamento();
		med.setNombre(nombreMed);
		med=mDao.getByName(med);
		
		if(med==null)  {throw new SQLException("no exite el medicamento");}
		
		ventaActual.addMedicamento(med, cant);
		
	}	
	
	
	public void cerrarVenta() throws SQLException, AccessException {
		if(!user.hasAccess(Usuario.VENDEDOR)) {throw new AccessException("Debe ser vendedor");}
		ventaActual.setFechaVenta(LocalDateTime.now());
		ventaActual.cacularTotal();
		
		vDao.add(ventaActual);
	}
	
}
