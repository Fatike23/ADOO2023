
package modelo;

import java.util.*;

import static modelo.MedioComunicacion.WHATSAPP;
import static modelo.MedioComunicacion.SMS;
import static modelo.MedioComunicacion.EMAIL;
import static modelo.Motivo.PREMIO;

public class Notificador {

	public CronJobPrestamos ejecuta;
	private EstrategiaNotificacion estrategia;
	private Socio socio;

	public Notificador(MedioComunicacion medio, Socio socio) {
		definirEstrategia(medio);
		this.socio = socio;
	}

	public Notificador() {
	}

	private void definirEstrategia(MedioComunicacion medioCom) {
		if (medioCom == WHATSAPP) {
			this.estrategia = new WhatsApp();
		} else if (medioCom == SMS) {
			this.estrategia = new SMS();
		} else {
			this.estrategia = new Email();
		}
	}
	public void enviarNotificacion(Notificacion notificacion) {
		this.estrategia.enviarNotificacion(notificacion, socio);
	}

	public void setMedioNotificacion(MedioComunicacion estrategia) {
		// TODO implement here
		return ;
	}

	public void enviarNotificacionDeBonificacion() {
		String titulo = "Has recibido un beneficio en tu biblioteca";
		String mensaje = generarMensajeNotificacion();
		Motivo motivo = PREMIO;
		Notificacion notificacion = new Notificacion(titulo, mensaje, motivo);
		estrategia.enviarNotificacion(notificacion, socio);
	}

	private String generarMensajeNotificacion() {
		return "Estimado/a " + socio.getNombre() + " ha finalizado de manera anticipada y exitosa 5 devoluciones. Tiene un dia mas de bonificacion para devolver sus prestamos ";
	}

	public String getLink() {
		return socio.getLink();
	}
}
