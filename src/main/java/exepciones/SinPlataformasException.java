package exepciones;

public class SinPlataformasException extends Exception{
	private static final long serialVersionUID = 1L;

	public SinPlataformasException(String string) {
		super(string);
	}
}