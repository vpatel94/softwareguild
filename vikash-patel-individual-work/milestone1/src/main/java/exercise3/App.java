/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise3;
import java.util.Scanner;
/**
 *
 * @author vpatel
 */
public class App 
{
    public static void main(String[] args)
    {
        questForUserInput();
        System.out.println("----------");
        dontForgetToStoreIt();
        System.out.println("----------");
        biggerBetterAdder();
        System.out.println("----------");
        passingTheTuringTest();
        System.out.println("----------");
        allTheTrivia();
        System.out.println("----------");
        doItBetter();
        System.out.println("----------");
        healthyHearts();
        System.out.println("----------");
        miniMadLibs();
    }
    
    private static void questForUserInput()
    {
        Scanner inputReader = new Scanner(System.in);

        String yourName;
        String yourQuest;
        double velocityOfSwallow;

        System.out.print("What is your name?? ");
        yourName = inputReader.nextLine();

        System.out.print("What is your quest?! ");
        yourQuest = inputReader.nextLine();

        System.out.print("What is the airspeed velocity of an unladen swallow?!?! ");
        velocityOfSwallow = inputReader.nextDouble();

        System.out.println("How do you know " + velocityOfSwallow + " is correct," + yourName + ",");
        System.out.println("when you didn't even know if the swallow was African or European!");
        System.out.println("Maybe skip answering things about birds and instead go " + yourQuest);
    }
    
    private static void dontForgetToStoreIt()
    {
        int meaningOfLifeAndEverything;
        double pi;
        String cheese, color;

        Scanner inputReader = new Scanner(System.in);

        System.out.println("Give me pi to at least 5 decimals: ");
        pi = inputReader.nextDouble();

        System.out.println("What is the meaning of life, the universe & everything? ");
        meaningOfLifeAndEverything = inputReader.nextInt();

        System.out.println("What is your favorite kind of cheese? ");
        cheese = inputReader.next();

        System.out.println("Do you like the color red or blue more? ");
        color = inputReader.next();

        System.out.println("Ooh, " + color + " " + cheese +" sounds delicious!");
        System.out.println("The circumference of life is " +( 2 * pi * meaningOfLifeAndEverything));
    }
    
    private static void biggerBetterAdder()
    {
        Scanner scan = new Scanner(System.in);
        
        int num1, num2, num3, total;
        System.out.println("Enter 3 separate numbers, pressing enter after each.");
        num1 = scan.nextInt();
        num2 = scan.nextInt();
        num3 = scan.nextInt();
        System.out.println("Number 1 is: " + num1);
        System.out.println("Number 2 is: " + num2);
        System.out.println("Number 3 is: " + num3);
        total = num1+num2+num3;
        System.out.println("The total is: " + total);
    }
    
    private static void passingTheTuringTest()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Hello there!" + "\nWhat is your name? ");
        String name = scan.nextLine();
        System.out.println("Hi, " + name + "! What is your favorite color? ");
        String color = scan.nextLine();
        System.out.println("Huh, " + color + "? Mine's Electric Lime.");
        System.out.println("I really like limes. They're my favorite fruit, too.");
        System.out.println("What's YOUR favorite fruit, " + name + "? ");
        String fruit = scan.nextLine();
        System.out.println("Really? " + fruit + "? That's wild!");
        System.out.println("Speaking of favorites, what's your favorite number? ");
        int num = scan.nextInt();
        System.out.println(num + " is a cool number. Mine's -7.");
        System.out.println("Did you know " + num + " * -7 is " +(num*-7)+ "? That's a cool number too!");
        System.out.println("Well, thanks for talking to me, " + name);
    }
    
    private static void allTheTrivia()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("1,024 Gigabytes is equal to one what? ");
        String a1 = scan.nextLine();
        System.out.print("In our solar system which is the only planet that rotates clockwise? ");
        String a2 = scan.nextLine();
        System.out.print("The largest volcano ever discovered in our solar system is located on which planet? ");
        String a3 = scan.nextLine();
        System.out.print("What is the most abundant element in the earth's atmosphere? ");
        String a4 = scan.nextLine();
        
        System.out.println("Wow, 1,024 Gigabytes is a " + a3);
        System.out.println("I didn't know that the largest ever volcano was discovered on " + a1);
        System.out.println("That's amazing that " + a2 + " is the most abundant element in the atmosphere...");
        System.out.println(a4 + " is the only planet that rotates clockwise, neat!");
    }
    
    private static void doItBetter()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("How many miles can you run? ");
        int miles = scan.nextInt();
        System.out.println("Only " + miles + "? I can run " + (miles*2+1) + "!");
        System.out.print("How many hot dogs can you eat? ");
        int hotdogs = scan.nextInt();
        System.out.println("Only " + hotdogs + "? I can eat " + (hotdogs*2+1) + "!");
        System.out.print("How many languages do you know? ");
        int lang = scan.nextInt();
        System.out.println("Only " + lang + "? I know " + (lang*2+1) + "!");
    }
    
    private static void healthyHearts()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("What is your age? ");
        int age = scan.nextInt();
        int max = 220-age;
        System.out.println("Your maximum heart rate should be " + max + "beats per minute.");
        System.out.println("Your target HR Zone is " + (max*0.5) + " - " + (max*0.85) + " beats per minute.");
    }
    
    private static void miniMadLibs()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Let's play MAD LIBS!\n");
        String s1, s2, s3, s5, s6, s7, s8, s9, s10;
        
        System.out.print("I need a noun: ");
        s1 = scan.nextLine();
        System.out.print("Now an adjective: ");
        s2 = scan.nextLine();
        System.out.print("Another noun: ");
        s3 = scan.nextLine();
        System.out.print("And a number: ");
        int s4 = scan.nextInt();
        System.out.print("Another adj: ");
        s5 = scan.nextLine();
        System.out.print("A pliural noun: ");
        s6 = scan.nextLine();
        System.out.print("Another one: ");
        s7 = scan.nextLine();
        System.out.print("One more: ");
        s8 = scan.nextLine();
        System.out.print("A verb (present tense): ");
        s9 = scan.nextLine();
        System.out.print("Same verb (past tense): ");
        s10 = scan.nextLine();
        
        System.out.println("*** NOW LETS GET MAD (libs) ***");
        System.out.println(s1 + ": the " + s2 + " frontier. These are the voyages"
                + " of the starship " + s3 + ". Its " + s4 + "-year mission: to explore"
                        + " strange " + s5 + " " + s6 + ", to seek out " + s5 + " "
                                + s7 + " and " + s5 + " " + s8 + ", to boldly " + s9 + 
                                    " where no one has " + s10 + " before.");
    }
}
