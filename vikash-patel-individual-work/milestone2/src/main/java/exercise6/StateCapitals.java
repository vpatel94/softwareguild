/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise6;

import java.util.*;

/**
 *
 * @author vpatel
 */
public class StateCapitals 
{
    
    public static HashMap<String,String> stateCaps = new HashMap();
    
    public static void main(String[] args) 
    {
       initializeMap();
       printStates();
       printCapitals();
       printStatesNCapitals();
       
    }
    
    public static void initializeMap()
    {
        String[] states = {"Alabama", "Alaska", "Arizona", "Arkansas", "California",
                            "Colorado", "Connecticut", "Delaware", "Florida", "Georgia",
                            "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas",
                            "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts",
                            "Michigan", "Minnesota", "Mississippi", "Missouri",
                            "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey",
                            "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio",
                            "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina",
                            "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia",
                            "Washington", "West Virginia", "Wisconsin", "Wyoming"};
        String[] capitals = {"Montgomery", "Juneau", "Phoenix", "Little Rock", "Sacramento",
                            "Denver", "Hartford", "Dover", "Tallahassee", "Atlanta", "Honolulu",
                            "Boise", "Springfield", "Indianapolis", "Des Moines", "Topeka", "Frankfort",
                            "Baton Rogue", "Augusta", "Annapolis", "Boston", "Lansing", "St. Paul",
                            "Jackson", "Jefferson City", "Helena", "Lincoln", "Carson City", "Concord",
                            "Trenton", "Santa Fe", "Albany", "Raleigh", "Bismarck", "Columbus",
                            "Oklahoma City", "Salem", "Harrisburg", "Providence", "Columbia", "Pierre",
                            "Nashville", "Austin", "Salt Lake City", "Montpelier", "Richmond",
                            "Olympia", "Charleston", "Madison", "Cheyenne"};
        
        for(int i = 0; i<50; i++)
        {
            stateCaps.put(states[i], capitals[i]);
        }
    }
    
    public static void printStates()
    {
        System.out.println("STATES:\n**********");
        for (String s : stateCaps.keySet())
        {
            System.out.println(s);
        }
        System.out.println();
    }
    
    public static void printCapitals()
    {
        System.out.println("STATES:\n**********");
        for (String s : stateCaps.keySet())
        {
            System.out.println(stateCaps.get(s));
        }
        System.out.println();
    }
    
    public static void printStatesNCapitals()
    {
        System.out.println("STATE/CAPITAL PAIRS:\n**********");
        for (String s : stateCaps.keySet())
        {
            System.out.println(s + " - " + stateCaps.get(s));
        }
        System.out.println();
    }
}
