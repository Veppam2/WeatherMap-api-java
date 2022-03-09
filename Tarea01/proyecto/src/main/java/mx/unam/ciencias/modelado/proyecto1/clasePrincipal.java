package mx.unam.ciencias.modelado.proyecto1;

public class clasePrincipal{
	public static void main( String[] args ){
		
		System.out.println( "Hello World!" );
		
		//ssss
		String lat = "19.331";
		String lon = "-99.566";

		Clima c = new Clima( "Nayarit", lat, lon );

		c.llenarAtributos();
	}
}

