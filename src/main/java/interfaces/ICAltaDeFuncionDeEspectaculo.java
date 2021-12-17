package interfaces;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import datatypes.DtArtista;
import datatypes.DtEspectaculo;
import exepciones.NombreFuncionRepetido;
import exepciones.SinEspectaculosException;
import exepciones.SinPlataformasException;

public interface ICAltaDeFuncionDeEspectaculo {
	
	public ArrayList<String> listarPlataformas() throws SinPlataformasException; 
	
	//devuelve una lista de los espectaculos asociados a nomPlataforma
	public List<String> selectPlataforma(String nomPlataforma) throws SinEspectaculosException;
	
	public void ingresarDatos(String nomEspectaculo, String nomFuncion, Date fecha, int horaInicio, Date fechaAlta, String urlImg) throws NombreFuncionRepetido;

	//agregar a el nombre de un artista a una coleccion de nombres de artistas
	public void seleccionarArtista(String nickArtista);
	
	public void altaFuncion();

	public void cancelar();
	
	public List<DtEspectaculo> listarEspectaculosDeArtista(String nickArtista);
	
	public List<String> listarNombresEspectaculosDeArtista(String nickArtista);
	
	public List<String> listarArtistasEnSistema();
	
	public DtArtista[] listarDtArtistasEnSistema();
	
	//Dado el nombre de un Espectaculo devuelve el nombre de la plataforma a la cual pertenece.
	public String nombrePlataformaDelEspectaculo(String nomEspectaculo);
	
	public boolean existeNombreFuncion(String nomFuncion, String nomEspectaculo, String nomPlataforma);
	
}
