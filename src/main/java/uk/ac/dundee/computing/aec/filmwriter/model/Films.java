/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.filmwriter.model;

import com.eclipsesource.json.JsonObject;
import com.myflix.myflix.stores.Video;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import static java.util.Spliterators.iterator;
import org.neo4j.driver.v1.*;
import uk.ac.dundee.computing.aec.lib.Hosts;


/**
 *
 * @author andycobley
 */
public class Films {

    Session session;
    LinkedList<String> ttl = new LinkedList();
    private String[] categories = {"Action", "Romcom", "Science Fiction", "Horror"};
    private String[] films = {
        "Citizen Kane",
        "The Godfather",
        "Casablanca",
        "Raging Bull",
        "Singin in the Rain",
        "Gone with the Wind",
        "Lawrence of Arabia",
        "Schindlers List",
        "Vertigo",
        "The Wizard of Oz",
        "City Lights",
        "The Searchers",
        "Star Wars",
        "Psycho",
        "2001: A Space Odyssey",
        "Sunset Blvd.",
        "The Graduate",
        "The General",
        "On the Waterfront",
        "Its a Wonderful Life",
        "Chinatown",
        "Some Like It Hot",
        "The Grapes of Wrath",
        "E.T. The Extra-Terrestrial",
        "To Kill A Mockingbird",
        "Mr. Smith Goes to Washington",
        "High Noon",
        "All About Eve",
        "Double Indemnity",
        "Apocalypse Now",
        "The Maltese Falcon",
        "The Godfather Part II",
        "One Flew Over the Cuckoos Nest",
        "Snow White and the Seven Dwarfs",
        "Annie Hall",
        "The Bridge on the River Kwai",
        "The Best Years of Our Lives",
        "The Treasure of the Sierra Madre",
        "Dr. Strangelove",
        "The Sound of Music",
        "King Kong",
        "Bonnie and Clyde",
        "Midnight Cowboy",
        "The Philadelphia Story",
        "Shane",
        "It Happened One Night",
        "A Streetcar Named Desire",
        "Rear Window",
        "Intolerance",
        "The Lord of the Rings: The Fellowship of the Ring",
        "West Side Story",
        "Taxi Driver",
        "The Deer Hunter",
        "M*a*s*h",
        "North By Northwest",
        "Jaws",
        "Rocky",
        "The Gold Rush",
        "Nashville",
        "Duck Soup",
        "Sullivans Travels",
        "American Graffiti",
        "Cabaret",
        "Network",
        "The African Queen",
        "Raiders of the Lost Ark",
        "Whos Afraid of Virginia Woolf?",
        "Unforgiven",
        "Tootsie",
        "A Clockwork Orange",
        "Saving Private Ryan",
        "The Shawshank Redemption",
        "Butch Cassidy and the Sundance Kid",
        "The Silence of the Lambs",
        "In the Heat of the Night",
        "Forrest Gump",
        "All the Presidents Men",
        "Modern Times",
        "The Wild Bunch",
        "The Apartment",
        "Spartacus",
        "Sunrise",
        "Titanic",
        "Easy Rider",
        "A Night at the Opera",
        "Platoon",
        "12 Angry Men",
        "Bringing Up Baby",
        "The Sixth Sense",
        "Swing Time",
        "Sophies Choice",
        "Goodfellas",
        "The French Connection",
        "Pulp Fiction",
        "The Last Picture Show",
        "Do the Right Thing",
        "Blade Runner",
        "Yankee Doodle Dandy",
        "Toy Story",
        "Ben-Hur",};

    public Films() {
        
    }

    public void writeFilms() {
        Hosts host = new Hosts();
        session = host.getSession();
        System.out.println("Writing films");
        String catagory = categories[(int) ((double) categories.length * Math.random())];
        String film = films[(int) ((double) films.length * Math.random())];
        session.run("MATCH (n) DETACH\n"
                + "DELETE n");
        //session.run("CREATE (a:film {name:'" + film + "',category:'" + catagory + "'})");
        ArrayList<Integer> Available = new ArrayList();
        for (int i = 0; i < films.length; i++) {
            Available.add(new Integer(i));
        }
        
        for (int i = 0; i < 50; i++) {
            int aTown = (int) ((Available.size() - 1) * Math.random());
            System.out.println("film: " + aTown);
            int iTown = (int) (Available.get(aTown));
            ttl.add(films[iTown]);
            Available.remove(aTown);
        }
        Iterator<String> iterator;
        iterator = ttl.iterator();
        while (iterator.hasNext()) {
            film = (String) iterator.next();
            catagory = categories[(int) ((double) categories.length * Math.random())];
            session.run("CREATE (a:film {name:'" + film + "',catagory:'" + catagory + "'})");
        }
        session.close();
        host.close();
    }
    
    public void watchfilms(LinkedList<String> names){
        Hosts host = new Hosts();
        session = host.getSession();
        Iterator<String> iterator = names.iterator();
        while (iterator.hasNext()) {
           String name = (String) iterator.next(); 
           int num =1+(int)(6*Math.random());
           for (int i=1;i<num;i++){
               int iFilm=(int)((ttl.size() - 1) * Math.random());
               
                 String film =(String)ttl.get(iFilm);
                 session.run("MATCH (a:person {name:\""+name+"\"})  MATCH (b:film {name:\""+film+"\"}) create (a)-[watched:WATCHED]->(b)");
           }
        }
        session.close();
        host.close();
    }

}
