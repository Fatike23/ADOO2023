package modelo;

import java.util.*;

public class Socio {

	private String nombre;
	private Integer dni;
	private String mail;
	private String telefono;
	public List<Prestamo> prestamos;
	private List<Integer> idsDePrestamos;
	private IEstadoConducta estado;
	private Integer prestamosRealizados;
	private Integer diasPenalizacion;
	private Boolean suspendido;
	private Integer diasBonificacion;
	private MedioComunicacion medioCom;

	public Socio(int dni, String nombre, String mail, String telefono) {
		this.nombre = nombre;
		this.dni = dni;
		this.mail = mail;
		this.telefono = telefono;
		prestamos = new ArrayList<>();
		idsDePrestamos = new ArrayList<>();
	}


	public int calcularModificacionPlazo() {
		// TODO implement here
		return 0;
	}



	public void setEstado(IEstadoConducta estado) {
		// TODO implement here
		return ;
	}

	public List<Prestamo> getUltimosPrestamos(int cantidad) {
		List<Prestamo> ultimosPrestamos = new ArrayList<>();
		int total = prestamos.size();
		for (int i = total; i > (total - cantidad); i--){
			ultimosPrestamos.add(prestamos.get(i));
		}
		return ultimosPrestamos;
	}

	public void setPrestamosRealizados() {
		// TODO implement here
		return ;
	}

	public void penalizarSocio(int dias) {
		// TODO implement here
		return ;
	}

	public void bonificarSocio(int dias) {
		// TODO implement here
		return ;
	}

	private void suspender(Boolean bool) {
		// TODO implement here
		return ;
	}

	public int getDNI() {
		return this.dni;
	}

	public void agregarPrestamo(Prestamo nuevoPrestamo) {
		prestamos.add(nuevoPrestamo);
		idsDePrestamos.add(nuevoPrestamo.getId());
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getMail() {
		return this.mail;
	}
	public String getTelefono() {
		return this.telefono;
	}

}
