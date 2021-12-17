	package logic.clases;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity(name = "usuario")
@javax.persistence.Table(name = "usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Usuario {
	@Id
	private String nickname;
	private String nombre;
	private String apellido;
	private String correo;
	private Date fechaNac;
	private String password;
	private String urlImg;
	
//	@OneToMany(cascade = CascadeType.ALL)
	//private List<Compra> compras = new ArrayList<Compra>();
	
	@ManyToMany
	private List<Usuario> siguiendo = new ArrayList<Usuario>();
	
	public Usuario() {
		super();
	}

	public Usuario(String nickname, String nombre, String apellido, String correo, Date fechaNac, String password, String urlImg) {
		super();
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.fechaNac = fechaNac;
		this.password = password;
		this.urlImg = urlImg;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrlImg() {
		return urlImg;
	}

	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}
	
	public void seguirUsuario(Usuario u) {
		siguiendo.add(u);
	}
	
	public void dejarDeSeguirUsuario(Usuario u) {
		siguiendo.remove(u);
	}
	
	public ArrayList<String> listarSiguiendo() {
		ArrayList<String> retorno = new ArrayList<>();
		for(Usuario u : siguiendo) {
			retorno.add(u.nickname);
		}
		
		return retorno;
	}
	
	//public void agregarCompra(Compra compra) {
		//this.compras.add(compra);
	//}

}
