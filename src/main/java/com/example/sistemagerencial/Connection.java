package com.example.sistemagerencial;


import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import org.bson.types.ObjectId;

public class Connection {
 public MongoClient mongoclient = null;
    public DB database;
    public DB clientsdb;
    public DBCursor cursor = null;
    public DBCollection Inventory;

    public DBCollection Client;
    public String server = "localhost";
    public Integer port = 27017;

    public BasicDBObject document;



 // Create connection to database
    public MongoClient CreateConnection(){

          try {
                mongoclient = new MongoClient(server,port);
                database = mongoclient.getDB("BiancaDB");
                System.out.println("Conexion exitosa");

                Client= database.getCollection("Clients");
                cursor = Client.find();

//                while (cursor.hasNext()){
//
//                    int i =1;
//                    System.out.println(cursor.next());
//                    i++;
//                }
                String id = cursor.next().get("_id").toString();
                System.out.println(id);


          }catch (MongoException e){
           System.out.println("Errro en la conexion: " + e);
          }
            return mongoclient;
  }

    public DB getDatabase() {
        return database;
    }

    public void setDatabase(DB database) {
        this.database = database;
    }

    // Close connection to dababase
    public MongoClient Close(){
    mongoclient.close();
    return mongoclient;
  }

  // Insert new product

    public void insertNewProduct(String nombre, Integer price,Integer store, Integer stock){
         CreateConnection();

        try {
            Inventory = database.getCollection("Inventario");
            document = new BasicDBObject();
            document.put("Nombre", nombre);
            document.put("Precio",price);
            document.put("Bodega",store);
            document.put("Existencias",stock);
            Inventory.insert(document);

            System.out.println("Producto agregado");


        }catch (MongoException e){
            System.out.println("Error al insertar"+e);

        }
        Close();
  }

    public void updateProduct(String id,String name, Integer price,Integer store, Integer stock){
        CreateConnection();
        try{
            BasicDBObject query = new BasicDBObject();
            query.put("_id", new ObjectId(id));

            BasicDBObject update = new BasicDBObject();
            update.put("Nombre", name);
            update.put("Precio", price);
            update.put("Bodega", store);
            update.put("Existencias", stock);

            BasicDBObject updateObject = new BasicDBObject();
            updateObject.put("$set", update);

            Inventory = database.getCollection("Inventario");

            Inventory.update(query, updateObject);
            System.out.println("Producto actualizado exitosamente en MongoDB.");

        }catch (MongoException e){
            System.out.println("Error al hacer update " +e );
        }

        Close();
    }

    public void deleteProduct(String id){
        CreateConnection();
        try {
            Inventory = database.getCollection("Inventario");
            // Código para la eliminación en MongoDB por medio del id
            BasicDBObject query = new BasicDBObject();
            query.put("_id", new ObjectId(id));
            Inventory.remove(query);

        }catch (MongoException e){
            System.out.println("Error al elimianr: " + e);
        }

        Close();
    }

    public void updateClientDB(String id, String name, String phone, String address, String nit){
        CreateConnection();
        try {
            BasicDBObject query =  new BasicDBObject();
            query.put("_id", new ObjectId(id));
            BasicDBObject update =  new BasicDBObject();
            update.put("Nombre", name);
            update.put("Telefono",phone);
            update.put("Direccion",address);
            update.put("NIT",nit);

            BasicDBObject updateObject =  new BasicDBObject();
            updateObject.put("$set",update);

            Inventory = database.getCollection("Clients");
            Inventory.update(query,updateObject);
            System.out.println("Cliente actualizado");

        }catch (MongoException e){
            System.out.println("Error al actualizar cliente" +e );
        }
        Close();
    }

    public void insertNewClient(String name,String address, String nit){
        CreateConnection();
        try {
            Inventory = database.getCollection("Clients");
            document =  new BasicDBObject();
            document.put("Nombre",name);
            document.put("Direccion",address);
            document.put("NIT",nit);

            Inventory.insert(document);
            System.out.println("Cliente intertado correctamente");

        }catch (MongoException e){
            System.out.println("error al insertar nuevo cliente" + e);
        }

        Close();
    }

    public void deleteClient(String id){
        CreateConnection();
        try {
            Inventory = database.getCollection("Clients");

            BasicDBObject query = new BasicDBObject();
            query.put("_id", new ObjectId(id));
            Inventory.remove(query);
            System.out.println("CLiente eliminado");

        }catch (MongoException e){
            System.out.println("error al eliminar cliente " + e);
        }

        Close();
    }

 }


