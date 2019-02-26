/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classroster;
import classroster.controller.*;
import classroster.dao.*;
import classroster.ui.*;
/**
 *
 * @author vpatel
 */
public class App 
{
    public static void main(String[] args) 
    {
        UserIO myIO = new UserIOConsoleImpl();
        ClassRosterView myView = new ClassRosterView(myIO);
        ClassRosterDAO myDAO = new ClassRosterDAOFileImpl();
        ClassRosterController controller = new ClassRosterController(myDAO, myView);
        controller.run();
    }   
}
