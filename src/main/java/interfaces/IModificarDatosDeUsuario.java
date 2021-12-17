package interfaces;

import java.util.Date;

import datatypes.DtUsuario;

public interface IModificarDatosDeUsuario {
	
	public DtUsuario mostrarDatosDeUsuario(String nickUsuario);
	
	public void modificarNombre(String nombre);
	
	public void modificarApellido(String apellido);
	
	public void modificarFechaNacimiento(Date fechaNac);
	
	public void modificarPassword(String password);
	
	public void modificarUrlImg(String urlImg);
	
	public void modificarDescripcion(String descripcion);

	public void modificarLink(String link);
	
	public void modificarBiografia(String biografia);
}
