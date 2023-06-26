package modelo;

import java.util.*;

public class WhatsApp implements EstrategiaNotificacion {


	public WhatsApp() {
	}

	private TwilioAdapter adaptador;


	public void enviarNotificacion(Notificacion notificacion, Socio socio) {
		System.out.println("El socio " + socio.getNombre() + "Ha recibido la siguiente notificacion por WPP: ");
		System.out.println(notificacion.toString());
	}


}
