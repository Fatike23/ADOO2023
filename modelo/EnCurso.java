package modelo;

public class EnCurso implements EstadoPrestamo {

	public EnCurso() {
	}

	public void finalizarPrestamo(Prestamo prestamo) {
		prestamo.setEstado(new Finalizado(true));
		prestamo.getSocio().sumarRachaDevoluciones();
	}

	@Override
	public String toString() {
		return "EnCurso";
	}
}
