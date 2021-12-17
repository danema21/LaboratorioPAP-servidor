package interfaces;

import datatypes.DtUsuario;

public interface ICIniciarSesion {
	public DtUsuario iniciarSesion(String idUsuario, String password);
}
