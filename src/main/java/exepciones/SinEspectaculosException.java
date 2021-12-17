package exepciones;

public class SinEspectaculosException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public SinEspectaculosException(String mensaje){
		super(mensaje);
	}
}
