/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringmastery.controller;
import flooringmastery.model.*;
import flooringmastery.dao.*;
import flooringmastery.view.*;
import flooringmastery.service.*;
import java.util.*;
import java.math.BigDecimal;
/**
 *
 * @author vpatel
 */
public class FlooringMasteryController 
{
    private FlooringMasteryService myService;
    private FlooringMasteryView myView;
    private boolean prod = false; 
    
    public FlooringMasteryController(FlooringMasteryService service, FlooringMasteryView view)
    {
        this.myService = service;
        this.myView = view;
    }
    
    public void run()
    {
        boolean run = true;
        welcome();
        do
        {
            myView.printBreak();
            myView.printMenu();
            int menuChoice = myView.promptMenuChoice();
            switch(menuChoice)
            {
                case 1: // Display Orders
                {
                    display();
                    break;
                }
                case 2: // Add an Order
                {
                    add();
                    break;
                }
                case 3: // Edit an Order
                {
                    edit();
                    break;
                }
                case 4: // Remove an Order
                {
                    remove();
                    break;
                }
                case 5: // Save work
                {
                    save(myService.getCurrentDate());
                    break;
                }
                case 6: // Quit
                {
                    run = false;
                    break;
                }
                default:
                    myView.printInvalidEntry();
                    break;
            }
        } while(run);
    }
    
    public void welcome()
    {
        myView.welcome();
        OUTER:
        do {
            int i = myView.welcomePromptPIN();
            switch (i) {
                case 0:
                    myView.welcomeTraining();
                    break OUTER;
                case 1234:
                    prod = true;
                    myView.welcomeProduction();
                    break OUTER;
                default:
                    myView.displayErrorMessage("Incorrect PIN. Try again.");
                    break;
            }
        } while (true);
    }
    
    public void display()
    {
        try
        {
            myView.printBreak();
            myView.printDisplayOrdersBanner();
            String date = myView.promptDate();
            List<Order> orders = myService.getOrders(date);
            for (Order o : orders)
            {
                myView.printBreak();
                myView.printOrder(o);
            }
        } catch (FlooringMasteryDAOException e)
        {
            myView.displayErrorMessage(e.getMessage());
        }
    }
    
    public void add()
    {
        try{
            myView.printBreak();
            myView.printAddBanner();
            String name = myView.promptName();
            BigDecimal area = myView.promptArea();
            String state = myView.promptState();
            String material = myView.promptMaterialType();
            Order o = myService.newOrder(name, area, state, material);
            myView.printBreak();
            myView.printOrderBanner();
            myView.printOrder(o);
            myView.printBreak();
            String choice = myView.promptAddConfirm();
            if(choice.toUpperCase().equals("Y"))
            {
                myService.addOrder(o);
                myView.printAddConfirmed();
            }
            else
            {
                myView.printAddDeleted();
            }
            save(myService.getCurrentDate());
        } catch (FlooringMasteryDAOException | MissingStateException | MissingMaterialException e)
        {
            myView.displayErrorMessage(e.getMessage());
        }
    }
    
    public void remove()
    {
        try{
            myView.printBreak();
            myView.printRemoveBanner();
            String date = myView.promptDate();
            int orderNum = myView.promptOrderNumber();
            Order o = myService.getOrder(date, orderNum);
            myView.printBreak();
            myView.printOrderBanner();
            myView.printOrder(o);
            myView.printBreak();
            String choice = myView.promptRemoveConfirm();
            if(choice.toUpperCase().equals("Y"))
            {
                myService.removeOrder(date, orderNum);
                myView.printRemoveConfirmed();
            }
            else
            {
                myView.printRemoveDeleted();
            }
            save(date);
        } catch (FlooringMasteryDAOException | MissingOrderNumException e)
        {
            myView.displayErrorMessage(e.getMessage());
        }
    }
    
    public void edit()
    {
        try{
            myView.printBreak();
            myView.printEditBanner();
            String date = myView.promptDate();
            int orderNum = myView.promptOrderNumber();
            Order o = myService.getOrder(date, orderNum);
            myView.printBreak();
            myView.printOrderBanner();
            myView.printOrder(o);
            myView.printBreak();
            myView.printEditOptions();
            int choice = myView.promptEditChoice();
            switch(choice)
            {
                case 1: //Name
                    String name = myView.promptNewName();
                    myService.editOrderName(date, orderNum, name);
                    break;
                case 2: //Area
                    BigDecimal area = myView.promptNewArea();
                    myService.editOrderArea(date, orderNum, area);
                    break;
                case 3: //State
                    String state = myView.promptNewState();
                    myService.editOrderState(date, orderNum, state);
                    break;
                case 4: //Material
                    String material = myView.promptNewMaterial();
                    myService.editOrderMaterial(date, orderNum, material);
                    break;
                default: 
                    myView.displayErrorMessage("Incorrect option. Try again.");
                    break;
            }
            save(date);
        } catch(FlooringMasteryDAOException | MissingOrderNumException | MissingStateException | MissingMaterialException e)
        {
            myView.displayErrorMessage(e.getMessage());
        }
    }
    
    public void save(String date)
    {
        try{
            myService.save(date, prod);
            myView.printSaveConfirmation();
        } catch (FlooringMasteryDAOException | TrainingModelException e)
        {
            myView.displayErrorMessage(e.getMessage());
        }
    }
    
}
