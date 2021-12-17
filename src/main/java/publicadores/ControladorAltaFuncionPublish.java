package publicadores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

import datatypes.DtArtista;
import datatypes.DtEspectaculo;
import exepciones.NombreFuncionRepetido;
import exepciones.SinEspectaculosException;
import exepciones.SinPlataformasException;
import interfaces.Fabrica;
import interfaces.ICAltaDeFuncionDeEspectaculo;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class ControladorAltaFuncionPublish {
	private Fabrica fabrica; 
	private ICAltaDeFuncionDeEspectaculo iControlador;
	private Endpoint endpoint = null;
	
	public ControladorAltaFuncionPublish(){
		fabrica = Fabrica.getInstance(); 
		iControlador = fabrica.getIAltaDeFuncionDeEspectaculo();
	}

	@WebMethod(exclude = true)
	public void publicar() {
		endpoint = Endpoint.publish("http://localhost:1942/ControladorAltaFuncion", this);
	}
	
	@WebMethod(exclude = true)
	public Endpoint getEndPoint() {
		return endpoint;
	}
	
	@WebMethod
	public String[] listarPlataformas() throws SinPlataformasException {
		ArrayList<String> plataformas = iControlador.listarPlataformas();
		return plataformas.toArray(new String[plataformas.size()]);
	}
	
	@WebMethod
	public String[] selectPlataforma(String nomPlataforma) throws SinEspectaculosException{
		List<String> espectaculos = iControlador.selectPlataforma(nomPlataforma);
		return espectaculos.toArray(new String[espectaculos.size()]);
	}
	
	@WebMethod
	public void ingresarDatos(String nomEspectaculo, String nomFuncion, Date fecha, int horaInicio,
							  Date fechaAlta, String urlImg) throws NombreFuncionRepetido {
		iControlador.ingresarDatos(nomEspectaculo, nomFuncion, fecha, horaInicio, fechaAlta, urlImg);
	}
	
	@WebMethod
	public DtArtista[] listarArtistas() {
		return iControlador.listarDtArtistasEnSistema();
	}
	
	@WebMethod
	public void seleccionarArtista(String nickArtista) {
		iControlador.seleccionarArtista(nickArtista);
	}
	
	@WebMethod 
	public void altaFuncion() {
		iControlador.altaFuncion();
	}
	
	@WebMethod
	public void cancelar() {
		iControlador.cancelar();
	}
	
	@WebMethod 
	public DtEspectaculo[] listarEspectaculosDeArtista(String correoArtista) {
		List<DtEspectaculo> dtEspectaculos = iControlador.listarEspectaculosDeArtista(correoArtista);
		
		if(dtEspectaculos != null) {
			return dtEspectaculos.toArray(new DtEspectaculo[dtEspectaculos.size()]);
		}
		
		return null;
	}
	
	@WebMethod
	public String[] listarNombresEspectaculosDeArtista(String nickArtista) {
		List<String> espectaculosDeArtista = iControlador.listarNombresEspectaculosDeArtista(nickArtista);
		return espectaculosDeArtista.toArray(new String[espectaculosDeArtista.size()]);
	}
	
	@WebMethod
	public String[] listarArtistasEnSistema() {
		List<String> artistas = iControlador.listarArtistasEnSistema();
		return artistas.toArray(new String[artistas.size()]);
	}
	
	@WebMethod 
	public String nombrePlataformaDelEspectaculo(String nomEspectaculo) {
		return iControlador.nombrePlataformaDelEspectaculo(nomEspectaculo);
	}
	
	@WebMethod
	public boolean existeNombreFuncion(String nomFuncion, String nomEspectaculo, String nomPlataforma) {
		return iControlador.existeNombreFuncion(nomFuncion, nomEspectaculo, nomPlataforma);
	}
	
	
	
}
