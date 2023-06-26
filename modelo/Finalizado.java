package modelo;



public class Finalizado implements EstadoPrestamo {

	private boolean enTermino;

	public Finalizado(boolean enTermino) {
		this.enTermino = enTermino;
	}


	public void finalizarPrestamo(Prestamo prestamo) {
	}

	public boolean isEnTermino(){
		return enTermino;
	}

	@Override
	public String toString() {
		return "Finalizado";
	}
}
