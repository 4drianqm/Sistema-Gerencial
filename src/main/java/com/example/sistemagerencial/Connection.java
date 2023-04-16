package com.example.sistemagerencial;


import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import org.bson.types.ObjectId;

public class Connection {
 public MongoClient mongoclient = null;
    public DB database;
    public DBCursor cursor = null;
    public DBCollection Inventory;
    public String server = "localhost";
    public Integer port = 27017;

    public BasicDBObject document;



 // Create connection to database
    public MongoClient CreateConnection(){

          try {
                mongoclient = new MongoClient(server,port);
                database = mongoclient.getDB("BiancaDB");
                System.out.println("Conexion exitosa");

                Inventory = database.getCollection("Inventario");
                cursor = Inventory.find();

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

 }


