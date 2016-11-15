/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.lib;
import org.neo4j.driver.v1.*;

/**
 *
 * @author andycobley
 */
public class Hosts {
    Session session=null;
    Driver driver=null;
    public Hosts()
    {
    driver = GraphDatabase.driver( "bolt://0.0.0.0", AuthTokens.basic( "neo4j", "XXX" ) );
    session = driver.session();
    }
    
    public Session getSession(){
        return session;
    }
    
    public void close(){
        driver.close();
    }
}
