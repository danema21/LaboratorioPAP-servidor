package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import exepciones.SinPlataformasException;
import logic.clases.Espectaculo;
import logic.clases.Funcion;
import logic.clases.Plataforma;
import logic.manejadores.ManejadorPlataforma;
import logic.controladores.CConsultaDeFuncionDeEspectaculo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CConsultaDeFuncionDeEspectaculoTest {
	private static int cantPlat = 0;
	private static Plataforma plat = null;
	private static Espectaculo e = null;
	private static Funcion fun = null;
	private static CConsultaDeFuncionDeEspectaculo cfe = new CConsultaDeFuncionDeEspectaculo();
	private static ManejadorPlataforma mP = ManejadorPlataforma.getInstance();
	
	@BeforeAll
	private static void cargarValores() {
		plat = new Plataforma("Test", "descripcion", "url");
		cantPlat = mP.listarNombresPlataformas().size();
	}

	@Test
	@Order(1)
	public void listarPlataformasTest() throws SinPlataformasException {		
		mP.agregaraPlataforma(plat);
		int cantPlatTest = cfe.listarPlataformas().length;
		assertEquals(cantPlat+1, cantPlatTest);
	}
	
	@Test
	@Order(2)
	public void selectPlataformaTest() {
		Date fecha = new Date();
		e = new Espectaculo("nombre", "descripcion", 0, 0, 0, "url", 0, fecha, "img");
		int cantEspTest = 0;
		plat.agregarEspectaculo(e);
		assertEquals(cantEspTest+1, cfe.selectPlataforma("Test").length);
	}
	
	@Test
	@Order(3)
	public void selectEspectaculoTest() {
		int canFun = 0;
		assertEquals(canFun, cfe.selectEspectaculo(e.getNombre()).length);
	}
	
	@Test
	@Order(4)
	public void selectEspectaculoDePlataformaTest() {
		int canFun = 0;
		assertEquals(canFun, cfe.selectEspectaculoDePlataforma(plat.getNombre(),e.getNombre()).length);
	}
	
	@Test
	@Order(5)
	public void selectFuncionTest() {
		Date fecha = new Date();
		fun = new Funcion("Fun", fecha, 0, fecha, "url");
		e.agregarFuncion(fun);
		assertEquals(fun.getNombre(),cfe.selectFuncion(fun.getNombre()).getNombre());
		
	}
	
	@Test
	@Order(6)
	public void selectFuncionDeEspectaculoTest() {
		Date fecha = new Date();
		fun = new Funcion("Fun", fecha, 0, fecha, "url");
		e.agregarFuncion(fun);
		assertEquals(fun.getNombre(),cfe.selectFuncionDeEspectaculo(plat.getNombre(), e.getNombre() ,fun.getNombre()).getNombre());
		
	}
	
	@AfterAll
	private static void cleanValues() {
		mP.eliminarPlataforma(plat);
	}

}
