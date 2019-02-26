/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringmastery;
import flooringmastery.controller.*;
import flooringmastery.dao.*;
import flooringmastery.service.*;
import flooringmastery.view.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author vpatel
 */
public class App 
{
    public static void main(String[] args) 
    {
//        UserIO myIO = new UserIOConsoleImpl();
//        FlooringMasteryView myView = new FlooringMasteryView(myIO);
//        FlooringMasteryDAO myDAO = new FlooringMasteryDAOFileImpl();
//        FlooringMasteryService myService = new FlooringMasteryService(myDAO);
//        FlooringMasteryController myController = new FlooringMasteryController(myService, myView);
        
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringMasteryController myController = ctx.getBean("myController", FlooringMasteryController.class);
        myController.run();
    }
}
