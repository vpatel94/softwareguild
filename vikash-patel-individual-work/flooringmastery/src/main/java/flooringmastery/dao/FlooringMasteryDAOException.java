/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringmastery.dao;

/**
 *
 * @author vpatel
 */
public class FlooringMasteryDAOException extends Exception
{
    public FlooringMasteryDAOException(String message) {
        super(message);
    }

    public FlooringMasteryDAOException(String message,
            Throwable cause) {
        super(message, cause);
    }
}
