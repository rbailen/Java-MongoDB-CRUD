package dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import db.DB;
import modelo.Direccion;
import modelo.Usuario;
import org.bson.Document;
import util.Properties;

public class UsuarioDAO {
    
    private final DB db;
    
    private final String tablaUsuarios;
    
    public UsuarioDAO(){
        db = new DB();
        
        Properties propiedad = new Properties();
        tablaUsuarios = propiedad.getPropiedad("tablaUsuarios");
    }
    
    public MongoCursor<Document> documents(){
        MongoDatabase md = db.conectar();
        
        MongoCollection<Document> collection = md.getCollection(tablaUsuarios);
        FindIterable<Document> it = collection.find();
        MongoCursor<Document> cursor = it.iterator();
        
        db.desconectar();
        
        return cursor;
    }
    
    public void insertar(Document nuevo){
        MongoDatabase md = db.conectar();
        
        MongoCollection<Document> collection = md.getCollection(tablaUsuarios);
        
        collection.insertOne(nuevo);
        
        db.desconectar();
    }
    
    public MongoCursor<Document> buscar (String id){
        MongoDatabase md = db.conectar();
        
        MongoCollection<Document> collection = md.getCollection(tablaUsuarios);
        
        BasicDBObject query = new BasicDBObject();
        query.put("id", id);
        
        FindIterable<Document> it = collection.find(query);
        MongoCursor<Document> cursor = it.iterator();
        
        db.desconectar();
        
        return cursor;
    }
    
    public Usuario buscarObject (String id){
        Usuario usuario = new Usuario();
        Direccion direccion = new Direccion();
        
        MongoDatabase md = db.conectar();
        
        MongoCollection<Document> collection = md.getCollection(tablaUsuarios);
        
        BasicDBObject query = new BasicDBObject();
        query.put("id", id);
        
        FindIterable<Document> it = collection.find(query);
        MongoCursor<Document> cursor = it.iterator();
      
        if(cursor.hasNext()){
            Document documentUsuario = cursor.next();
            usuario.setId(documentUsuario.getString("id"));
            usuario.setNombre(documentUsuario.getString("nombre"));
            
            Document documentDireccion = (Document) documentUsuario.get("direccion");
            direccion.setCalle(documentDireccion.getString("calle"));
            direccion.setCodigo_postal(documentDireccion.getString("codigo_postal"));
            
            usuario.setDireccion(direccion);
        }else{
            usuario = null;
        }
        
        db.desconectar();
        
        return usuario;
    }
    
    public void actualizar(String id, Document nuevo){
        MongoDatabase md = db.conectar();
        
        MongoCollection<Document> collection = md.getCollection(tablaUsuarios);
        
        collection.updateOne(new Document("id", id), new Document("$set", nuevo));
        
        db.desconectar();
    }
    
    public void eliminar(String id){
        MongoDatabase md = db.conectar();
        
        MongoCollection<Document> collection = md.getCollection(tablaUsuarios);
        
        collection.deleteOne(new Document("id", id));
        
        db.desconectar();
    }
    
    public long total(){
        long total = 0;
                
        MongoDatabase md = db.conectar();
        
        MongoCollection<Document> collection = md.getCollection(tablaUsuarios);
        
        total = collection.count();
        
        db.desconectar();
        
        return total;
    }
}
