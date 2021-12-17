package publicadores;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

import datatypes.DtUsuario;
import interfaces.Fabrica;
import interfaces.ICGestionSeguimientoUsuario;
import interfaces.ICIniciarSesion;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class ControladorGestionSeguimientoUsuarioPublish {
	private Fabrica fabrica;
	private ICGestionSeguimientoUsuario icgsu;
	//private WebServiceConfiguracion config;
	private Endpoint endpoint = null;
	
	public ControladorGestionSeguimientoUsuarioPublish() {
		fabrica = Fabrica.getInstance();
		icgsu = fabrica.getCGestionSeguimientoUsuario();
	}
	
    @WebMethod(exclude = true)
    public void publicar(){
         endpoint = Endpoint.publish("http://localhost:1942/ControladorGestionSeguimientoUsuarioPublish", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
            return endpoint;
    }
    
    @WebMethod
	public DtUsuario[] listarUsuarios(DtUsuario usuarioSesion) {
		DtUsuario[] uns = icgsu.listarUsuarios(usuarioSesion);
		return uns;
	}
	
    @WebMethod
	public String[] listarUsuariosSeguidos(DtUsuario usuarioSesion) {
		String[] us = icgsu.listarUsuariosSeguidos(usuarioSesion);
		return us;
	}
	
    @WebMethod
	public void seguirUsuario(String origen, String aSeguir) {
		icgsu.seguirUsuario(origen, aSeguir);
	}
	
    @WebMethod
	public void dejarDeSeguirUsuario(String origen, String aDejarDeSeguir) {
    	icgsu.dejarDeSeguirUsuario(origen, aDejarDeSeguir);
    }
}
