package excepciones;

public class RestauranteException extends Exception {
	
	public RestauranteException(String mensaje) {
		super(mensaje);
		this.printStackTrace();
		
	}

}
