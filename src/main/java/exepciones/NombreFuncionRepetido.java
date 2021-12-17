package exepciones;

public class NombreFuncionRepetido extends Exception{
	private static final long serialVersionUID = 1L;

	public NombreFuncionRepetido(String mensaje) {
		super(mensaje);
	}
}
