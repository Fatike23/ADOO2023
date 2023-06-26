package modelo;

import java.util.*;

public class Prestamo {

	private int idPrestamo;
	private Ejemplar ejemplar;
	private Date fechaVencimiento;
	private Date fechaDevolucion;
	private Socio socio;
	private EstadoPrestamo estado;

	public Prestamo(Ejemplar ejemplar, Socio socio, int plazoPrestamo) {
		this.ejemplar = ejemplar;
		//La fecha de vencimiento se calcula con el plazo del ejemplar.
		//this.fechaVencimiento = fechaVencimiento;
		//his.fechaDevolucion = fechaDevolucion;
		this.socio = socio;
		inicializarEstado();
	}
	public  void setFechaVencimiento(Date FechaNueva) {
		this.fechaVencimiento = FechaNueva;
	}

	private void inicializarEstado() {
		this.estado = new EnCurso();
	}
	public Date getDevolucion() {
		return this.fechaDevolucion;
	}

	public int diasRestantesHastaVencimiento() {
		Date fechaActual = new Date();
		long diff = fechaVencimiento.getTime() - fechaActual.getTime();
		long diffDays = diff / (24 * 60 * 60 * 1000);
		return (int) diffDays;
	}

	public void finalizarPrestamo() {
		estado.finalizarPrestamo(this);
	}

	public int getId() {
		return idPrestamo;
	}

	public EstadoPrestamo getEstado() {
		return estado;
	}

	public void setEstado(EstadoPrestamo estado) {
		this.estado = estado;
	}

	public Socio getSocio() {
		return socio;
	}

	public Date getFechaVencimiento(){return this.fechaVencimiento;}

	public Ejemplar getEjemplar() {
		return this.ejemplar;
	}

	public Date getFechaDeCreacion() {
		return this.fechaDeCreacion;
	}

	public Notificador getNotificadorSocio(){
		return socio.getNotificador();
	}
}
