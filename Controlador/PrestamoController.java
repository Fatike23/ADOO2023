
package controladores;

import java.util.*;

import modelo.Ejemplar;
import modelo.EstadoPrestamo;
import modelo.Prestamo;
import modelo.Socio;

public class PrestamoController {

	// ATRIBUTOS
	private static PrestamoController instance;
	private ArrayList<Prestamo> prestamos;
	private int idSiguiente;

	// GET INSTANCE (Patron Singleton)
	public static PrestamoController getInstance() {
		if (instance == null)
			instance = new PrestamoController();
		return instance;
	}

	// CONSTRUCTOR PRIVADO
	private PrestamoController(){
		prestamos = new ArrayList<Prestamo>();
		idSiguiente = 1000;
	}

	public void crearPrestamo(int idEjemplar, int dniSocio) {
		EjemplarController EC = EjemplarController.getInstance();
		Ejemplar ejemplar = EC.buscarEjemplarId(idEjemplar);

		SocioController SC = SocioController.getInstance();
		Socio socio = SC.buscarSocio(dniSocio);

		Prestamo nuevoPrestamo;
		nuevoPrestamo = new Prestamo(ejemplar, socio, ejemplar.getPlazoPrestamo());

		socio.agregarPrestamo(nuevoPrestamo);

		prestamos.add(nuevoPrestamo);
	}

	public void finalizarPrestamo(int idPrestamo) {
		//COMPLETAR
	}
	public Prestamo buscarPrestamo(int idPrestamo) {
		int i = 0;
		while (prestamos.get(i).getId() != idPrestamo)
			i++;
		return prestamos.get(i);
	}
}
