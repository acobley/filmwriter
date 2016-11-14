/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.filmwriter;
import java.util.LinkedList;
import uk.ac.dundee.computing.aec.filmwriter.model.Films;
import uk.ac.dundee.computing.aec.filmwriter.model.People;
/**
 *
 * @author andycobley
 */
public class Writer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Films ff=new Films();
        ff.writeFilms();
        People pp = new People();
        pp.writePeople();
        LinkedList<String> names = pp.getNames();
        ff.watchfilms(names);
        
        
    }
    
}
