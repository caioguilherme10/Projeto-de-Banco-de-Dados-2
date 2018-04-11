/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bancodedados.Dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.text;
import com.mycompany.bancodedados.Conexao.ConexaoMongo;
import com.mycompany.bancodedados.Model.Topico;
import java.util.ArrayList;

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

    
}
