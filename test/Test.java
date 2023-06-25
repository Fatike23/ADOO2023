package test;

import java.util.ArrayList;
import java.util.Date;
import controladores.EjemplarController;
import controladores.SocioController;
import modelo.Ejemplar;
import modelo.Socio;
import modelo.Categoria;

public class Test {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		SocioController.getInstance().altaSocio(12345678, "Juan Perez", "123@gmail.com", "1234-5678");
		SocioController.getInstance().altaSocio(87654321, "Maria Perez", "321@gmail.com", "8765-4321");
		ArrayList<Socio> listado = SocioController.getInstance().getSocios();
		
		for(int i=0; i<listado.size(); i++) {		
			System.out.println("DNI: " + listado.get(i).getDNI() 
					+ ", Nombre: "+ listado.get(i).getNombre() 
					+ ", Mail: "+ listado.get(i).getMail() 
					+ ", Telefono: "+ listado.get(i).getTelefono());
		}
	
		EjemplarController.getInstance().altaEjemplar("Platero y yo", new Date(14, 1, 1), "Juan Ramon Gimenez", Categoria.LIBRO);
		EjemplarController.getInstance().altaEjemplar("Ole", new Date(115, 1, 1), "Montoto", Categoria.REVISTAESPECIALIZADA);
		ArrayList<Ejemplar> listado2 = EjemplarController.getInstance().getEjemplares();
		
		for(int i=0; i<listado.size(); i++) {		
			System.out.println("Titulo: " + listado2.get(i).getTitulo() 
					+ ", Publicado: "+ listado2.get(i).getFecha() 
					+ ", Autor: "+ listado2.get(i).getAutor() 
					+ ", Cateogira: "+ listado2.get(i).getCategory());
		}
			
	}
}
/*
-Cargar nuevos socios.
-Cargar ejemplares (libros, revistas especializadas, revistas, diarios).
-Buscar ejemplares según el criterio solicitado y mostrar datos completos (incluyendo ubicación).
-Actualizar el estado de ejemplares prestados presencialmente o vía web (préstamo y devolución).
-Actualizar parámetros de préstamos (plazo en días).
-Notificar a socios acerca de situaciones particulares (próximo vencimiento, penalización por devolución fuera de término, premio por devoluciones).
-Visualizar el historial de préstamos de un socio.
*/
