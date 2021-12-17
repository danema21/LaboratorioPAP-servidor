package logic.controladores;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import datatypes.DtUsuario;
import interfaces.ICGestionSeguimientoUsuario;
import logic.clases.Usuario;
import logic.manejadores.ManejadorUsuario;
import persistencia.Conexion;

public class CGestionSeguimientoUsuario implements ICGestionSeguimientoUsuario {
	
	@Override
	public DtUsuario[] listarUsuarios(DtUsuario usuarioSesion) {
		ArrayList<DtUsuario> listaUsuarios = ManejadorUsuario.getInstancia().listarUsuarios();
		int i = 0;
		boolean encontroUsuarioSesion = false;
		while(!encontroUsuarioSesion) {
			if(listaUsuarios.get(i).getNickname().equals(usuarioSesion.getNickname())) {
				listaUsuarios.remove(i);
				encontroUsuarioSesion = true;
			}
			i++;
		}
		
		DtUsuario[] retorno = new DtUsuario[listaUsuarios.size()];
		i=0;
        for(DtUsuario u : listaUsuarios) {
        	retorno[i] = u;
        	i++;
        }
		return retorno;
	}

	@Override
	public String[] listarUsuariosSeguidos(DtUsuario usuarioSesion){
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario usu = mU.buscarUsuario(usuarioSesion.getNickname());
		ArrayList<String> usuariosSeguidos = usu.listarSiguiendo();
		String[] retorno = new String[usuariosSeguidos.size()];
		int i=0;
        for(String s : usuariosSeguidos) {
        	retorno[i] = s;
        	i++;
        }
		
		return retorno;
	}
	
	@Override
	public void seguirUsuario(String uOrigen, String uASeguir) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario u = mU.buscarUsuario(uOrigen);
		Usuario u1 = mU.buscarUsuario(uASeguir);
		u.seguirUsuario(u1);
		
		Conexion conexion = Conexion.getInstancia();
	    EntityManager em = conexion.getEntityManager();
			
		em.getTransaction().begin();			
		em.persist(u);			
		em.getTransaction().commit();
	}
	
	@Override
	public void dejarDeSeguirUsuario(String uOrigen, String uADejarDeSeguir) {
		ManejadorUsuario mU = ManejadorUsuario.getInstancia();
		Usuario u = mU.buscarUsuario(uOrigen);
		Usuario u1 = mU.buscarUsuario(uADejarDeSeguir);
		u.dejarDeSeguirUsuario(u1);
		
		Conexion conexion = Conexion.getInstancia();
	    EntityManager em = conexion.getEntityManager();
			
		em.getTransaction().begin();			
		em.persist(u);			
		em.getTransaction().commit();
	}
}
