/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bancodedados.Dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.text;
import com.mongodb.client.model.TextSearchOptions;
import com.mycompany.bancodedados.Conexao.ConexaoMongo;
import com.mycompany.bancodedados.Model.Topico;
import java.util.ArrayList;
import java.util.regex.Pattern;
import org.bson.BsonDocument;
import org.bson.BsonObjectId;
import org.bson.BsonRegularExpression;
import org.bson.BsonString;
import org.bson.BsonUndefined;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 *
 * @author caio
 */
public class DaoMongo {

    MongoDatabase database;

    public DaoMongo() {

        database = ConexaoMongo.getConectionDB();
    }

    public void salvarTopico(Topico topico) {
        MongoCollection collection = database.getCollection("Topico", Topico.class);

        collection.insertOne(topico);
    }

    public void autalizarTopico(Topico topico) throws JsonProcessingException {
        MongoCollection collection = database.getCollection("Topico", Topico.class);
        
        Bson filter = new BsonDocument("_id", new BsonObjectId(topico.getId()));  
        collection.findOneAndReplace(filter, topico);
    }

    public ArrayList<Topico> buscarTopicoCategoria(String query) {

        MongoCursor cursor = buscarCategoria(query);

        ArrayList<Topico> topicos = new ArrayList<>();

        try {
            while (cursor.hasNext()) {
                topicos.add((Topico) cursor.next());
            }
        } finally {
            cursor.close();
        }

        return topicos;
    }

    public MongoCursor buscarCategoria(String categoria) {
        MongoCollection collection = database.getCollection("Topico", Topico.class);

        return collection.find(text(categoria)).iterator();
    }

    public MongoCursor buscartopico() {
        MongoCollection collection = database.getCollection("Topico", Topico.class);

        return collection.find().iterator();
    }

    public ArrayList<Topico> buscarTopicos() {

        MongoCursor cursor = buscartopico();

        ArrayList<Topico> topicos = new ArrayList<>();

        try {
            while (cursor.hasNext()) {
                topicos.add((Topico) cursor.next());
            }
        } finally {
            cursor.close();
        }

        return topicos;
    }

    public ArrayList<Topico> buscarPorTitulo(String query) {
        ArrayList<Topico> topicos = new ArrayList<>();
        MongoCollection collection = database.getCollection("Topico", Topico.class);
        
        Pattern pattern = Pattern.compile(query + ".*", Pattern.CASE_INSENSITIVE);
        
        TextSearchOptions opt = new TextSearchOptions();
        opt.caseSensitive(Boolean.FALSE);
        
        Bson mathTopic = new BsonDocument("titulo", new BsonRegularExpression("^[a-zA-Z ]*" + query + "*"));
        
        try (MongoCursor results = collection.find(mathTopic).iterator()) {
            while (results.hasNext()) {
                topicos.add((Topico) results.next());
            } 
        }
        
        return topicos;
    }
    
}
