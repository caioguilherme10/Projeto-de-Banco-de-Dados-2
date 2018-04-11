/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bancodedados.Servelt;

import com.mycompany.bancodedados.Dao.DaoMongo;
import com.mycompany.bancodedados.Model.Topico;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.bson.types.ObjectId;

/**
 *
 * @author caio
 */
public class SalvarTopicoCommand implements Command{

    private DaoMongo daomongo;
    
    public SalvarTopicoCommand() {

        this.daomongo = new DaoMongo();
    }
    
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        
        Topico topico = new Topico();
        
        String email = req.getParameter("email1");
        String nome = req.getParameter("nome1");
        String titulo = req.getParameter("titulo");
        String categoria = req.getParameter("categoria");
        String conteudo = req.getParameter("conteudo");
        
        topico.setId(new ObjectId());
        topico.setNome(nome);
        topico.setEmail(email);
        topico.setCategoria(categoria);
        topico.setConteudo(conteudo);
        topico.setTitulo(titulo);
        
        daomongo.salvarTopico(topico);
            
        res.sendRedirect("TelaPrincipal.jsp");
        
        
    }
    
}
