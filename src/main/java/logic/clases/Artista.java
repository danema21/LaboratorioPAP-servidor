package logic.clases;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import datatypes.DtArtista;
import datatypes.DtEspectaculo;
import datatypes.DtFuncion;

@Entity
@DiscriminatorValue("A")
public class Artista extends Usuario{
	private String descripcion;
	private String link;
	private String biografia;

	@ManyToMany(cascade = CascadeType.ALL)
	private List<Funcion> funciones = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL)
	private List<Espectaculo> espectaculos = new ArrayList<>();
	
	public Artista() {
		super();
	}

	public Artista(String nickname, String nombre, String apellido, String correo, Date fechaNac, String descripcion, String url, String biografia, String password,
			String urlImg) {
		super(nickname, nombre, apellido, correo, fechaNac, password, urlImg);
		this.descripcion = descripcion;
		this.link = url;
		this.biografia = biografia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
	
	public void agregarParticipacionDeFuncion(Funcion funcion) {
		this.funciones.add(funcion);
	}
	
	public void agregarEspectaculo(Espectaculo espectaculo) {
		this.espectaculos.add(espectaculo);
	}

	public List<DtEspectaculo> listarEspectaculos(){
		List<DtEspectaculo> listaResultado = new ArrayList<>();
		
		for(Espectaculo espectaculo: this.espectaculos) {
			DtEspectaculo dtEspectaculo = espectaculo.getData();
			listaResultado.add(dtEspectaculo);
		}
		
		return listaResultado;
	}
	
	public List<String> listarNombresEspectaculo(){
		List<String> listaResultado = new ArrayList<>();
		
		for(Espectaculo espectaculo: this.espectaculos) {
			listaResultado.add(espectaculo.getNombre());
		}
		
		return listaResultado;
	}

	public DtArtista getDatosArtista() {
		List<DtFuncion> dtFuncionesArtista = new ArrayList<DtFuncion>();
		List<DtEspectaculo> dtEspectaculosArtista = new ArrayList<DtEspectaculo>();
		
		for(int i = 0; i < this.funciones.size();i++) {
			dtFuncionesArtista.add(funciones.get(i).getData());
		}
		
		for(int j = 0; j < this.espectaculos.size(); j++) {
			dtEspectaculosArtista.add(espectaculos.get(j).getData());
		}
		
		return new DtArtista(this.getNickname(),this.getNombre(),this.getApellido(),this.getCorreo(),
				this.getFechaNac(),this.getPassword(),this.getUrlImg(),true, this.descripcion,this.link,
				this.biografia, dtFuncionesArtista, dtEspectaculosArtista);
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
		Artista test = (Artista) obj;
		if(test.getDescripcion() != this.descripcion)
			return false;
		if(test.getBiografia() != this.biografia)
			return false;
		if(test.getLink() != this.link)
			return false;
		return true;
	}
}