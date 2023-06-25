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
		//INICIALIZAR ESTADO DEL PRESTAMO
		//AL EMPEZAR SIEMPRE EMPIEZA EN CURSO
	}
	public Date getDevolucion() {
		//return this.fechaDevolucion
		return null;
	}
	public Date calcularDevolucion(Socio socio, Ejemplar ejemplar) {
		// return this.fechaDevolucion
		return null;
	}
	public void finalizarPrestamo() {
		// estado = finalizado
	}
	public int getId() {
		return idPrestamo;
	}

}
