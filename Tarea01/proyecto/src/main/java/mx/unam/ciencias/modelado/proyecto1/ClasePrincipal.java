package mx.unam.ciencias.modelado.proyecto1;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.util.Date;


import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class ClasePrincipal{

	//Ruta del archivo donde se van a leer los datos de entrada
	private static String rutaCSV = "src/main/resources/archivoBoletos/dataset1.csv";

	//Máximo número de peticiones según nuesta cuenta de WM
	private static int maximoNumeroPeticiones = 55;

	//Máximo tiempo de caché en milisegundos (5 horas)
	private static long maxTiempoCache = (	1000 * 60 * 60 * 5 );

	//La llave es el código IATA 
	private static Map<String, Lugar> diccionarioRegiones = new HashMap<String, Lugar>();

	//La llave es la concat de el IATA de origen y destino en ese orden.
	//private static Map<String, Vuelo> diccionarioVuelos = new HashMap<String, Vuelo>();

	private static LinkedList<Vuelo> listaVuelos = new LinkedList<Vuelo>();

	//La llave es el código IATA del lugar
	private static Map<String, Clima> diccionarioClimas = new HashMap<String, Clima>();


	private static void meterADiccionarios(String sOrigen, String sDestino,String so_lat, String so_lon, String sd_lat, String sd_lon){

		/**
		 * La forma de localiar una regón es por su código IMEI, de esta forma se buscarán en el diccionario, si ya está, se asignará esa región, caso contrario se creará una nueva región y se asignará un nuevo objeto clima ligado a el nuevo objeto lugar para hacer la petición de climas
		 * 
		 * Como los lugares son sin repeticiones, los climas tampoco se repiten
		 */
		
		Lugar origen = null;
		Lugar destino = null;

		//Checar existencia de origen
		if( diccionarioRegiones.containsKey(sOrigen) ){
			origen = diccionarioRegiones.get(sOrigen);
		}else{
			origen = new Lugar(sOrigen, so_lat, so_lon);
			diccionarioRegiones.put(sOrigen, origen);

			Clima clima = new Clima(sOrigen, so_lat, so_lon);
			diccionarioClimas.put(sOrigen, clima);
		}

		//Checar existencia de destino
		if( diccionarioRegiones.containsKey(sDestino) ){
			destino = diccionarioRegiones.get(sDestino);
		}else{
			destino = new Lugar(sDestino, sd_lat, sd_lon);
			diccionarioRegiones.put(sDestino, destino);

			Clima clima = new Clima(sDestino, sd_lat, sd_lon);
			diccionarioClimas.put(sDestino, clima);
		}

		//Siempre se agrega un vuelo
		int id = Vuelo.getNuevoIdDeVuelo();
		System.out.println("id :"+id);
		Vuelo vuelo = new Vuelo(
				origen, 
				destino, 
				id
			);
		/**
		 * En el diccionario de vuelos se generará colisión para cad ticket con mismo origen y destino, pero por el método equals de Vuelo, no se borrará el ticket
		 */
		listaVuelos.add(vuelo);


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
		for(Vuelo v:  listaVuelos ){

			

			String origen = v.getOrigen().getId();
			String destino = v.getDestino().getId();

			String temp_origen = diccionarioClimas.get(origen).getTemperatura();
			String temp_destino = diccionarioClimas.get(destino).getTemperatura();

			String clima_origen = diccionarioClimas.get(origen).getTipoClima();
			String clima_destino = diccionarioClimas.get(destino).getTipoClima();

			String descClima_origen = diccionarioClimas.get(origen).getDescripcionDelClima();
			String descClima_destino= diccionarioClimas.get(destino).getDescripcionDelClima();

			String presion_origen = diccionarioClimas.get(origen).getPresionA();
			String presion_destino = diccionarioClimas.get(destino).getPresionA();

			String humedad_origen = diccionarioClimas.get(origen).getHumedad();
			String humedad_destino = diccionarioClimas.get(destino).getHumedad();

			String dataO = String.format(
			"%-10s %-10s %-15s %-10s %-10s %-10s",
			origen, clima_origen, temp_origen, descClima_origen, presion_origen, humedad_origen
			);

			String dataD = String.format(
			"%-10s %-10s %-15s %-10s %-10s %-10s",
			destino, clima_destino, temp_destino, descClima_destino, presion_destino, humedad_destino
			);


			System.out.println("Vuelo de "+origen+" a "+destino+". Ticket: "+v.getId());
			System.out.println("->Origen: "+dataO+"\n->Destino: "+dataD+"\n");


		}
		//System.out.format("%-25s %9.7f%n", "K and Burstner", 0.055170);
	

	}


	public static void main( String[] args ){

		/**
		 * El usuario tiene nula interacción con el sistema, incluso desde la ejecución
		 * El sistema únicamente necesita ser ejecutado, ningún archivo o bandera se pasa como argumento.
		 * 
		 * Para esto suponemos que siempre se cuentra dentro de la carpeta src/main/resources/archivoBoletos/ un archivo llamado dataset1.csv el cual contiene toda la información necesaria para la ejecución del sistema.
		 * 
		 * El sistema primero llama a leer a caché el tiempo de la primera ejecución del día del sistema para ver que no hayan pasado más de 24 horas y tenga que leerse un nuevo csv 
		 * 
		 * Si ya pasaron 24 horas se lee el csv dentro de resources y se hacen las peticiones desde 0
		 * 
		 * Si no han pasado más de 24 horas se lee otro archivo con la fecha del último momento de ejecución del sistema para ver que no hayan más de 5 horas de diferencia y se tenga que volver a hacer la petición, si hay menos de 5 hras se lee en la caché la información de los climas
		 */

		Date ahora = new Date();
		Date primeraEjecucionDelDia = EscritorLectorCache.leerCacheHoraEjecucionDelDia();

		//Diferencia de un día para volver a leer los datos
		long tiempo24Horas = (
			1000 * 60 * 60 * 24
		);

		long diferencia = 0;
		if(primeraEjecucionDelDia != null){
			diferencia = ahora.getTime() - primeraEjecucionDelDia.getTime();
		}else
			diferencia =  tiempo24Horas *2; //Para que se cree desde 0 
		

		if(diferencia >= tiempo24Horas){
			
			System.out.println( "Lee los tickets de un nuevo día" );
			
			//Crear objeto vuelo y clima de origen y destino
			/**
			 * OBSERVACIONES: 
			 * 1) Para el vuelo sólo se va a tomar en cuenta el origen y destino pues lo demás no es indispensable para el programa, dos vuelos son iguales sí y sólo si tienen mismo origen y destino. Esto para no guardar vuelos de más
			 * 
			 * 2) Para el origen y destino sólo se buscará una vez para disminuir la cantidad de peticiones a WM. Esta petición se realiza de jalón pues el usuario tiene nula interacción con el sistema. Se buscan a todos por default. Una localidad son iguales sí y sólo si tienen mismo código IATA
			 */

			//Leer el csv
			CSVReader reader = null;
			
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

					/**
					 * Como se tienen que mostrar los datos de cada uno de los 3000 boletos, el diccionario [diccionarioVuelos] va a tener 3000 elementos de tipo Vuelo. Cada uno diferente por un id. 
					 * 
					 * Para agregarlo, entonces se va a agregar cada vuelo al diccionario de vuelos, pero sólo se va a agregar la región una vez al diccionario de Regiones, para que de esa forma, en el diccionario de climas no existan repeticiones y no hayan peticiones repeetidas. 
					 */
					System.out.println("Mete algo");

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


			//Mandar a caché la información

			//Objetos date para el manejo de cuándo hacer peticiones
			EscritorLectorCache.escribirUltimaFechaEjecucion(ahora);
			EscritorLectorCache.escribirFechaEjecucionDelDia(ahora);


			EscritorLectorCache.escribirClimas(
				(HashMap<String,Clima>) diccionarioClimas
			);
			EscritorLectorCache.escribirLugares(
				(HashMap<String,Lugar>) diccionarioRegiones
			);
			EscritorLectorCache.escribirVuelos(
				 listaVuelos
			);

			//Desplegar menú

			System.out.println("número de tickets: "+listaVuelos.size());
			System.out.println("número de climas: "+diccionarioClimas.size());
			System.out.println("número de regiones: "+diccionarioRegiones.size());

			imprimirMenu();

			

		}else{

			/*
				Observación:
				El algoritmo se ejecuta dirario, si dentro de las 24 horas que se ejecutó el algoritmo, se vuelve a ejecutar, se buscarán los vuelos correspondientes a la lista pasada dentro de esas 24 horas. Si se ejecuta después de ese intervalo, se busca de nuevo en el csv correspndiente (Caso anterior)
			*/

			/*
				Checar en caché si hace no más de 5 horas ya se hizo una búsqueda

				Si no se ha buscado nunca, marca error
				Si hay una búsqueda anterior 
					-> Si es de más o igual a 5 horas, hace nueva búsqueda
					-> Si es menor a 5 horas mostrar la info ya recopilada
			*/

			Date horaActual = new Date();
			Date horaAnterior = EscritorLectorCache.leerCacheUltimaHoraEjecucion();

			long diferencia2 = horaActual.getTime() - horaAnterior.getTime();

			listaVuelos = EscritorLectorCache.leerCacheVuelos();
			diccionarioRegiones = EscritorLectorCache.leerCacheLugares();
			diccionarioClimas = EscritorLectorCache.leerCacheClima();

			if(diferencia2 >= maxTiempoCache){
				System.out.println("más de "+maxTiempoCache+" nueva búsqueda");

				//Volver a pedir valores a la api
				llenarValoresDiccionarioClima();

				//Reescribir valores en caché de la fecha de ejecución y climas
				EscritorLectorCache.escribirUltimaFechaEjecucion(horaActual);
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

