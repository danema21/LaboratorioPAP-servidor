package interfaces;


import logic.controladores.*;

public class Fabrica {
	
	private static Fabrica instancia = null;


	private Fabrica() {}
	
	public static Fabrica getInstance() {
		if (instancia == null) {
			instancia = new Fabrica();
	    }

	    return instancia;
	 }
	
	public ICAltaUsuario getCAltaUsuario() {
		CAltaUsuario icau = new CAltaUsuario();
		return icau;
	}
	
	
	public ICAltaPlataforma getCAltaPlataforma() {
		CAltaPlataforma icap = new CAltaPlataforma();
		return icap;
	}
	
	public ICAltaEspectaculo getCAltaEspectaculo(){
		CAltaEspectaculo icae = new CAltaEspectaculo();
		return icae;
	}

	public ICAltaDeFuncionDeEspectaculo getIAltaDeFuncionDeEspectaculo() {
		return new CAltaDeFuncionDeEspectaculo();
	}
	
	public IConsultaDeFuncionDeEspectaculo getIConsultaDeFuncionDeEspectaculo() {
		return new CConsultaDeFuncionDeEspectaculo();
	}

	public IConsultaEspectaculo getCConsultaEspectaculo() {
		return new CConsultaEspectaculo();
	}
	
	public ICGestionSeguimientoUsuario getCGestionSeguimientoUsuario() {
		return new CGestionSeguimientoUsuario();
	}
	
	public ICIniciarSesion getCIniciarSesion() {
		return new CIniciarSesion();
	}
	

}

