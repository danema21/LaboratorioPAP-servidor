package interfaces;

import exepciones.NombrePlataformaRepetidoException;

public interface ICAltaPlataforma {

	void ingresarDatos(String nombre, String descripcion, String url);
	void reingresarNombre(String nombre) throws NombrePlataformaRepetidoException;
	void altaPlataforma();
}
