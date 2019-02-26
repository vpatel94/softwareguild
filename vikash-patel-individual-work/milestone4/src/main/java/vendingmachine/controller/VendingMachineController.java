/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingmachine.controller;
import vendingmachine.dao.*;
import vendingmachine.dto.*;
import vendingmachine.service.*;
import vendingmachine.ui.*;
import java.util.*;
import java.math.BigDecimal;
/**
 *
 * @author vpatel
 */
public class VendingMachineController 
{
    VendingMachineService service;
    VendingMachineView view;
    
    public VendingMachineController(VendingMachineService service, VendingMachineView view)
    {
        this.service = service;
        this.view = view;
    }
    
    public void run()
    {
        while(true)
        {    
            welcome();
            int i = view.keepRunning();
            if(i == 0)
            {
                view.printExit();
                break;
            }
            collectAndVend();
        }
    }
    
    public void welcome()
    {
        try{
            service.startMachine();
            view.printWelcome();
            view.printItems(service.getItems());
        } catch (VendingMachineDAOException e)
        {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    public void collectAndVend()
    {
        BigDecimal money = view.collectMoney();
        String selection = view.getSelection();
        int[] change;
        try{
            service.vendItem(selection, money);
            view.printChange(service.showChange());
            change = service.calcChange();
            view.printChangeBreakdown(change);
            view.printPostVend(selection);
        } catch (OutOfStockException | InsufficientFundsException | 
                    InvalidSelectionException | VendingMachineDAOException e)
        {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
}
