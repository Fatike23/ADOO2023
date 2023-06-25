package modelo;

import java.util.*;

public class Revista extends Ejemplar {

	public Revista(String titulo, Date fechaPublicacion, String autor, Categoria categoria) {
		super(titulo, fechaPublicacion, autor, categoria);
	}

	private Integer plazoPrestamo = 5;

	public int getPlazoPrestamo() {
		// TODO implement here
		return this.plazoPrestamo;
	}

	public void setPlazoPrestamo(int plazo) {
		// TODO implement here
		return ;
	}
}
