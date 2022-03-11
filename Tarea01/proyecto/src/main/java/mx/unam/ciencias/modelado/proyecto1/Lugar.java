package mx.unam.ciencias.modelado.proyecto1;

import java.io.Serializable;

public class Lugar implements Serializable{
    
    private String id;
    private String longitud;
    private String latitud;

    public Lugar(String id, String lat, String lon){
        this.id = id;
        this.longitud = lon;
        this.latitud = lat;
    }

    public String getId(){
        return this.id;
    }
    public String getLatitud(){
        return this.latitud;
    }
    public String getLongitud(){
        return this.longitud;
    }
}
