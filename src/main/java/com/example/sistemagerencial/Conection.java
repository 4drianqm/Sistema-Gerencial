package com.example.sistemagerencial;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

import java.util.List;

public class Conection {


    public Mongo CreateConection(){
    DB db;
       try {
           Mongo mongo = new Mongo("localhost",27017);
           db = mongo.getDB("BiancaDB");
           List<String> DBnames = mongo.getDatabaseNames();
           System.out.println("se cocencto correctamente: " + DBnames);
       }catch (MongoException e){
           System.out.println("Error de conexcion");
       }
      return null;
    }
 }


