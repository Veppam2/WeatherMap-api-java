package mx.unam.ciencias.modelado.proyecto1;

/*
import java.util.Map;
import java.util.Set;

*/

import io.github.cdimascio.dotenv.Dotenv;


import com.mashape.unirest.http.HttpResponse; //maneja la petición http
import com.mashape.unirest.http.JsonNode; //Que sea de tipo json
import com.mashape.unirest.http.Unirest; //Mandar la petición

//Tratar el json
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/*
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
*/

//Excepciones
import com.mashape.unirest.http.exceptions.UnirestException;

public class PeticionWM{
	
	private static String host = "http://api.openweathermap.org/data/2.5/weather";

	private PeticionWM(){
		
	}

	private static String getWM_ID(){

		Dotenv dotenv = null;
		dotenv = Dotenv.configure().load();

		return dotenv.get("WM_ID");

	}

	public static String pedirDatos(String latitude, String longitude){
		
		String ptyJsonSt = "";
		//HACER PETICION
		try{
			HttpResponse <JsonNode> response = Unirest.get( host )
				.queryString("lat", latitude)
				.queryString("lon", longitude)
				.queryString("appid", getWM_ID())
				.asJson();
			/*
			System.out.println( response.getStatus() );
			System.out.println( response.getHeaders().get("Content-Type") );
			*/

			//Recibir el json
			Gson gson  = new GsonBuilder().setPrettyPrinting().create();
			JsonParser jp = new JsonParser();
			JsonElement je = jp.parse( response.getBody().toString() );
			ptyJsonSt = gson.toJson(je);
			
			//System.out.println(ptyJsonSt);
			
			/*
			Object obj = new JSONParser().parse(ptyJsonSt);
			JSONObject jo = (JSONObject) obj;

			//ejemplo main jsonwm
			Map m = ( (Map) jo.get("main") );
			for( Map.Entry pair : (Set<Map.Entry>) m.entrySet()){
				System.out.println(
					pair.getKey() + " : " + pair.getValue()	
				);
			}
			*/
	

		}catch(UnirestException ee){
			System.out.println("Unirest problem");
		}
	
		return ptyJsonSt;

	}
	/*

	public static void hacerPeticion(){


		String o_Latitude = "19.331";
		String o_Longitude = "-99.566";

		//HACER PETICION
		try{
			HttpResponse <JsonNode> response = Unirest.get( host )
				.queryString("lat", o_Latitude)
				.queryString("lon", o_Longitude)
				.queryString("appid", APP_ID)
				.asJson();

			System.out.println(response.getStatus() );
			System.out.println(response.getHeaders().get("Content-Type") );

			//Recibir el json
			Gson gson  = new GsonBuilder().setPrettyPrinting().create();
			JsonParser jp = new JsonParser();
			JsonElement je = jp.parse( response.getBody().toString() );
			String ptyJsonSt = gson.toJson(je);
			
			System.out.println(ptyJsonSt);
			
			Object obj = new JSONParser().parse(ptyJsonSt);
			JSONObject jo = (JSONObject) obj;

			//ejemplo main jsonwm
			Map m = ( (Map) jo.get("main") );
			for( Map.Entry pair : (Set<Map.Entry>) m.entrySet()){
				System.out.println(
					pair.getKey() + " : " + pair.getValue()		
				);	
			}

		}catch(ParseException e ){
			System.out.println("Parse problem");
		}catch(UnirestException ee){
			System.out.println("Unirest problem");
		}
		
	}

	public static void hacerPeticion(String lat, String lon ){

		String origin = "TLC";
		String destination = "MTY";

		String o_Latitude = "19.331";
		String o_Longitude = "-99.566";

		String d_Latitude = "25.7785";
		String d_Longitude = "-100.107";


		//HACER PETICION
		try{
			HttpResponse <JsonNode> response = Unirest.get( host )
				.queryString("lat", o_Latitude)
				.queryString("lon", o_Longitude)
				.queryString("appid", APP_ID)
				.asJson();

			System.out.println(response.getStatus() );
			System.out.println(response.getHeaders().get("Content-Type") );

			//Recibir el json
			Gson gson  = new GsonBuilder().setPrettyPrinting().create();
			JsonParser jp = new JsonParser();
			JsonElement je = jp.parse( response.getBody().toString() );
			String ptyJsonSt = gson.toJson(je);
			System.out.println(ptyJsonSt);

		}catch(UnirestException ee){
			System.out.println("Unirest problem");
		}
	}

	*/
}
