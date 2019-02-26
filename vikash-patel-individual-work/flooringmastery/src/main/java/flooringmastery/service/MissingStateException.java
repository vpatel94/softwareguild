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
public class MissingStateException extends Exception
{
    public MissingStateException(String message) {
        super(message);
    }

    public MissingStateException(String message,
            Throwable cause) {
        super(message, cause);
    }
}
