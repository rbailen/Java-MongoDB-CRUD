package util;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Properties {
    private java.util.Properties propiedades = null ;
	
    public Properties(){
        try {
            propiedades = new java.util.Properties();
            propiedades.load(getClass().getResourceAsStream("configuracion.properties"));			
        } catch (IOException ex) {
            Logger.getLogger(Properties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getPropiedad(String valor){
        return propiedades.getProperty(valor);
    }
}
