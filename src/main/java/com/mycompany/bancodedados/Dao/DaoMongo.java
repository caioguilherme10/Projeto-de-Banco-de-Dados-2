/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bancodedados.Dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mycompany.bancodedados.Conexao.ConexaoMongo;
import com.mycompany.bancodedados.Model.Topico;

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

    
}
