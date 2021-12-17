package test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import datatypes.DtArtista;
import datatypes.DtEspectaculo;
import exepciones.NombreFuncionRepetido;
import exepciones.NombrePlataformaRepetidoException;
import exepciones.SinEspectaculosException;
import exepciones.SinPlataformasException;
import logic.clases.Artista;
import logic.clases.Espectaculo;
import logic.clases.Funcion;
import logic.clases.Plataforma;
import logic.controladores.CAltaDeFuncionDeEspectaculo;
import logic.controladores.CAltaEspectaculo;
import logic.controladores.CAltaPlataforma;
import logic.manejadores.ManejadorPlataforma;
import logic.manejadores.ManejadorUsuario;
import persistencia.Conexion;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ControladorAltaFuncionTests {
	private static CAltaDeFuncionDeEspectaculo cafe = new CAltaDeFuncionDeEspectaculo();
	private static CAltaPlataforma cap = new CAltaPlataforma();
	private static CAltaEspectaculo cae = new CAltaEspectaculo();
	private static Date fecha = new Date();
	private static Artista arTest = null;
	private static String nomPlataforma = "plataformaTest";
	private static String nomEspectaculo = "espectaculoTest";
	private String compararFuncion = "funcionTest";
	
	@BeforeAll
	public static void before() throws NombrePlataformaRepetidoException {
		arTest = new Artista("nick","nombre","apellido","correo",fecha,"desc","url", "bio","pass","img");
		ManejadorUsuario.getInstancia().add(arTest);
		
		cap.ingresarDatos(nomPlataforma,"ninguna", "www.plataformaTest.com");
		cap.altaPlataforma();
		
		cae.selectPlataforma(nomPlataforma);
		cae.ingresarDatos(nomEspectaculo, "ninguna", 1, 1, 2, "www.espTest.com", 100, null);
		cae.altaEspectaculo(arTest.getNickname());
	} 

	@Test
	@Order(1)
	void sistemaTienePlataformas() throws SinPlataformasException {
			ArrayList<String> plataformas = cafe.listarPlataformas();
			assertNotNull(plataformas);
	}
	
	@Test
	@Order(2)
	void plataformaTieneEspectaculos() throws SinEspectaculosException {
		List<String> espectaculos = cafe.selectPlataforma(nomPlataforma);
		assertNotNull(espectaculos);
	}
	
	@Test
	@Order(3)
	void sistemaTieneArtistasEnSistema() {
		List<String> artistas = cafe.listarArtistasEnSistema();
		assertNotNull(artistas);
	}
	
	@Test
	@Order(4)
	void artistaTieneEspectaculos() {
		List<DtEspectaculo> espectaculosDeArtista = cafe.listarEspectaculosDeArtista(arTest.getNickname());
		assertNotNull(espectaculosDeArtista);
	}
	
	@Test
	@Order(5)
	void artistaSinEspectaculos() {
		List<DtEspectaculo> espectaculosDeArtista = cafe.listarEspectaculosDeArtista("noExiste");
		assertNull(espectaculosDeArtista);
	}
	
	@Test
	@Order(6)
	void hayDtArtistasEnSistema() {
		DtArtista[] artistas = cafe.listarDtArtistasEnSistema();
		assertNotNull(artistas);
	}
	
	@Test
	@Order(7)
	void noExisteFuncion() {
		assertFalse(cafe.existeNombreFuncion("NoExiste", nomEspectaculo, nomPlataforma));
	}
	
	@Test
	@Order(8)
	void obtenerPlataformaSegunEspectaculo() {
		String plataformaObtenida = cafe.nombrePlataformaDelEspectaculo(nomEspectaculo);
		assertEquals(nomPlataforma, plataformaObtenida);
	}
	
	@Test
	@Order(9)
	void altaFuncionTest() throws NombreFuncionRepetido, SinEspectaculosException {
		Plataforma plataforma = ManejadorPlataforma.getInstance().buscarPlataforma(nomPlataforma); 
		Espectaculo esp = plataforma.buscarEspectaculo(nomEspectaculo);
		cafe.selectPlataforma(nomPlataforma);
		cafe.ingresarDatos(nomEspectaculo, "funcionTest", fecha, 10, fecha, null);
		cafe.seleccionarArtista(arTest.getNickname());
		cafe.altaFuncion();
		assertTrue(esp.existeFuncion(compararFuncion));	
	}

	@Test
	@Order(10)
	void existeFuncion(){
		assertTrue(cafe.existeNombreFuncion(compararFuncion, nomEspectaculo, nomPlataforma));
	}
	
	@Test
	@Order(11)
	void cancelarTest() throws SinEspectaculosException, NombreFuncionRepetido{
		Plataforma plataforma = ManejadorPlataforma.getInstance().buscarPlataforma(nomPlataforma); 
		Espectaculo esp = plataforma.buscarEspectaculo(nomEspectaculo);
		cafe.selectPlataforma(nomPlataforma);
		cafe.ingresarDatos(nomEspectaculo, "funcionTest2", fecha, 10, fecha, null);
		cafe.seleccionarArtista(arTest.getNickname());
		cafe.cancelar();
		assertFalse(esp.existeFuncion("funcionTest2"));
	}
	
	@Test
	@Order(12)
	void artistaNoTieneEspectaculosString() {
		List<String> espectaculos = cafe.listarNombresEspectaculosDeArtista("");
		assertNull(espectaculos);
	}
	
	@Test
	@Order(13)
	void artistaTieneEspectaculosString() {
		List<String> espectaculos = cafe.listarNombresEspectaculosDeArtista(arTest.getNickname());
		assertNotNull(espectaculos);
	}
	
	@AfterAll
	public static void after() {
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		ManejadorPlataforma mp = ManejadorPlataforma.getInstance();
		Plataforma plataforma = mp.buscarPlataforma(nomPlataforma); 
		Espectaculo esp = plataforma.buscarEspectaculo(nomEspectaculo);
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		Funcion funcion = em.find(Funcion.class, "funcionTest");
		
		mu.eliminarUsuario(arTest.getNickname());
		plataforma.quitarEspectaculo(esp);
		mp.eliminarPlataforma(plataforma);
		esp.quitarFunciones();
		em.getTransaction().begin();
		em.remove(funcion);
		em.remove(esp);
		em.getTransaction().commit();			
	}
}	

