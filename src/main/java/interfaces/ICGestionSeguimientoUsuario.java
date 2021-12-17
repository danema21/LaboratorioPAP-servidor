package interfaces;

import java.util.ArrayList;

import datatypes.DtUsuario;

public interface ICGestionSeguimientoUsuario {
	
	public DtUsuario[] listarUsuarios(DtUsuario usuarioSesion);
	
	public String[] listarUsuariosSeguidos(DtUsuario usuarioSesion);
	
	public void seguirUsuario(String origen, String aSeguir);
	
	public void dejarDeSeguirUsuario(String origen, String aDejarDeSeguir);

}
