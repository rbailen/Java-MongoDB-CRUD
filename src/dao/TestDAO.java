package dao;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import db.DB;
import java.util.ArrayList;
import java.util.List;

public class TestDAO {
    
    private final DB db;
    
    public TestDAO(){
        db = new DB();
    }
    
    public List<String> databases(){
        MongoDatabase md = db.conectar();
        
        MongoIterable<String> it = db.getClient().listDatabaseNames();
        
        List<String> databases = new ArrayList<>();
        
        for(String databaseName: it){
            databases.add(databaseName);
        }
        
        db.desconectar();
        
        return databases;
    }
}
