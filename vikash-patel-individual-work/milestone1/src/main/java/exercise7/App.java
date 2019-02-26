/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise7;
import java.util.Scanner;
/**
 *
 * @author vpatel
 */
public class App 
{
    public static void main(String[] args)
    {
        forAndTwentyBlackbirds();
        System.out.println("**********");
        springForwardFallBack();
        System.out.println("**********");
        forTimes();
        System.out.println("**********");
        forTimesFor();
        System.out.println("**********");
        theCount();
        System.out.println("**********");
        twoForsAndTenYearsAgo();
        System.out.println("**********");
        differentKettleOfFish();
        System.out.println("**********");
        forByFor();
        System.out.println("**********");
        traditionalFizzBuzz();
    }
    
    private static void forAndTwentyBlackbirds()
    {
        int birdsInPie = 0;
        for (int i = 1; i < 25; i++) {
            System.out.println("Blackbird #" + i + " goes into the pie!");
            birdsInPie++;
        }

        System.out.println("There are " + birdsInPie + " birds in there!");
        System.out.println("Quite the pie full!");
    }
    
    private static void springForwardFallBack()
    {
        //Mastered
    }
    
    private static void forTimes()
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("How many times tables shall I recite? ");
        int num = scan.nextInt();
        for(int i = 1; i <= 15; i++)
        {
            System.out.println(i+" * "+num+" is: "+(i*num));
        }
    }
    
    private static void forTimesFor()
    {
        //Mastered
    }
    
    private static void theCount()
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("*** I LOVE TO COUNT! LET ME SHARE MY COUNTING WITH YOU! ***");
        System.out.print("Start at: ");
        int start = scan.nextInt();
        System.out.print("Stop at: ");
        int stop = scan.nextInt();
        System.out.print("Count by: ");
        int inc = scan.nextInt();
        for(int i = start; i <= stop; i+= inc)
        {
            System.out.println(i);
        }
    }
    
    private static void twoForsAndTenYearsAgo()
    {
        //Mastered
    }
    
    private static void differentKettleOfFish()
    {
        //Mastered
    }
    
    private static void forByFor()
    {
        for (int i = 0; i < 3; i++) {
            System.out.print("|");

            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if(i==1 || j==1)   
                    {
                        if(i == 1 && j == 1)
                        {
                            System.out.print("#");
                        }
                        else if(j == 1)
                        {
                            System.out.print("$");
                        }
                        else if(i == 1)
                        {
                            System.out.print("@");
                        }
                    }
                    else {System.out.print("*");}
                }
                System.out.print("|");
            }
            System.out.println("");
        }
    }
    
    private static void traditionalFizzBuzz()
    {
        //Mastered
    }
}
