package mx.unam.ciencias.modelado.proyecto1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.HashMap;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class EscritorLectorCache {
    
    private static final String DIR_DATE = "src/main/resources/cache/date.txt";
    private static final String DIR_CLIMAS = "src/main/resources/cache/climas.txt";
    private static final String DIR_VUELOS = "src/main/resources/cache/vuelos.txt";
    private static final String DIR_LUGARES = "src/main/resources/cache/lugares.txt";


    private EscritorLectorCache(){}

    public static void escribirFechaEjecucion(Date d){

        FileOutputStream salida = null;
        ObjectOutputStream escitor = null;

        try{

            File f = new File(DIR_DATE.replaceAll("/", "//"));
            

            salida = new FileOutputStream(f,false); //False para reescribir archivo

            escitor = new ObjectOutputStream(salida);
            escitor.writeObject(d);

            escitor.close();
            salida.close();

        }catch(FileNotFoundException e){
            System.out.println("No se encontró: "+DIR_DATE);
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Error en OutputStream");
        }

    }

    public static void escribirClimas(HashMap<String,Clima> climas){

        FileOutputStream salida = null;
        ObjectOutputStream escitor = null;

        try{

            File f = new File(DIR_CLIMAS.replaceAll("/", "//"));

            salida = new FileOutputStream(f,false); //False para reescribir archivo

            escitor = new ObjectOutputStream(salida);
            escitor.writeObject(climas);

            escitor.close();
            salida.close();

        }catch(FileNotFoundException e){
            System.out.println("No se encontró: "+DIR_CLIMAS);
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Error en OutputStream");
        }

    }

    public static void escribirVuelos(HashMap<String,Vuelo> vuelos){

        FileOutputStream salida = null;
        ObjectOutputStream escitor = null;

        try{
            
            File f = new File(DIR_VUELOS.replaceAll("/", "//"));

            salida = new FileOutputStream(f,false); //False para reescribir archivo

            escitor = new ObjectOutputStream(salida);
            escitor.writeObject(vuelos);

            escitor.close();
            salida.close();


        }catch(FileNotFoundException e){
            System.out.println("No se encontró: "+DIR_VUELOS);
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Error en OutputStream");
        }

    }

    public static void escribirLugares(HashMap<String,Lugar> lugares){

        FileOutputStream salida = null;
        ObjectOutputStream escitor = null;

        try{

            File f = new File(DIR_LUGARES.replaceAll("/", "//"));

            salida = new FileOutputStream(f,false); //False para reescribir archivo

            escitor = new ObjectOutputStream(salida);
            escitor.writeObject(lugares);

            escitor.close();
            salida.close();

        }catch(FileNotFoundException e){
            System.out.println("No se encontró: "+DIR_LUGARES);
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Error en OutputStream");
        }

    }

    public static HashMap<String, Clima> leerCacheClima(){

        FileInputStream entrada = null;
        ObjectInputStream  lector = null;

        HashMap<String, Clima> diccionario = null;
        
        try{

            File f = new File(DIR_CLIMAS.replaceAll("/", "//"));
            entrada = new FileInputStream(f);
            lector = new ObjectInputStream(entrada);
            diccionario = (HashMap<String,Clima>) lector.readObject();

            lector.close();
            entrada.close();

        }catch(ClassNotFoundException e){
            System.out.println("Clase no encontrada");
        }catch(FileNotFoundException e){
            System.out.println("No se encontró: "+DIR_CLIMAS);
        }catch(IOException e){
            System.out.print("Error al leer el archivo"+ DIR_CLIMAS);
        }

        return diccionario;
    }

    public static HashMap<String, Vuelo> leerCacheVuelos(){

        FileInputStream entrada = null;
        ObjectInputStream  lector = null;

        HashMap<String, Vuelo> diccionario = null;
        
        try{

            File f = new File(DIR_VUELOS.replaceAll("/", "//"));
            entrada = new FileInputStream(f);
            lector = new ObjectInputStream(entrada);
            diccionario = (HashMap<String,Vuelo>) lector.readObject();

            lector.close();
            entrada.close();

        }catch(ClassNotFoundException e){
            System.out.println("Clase no encontrada");
        }catch(FileNotFoundException e){
            System.out.println("No se encontró: "+DIR_VUELOS);
        }catch(IOException e){
            System.out.print("Error al leer el archivo"+ DIR_VUELOS);
        }

        return diccionario;
    }

    public static HashMap<String, Lugar> leerCacheLugares(){

        FileInputStream entrada = null;
        ObjectInputStream  lector = null;

        HashMap<String,Lugar> diccionario = null;
        
        try{

            File f = new File(DIR_LUGARES.replaceAll("/", "//"));
            entrada = new FileInputStream(f);
            lector = new ObjectInputStream(entrada);
            diccionario = (HashMap<String,Lugar>) lector.readObject();

            lector.close();
            entrada.close();

        }catch(ClassNotFoundException e){
            System.out.println("Clase no encontrada");
        }catch(FileNotFoundException e){
            System.out.println("No se encontró: "+DIR_LUGARES);
        }catch(IOException e){
            System.out.print("Error al leer el archivo"+ DIR_LUGARES);
        }

        return diccionario;
    }


    public static Date leerCacheHoraEjecucion(){

        FileInputStream entrada = null;
        ObjectInputStream  lector = null;

        Date hora = null;
        
        try{

            File f = new File(DIR_DATE.replaceAll("/", "//"));
            entrada = new FileInputStream(f);
            lector = new ObjectInputStream(entrada);
            hora = (Date) lector.readObject();

            lector.close();
            entrada.close();

        }catch(ClassNotFoundException e){
            System.out.println("Clase no encontrada");
        }catch(FileNotFoundException e){
            System.out.println("No se encontró: "+DIR_DATE);
        }catch(IOException e){
            System.out.print("Error al leer el archivo"+ DIR_DATE);
        }

        return hora;
    }



}
