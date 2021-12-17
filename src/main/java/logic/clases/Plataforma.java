package logic.clases;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Query;

import datatypes.DtEspectaculo;
import persistencia.Conexion;

@Entity(name = "plataforma")
@javax.persistence.Table(name = "plataforma")
public class Plataforma {
	@Id
	private String nombre;
	private String descripcion;
	private String url;

	@OneToMany(orphanRemoval = true)
	private List<Espectaculo> espectaculos = new ArrayList<>();


	public Plataforma() {
		super();
	}

	public Plataforma(String nombre, String descripcion, String url) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.url = url;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	//methods
	public ArrayList<Espectaculo> getEspectaculos() {
		ArrayList<Espectaculo> espect = new ArrayList<>();
		for(Espectaculo e: espectaculos) {
			espect.add(e);
		}
		return espect;
	}
	
	public ArrayList<String> listaEspectaculos() {
		ArrayList<String> lista = new ArrayList<String>();
		Iterator<Espectaculo> iterator = espectaculos.iterator();
		Espectaculo espectaculo = null;

		while (iterator.hasNext()) {
			espectaculo = iterator.next();
			lista.add(espectaculo.getNombre());
		}
		return lista;
	}
	
	public List<Espectaculo> listarEspectaculos() {
		return this.espectaculos;
	}

	public void agregarEspectaculo(Espectaculo e) {
		this.espectaculos.add(e);
	}
	
	public void quitarEspectaculo(Espectaculo e) {
		this.espectaculos.remove(e);
	}
	
	public String[] listarEspectaculosString() {
		String[] listEspectaculos = new String[this.espectaculos.size()]; 
		int i = 0; 
		
		for(Espectaculo espectaculo : this.espectaculos) {
			listEspectaculos[i] = espectaculo.getNombre();
			i++;
		}
		
		return listEspectaculos;
	}


	public Espectaculo buscarEspectaculo(String nombreEspectaculo) {
		Iterator<Espectaculo> iterator = this.espectaculos.iterator();
		Espectaculo e = null;
		boolean encontrado = false;

		if(this.espectaculos.isEmpty()) {
			return null;
		}
		
		while(iterator.hasNext() && !encontrado) {
			e = iterator.next();
			if (e.getNombre().equals(nombreEspectaculo)) {
				return e;
			}
		}
		
		return null;
	}

	public List<String> listarNombresEspectaculos() {
		List<String> nombres = new ArrayList<>();

		for (Espectaculo e: this.espectaculos) {
			nombres.add(e.getNombre());
		}

		return nombres;
	}
	public boolean existeFuncion(String nomEspectaculo , String nomFuncion) {
		Espectaculo e = buscarEspectaculo(nomEspectaculo);
		return e.existeFuncion(nomFuncion);
	}
	
	public boolean tieneEspectaculo(String nomEspectaculo) {
		int i = 0; 
		int cantEspectaculos = this.espectaculos.size();
		
		while(i < cantEspectaculos && !this.espectaculos.get(i).getNombre().equals(nomEspectaculo)) {
			i++;
		}
		
		if(i < cantEspectaculos) {
			return true;
		}
		
		return false;
	}
	
	public void eliminarEspectaculos() {
		this.espectaculos.clear();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plataforma test = (Plataforma) obj;
		if(test.getNombre() != this.nombre)
			return false;
		if(test.getDescripcion() != this.descripcion)
			return false;
		if(test.getUrl() != this.url)
			return false;
		return true;
	}
}