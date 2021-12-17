package datatypes;

import java.sql.Date;

public class DtVigencia {
	private Date fechaInicio;
	private Date fechaFin;
	
	public DtVigencia() {
		super();
	}

	public DtVigencia(Date fechaInicio, Date fechaFin) {
		super();
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}
	
	
}
