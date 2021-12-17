package publicadores;

import exepciones.ContraseniaIncorrectaException;
import exepciones.UsuarioRepetidoException;
import interfaces.Fabrica;
import interfaces.ICAltaUsuario;
import java.util.Date;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public class ControladorAltaUsuarioPublish {
    
    private Fabrica fabrica;
    private ICAltaUsuario icau;
    private Endpoint endpoint = null;

    public ControladorAltaUsuarioPublish() {
        fabrica = Fabrica.getInstance();
        icau = fabrica.getCAltaUsuario();
    }
    
    @WebMethod(exclude = true)
    public void publicar() {
        endpoint = Endpoint.publish("http://localhost:1942/ControladorAltaUsuarioPublish", this);
    }
    
    @WebMethod(exclude = true)
    public Endpoint getEndpoint() {
        return endpoint;
    }
    
    //METODOS A PUBLICAR
    
    @WebMethod
    public void ingresarDatos(String nickname, String correo, String nombre, String apellido, Date fechaNac, String password) throws UsuarioRepetidoException {
        icau.ingresarDatos(nickname, correo, nombre, apellido, fechaNac, password);
    }

    @WebMethod
    public void confirmacionPass(String confirmarPassword) throws ContraseniaIncorrectaException {
        icau.confirmacionPass(confirmarPassword);
    }

    @WebMethod
    public void ingresarDatosArtista(String descripcion) {
        icau.ingresarDatosArtista(descripcion);
    }

    @WebMethod
    public void elegirImagen(String urlImg) {
        icau.elegirImagen(urlImg);
    }

    @WebMethod
    public void linkPagina(String url) {
        icau.linkPagina(url);
    }

    @WebMethod
    public void ingresarBiografia(String biografia) {
        icau.ingresarBiografia(biografia);
    }

    @WebMethod
    public void modificarMail(String email) {
        icau.modificarMail(email);
    }

    @WebMethod
    public void modificarNickname(String nickname) {
        icau.modificarNickname(nickname);
    }

    @WebMethod
    public void altaUsuario() {
        icau.altaUsuario();
    }
}

