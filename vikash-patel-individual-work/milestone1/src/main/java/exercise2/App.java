/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise2;

/**
 *
 * @author vpatel
 */
public class App 
{
    public static void main(String[] args)
    {
        inABucket();
        System.out.println("----------");
        moreBucketsMoreFun();
        System.out.println("----------");
        theOrderOfThings();
        System.out.println("----------");
        bestAdderEver();
        System.out.println("----------");
        allAboutMe();
        System.out.println("----------");
        menuOfChampions();
        
    }
    
    private static void inABucket()
    {
         // You can declare all KINDS of variables.
        String walrus;
        double piesEaten;
        float weightOfTeacupPig;
        int grainsOfSand;

        // But declaring them just sets up the space for data
        // to use the variable, you have to put data IN it first!
        walrus = "Sir Leroy Jenkins III";
        piesEaten = 42.1;

        System.out.println("Meet my pet Walrus, " + walrus);
        System.out.print("He was a bit hungry today, and ate this many pies : ");
        System.out.println(piesEaten);
        
        weightOfTeacupPig = 13;
        grainsOfSand = 2;
        
        System.out.println("Weight of teacup: " + weightOfTeacupPig);
        System.out.println("Grains of sand: " + grainsOfSand);
        
    }
    
    private static void moreBucketsMoreFun()
    {
         // Declare ALL THE THINGS
        // (Usually it's a good idea to declare them at the beginning of the program)
        int butterflies, beetles, bugs;
        String color, size, shape, thing;
        double number;

        // Now give a couple of them some values
        butterflies = 2;
        beetles = 4;

        bugs = butterflies + beetles;
        System.out.println("There are only " + butterflies + " butterflies,");
        System.out.println("but " + bugs + " bugs total.");

        System.out.println("Uh oh, my dog ate one.");
        butterflies--; //Decrement operator used here
        System.out.println("Now there are only " + butterflies + " butterflies left.");
        System.out.println("But still " + bugs + " bugs left, wait a minute!!!");
        System.out.println("Maybe I just counted wrong the first time...");
        // Number of bugs does not change because it is a separate variable that is not altered.
    }
    
    private static void theOrderOfThings()
    {
        double number;
        String opinion, size, age, shape, color, origin, material, purpose;
        String noun;

        number = 5.0;
        opinion = " AWESOME ";
        size = "teensy-weensy ";
        age = "new ";
        shape = "oblong ";
        color = "BRIGHT yellow ";
        origin = "AlphaCentaurian ";
        material = "platinum ";
        purpose = "good ";

        noun = "dragons";

        // Using the + with strings, doesn't add it concatenates! (sticks them together)
        System.out.println(number + opinion + size + age + shape + color
                + origin + material + purpose + noun);
    }
    
    private static void bestAdderEver()
    {
        int num1, num2, num3, total;
        num1 = 3;
        num2 = 4;
        num3 = 10;
        System.out.println("Number 1 is: " + num1);
        System.out.println("Number 2 is: " + num2);
        System.out.println("Number 3 is: " + num3);
        total = num1+num2+num3;
        System.out.println("The total is: " + total);
    }
    
    private static void allAboutMe()
    {
        String name = "Vikash";
        String food = "pizza";
        int pets = 0;
        boolean gnocchi = true;
        int age = 7;
        
        System.out.println("I am " + name + ".");
        System.out.println("I have " + pets + " pets.");
        System.out.println("My favorite food is " + food + ".");
        System.out.println("It is " + gnocchi + " that I have eaten gnocchi.");
        System.out.println("And when I was " + age + " I learned to whistle.");
    }
    
    private static void menuOfChampions()
    {
        String item1 = "Slice of Big Rico Pizza --- $";
        String item2 = "Invisible Strawberry Pie --- $";
        String item3 = "Denver Omelet --- $";
        double price1 = 500.00;
        float price2 = (float) 2.00;
        float price3 = (float) 1.50;
        
        System.out.println(".oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.\n" +
            "			\n" +
            "		WELCOME TO RESTAURANT NIGHT VALE!\n" +
            "			Today's Menu Is...\n" +
            "\n" +
            ".oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.oOo.");
        System.out.println();
        System.out.print("	" + item1);
        System.out.printf("%.2f", price1);
        System.out.println();
        System.out.print("	" + item2);
        System.out.printf("%.2f", price2);
        System.out.println();
                System.out.print("	" + item3);
        System.out.printf("%.2f", price3);
        System.out.println();
    }
}
