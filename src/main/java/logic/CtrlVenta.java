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
	
	
	
	public Venta iniciarVentaLibre(Usuario user) throws AccessException {
		if(!user.hasAccess(Usuario.VENDEDOR)) {throw new AccessException("Debe ser vendedor");}
		Venta venta=new Venta();
		venta.setVendidoPor(user);
		return venta;
	}
	
	
	public Venta addMedicamento(Venta venta, String nombreMed, Integer cant, Usuario user) throws SQLException, AccessException {
		if(!user.hasAccess(Usuario.VENDEDOR)) {throw new AccessException("Debe ser vendedor");}
		Medicamento med= new Medicamento();
		med.setNombre(nombreMed);
		med=mDao.getByName(med);
		
		venta.addMedicamento(med, cant);
		
		return venta;
	}	
	
	
	public Venta cerrarVenta(Venta venta, Usuario user) throws SQLException, AccessException {
		if(!user.hasAccess(Usuario.VENDEDOR)) {throw new AccessException("Debe ser vendedor");}
		venta.setFechaVenta(LocalDateTime.now());
		venta.cacularTotal();
		
		vDao.add(venta);
		
		return venta;
	}
	
}
