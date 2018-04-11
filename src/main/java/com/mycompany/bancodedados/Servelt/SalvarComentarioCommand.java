/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bancodedados.Servelt;

import com.mycompany.bancodedados.Dao.DaoMongo;
import com.mycompany.bancodedados.Model.Comentario;
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
public class SalvarComentarioCommand implements Command{
    
    private DaoMongo daomongo;
    private ArrayList<Topico> topicos;
    private ArrayList<Comentario> comentarios;
    
    public SalvarComentarioCommand() {

        this.daomongo = new DaoMongo();
        this.topicos = new ArrayList<>();
        this.comentarios = new ArrayList<>();
        
    }
    

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        
        Comentario comentario = new Comentario();
        
        String email = req.getParameter("email1");
        String nome = req.getParameter("nome1");
        String conteudo = req.getParameter("conteudo");
        
        comentario.setConteudo(conteudo);
        comentario.setEmail(email);
        comentario.setNome(nome);
        
        topicos = daomongo.buscarTopicos();
        
        Topico topico2 = new Topico();
        ObjectId id = new ObjectId(req.getParameter("id"));
        
        for (Topico topico : topicos) {
            if(topico.getId().equals(id)){
                topico2 = topico;
            } else {
                
            }
        }
        
        if(topico2.getComentario() == null){
            comentarios.add(comentario);
            topico2.setComentario(comentarios);
            daomongo.autalizarTopico(topico2);
        }else{
            comentarios = topico2.getComentario();
            comentarios.add(comentario);
            topico2.setComentario(comentarios);
            daomongo.autalizarTopico(topico2);
        }
        
        
        
        ArrayList<Topico> topicos2 = new ArrayList<>();
        
        for (Topico topico : topicos) {
            if((topico2.getCategoria().equals(topico.getCategoria())) && (!topico2.equals(topico))){
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
