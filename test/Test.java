package test;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import controladores.EjemplarController;
import controladores.PrestamoController;
import controladores.SocioController;
import modelo.*;

import static java.lang.System.out;
import static java.lang.System.setOut;
import static modelo.Categoria.LIBRO;
import static modelo.Categoria.REVISTAESPECIALIZADA;
import static modelo.MedioComunicacion.EMAIL;
import static modelo.MedioComunicacion.WHATSAPP;
import static modelo.MedioComunicacion.SMS;

public class Test {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		SocioController socioController = SocioController.getInstance();
		EjemplarController ejemplarController = EjemplarController.getInstance();
		PrestamoController prestamoController = PrestamoController.getInstance();


		// 1. Alta Socio
		socioController.altaSocio(12345678, "Juan Perez", "123@gmail.com", "1234-5678", EMAIL);
		socioController.altaSocio(87654321, "Maria Perez", "321@gmail.com", "8765-4321", SMS);
		socioController.altaSocio(32165487, "Horacio Gimenez", "Horacundo@gmail.com", "7761-4321", WHATSAPP);
		socioController.altaSocio(98985454, "Furnacio Hernera", "Fhernera@gmail.com", "5725-7723", SMS);

		ArrayList<Socio> listado = SocioController.getInstance().getSocios();
		out.println("<<---------- 1. TODOS LOS SOCIOS ---------->>");
		listarSocios(listado);
		out.println("<<---------- ----- + ----- ---------->>");
		out.println();
		out.println();


		// 2. Alta Ejemplar
		ejemplarController.altaEjemplar("Platero y yo", new Date(2014, 1, 1), "Juan Ramon Gimenez", LIBRO);
		ejemplarController.altaEjemplar("Ole", new Date(2000, 1, 1), "Montoto", REVISTAESPECIALIZADA);
		ejemplarController.altaEjemplar("Homo Deus", new Date(2014, 1, 1), "Yuval Noah Harari", LIBRO);
		ejemplarController.altaEjemplar("De Humanos A Dioses", new Date(2014, 1, 1), "Yuval Noah Harari", LIBRO);
		ejemplarController.altaEjemplar("Constrole Su Destino", new Date(2014, 1, 1), " Anthony Robbins", LIBRO);

		List<Ejemplar> listado2 = ejemplarController.getEjemplares();
		out.println("<<---------- 2. TODOS LOS EJEMPLARES ---------->>");
		listarEjemplares(listado2);
		out.println("<<---------- ----- + ----- ---------->>");
		out.println();
		out.println();


		// 3.0 Busqueda por fecha
		out.println("<<---------- 3.0 BUSQUEDA POR FECHA DE PUBLICACION 2014 ---------->>");
		Date unaFecha = new Date(2014, 1, 1);
		List<Ejemplar> porFecha = ejemplarController.buscarEjemplaresFecha(unaFecha);
		listarEjemplares(porFecha);
		out.println("<<---------- ----- + ----- ---------->>");
		out.println();
		out.println();

		// 3.1 Busqueda por autor
		out.println("<<---------- 3.1 BUSQUEDA POR AUTOR: Yuval Noah Harari ---------->>");
		List<Ejemplar> porAutor = ejemplarController.buscarEjemplaresAut("Yuval Noah Harari");
		listarEjemplares(porAutor);
		out.println("<<---------- ----- + ----- ---------->>");
		out.println();
		out.println();

		// 3.2 Busqueda por ID de ejemplar
		out.println("<<---------- 3.2 BUSQUEDA POR ID DE EJEMPLAR: 1000 ---------->>");
		Ejemplar porID = ejemplarController.buscarEjemplarId(1000);
		imprimirEjemplar(porID);
		out.println("<<---------- ----- + ----- ---------->>");
		out.println();
		out.println();


		// 3.3 Busqueda por Titulo
		out.println("<<---------- 3.3 BUSQUEDA POR TITULO: Constrole Su Destino ---------->>");
		List<Ejemplar> porTitulo =ejemplarController.buscarEjemplaresTitulo("Constrole Su Destino");
		listarEjemplares(porTitulo);
		out.println("<<---------- ----- + ----- ---------->>");
		out.println();
		out.println();

		// 4. Actualizar el estado de ejemplares prestados presencialmente o vía web (préstamo y devolución).

		//4.1 PRESTAMO DEVUELTO A TIEMPO
		out.println("<<---------- 4.1 ACTUALIZACION DEL ESTADO DE EJEMPLARES: PRESTAMO DEVUELTO A TIEMPO---------->>");
		prestamoController.crearPrestamo(1001, 87654321);
		Prestamo prestamo = prestamoController.buscarPrestamo(1000);
		out.println("Primer prestamo creado: ");
		imprimirPrestamo(prestamo);
		out.println("Estado inicial del prestamo: " + prestamo.getEstado().toString());
		out.println();
		out.println("Lo finalizamos dentro de la fecha correspondiente");
		prestamoController.finalizarPrestamo(1000);
		out.println("Y el estado del prestamo pasa a estar:");
		imprimirPrestamo(prestamo);
		out.println("Estado final del prestamo: " + prestamo.getEstado().toString());
		out.println();
		out.println("<<---------- ----- + ----- ---------->>");
		out.println();
		out.println();

		//4.2 PRESTAMO DEVUELTO TARDE
		out.println("<<---------- 4.2 ACTUALIZACION DEL ESTADO DE EJEMPLARES: PRESTAMO DEVUELTO TARDE ---------->>");
		prestamoController.crearPrestamo(1002, 87654321);
		prestamo = prestamoController.buscarPrestamo(1001);
		out.println("Segundo prestamo creado: ");
		imprimirPrestamo(prestamo);
		out.println("Estado inicial del prestamo: " + prestamo.getEstado().toString());
		out.println();
		out.println("Modificamos la fecha de devolucion para que el prestamo venza: ");
		prestamo.modificarFechaVencimiento(-100);
		imprimirPrestamo(prestamo);
		out.println();

		//ejecuto el cronJob para que cambie el estado del prestamo y notifique al socio
		CronJobPrestamos.getInstance().actualizarEstado();

		prestamoController.finalizarPrestamo(1000);
		out.println("Y el estado del prestamo pasa a estar");
		imprimirPrestamo(prestamo);
		out.println("Estado final del prestamo: " + prestamo.getEstado().toString());
		out.println("<<---------- ----- + ----- ---------->>");
		out.println();
		out.println();


		// 5 Actualizar los dias de devolucion de un prestamo
		out.println("<<---------- 5. MODIFICAR EL PLAZO DE ENTRGA DE UN PRESTAMO MANUALMENTE ---------->>");
		prestamoController.crearPrestamo(1000, 12345678);
		Prestamo unPrestamo = prestamoController.getUltimoPrestamo();
		int idDelPrestamo = unPrestamo.getId();
		out.println("Damos un nuevo prestamo de alta: ");
		imprimirPrestamo(unPrestamo);
		out.println("Fecha original de vencimiento del prestamo: " + DateToString(unPrestamo.getFechaVencimiento()));
		prestamoController.actualizarDiasPrestamo(idDelPrestamo, 5); // Se agregan 5 dias
		out.println("Fecha de vencimiento luego de agregarle 5 dias mas: " + DateToString(unPrestamo.getFechaVencimiento()));
		out.println("Estado final del prestamo");
		imprimirPrestamo(unPrestamo);
		out.println("<<---------- ----- + ----- ---------->>");
		out.println();
		out.println();

		// 6. Notificar a socios acerca de situaciones particulares (próximo vencimiento, penalización por devolución fuera de término, premio por devoluciones)
		out.println("<<---------- 6. NOTIFICAR A UN SOCIO POR SITUACIONES PARTICULARES ---------->>");
		out.println("En este caso damos de alta 5 prestamos: ");
		prestamoController.crearPrestamo(1001, 12345678);
		prestamoController.crearPrestamo(1002, 12345678);
		prestamoController.crearPrestamo(1003, 12345678);
		prestamoController.crearPrestamo(1003, 12345678);
		prestamoController.crearPrestamo(1003, 12345678);
		imprimirPrestamo(prestamoController.buscarPrestamo(1002));
		imprimirPrestamo(prestamoController.buscarPrestamo(1003));
		imprimirPrestamo(prestamoController.buscarPrestamo(1004));
		imprimirPrestamo(prestamoController.buscarPrestamo(1005));
		imprimirPrestamo(prestamoController.buscarPrestamo(1006));
		out.println();

		out.println("Finalizamos a tiempo los 5 prestamos para bonificar al socio: ");
		prestamoController.finalizarPrestamo(1002);
		prestamoController.finalizarPrestamo(1003);
		prestamoController.finalizarPrestamo(1004);
		prestamoController.finalizarPrestamo(1005);
		prestamoController.finalizarPrestamo(1006);
		imprimirPrestamo(prestamoController.buscarPrestamo(1002));
		imprimirPrestamo(prestamoController.buscarPrestamo(1003));
		imprimirPrestamo(prestamoController.buscarPrestamo(1004));
		imprimirPrestamo(prestamoController.buscarPrestamo(1005));
		imprimirPrestamo(prestamoController.buscarPrestamo(1006));
		out.println();

		out.println("<<---------- ----- + ----- ---------->>");
		out.println();
		out.println();

		// 7. Visualizar el historial de prestamos de un socio
		out.println("<<---------- 7. VISUALIZAR LOS N ULTIMOS PRESTAMOS DE UN SOCIO ---------->>");
		prestamoController.crearPrestamo(1001, 98985454);
		prestamoController.crearPrestamo(1002, 98985454);
		prestamoController.crearPrestamo(1003, 98985454);
		List<Prestamo> ultimoPrestamos = socioController.getUltimosPrestamosDeSocio(98985454, 3);
		listarprestamos(ultimoPrestamos);
		out.println();
		out.println("<<---------- ----- + ----- ---------->>");
		out.println();
		out.println();




	}

	public static void listarEjemplares(List<Ejemplar> ejemplares){
		if (ejemplares.size() == 0){
			out.println("Lista Vacia...");
			return;
		} else {
			for(Ejemplar ejemplar : ejemplares) {
				out.println("Titulo: " + ejemplar.getTitulo()
						+ ", Publicado: "+ ejemplar.getFecha()
						+ ", Autor: "+ ejemplar.getAutor()
						+ ", Cateogira: "+ ejemplar.getCategory());
			}
		}
	}

	public static void listarSocios(List<Socio> socios){
		if (socios.size() == 0){
			out.println("Lista Vacia...");
			return;
		} else {
			for (Socio socio : socios) {
				out.println("DNI: " + socio.getDNI()
						+ ", Nombre: " + socio.getNombre()
						+ ", Mail: " + socio.getMail()
						+ ", Telefono: " + socio.getTelefono());
			}
		}
	}

	public static void listarprestamos(List<Prestamo> prestamos){
		if (prestamos.size() == 0){
			out.println("Lista Vacia...");
			return;
		} else {
			for (Prestamo prestamo : prestamos) {
				imprimirPrestamo(prestamo);
			}
		}
	}

	public static void imprimirPrestamo(Prestamo prestamo){
		out.println("DNI Socio: " + prestamo.getSocio().getDNI()
				+ ", Nombre Socio: " + prestamo.getSocio().getNombre()
				+ ", Titulo Del Ejemplar: " + prestamo.getEjemplar().getTitulo()
				+ ", ID del ejemplar: " + prestamo.getEjemplar().getId()
				+ ", Fecha de creacion del prestamo: " + DateToString(prestamo.getFechaDeCreacion())
				+ ", Fecha de vencimiento del prestamo: " + DateToString(prestamo.getFechaVencimiento())
				+ ", Estado del Prestamo: " + prestamo.getEstado().toString()
		);
	}

	public static void imprimirEjemplar(Ejemplar ejemplar){
		out.println("Titulo: " + ejemplar.getTitulo()
				+ ", Publicado: "+ ejemplar.getFecha()
				+ ", Autor: "+ ejemplar.getAutor()
				+ ", Cateogira: "+ ejemplar.getCategory());
	}

	public static String DateToString(Date fecha){
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		return formato.format(fecha);
	}
}
/*

- OK ---> 1. Cargar nuevos socios.
- OK ---> 2. Cargar ejemplares (libros, revistas especializadas, revistas, diarios).
- OK ---> 3. Buscar ejemplares según el criterio solicitado y mostrar datos completos (incluyendo ubicación).
- PENDIENTE ---> 4. Actualizar el estado de ejemplares prestados presencialmente o vía web (préstamo y devolución).
- OK ---> 5. Actualizar parámetros de préstamos (plazo en días).
- PENDIENTE ---> 6. Notificar a socios acerca de situaciones particulares (próximo vencimiento, penalización por devolución fuera de término, premio por devoluciones).
- OK ---> 7. Visualizar el historial de préstamos de un socio.

*/
