package mx.unam.ciencias.modelado.proyecto1;

import java.util.Map;
import java.util.HashMap;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.util.Date;


import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class clasePrincipal{

	//Máximo número de peticiones según nuesta cuenta de WM
	private static int maximoNumeroPeticiones = 55;

	//Máximo tiempo de caché en milisegundos
	private static long maxTiempoCache = (	1000 * 30 );

	//La llave es el código IATA 
	private static Map<String, Lugar> diccionarioRegiones = new HashMap<String, Lugar>();

	//La llave es la concat de el IATA de origen y destino en ese orden.
	private static Map<String, Vuelo> diccionarioVuelos = new HashMap<String, Vuelo>();

	//La llave es el código IATA del lugar
	private static Map<String, Clima> diccionarioClimas = new HashMap<String, Clima>();


	private static void meterADiccionarios(String sOrigen, String sDestino,String so_lat, String so_lon, String sd_lat, String sd_lon){

		//Checar que no se tenga un vuelo con mismo origen y destino
		if( ! diccionarioVuelos.containsKey(sOrigen+sDestino) ){

			Lugar origen = null;
			Lugar destino = null;

			if(diccionarioRegiones.containsKey(sOrigen)){
				origen = diccionarioRegiones.get(sOrigen);
			}else{
				origen = new Lugar(sOrigen, so_lat, so_lon);
				diccionarioRegiones.put(sOrigen, origen);

				Clima clima = new Clima(sOrigen, so_lat, so_lon);
				diccionarioClimas.put(sOrigen, clima);

			}

			if(diccionarioRegiones.containsKey(sDestino)){
				destino = diccionarioRegiones.get(sDestino);
			}else{
				destino = new Lugar(sOrigen, sd_lat, sd_lon);
				diccionarioRegiones.put(sDestino, destino);

				Clima clima = new Clima(sDestino, sd_lat, sd_lon);
				diccionarioClimas.put(sDestino, clima);
			}

			Vuelo vuelo = new Vuelo(origen, destino);
			diccionarioVuelos.put( (sOrigen+sDestino) , vuelo );
		
		}

	}

	private static void llenarValoresDiccionarioClima(){

		System.out.println(diccionarioClimas.size());
			
		System.out.println("Cargando peticiones...");
		
		int contador = 0;
		for(Map.Entry<String,Clima> v:  diccionarioClimas.entrySet() ){
			try{
				if(contador>0 && contador%maximoNumeroPeticiones==0 ){
					
					System.out.println("Hay más de "+maximoNumeroPeticiones+" peticiones, hay que esperar 1 min entre ellas.");
					
					System.out.println("Esperando para un bloque");
					Thread.sleep(1000*60);
				}
				
				v.getValue().llenarAtributos();
				contador++;
			}catch(InterruptedException e){
				System.out.println("ERROR en Esperar peticiones");
			}
		}
		System.out.println("Terminó");

	}

	private static void imprimirMenu(){
		System.out.println("*****************VUELOS************");

		/*System.out.println("DATOS DE LOS CLIMAS: ");
		for(Map.Entry<String,Clima> v:  diccionarioClimas.entrySet() ){
			System.out.println(v.getValue().toString() +"\n");
		}*/

		/*System.out.format(
			"%-10s %-10s %-10s %-10s %-15s %-10s %-10s",
			"Origen", "Destino", "Temperatura","Clima", "Descripcion", "Presión", "Humedad"
			);
		*/
		for(Map.Entry<String,Vuelo> v:  diccionarioVuelos.entrySet() ){

			

			String o = v.getValue().getOrigen().getId();
			String d = v.getValue().getDestino().getId();

			String t_o = diccionarioClimas.get(o).getTemperatura();
			String t_d = diccionarioClimas.get(d).getTemperatura();

			String c_o = diccionarioClimas.get(o).getTipoClima();
			String c_d = diccionarioClimas.get(d).getTipoClima();

			String dc_o = diccionarioClimas.get(o).getDescripcionDelClima();
			String dc_d = diccionarioClimas.get(d).getDescripcionDelClima();

			String p_o = diccionarioClimas.get(o).getPresionA();
			String p_d = diccionarioClimas.get(d).getPresionA();

			String h_o = diccionarioClimas.get(o).getHumedad();
			String h_d = diccionarioClimas.get(d).getHumedad();

			String dataO = String.format(
			"%-10s %-10s %-15s %-10s %-10s %-10s",
			o,  	c_o, t_o, 	dc_o, 	p_o, 	h_o
			);

			String dataD = String.format(
			"%-10s %-10s %-15s %-10s %-10s %-10s",
			d,  	c_d, t_d, 	dc_d, 	p_d, 	h_d
			);


			System.out.println("Vuelo de "+o+" a "+d);
			System.out.println("->Origen: "+dataO+"\n->Destino: "+dataD+"\n");


		}
		//System.out.format("%-25s %9.7f%n", "K and Burstner", 0.055170);
	

	}


	public static void main( String[] args ){

		if(args.length >0){
			
			System.out.println( "Crea desde csv nuevo" );
			
			//Crear objeto vuelo y clima de origen y destino
			/**
			 * OBSERVACIONES: 
			 * 1) Para el vuelo sólo se va a tomar en cuenta el origen y destino pues lo demás no es indispensable para el programa, dos vuelos son iguales sí y sólo si tienen mismo origen y destino. Esto para no guardar vuelos de más
			 * 
			 * 2) Para el origen y destino sólo se buscará una vez para disminuir la cantidad de peticiones a WM. Esta petición se realiza de jalón pues el usuario tiene nula interacción con el sistema. Se buscan a todos por default. Una localidad son iguales sí y sólo si tienen mismo código IATA
			 */

			//Tiempo de ejecución del algoritmo
			Date fechaEjecucion = new Date();

			//Leer el csv
			CSVReader reader = null;
			String rutaCSV = args[0];
			
			try{
				reader = new CSVReader( new FileReader(rutaCSV.replaceAll("/","//")) );
				
				//Evitarse leer cabecera del svg
				String[] linea = reader.readNext();


				while( (linea = reader.readNext()) != null ){
					/**
					 * Crear Lugares con IMEI, lat y lon 
					 * Asignar Origen y destino a cada Vuelo
					 */
					/**
					 * El csv va en formato
					 * origin | destination | origin_latitude | origin_longitude | destination_latitude | destination longitude
					 * en ese orden los vamos a asignar
					 */
					String sOrigen  = linea[0];
					String sDestino  = linea[1];
					String so_lat  = linea[2];
					String so_lon  = linea[3];
					String sd_lat  = linea[4];
					String sd_lon  = linea[5];

					meterADiccionarios(sOrigen, sDestino, so_lat, so_lon, sd_lat, sd_lon);

				}
			}catch(FileNotFoundException e){
				System.out.println("Error al leer CSV");
			}catch(IOException ee){
				System.out.println("IOException");
			}catch(CsvValidationException eee){
				System.out.println("CsvValidationExveption");
			}

			//Pedir el clima a WM para cada región
			/**
			 * OBSERVACION: 
			 * El plan FREE de Weather Map ofrece 60 peticiones por minuto. Hay que tener cuidado de no superar esa marca
			 * 
			 * La caché se guardará como un objeto time con el que se comparará una diferencia de 5 horas para volver a ejecutar las peticiones
			 * La chacé se guardará como objetos serializables del m
			 */

			llenarValoresDiccionarioClima();


			/*
			for(Map.Entry<String,Clima> v:  diccionarioClimas.entrySet() ){
				System.out.println(v.getKey() );
			}
			System.out.println("\n----------------asdasda-----");


			for(Map.Entry<String,Lugar> v:  diccionarioRegiones.entrySet() ){
				System.out.println(v.getKey() );
			}
			System.out.println("\n----------------asdasda-----");

			for(Map.Entry<String,Vuelo> v:  diccionarioVuelos.entrySet() ){
				System.out.println(v.getKey() );
			}
			*/

			//Mandar a caché la información

			EscritorLectorCache.escribirFechaEjecucion(fechaEjecucion);
			EscritorLectorCache.escribirClimas(
				(HashMap<String,Clima>) diccionarioClimas
			);
			EscritorLectorCache.escribirLugares(
				(HashMap<String,Lugar>) diccionarioRegiones
			);
			EscritorLectorCache.escribirVuelos(
				(HashMap<String,Vuelo>) diccionarioVuelos
			);

			//Desplegar menú

			imprimirMenu();
			

		}else{
			/*
				Checar en caché si hace no más de 5 horas ya se hizo una búsqueda

				Si no se ha buscado nunca, marca error
				Si hay una búsqueda anterior 
					-> Si es de más o igual a 5 horas, hace nueva búsqueda
					-> Si es menor a 5 horas mostrar la info ya recopilada
			*/

			/*
				Observación:
				El algoritmo se ejecuta dirario, si dentro de las 24 horas que se ejecutó el algoritmo, se vuelve a ejecutar, se buscarán los vuelos correspondientes a la lista pasada dentro de esas 24 horas. Si se ejecuta después de ese intervalo, se mostrará una advertencia y se buscarán los vuelos correspondientes a ese intervalo
			*/
			Date horaActual = new Date();
			Date horaAnterior = EscritorLectorCache.leerCacheHoraEjecucion();

			long diferencia = horaActual.getTime() - horaAnterior.getTime();

			diccionarioVuelos = EscritorLectorCache.leerCacheVuelos();
			diccionarioRegiones = EscritorLectorCache.leerCacheLugares();
			diccionarioClimas = EscritorLectorCache.leerCacheClima();

			if(diferencia >= maxTiempoCache){
				System.out.println("más de "+maxTiempoCache+" nueva búsqueda");

				/*
				diccionarioClimas = new HashMap<String, Clima>();

				//LLenar nueva
				for(Map.Entry<String,Lugar> v:  diccionarioRegiones.entrySet() ){

					String IMEIRegion = v.getValue().getId();

					Clima clima = new Clima(
							IMEIRegion, 
							v.getValue().getLatitud(), 
							v.getValue().getLongitud()
					);

					diccionarioClimas.put(IMEIRegion, clima);

				}
				*/
				//Volver a pedir valores a la api
				llenarValoresDiccionarioClima();

				//Reescribir valores en caché de la fecha de ejecución y climas
				EscritorLectorCache.escribirFechaEjecucion(horaActual);
				EscritorLectorCache.escribirClimas(
					(HashMap<String,Clima>) diccionarioClimas
				);



			}

			imprimirMenu();
			
		}/*else{
			PeticionWM.getWM_ID();
		}*/

	}
}

