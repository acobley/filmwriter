/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
my friends
MATCH (user:person {name:'Honey Mangrum'})-[:Friend]->(f)
RETURN f

What I watched
MATCH (user:person {name:'Sheryll Kuehn'})-[:WATCHED]->(w)
RETURN w

My friends films
MATCH (user:person {name:'Honey Mangrum'})-[:Friend]->(f)-[:WATCHED]->(m:film) return m;
*/

package uk.ac.dundee.computing.aec.lib;

import java.util.Iterator;
import java.util.LinkedList;
import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;

/**
 *
 * @author andy
 */
public class Report {

    Session session;

    public Report() {

    }

    public void getReport(LinkedList<String> names, String FileName) {
        Hosts host = new Hosts();
        session = host.getSession();
        Iterator<String> iterator = names.iterator();
        while (iterator.hasNext()) {
            String name = (String) iterator.next();
            StatementResult result = session.run("MATCH (user:person {name:'" + name + "'})-[:Friend]->(f)-[:WATCHED]->(m:film) RETURN m.name AS title, m.catagory AS catagory");
            while (result.hasNext()) {
                Record record = result.next();
                System.out.println(name +": "+ record.get("title").asString() + ": " + record.get("catagory").asString());
            }

        }
        session.close();
        host.close();
    }
}
