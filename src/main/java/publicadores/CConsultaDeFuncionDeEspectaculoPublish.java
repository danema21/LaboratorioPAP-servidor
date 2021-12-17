package publicadores;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

import datatypes.DtFuncion;
import exepciones.SinPlataformasException;
import interfaces.Fabrica;
import interfaces.IConsultaDeFuncionDeEspectaculo;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class CConsultaDeFuncionDeEspectaculoPublish {
	private Fabrica fabrica;
	private IConsultaDeFuncionDeEspectaculo icfe;
	//private WebServiceConfiguracion config;
	private Endpoint endpoint = null;
	
	public CConsultaDeFuncionDeEspectaculoPublish() {
		fabrica = Fabrica.getInstance();
		icfe = fabrica.getIConsultaDeFuncionDeEspectaculo();
	}
	
    @WebMethod(exclude = true)
    public void publicar(){
         endpoint = Endpoint.publish("http://localhost:1942/CConsultaDeFuncionDeEspectaculoPublish", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
            return endpoint;
    }
    @WebMethod
    public String[] listarPlataformas() throws SinPlataformasException {
    	return icfe.listarPlataformas();
    	
    }
    @WebMethod
    public String[] selectPlataforma(String nomPlataforma){
    	return icfe.selectPlataforma(nomPlataforma);
    }
    @WebMethod
    public String[] selectEspectaculoDePlataforma(String nomPlat, String nomEspectaculo) {
    	return icfe.selectEspectaculoDePlataforma(nomPlat, nomEspectaculo);
    }
    @WebMethod
    public DtFuncion selectFuncionDeEspectaculo(String nomPlat, String nomEspectaculo, String nomFuncion) {
    	return icfe.selectFuncionDeEspectaculo(nomPlat, nomEspectaculo, nomFuncion);
    }
       
}
