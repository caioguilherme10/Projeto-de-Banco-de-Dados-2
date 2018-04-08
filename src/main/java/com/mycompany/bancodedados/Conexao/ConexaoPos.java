/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bancodedados.Conexao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author caio
 */
public class ConexaoPos {
    
    private static final String url = "jdbc:postgresql://localhost:5432/BancoD2";
    private static final String usuario = "postgres";
    private static final String senha = "caio0604";
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(url, usuario, senha);
    }
    
}
