package mx.unam.ciencias.modelado.proyecto1;

import java.io.Serializable;

/**
 * Clase que define un objeto Vuelo que tiene una ciudad origen, una ciudad destino, 
 * latitud y longitud de la ciudad origen, y latitud y longitud de la ciudad origen
 * @author Carlos Daniel Cortes Jimenez
 * @author Alexis de Jesús Arizmendi López
 * @author Víctor Emiliano Cruz Hérnandez
 */
  public class Vuelo implements Serializable{


    /** Ciudad de origen del vuelo*/
  	private Lugar origen;
    /** Ciudad de destino del vuelo*/
  	private Lugar destino;
    /** Latitud de la ciudad de origen del vuelo*/



    /**
     * Define el estado inicial de un vuelo.
     * @param origen la ciudad de origen del vuelo.
     * @param destino la ciudad de destino del vuelo.
     */
    public Vuelo(Lugar o, Lugar d) {
        this.origen = o;
		this.destino = d;
    }

  	/**
     * Establece la ciudad de origen del vuelo.
     * @param origen el nombre de la ciudad de origen del vuelo.
     */
  	public void setOrigen(Lugar origen){
  		this.origen = origen;
  	}

    /**
     * Establece la ciudad de destino del vuelo.
     * @param destino el nombre de la ciudad de destino del vuelo.
     */
  	public void setDestino(Lugar destino){
  		this.destino = destino;
  	}
  	
    /**
  	 * Devuelve la ciudad de origen del vuelo.
  	 * @return la ciudad de origen del vuelo.
  	 */
  	public Lugar getOrigen(){
  		return origen;
  	}

  	/**
  	 * Devuelve la ciudad de destino del vuelo.
  	 * @return la ciudad de destino del vuelo.
  	 */
  	public Lugar getDestino(){
  		return destino;
  	}
  }
