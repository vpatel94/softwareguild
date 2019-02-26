/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise5;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author vpatel
 */
public class App {
    
    public static void main(String[] args)
    {
        aLittleChaos();
        System.out.println("**********");
        opinionator();
        System.out.println("**********");
        fortuneCookie();
        System.out.println("**********");
        highRoller();
        System.out.println("**********");
        coinFlipper();
        System.out.println("**********");
        guessMeMore();
    }
    
    private static void aLittleChaos()
    {
        Random randomizer = new Random();

        System.out.println("Random can make integers: " + randomizer.nextInt());
        System.out.println("Or a double: " + randomizer.nextDouble());
        System.out.println("Or even a boolean: " + randomizer.nextBoolean());

        int num = randomizer.nextInt(100);

        System.out.println("You can store a randomized result: " + num);
        System.out.println("And use it over and over again: " + num + ", " + num);

        System.out.println("Or just keep generating new values");
        System.out.println("Here's a bunch of numbers from 0 - 100: ");

        System.out.print(randomizer.nextInt(101) + ", ");
        System.out.print(randomizer.nextInt(101) + ", ");
        System.out.print(randomizer.nextInt(101) + ", ");
        System.out.print(randomizer.nextInt(101) + ", ");
        System.out.print(randomizer.nextInt(101) + ", ");
        System.out.println(randomizer.nextInt(101));
    }
    
    private static void opinionator()
    {
        Random randomizer = new Random();
        System.out.println("I can't decide what animal I like the best.");
        System.out.println("I know! Random can decide FOR ME!");

        int x = randomizer.nextInt(5);

        System.out.println("The number we chose was: " + x);

        if (x == 0) {
            System.out.println("Llamas are the best!");
        } else if (x == 1) {
            System.out.println("Dodos are the best!");
        } else if (x == 2) {
            System.out.println("Woolly Mammoths are DEFINITELY the best!");
        } else if (x == 3) {
            System.out.println("Sharks are the greatest, they have their own week!");
        } else if (x == 4) {
            System.out.println("Cockatoos are just so awesomme!");
        } else if (x == 5) {
            System.out.println("Have you ever met a Mole-Rat? They're GREAT!");
        }

        System.out.println("Thanks Random, maybe YOU'RE the best!");
    }
    
    private static void fortuneCookie()
    {
        String[] fortunes = {"Those aren’t the droids you’re looking for.",
                            "Never go in against a Sicilian when death is on the line!",
                            "Goonies never say die.",
                            "With great power there must also come — great responsibility.",
                            "Never argue with the data.",
                            "Try not. Do, or do not. There is no try.",
                            "You are a leaf on the wind, watch how you soar.",
                            "Do absolutely nothing, and it will be everything that you thought it could be.",
                            "Kneel before Zod.",
                            "Make it so."};
        Random randomizer = new Random();
        int x = randomizer.nextInt(10);
        System.out.println(fortunes[x]);
    }
    
    private static void highRoller()
    {
        Random diceRoller = new Random();
        Scanner scan = new Scanner(System.in);
        System.out.print("How many sides is the dice? ");
        int sides = scan.nextInt();
        int rollResult = diceRoller.nextInt(sides) + 1;

        System.out.println("TIME TO ROOOOOOLL THE DICE!");
        System.out.println("I rolled a " + rollResult);

        if (rollResult == 1) 
        {
            System.out.println("You rolled a critical failure!");
        }
        else if (rollResult == sides)
        {
            System.out.println("You rolled a critical! Nice Job!");
        }
    }
    
    private static void coinFlipper()
    {
        Random randomizer = new Random();
        System.out.println("Ready.. set.. FLIP!");
        if (randomizer.nextBoolean()) //if randomizer produces true -> Heads
        {
            System.out.println("You got heads!");
        }
        else
        {
            System.out.println("You got tails!");
        }
    }
    
    private static void guessMeMore()
    {
        Scanner scan = new Scanner(System.in);
        Random randomizer = new Random();
        int num = randomizer.nextInt(201) - 100;
        System.out.println("I've chosen a number, betcha can't guess it! ");
        System.out.print("Your guess: ");
        int guess = scan.nextInt();
        
        if (guess == num)
        {
            System.out.println("Wow, nice guess! That was it!");
        }
        else if (guess < num)
        {
            System.out.println("Ha, nice try - too low! I chose " + num);
        }
        else
        {
            System.out.println("Too bad, way too high. I chose " + num);
        }
    }
  
}
