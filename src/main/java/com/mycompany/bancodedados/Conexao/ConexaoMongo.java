/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bancodedados.Conexao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoDatabase;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author caio
 */
public class ConexaoMongo {
    
    private static CodecRegistry pojoCodecRegistry;
    private static MongoClient mongoClient;
    private static MongoDatabase database;

    private static void createConection() {

        pojoCodecRegistry =
                fromRegistries(MongoClient.getDefaultCodecRegistry(),
                        fromProviders(PojoCodecProvider.builder()
                                .automatic(true).build()));

        mongoClient = new MongoClient("localhost", MongoClientOptions.builder()
                .codecRegistry(pojoCodecRegistry)
                .build());
    }

    public static CodecRegistry getCodecRegistry() {

        return pojoCodecRegistry;
    }

    public static MongoClient getMongoClient() {

        return mongoClient;
    }

    public static MongoDatabase getConectionDB() {

        createConection();

        database = getMongoClient().getDatabase("projeto")
                .withCodecRegistry(getCodecRegistry());

        return database;
    }
}
