/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.assignment1;
import java.util.*;

/**
 *
 * @author vpatel
 */
public class RockPaperScissors 
{
    
    static int userWins = 0, cpuWins = 0, ties=0;
    public static void main(String[] args) 
    {
        boolean play;
        do
        {
            System.out.println("**********************************");
            System.out.println("Welcome to Rock, Paper, Scissors!");
            
            int rounds = askRounds();
            System.out.println();
            if(rounds > 10 || rounds < 1)
            {
                System.out.println("You must play at least 1 round, and at most 10 rounds.");
                break; //quits the game if rounds input is not within range
            }
            
            playGame(rounds);
            System.out.println();
            play = playAgain();
        }
        while(play);
    }
    
    private static void playGame(int rounds)
    {
        Random rand = new Random();
        for(int i = 0; i < rounds; i++)
        {
            String user = askChoice();
            String cpu = choiceToString(rand.nextInt(3)+1);
            System.out.println();

            if(user.equals(cpu))
            {
                System.out.println("You and CPU both picked the same thing! This round ends in a tie.");
                ties++;
            }
            else
            {
                findWinner(user, cpu);
            }     
            System.out.println();
        }
        System.out.println("Game over! Let's see the results:");
        System.out.println("User wins: " + userWins);
        System.out.println("CPU wins: " + cpuWins);
        System.out.println("Ties: " + ties);
        if(userWins>cpuWins)
            {System.out.println("You win the game!");}
        else if(cpuWins>userWins)
            {System.out.println("CPU wins the game!");}
        else //Tie
            {System.out.println("The game ends in a tie!");}
    }
    
    private static void findWinner(String user, String cpu)
    {
        switch(user)
        {
            case "Rock":
                switch(cpu)
                {
                    case "Paper":
                        System.out.println("CPU chose Paper, which wraps Rock! CPU wins this round.");
                        cpuWins++;
                        break;
                    case "Scissors":
                        System.out.println("You chose Rock, which breaks Scissors(CPU)! You win this round.");
                        userWins++;
                        break;
                }
                break;
            case "Paper":
                switch(cpu)
                {
                    case "Rock":
                        System.out.println("You chose Paper, which covers Rock(CPU)! You win this round.");
                        userWins++;
                        break;
                    case "Scissors":
                        System.out.println("CPU chose Scissors, which cuts Paper! CPU wins this round.");
                        cpuWins++;
                        break;
                }
                break;
            case "Scissors":
                switch(cpu)
                {
                    case "Rock":
                        System.out.println("CPU chose Rock, which breaks Scissors! CPU wins this round.");
                        cpuWins++;
                        break;
                    case "Paper":
                        System.out.println("You chose Scissors, which cuts Paper(CPU)! You win this round.");
                        userWins++;
                        break;
                }
                break;
        }
    }
    
    private static String askChoice()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("Rock(1), Paper(2), or Scissors(3)? ");
        int user = scan.nextInt();
        return choiceToString(user);
    }
    
    private static String choiceToString(int user)
    {
        switch (user)
        {
            case 1: return "Rock";
            case 2: return "Paper";
            case 3: return "Scissors";
            default: 
                System.out.println("Invalid choice, you will be given Rock.");
                return "Rock";
        }
    }
    
    private static int askRounds()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("How many rounds would you like to play? ");
        return scan.nextInt();
    }
    
    private static boolean playAgain()
    {
        System.out.print("Would you like to play again? (y/n): ");
        Scanner scan = new Scanner(System.in);
        String again = scan.nextLine();
        switch(again)
        {
            case "y": 
                userWins = 0;
                cpuWins = 0;
                ties = 0;
                return true;
            case "n": 
                System.out.println("Thanks for playing!");
                return false;
            default: 
                System.out.println("Incorrect input, the game will now end.");
                return false;
        }
    }
}
