/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviedatabase;
import moviedatabase.controller.*;
import moviedatabase.dao.*;
import moviedatabase.ui.*;
/**
 *
 * @author vpatel
 */
public class App 
{
    public static void main(String[] args) 
    {
        UserIO myIO = new UserIOConsoleImpl();
        MovieDatabaseView myView = new MovieDatabaseView(myIO);
        MovieDatabaseDAO myDAO = new MovieDatabaseDAOFileImpl();
        MovieDatabaseController controller = new MovieDatabaseController(myDAO, myView);
        controller.run();
    }
}
