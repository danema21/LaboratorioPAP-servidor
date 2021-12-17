package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import exepciones.NombrePlataformaRepetidoException;
import logic.clases.Plataforma;
import logic.controladores.CAltaPlataforma;
import logic.manejadores.ManejadorPlataforma;
import logic.manejadores.ManejadorUsuario;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ControladorAltaPlataformaTest {
	private static Plataforma platTest;
	private static Plataforma platTest2;
	
	@BeforeAll
	public static void cargarPlataforma() {
		platTest = new Plataforma("nombre", "descripcion", "url");
		platTest2 = new Plataforma("nombre2", "descripcion", "url");
	}

	@Test
	@Order(1)
	void ingresarDatosTest() {
		CAltaPlataforma cap= new CAltaPlataforma();
		cap.ingresarDatos("nombre", "descripcion", "url");
		cap.altaPlataforma();
		
		assertEquals(platTest, ManejadorPlataforma.getInstance().buscarPlataforma("nombre"));
	}
	
	@Test
	@Order(2)
	void reingresarNombreTest() throws NombrePlataformaRepetidoException {
		CAltaPlataforma cap = new CAltaPlataforma();
		cap.ingresarDatos("nombre", "descripcion", "url");
		cap.reingresarNombre("nombre2");
		cap.altaPlataforma();
		
		assertEquals(platTest2, ManejadorPlataforma.getInstance().buscarPlataforma("nombre2"));
	}
	
	@Test
	@Order(3)
	void nombrePlataformaRepetidoRepetidoTest() {
		CAltaPlataforma cap = new CAltaPlataforma();
		Exception exception = assertThrows(NombrePlataformaRepetidoException.class, () -> {
			cap.reingresarNombre("nombre2");
		});
		
		String expectedMessage = "Ya hay una Plataforma de nombre 'nombre2' en el sistema";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@AfterAll
	public static void finalizar() {
		System.out.println("Entra a finalizar");
		ManejadorPlataforma mp = ManejadorPlataforma.getInstance();
		mp.eliminarPlataforma(mp.buscarPlataforma("nombre"));
		mp.eliminarPlataforma(mp.buscarPlataforma("nombre2"));
	}
}
