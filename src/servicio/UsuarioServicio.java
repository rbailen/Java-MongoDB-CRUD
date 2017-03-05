package servicio;

import com.mongodb.client.MongoCursor;
import dao.UsuarioDAO;
import modelo.Usuario;
import org.bson.Document;

public class UsuarioServicio {
    private final UsuarioDAO usuarioDAO;
    
    public UsuarioServicio(){
        usuarioDAO = new UsuarioDAO();
    }
    
    public MongoCursor<Document> documents(){
        return usuarioDAO.documents();
    }
    
    public void insertar(Document nuevo){ 
        usuarioDAO.insertar(nuevo);
    }
    
    public MongoCursor<Document> buscar(String id){
        return usuarioDAO.buscar(id);
    }
    
    public Usuario buscarObject(String id){
        return usuarioDAO.buscarObject(id);
    }
    
    public void actualizar(String id, Document nuevo){
        usuarioDAO.actualizar(id, nuevo);
    }
    
    public void eliminar(String id){
        usuarioDAO.eliminar(id);
    }
    
    public long total(){
        return usuarioDAO.total();
    }
}
