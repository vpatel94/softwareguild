/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flooringmastery.service;

/**
 *
 * @author vpatel
 */
public class MissingOrderNumException extends Exception
{
    public MissingOrderNumException(String message) {
        super(message);
    }

    public MissingOrderNumException(String message,
            Throwable cause) {
        super(message, cause);
    }
}
