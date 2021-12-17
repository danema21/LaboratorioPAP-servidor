package logic.manejadores;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import datatypes.DtUsuario;
import logic.clases.Artista;
import logic.clases.Usuario;
import persistencia.Conexion;

public class ManejadorUsuario {
	
	private String descripcion;
	private String biografia;
	private String link;
	private static ManejadorUsuario instancia = null;
	private ArrayList<Usuario> usuarios = new ArrayList<>();
	
	private ManejadorUsuario(){}
	
	public static ManejadorUsuario getInstancia() {
		if(instancia == null) {
			instancia = new ManejadorUsuario();
		}
		return instancia;
	}
	
	public void add(Usuario usu) {
		usuarios.add(usu);
		Conexion conexion = Conexion.getInstancia();
	    EntityManager em = conexion.getEntityManager();
			
		em.getTransaction().begin();			
		em.persist(usu);			
		em.getTransaction().commit();
	}
	
	public boolean buscarUsuarioNickname(String nickname) {
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        
        Query query = em.createQuery("select u from usuario u where u.nickname = '" + nickname + "'");
        @SuppressWarnings("unchecked")
		List<Usuario> retorno = query.getResultList();
        
        if (retorno.isEmpty()) return false;
        
        return true;
	}
	
	public boolean buscarUsuarioEmail(String email) {
		Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        
        Query query = em.createQuery("select u from usuario u where u.correo = '" + email + "'");
        @SuppressWarnings("unchecked")
		List<Usuario> retorno = query.getResultList();
        
        if (retorno.isEmpty()) return false;
        
        return true;
	}
	
	public boolean esArtista(String nickUsuario) {
		Usuario usuario = this.buscarUsuario(nickUsuario);
		return (usuario != null) && (usuario instanceof Artista);
	}
	
	public Usuario buscarUsuario(String idUsuario) {
        Usuario  retorno = null;
        Conexion conexion = Conexion.getInstancia();
        EntityManager em = conexion.getEntityManager();
        
        retorno = em.find(Usuario.class, idUsuario);
        if(retorno == null) {
			Query query = em.createQuery("select u from usuario u where u.correo = '" + idUsuario + "'");
			@SuppressWarnings("unchecked")
			List<Usuario> retornoUsuario = query.getResultList();
			if(!retornoUsuario.isEmpty()) {
				retorno = retornoUsuario.get(0);
			}
        }
        
        return retorno;
	}
	
	public ArrayList<String> listarNombresArtistas() {
		Conexion conexion = Conexion.getInstancia();
	    EntityManager em = conexion.getEntityManager();
	    
	    Query query = em.createQuery("from usuario where dtype = 'A'");
	    //Query query = em.createQuery("select a from usuario a  where a.dtype = 'A'");
	    
	    @SuppressWarnings("unchecked")
		List<Usuario> lista = query.getResultList();
	    
	    ArrayList<String> retorna = new ArrayList<>();
	    
	    for (Usuario u: lista) {
	    	retorna.add(u.getNombre());
	    }
	    return retorna;
	}
	
	public List<String> listarNombresArtistasList() {
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		Query query = em.createQuery("from usuario where dtype = 'A'");
		
		@SuppressWarnings("unchecked")
		List<Usuario> lista = query.getResultList();
		
		List<String> listaResultado = new ArrayList<>();
		
		for(Usuario u: lista) {
			listaResultado.add(u.getNickname());
		}
		
		return listaResultado;
	}
	
	public ArrayList<DtUsuario> listarUsuarios(){
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		
		Query query = em.createQuery("from usuario");
		
		@SuppressWarnings("unchecked")
		List<Usuario> lista = query.getResultList();
		
		ArrayList<DtUsuario> listaResultado = new ArrayList<>();
		
		for(Usuario u: lista) {
			DtUsuario datos = new DtUsuario(u.getNickname(), u.getNombre(), u.getApellido(), u.getCorreo(), u.getFechaNac(), u.getPassword(), u.getUrlImg(), u instanceof Artista);
			listaResultado.add(datos);
		}
		
		return listaResultado;
	}
	
	public void eliminarUsuario(String idUsuario) {
		EntityManager em = Conexion.getInstancia().getEntityManager();
		Usuario usu = buscarUsuario(idUsuario);

		if(usu instanceof Artista) {
			Artista art = (Artista) usu; 
			art.eliminarEspectaculos();
		}

		em.getTransaction().begin();
		em.remove(usu);
		em.getTransaction().commit();
		
	}
}

