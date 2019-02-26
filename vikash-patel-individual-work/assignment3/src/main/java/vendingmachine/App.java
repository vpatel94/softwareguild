/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingmachine;
import vendingmachine.controller.*;
import vendingmachine.dao.*;
import vendingmachine.service.*;
import vendingmachine.ui.*;
/**
 *
 * @author vpatel
 */
public class App 
{
    public static void main(String[] args) 
    {
        UserIO myIO = new UserIOConsoleImpl();
        VendingMachineView myView = new VendingMachineView(myIO);
        VendingMachineDAO myDAO = new VendingMachineDAOFileImpl();
        VendingMachineService myService = new VendingMachineService();
        VendingMachineController myController = new VendingMachineController(myService, myView);
        myController.run();
    }
}
