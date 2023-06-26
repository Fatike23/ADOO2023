package modelo;

import java.util.*;

public class Notificacion {

	public Notificacion(String titulo, String mensaje, Motivo motivo) {
		this.titulo = titulo;
		this.mensaje = mensaje;
		this.fecha = new Date();
		this.motivo = motivo;
	}

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Motivo getMotivo() {
		return motivo;
	}
	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}

	@Override
	public String toString() {
		return "Notificacion{" +
				"titulo='" + titulo + '\'' +
				", mensaje='" + mensaje + '\'' +
				", fecha=" + fecha +
				", motivo=" + motivo +
				'}';
	}
}
