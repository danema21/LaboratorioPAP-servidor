package logic.controladores;

import exepciones.NombrePlataformaRepetidoException;
import interfaces.ICAltaPlataforma;
import logic.clases.Plataforma;
import logic.manejadores.ManejadorPlataforma;

public class CAltaPlataforma implements ICAltaPlataforma{
	private String nombre;
	private String descripcion;
	private String url;

	@Override
	public void ingresarDatos(String nombre, String descripcion, String url){
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.url = url;
	}
	
	@Override
	public void reingresarNombre(String nombre) throws NombrePlataformaRepetidoException {
		ManejadorPlataforma mP = ManejadorPlataforma.getInstance();
		if(mP.buscarPlataforma(nombre) != null) {
			throw new NombrePlataformaRepetidoException("Ya hay una Plataforma de nombre '" + nombre + "' en el sistema");
		}
		this.nombre = nombre;
	}

	@Override
	public void altaPlataforma() {
		Plataforma plat = new Plataforma(this.nombre, this.descripcion, this.url);
		ManejadorPlataforma mP = ManejadorPlataforma.getInstance();
		mP.agregaraPlataforma(plat);
	}
}
