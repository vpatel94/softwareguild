/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise6;
import java.util.Scanner;

/**
 *
 * @author vpatel
 */
public class Factorizor 
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("What number would you like to factor? ");
        int user = scan.nextInt();
        int numfactors = 0;
        int perfectcount = 0;
        System.out.println("The factors of " + user + " are: ");
        for(int i = user-1; i > 0; i--)
        {
            if (user % i == 0)
            {
                System.out.println(i);
                numfactors++;
                perfectcount += i;
            }
        }
        output(user, perfectcount, numfactors);
    }
    
    private static void output(int user, int perfectcount, int numfactors)
    {
        System.out.println("Total number of factors: " + numfactors);
        isPerfect(perfectcount, user);
        isPrime(numfactors, user);
    }
    
    private static void isPerfect(int perfectcount, int user)
    {
        if(perfectcount == user)
        {
            System.out.println(user + " is a perfect number!");
        }
        else
        {
            System.out.println(user + " is not a perfect number.");
        }
    }
    
    private static void isPrime(int numfactors, int user)
    {
        if(numfactors == 1)
        {
            System.out.println(user + " is a prime number! (Only 1 and itself)");
        }
        else
        {
            System.out.println(user + " is not a prime number.");
        }
    }
    
}
