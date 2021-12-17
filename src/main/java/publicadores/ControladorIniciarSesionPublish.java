package publicadores;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

import datatypes.DtUsuario;
import interfaces.Fabrica;
import interfaces.ICIniciarSesion;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class ControladorIniciarSesionPublish {
	private Fabrica fabrica;
	private ICIniciarSesion icis;
	//private WebServiceConfiguracion config;
	private Endpoint endpoint = null;
	
	public ControladorIniciarSesionPublish() {
		fabrica = Fabrica.getInstance();
		icis = fabrica.getCIniciarSesion();
	}
	
    @WebMethod(exclude = true)
    public void publicar(){
         endpoint = Endpoint.publish("http://localhost:1942/ControladorIniciarSesionPublish", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
            return endpoint;
    }
    @WebMethod
	public DtUsuario iniciarSesion(String idUsuario, String password) {
    	DtUsuario dtu = icis.iniciarSesion(idUsuario, password);
    	return dtu;
		
	}

}
