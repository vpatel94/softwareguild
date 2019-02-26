/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise8;
import java.util.Random;
/**
 *
 * @author vpatel
 */
public class App 
{
    public static void main(String[] args) 
    {
        aBeginning();
        System.out.println("**********");
        returnToSender();
        System.out.println("**********");
        matchWork();
        System.out.println("**********");
        barelyControlledChaos();
    }
    
    private static void barelyControlledChaos()
    {
        String color = randColor(); // call color method here 
        String animal = randAnimal(); // call animal method again here 
        String colorAgain = randColor(); // call color method again here 
        int weight = randInt(5,200); // call number method, 
            // with a range between 5 - 200 
        int distance = randInt(10,20); // call number method, 
            // with a range between 10 - 20 
        int number = randInt(10000,20000); // call number method, 
            // with a range between 10000 - 20000 
        int time = randInt(2,6); // call number method, 
            // with a range between 2 - 6            
    
        System.out.println("Once, when I was very small...");

        System.out.println("I was chased by a " + color + ", "
            + weight + "lb " + " miniature " + animal 
            + " for over " + distance + " miles!!");

        System.out.println("I had to hide in a field of over " 
            + number + " " + colorAgain + " poppies for nearly " 
            + time + " hours until it left me alone!");

        System.out.println("\nIt was QUITE the experience, " 
            + "let me tell you!");
    }
    
    static String randColor()
    {
        Random rand = new Random();
        int num = rand.nextInt(5);
        String s;
        switch(num){
            case 0: s = "Red"; break;
            case 1: s = "Blue"; break;
            case 2: s = "Green"; break;
            case 3: s = "Yellow"; break;
            case 4: s = "Black"; break;
            default: s=""; break;
        }
        return s;
    }
    
    static String randAnimal()
    {
        Random rand = new Random();
        int num = rand.nextInt(5);
        String s;
        switch(num){
            case 0: s = "Bear"; break;
            case 1: s = "Cat"; break;
            case 2: s = "Dog"; break;
            case 3: s = "Hawk"; break;
            case 4: s = "Rabbit"; break;
            default: s=""; break;
        }
        return s;
    }
    
    static int randInt(int min, int max)
    {
        Random rand = new Random();
        int num = rand.nextInt(max+1) + min;
        return num;
    }
    
    private static void matchWork()
    {
        //Mastered
    }
    
    private static void returnToSender()
    {
        //Mastered
    }
    
    private static void aBeginning()
    {
        eatMe();
        drinkMe();
        System.out.println("\n ― Lewis Carroll, Alice in Wonderland");
    }
    
    static void eatMe(){
        System.out.println(" 'But I don’t want to go among mad people,' Alice remarked.");
        System.out.println(" 'Oh, you can’t help that,' said the Cat.");
        System.out.print(" 'We’re all mad here. I’m mad. You’re mad.'");
    }

    static void drinkMe(){
        System.out.println(" 'How do you know I’m mad?' said Alice.");
        System.out.println(" 'You must be,' said the Cat, 'or you wouldn’t have come here.' ");
    }
}
