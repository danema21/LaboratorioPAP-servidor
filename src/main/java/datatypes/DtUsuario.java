package datatypes;

import java.util.Date;

public class DtUsuario {
	private String nickname;
	private String nombre;
	private String apellido;
	private String correo;
	public boolean isEsArtista() {
		return esArtista;
	}

	public void setEsArtista(boolean esArtista) {
		this.esArtista = esArtista;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}

	private Date fechaNac;
	private String password;
	private String urlImg;
	private boolean esArtista;
	
	public DtUsuario() {
		super();
	}

	public DtUsuario(String nickname, String nombre, String apellido, String correo, Date fechaNac, String password,
			String urlImg, boolean esArtista) {
		super();
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.fechaNac = fechaNac;
		this.password = password;
		this.urlImg = urlImg;
		this.esArtista = esArtista;
	}

	public boolean EsArtista() {
		return esArtista;
	}

	public String getNickname() {
		return nickname;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public String getPassword() {
		return password;
	}

	public String getUrlImg() {
		return urlImg;
	}
	
}
