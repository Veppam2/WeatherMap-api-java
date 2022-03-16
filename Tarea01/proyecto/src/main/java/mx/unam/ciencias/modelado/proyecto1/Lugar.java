package mx.unam.ciencias.modelado.proyecto1;

import java.io.Serializable;
/**
 * Lugar representa la información geografica de una cuidad 
 * @author Carlos Daniel Cortes Jimenez
 * @author Alexis de Jesús Arizmendi López
 * @author Víctor Emiliano Cruz Hérnandez
 * @version 2 15/03/2022
 */
public class Lugar implements Serializable{
    
    private String id;
    private String longitud;
    private String latitud;

    /**
     * Crea el estado incial de un objeto Lugar con ciertos parametros
     * @param id representa el codigo de la ciudad
     * @param lat representa la latitud de la ciudad
     * @param lon repsenta la longitud de la ciudad
     */
    public Lugar(String id, String lat, String lon){
        this.id = id;
        this.longitud = lon;
        this.latitud = lat;
    }
    /**
     * Devuelve el codigo de la ciudad solicitada
     * @return el codigo de esa ciudad
     */
    public String getId(){
        return this.id;
    }
    /**
     * Devuelve la latitud de la ciudad solicitada
     * @return la latitud de esa ciudad
     */
    public String getLatitud(){
        return this.latitud;
    }
    /**
     * Devuelve la longitud de la ciudad solicitada
     * @return la longitud de esa ciudad
     */
    public String getLongitud(){
        return this.longitud;
    }
}
