package modelo;

import java.util.*;

public class SMS implements EstrategiaNotificacion {


	public SMS() {
	}

	private TwilioAdapter adaptador;


	public void enviarNotificacion(Notificacion notificacion, Socio socio) {
		System.out.println("El socio " + socio.getNombre() + "Ha recibido la siguiente notificacion por SMS: ");
		System.out.println(notificacion.toString());
	}

}
