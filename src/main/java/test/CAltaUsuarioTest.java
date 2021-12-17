package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;

import exepciones.UsuarioRepetidoException;
import logic.clases.Artista;
import logic.clases.Espectador;
import logic.controladores.CAltaUsuario;
import logic.manejadores.ManejadorUsuario;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CAltaUsuarioTest {
	private static Date fecha = new Date();
	private static Artista arTest;
	private static Espectador espTest;
	
	@BeforeAll
	public static void cargarUsuarios() {
		arTest = new Artista("nick","nombre","apellido","correo",fecha,"desc","url", "bio","pass","img");
		espTest = new Espectador("nickname", "nombre", "apellido", "correo", fecha, "password", "urlImg");
	}
	
	@Test
	@Order(1) 
	void ingresarDatosArtistaTest() throws UsuarioRepetidoException {
		CAltaUsuario cau = new CAltaUsuario();
		cau.ingresarDatos("nick","correo","nombre" ,"apellido", fecha, "pass");
		cau.ingresarDatosArtista("desc");
		cau.elegirImagen("img");
		cau.linkPagina("url");
		cau.ingresarBiografia("bio");
		cau.altaUsuario();
		
		assertEquals(arTest,(Artista) ManejadorUsuario.getInstancia().buscarUsuario("nick"));
		
	}
	
	@Test
	@Order(2) 
	void ingresarDatosEspectadorTest() throws UsuarioRepetidoException {
		CAltaUsuario cau = new CAltaUsuario();
		cau.ingresarDatos("nickname","email","nombre" ,"apellido", fecha, "password");
		cau.elegirImagen("img");
		cau.linkPagina("url");
		cau.ingresarBiografia(null);
		cau.altaUsuario();
		
		assertEquals(espTest,(Espectador) ManejadorUsuario.getInstancia().buscarUsuario("nickname"));
		
	}
	
	@Test
	@Order(3) 
	public void usuarioRepetidoNickTest() {
		CAltaUsuario cau = new CAltaUsuario();
		
	    Exception exception = assertThrows(UsuarioRepetidoException.class, () -> {
	    	cau.ingresarDatos("nick","correo","nombre" ,"apellido", fecha, "pass");
	    });
	    
	    String expectedMessage = "Ya hay un usuario con Nickname 'nick' en el sistema";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
		
	}
	
	@Test
	@Order(4) 
	public void usuarioRepetidoMailTest() {
		CAltaUsuario cau = new CAltaUsuario();
		
	    Exception exception = assertThrows(UsuarioRepetidoException.class, () -> {
	    	cau.ingresarDatos("nick2","correo","nombre" ,"apellido", fecha, "pass");
	    });
	    
	    String expectedMessage = "Ya hay un usuario con Email 'correo' en el sistema";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
		
	}	
	
	@AfterAll
	public static void finalizar() {
		System.out.println("Entra a finalizar");
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		mu.eliminarUsuario("nick");
		mu.eliminarUsuario("nickname");
		
	}
}
