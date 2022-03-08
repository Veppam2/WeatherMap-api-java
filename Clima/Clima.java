/**
 * Clase que define un objeto Clima que contiene cuidad, temperatura, tipo de clima, latitud, longitud presion atmosferica y humedad
 * @author Carlos Daniel Cortes Jimenez
 * @author Alexis de Jesús Arizmendi López
 * @author Víctor Emiliano Cruz Hérnandez
 */
  public class Clima{

  	private String ciudad;
  	private double temperatura;
  	private String tipoclima;
  	private double latitud;
  	private double longitud;
  	private double presionA;
  	private int humedad;

  	/**
  	 * Metodo set que establece un valor cuidad de tipo String a Clima
  	 */
  	public void setCuidad(String ciudad){
  		this.ciudad=ciudad;
  	}
  	/**
  	 * Metodo set que establece un valor temperatura de tipo double a Clima
  	 */
  	public void setTemperatura(double temperatura){
  		this.temperatura=temperatura;
  	}
  	/**
  	 * Metodo set que establece un valor tipoclima de tipo String a Clima
  	 */
  	public void setTipoClima(String tipoclima){
  		this.tipoclima=tipoclima;
  	}
  	/**
  	 * Metodo set que establece un valor latitud de tipo String a Clima
  	 */
  	public void setLatitud(double latitud){
  		this.latitud=latitud;
  	}
  	/**
  	 * Metodo set que establece un valor longitud de tipo String a Clima
  	 */
  	public void setLongitud(double longitud){
  		this.longitud=longitud;
  	}
  	/**
  	 * Metodo set que establece un valor presionA de tipo String a Clima
  	 */
  	public void setPresionA(double presionA){
  		this.presionA=presionA;
  	}
  	/**
  	 * Metodo set que establece un valor humedad de tipo String a Clima
  	 */
  	public void setHumedad(int humedad){
  		this.humedad=humedad;
  	}
  	/**
  	 * Metodo get que devuelve un valor de tipo String 
  	 * @return devuelve el nombre de una cuidad que se dado o asigando
  	 */
  	public String getCuidad(){
  		return ciudad;
  	}
  	/**
  	 * Metodo get que devuelve un valor de tipo double 
  	 * @return devuelve la temperatura dada o asiganada
  	 */
  	public double getTemperatura(){
  		return temperatura;
  	}
  	/**
  	 * Metodo get que devuelve un valor de tipo String 
  	 * @return devuelve el tipo de clima en el que se encuentre 
  	 */
  	public String getTipoClima(){
  		return tipoclima;
  	}
  	/**
  	 * Metodo get que devuelve un valor de tipo double 
  	 * @return devuelve la latitud del lugar en el que se encuentre
  	 */
  	public double getLatitud(){
  		return latitud;
  	}
  	/**
  	 * Metodo get que devuelve un valor de tipo double 
  	 * @return devuelve la longitud del lugar en el que se encuentre
  	 */
  	public double getLongitud(){
  		return longitud;
  	}
  	/**
  	 * Metodo get que devuelve un valor de tipo double 
  	 * @return devuelve la presion atmosferica del lugar en el que se encuentre
  	 */
  	public double getPresionA(){
  		return presionA;
  	}
  	/**
  	 * Metodo get que devuelve un valor de tipo int 
  	 * @return devuelve la humedad del lugar en el que se encuentre
  	 */
  	public int getHumedad(){
  		return humedad;
  	}

  }
