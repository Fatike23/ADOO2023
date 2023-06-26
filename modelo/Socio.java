package modelo;

import java.util.*;

import static modelo.MedioComunicacion.WHATSAPP;
import static modelo.MedioComunicacion.SMS;
import static modelo.MedioComunicacion.EMAIL;

public class Socio {

	private String nombre;
	private int dni;
	private String mail;
	private String telefono;
	public List<Prestamo> prestamos;
	private List<Integer> idsDePrestamos;
	private IEstadoConducta estado;
	private int prestamosRealizados;
	private Boolean suspendido;
	private int diasBonificacion;
	private MedioComunicacion medioCom;
	private int rachaDevoluciones;
	private Notificador notificador;

	public Socio(int dni, String nombre, String mail, String telefono, MedioComunicacion medioCom) {
		this.nombre = nombre;
		this.dni = dni;
		this.mail = mail;
		this.telefono = telefono;
		this.medioCom = medioCom;
		this.notificador = new Notificador(medioCom, this);
		prestamos = new ArrayList<>();
		idsDePrestamos = new ArrayList<>();
		rachaDevoluciones = 0;
	}


	public int calcularModificacionPlazo() {
		// CALCULAR CUANTOS PRESTAMOS HECHOS POR ESTE SOCIO HAN SIDO
		// DEVUELTO 5 VECES DENTRO DEL PLAZO ESTIPULADO
		return 0;
	}



	public void setEstado(IEstadoConducta estado) {
		// TODO implement here
		return ;
	}

	public List<Prestamo> getUltimosPrestamos(int cantidad) {
		if (cantidad  >= prestamos.size()) {
			return prestamos;
		}
		List<Prestamo> ultimosPrestamos = new ArrayList<>();
		int total = prestamos.size();
		for (int i = total; i > (total - cantidad); i--){
			ultimosPrestamos.add(prestamos.get(i));
		}
		return ultimosPrestamos;
	}

	public void setPrestamosRealizados() {
		// TODO implement here
		return ;
	}

	public void penalizarSocio(int dias) {
		rachaDevoluciones = 0;
		this.diasBonificacion -= dias;
		if (this.diasBonificacion <= -10) {
			this.suspender(true);
		}
	}

	private void suspender(Boolean bool) {
		this.suspendido = bool;
	}

	public int getDNI() {
		return this.dni;
	}

	public void agregarPrestamo(Prestamo nuevoPrestamo) {
		prestamos.add(nuevoPrestamo);
		idsDePrestamos.add(nuevoPrestamo.getId());
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getMail() {
		return this.mail;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public int getDiasBonificacion(){
		return diasBonificacion;
	}

	public void sumarRachaDevoluciones(){
		rachaDevoluciones += 1;
		if (rachaDevoluciones == 5) {
			this.diasBonificacion++;
			rachaDevoluciones = 0;
			notificador.enviarNotificacionDeBonificacion();
		}
	}

	public Notificador getNotificador() {
		return this.notificador;
	}

	public String getLink(){
		if (medioCom == EMAIL){
			return this.mail;
		} else {
			return this.telefono;
		}
	}

}
