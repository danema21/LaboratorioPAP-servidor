package interfaces;

import java.util.Date;

import exepciones.UsuarioRepetidoException;
import exepciones.ContraseniaIncorrectaException;

public interface ICAltaUsuario {
	
	public void ingresarDatos(String nickname, String correo, String nombre, String apellido, Date fechaNac, String password) throws UsuarioRepetidoException;
		
	public void ingresarDatosArtista(String descripcion);
	
	public void linkPagina(String url);
	
	public void ingresarBiografia(String biografia);
	
	public void modificarMail(String email);
	
	public void modificarNickname(String nickname);

	public void confirmacionPass(String confirmarPassword) throws ContraseniaIncorrectaException;
	
	public void elegirImagen(String urlImg);
	
	public void altaUsuario();
}
