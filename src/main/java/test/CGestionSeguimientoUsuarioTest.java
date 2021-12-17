package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import datatypes.DtUsuario;
import logic.clases.Artista;
import logic.clases.Espectador;
import logic.clases.Usuario;
import logic.controladores.CGestionSeguimientoUsuario;
import logic.manejadores.ManejadorUsuario;

public class CGestionSeguimientoUsuarioTest {
	private static Date fecha = new Date();
	private static Usuario espSesionTest;
	private static Usuario artDeListaTest;
	private static Usuario espDeListaTest;
	private static DtUsuario dtuSesionTest;
	
	@BeforeAll
	public static void cargarUsuarios() {
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		espSesionTest = new Espectador("nicknameUsesion", "nombre", "apellido", "correoUsesion", fecha, "password", "urlImg");
		espDeListaTest = new Espectador("nicknameEsp", "nombre", "apellido", "correoEsp", fecha, "password", "urlImg");
		artDeListaTest = new Artista("nicknameArt","nombre","apellido","correoArt",fecha,"desc","url", "bio","pass","img");
		dtuSesionTest = new DtUsuario("nicknameUsesion", "nombre", "apellido", "correoUsesion", fecha, "password", "urlImg", false);
		mu.add(espSesionTest);
		mu.add(espDeListaTest);
		mu.add(artDeListaTest);
	}
	
	@Test
	@Order(1)
	void listarUsuariosTest() {
		CGestionSeguimientoUsuario cgsu = new CGestionSeguimientoUsuario();
		DtUsuario[] dtuarr  = cgsu.listarUsuarios(dtuSesionTest);
		
		int i = 0;
		int indexEspDeLista = 0;
		int indexArtDeLista = 0;
		boolean espEsta = false;
		boolean artEsta = false;
		while(!espEsta || !artEsta) {
			if(dtuarr[i].getNickname() == espDeListaTest.getNickname()) {
				indexEspDeLista = i;
				espEsta = true;
			}else if(dtuarr[i].getNickname() == artDeListaTest.getNickname()) {
				indexArtDeLista = i;
				artEsta = true;
			}
			i++;
		}
		
		assertTrue((dtuarr[indexEspDeLista].getNickname() == espDeListaTest.getNickname()) && (dtuarr[indexArtDeLista].getNickname() == artDeListaTest.getNickname()));
	}
	
	@Test
	@Order(2)
	void seguirUsuarioTest() {
		CGestionSeguimientoUsuario cgsu = new CGestionSeguimientoUsuario();
		cgsu.seguirUsuario(dtuSesionTest.getNickname(), artDeListaTest.getNickname());
		
		assertTrue(espSesionTest.listarSiguiendo().contains(artDeListaTest.getNickname()));
	}
	
	@Test
	@Order(3)
	void listarUsuariosSeguidosTest() {
		CGestionSeguimientoUsuario cgsu = new CGestionSeguimientoUsuario();
		cgsu.seguirUsuario(dtuSesionTest.getNickname(), artDeListaTest.getNickname());
		String[] usSeg = cgsu.listarUsuariosSeguidos(dtuSesionTest);
		
		assertTrue(usSeg[0].equals(artDeListaTest.getNickname()));
	}
	
	@Test
	@Order(4)
	void dejarDeSeguirUsuarioTest() {
		CGestionSeguimientoUsuario cgsu = new CGestionSeguimientoUsuario();
		cgsu.dejarDeSeguirUsuario(dtuSesionTest.getNickname(), artDeListaTest.getNickname());
		
		assertFalse(espSesionTest.listarSiguiendo().contains(artDeListaTest.getNickname()));
	}
	
	@AfterAll
	public static void finalizar() {
		System.out.println("Entra a finalizar");
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		mu.eliminarUsuario("nicknameUsesion");
		mu.eliminarUsuario("nicknameEsp");
		mu.eliminarUsuario("nicknameArt");
	}
}
