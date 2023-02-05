package logic;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;

import data.LineaVentaDao;
import data.MedicamentoDao;
import data.VentaDao;
import entities.LineaVenta;
import entities.Medicamento;
import entities.ObraSocial;
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
	
	public void iniciarVentaLibre(Usuario _user) throws AppException {
		user=_user;
		if(!user.hasAccess(Usuario.VENDEDOR)) {throw new AppException("Debe ser vendedor", 401);}
		ventaActual=new Venta();
		ventaActual.setVendidoPor(user);
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
	
	public LinkedList<Venta> listarVentas(Usuario _user, LocalDate fechaDesde, LocalDate fechaHasta, ObraSocial os) throws AppException {
		user=_user;
		if(!user.hasAccess(Usuario.VENDEDOR)) {throw new AppException("Debe ser vendedor", 401);}
		if(os.getId()==34) {
			return vDao.listarVentasParticular(fechaDesde, fechaHasta);
		}
		return vDao.listarVentasOS(fechaDesde,fechaHasta,os);
		
	}
	public LinkedList<LineaVenta> detalleVenta(Usuario _user, Venta venta) throws AppException {
		user=_user;
		if(!user.hasAccess(Usuario.VENDEDOR)) {throw new AppException("Debe ser vendedor", 401);}
		return new LineaVentaDao().getAllFromVenta(venta);		
	}
	
	public Venta getOne(Usuario _user, Venta venta) throws AppException {
		user=_user;
		if(!user.hasAccess(Usuario.VENDEDOR)) {
			throw new AppException("Debe ser vendedor",401);}
		return  vDao.getOne(venta);
		}
	
}
