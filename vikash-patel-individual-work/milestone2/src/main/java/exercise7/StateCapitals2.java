/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise7;
import java.util.*;
/**
 *
 * @author vpatel
 */
public class StateCapitals2 
{
    private static ArrayList<Capital> allCapitals = new ArrayList<>();
    private static HashMap<String,Capital> stateCaps = new HashMap();
    public static void main(String[] args) 
    {
        initializeCapitals();
        initializeMap();
        printOutput();
        popGreater();
    }
    
    public static void initializeCapitals()
    {
         String[] capNames = {"Montgomery", "Juneau", "Phoenix", "Little Rock", "Sacramento",
                            "Denver", "Hartford", "Dover", "Tallahassee", "Atlanta", "Honolulu",
                            "Boise", "Springfield", "Indianapolis", "Des Moines", "Topeka", "Frankfort",
                            "Baton Rogue", "Augusta", "Annapolis", "Boston", "Lansing", "St. Paul",
                            "Jackson", "Jefferson City", "Helena", "Lincoln", "Carson City", "Concord",
                            "Trenton", "Santa Fe", "Albany", "Raleigh", "Bismarck", "Columbus",
                            "Oklahoma City", "Salem", "Harrisburg", "Providence", "Columbia", "Pierre",
                            "Nashville", "Austin", "Salt Lake City", "Montpelier", "Richmond",
                            "Olympia", "Charleston", "Madison", "Cheyenne"}; 
        int[] pops = {198232, 32198, 1679243, 199233, 507298,
                    719116, 121991, 38414, 193078, 491626, 351280,
                    228900, 114219, 860902, 216788, 125838, 28105,
                    226505, 18534, 39342, 687584, 118242, 308138, 
                    165608, 42623, 32285, 287870, 55416, 43428, 
                    83798, 85053, 97797, 476746, 74711, 880182,
                    653865, 173819, 48572, 179653, 134969, 14070,
                    673008, 983366, 198356, 7413, 230254, 
                    53144, 47908, 260427, 65249};
        int[] sqmis = {162, 3255, 517, 122, 100,
                    155, 18, 23, 103, 134, 68,
                    83, 66, 368, 83, 61, 15,
                    88, 58, 8, 90, 37, 56,
                    113, 38, 16, 94, 157, 67,
                    8, 37, 22, 145, 31, 223,
                    620, 48, 12, 21, 135, 13, 
                    526, 272, 110, 10, 63,
                    20, 33, 94, 25};
        
        for(int i=0; i<50; i++)
        {
            allCapitals.add(new Capital(capNames[i], pops[i], sqmis[i]));
        }
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
        
        for(int i = 0; i<50; i++)
        {
           stateCaps.put(states[i], allCapitals.get(i));
        }
    }
    
    public static void printOutput()
    {
        System.out.println("STATE/CAPITAL PAIRS:\n**********");
        for (String s : stateCaps.keySet())
        {
            System.out.println(s + " - " + stateCaps.get(s).getName() + 
                            " | Pop: " + stateCaps.get(s).getPopulation() + 
                            " | Area: " + stateCaps.get(s).getSqMiles() + " sq mi");
        }
        System.out.println();
    }
    
    public static void popGreater()
    {
        int pop = readInt();
        System.out.println("\nLISTING CAPITALS WITH POPULATIONS GREATER THAN " + pop + ":\n");
        for (String s : stateCaps.keySet())
        {
            if(stateCaps.get(s).getPopulation() > pop)
            {
                System.out.println(s + " - " + stateCaps.get(s).getName() + 
                                " | Pop: " + stateCaps.get(s).getPopulation() + 
                                " | Area: " + stateCaps.get(s).getSqMiles() + " sq mi");
            }
        }
    }
    
    public static int readInt()
    {
        System.out.print("Please enter the lower limit for capital city population: ");
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }
}
