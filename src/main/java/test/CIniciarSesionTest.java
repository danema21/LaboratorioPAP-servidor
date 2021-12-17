package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import datatypes.DtUsuario;
import exepciones.UsuarioRepetidoException;
import logic.controladores.CAltaUsuario;
import logic.controladores.CIniciarSesion;
import logic.manejadores.ManejadorUsuario;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CIniciarSesionTest {
	
	private static DtUsuario dtu = new DtUsuario();
	private static Date fecha = new Date();
	private static DtUsuario dte = new DtUsuario();
	
	
	
	/*
	this.nickname = nickname;
	this.nombre = nombre;
	this.apellido = apellido;
	this.correo = correo;
	this.fechaNac = fechaNac;
	this.password = password;
	this.urlImg = urlImg;
	this.esArtista = esArtista;
	*/
	
	@BeforeAll
	static void usuarioNuevo() throws UsuarioRepetidoException {
		dtu = new DtUsuario("art0", "pelado", "miche", "lumi@gmail.com", fecha, "123", "img1", true);
		dte = new DtUsuario("pablito", "pablo", "bentancurt", "pablito@gmail.com", fecha, "123", "img1", false);
		CAltaUsuario cau = new CAltaUsuario();
		cau.ingresarDatos("pablito","pablito@gmail.com","pablo" ,"bentancurt", fecha, "123");
		cau.elegirImagen("img1");
		cau.altaUsuario();
		
		cau.ingresarDatos("art0", "pelado", "miche", "lumi@gmail.com", fecha, "123");
		cau.elegirImagen("img1");
		cau.ingresarDatosArtista("desc");
		cau.altaUsuario();
	}

	@Test
	@Order(1)
	void iniciarSesionTest() throws UsuarioRepetidoException {
		CIniciarSesion cis = new CIniciarSesion();
		DtUsuario dtt = cis.iniciarSesion("art0", "123");
		assertEquals(dtu.getNickname(), dtt.getNickname());
	}
	
	@Test
	@Order(2)
	void iniciarSesionEspTest() throws UsuarioRepetidoException {
		CIniciarSesion cis = new CIniciarSesion();
		DtUsuario dtt = cis.iniciarSesion("pablito", "123");
		assertEquals(dte.getNickname(), dtt.getNickname());
	}
	
	@Test
	@Order(3)
	void iniciarSesionNullTest() throws UsuarioRepetidoException {
		CIniciarSesion cis = new CIniciarSesion();
		DtUsuario dtt = cis.iniciarSesion("noex", "123");
		assertEquals(null, dtt);
	}
	
	@AfterAll
	public static void finalizar() {
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		mu.eliminarUsuario("art0");
		mu.eliminarUsuario("pablito");
		
	}

}
