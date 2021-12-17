package publicadores;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

import datatypes.DtDataEspectaculo;
import datatypes.DtEspectaculo;
import exepciones.SinPlataformasException;
import interfaces.Fabrica;
import interfaces.IConsultaEspectaculo;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class ControladorConsultaEspectaculoPublish {
	private Fabrica fabrica;
	private IConsultaEspectaculo ice;
	//private WebServiceConfiguracion config;
	private Endpoint endpoint = null;
	
	public ControladorConsultaEspectaculoPublish() {
		fabrica = Fabrica.getInstance();
		ice = fabrica.getCConsultaEspectaculo();
	}
	
    @WebMethod(exclude = true)
    public void publicar(){
         endpoint = Endpoint.publish("http://localhost:1942/ControladorConsultaEspectaculoPublish", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
            return endpoint;
    }

    @WebMethod
    public String[] listarPlataformas() throws SinPlataformasException {
    	List<String> plataformas = ice.listarPlataformas();
        String[] nombres = new String[plataformas.size()];
        int position = 0;
        
        for (String p: plataformas) {
        	nombres[position] = p;
        	position++;
        }
        return nombres;
    }

    @WebMethod
    public String[] listarEspectaculos(String nombrePlataforma) {
    	
    	List<String> espectaculos = ice.listarEspectaculos(nombrePlataforma);
        String[] nombres = new String[espectaculos.size()];
        int position = 0;
        
        for (String e: espectaculos) {
        	nombres[position] = e;
        	position++;
        }
        return nombres;
    }
    
    @WebMethod
    public DtEspectaculo mostrarDatosEspectaculos(String nombreEspectaculo) {
    	
    	DtDataEspectaculo dte = ice.mostrarDatosEspectaculos(nombreEspectaculo);
    	DtEspectaculo dtes = dte.getEspectaculo();
    	
    	return dtes;

    }

    @WebMethod
    public boolean plataformaTieneEspectaculo(String nombreEspectaculo) {
    	return plataformaTieneEspectaculo(nombreEspectaculo);
    }
    @WebMethod
    public boolean espectaculoNoTieneFunciones() {
    	return ice.espectaculoNoTieneFunciones();
    }

}