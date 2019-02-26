/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingmachine.ui;
import vendingmachine.dto.*;
import java.util.*;
import java.math.BigDecimal;

/**
 *
 * @author vpatel
 */
public class VendingMachineView 
{
    UserIO io;
    
    public VendingMachineView(UserIO io)
    {
        this.io = io;
    }
    
    public void printWelcome() 
    {
        io.println("*********************************************************");
        io.println("Welcome to the Vending Machine!");
    }
    
    public void printItems(List<Item> items)
    {
        io.println("Available Items:");
        for(Item i : items)
        {
            io.println(i.getName() + " --- Cost: $" + i.getCost());
        }
        io.println("*********************************************************");
    }
    
    public int keepRunning()
    {
        return io.readInt("Enter 0 to exit, or 1 to continue: ");
    }
    
    public BigDecimal collectMoney()
    {
        float f = io.readFloat("Please insert money before continuing: $");
        BigDecimal bd = new BigDecimal(f).setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd;
    }
    
    public String getSelection()
    {
        return io.readString("Which item would you like to purchase? ");
    }
    
    public void printPostVend(String s) 
    {
        String choice = s.substring(0, 1).toUpperCase() + s.substring(1);
        io.println("Your " + choice + " is on the way! Enjoy!");
    }
    
    public void printExit()
    {
        io.println("Thank you for using the Vending Machine! Goodbye.");
    }
    
    public void printChange(BigDecimal change)
    {
        io.println("*********************************************************");
        io.println("Thank you for your purchase. Here is your change: $" + change);
    }
    
    public void printChangeBreakdown(int[] change)
    {
        io.println("Change Breakdown:");
        io.println("Quarters: " + change[0]);
        io.println("Dimes: " + change[1]);
        io.println("Nickels: " + change[2]);
        io.println("Pennies: " + change[3]);
        io.println("Your change is being dispensed now.");
    }
    
    public void displayErrorMessage(String errorMsg) 
    {
        io.println("=== ERROR ===");
        io.println(errorMsg);
    }

}
