package principal;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCursor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import modelo.Usuario;
import org.bson.Document;
import servicio.TestServicio;
import servicio.UsuarioServicio;

public class main {
    
    public static void menu(){
        System.out.println("1.- Listar bases de datos");
        System.out.println("2.- Mostrar información almacenada en formato JSON");
        System.out.println("3.- Insertar");
        System.out.println("4.- Buscar por ID devolviendo el resultado en formato JSON");
        System.out.println("5.- Buscar por ID devolviendo un objeto de tipo Usuario");
        System.out.println("6.- Actualizar por ID");
        System.out.println("7.- Eliminar por ID");
        System.out.println("8.- Mostrar número de usuarios almacenados");
        System.out.println("9.- Salir");
    }
    
    public static void main(String[] args) throws IOException {
        
        int opcion = 0;
        String id = "";
        String nombre = "";
        String calle = "";
        String codigo_postal = "";
        
        TestServicio testServicio = new TestServicio();
        UsuarioServicio usuarioServicio = new UsuarioServicio();

        do{
            menu();
            
            Scanner leer = new Scanner(System.in); 
            BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Introduzca una opción: ");
            opcion = leer.nextInt();
        
            switch(opcion){
                case 1: {
                    List<String> databases = testServicio.databases();
                    
                    for(String databaseName : databases){
                        System.out.println("Base de Datos: " + databaseName);
                    }
                    
                    break;
                }
                
                case 2: {
                    MongoCursor<Document> cursor = usuarioServicio.documents();
                   
                    while(cursor.hasNext()){
                        System.out.println(cursor.next());
                    }
                    
                    break;
                }
                
                case 3:{
                    System.out.println("ID: ");
                    id = leer.next();
                    
                    System.out.println("Nombre: ");
                    nombre = entrada.readLine();
                    
                    System.out.println("Calle: ");
                    calle = entrada.readLine();
                    
                    System.out.println("Codigo postal: ");
                    codigo_postal = entrada.readLine();
                    
                    Document nuevo = new Document();
                    nuevo.put("id", id);
                    nuevo.put("nombre", nombre);

                    BasicDBObject addressDocument = new BasicDBObject();
                    addressDocument.put("calle", calle);
                    addressDocument.put("codigo_postal", codigo_postal);

                    nuevo.put("direccion", addressDocument);
                    
                    usuarioServicio.insertar(nuevo);
                    
                    break;
                }
                
                case 4:{
                    System.out.println("ID: ");
                    id = leer.next();
                            
                    MongoCursor<Document> cursor = usuarioServicio.buscar(id);
                    
                    while(cursor.hasNext()){
                        System.out.println(cursor.next());
                    }
                    
                    break;
                }
                
                case 5:{
                    System.out.println("ID: ");
                    id = leer.next();
                            
                    Usuario usuario = usuarioServicio.buscarObject(id);
                    
                    if(usuario != null){
                        System.out.println("ID: " + usuario.getId());
                        System.out.println("Nombre: " + usuario.getNombre());
                        System.out.println("Calle: " + usuario.getDireccion().getCalle());
                        System.out.println("Codigo Postal: " + usuario.getDireccion().getCodigo_postal());
                    }else{
                        System.out.println("No existe un usuario con ese ID");
                    }

                    break;
                }
                
                case 6:{
                    System.out.println("ID: ");
                    id = leer.next();
                    
                    System.out.println("Nombre: ");
                    nombre = entrada.readLine();
                    
                    System.out.println("Calle: ");
                    calle = entrada.readLine();
                    
                    System.out.println("Codigo Postal: ");
                    codigo_postal = entrada.readLine();
                    
                    Document nuevo = new Document();
                    nuevo.put("id", id);
                    nuevo.put("nombre", nombre);

                    BasicDBObject addressDocument = new BasicDBObject();
                    addressDocument.put("calle", calle);
                    addressDocument.put("codigo_postal", codigo_postal);

                    nuevo.put("direccion", addressDocument);
                    
                    usuarioServicio.actualizar(id, nuevo);
                    
                    break;
                }
                
                case 7:{
                    System.out.println("ID: ");
                    id = leer.next();
                    
                    usuarioServicio.eliminar(id);
                            
                    break;
                }
                
                case 8:{
                    long total = usuarioServicio.total();
                    
                    System.out.println(total);
                    break;
                }
            }  
        }while(opcion != 9);
    }
}
