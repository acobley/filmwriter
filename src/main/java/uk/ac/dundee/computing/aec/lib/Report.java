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

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
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
        FileWriter fw = null;
        try {
            fw = new FileWriter(FileName);
        } catch (Exception et) {
            System.out.println("Can't open file" + FileName);
            return;
        }
        while (iterator.hasNext()) {
            String name = (String) iterator.next();
            StatementResult result = session.run("MATCH (user:person {name:'" + name + "'})-[:Friend]->(f)-[:WATCHED]->(m:film) RETURN m.name AS title, m.catagory AS catagory");
            while (result.hasNext()) {
                Record record = result.next();
                String out = name + ": " + record.get("title").asString() + ": " + record.get("catagory").asString() + "\n";
                System.out.println(out);
                try {
                    fw.write(out);

                } catch (Exception et) {
                    System.out.println("Can't write to" + FileName);
                }
            }

        }
        try {
            fw.close();
        } catch (Exception et) {
            System.out.println("Can't close" + FileName);
        }
        session.close();
        host.close();
    }

    public void MoveToHDFS() {
        try {
            StringBuffer output = new StringBuffer();
            Process p = Runtime.getRuntime().exec("/usr/local/bin/docker exec hadoop-test hadoop fs -rm Data/FilmsReport.txt");
            p.waitFor();
            BufferedReader  reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line="";
            output= new StringBuffer();
              while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
            System.out.println(output);
             reader = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
            
            System.out.println(output);
            
             p = Runtime.getRuntime().exec("/usr/local/bin/docker exec hadoop-test hadoop fs -mkdir Data");
            p.waitFor();
            reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
            
           reader = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
            
            System.out.println(output);

            p = Runtime.getRuntime().exec("/usr/local/bin/docker exec hadoop-test hadoop fs -put /home/FilmsReport.txt Data");
            p.waitFor();
            reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            line="";
            output= new StringBuffer();
              while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
            System.out.println(output);
             reader = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            line = "";
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
            
            System.out.println(output);
        } catch (Exception et) {
            System.out.println("Can't move to Hadoop" + et);
        }
    }
}
