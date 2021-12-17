package exepciones;

public class SinArtistasException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public SinArtistasException(String mensaje){
		super(mensaje);
	}
}
