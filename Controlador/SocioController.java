package controladores;

import java.util.*;

import modelo.Ejemplar;
import modelo.Prestamo;
import modelo.Socio;

public class SocioController {

	// ATRIBUTOS
	private static SocioController instance;
	private ArrayList<Socio> socios;
	private int idSiguiente;

	// GET INSTANCE (Patron Singleton)
	public static SocioController getInstance() {
		if (instance == null)
			instance = new SocioController();
		return instance;
	}

	// CONSTRUCTOR PRIVATE
	private SocioController(){
		socios = new ArrayList<Socio>();
		idSiguiente = 1000;
	}

	public void altaSocio(int dni, String nombre, String mail, String telefono) {
		Socio nuevoSocio = new Socio(dni, nombre, mail, telefono);
		socios.add(nuevoSocio);
	}

	public List<Prestamo> getUltimosPrestamosDeSocio(int dni, int cantidad) {
		SocioController SC = SocioController.getInstance();
		Socio socio = SC.buscarSocio(dni);
		return socio.getUltimosPrestamos(cantidad);
	}

	public void cambiarMedioNotificacion(String medio) {
		// TODO implement here
		return ;
	}

	public void bajaSocio(int dni) {
		SocioController SC = SocioController.getInstance();
		Socio socio = SC.buscarSocio(dni);
		socios.remove(socio);
	}

	public Socio buscarSocio(int dniSocio) {
		int i = 0;
		while (socios.get(i).getDNI() != dniSocio)
			i++;
		return socios.get(i);
	}

	public ArrayList<Socio> getSocios() {
		return socios;
	}
}
