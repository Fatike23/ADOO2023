package modelo;

import java.util.*;

public class Demorado implements EstadoPrestamo {

	public Demorado() {
	}

	public void sumarDiaPenalizacion(Socio socio, Date fechaVencimiento) {
		// TODO implement here
		return ;
	}

	public void finalizarPrestamo(Prestamo prestamo) {
		int diasAtraso = -prestamo.diasRestantesHastaVencimiento();
		prestamo.getSocio().penalizarSocio(diasAtraso);
		prestamo.setEstado(new Finalizado(false));
	}

}
