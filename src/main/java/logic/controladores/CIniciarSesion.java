package logic.controladores;

import datatypes.DtArtista;
import datatypes.DtUsuario;
import interfaces.ICIniciarSesion;
import logic.clases.Artista;
import logic.clases.Usuario;
import logic.manejadores.ManejadorUsuario;

public class CIniciarSesion implements ICIniciarSesion {
	@Override
	public DtUsuario iniciarSesion(String idUsuario, String password) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
        Usuario u = mU.buscarUsuario(idUsuario);
        if(u != null && u.getPassword().equals(password)) {
        	if(u instanceof Artista) {
        		Artista a = (Artista) u;
        		DtUsuario dta = a.getDatosArtista();
        		return dta;
        	}
        	DtUsuario dtu = new DtUsuario(u.getNickname(), u.getNombre(), u.getApellido(), u.getCorreo(), u.getFechaNac(), u.getPassword(), u.getUrlImg(), u instanceof Artista);
        	return dtu;
        }else {
        	return null;
        }
	}
}