/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingmachine.dao;

/**
 *
 * @author vpatel
 */
public class VendingMachineDAOException extends Exception
{
    public VendingMachineDAOException(String message) {
        super(message);
    }

    public VendingMachineDAOException(String message,
            Throwable cause) {
        super(message, cause);
    }
}
