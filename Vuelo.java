/**
 * Clase que define un objeto Vuelo que tiene una ciudad origen, una ciudad destino, 
 * latitud y longitud de la ciudad origen, y latitud y longitud de la ciudad origen
 * @author Carlos Daniel Cortes Jimenez
 * @author Alexis de Jesús Arizmendi López
 * @author Víctor Emiliano Cruz Hérnandez
 */
  public class Vuelo{

    /** Ciudad de origen del vuelo*/
  	private String origen;
    /** Ciudad de destino del vuelo*/
  	private String destino;
    /** Latitud de la ciudad de origen del vuelo*/
  	private double latitudO;
    /** Longitud de la ciudad de origen del vuelo*/
  	private double longitudO;
    /** Latitud de la ciudad de destino del vuelo*/
    private double latitudD;
    /** Longitud de la ciudad de destino del vuelo*/
  	private double longitudD;

    /**
     * Define el estado inicial de un vuelo.
     * @param origen la ciudad de origen del vuelo.
     * @param destino la ciudad de destino del vuelo.
     * @param latitudO la latitud de la ciudad de origen del vuelo.
     * @param longitudO la longitud de la ciudad de origen del vuelo.
     * @param latitudD la latitud de la ciudad de destino del vuelo.
     * @param longitudD la longitud de la ciudad de destino del vuelo.
     */
    public Estudiante(String origen,
                      String destino,
                      double latitudO,
                      double longitudO,
                      double latitudD,
                      double longitudD) {
        this.origen    = origen;
        this.destino   = destino;
        this.latitudO  = latitudO;
        this.longitudO = longitudO;
        this.latitudD  = latitudD;
        this.longitudD = longitudD;
    }

  	/**
     * Establece la ciudad de origen del vuelo.
     * @param origen el nombre de la ciudad de origen del vuelo.
     */
  	public void setOrigen(String origen){
  		this.origen = origen;
  	}

    /**
     * Establece la ciudad de destino del vuelo.
     * @param destino el nombre de la ciudad de destino del vuelo.
     */
  	public void setDestino(String destino){
  		this.destino = destino;
  	}

  	/**
     * Establece la latitud de la ciudad de origen del vuelo.
     * @param latitudO la latitud en grados de la ciudad de origen del vuelo.
     */
  	public void setLatitudO(double latitudO){
  		this.latitudO = latitudO;
  	}

  	/**
     * Establece la longitud de la ciudad de origen del vuelo.
     * @param longitudO la longitud en grados de la ciudad de origen del vuelo.
     */
  	public void setLongitudO(double longitudO){
  		this.longitudO = longitudO;
  	}

    /**
     * Establece la latitud de la ciudad de destino del vuelo.
     * @param latitudD la latitud en grados de la ciudad de destino del vuelo.
     */
  	public void setLatitudO(double latitudD){
  		this.latitudD = latitudD;
  	}

  	/**
     * Establece la longitud de la ciudad de destino del vuelo.
     * @param longitudD la longitud en grados de la ciudad de destino del vuelo.
     */
  	public void setLongitudD(double longitudD){
  		this.longitudD = longitudD;
  	}
  	
    /**
  	 * Devuelve la ciudad de origen del vuelo.
  	 * @return la ciudad de origen del vuelo.
  	 */
  	public String getOrigen(){
  		return origen;
  	}

  	/**
  	 * Devuelve la ciudad de destino del vuelo.
  	 * @return la ciudad de destino del vuelo.
  	 */
  	public String getDestino(){
  		return destino;
  	}

  	/**
  	 * Devuelve la latitud de la ciudad de destino del vuelo.
  	 * @return la latitud de la ciudad de destino del vuelo.
  	 */
  	public double getLatitudO(){
  		return latitudO;
  	}

  	/**
  	 * Devuelve la longitud de la ciudad de destino del vuelo.
  	 * @return la longitud de la ciudad de destino del vuelo.
  	 */
  	public double getLongitudO(){
  		return longitudO;
  	}

    /**
  	 * Devuelve la latitud de la ciudad de origen del vuelo.
  	 * @return la latitud de la ciudad de origen del vuelo.
  	 */
  	public double getLatitudO(){
  		return latitudO;
  	}

    /**
  	 * Devuelve la longitud de la ciudad de origen del vuelo.
  	 * @return la longitud de la ciudad de origen del vuelo.
  	 */
  	public double getLongitudO(){
  		return longitudO;
  	}
  }