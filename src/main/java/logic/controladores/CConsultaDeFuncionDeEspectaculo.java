package logic.controladores;

import java.util.ArrayList;

import datatypes.DtFuncion;
import exepciones.SinPlataformasException;
import interfaces.IConsultaDeFuncionDeEspectaculo;
import logic.clases.Espectaculo;
import logic.clases.Plataforma;
import logic.manejadores.ManejadorPlataforma;

public class CConsultaDeFuncionDeEspectaculo implements IConsultaDeFuncionDeEspectaculo {
	private Plataforma plataforma;
	private Espectaculo espectaculo;

	@Override
	public String[] listarPlataformas() throws SinPlataformasException{
		ManejadorPlataforma mP = ManejadorPlataforma.getInstance();
		ArrayList<String> plataformas = mP.listarNombresPlataformas();
		if(plataformas.isEmpty())
			throw new SinPlataformasException("No existen Plataformas creadas.");
		
		String[] plat_ret = new String[plataformas.size()];
        int i=0;
        for(String p:plataformas) {
        	plat_ret[i]=p;
        	i++;
        }
        return plat_ret;
	}

	@Override
	public String[] selectPlataforma(String nomPlataforma) {
		this.plataforma = ManejadorPlataforma.getInstance().buscarPlataforma(nomPlataforma);
		return plataforma.listarEspectaculosString();
	}


	@Override
	public String[] selectEspectaculo(String nomEspectaculo) {
		this.espectaculo = plataforma.buscarEspectaculo(nomEspectaculo);
		return espectaculo.listarFuncionesString();
	}
	
	@Override
	public String[] selectEspectaculoDePlataforma(String nomPlat, String nomEspectaculo) {
		Plataforma plat = ManejadorPlataforma.getInstance().buscarPlataforma(nomPlat);
		Espectaculo esp = plat.buscarEspectaculo(nomEspectaculo);
		
		return esp.listarFuncionesString();
	}


	@Override
	public DtFuncion selectFuncion(String nomFuncion) {
		return espectaculo.getDtFuncion(nomFuncion);
	}
	
	@Override
	public DtFuncion selectFuncionDeEspectaculo(String nomPlat, String nomEspectaculo, String nomFuncion) {
		Plataforma plat = ManejadorPlataforma.getInstance().buscarPlataforma(nomPlat);
		Espectaculo esp = plat.buscarEspectaculo(nomEspectaculo);
		return esp.getDtFuncion(nomFuncion);		 
	}
	
}
