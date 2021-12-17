package logic.controladores;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import datatypes.DtDataEspectaculo;
import datatypes.DtEspectaculo;
import datatypes.DtFuncion;
import datatypes.DtPaquete;
import exepciones.SinPlataformasException;
import interfaces.IConsultaEspectaculo;
import logic.clases.Espectaculo;
import logic.clases.Funcion;
import logic.clases.Plataforma;
import logic.manejadores.ManejadorPlataforma;
import persistencia.Conexion;

public class CConsultaEspectaculo implements IConsultaEspectaculo {

    private static Plataforma plataforma;
    private static Espectaculo espectaculo;
    
    @Override
    public List<String> listarPlataformas() throws SinPlataformasException {
    	ArrayList<String> plataformas = ManejadorPlataforma.getInstance().listarNombresPlataformas();
    	return plataformas;
    }

    @Override
    public List<String> listarEspectaculos(String nombrePlataforma) {
        
        this.plataforma = ManejadorPlataforma.getInstance().buscarPlataforma(nombrePlataforma);
        
        List<Espectaculo> espectaculos = this.plataforma.listarEspectaculos();
        List<String> nombres = new ArrayList<>();
        
        for (Espectaculo e: espectaculos) {
        	nombres.add(e.getNombre());
        }

        return nombres;
    }

    @Override
    public DtDataEspectaculo mostrarDatosEspectaculos(String nombreEspectaculo) {
        this.espectaculo = this.plataforma.buscarEspectaculo(nombreEspectaculo);
        DtEspectaculo data = this.espectaculo.getData();
        DtDataEspectaculo data_show = new DtDataEspectaculo(data, null);
        return data_show;
    }

    @Override
    public boolean plataformaTieneEspectaculo(String nombreEspectaculo) {
    	List<Espectaculo> espectaculos = this.plataforma.getEspectaculos();
    	Iterator<Espectaculo> iterator = espectaculos.iterator();
    	
    	if (espectaculos.isEmpty()) {
    		return false;
    	}
    	Espectaculo e = espectaculos.get(0);
    	
    	while(iterator.hasNext() && !e.getNombre().equals(nombreEspectaculo)) {
    		e = iterator.next();
    	}
    	
    	if(e.getNombre().equals(nombreEspectaculo)) {
    		return true;
    	}
    	
    	return false;
    }
    
    @Override
    public boolean espectaculoNoTieneFunciones() {
    	return this.espectaculo.getFunciones().isEmpty();
    }
   
}
