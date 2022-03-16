package mx.unam.ciencias.modelado.proyecto1;

import java.io.Serializable;

/**
 * Clase que define un objeto Vuelo que tiene una ciudad origen, una ciudad destino, 
 * latitud y longitud de la ciudad origen, y latitud y longitud de la ciudad origen
 * @author Carlos Daniel Cortes Jimenez
 * @author Alexis de Jesús Arizmendi López
 * @author Víctor Emiliano Cruz Hérnandez
 * @version 2 15/03/2022
 */
  public class Vuelo implements Serializable{

	private static int numeroDeVuelos;

    /** Ciudad de origen del vuelo*/
  	private Lugar origen;
    /** Ciudad de destino del vuelo*/
  	private Lugar destino;
    /** Id del vuelo*/
	private int id;



    /**
     * Define el estado inicial de un vuelo.
     * @param origen la ciudad de origen del vuelo.
     * @param destino la ciudad de destino del vuelo.
     */
    public Vuelo(Lugar o, Lugar d, int id) {

        this.origen = o;
		this.destino = d;
		this.id = id;
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
    /**
     * Devuelve el id del vuelo
     * @return el id de vuelo
     */
	public int getId(){
		return this.id;
	}
    /**
     * Devuelve un nuevo id de vuelo 
     * @return el número de vueloa nuevos
     */
	public static int getNuevoIdDeVuelo(){
		return numeroDeVuelos++;
	}

	@Override
	public boolean equals(Object objeto){
		if (objeto == null || getClass() != objeto.getClass())
            return false;

    	Vuelo recibido = (Vuelo)objeto;

		return (this.id == recibido.getId());
        
	}
  }
