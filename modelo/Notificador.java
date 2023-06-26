
package modelo;

import java.util.*;

import static modelo.MedioComunicacion.WHATSAPP;
import static modelo.MedioComunicacion.SMS;
import static modelo.MedioComunicacion.EMAIL;
import static modelo.Motivo.PREMIO;

public class Notificador {

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
		// TODO implement here
		return ;
	}

	public void setMedioNotificacion(MedioComunicacion estrategia) {
		// TODO implement here
		return ;
	}

}
