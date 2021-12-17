package interfaces;

import datatypes.DtFuncion;
import datatypes.DtPaquete;
import exepciones.SinPlataformasException;

import java.util.List;

import datatypes.DtDataEspectaculo;

public interface IConsultaEspectaculo {
	public List<String> listarPlataformas() throws SinPlataformasException;
    public List<String> listarEspectaculos(String nombrePlataforma);
    public DtDataEspectaculo mostrarDatosEspectaculos(String nombreEspectaculo);
    public boolean plataformaTieneEspectaculo(String nombreEspectaculo);
    public boolean espectaculoNoTieneFunciones();
}
