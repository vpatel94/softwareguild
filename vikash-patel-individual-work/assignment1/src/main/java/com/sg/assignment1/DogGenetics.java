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
public class DogGenetics 
{
    public static void main(String[] args) 
    {
        String name = findName();
        System.out.println("Well then, I have this highly reliable report on "
                + name + "'s prestigious background right here.");
        System.out.println("\n"+name+" is:\n");
        report();
    }
    
    private static void report()
    {
        Random rand = new Random();
        
        int b1 = rand.nextInt(101);
        int b2 = rand.nextInt(100-b1);
        int b3 = rand.nextInt(100-b1-b2);
        int b4 = rand.nextInt(100-b1-b2-b3);
        int b5 = 100-b1-b2-b3-b4; //No need to generate random as it is the last percentage remaining.
        
        System.out.println(b1 +"% " + "St. Bernard");
        System.out.println(b2 +"% " + "Chihuahua");
        System.out.println(b3 +"% " + "Dramatic RedNosed Asian Pug");
        System.out.println(b4 +"% " + "Common Cur");
        System.out.println(b5 +"% " + "King Doberman");
        System.out.println("\nWow that's QUITE the dog!");
    }
    
    private static String findName()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("What is your dog's name? ");
        return scan.nextLine();
    }
}


