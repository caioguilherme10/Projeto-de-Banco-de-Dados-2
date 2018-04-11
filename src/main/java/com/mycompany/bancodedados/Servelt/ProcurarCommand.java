/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bancodedados.Servelt;

import com.mycompany.bancodedados.Dao.DaoMongo;
import com.mycompany.bancodedados.Model.Topico;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author caio
 */
public class ProcurarCommand implements Command{
    
    private DaoMongo daomongo;
    private ArrayList<Topico> topicos;
    
    public ProcurarCommand() {

        this.daomongo = new DaoMongo();
        this.topicos = new ArrayList<>();
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        
        String texto = req.getParameter("texto");
        
        topicos = daomongo.buscarPorTitulo(texto);
        
        req.setAttribute("Topicos", topicos);
        
        RequestDispatcher dispatcher = req.getRequestDispatcher("TelaPrincipal.jsp");
        
        dispatcher.forward(req, res);
        
    }
    
}
