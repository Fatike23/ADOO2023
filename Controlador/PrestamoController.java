
package controladores;

import java.util.*;

import modelo.Ejemplar;
import modelo.EstadoPrestamo;
import modelo.Prestamo;
import modelo.Socio;

import static java.util.Calendar.DAY_OF_YEAR;

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
		EjemplarController ejemplarController = EjemplarController.getInstance();
		Ejemplar ejemplar = ejemplarController.buscarEjemplarId(idEjemplar);

		SocioController socioController = SocioController.getInstance();
		Socio socio = socioController.buscarSocio(dniSocio);

		Prestamo nuevoPrestamo;
		nuevoPrestamo = new Prestamo(ejemplar, socio, ejemplar.getPlazoPrestamo());

		socio.agregarPrestamo(nuevoPrestamo);

		prestamos.add(nuevoPrestamo);
	}

	public void actualizarDiasPrestamo(int idPrestamo, int dias){
		buscarPrestamo(idPrestamo).modificarFechaVencimiento(dias);
	}

	public void finalizarPrestamo(int idPrestamo) {
		buscarPrestamo(idPrestamo).finalizarPrestamo();
	}

	public Prestamo buscarPrestamo(int idPrestamo) {
		int i = 0;
		while (prestamos.get(i).getId() != idPrestamo)
			i++;
		return prestamos.get(i);
	}

	public List<Prestamo> getPrestamos(){
		return this.prestamos;
	}

	public Prestamo getUltimoPrestamo() {
		return prestamos.get(prestamos.size() - 1);
	}
}
