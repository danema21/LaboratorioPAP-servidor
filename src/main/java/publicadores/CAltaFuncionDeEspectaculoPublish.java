package publicadores;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

import interfaces.Fabrica;
import interfaces.ICAltaDeFuncionDeEspectaculo;

@WebService
@SOAPBinding(style = Style.RPC, parameterStyle = ParameterStyle.WRAPPED)
public class CAltaFuncionDeEspectaculoPublish {
	private Fabrica fabrica;
	private ICAltaDeFuncionDeEspectaculo icafe;
	private Endpoint endpoint = null;
	
	
	public CAltaFuncionDeEspectaculoPublish() {
		fabrica = Fabrica.getInstance();
		icafe = fabrica.getIAltaDeFuncionDeEspectaculo();
	}
	
    @WebMethod(exclude = true)
    public void publicar(){
         endpoint = Endpoint.publish("http://localhost:1942/CAltaFuncionDeEspectaculoPublish", this);
    }

    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
            return endpoint;
    }
    @WebMethod
    public String nombrePlataformaDelEspectaculo(String nomEspectaculo){
    	return icafe.nombrePlataformaDelEspectaculo(nomEspectaculo);
    }
}


