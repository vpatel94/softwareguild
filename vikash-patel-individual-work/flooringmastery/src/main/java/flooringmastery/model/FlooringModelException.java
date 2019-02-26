/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringmastery.model;

/**
 *
 * @author vpatel
 */
public class FlooringModelException extends Exception
{
    public FlooringModelException(String message) {
        super(message);
    }

    public FlooringModelException(String message,
            Throwable cause) {
        super(message, cause);
    }
}
