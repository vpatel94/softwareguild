/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise4;
import java.util.Scanner;
/**
 *
 * @author vpatel
 */
public class App 
{
    public static void main(String[] args)
    {
        llamasWhalesAndDodos();
        System.out.println("**********");
        guessMe();
        System.out.println("**********");
        yourLifeInMovies();
        System.out.println("**********");
        spaceRustlers();
        System.out.println("**********");
        birthStones();
        System.out.println("**********");
        triviaNight();
        System.out.println("**********");
        knockKnock();
        System.out.println("**********");
        pickyEater();
        System.out.println("**********");
        fieldDay();
        System.out.println("**********");
        miniZork();
    }
    
    private static void llamasWhalesAndDodos()
    {
        int llamas = 20;
        int whales = 15;
        int dodos = 0;

        if (dodos > 0) {
            System.out.println("Egads, I thought dodos were extinct!");
        }

        if(dodos < 0){
            System.out.println("Hold on, how can we have NEGATIVE dodos??!");
        }

        if(llamas > whales){
            System.out.println("Whales may be bigger, but llamas are better, ha!");
        }

        if(llamas <= whales){
            System.out.println("Aw man, brawn over brains I guess. Whales beat llamas.");
        }

        System.out.println("There's been a huge increase in the dodo population via cloning!");
        dodos += 10;

        if( (whales + llamas) < dodos){
            System.out.println("I never thought I'd see the day when dodos ruled the earth.");
        }

        if(llamas > whales && llamas > dodos){
            System.out.println("I don't know how, but the llamas have come out ahead! Sneaky!");
        }
    }
    
    private static void guessMe()
    {
        Scanner scan = new Scanner(System.in);
        int num = 7;
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
    
    private static void yourLifeInMovies()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Hey, let's play a game! What's your name? ");
        String name = scan.nextLine();
        System.out.println("Okay, " + name + ", when were you born? ");
        int year = scan.nextInt();
        System.out.println("Well Kristin...");
        if (year < 2005)
        {
            System.out.println("Pixar's 'Up' came out half a decade ago");
        }
        
        if (year < 1995)
        {
            System.out.println("The first Harry Potter came out over 15 years ago.");
        }
        
        if (year < 1985)
        {
            System.out.println("Space Jam came out not last decade, but the one before THAT.");
        }
        
        if (year < 1975)
        {
            System.out.println("The original Jurassic Park release is closer to the lunar landing, than today.");
        }
        
        if (year < 1965)
        {
            System.out.println("MASH has been around for almost half a century!");
        }
    }
    
    private static void spaceRustlers()
    {
        int spaceships = 10;
        int aliens = 25;
        int cows = 100;

        if(aliens > spaceships){
            System.out.println("Vrroom, vroom! Let's get going!");
        } else{
            System.out.println("There aren't enough green guys to drive these ships!");
        }

        if(cows == spaceships){
            System.out.println("Wow, way to plan ahead! JUST enough room for all these walking hamburgers!");
        } else if (cows > spaceships){
            System.out.println("Dangit! I don't how we're going to fit all these cows in here!");
        } else {
            System.out.println("Too many ships! Not enough cows.");
        }
    }
    
    private static void birthStones()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("What month's birthstone are you wanting to know? (number): ");
        int month = scan.nextInt();
        
        if(month == 1)
        {
            System.out.println("January's birthstone is Garnet.");
        }
        else if(month == 2)
        {
            System.out.println("February's birthstone is Amethyst.");
        }
        else if(month == 3)
        {
            System.out.println("March's birthstone is Aquamarine.");
        }
        else if(month == 4)
        {
            System.out.println("April's birthstone is Diamond.");
        }
        else if(month == 5)
        {
            System.out.println("May's birthstone is Emerald.");
        }
        else if(month == 6)
        {
            System.out.println("June's birthstone is Pearl.");
        }
        else if(month == 7)
        {
            System.out.println("July's birthstone is Ruby.");
        }
        else if(month == 8)
        {
            System.out.println("August's birthstone is Peridot.");
        }
        else if(month == 9)
        {
            System.out.println("September's birthstone is Sapphire.");
        }
        else if(month == 10)
        {
            System.out.println("October's birthstone is Opal.");
        }
        else if(month == 11)
        {
            System.out.println("November's birthstone is Topaz.");
        }
        else if(month == 12)
        {
            System.out.println("December's birthstone is Turquoise.");
        }
        else
        {
            System.out.println("I think you must be confused, " + month + " doesn't match a month.");
        }
    }
    
    private static void triviaNight()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("It's TRIVIA NIGHT! Are you ready?!");
        int correct = 0;
        
        System.out.println("\nFirst Question:"+"\n"+"What is the Lowest Level Programming Language?"+
            "\n1) Source Code\n2) Assembly Language\n3) C#\n4) Machine Code\n");
        System.out.print("Your Answer: ");
        int answer = scan.nextInt();
        if(answer == 4)
        {
            correct++;
        }
        
        System.out.println("\nSecond Question:"+"\n"+"Website Security CAPTCHA Forms Are Descended From the Work of?"+
            "\n1) Grace Hopper\n2) Alan Turing\n3) Charles Babbage\n4) Larry Page\n");
        System.out.print("Your Answer: ");
        answer = scan.nextInt();
        if(answer == 2)
        {
            correct++;
        }
        
        System.out.println("\nLast Question:"+"\n"+"Which of These Sci-Fi Ships Was Once Slated for a Full-Size Replica in Las Vegas?"+
            "\n1) Serenity\n2) The Battlestar Galactica\n3) The USS Enterprise\n4) The Millennium Falcon\n");
        System.out.print("Your Answer: ");
        answer = scan.nextInt();
        if(answer == 3)
        {
            correct++;
        }
        
        System.out.println("You're done! You got " + correct + " correct!");
    }
    
    private static void knockKnock()
    {
        Scanner inputReader = new Scanner(System.in);

        System.out.print("Knock Knock! Guess who!! ");
        String nameGuess = inputReader.nextLine();

        if(nameGuess.equals("Marty McFly")){
            System.out.println("Hey! That's right! I'm back!");
            System.out.println(".... from the Future."); // Sorry, had to!
        }else{
            System.out.println("Dude, do I -look- like " + nameGuess);
        }
    }
    
    private static void pickyEater()
    {
        Scanner userInput = new Scanner(System.in);

        System.out.print("How many times has it been fried? (#) ");
        int timesFried = userInput.nextInt();

        System.out.print("Does it have any spinach in it? (y/n) ");
        String hasSpinach = userInput.next();

        System.out.print("Is it covered in cheese? (y/n) ");
        String cheeseCovered = userInput.next();

        System.out.print("How many pats of butter are on top? (#) ");
        int butterPats = userInput.nextInt();

        System.out.print("Is it covered in chocolate? (y/n) ");
        String chocolatedCovered = userInput.next();

        System.out.print("Does it have a funny name? (y/n) ");
        String funnyName = userInput.next();

        System.out.print("Is it broccoli? (y/n) ");
        String isBroccoli = userInput.next();

        // Conditionals should go here! Here's the first one for FREE!
        if (hasSpinach.equals("y") || funnyName.equals("y")) 
        {
            System.out.println("There's no way that'll get eaten.");
        }
        if (timesFried == 3 && chocolatedCovered.equals("y"))
        {
            System.out.println("Oh, it's like a deep fried snickers. That'll be a hit!");
        }
        if (timesFried == 2 && cheeseCovered.equals("y"))
        {
            System.out.println("Mmm. Yeah, fried cheesy doodles will get et.");
        }
        if (isBroccoli.equals("y") && butterPats > 6 && cheeseCovered.equals("y"))
        {
            System.out.println("As long as the green is hidden by cheddar, it'll happen!");
        }
        if (isBroccoli.equals("y"))
        {
            System.out.println("Oh, green stuff like that might as well go in the bin.");
        }
    }
    
    private static void fieldDay()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("What's your last name? ");
        String name = scan.nextLine();
        
        if (name.compareTo("Baggins") < 0)
        {
            System.out.println("You are on team Red Dragons!");
        }
        else if (name.compareTo("Baggins") > 0 && name.compareTo("Dresden") < 0)
        {
            System.out.println("You are on team Dark Wizards!");
        }
        else if (name.compareTo("Dresden") > 0 && name.compareTo("Howl") < 0)
        {
            System.out.println("You are on team Moving Castles!");
        }
        else if (name.compareTo("Howl") > 0 && name.compareTo("Potter") < 0)
        {
            System.out.println("You are on team Golden Snitches!");
        }
        else if (name.compareTo("Potter") > 0 && name.compareTo("Vimes") < 0)
        {
            System.out.println("You are on team Night Guards!");
        }
        else
        {
            System.out.println("You are on the team Black Holes!");
        }
    }
    
    private static void miniZork()
    {
        Scanner userInput = new Scanner(System.in);

        System.out.println("You are standing in an open field west of a white house,");
        System.out.println("With a boarded front door.");
        System.out.println("There is a small mailbox here.");
        System.out.print("Go to the house, or open the mailbox? ");

        String action = userInput.nextLine();

        if (action.equals("open the mailbox")) {
            System.out.println("You open the mailbox.");
            System.out.println("It's really dark in there.");
            System.out.print("Look inside or stick your hand in? ");
            action = userInput.nextLine();

            if (action.equals("look inside")) {
                System.out.println("You peer inside the mailbox.");
                System.out.println("It's really very dark. So ... so very dark.");
                System.out.print("Run away or keep looking? ");
                action = userInput.nextLine();

                if (action.equals("keep looking")) {
                    System.out.println("Turns out, hanging out around dark places isn't a good idea.");
                    System.out.println("You've been eaten by a grue.");
                } else if (action.equals("run away")) {
                    System.out.println("You run away screaming across the fields - looking very foolish.");
                    System.out.println("But you alive. Possibly a wise choice.");
                }
            } else if (action.equals("stick your hand in")) { }
        } else if (action.equals("go to the house")) { }
    }
    
}
