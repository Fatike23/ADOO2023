package modelo;

import java.util.*;

public class Libro extends Ejemplar {

	public Libro(String titulo, Date fechaPublicacion, String autor, Categoria categoria) {
		super(titulo, fechaPublicacion, autor, categoria);
		this.plazoPrestamo = 10;
	}

	private Integer plazoPrestamo = 10;

	public int getPlazoPrestamo() {
		// TODO implement here
		return this.plazoPrestamo;
	}

	public void setPlazoPrestamo(int plazo) {
		// TODO implement here
		return ;
	}

}
