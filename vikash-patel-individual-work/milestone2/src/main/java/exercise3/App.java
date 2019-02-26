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
    public static int num1;
    public static int num2;
    
    public static void main(String[] args) 
    {
        boolean run = true;
        while(run)
        {
            int choice = prompt();
            switch(choice)
            {
                case 1:
                    prompt2("add");
                    System.out.println("The sum is: " + SimpleCalculator.add(num1, num2));
                    break;
                case 2:
                    prompt2("subtract");
                    System.out.println("The difference is: " + SimpleCalculator.sub(num1, num2));
                    break;
                case 3:
                    prompt2("multiply");
                    System.out.println("The product is: " + SimpleCalculator.multiply(num1, num2));
                    break;
                case 4:
                    prompt2("divide");
                    System.out.println("The quotient is: " + SimpleCalculator.divide(num1, num2));
                    break;
                case 5:
                    System.out.println("Thank you for using SimpleCalculator!");
                    run = false;
                    break;
                default:
                    System.out.println("Invalid input, try again.");
                    break;
            }
        }
    }
    
    public static int prompt()
    {
        System.out.println("Welcome to the SimpleCalculator!");
        System.out.println("What operation would you like to perform today?");
        System.out.print("Add(1), Subtract(2), Multiply(3), Divide(4), or Quit(5): ");
        Scanner scan = new Scanner(System.in);
        return scan.nextInt();
    }
    
    public static void prompt2(String op)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Which two numbers would you like to " + op + "? ");
        System.out.print("Number 1: ");
        num1 = scan.nextInt();
        System.out.print("Number 2: ");
        num2 = scan.nextInt();
    }
    
}
