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
import org.bson.types.ObjectId;

/**
 *
 * @author caio
 */
public class IrParaTopicoCommand implements Command{
    
    private DaoMongo daomongo;
    private ArrayList<Topico> topicos;
    
    public IrParaTopicoCommand() {

        this.daomongo = new DaoMongo();
        this.topicos = new ArrayList<>();
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        
        topicos = daomongo.buscarTopicos();
        
        Topico topico2 = new Topico();
        ObjectId id = new ObjectId(req.getParameter("id"));
        
        for (Topico topico : topicos) {
            if(topico.getId() == id){
                topico2 = topico;
            } else {
                
            }
        }
        
        ArrayList<Topico> topicos2 = new ArrayList<>();
        
        for (Topico topico : topicos) {
            if(topico2.getCategoria().equals(topico.getCategoria())){
                topicos2.add(topico);
            } else {
                
            }
        }
        
        req.setAttribute("Topico", topico2);
        req.setAttribute("Topicos", topicos2);
        
        RequestDispatcher dispatcher = req.getRequestDispatcher("TelaTopico.jsp");
        
        dispatcher.forward(req, res);
        
    }
    
}
