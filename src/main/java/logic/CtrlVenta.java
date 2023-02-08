package logic;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import data.LineaVentaDao;
import data.MedicamentoDao;
import data.VentaDao;
import entities.Cliente;
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
	
	private Medicamento buscarMedicamento(String nombreMed) throws AppException {
		Medicamento med= new Medicamento();
		med.setNombre(nombreMed);
		return mDao.getByName(med);
	}
	
	public Venta getVenta() {
		return ventaActual;
	}
	
	public void iniciarVenta(Usuario _user) throws AppException {
		user=_user;
		if(!user.hasAccess(Usuario.VENDEDOR)) {throw new AppException("Debe ser vendedor", 401);}
		ventaActual=new Venta();
		ventaActual.setVendidoPor(user);
	}
	
	public void setCliente(Cliente cli) throws AppException {
		if(!user.hasAccess(Usuario.VENDEDOR)) {throw new AppException("Debe ser vendedor", 401);}
		ventaActual.setCliente(cli);
	}
	
	public Boolean addMedicamento(String nombreMed, Integer cant) throws AppException {
		if(!user.hasAccess(Usuario.VENDEDOR)) {throw new AppException("Debe ser vendedor", 401);}
		Medicamento med = buscarMedicamento(nombreMed);	
		if(med==null)  {return false; }		
		ventaActual.addMedicamento(med, cant);
		return true;
	}
	
	public Boolean addMedicamento(String nombreMed, Integer cant, Double descuento) throws AppException {
		if(!user.hasAccess(Usuario.VENDEDOR)) {throw new AppException("Debe ser vendedor", 401);}
		Medicamento med = buscarMedicamento(nombreMed);
		if(med==null)  {return false; }
		
		med.setPrecio(med.getPrecio()*(1-(descuento/100)));
		
		ventaActual.addMedicamento(med, cant);
		return true;
	}
	
	
	public void cerrarVenta() throws AppException {
		if(!user.hasAccess(Usuario.VENDEDOR)) {throw new AppException("Debe ser vendedor", 401);}
		ventaActual.setFechaVenta(LocalDateTime.now());
		ventaActual.calcularTotal();
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
