/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringmastery.view;
import java.math.BigDecimal;
import flooringmastery.model.*;
/**
 *
 * @author vpatel
 */
public class FlooringMasteryView 
{
    private UserIO io;
    
    public FlooringMasteryView(UserIO io)
    {
        this.io = io;
    }
    
    public void printBreak()
    {
        io.println("****************************************");
    }
    
    public void welcome()
    {
        io.println("****************************************");
        io.println("< < Flooring Program > >");
    }
    
    public int welcomePromptPIN()
    {
        return io.readInt("Enter 0 for Training Mode, or enter PIN to go into Production: "); //PIN = 1234
    }
    
    public void welcomeTraining()
    {
        io.println("*** TRAINING MODE - NO CHANGES WILL BE SAVED TO FILE ***");
    }
    
    public void welcomeProduction()
    {
        io.println("*** PRODUCTION MODE ***");
    }
    
    public void printMenu()
    {
        io.println("< < Flooring Program Menu > >");
        io.println("1. Display Orders");
        io.println("2. Add an Order");
        io.println("3. Edit an Order");
        io.println("4. Remove an Order");
        io.println("5. Save Current Work");
        io.println("6. Quit");
    }
    
    public int promptMenuChoice()
    {
        return io.readInt("Please enter the number of one of the choices above: ");
    }
    
    public String promptDate()
    {
        return io.readString("Please enter the date (MMDDYYYY) of the orders you would like to see: ");
    }
    
    public int promptOrderNumber()
    {
        return io.readInt("Please enter the order number: ");
    }
    
    public void printDisplayOrdersBanner()
    {
        io.println("Display Orders");
    }
    
    public void printOrderBanner()
    {
        io.println("Displaying Order Information:"); 
    }
    
    public void printOrder(Order o)
    {
        io.println("Order Number: " + o.getOrderNum());
        io.println("Customer Name: " + o.getName());
        io.println("State: " + o.getState());
        io.println("Tax Rate: " + o.getTaxRate() + "%");
        io.println("Material Type: " + o.getMaterialType());
        io.println("Area Requested: " + o.getArea() + " sqft");
        io.println("Material Cost Per Square Foot: $" + o.getMaterialCostPerArea() + "/sqft");
        io.println("Labor Cost Per Square Foot: $" + o.getLaborCostPerArea() + "/sqft");
        io.println("Total Material Cost: $" + o.getTotalMaterialCost());
        io.println("Total Labor Cost: $" + o.getTotalLaborCost());
        io.println("Total Tax: $" + o.getTotalTax());
        io.println("Total Cost: $" + o.getTotalCost());
    }
    
    public void printAddBanner()
    {
        io.println("Add an Order:");
    }
    
    public String promptName()
    {
        return io.readString("Please enter the name of the customer: ");
    }
    
    public BigDecimal promptArea()
    {
        float f = io.readFloat("Please enter the square footage requested by the customer (up to two decimals): ");
        return new BigDecimal(f).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    
    public String promptState()
    {
        return io.readString("Please enter the state abbreviation (ex. NC for North Carolina): ");
    }
    
    public String promptMaterialType()
    {
        return io.readString("Please enter the material requested by the customer: ");
    }
    
    public String promptAddConfirm()
    {
        return io.readString("Would you like to commit the new order? (Y/N): ");
    }
    
    public void printAddConfirmed()
    {
        io.println("New order successfully added.");
    }
    
    public void printAddDeleted()
    {
        io.println("Order information discarded.");
    }

    public void printRemoveBanner()
    {
        io.println("Remove an Order:");
    }
    
    public String promptRemoveConfirm()
    {
        return io.readString("Please confirm to delete this order (Y/N): ");
    }
    
    public void printRemoveConfirmed()
    {
        io.println("Order successully deleted.");
    }
    
    public void printRemoveDeleted()
    {
        io.println("Order was not deleted.");
    }
    
    public void printEditBanner()
    {
        io.println("Edit an Order:");
    }
    
    public void printEditOptions()
    {
        io.println("1. Edit Name");
        io.println("2. Edit Area Requested");
        io.println("3. Edit State");
        io.println("4. Edit Material Requested");
    }
    
    public int promptEditChoice()
    {
        return io.readInt("Please enter the number of one of the choices above: ");
    }
    
    public String promptNewName()
    {
        return io.readString("Please enter a new name: ");
    }
    
    public BigDecimal promptNewArea()
    {
        float f = io.readFloat("Please enter a new square footage: ");
        return new BigDecimal(f).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    
    public String promptNewState()
    {
        return io.readString("Please enter a new state abbreviation: ");
    }
    
    public String promptNewMaterial()
    {
        return io.readString("Please enter a new material: ");
    }
    
    public void printSaveConfirmation()
    {
        io.println("Changes successfully saved to file.");
    }
    
    public void printInvalidEntry()
    {
        io.println("Invalid entry, try again.");
    }
    
    public void displayErrorMessage(String errorMsg) 
    {
        io.println("=== ERROR ===");
        io.println(errorMsg);
    }
    
}
