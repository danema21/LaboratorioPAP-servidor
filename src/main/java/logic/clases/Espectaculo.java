package logic.clases;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Query;

import datatypes.DtEspectaculo;
import datatypes.DtFuncion;
import persistencia.Conexion;
import javax.persistence.Access;
import static javax.persistence.AccessType.FIELD;
import static javax.persistence.AccessType.PROPERTY;

@Entity(name = "espectaculo")
@javax.persistence.Table(name = "espectaculo")
public class Espectaculo {
	@Id
	private String nombre;
	private String descripcion;
	private int duracionMin;
	private int cantMinEspectadores;
	private int cantMaxEspectadores;
	private String url;
	private float costo;
	private String img;
	//@Temporal(TemporalType.DATE)
	private Date fechaRegistro;
	@OneToMany
	private List<Funcion> funciones = new ArrayList<>();
	
	public Espectaculo() {
		super();
	}

	public Espectaculo(String nombre, String descripcion, int duracionMin, int cantMinEspectadores,
			int cantMaxEspectadores, String url, float costo, Date fechaRegistro, String img) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.duracionMin = duracionMin;
		this.cantMinEspectadores = cantMinEspectadores;
		this.cantMaxEspectadores = cantMaxEspectadores;
		this.url = url;
		this.costo = costo;
		this.fechaRegistro = fechaRegistro;
		this.img = img;
		
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

	public int getDuracionMin() {
		return duracionMin;
	}

	public void setDuracionMin(int duracionMin) {
		this.duracionMin = duracionMin;
	}

	public int getCantMinEspectadores() {
		return cantMinEspectadores;
	}

	public void setCantMinEspectadores(int cantMinEspectadores) {
		this.cantMinEspectadores = cantMinEspectadores;
	}

	public int getCantMaxEspectadores() {
		return cantMaxEspectadores;
	}

	public void setCantMaxEspectadores(int cantMaxEspectadores) {
		this.cantMaxEspectadores = cantMaxEspectadores;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setImg(String img){
		this.img = img;
	}

	public String getImg(){
		return this.img;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	public void agregarFuncion(Funcion f) {
		funciones.add(f);
	}
	
	public void quitarFuncion(Funcion f) {
		funciones.remove(f);
	}
	
	public List<Funcion> getFunciones() {
		return this.funciones;
	}

	//Crea un DtEspectaculo con los datos de espectaculo y las funciones asociadas
	@SuppressWarnings("null")
	public DtEspectaculo getData() {
		DtFuncion[] data_funciones = new DtFuncion[funciones.size()];

		if(this.funciones.isEmpty()) {
			data_funciones = null;
		} else {
			Iterator<Funcion> iterator = funciones.iterator();
			Funcion funcion;
			int i = 0;
			
			while (iterator.hasNext()) {
				funcion = iterator.next();
				data_funciones[i] = funcion.getData();
				i++;
			}
		}


		DtEspectaculo newEspectaculo = new DtEspectaculo(nombre,descripcion,
										duracionMin, 
										cantMinEspectadores, 
										cantMaxEspectadores, 
										url, 
										costo, 
										fechaRegistro, 
										img,
										data_funciones);

		return newEspectaculo;
	}

	public boolean existeFuncion(String nomFuncion) {
		Iterator<Funcion> iterator = this.funciones.iterator();
		boolean encontrado = false;

		if(this.funciones.isEmpty()) {
			return false;
		}

		Funcion f;
		while(iterator.hasNext() && !encontrado) {
			f = iterator.next();
			if (f.getNombre().equals(nomFuncion)) {
				encontrado = true;
			}
			
		}
		
		return encontrado;
	}
	
	public DtFuncion getDtFuncion(String nombreFuncion) {
		
		Iterator<Funcion> iterator = funciones.iterator();
		Funcion funcion = null;
		boolean encontrado = false;
		while(iterator.hasNext() && !encontrado){
			funcion = iterator.next();
			if(funcion.getNombre().equals(nombreFuncion))
				encontrado = true;				
		}
		return funcion.getData();

	}

	public String[] listarFuncionesString() {
		String[] listFunciones = new String[this.funciones.size()];
		int i = 0; 
		
		for(Funcion funcion : this.funciones) {
			listFunciones[i] = funcion.getNombre();
			i++;
		}
		
		return listFunciones;
	}
	
	public void quitarFunciones() {
		this.funciones.clear();
	}
}
