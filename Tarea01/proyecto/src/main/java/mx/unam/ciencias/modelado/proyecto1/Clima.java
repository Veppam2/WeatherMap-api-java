package mx.unam.ciencias.modelado.proyecto1;

//---------
import java.util.Map;
import java.util.Set;
//---------

import java.io.Serializable;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

/**
 * Clase que define un objeto Clima que contiene cuidad, temperatura, tipo de clima, latitud, longitud presion atmosferica y humedad
 * @author Carlos Daniel Cortes Jimenez
 * @author Alexis de Jesús Arizmendi López
 * @author Víctor Emiliano Cruz Hérnandez
 */
  public class Clima implements Serializable{

  	private String ciudad;
  	private String temperatura;
  	private String tipoclima;
	private String descClima;
  	private String latitud;
  	private String longitud;
  	private String presionA;
  	private String humedad;

	
	public Clima(String c, String lat, String lon){
		this.ciudad = c;
		this.latitud = lat;
		this.longitud = lon;
	}

	public void llenarAtributos(){

		String json  = 	PeticionWM.pedirDatos(this.latitud, this.longitud);

		//System.out.println(json);
		
		try{	
			Object obj = new JSONParser().parse( json );
			JSONObject jo = ( JSONObject ) obj;

			//el rubro main tiene los datos necesarios
			Map m = ( (Map) jo.get("main") );
			
			for( Map.Entry pair : (Set<Map.Entry>) m.entrySet()){

				//Ejemplo de respuesta
				/*
				temp : 294.43
				temp_min : 294.14
				humidity : 26
				pressure : 1023
				feels_like : 293.29
				temp_max : 296.72
				 */

				/*System.out.println(
					pair.getKey() + " : " + pair.getValue()		
				);*/

				String k = String.valueOf( pair.getKey() );
				String v = String.valueOf( pair.getValue() );

				if( k.equals("temp") )
					this.temperatura = v;
				else if (  k.equals("temp") )
					this.presionA = v;
				else if (  k.equals("humidity") )
					this.humedad = v;
				else if (  k.equals("pressure") )
					this.presionA = v;
			}
			
			//El rubo para el clima y la desc
			JSONArray ja =  (JSONArray) jo.get("weather");
			m = (Map) ja.iterator().next();
			//Map b = ( ( Map[] ) jo.get("weather") )[0];
			for( Map.Entry pair : (Set<Map.Entry>) m.entrySet()){

				//Ejemplo de respuesta
				/*
				"weather": [
				    {
				          "icon": "03d",
				          "description": "scattered clouds",
				          "main": "Clouds",
				          "id": 802
      			          }
				],
				*/
				
				/*System.out.println(
					pair.getKey() + " : " + pair.getValue()	
				);*/
				
				String k = String.valueOf( pair.getKey() );
				String v = String.valueOf( pair.getValue() );

				if( k.equals("description") )
					this.descClima = v;
				else if (  k.equals("main") )
					this.tipoclima = v;
	
			}

		}catch( ParseException e){
			System.out.println("Parse Error");
		}
	}

  	/**
  	 * Metodo set que establece un valor cuidad de tipo String a Clima
  	 */
  	public void setCuidad(String ciudad){
  		this.ciudad=ciudad;
  	}
  	/**
  	 * Metodo set que establece un valor temperatura de tipo double a Clima
  	 */
  	public void setTemperatura(String temperatura){
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
  	public void setLatitud( String latitud){
  		this.latitud=latitud;
  	}
  	/**
  	 * Metodo set que establece un valor longitud de tipo String a Clima
  	 */
  	public void setLongitud( String longitud){
  		this.longitud=longitud;
  	}
  	/**
  	 * Metodo set que establece un valor presionA de tipo String a Clima
  	 */
  	public void setPresionA( String presionA){
  		this.presionA=presionA;
  	}
  	/**
  	 * Metodo set que establece un valor humedad de tipo String a Clima
  	 */
  	public void setHumedad( String humedad){
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
  	public String getTemperatura(){
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
  	public String getLatitud(){
  		return latitud;
  	}
  	/**
  	 * Metodo get que devuelve un valor de tipo double 
  	 * @return devuelve la longitud del lugar en el que se encuentre
  	 */
  	public String getLongitud(){
  		return longitud;
  	}
  	/**
  	 * Metodo get que devuelve un valor de tipo double 
  	 * @return devuelve la presion atmosferica del lugar en el que se encuentre
  	 */
  	public String getPresionA(){
  		return presionA;
  	}
  	/**
  	 * Metodo get que devuelve un valor de tipo int 
  	 * @return devuelve la humedad del lugar en el que se encuentre
  	 */
  	public String getHumedad(){
  		return humedad;
  	}
	  /**
  	 * Metodo get que devuelve String con una mejor explicación del clima
  	 * @return devuelve la humedad del lugar en el que se encuentre
  	 */
  	public String getDescripcionDelClima(){
		return descClima;
	}

	@Override
	public String toString() {
		String s = new String(
			"Lugar: "+ this.ciudad+"\n"+
			"Clima: "+ this.tipoclima+"\n"+
			"descClima: "+ this.descClima+"\n"+
			"Presión: "+ this.presionA+"\n"+
			"Humedad: "+ this.humedad
		);
		return s;
	}

  }
