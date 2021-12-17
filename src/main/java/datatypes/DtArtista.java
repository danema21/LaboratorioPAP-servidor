package datatypes;

import java.util.Date;
import java.util.List;

public class DtArtista extends DtUsuario{
	private String descripcion; 
	private String link; 
	private String biografia; 
	
	private List<DtFuncion> funciones;
	private List<DtEspectaculo> espectaculos;
	public DtArtista() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DtArtista(String nickname, String nombre, String apellido, String correo, Date fechaNac, String password,
			String urlImg,boolean esArtista, String descripcion, String link, String biografia, List<DtFuncion> funciones, List<DtEspectaculo> espectaculos) {
		super(nickname, nombre, apellido, correo, fechaNac, password, urlImg, esArtista);

		this.descripcion = descripcion; 
		this.link = link; 
		this.biografia = biografia; 
		this.funciones = funciones; 
		this.espectaculos = espectaculos; 
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getLink() {
		return link;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public void setFunciones(List<DtFuncion> funciones) {
		this.funciones = funciones;
	}

	public void setEspectaculos(List<DtEspectaculo> espectaculos) {
		this.espectaculos = espectaculos;
	}

	public List<DtFuncion> getFunciones() {
		return funciones;
	}

	public List<DtEspectaculo> getEspectaculos() {
		return espectaculos;
	}
}
