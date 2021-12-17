package exepciones;

public class ContraseniaIncorrectaException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public ContraseniaIncorrectaException(String mensaje) {
		super(mensaje);
	}

}
