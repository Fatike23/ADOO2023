package modelo;

import java.util.*;

import api.SistemaExternoAdapter;

public class Ejemplar implements SistemaExternoAdapter {

	// ATRIBUTOS
	private static int idSiguiente = 1000;
	private int id;
	private String titulo;
	private Date fechaPublicacion;
	private String autor;
	private Categoria categoria;
	private boolean disponible;
	private SistemaExternoAdapter adaptadorUbicacion;
	private int plazoPrestamo;

	// CONSTRUCTOR
	public Ejemplar(String titulo, Date fechaPublicacion, String autor, Categoria categoria) {
		this.titulo = titulo;
		this.fechaPublicacion = fechaPublicacion;
		this.autor = autor;
		this.categoria = categoria;
		this.id = idSiguiente;
		idSiguiente++;
		this.disponible = true;
		this.plazoPrestamo = 5;
	}

	public int getPlazoPrestamo() {
		return this.plazoPrestamo;
	}

	public void setPlazoPrestamo(int plazo) {
		this.plazoPrestamo = plazo;
	}


	public String getUbicacionFisica() {
		// TODO implement here
		return "";
	}

	public boolean isDisponible() {
		return this.disponible;
	}

	public String getUbicacionFisica(Ejemplar ejemplar) {
		// TODO implement here
		return "";
	}

	public int getId() {
		return id;
	}

	public Date getFechaPublicacion() {
		return this.fechaPublicacion;
	}

	public String getAutor() {
		return this.autor;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public Categoria getCategory() {
		return this.categoria;
	}

	public Date getFecha() {
		return this.fechaPublicacion;
	}
}
