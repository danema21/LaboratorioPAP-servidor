package datatypes;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class DtEspectaculo {
      
	private String nombre;
	private String descripcion;
	private int duracionMin;
	private int cantMinEspectadores;
	private int cantMaxEspectadores;
	private String url;
	private float costo;
	private Date fechaRegistro;
    private String urlImg;
	private DtFuncion[] funciones;
	

      public DtEspectaculo() {
            super();
      }

      public DtEspectaculo(String nombre, String descripcion, int duracionMin, int cantMinEspectadores,
			int cantMaxEspectadores, String url, float costo, Date fechaRegistro, String urlImg, DtFuncion[] funciones) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.duracionMin = duracionMin;
		this.cantMinEspectadores = cantMinEspectadores;
		this.cantMaxEspectadores = cantMaxEspectadores;
		this.url = url;
		this.costo = costo;
		this.fechaRegistro = fechaRegistro;
        this.urlImg = urlImg;
        this.funciones = funciones;
	}

	/**
     * @return String return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setDuracionMin(int duracionMin) {
		this.duracionMin = duracionMin;
	}

	public void setCantMinEspectadores(int cantMinEspectadores) {
		this.cantMinEspectadores = cantMinEspectadores;
	}

	public void setCantMaxEspectadores(int cantMaxEspectadores) {
		this.cantMaxEspectadores = cantMaxEspectadores;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public void setUrlImg(String urlImg) {
		this.urlImg = urlImg;
	}

	public void setFunciones(DtFuncion[] funciones) {
		this.funciones = funciones;
	}

	/**
     * @return String return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @return int return the duracionMin
     */
    public int getDuracionMin() {
        return duracionMin;
    }

    /**
     * @return int return the cantMinEspectadores
     */
    public int getCantMinEspectadores() {
        return cantMinEspectadores;
    }

    /**
     * @return int return the cantMaxEspectadores
     */
    public int getCantMaxEspectadores() {
        return cantMaxEspectadores;
    }

    /**
     * @return String return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @return float return the costo
     */
    public float getCosto() {
        return costo;
    }

    /**
     * @return Date return the fechaRegistro
     */
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public String getUrlImg() {
		return urlImg;
	}

     /* @return ArrayList<String> return the funciones
     */
    public DtFuncion[] getFunciones() {
        return funciones;
    }
}
