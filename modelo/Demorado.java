package modelo;

public class Demorado implements EstadoPrestamo {

	public Demorado() {
	}

	public void finalizarPrestamo(Prestamo prestamo) {
		int diasAtraso = -prestamo.diasRestantesHastaVencimiento();
		prestamo.getSocio().penalizarSocio(diasAtraso);
		prestamo.setEstado(new Finalizado(false));
	}

	@Override
	public String toString() {
		return "Demorado";
	}
}
