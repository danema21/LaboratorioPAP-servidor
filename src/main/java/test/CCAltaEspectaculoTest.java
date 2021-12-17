package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import exepciones.NombrePlataformaRepetidoException;
import exepciones.SinArtistasException;
import exepciones.SinPlataformasException;
import logic.clases.Artista;
import logic.clases.Espectaculo;
import logic.clases.Plataforma;
import logic.controladores.CAltaEspectaculo;
import logic.manejadores.ManejadorPlataforma;
import logic.manejadores.ManejadorUsuario;
import persistencia.Conexion;

class CCAltaEspectaculoTest {

	private static CAltaEspectaculo cae = new CAltaEspectaculo();
	private static String nomArt = "nickart";
	private static Artista art = null;
	private static Plataforma plat = null;
	private static String nombre_plataforma = "plataformatestCAE";

	@BeforeAll
	public static void cargar() throws NombrePlataformaRepetidoException {
		System.out.println("before");
		plat = new Plataforma(nombre_plataforma, "desc","url");
		ManejadorPlataforma.getInstance().agregaraPlataforma(plat);

		art = new Artista(nomArt,"nombre","apellido","correo",new Date(),"desc","url", "bio","pass","img");
		ManejadorUsuario.getInstancia().add(art);
		
	}
	
	@Test()
	@Order(1)
	public void listarPlataformas() throws SinPlataformasException {
		String[] p = cae.listarPlataformas();
		assertEquals(p.length, ManejadorPlataforma.getInstance().listarNombresPlataformas().size());
	}
	
	@Test()
	@Order(2)
	public void listarArtistas() throws SinArtistasException, SinPlataformasException {
		String[] a = cae.listarArtistas();
		assertEquals(a.length, ManejadorUsuario.getInstancia().listarNombresArtistas().size());
	}
	
	@Test()
	@Order(3)
	public void AltaEspectaculo() throws NombrePlataformaRepetidoException {
		cae.selectPlataforma(nombre_plataforma);
		cae.ingresarDatos("espec", "dsc", 12, 12, 13, "uurl", 123, "img");
		cae.altaEspectaculo(nomArt);
		Espectaculo es = ManejadorPlataforma.getInstance().buscarPlataforma(nombre_plataforma).buscarEspectaculo("espec");
		assertEquals("espec", es.getNombre());
	}
	
	
	
	@AfterAll
	public static void eliminarDatos() {
		System.out.println("after");
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		ManejadorPlataforma mp = ManejadorPlataforma.getInstance();
		Plataforma plataforma = mp.buscarPlataforma(nombre_plataforma); 
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		mu.eliminarUsuario(nomArt);
		mp.eliminarPlataforma(plataforma);
		em.getTransaction().begin();
		em.remove(plataforma);
		em.getTransaction().commit();
	}

}
