
import com.mycompany.bancodedados.Dao.DaoMongo;
import com.mycompany.bancodedados.Model.Topico;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author caio
 */
public class MainTest {
    public static void main(String[] args) {
        
        DaoMongo topics = new DaoMongo();
        ArrayList<Topico> answers = topics.buscarPorTitulo("test");
        answers.stream().forEach(System.out::println);
        
    }
}
