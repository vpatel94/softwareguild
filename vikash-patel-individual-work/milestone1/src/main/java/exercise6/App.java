/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise6;
import java.util.Scanner;
import java.util.Random;
/**
 *
 * @author vpatel
 */
public class App 
{
    public static void main(String[] args)
    {
        waitAWhile();
        System.out.println("**********");
        stayPositive();
        System.out.println("**********");
        rollerCoaster();
        System.out.println("**********");
        doOrDoNot();
        System.out.println("**********");
        lovesMe();
        System.out.println("**********");
        maybeItLovesMe();
        System.out.println("**********");
        guessMeFinally();
        System.out.println("**********");
        lazyTeenager();
    }
    
    private static void waitAWhile()
    {
        int timeNow = 5;
        int bedTime = 10;

        while (timeNow < bedTime) {
            System.out.println("It's only " + timeNow + " o'clock!");
            System.out.println("I think I'll stay up just a liiiiittle longer....");
            timeNow++; // Time passes
        }

        System.out.println("Oh. It's " + timeNow + " o'clock.");
        System.out.println("Guess I should go to bed ...");
    }
    
    private static void stayPositive()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("What number should I count down from? ");
        int num = scan.nextInt();
        while(num >= 0)
        {
            System.out.println(num);
            num--;
        }
    }
    
    private static void rollerCoaster()
    {
        Scanner userInput = new Scanner(System.in);

        System.out.println("We're going to go on a roller coaster...");
        System.out.println("Let me know when you want to get off...!");

        String keepRiding = "y";
        int loopsLooped = 0;
        while (keepRiding.equals("y")) {
            System.out.println("WHEEEEEEEEEEEEEeEeEEEEeEeeee.....!!!");
            System.out.print("Want to keep going? (y/n) :");
            keepRiding = userInput.nextLine();
            loopsLooped++;
        }

        System.out.println("Wow, that was FUN!");
        System.out.println("We looped that loop " + loopsLooped + " times!!");
    }
    
    private static void doOrDoNot()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Should I do it? (y/n) ");
        boolean doIt;

        if (input.next().equals("y")) {
            doIt = true; // DO IT!
        } else {
            doIt = false; // DONT YOU DARE!
        }

        boolean iDidIt = false;

        do {
            iDidIt = true;
            break;
        } while (doIt);

        if (doIt && iDidIt) {
            System.out.println("I did it!");
        } else if (!doIt && iDidIt) {
            System.out.println("I know you said not to ... but I totally did anyways.");
        } else {
            System.out.println("Don't look at me, I didn't do anything!");
        }
    }
    
    private static void lovesMe()
    {
        int num = 17;
        while(num > 0)
        {
            System.out.println("It loves me NOT!");
            System.out.println("It LOVES me!");
            num--;
        }
    }
    
    private static void maybeItLovesMe()
    {
        //Mastered
    }
    
    private static void guessMeFinally()
    {
        //Mastered
    }
    
    private static void bewareTheKraken()
    {
        System.out.println("Already, get those flippers and wetsuit on - we're going diving!");
        System.out.println("Here we goooOOooOooo.....! *SPLASH*");

        int depthDivedInFt = 0;

        // Turns out the ocean is only so deep, 36200 at the deepest survey,
        // so if we reach the bottom ... we should probably stop.
        while(depthDivedInFt < 36200){
            System.out.println("So far, we've swam " + depthDivedInFt + " feet");

            if(depthDivedInFt >= 20000){
                System.out.println("Uhhh, I think I see a Kraken, guys ....");
                System.out.println("TIME TO GO!");
                break;
            }

            // I can swim, really fast! 500ft at a time!
            depthDivedInFt += 1000;
        }
        System.out.println("");
        System.out.println("We ended up swimming " + depthDivedInFt + " feet down.");
        System.out.println("I bet we can do better next time!");
    }
    
    private static void lazyTeenager()
    {
        // Mastered
    }       
    
}
