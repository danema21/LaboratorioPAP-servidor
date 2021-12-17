package logic.controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import datatypes.DtArtista;
import datatypes.DtEspectaculo;
import exepciones.NombreFuncionRepetido;
import exepciones.SinEspectaculosException;
import exepciones.SinPlataformasException;
import interfaces.ICAltaDeFuncionDeEspectaculo;
import logic.clases.Artista;
import logic.clases.Espectaculo;
import logic.clases.Funcion;
import logic.clases.Plataforma;
import logic.clases.Usuario;
import logic.manejadores.ManejadorPlataforma;
import logic.manejadores.ManejadorUsuario;
import persistencia.Conexion;

public class CAltaDeFuncionDeEspectaculo implements ICAltaDeFuncionDeEspectaculo{

	private String nomFuncion = null;
	private Date fechaAlta = null;
	private int horaInicio = 0;
	private Date fecha = null;
	private ArrayList<String> artistas = new ArrayList<String>();
	private String nomEspectaculo = null;
	private String nomPlataforma = null;
	private String urlImg = null;

	@Override
	public ArrayList<String> listarPlataformas() throws SinPlataformasException {
		ManejadorPlataforma mp = ManejadorPlataforma.getInstance();
		if(mp.listarNombresPlataformas().isEmpty())
				throw new SinPlataformasException("No hay Plataformas en el sistema");
		
		if(mp.sinPlataformas())
			throw new SinPlataformasException("Sin Plataformas");
		
		return mp.listarNombresPlataformas();
	}

	@Override
	public List<String> selectPlataforma(String nomPlataforma) throws SinEspectaculosException {
		Plataforma p = ManejadorPlataforma.getInstance().buscarPlataforma(nomPlataforma);
		
		this.nomPlataforma = nomPlataforma;
		if(p.listarEspectaculos().isEmpty())
			throw new SinEspectaculosException("Sin Espectaculos");
	
		return p.listarNombresEspectaculos();

	}

	@Override
	public void ingresarDatos(String nomEspectaculo, String nomFuncion, Date fecha, int horaInicio,
			Date fechaAlta, String urlImg) throws NombreFuncionRepetido {
		
		if(this.existeNombreFuncion(nomFuncion, nomEspectaculo, this.nomPlataforma)) {
			throw new NombreFuncionRepetido("Ya existe una funcion con el nombre" + nomFuncion + " en el sistema");
		}

		this.nomFuncion = nomFuncion;
		this.fecha = fecha;
		this.horaInicio = horaInicio; 
		this.fechaAlta = fechaAlta;
		this.nomEspectaculo = nomEspectaculo;
		this.urlImg = urlImg;
	}
	
	@Override
	public void seleccionarArtista(String nickArtista) {
		this.artistas.add(nickArtista);
	}

	@Override
	public void altaFuncion() {
		Funcion nuevaFuncion = new Funcion(this.nomFuncion, this.fecha, this.horaInicio, this.fechaAlta, this.urlImg);
		Plataforma p = ManejadorPlataforma.getInstance().buscarPlataforma(this.nomPlataforma);
		Espectaculo e = p.buscarEspectaculo(this.nomEspectaculo);		
		Conexion conexion = Conexion.getInstancia();
		EntityManager em = conexion.getEntityManager();
		e.agregarFuncion(nuevaFuncion);
		
		for(String nomArtista: this.artistas) {
			nuevaFuncion.agregarArtista(nomArtista);
		}

		em.getTransaction().begin();
		em.persist(nuevaFuncion); 
		em.getTransaction().commit();
		liberarMemoria();
	}
	
	private void liberarMemoria() {
		this.nomFuncion = null;
		this.fecha = null;
		this.fechaAlta = null; 
		this.nomEspectaculo = null;
		this.artistas.clear();
	}
	
	@Override
	public void cancelar() {
		liberarMemoria();
	}

	@Override
	public List<DtEspectaculo> listarEspectaculosDeArtista(String correoArtista) {
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		Usuario usuario = mu.buscarUsuario(correoArtista);
		if(usuario != null && mu.esArtista(usuario.getNickname())) {
			Artista artista = (Artista) usuario;
			return artista.listarEspectaculos();
		}		
		return null;
	}
	
	@Override
	public List<String> listarNombresEspectaculosDeArtista(String nickArtista){
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		Usuario usuario = mu.buscarUsuario(nickArtista);
		if(usuario != null &&mu.esArtista(usuario.getNickname())) {
			Artista artista = (Artista) usuario;
			return artista.listarNombresEspectaculo();
		}
		
		return null;
	}
	
	@Override
	public List<String> listarArtistasEnSistema(){
		return ManejadorUsuario.getInstancia().listarNombresArtistasList();
	}
	
	@Override
	public String nombrePlataformaDelEspectaculo(String nomEspectaculo) {
		return ManejadorPlataforma.getInstance().nombrePlataformaDeEspectaculo(nomEspectaculo);
	}
	
	@Override
	public boolean existeNombreFuncion(String nomFuncion, String nomEspectaculo, String nomPlataforma) {
		Plataforma p = ManejadorPlataforma.getInstance().buscarPlataforma(nomPlataforma);
		return p.existeFuncion(nomEspectaculo ,nomFuncion);
	}

	@Override
	public DtArtista[] listarDtArtistasEnSistema() {
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		List<String> nombresDeArtistas = this.listarArtistasEnSistema();
		DtArtista[] listaResultado = new DtArtista[nombresDeArtistas.size()];
		int iterador = 0;
		Usuario usuario = null;
		
		for(String nombre : nombresDeArtistas) {
			usuario = mu.buscarUsuario(nombre);		
			if(usuario instanceof Artista) {
				Artista artista = (Artista) usuario;
				listaResultado[iterador] = artista.getDatosArtista();
			}			
			
			iterador++;
		}
		
		return listaResultado;
	}
}
