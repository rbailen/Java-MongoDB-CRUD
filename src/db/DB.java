package db;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import util.Properties;

public class DB {
    private MongoClient client;
    
    public MongoDatabase conectar(){
        MongoDatabase db = null;
                
        try{
            Properties propiedad = new util.Properties();
            
            String host = propiedad.getPropiedad("host");
            int port = Integer.parseInt(propiedad.getPropiedad("port"));
            String bd = propiedad.getPropiedad("bd");
             
            /* Conexión del servidor */
            client = new MongoClient(host, port);

            /* Conexión de la base de datos */
            db = client.getDatabase(bd);

        }catch(Exception e){
            System.out.println(e.getClass().getName() + ":" + e.getMessage());
        }
        
        return db;
    }
    
    public void desconectar(){
        client.close();
    }

    /**
     * @return the client
     */
    public MongoClient getClient() {
        return client;
    }
}
