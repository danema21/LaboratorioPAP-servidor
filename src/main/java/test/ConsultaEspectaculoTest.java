package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import exepciones.NombrePlataformaRepetidoException;
import exepciones.SinPlataformasException;
import logic.clases.Artista;
import logic.clases.Espectaculo;
import logic.clases.Plataforma;
import logic.controladores.CAltaEspectaculo;
import logic.controladores.CConsultaEspectaculo;
import logic.manejadores.ManejadorPlataforma;
import logic.manejadores.ManejadorUsuario;
import persistencia.Conexion;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ConsultaEspectaculoTest {
	
	private CConsultaEspectaculo ce = new CConsultaEspectaculo();
	private static CAltaEspectaculo cae = new CAltaEspectaculo();
	private static Artista art = null;
	private static Plataforma plat = null;
	private static String nombre_plataforma = "plataformatestCE";
	private static String nombre_espectaculo = "espectaculotestCE";
	
	@BeforeAll
	public static void cargar() throws NombrePlataformaRepetidoException {
		System.out.println("before");
		plat = new Plataforma(nombre_plataforma, "desc","url");
		ManejadorPlataforma.getInstance().agregaraPlataforma(plat);

		art = new Artista("nick","nombre","apellido","correo",new Date(),"desc","url", "bio","pass","img");
		ManejadorUsuario.getInstancia().add(art);
		
		cae.selectPlataforma(nombre_plataforma);

			cae.ingresarDatos(nombre_espectaculo, "test", 12, 12, 13, "url", 123, null);
	
		cae.altaEspectaculo("artistaTESTCE");
	}

	@Test()
	@Order(1)
	public void listarPlataformas() throws SinPlataformasException {
		List<String> p = ce.listarPlataformas();
		assertEquals(p, ManejadorPlataforma.getInstance().listarNombresPlataformas());
	}
	
	@Test()
	@Order(2)
	public void listarEspectaculos() {
		List<String> e = ce.listarEspectaculos(nombre_plataforma);
		assertNotNull(e);
	}
	
	@Test()
	@Order(3)
	public void mostrarDatosEspectaculos() {
		assertNotNull(ce.mostrarDatosEspectaculos(nombre_espectaculo));
	}
	
	@Test()
	@Order(4)
	public void espectaculoNoTieneFunciones() {
		ce.mostrarDatosEspectaculos(nombre_espectaculo);
		assertTrue(ce.espectaculoNoTieneFunciones());
    }
	
	@Test()
	@Order(5)
	public void plataformaTieneEspectaculo() {
		assertTrue(ce.plataformaTieneEspectaculo(nombre_espectaculo));
	}
	
	
	@AfterAll
	public static void eliminarDatos() {
		System.out.println("after");
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		ManejadorPlataforma mp = ManejadorPlataforma.getInstance();
		Plataforma plataforma = mp.buscarPlataforma(nombre_plataforma); 
		Espectaculo esp = plataforma.buscarEspectaculo(nombre_espectaculo);
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		mu.eliminarUsuario("nick");
		plataforma.quitarEspectaculo(esp);
		mp.eliminarPlataforma(plataforma);
		esp.quitarFunciones();
		em.getTransaction().begin();
		em.remove(esp);
		em.remove(plataforma);
		em.getTransaction().commit();
	}
}
