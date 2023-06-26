package modelo;

import java.util.*;

import static java.lang.System.out;

public class Email implements EstrategiaNotificacion {

	public Email() {
	}

	private AngusAdapter adaptador;


	public void enviarNotificacion(Notificacion notificacion, Socio socio) {
		out.println("El socio " + socio.getNombre() + "Ha recibido la siguiente notificacion por EMAIL: ");
		out.println(notificacion.toString());
	}

}
