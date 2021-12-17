package logic.clases;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("E")
public class Espectador extends Usuario {
	//@OneToMany(cascade = CascadeType.ALL)
	//private List<Registro> registros = new ArrayList<>();

	public Espectador() {
		super();
	}

	public Espectador(String nickname, String nombre, String apellido, String correo, Date fechaNac, String password,
			String urlImg) {
		super(nickname, nombre, apellido, correo, fechaNac, password, urlImg);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

}