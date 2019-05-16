package rmi;

import java.io.Serializable;

/**
 * Clase Image que almacena los objetos de las imágenes a ser procesadas.
 * @author Luis Bonilla
 */
public class Image implements Serializable {
    
    private String name;
    private String url;

    /**
     * Constructor de la clase Image.
     * @param name El nombre
     * @param url La URL
     */
    public Image(String name, String url) {
        this.name = name;
        this.url = url;
    }
    
    /**
     * 
     * @return El nombre
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name El nombre para asignar.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return La URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url La URL para asignar.
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
