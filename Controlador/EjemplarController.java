package controladores;

import modelo.*;

import java.util.*;

import javax.xml.crypto.Data;

public class EjemplarController {

	// ATRIBUTOS
	private static EjemplarController instance;
	private ArrayList<Ejemplar> ejemplares;
	private int idSiguiente;

	// GET INSTANCE (Patron Singleton)
	public static EjemplarController getInstance() {
		if (instance == null)
			instance = new EjemplarController();
		return instance;
	}

	// CONSTRUCTOR PRIVADO
	private EjemplarController(){
		ejemplares = new ArrayList<Ejemplar>();
		idSiguiente = 1000;
	}

	public void altaEjemplar(String titulo, Date fechaPublicacion, String autor, Categoria categoria) {

		Ejemplar nuevoEjemplar;
		if (categoria.equals(Categoria.LIBRO)){
			nuevoEjemplar = new Libro(titulo, fechaPublicacion, autor, categoria);
		} else if (categoria.equals(Categoria.REVISTA)) {
			nuevoEjemplar = new Revista(titulo, fechaPublicacion, autor, categoria);
		} else if (categoria.equals(Categoria.REVISTAESPECIALIZADA)){
			nuevoEjemplar = new RevistaEspecializada(titulo, fechaPublicacion, autor, categoria);
		} else {
			nuevoEjemplar = new Diario(titulo, fechaPublicacion, autor, categoria);
		}
		ejemplares.add(nuevoEjemplar);

	}

	public void modificarPlazoPrestamo(int idEjemplar, int plazo) {
		Ejemplar ejemplar = buscarEjemplarId(idEjemplar);
		ejemplar.setPlazoPrestamo(plazo);
	}

	// Busqueda por fecha de publicacion
	public List<Ejemplar> buscarEjemplaresFecha(Data fechaPublicacion) {
		List<Ejemplar> coincidencias = new ArrayList<Ejemplar>();
		for (Ejemplar ej : ejemplares){
			if (ej.getFechaPublicacion().equals(fechaPublicacion)){
				coincidencias.add(ej);
			}
		}
		return coincidencias;
	}

	//Busqueda por autor
	public List<Ejemplar> buscarEjemplaresAut(String autor) {
		List<Ejemplar> coincidencias = new ArrayList<Ejemplar>();
		for (Ejemplar ej : ejemplares){
			if (ej.getAutor().equals(autor)){
				coincidencias.add(ej);
			}
		}
		return coincidencias;
	}

	//Busqueda por categoria
	public List<Ejemplar> buscarEjemplaresCat(Categoria categoria) {
		List<Ejemplar> coincidencias = new ArrayList<Ejemplar>();
		for (Ejemplar ej : ejemplares){
			if (ej.getCategory().equals(categoria)){
				coincidencias.add(ej);
			}
		}
		return coincidencias;
	}

	//Busqueda titulo
	public List<Ejemplar> buscarEjemplaresTitulo(String titulo) {
		List<Ejemplar> coincidencias = new ArrayList<Ejemplar>();
		for (Ejemplar ej : ejemplares){
			if (ej.getTitulo().equals(titulo)){
				coincidencias.add(ej);
			}
		}
		return coincidencias;
	}


	public Ejemplar buscarEjemplarId(int idEjemplar) {
		int i = 0;
		while (ejemplares.get(i).getId() != idEjemplar)
			i++;
		return ejemplares.get(i);
	}

	public void bajaEjemplar(int idEjemplar) {
		Ejemplar ejemplar = buscarEjemplarId(idEjemplar);
		ejemplares.remove(ejemplar);
	}

	public ArrayList<Ejemplar> getEjemplares() {
		return ejemplares;
	}

}
