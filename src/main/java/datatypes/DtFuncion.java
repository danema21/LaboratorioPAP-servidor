package datatypes;

import java.util.Date;
import java.util.List;

public class DtFuncion {

    private String nombre;
	private Date fecha;
	private int horaInicio;
	private Date fechaRegistro;
	private String[] artistas;
	private String urlImg;

    public DtFuncion() {}

    public DtFuncion(String nombre, Date fecha, int horaInicio, Date fechaRegistro, String[] artistas, String urlImg) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.fechaRegistro = fechaRegistro;
        this.artistas = artistas;
        this.urlImg = urlImg;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setHoraInicio(int horaInicio) {
		this.horaInicio = horaInicio;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public void setArtistas(String[] artistas) {
		this.artistas = artistas;
	}

	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}

	public Date getFecha() {
        return fecha;
    }

    public int getHoraInicio() {
        return horaInicio;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }
    
    public String[] getArtistas() {
    	return this.artistas;
    }

	public String getUrlImg() {
		return urlImg;
	}
}
