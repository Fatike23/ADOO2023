package modelo;

import controladores.PrestamoController;

import java.util.*;

import static modelo.Motivo.PENALIZACION;
import static modelo.Motivo.PROXVTO;

public class CronJobPrestamos {

	private static CronJobPrestamos instance;

	public static CronJobPrestamos getInstance(){
		if (instance == null){
			instance = new CronJobPrestamos();
		}
		return instance;
	}

	private CronJobPrestamos() {
	}


	private List<Prestamo> obtenerPrestamos() {
		PrestamoController prestamoController = PrestamoController.getInstance();
		List<Prestamo> prestamos = prestamoController.getPrestamos();
		return prestamos;
	}

	public void actualizarEstado() {
		List<Prestamo> prestamos = obtenerPrestamos();
		for (Prestamo prestamo : prestamos) {
			int diasRestantesHastaVencimiento = prestamo.diasRestantesHastaVencimiento();
			if (diasRestantesHastaVencimiento < 0){
				prestamo.setEstado(new Demorado());
				this.enviarNotificacion(prestamo, diasRestantesHastaVencimiento, PENALIZACION);
			} if (diasRestantesHastaVencimiento >= 0 && diasRestantesHastaVencimiento <= 2) {
				this.enviarNotificacion(prestamo, diasRestantesHastaVencimiento, PROXVTO);
			}
		};
	}

	public void enviarNotificacion(Prestamo prestamo, int diasRestantesHastaVencimiento, Motivo motivo) {
		Notificador notificadorSocio = prestamo.getNotificadorSocio();
		String mensaje = definirMensajePorMotivo(motivo, diasRestantesHastaVencimiento);
		int idPrestamo = prestamo.getId();
		String titulo = String.format("Informacion sobre prestamo %d", idPrestamo);
		Notificacion notificacion = new Notificacion(titulo, mensaje, motivo);
		notificadorSocio.enviarNotificacion(notificacion);
	}

	public String definirMensajePorMotivo(Motivo motivo, int dias){
		String mensaje;
		if (motivo == PENALIZACION) {
			dias = -dias;
			mensaje = String.format("Tienes una penalizacion por haberte excedido %d dias.", dias);
			return mensaje;
		} else if (motivo == PROXVTO) {
			mensaje = String.format("Tu prestamo esta proximo a vencer en %d dias.", dias);
			return mensaje;
		}
		return "";
	}


}
