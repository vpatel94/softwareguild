/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise5;

/**
 *
 * @author vpatel
 */
public class App 
{
    static String prompt1 = "Please enter a number: ";
    static String prompt2 = "Please enter another number: ";
    
    
    public static void main(String[] args) 
    {
        InOut io1 = new InOut();
        io1.print("The sum is: " + SimpleCalculator.add(io1.readInt(prompt1), io1.readInt(prompt2)));
    }
}
