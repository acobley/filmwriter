/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.filmwriter.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import org.neo4j.driver.v1.Session;
import uk.ac.dundee.computing.aec.lib.Hosts;

/**
 *
 * @author andycobley
 */
public class People {
    Session session;
    LinkedList<String> ttl = new LinkedList();
    String[] names={
    "Dedra Fleener",
    "Taneka Marino",
    "Neil Antunez",
    "Jerome Edberg",
    "Aaron Defrancisco",
    "Vanna Ventura",
    "Kenny Mchale",
    "Cole Marse",
    "Inocencia Nottage",
    "Laquanda Samuelson",
    "Stanton Auston",
    "Alden Silverman",
    "Grayce Ellerman",
    "Russell Pastorius",
    "Mallie Hood",
    "Margery Kraft",
    "Randee Tarpley",
    "Cortney Bertucci",
    "Chia Reza",
    "Mei Neves",
    "Janella Durrance",
   "Cherrie Crumley",
    "Catherine Wrinkle",
    "Shemika Rayos",
    "Luetta Stevenson",
    "Abe Kirksey",
    "Brigid Glidden",
    "Laure Giannini",
    "Russ Samson",
    "Jen Higa",
    "Justa Birge",
    "Jaimie Beltz",
    "Darlena Charlebois",
    "Kermit Saulsv",
    "Sheryll Kuehn",
    "Simonne Rockwellv",
    "Niesha Brister",
    "Catrice Buff",
    "Angelo Viola",
    "Palmer Valerius",
    "Honey Mangrum",
    "Hertha Stclair",
    "Andy Louque",
    "Pasquale Verhoeven",
    "Madge Westlake",
    "Lillian Roemer",
    "Salena Hewes",
    "Domitila Mongillo",
    "Alene Olivero",
    "Monique Hamman"};
    
    public People(){
        
    }
    public void writePeople(){
         Hosts host = new Hosts();
        session = host.getSession();
         System.out.println("Writing people");
        ArrayList<Integer> Available = new ArrayList();
        for (int i = 0; i < names.length; i++) {
            Available.add(new Integer(i));
        }
        
        for (int i = 0; i < 30; i++) {
            int aTown = (int) ((Available.size() - 1) * Math.random());
            System.out.println("person : " + aTown);
            int iTown = (int) (Available.get(aTown));
            ttl.add(names[iTown]);
            Available.remove(aTown);
        }
        Iterator<String> iterator;
        iterator = ttl.iterator();
        while (iterator.hasNext()) {
           String name = (String) iterator.next();
           //name=name.replace(" ", "");
            session.run("CREATE (a:person {name:'" + name + "'})");
        }
        
        //Create friends
        iterator = ttl.iterator();
        while (iterator.hasNext()) {
           String name = (String) iterator.next(); 
           int me= ttl.indexOf(name);
           int num =3+(int)(10*Math.random());
           for (int i=1;i<num;i++){
               int you=(int)((ttl.size() - 1) * Math.random());
               if (me != you){
                 String name2 =(String)ttl.get(you);
                 session.run("MATCH (a:person {name:\""+name+"\"})  MATCH (b:person {name:\""+name2+"\"}) create (a)-[friend:Friend]->(b)");
           
               }
           }
        }
        session.close();
        host.close();
    }
    
    public LinkedList<String> getNames(){
        return ttl;
    }
            
}
